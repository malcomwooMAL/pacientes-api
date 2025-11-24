# Segurança da API (OAuth 2.0 Resource Server)

Este documento detalha a implementação de segurança do backend do consultório odontológico. A aplicação atua como um **Resource Server** OAuth 2.0, delegando a autenticação a um servidor de autorização externo.

## Visão Geral

A segurança é garantida pelo **Spring Security**. A aplicação não gerencia usuários ou senhas diretamente; em vez disso, ela valida tokens de acesso (Bearer Tokens) enviados pelos clientes (frontend).

### Fluxo de Autenticação

1. O cliente (frontend) obtém um token de acesso junto ao Servidor de Autorização.
2. O cliente envia uma requisição para esta API com o token no cabeçalho `Authorization`.
3. Esta API recebe o token e o envia para o endpoint de introspecção do Servidor de Autorização para validação.
4. Se o token for válido, a requisição é processada. Caso contrário, retorna `401 Unauthorized`.

## Configuração

A configuração de segurança é centralizada na classe `SecurityConfig.java` e no arquivo `application.properties`.

### Dependências

As seguintes dependências foram adicionadas ao `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
```

### Propriedades de Configuração

As credenciais do cliente (client-id e client-secret) e a URI de introspecção devem ser configuradas no `application.properties`:

```properties
# Configuração de Segurança - OAuth 2.0 Resource Server
spring.security.oauth2.resourceserver.opaque-token.introspection-uri=http://localhost:9000/oauth2/introspect
spring.security.oauth2.resourceserver.opaque-token.client-id=seu-client-id
spring.security.oauth2.resourceserver.opaque-token.client-secret=seu-client-secret
```

> **Nota**: Em ambiente de produção, certifique-se de usar variáveis de ambiente para injetar o `client-secret`.

### SecurityConfig.java

A classe `SecurityConfig` define:
- **Proteção de rotas**: Todas as requisições para `/api/**` exigem autenticação.
- **Resource Server**: Habilita o suporte a tokens opacos (`opaqueToken()`).

## Testando Endpoints Protegidos

Para acessar os endpoints protegidos, você deve incluir o token no cabeçalho da requisição:

**Header:**
```
Authorization: Bearer <seu_token_de_acesso>
```

**Exemplo com cURL:**
```bash
curl -X GET http://localhost:8080/api/pacientes \
     -H "Authorization: Bearer abc-123-xyz"
```
