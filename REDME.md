
---

# Projeto Prontuário API

Este é um projeto de exemplo de uma API REST desenvolvida com Spring Boot, que implementa um CRUD de documentos. O sistema permite o envio, atualização e consulta de documentos, sendo ideal para gerenciar registros de arquivos como PDFs.

## Funcionalidades

- **Criar documento**: Enviar um novo documento com nome, tipo, data de envio e conteúdo do arquivo.
- **Atualizar documento**: Atualizar os dados de um documento existente.
- **Consultar documento**: Recuperar os detalhes de um documento específico.
- **Listar todos os documentos**: Retornar todos os documentos armazenados.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 2.x**
- **JPA/Hibernate** para persistência de dados
- **JUnit 5** para testes unitários
- **MockMvc** para testes de integração da API

## Estrutura do Projeto

O projeto é dividido nas seguintes pastas principais:

- **`src/main/java`**: Contém a lógica da aplicação, incluindo:
    - **Controller**: Responsável por expor os endpoints da API.
    - **Service**: Contém a lógica de negócio para manipulação de documentos.
    - **Model**: Representa as entidades persistentes no banco de dados.
    - **Repository**: Interface para comunicação com o banco de dados.
    - **DTO (Data Transfer Object)**: Objetos utilizados para a transferência de dados.

- **`src/test/java`**: Contém os testes unitários e de integração da API, incluindo testes de controller e de serviço.

## Instruções de Uso

### Pré-requisitos

Certifique-se de ter o seguinte instalado em sua máquina:

- **Java 17 ou superior**
- **Maven** (ou **Gradle**, caso prefira usar outro gerenciador de dependências)

### Como rodar o projeto

1. Clone o repositório:

   ```bash
   git clone https://github.com/seuusuario/prontuario-api.git
   cd prontuario-api
   ```

2. Compile o projeto usando o Maven:

   ```bash
   mvn clean install
   ```

3. Execute o projeto:

   ```bash
   mvn spring-boot:run
   ```

4. A aplicação estará rodando em `http://localhost:8080`.

### Endpoints da API

1. **Criar Documento**

    - **Endpoint**: `POST /api/documents`
    - **Corpo**:
      ```json
      {
        "name": "Document 1",
        "documentType": "Type A",
        "sentDate": "2025-04-27",
        "documentFile": "file content as base64 or byte[]"
      }
      ```
    - **Resposta**:
      ```json
      {
        "id": 1,
        "name": "Document 1",
        "documentType": "Type A",
        "sentDate": "2025-04-27"
      }
      ```

2. **Atualizar Documento**

    - **Endpoint**: `PUT /api/documents/{id}`
    - **Corpo**:
      ```json
      {
        "name": "Updated Document",
        "documentType": "Type B",
        "sentDate": "2025-04-28",
        "documentFile": "updated file content as byte[]"
      }
      ```

3. **Consultar Documento por ID**

    - **Endpoint**: `GET /api/documents/{id}`
    - **Resposta**:
      ```json
      {
        "id": 1,
        "name": "Document 1",
        "documentType": "Type A",
        "sentDate": "2025-04-27"
      }
      ```

4. **Listar Todos os Documentos**

    - **Endpoint**: `GET /api/documents`
    - **Resposta**:
      ```json
      [
        {
          "id": 1,
          "name": "Document 1",
          "documentType": "Type A",
          "sentDate": "2025-04-27"
        },
        {
          "id": 2,
          "name": "Document 2",
          "documentType": "Type B",
          "sentDate": "2025-04-28"
        }
      ]
      ```

## Testes

Os testes estão localizados na pasta `src/test/java` e incluem:

- **DocumentControllerTest**: Testes de integração para a API REST utilizando `MockMvc`.
- **DocumentServiceTest**: Testes unitários para a lógica de serviço.

### Como rodar os testes

Para rodar os testes, basta executar:

```bash
mvn test
```

## Contribuições

Contribuições são bem-vindas! Se você tem alguma melhoria ou correção, basta abrir um **Pull Request**.



---

