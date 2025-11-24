# Resumo das Modificações no Backend do Consultório

Este documento consolida as principais alterações e novas funcionalidades implementadas no projeto de backend do consultório odontológico.

## 1. Novas Funcionalidades

### Gestão de Dentistas
- **Entidade `Dentista`**: Foi criada uma nova entidade para representar os dentistas, com os campos `id` (UUID), `nome` e `especialidade`.
- **API CRUD Completa**: Foram expostos os endpoints em `/api/dentistas` para criar, listar, buscar por ID, atualizar e deletar dentistas.

### Gestão de Consultas
- **Entidade `Consulta`**: Foi criada a entidade central do sistema, que relaciona um `Paciente` a um `Dentista` em uma `dataHora` específica.
- **API de Agendamento**: Foram criados os endpoints em `/api/consultas` para:
  - Agendar uma nova consulta (`POST /agendar`).
  - Cancelar uma consulta existente (`DELETE /{id}`).
  - Listar todas as consultas (`GET /`).
- **Consulta de Agenda do Dentista**: Foi adicionado um endpoint específico (`GET /api/consultas/dentista/{dentistaId}?dia=...`) para permitir que a interface do dentista busque todos os agendamentos de um dia específico.

## 2. Melhorias na Entidade `Paciente`

A entidade `Paciente` foi atualizada para armazenar mais informações relevantes:
- O campo `telefone` foi renomeado para `telefoneWhatsapp` para maior clareza.
- Foram adicionados os seguintes novos campos (configurados para aceitar valores nulos):
  - `dataUltimaConsulta`: Para registrar a data da última visita.
  - `ultimoProcedimento`: Um campo de texto para descrever o último procedimento realizado.
  - `dataUltimoContato`: Para registrar a data do último contato com o paciente.
- A lógica de atualização no `PacienteService` foi modificada para incluir esses novos campos.

## 3. Segurança da API

A segurança foi implementada utilizando **Spring Security** e **OAuth 2.0**, configurando a aplicação como um Resource Server.

- **OAuth 2.0 Resource Server**: A aplicação valida tokens opacos via introspecção.
- **Proteção de Endpoints**: Todos os endpoints sob `/api/**` exigem autenticação.
- **Dependências**: Adicionadas `spring-boot-starter-security` e `spring-boot-starter-oauth2-resource-server`.
- **Configuração**:
  - `SecurityConfig.java`: Define o `SecurityFilterChain` para autorizar requisições e configurar o servidor de recursos.
  - `application.properties`: Gerencia credenciais e URI de introspecção (`introspection-uri`, `client-id`, `client-secret`).

Para detalhes técnicos e de configuração, consulte [SECURITY.md](SECURITY.md).

## 4. Testes Automatizados

Os testes de controlador foram atualizados para suportar a camada de segurança:

- **Simulação de Usuário**: Uso da anotação `@WithMockUser` para simular um usuário autenticado em testes de unidade (`@WebMvcTest`).
- **Proteção CSRF**: Inclusão de tokens CSRF (`csrf()`) em requisições de modificação (POST, PUT, DELETE) nos testes.
- **Mocking**: Serviços continuam sendo mockados (`@MockBean`) para isolar o teste da camada de dados.

Para instruções de execução dos testes, consulte [TESTING.md](TESTING.md).

## 5. Estrutura do Projeto

A arquitetura do projeto foi mantida e expandida seguindo o padrão de camadas:
- **`dto`**: Um novo pacote foi criado para abrigar Data Transfer Objects, como o `AgendamentoRequest.java`, melhorando o desacoplamento entre a API e o modelo de dados interno.
- **`config`**: Um novo pacote foi criado para as classes de configuração, como a `SecurityConfig.java`.
