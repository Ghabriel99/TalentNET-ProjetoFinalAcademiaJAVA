# TalentNET-ProjetoFinalAcademiaJAVA
API REST Web desenvolvida para o Projeto final da Quinta Academia de JAVA da ATOS com a UFN

## Resumi da aplicação :

Este projeto é uma aplicação web REST desenvolvida em Java com Spring Boot no Backend, e Angular no Frontend. <br><br>
Funcionalidades da aplicação: <br>
Cadastro, atualização, remoção e listagem de Canidatos e Vagas para o sistema interno de uma empresa.
Aplicação protegida em todos seus endpoints com Spring Security e JWT (JSON WEB TOKEN)

## Pré-requisitos para executar o projeto :
Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- Java Development Kit (JDK) 8 ou superior
- Node.js
- Angular CLI
- Angular Material
- Git

## Configurações para compilar e executar o projeto:
### 1. Clonar o repositório

Clone este repositório para o seu ambiente local:
git clone https://github.com/Ghabriel99/TalentNET-ProjetoFinalAcademiaJAVA

### 2. Backend - Configuração Spring Boot

- Abra o projeto preferencialmente na IDE IntelliJ.
- Certifique-se de ter as dependências abaixo adicionadas ao seu arquivo `pom.xml` :
  - spring-boot-starter-web
  - spring-boot-starter-data-jpa
  - spring-boot-starter-validation
  - spring-boot-starter-test
  - spring-boot-starter-security
  - org.springframework.security
  - io.jsonwebtoken
 
- Crie um banco de dados no Mysql e configure as propriedades do banco de dados no arquivo `application.properties` ou `application.yml`. <br>
- application.properties: <br>
spring.datasource.url=jdbc:mysql://localhost/nomeDoBancoQueCriou <br>
spring.datasource.username=root <br>
spring.datasource.password=sua senha do MySql <br>
spring.jpa.hibernate.ddl-auto=update <br>
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect <br>
spring.jpa.show-sql=true <br>
spring.jpa.properties.hibernate.format_sql=true <br>

- Execute a aplicação Spring Boot para iniciar o Backend

### 3. Frontend - Configuração Angular

- Navegue até o diretório `Ghabriel-ProjetoFinal-frontend` do projeto atráves do comando:-> cd Ghabriel-ProjetoFinal-frontend
- Instale as dependências executando o seguinte comando:-> npm install
  
- Certifique-se de ter o Angular Material instalado, se não tiver, executar o comando:->  ng add @angular/material

- Execute o e compile o projeto Angular com o comando :
  ng serve ,  ou <br>
  ng serve --o (executa o projeto e abre diretamente no seu navegador)

### 4. Acesse a aplicação
Abra um navegador e acesse `http://localhost:4200`.

### 5. Funcionalidades da aplicação:
Tela de login aonde é feito a autenticação, e é gerado o token JWT para o usuário
![image](https://github.com/Ghabriel99/TalentNET-ProjetoFinalAcademiaJAVA/assets/86672683/21c03fd9-eded-4195-bd5e-c736bb17df5a)


## Contribuições

Dúvidas, sugestões ou críticas?

Se você quiser contribuir para este projeto, siga as etapas abaixo:

1. Faça um fork deste repositório.
2. Crie uma branch para sua nova funcionalidade (`git checkout -b feature/nova-funcionalidade`).
3. Faça commit de suas alterações (`git commit -am 'Adiciona nova funcionalidade'`).
4. Envie sua branch para o repositório remoto (`git push origin feature/nova-funcionalidade`).
5. Abra um Pull Request.

## Licença
Este projeto foi desenvolvido pelo aluno Ghabriel Marchesi Schiavo.

