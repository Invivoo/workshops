# Rapid Application Development with Spring-Boot

## Table of Contents
1. Prerequisite
1. Presentation
  1. What is Spring Boot ?
  1. Why Spring Boot ?
  1. Supported Spring modules
1. Key Concepts
  1. Project setup
  1. Bootstrap your application
  1. Packaging
1. Example 1 - Database Extraction batch
1. Example 2 - Secured Rest API

## Prerequisite

* Basic Maven knowledge
* Basic Spring Knowledge

## Presentation

### What is Spring Boot ?

Spring Boot is about applying COC (Covention Over Configuration) to the Spring Framework. Spring Boot helps you build 
applications as fast possible without having to take care of any boiler plate configuration or declaration. 

Here are the main features :

* Provides pre configured beans ready to be used for any Spring module you relie on.
* Opinionated out of the box by letting you easily overwrite any default component or configuration.
* No code generation and no XML configuration required.
* Easily packages your application to a runnable jar with `java -jar` or to a conventionnal war file.

### Why Spring Boot ?

* Building Spring based application usally ends in declaring the same beans over and over again, projects after projects.
* Start up you projects super fast and focus on your business requirements.
* Gives your development and deployed environment the same runtime setup.
* Every Spring 'Getting Started' guides are now based on Spring Boot, no need to resist.

### Supported Spring modules

Here is the list of the Spring Projects supported by Spring Boot as of version 1.3.0 :

Name  | Description
------------- | -------------
spring-boot-starter  | The core Spring Boot starter, including auto-configuration support, logging and YAML.
spring-boot-starter-actuator  | Production ready features to help you monitor and manage your application.
spring-boot-starter-amqp | Support for the 'Advanced Message Queuing Protocol' via spring-rabbit.
spring-boot-starter-aop | Support for aspect-oriented programming including spring-aop and AspectJ.
spring-boot-starter-artemis | Support for “Java Message Service API” via Apache Artemis.
spring-boot-starter-batch | Support for 'Spring Batch' including HSQLDB database.
spring-boot-starter-cache | Support for Spring’s Cache abstraction.
spring-boot-starter-cloud-connectors | Support for 'Spring Cloud Connectors' which simplifies connecting to services in cloud platforms like Cloud Foundry and Heroku.
spring-boot-starter-data-elasticsearch | Support for the Elasticsearch search and analytics engine including spring-data-elasticsearch.
spring-boot-starter-data-gemfire | Support for the GemFire distributed data store including spring-data-gemfire.
spring-boot-starter-data-jpa | Support for the “Java Persistence API” including spring-data-jpa, spring-orm and Hibernate.
spring-boot-starter-data-mongodb | Support for the MongoDB NoSQL Database, including spring-data-mongodb.
spring-boot-starter-data-rest | Support for exposing Spring Data repositories over REST via spring-data-rest-webmvc.
spring-boot-starter-data-solr | Support for the Apache Solr search platform, including spring-data-solr.
spring-boot-starter-freemarker | Support for the FreeMarker templating engine.
spring-boot-starter-groovy-templates | Support for the Groovy templating engine.
spring-boot-starter-hateoas | Support for HATEOAS-based RESTful services via spring-hateoas.
spring-boot-starter-hornetq | Support for “Java Message Service API” via HornetQ.
spring-boot-starter-integration | Support for common spring-integration modules.
spring-boot-starter-jdbc | Support for JDBC databases.
spring-boot-starter-jersey | Support for the Jersey RESTful Web Services framework.
spring-boot-starter-jta-atomikos | Support for JTA distributed transactions via Atomikos.
spring-boot-starter-jta-bitronix | Support for JTA distributed transactions via Bitronix.
spring-boot-starter-mail | Support for javax.mail.
spring-boot-starter-mobile | Support for spring-mobile.
spring-boot-starter-mustache | Support for the Mustache templating engine.
spring-boot-starter-redis | Support for the REDIS key-value data store, including spring-redis.
spring-boot-starter-security | Support for spring-security.
spring-boot-starter-social-facebook | Support for spring-social-facebook.
spring-boot-starter-social-linkedin | Support for spring-social-linkedin.
spring-boot-starter-social-twitter | Support for spring-social-twitter.
spring-boot-starter-test | Support for common test dependencies, including JUnit, Hamcrest and Mockito along with the spring-test module.
spring-boot-starter-thymeleaf | Support for the Thymeleaf templating engine, including integration with Spring.
spring-boot-starter-velocity | Support for the Velocity templating engine.
spring-boot-starter-web | Support for full-stack web development, including Tomcat and spring-webmvc.
spring-boot-starter-websocket | Support for WebSocket development.
spring-boot-starter-ws | Support for Spring Web Services.

## Key Concepts

### Project setup

#### Inherting the starter parent

Declaring the starter parent will help you defines the Spring Boot dependencies needed for your project.

```
<!-- Inherit defaults from Spring Boot -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.3.0.BUILD-SNAPSHOT</version>
</parent>
```

