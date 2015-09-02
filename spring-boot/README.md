# Développement RAD avec Spring Boot

English version available [here](README-EN.md).

## Table of Contents
1. [Prérequis](#prérequis)
1. [Présentation](#presentation)
  1. [Qu'est-ce que Spring Boot ?](#qu-est-ce-que-spring-boot-)
  1. [Pourquoi Spring Boot ?](#pourquoi-spring-boot-)
  1. [Modules Spring supportés](#modules-spring-supportés)
1. [Concepts Clés](#concepts-clés)
  1. [Configuration projet](#configuratipn-projet)
  1. [Amorcer votre application](#amorcer-votre-application)
  1. [Packager votre application](#packager-votre-application)
1. Example 1 - Database Extraction batch - TO BE DONE
1. Example 2 - Secured Rest API - TO BE DONE

## Prérequis

* Connaissance maven de base.
* Connaissance Spring de base et configuration par annotation.

## Présentation

### Pourquoi Spring Boot ?

Spring Boot apporter le patron de conception COC (Covention Over Configuration) au Framework Spring. Spring Boot vous aide à construire des applications le plus rapidement possible sans avoir à répéter des déclarations ou des configurations de beans standard.

Voici les principales fonctionnalités :

* Fourni des beans pré configuré pour chacun des modules Spring nécessaire à votre application.
* Approche de configuration opiniâtre pour les composants mais facile à surcharger pour un comportement personalisé.
* Aucune génération de code ou de configuration XML.
* Packager facilement votre application sous un jar exécutable avec la commande `java -jar`ou sous un war conventionnel.

### Pourquoi Spring Boot ?

* La construction d'applications Spring se traduit par la déclaration à réptition de beans, projets après projets.
* Permet de démarrer un projet très rapidement et de se concentrer sur les besoins métiers.
* Le packaging de Spring Boot uniformise le déploiement de l'application pour tous les environments (pas de conteneur).
* Tous les tutoriaux officiels de Spring sont maintenant basé sur Spring Boot (vous n'y échapperez pas !).

### Modules Spring supportés

Voici la liste des modules Spring supportés en version Spring Boot 1.3.0 :

Nom  | Description
------------- | -------------
spring-boot-starter-actuator  | Module de monitoring pour la surveillance de l'application.
spring-boot-starter-amqp | Support pour 'Advanced Message Queuing Protocol' via spring-rabbit.
spring-boot-starter-aop | Support pour la programmtion orienté par aspect avec spring-aop et AspectJ.
spring-boot-starter-artemis | Support pour “Java Message Service API” via Apache Artemis.
spring-boot-starter-batch | Support pour 'Spring Batch' includant la base de donnée HSQLDB.
spring-boot-starter-cache | Support d'abstraction pour Spring’s Cache.
spring-boot-starter-cloud-connectors | Support pour 'Spring Cloud Connectors' qui simplifient la connection à des platformes Cloud comme Cloud Foundry et Heroku.
spring-boot-starter-data-elasticsearch | Support pour le moteur de recherche et d'analytique en incluant  spring-data-elasticsearch.
spring-boot-starter-data-gemfire | Support pour le store distribué GemFire en incluant spring-data-gemfire.
spring-boot-starter-data-jpa | Support pour “Java Persistence API” en incluant spring-data-jpa, spring-orm and Hibernate.
spring-boot-starter-data-mongodb | Support pour la base de données NoSQL MongoDB en incluant spring-data-mongodb.
spring-boot-starter-data-rest | Support pour exposer des repositories Spring Data en REST via spring-data-rest-webmvc.
spring-boot-starter-data-solr | Support pour le moeteur de recherche Apache Solr en incluant spring-data-solr.
spring-boot-starter-freemarker | Support pour le moteur de templating FreeMarker.
spring-boot-starter-groovy-templates | Support pour le moteur de templating Groovy.
spring-boot-starter-hateoas | Support pour services web HATEOAS RESTful via spring-hateoas.
spring-boot-starter-hornetq | Support pour “Java Message Service API” via HornetQ.
spring-boot-starter-integration | Support pour les modules communs de spring-integration.
spring-boot-starter-jdbc | Support pour les bases de données avec JDBC.
spring-boot-starter-jersey | Support pour le framework de services web RESTful Jersey.
spring-boot-starter-jta-atomikos | Support pour transactions distribuées JTA via Atomikos.
spring-boot-starter-jta-bitronix | Support pour transactions distribuées JTA via Bitronix.
spring-boot-starter-mail | Support pour javax.mail.
spring-boot-starter-mobile | Support pour spring-mobile.
spring-boot-starter-mustache | Support pour le moteur de template Mustache.
spring-boot-starter-redis | Support pour le store de données clé/valeur REDIS en incluant spring-redis.
spring-boot-starter-security | Support pour spring-security.
spring-boot-starter-social-facebook | Support pour spring-social-facebook.
spring-boot-starter-social-linkedin | Support pour spring-social-linkedin.
spring-boot-starter-social-twitter | Support pour spring-social-twitter.
spring-boot-starter-test | Support pour les différentes dépendences de tests unitaire en incluant JUnit, Hamcrest and Mockito ainsi que spring-test module.
spring-boot-starter-thymeleaf | Support pour le moteur de templating Thymeleaf.
spring-boot-starter-velocity | Support pour le moteur de templating Velocity.
spring-boot-starter-web | Support for développement de la pile web complête en incluant Tomcat et spring-webmvc.
spring-boot-starter-websocket | Support pour le développement WebSocket.
spring-boot-starter-ws | Support pour Spring Web Services.

## Concepts Clés

### Configuration projet

#### Hériter de starter parent

Déclarer le starter parent pour faciliter la déclaration des dépendences de Spring Boot pour votre projet.
```
<!-- Inherit defaults from Spring Boot -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.3.0.BUILD-SNAPSHOT</version>
</parent>
```

Ensuite il faut sélectionner les composants Spring Boot pour les modules spring requise pour notre application. Dans notre exemple, nous construirons une webapp simple qui sert du contenu localisé dans l'arborescence classpath:/resources.

```
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
</dependencies>
```
Le fonctionnalités principales de Spring Boot seront disponible à l'application déclarant en dépendences n'importe quel module starter de Spring Boot.

### Amorcer votre application

Sprinb Boot fonctionne principalement par annotation. au grand désarroi de ceux qui tiennent toujours à la configuration Spring par fichiers XML. Démarrer une application avec Spring Boot nécessite d'exécuter la méthode statique **org.springframework.boot.SpringApplication.run(...)** depuis n'importe quel endroit. Mon conseil est de déclarer l'appel depuis la méthod statique main de votre application. Ainsi vous pourrez démarrer votre application avec la commande `java -jar`.

**SpringApplication.run(...)** prend comme argument une classe. Spring Boot recherchera les annotations java parmis celle qui sera passé en paramètre. Comme il retrouve l'annotation **@SpringBootApplication**, un **AnnotationConfigEmbeddedWebApplicationContext** sera instancié. Ce conteneur Spring sera peuplé par tous les beans auto configurés fourni par le module Spring Boot que vous aurez mis en dépendences ainsi que tout ceux déclaré par annotation dans le package de la classe passé en paramètre (**.invivoo.springboot.keyconcepts**).

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

Voici la sortie de console du lancement de la méthode main ci dessus :

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

Vous avez maintenant un Tomcat embarqué écoutant le port 8080. Toutes resources situé dans classpath:/resources sera disponible à l'url http://localhost:8080/* (ex : <a target="_blank" href="http://localhost:800/hello-world.html">http://localhost:8080/hello-world.html</a>).

### Packager votre application

Spring Boot fourni un plugin maven qui permettra d'ajouter toutes les dépendences de votre projet ainsi que la configuration néssaire afin que le jar final produit lors de la séquence maven package soit exécutable en autonome.

Ajouter la déclaration de plugin suivnate à votre `pom.xml` : 

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

Builder votre application avec maven.

```
$ mvn clean install
```

Exécuter le jar produit.

```
java -jar target/key-concepts-0.0.1-SNAPSHOT.jar
```
