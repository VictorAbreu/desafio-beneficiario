# API Beneficiario

Projeto Beneficiários desenvolvido como desafio técnico.

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- Spring Boot Starter OAuth2 Resource Server
- H2 Database
- Swagger (Springdoc OpenAPI)
- Lombok

## Como Rodar o Projeto

### Pré-requisitos

- Java 21
- Maven
- Git
- IDE de sua preferência (recomendado: Spring Tools Suite 4 ou Eclipse)

### Passo a Passo

Clone o repositório:
git clone https://github.com/VictorAbreu/desafio-beneficiario

Abra o projeto na IDE:
- Importe o projeto como um projeto Maven utilizando o arquivo `pom.xml`.

Instale as dependências:
- O Maven deve baixar todas as dependências automaticamente ao importar o projeto.

Configure o banco de dados:
- O projeto utiliza o banco de dados em memória H2.
- Um seed foi criado para os Users e Roles:
  - Usuário: `alex@gmail.com`, Role: `ROLE_OPERATOR`, Senha: `123456`
  - Usuário: `maria@gmail.com`, Role: `ROLE_OPERATOR`, `ROLE_ADMIN`, Senha: `123456`

Execute a aplicação:
- Execute a aplicação a partir da classe principal ou diretamente pela sua IDE.

### Parâmetros de Configuração

- As configurações estão parametrizadas no arquivo `application.properties` com o perfil configurado para `test`.

## Swagger

Este projeto utiliza o Swagger para documentar e testar os endpoints.

- Acesse a documentação do Swagger através do link:
  http://localhost:8080/swagger-ui/index.html

## Autor

- Victor Luiz de Abreu - https://github.com/VictorAbreu
