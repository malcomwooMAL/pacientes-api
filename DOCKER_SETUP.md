# Configuração do Ambiente de Desenvolvimento com Docker

Este guia fornece as instruções para executar o banco de dados PostgreSQL necessário para esta aplicação usando Docker e Docker Compose. Isso garante um ambiente de desenvolvimento consistente e isolado.

## Pré-requisitos

- [Docker](https://docs.docker.com/get-docker/) instalado na sua máquina.
- [Docker Compose](https://docs.docker.com/compose/install/) (geralmente já vem com o Docker Desktop).

## Instruções

1.  **Crie o arquivo `docker-compose.yml`**:
    Na raiz do projeto, crie um arquivo com o nome `docker-compose.yml`.

2.  **Copie o Conteúdo Abaixo**:
    Copie e cole o código a seguir dentro do arquivo `docker-compose.yml` que você acabou de criar.

    ```yaml
    version: '3.8'

    services:
      postgres_db:
        image: postgres:16
        container_name: consultorio_postgres
        environment:
          POSTGRES_DB: consultorio_db
          POSTGRES_USER: postgres
          POSTGRES_PASSWORD: 12345678
        ports:
          - "5432:5432"
        volumes:
          - postgres_data:/var/lib/postgresql/data
        restart: unless-stopped

    volumes:
      postgres_data:
    ```

3.  **Inicie o Contêiner**:
    Abra um terminal na raiz do projeto (onde o arquivo `docker-compose.yml` está localizado) e execute o seguinte comando:
    ```bash
    docker-compose up -d
    ```
    O `-d` no final faz com que o contêiner seja executado em modo "detached" (em segundo plano).

4.  **Verifique se o Contêiner está Rodando**:
    Você pode verificar o status do seu contêiner com o comando:
    ```bash
    docker-compose ps
    ```
    Você deverá ver o serviço `postgres_db` com o status "Up".

## Sobre a Configuração

- **`image: postgres:16`**: Utiliza a imagem oficial do PostgreSQL na versão 16.
- **`environment`**: As variáveis de ambiente `POSTGRES_DB`, `POSTGRES_USER`, e `POSTGRES_PASSWORD` são usadas para criar o banco de dados e o usuário com a senha correspondente. **Estes valores correspondem exatamente aos que estão configurados no arquivo `application.properties` da aplicação Spring Boot.**
- **`ports: - "5432:5432"`**: Mapeia a porta 5432 do seu computador (host) para a porta 5432 do contêiner, permitindo que a aplicação Spring Boot se conecte ao banco de dados através de `localhost:5432`.
- **`volumes`**: Cria um volume chamado `postgres_data` para persistir os dados do banco de dados. Isso significa que, mesmo que você pare ou remova o contêiner, seus dados não serão perdidos.

Para parar o contêiner, utilize o comando `docker-compose down` no mesmo diretório.
