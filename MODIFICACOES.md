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

- **OAuth 2.0 Resource Server**: A aplicação foi configurada como um "Resource Server". Agora, todos os endpoints sob `/api/**` são protegidos e exigem um token de acesso (Bearer Token) válido para serem acessados.
- **Configuração Centralizada**: A integração com o servidor de autorização (que é um serviço externo) é gerenciada através do arquivo `application.properties`, permitindo fácil alteração dos parâmetros (`introspection-uri`, `client-id`, `client-secret`) sem a necessidade de recompilar o código.

## 4. Estrutura do Projeto

A arquitetura do projeto foi mantida e expandida seguindo o padrão de camadas:
- **`dto`**: Um novo pacote foi criado para abrigar Data Transfer Objects, como o `AgendamentoRequest.java`, melhorando o desacoplamento entre a API e o modelo de dados interno.
- **`config`**: Um novo pacote foi criado para as classes de configuração, como a `SecurityConfig.java`.