Then choose the Spring Boot starter for the Spring modules required by your application. for our exemple, we will build a simple webapp serverving content from classpath:/resources.

```
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
</dependencies>
```

The Spring Boot core features will be available to your application by declaring any of the Spring Boot starter dependencies.

### Bootstrap your application

Spring Boot is mainly about annotations. Sorry for the likes  who still holds on to old XML configuration. 
Starting a Spring Boot Application requires that you execute the 
**org.springframework.boot.SpringApplication.run(...)** method from anywhere. 
My advice is to call it directly from a static main method
so you can startup your application with a 'java -jar' command.

**SpringApplication.run(...)** takes a class as an argument. 
Spring Boot will then lookup for java annotations within that class. 
Since it will find **@SpringBootApplication**, an **AnnotationConfigEmbeddedWebApplicationContext** 
will be created. This application context is going to be your Spring Container and will filled with
beans configured by Spring Boot and with others available for component 
scanning inside the **.invivoo.springboot.keyconcepts** package.

```
package com.invivoo.springboot.keyconcepts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KeyConcepts {
	private static final Logger LOGGER = LoggerFactory.getLogger(KeyConcepts.class);

	public static void main(String[] args) {
		SpringApplication.run(KeyConcepts.class, args);

		LOGGER.info("Demo application has been initialized successfully.");
	}
}
```

Here are the outputs from running the main class above.

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.2.5.RELEASE)

2015-09-02 12:44:53.940  INFO 6748 --- [           main] c.i.springboot.keyconcepts.KeyConcepts   : Starting KeyConcepts on PC-002 with PID 6748 (C:\daniel\projets\spring-boot\git\spring-boot\key-concepts\target\classes started by daniel.lavoie in C:\daniel\projets\spring-boot\git\spring-boot\key-concepts)
2015-09-02 12:44:53.979  INFO 6748 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@28975c28: startup date [Wed Sep 02 12:44:53 CEST 2015]; root of context hierarchy
2015-09-02 12:44:54.672  INFO 6748 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : Overriding bean definition for bean 'beanNameViewResolver': replacing [Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration; factoryMethodName=beanNameViewResolver; initMethodName=null; destroyMethodName=(inferred); defined in class path resource [org/springframework/boot/autoconfigure/web/ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration.class]] with [Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter; factoryMethodName=beanNameViewResolver; initMethodName=null; destroyMethodName=(inferred); defined in class path resource [org/springframework/boot/autoconfigure/web/WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter.class]]
2015-09-02 12:44:56.411  INFO 6748 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 8080 (http)
2015-09-02 12:44:56.658  INFO 6748 --- [           main] o.apache.catalina.core.StandardService   : Starting service Tomcat
2015-09-02 12:44:56.659  INFO 6748 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.0.23
2015-09-02 12:44:56.799  INFO 6748 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2015-09-02 12:44:56.799  INFO 6748 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 2823 ms
2015-09-02 12:44:57.537  INFO 6748 --- [ost-startStop-1] o.s.b.c.e.ServletRegistrationBean        : Mapping servlet: 'dispatcherServlet' to [/]
2015-09-02 12:44:57.541  INFO 6748 --- [ost-startStop-1] o.s.b.c.embedded.FilterRegistrationBean  : Mapping filter: 'characterEncodingFilter' to: [/*]
2015-09-02 12:44:57.542  INFO 6748 --- [ost-startStop-1] o.s.b.c.embedded.FilterRegistrationBean  : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2015-09-02 12:44:57.891  INFO 6748 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@28975c28: startup date [Wed Sep 02 12:44:53 CEST 2015]; root of context hierarchy
2015-09-02 12:44:57.955  INFO 6748 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2015-09-02 12:44:57.956  INFO 6748 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest)
2015-09-02 12:44:57.988  INFO 6748 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2015-09-02 12:44:57.988  INFO 6748 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2015-09-02 12:44:58.034  INFO 6748 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2015-09-02 12:44:58.117  INFO 6748 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2015-09-02 12:44:58.194  INFO 6748 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2015-09-02 12:44:58.196  INFO 6748 --- [           main] c.i.springboot.keyconcepts.KeyConcepts   : Started KeyConcepts in 4.553 seconds (JVM running for 5.142)
```

You now have an embedded Tomcat listening for request on port 8080. Any resources found in classpath:/resources will be avaiable to http://localhost:8080/* (ex : <a target="_blank" href="http://localhost:800/hello-world.html">http://localhost:8080/hello-world.html</a>).

### Package your application

Spring Boot provides a maven plugin that will generates a standlone JAR containing all the dependencies required by your project. If your application uses an embedded http server, it will be also packaged inside your final jar.

Add the following plugin declaration to your `pom.xml` : 

```
<build>
	<plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
               </plugin>
	</plugins>
</build>
```

Build your application with maven.

```
$ mvn clean install
```

Run your application

```
java -jar target/key-concepts-0.0.1-SNAPSHOT.jar
```
