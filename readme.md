# Getting Started

### Prepare Environment
* Java 17
* Docker and Docker Compose
* Gradle 8 (Optional)
### Bootstrapping Application (Local)
* At the application root folder, run:
```
source run.sh
docker compose up -d
gradle bootRun 
```
* To stop application and shut down all related processes
```
docker compose down
```

### Run Test
```
gradle test --tests "*BlackboxTest"
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.3/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.3/gradle-plugin/packaging-oci-image.html)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#howto.data-initialization.migration-tool.liquibase)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#web.security)
* [Validation](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#io.validation)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

