# API Backend do Consultório

Este projeto é uma aplicação Spring Boot que fornece uma API RESTful para gerenciar agendamentos de um consultório odontológico. Permite gerenciar pacientes, dentistas e consultas.

## Tecnologias Utilizadas

* Java 17
* Spring Boot 3
* Maven
* PostgreSQL
* Spring Security (OAuth2/Opaque Token)

## Documentação

Para informações detalhadas sobre as alterações, implementação de segurança e testes, consulte os seguintes documentos:

* [**MODIFICACOES.md**](MODIFICACOES.md): Um resumo de todas as alterações, novas funcionalidades e melhorias feitas no projeto.
* [**SECURITY.md**](SECURITY.md): Explicação detalhada da implementação do Resource Server OAuth 2.0 e configuração de segurança.
* [**TESTING.md**](TESTING.md): Guia sobre como executar testes, abordando especificamente considerações de segurança em testes de controladores.
* [**API_ENDPOINTS.md**](API_ENDPOINTS.md): Lista de todos os endpoints da API disponíveis.

## Configuração

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/consultorio-backend.git
   ```

2. **Configure o banco de dados:**
   - Certifique-se de ter o PostgreSQL instalado e rodando.
   - Crie um banco de dados chamado `consultorio`.
   - Atualize o arquivo `application.properties` em `src/main/resources` com as credenciais do seu banco de dados:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/consultorio
     spring.datasource.username=seu-usuario
     spring.datasource.password=sua-senha
     ```

3. **Execute a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```

## Uso

A API fornece os seguintes endpoints:

### Pacientes

* `GET /api/pacientes`: Obter todos os pacientes.
* `GET /api/pacientes/{id}`: Obter um paciente por ID.
* `POST /api/pacientes`: Criar um novo paciente.
* `PUT /api/pacientes/{id}`: Atualizar um paciente.
* `DELETE /api/pacientes/{id}`: Deletar um paciente.

### Dentistas

* `GET /api/dentistas`: Obter todos os dentistas.
* `GET /api/dentistas/{id}`: Obter um dentista por ID.
* `POST /api/dentistas`: Criar um novo dentista.
* `PUT /api/dentistas/{id}`: Atualizar um dentista.
* `DELETE /api/dentistas/{id}`: Deletar um dentista.

### Consultas

* `POST /api/consultas/agendar`: Agendar uma nova consulta.
* `DELETE /api/consultas/{id}`: Cancelar uma consulta.
* `GET /api/consultas`: Obter todas as consultas.
* `GET /api/consultas/{id}`: Obter uma consulta por ID.
* `GET /api/consultas/dentista/{dentistaId}?dia={dia}`: Obter todas as consultas de um dentista em um dia específico.
