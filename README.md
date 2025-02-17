# Projeto Spring Boot com JSF, JPA e PostgreSQL

Este projeto é uma aplicação web desenvolvida com **Spring Boot**, **JSF (PrimeFaces)**, **JPA (Hibernate)** e **PostgreSQL**, implementando uma aplicação de Gerenciamento de Tarefas.

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- **JDK 11**
- **Apache Maven**
- **PostgreSQL** com um banco de dados configurado

## Criando do Banco de Dados

1. Crie um banco de dados PostgreSQL chamado `esig`:
## Baixando e Inicializando o Projeto

1. Clone o repositório para rodar o projeto localmente.
   ```sql
   $ git clone https://github.com/JuanVictorss/GerenciadorTarefasJSF.git
1. Importe o projeto na sua IDE de preferência.
2. Aguarde o carregamento da IDE.
3. Adicione as configurações do banco de dados no arquivo `application.properties`:
4.    ```sql
      spring.datasource.username=USUARIO_POSTGRES_AQUI
      spring.datasource.password=SUA_SENHA_AQUI

3. Inicialize o projeto.(Aqui serão criadas as tables)

## Populando tabela "responsavel" do Banco de Dados

1.    Utilize o comando SQL para inserção dos Responsáveis no banco de dados:
   2. ```sql
      INSERT INTO responsavel (nome) 
      VALUES ('João da Silva');
   
      INSERT INTO responsavel (nome)
      VALUES ('Maria Oliveira');
   
      INSERT INTO responsavel (nome)
      VALUES ('Carlos Souza');
   
      INSERT INTO responsavel (nome)
      VALUES ('Ana Costa');
   
      INSERT INTO responsavel (nome)
      VALUES ('Pedro Almeida');
   
      INSERT INTO responsavel (nome)
      VALUES ('Luana Pereira');
2. Agora acesse a aplicação e direcionar tarefas as pessoas adicionadas no banco de dados. 
3. ```sql     
      http://localhost:8080/GestaoTarefas.xhtml
## Itens Implementados

Os seguintes itens foram implementados no projeto:

- [x] : Cadastro, listagem, remoção e atualização de Tarefas
- [x] : Filtragem de Tarefa por nome


## Tecnologias utilizadas

- Spring Boot
- Java 11
- JSF
- PostgreSQL

## Licença

Este projeto está licenciado sob a licença Apache 2.0. Veja o arquivo LICENSE para mais informações.