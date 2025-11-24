# Guia de Testes

Este projeto utiliza **JUnit 5** e **Mockito** para testes unitários e de integração, com suporte do **Spring Boot Test**.

## Executando os Testes

Para rodar todos os testes do projeto, utilize o comando Maven na raiz do projeto:

```bash
mvn test
```

## Estratégia de Testes para Endpoints Seguros

Como a API agora é protegida por Spring Security, os testes de controlador (`@WebMvcTest`) precisam simular um contexto de segurança autenticado.

### Anotações e Ferramentas

- **`@WebMvcTest`**: Foca apenas na camada web (Controllers), não carregando o contexto completo da aplicação.
- **`@MockBean`**: Utilizado para mockar a camada de serviço (`Service`), isolando o controlador.
- **`@WithMockUser`**: Anotação do `spring-security-test` que simula um usuário autenticado para o método de teste.
- **`csrf()`**: O `SecurityMockMvcRequestPostProcessors.csrf()` deve ser adicionado a requisições que alteram estado (POST, PUT, DELETE) para passar pela proteção CSRF habilitada por padrão nos testes.

### Exemplo de Teste

```java
@Test
@WithMockUser // Simula usuário autenticado
void testCadastrar() throws Exception {
    when(pacienteService.cadastrar(any(Paciente.class))).thenReturn(paciente);

    mockMvc.perform(post("/api/pacientes")
            .with(csrf().asHeader()) // Token CSRF necessário para POST
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(paciente)))
            .andExpect(status().isCreated());
}
```

## Requisitos do Ambiente de Teste

Para testes que envolvem a camada de persistência (ou testes de integração completos), é necessário que o banco de dados PostgreSQL esteja acessível conforme configurado no `application.properties`, pois o projeto não utiliza banco em memória (H2) para testes.

Certifique-se de que o container Docker esteja rodando:

```bash
docker-compose up -d
```
