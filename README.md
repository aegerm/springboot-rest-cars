## Objetivo da API
O objetivo dessa API é manter o cadastro de carros e seu respectivo tipo. Podendo o mesmo ser deletado, atualizado e listado por meio de filtros.
Para cadastrar um veículo o usuário deve possuir a role de admin e informar o nome e o seu tipo.
- Ex: Nome: Audi A3 e Tipo: Esportivo.

## Iniciando
- Utilizado Suite Springboot
- Utilizado Spring Data (JPA)
- Desenvolvido com Java 11
- Utilizar banco de dados MariaDB, versão 10.5 (Schema é criado automaticamente ao executar a aplicação)
- Coverage 85%
- Utilizado banco de dados H2 para testes unitários e de integração
- Utilizado Spring Security e JWT (Json Web Token) para segurança da aplicação
- Executar a aplicação com o comando **mvn spring-boot:run**, após a instalação do MariaDB

## Uso da API
 - Dentro da pasta resources encontra-se um arquivo json para ser importado na ferramenta Postman. Com as requisições prontas.
 - Após efetuar login deve ser utilizado o Bearer Token para autenticar.
 
 ## Documentação dos Frameworks Utilizados
 - https://adoptopenjdk.net/
 - https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
 - https://docs.spring.io/spring-security/site/docs/current/reference/html5/
 - https://spring.io/projects/spring-data
 - https://springfox.github.io/springfox/docs/current/#introduction
 - https://projectlombok.org/
 - http://modelmapper.org/getting-started/
 - https://www.jsonwebtoken.io/
 - https://junit.org/junit5/docs/current/user-guide/
 
 ## Ferramentas Utilizadas no Desenvolvimento
 - Intellij: https://www.jetbrains.com/pt-br/idea/
 - MariaDB: https://mariadb.org/
 - HeidiSQL: https://www.heidisql.com/
 - Postman: https://www.postman.com/
 - Windows 10: https://www.microsoft.com/pt-br/windows/
