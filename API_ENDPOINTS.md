# Documentação dos Endpoints da API

Este arquivo descreve todos os endpoints disponíveis na API do sistema de consultório odontológico.

**Nota Importante**: Todos os endpoints requerem um token de autenticação (Bearer Token) no cabeçalho `Authorization` da requisição.

---

## 1. Pacientes

**Prefixo da URL**: `/api/pacientes`

| Método HTTP | URI                               | Descrição                                         | Corpo da Requisição (JSON) Exemplo                                                                                                                              |
| :---------- | :-------------------------------- | :-------------------------------------------------- | :-------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `GET`       | `/`                               | Lista todos os pacientes cadastrados.               | N/A                                                                                                                                                             |
| `GET`       | `/{id}`                           | Busca um paciente específico pelo seu ID (UUID).    | N/A                                                                                                                                                             |
| `POST`      | `/`                               | Cadastra um novo paciente.                        | `{ "nome": "João da Silva", "cpf": "12345678901", "email": "joao@email.com", "telefoneWhatsapp": "11987654321", "dataNascimento": "1990-01-15" }`            |
| `PUT`       | `/{id}`                           | Atualiza os dados de um paciente existente.       | `{ "nome": "João da Silva Souza", "email": "joao.souza@email.com", ... }`                                                                                       |
| `DELETE`    | `/{id}`                           | Deleta o registro de um paciente.                 | N/A                                                                                                                                                             |

---

## 2. Dentistas

**Prefixo da URL**: `/api/dentistas`

| Método HTTP | URI                               | Descrição                                         | Corpo da Requisição (JSON) Exemplo                                    |
| :---------- | :-------------------------------- | :-------------------------------------------------- | :-------------------------------------------------------------------- |
| `GET`       | `/`                               | Lista todos os dentistas cadastrados.             | N/A                                                                   |
| `GET`       | `/{id}`                           | Busca um dentista específico pelo seu ID (UUID).    | N/A                                                                   |
| `POST`      | `/`                               | Cadastra um novo dentista.                        | `{ "nome": "Dr. Carlos Andrade", "especialidade": "Ortodontista" }`   |
| `PUT`       | `/{id}`                           | Atualiza os dados de um dentista existente.       | `{ "especialidade": "Clínico Geral" }`                                |
| `DELETE`    | `/{id}`                           | Deleta o registro de um dentista.                 | N/A                                                                   |

---

## 3. Consultas

**Prefixo da URL**: `/api/consultas`

| Método HTTP | URI                               | Descrição                                                                      | Corpo da Requisição (JSON) Exemplo                                                                               |
| :---------- | :-------------------------------- | :------------------------------------------------------------------------------- | :--------------------------------------------------------------------------------------------------------------- |
| `GET`       | `/`                               | Lista todas as consultas agendadas no sistema.                                 | N/A                                                                                                              |
| `GET`       | `/{id}`                           | Busca uma consulta específica pelo seu ID (UUID).                                | N/A                                                                                                              |
| `POST`      | `/agendar`                        | Agenda uma nova consulta para um paciente com um dentista.                     | `{ "pacienteId": "...", "dentistaId": "...", "dataHora": "2025-12-25T14:30:00" }` |
| `DELETE`    | `/{id}`                           | Cancela (deleta) uma consulta agendada.                                          | N/A                                                                                                              |
| `GET`       | `/dentista/{dentistaId}`          | Busca as consultas de um dentista específico para um determinado dia.            | N/A. Requer parâmetro de query, ex: `?dia=2025-12-25T00:00:00`                                                     |
