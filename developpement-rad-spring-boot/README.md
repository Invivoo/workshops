# Développement RAD avec Spring Boot

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
1. [Example - API Rest sécurisé](#example---api-rest-sécurisé)

## Prérequis

* Connaissances maven de base.
* Connaissances Spring de base et configuration par annotation.
* Connaissances Hibernate de base.

## Présentation

### Pourquoi Spring Boot ?

Spring Boot apporte le patron de conception COC (Covention Over Configuration) au Framework Spring. Spring Boot vous aide à construire des applications le plus rapidement possible sans avoir à répéter des déclarations ou des configurations de beans standard.

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

Voici la liste des modules Spring supportés en version Spring Boot 1.2.5 :

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
    <version>1.2.5.RELEASE</version>
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

## Example - API Rest sécurisé

L'objectif de cette démonstration consiste à vous montrer la rapidité avec laquelle le métier de votre application peut être exposé de manière sécurisé à travers une interface web RESTfull. 

Voici les fonctionnalités offerte pas notre application :

* Consultation / ajout / suppression d'actions.
* consultation / ajout / suppression de cotation d'actions.

Notre application sera basé sur les technologies suivante : 

* Spring Data Jpa pour la persistence de nos entités métiers.
* Spring WebMCV pour exposer nos entités métiers.
* Spring Security pour gérer l'accès à ces ressources.
* HSQLDB comme base de données mémoire embarqué.

La démonstration fera usage d'AngularJS au sein des pages HTML fourni par le service pour démontrer les intéractions RESTfull avec l'api que nous allons construire. Les explications concernant la partie cliente seront brèves puisque le workshop n'a pas pour but de présenter AngularJS.

### Configuration du projet

Déclaration du starter parent Spring Boot dans notre `pom.xml`

```
<!-- Inherit defaults from Spring Boot -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.2.5.RELEASE</version>
</parent>
```

Notre projet nécessite donc les modules Spring Boot suivants :

* spring-boot-starter-data-jpa
* spring-boot-starter-web
* spring-boot-security

En complément, nous utiliserons une base de données HSQLDB embarqué en mémoire. Elle facilitera notre développement

Il n'y a qu'à les déclarer en dépendences au sein du `pom.xml`

```
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
	</dependency>
		<dependency>
			<groupId>org.hsqldb</groupId>
			<artifactId>hsqldb</artifactId>
		</dependency>
</dependencies>
```

### Création de nos entités métiers


Stock.java

```
package com.invivoo.springboot.securedrestapi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Stock {
	private long id;
	private String name;
	private String isin;
	private List<StockQuote> stockQuotes;

	public Stock() {

	}

	public Stock(long id, String name, String isin, List<StockQuote> stockQuotes) {
		this.id = id;
		this.name = name;
		this.isin = isin;
		this.stockQuotes = stockQuotes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy="stock")
	public List<StockQuote> getStockQuotes() {
		return stockQuotes;
	}

	public void setStockQuotes(List<StockQuote> stockQuotes) {
		this.stockQuotes = stockQuotes;
	}
}
```

StockQuote.java

```
ppackage com.invivoo.springboot.securedrestapi.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class StockQuote {
	private long id;
	private Stock stock;
	private Double quote;
	private Date timestamp;

	public StockQuote() {

	}

	public StockQuote(long id, Double quote, Date timestamp) {
		this.id = id;
		this.quote = quote;
		this.timestamp = timestamp;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JsonBackReference
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Double getQuote() {
		return quote;
	}

	public void setQuote(Double quote) {
		this.quote = quote;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
```

### Implémentation de la persistence des entités.

Nous utiliserons Spring Data JPA pour gérer la persistence de ces entités. Ce workshop n'a pas pour but de d'expliquer le fonctionnement de Spring Data JPA mais en voici la principale fonctionnalité. Spring Data JPA s'occupe d'implémenter automatiquement les Data Access Object (DAO) des entités JPA de notre projet. Ainsi, l'outil nous fourni les implémentation nécessaire pour réaliser des opérations CRUD sur nos entités sans avoir à coder quoi que ce soit. Pour ce faire, nous devons simplement déclarer une interface qui hérite de JPARepository typé avec notre entité JPA.

Déclaration de notre DAO pour gérer notre entity Stock :

```
package com.invivoo.springboot.securedrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invivoo.springboot.securedrestapi.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
```

Spring Data JPA qui sera initialisé par Spring Boot s'occupera de scanner notre package **com.invivoo.springboot.securedrestapi** pour y rechercher des interfaces héritant de JPARepository. Le framework construira ensuite l'implémentation de l'interface nécessaire pour réaliser les opérations CRUD basique. Cette implémentation sera donc disponible à notre application pour une injection par le conteneur Spring.

Dans le cadre de notre exemple, nous n'avons pas besoin de DAO pour StockQuote puisque cette dernière ont été configuré pour être cascadés lors de la sauvegarde de notre entité Stock.

```
@OneToMany(cascade = CascadeType.ALL)
public List<StockQuote> getStockQuotes() {
	return stockQuotes;
}
```

### Implémentation de la couche Service

La couche service sert habituellement à la manipulation métier. Toute manipulations ou validation fonctionnelle doit normalement s'y retrouve. Cette couche correspond au liens entre la couche controleur et celle de la persistence.

StockService.java

```
package com.invivoo.springboot.securedrestapi.service;

import java.util.List;

import com.invivoo.springboot.securedrestapi.entity.Stock;

public interface StockService {
	List<Stock> findAll();

	Stock findOne(long id);

	Stock save(Stock stock);
}
```

StockServiceImpl.java

```
package com.invivoo.springboot.securedrestapi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.invivoo.springboot.securedrestapi.entity.Stock;
import com.invivoo.springboot.securedrestapi.repository.StockRepository;
import com.invivoo.springboot.securedrestapi.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	@Resource
	private StockRepository stockRepository;

	@Override
	public List<Stock> findAll() {
		return stockRepository.findAll();
	}

	@Override
	public Stock findOne(long id) {
		return stockRepository.findOne(id);
	}

	@Override
	public Stock save(Stock stock) {
		return stockRepository.save(stock);
	}
}
```

### Implémentation du controleur WebMVC pour l'entité Stock

La classe StockController servira à déclarer les point d'accès de notre Web Service REST. Cette implémentation est très standard aux fonctionnalités classiques offerte par Spring Web MVC.

StockController.java

```
package com.invivoo.springboot.securedrestapi.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invivoo.springboot.securedrestapi.entity.Stock;
import com.invivoo.springboot.securedrestapi.service.StockService;

@Controller
@ResponseBody
@RequestMapping(value = "/stock", produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {
	@Resource
	private StockService stockService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Stock> findAll() {
		return stockService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Stock findOne(@PathVariable long id) {
		return stockService.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Stock save(@RequestBody Stock stock) {
		return stockService.save(stock);
	}
}
```

### Fournir un petit jeu de donnée

Nous verrons plus tard comment Spring Boot s'occupe de configurer automatiquement la base de données embarqué HSQLDB. Pour l'instant, vous pouvez fournir un fichier `data.sql` dans le classpath (`src/main/resources`) pour initialiser des données de tests.

data.sql

```
INSERT INTO PUBLIC.STOCK (ID, NAME, ISIN) VALUES (1, 'ACCOR', 'FR0000120404');
INSERT INTO PUBLIC.STOCK (ID, NAME, ISIN) VALUES (2, 'AIR LIQUIDE', 'FR0000120073');
INSERT INTO PUBLIC.STOCK (ID, NAME, ISIN) VALUES (3, 'ALSTOM', 'FR0010220475');
```

Spring Boot initialisera une base de donnée vide, générera le schéma à l'aide HBM2DDL de Hibernate puis exécutera data.sql afin d'importer des données pour vos développement. Il faut toujours garder à l'esprit que cette fonctionnalité est configurable et désactivable à travers les options Spring Boot que nous verrons plus tard.

### Implémentation de notre classe d'amorçage Spring Boot

SecuredRestApi.java

```
package com.invivoo.springboot.securedrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecuredRestApi {
	public static void main(String[] args) {
		SpringApplication.run(SecuredRestApi.class, args);
	}
}
```
