import urllib.request
import urllib.error
import json
import sys

# --- Configurações ---
AUTH_SERVICE_URL = "http://localhost:9000"  # Porta alterada conforme nosso guia
PACIENTES_API_URL = "http://localhost:8080" # Porta padrão
USERNAME = "usuario_teste_integracao"
PASSWORD = "senha_secreta_123"

def print_step(message):
    print(f"\n{'='*50}")
    print(f"STEP: {message}")
    print(f"{'='*50}")

def make_request(url, method="GET", data=None, headers=None):
    if headers is None:
        headers = {}
    
    if data:
        json_data = json.dumps(data).encode('utf-8')
        headers["Content-Type"] = "application/json"
    else:
        json_data = None

    req = urllib.request.Request(url, data=json_data, headers=headers, method=method)
    
    try:
        with urllib.request.urlopen(req) as response:
            status = response.getcode()
            response_body = response.read().decode('utf-8')
            return status, response_body
    except urllib.error.HTTPError as e:
        return e.code, e.read().decode('utf-8')
    except Exception as e:
        print(f"Erro de conexão: {e}")
        sys.exit(1)

def main():
    print(">>> Iniciando Teste de Integração: Auth Service + Pacientes API <<<\n")

    # 1. Registrar Usuário no Auth Service
    print_step("1. Registrando usuário no Auth Service (Porta 9000)")
    payload_registro = {"username": USERNAME, "password": PASSWORD}
    status, body = make_request(f"{AUTH_SERVICE_URL}/api/auth/registrar", "POST", payload_registro)
    
    if status == 200:
        print(f"Sucesso! Usuário '{USERNAME}' registrado.")
    else:
        print(f"Nota: Se falhar (400/500), o usuário pode já existir. Continuando...")
        print(f"Status: {status} | Body: {body}")

    # 2. Realizar Login e Obter Token
    print_step("2. Realizando Login para obter Token (Porta 9000)")
    status, body = make_request(f"{AUTH_SERVICE_URL}/api/auth/login", "POST", payload_registro)
    
    token = None
    if status == 200:
        try:
            resp_json = json.loads(body)
            token = resp_json.get("access_token")
            print("Login realizado com sucesso!")
            print(f"Token recebido: {token[:20]}... (truncado)")
        except:
            print("Erro ao fazer parse do JSON de resposta.")
    else:
        print(f"Falha no login. Status: {status} | Body: {body}")
        sys.exit(1)

    # 3. Acessar Pacientes API (Sem Token) - Teste Negativo
    print_step("3. Testando acesso SEM TOKEN (Esperado: 401 Unauthorized)")
    status, body = make_request(f"{PACIENTES_API_URL}/api/pacientes", "GET")
    if status == 401:
        print("Sucesso! A API bloqueou o acesso não autorizado.")
    else:
        print(f"Falha! A API permitiu ou retornou outro erro. Status: {status}")

    # 4. Acessar Pacientes API (Com Token) - Teste Positivo
    print_step("4. Testando acesso COM TOKEN (Esperado: 200 OK)")
    headers = {"Authorization": f"Bearer {token}"}
    status, body = make_request(f"{PACIENTES_API_URL}/api/pacientes", "GET", headers=headers)
    
    if status == 200:
        print("Sucesso! Integração confirmada.")
        print("Resposta da API de Pacientes:")
        try:
            pacientes = json.loads(body)
            print(json.dumps(pacientes, indent=2))
        except:
            print(body)
    else:
        print(f"Falha na integração. Status: {status}")
        print(f"Body: {body}")
        print("\nDica: Verifique se o 'pacientes-api' está configurado com o client-id/secret corretos ('client' e 'secret').")

if __name__ == "__main__":
    main()