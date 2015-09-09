# Processeur de Template HTML Thymeleaf

## Table des matières

1. [Présentation](#présentation)
  1. [Qu'est-ce que Thymeleaf ?](#quest-ce-que-thymeleaf-)
  1. [Fonctionnalités](#fonctionnalités)
1. [Utiliser Thymeleaf](#utiliser-thymeleaf)
  1. [Dialectes](#dialectes)
  1. [Afficher du texte](#afficher-du-texte)
  1. [Expressions Standards](#expressions-standards)
  1. [Itérations](#itérations)
  1. [Evaluations conditionnelles](#evaluations-conditionnelles)
  1. [Mise en page](#mise-en-page)
1. [Intégration avec Spring](#intégration-avec-spring)
  1. [Configuration](#configuration)
  1. [Résolution des templates](#résolution-des-templates)
1. Exemple 1 - Servlet servant du HTML servi par Thymeleaf
1. Exemple 2 - Application Web Spring Boot avec HTML servi par Thymeleaf

## Présentation

### Qu'est-ce que Thymeleaf ?

Thymeleaf est un moteur de templating Java pour XML / XHTML / HTML5. Il peut aussi bien être utilisé pour servir du contenus en ligne que hors ligne (génération de fichier HTML par exemple). En complément, il offre une intégration transparante à Spring MVC. Thymeleaf propose des tags HTML5 qui seront interprété par le moteur pour afficher du conteneu dynamique. L'avantage de Thymeleaf repose dans le fait que les tags utilisés laissent le HTML du template valide. Par conséquent, un Web Designer peut travailler et tester le rendu d'un template sans devoir le compiler par le moteur. Il peut travailler de manière autonome sans avoir besoin d'une application Java.

Site Web du projet : [http://www.thymeleaf.org/](http://www.thymeleaf.org/)

Documentation de reférence (très riche et informative) : [http://www.thymeleaf.org/documentation.html](http://www.thymeleaf.org/documentation.html)

Tutoriel interractif : [http://itutorial.thymeleaf.org/](http://itutorial.thymeleaf.org/)

Cet article a pour but de vous faire découvrir très rapidement les fonctionnalités de Thymeleaf. Je suggère fortement de parcourir la documentation de référence. Elle est très complète et bien organisé. Elle n'est pas trop volumineuse donc on ne s'y perd pas facilement.

Revenons en à l'essentiel. Voici un exemple de code qui montre comment Thymeleaf vous permettra de générer un table avec une ligne pour chaque élément d'une collection : 

```
<table>
	<thead>
		<th>Marque</th>
		<th>Model</th>
		<th>Prix</th>
	</thead>

	<tbody>
		<tr th:each="voiture : ${voitures}">
			<td th:text="${voiture.marque}">Citroen</td>
			<td th:text="${voiture.model}">2CV</td>
			<td th:text="${#number.formatDecimal(voiture.prix,1,2)}">6 990</td>
		</tr>
	</tbody>
</table>
```

Les navigateurs web n'interpréterons pas les attributs débutants par th:. Par conséquent, le template reste interprétable et pourra être manipulé par un designer. Par contre, une fois le template interprété par le moteur Thymeleaf, le contenus des balises ayant pour attribut `th:text` sera remplacé par la résolution des expressions y étant définies.

Thymeleaf se distingue ainsi de JSP. Voici un exemple de code JSP classique qui ne peut être interprété par le browser :

```
<form:inputText name="userName" value="${user.name}" />
```

Avec Thymeleaf, nous pouvons atteindre le même résultat en proposant un HTML valide : 

```
<input type="text" name="userName" value="James Carrot" th:value="${user.name}" />
```

Le moteur Thymeleaf s'occupera de remplacer `value="..."` par l'interprétation de `th:value=="..."`.

### Fonctionnalités

* Moteur de templating pour XML, XHTML et HTML5
* Fonctionne en mode en ligne et hors ligne
* Mécanisme de dialectes customiser le moteur.
* Validation XSD possible.
* Gestion de l'internationalisation.
* Caching sur les templates pour optimiser les performances.
* Intégration native avec Spring MVC.

## Utiliser Thymeleaf

### Dialectes

Thymeleaf repose sur un principe de dialectes. Un dialecte regroupe les processeurs qui s'occuperont de parser et traiter le template afin de déclencher les interpréteur. Le dialecte par défaut s'apelle StandardDialect. Il est suffisant pour gérer la majorité des attributs proposé par Thymeleaf. La configuration de dialecte permet d'utiliser un dialecte customisé reposant sur le StandardDialect mais qui permettrait de processer des instructions sur mesures pour nos propres templates. L'intégration avec Spring propose par exemple le SpringDialect, ce dernier ajoute, par exemplce, le support du Spring Expression Language.

### Afficher du texte

TODO

### Expressions Standards

### Itérations

### Evaluations conditionnelles

### Mise en page

## Intégration avec Spring

### Configuration

### Résolution des templates

## Exemple 1 - Servlet servant du HTML servi par Thymeleaf

Ce premier exemple correspond à une servlet qui servira une page HTML généré par thymeleaf en fonction d'une URL. Ainsi en accédant à l'url suivante (http://localhost:8080/thymeleaf-servlet/helle-world?name=Daniel), le template thymeleaf `hello-world.html` sera servit par notre application.

Les sources de cet exemple sont disponible [ici](thymeleaf-servlet).

### Création du pom.xml

Nous aurons besoin de cet exercice des dépendences Thymeleaf et d'une implémentation Servlet 3.0.

```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"
>	
	<modelVersion>4.0.0</modelVersion>
	
	<groupId>com.invivoo.thymeleaf</groupId>
	
	<artifactId>thymeleaf-servlet</artifactId>
	
	<version>0.0.1-SNAPSHOT</version>
	
	<name>Thymeleaf Servlet Exemple</name>
	
	<description>Thymeleaf Servlet Exemple</description>

	<build>
		<resources>
			<resource><directory>src/main/resources</directory></resource>
			<resource><directory>src/main/webapp</directory></resource>
		</resources>
	
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.tomcat.maven</groupId>
				<artifactId>tomcat7-maven-plugin</artifactId>
				<version>2.2</version>
			</plugin>
		</plugins>
	</build>

	<dependencies>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.0.1</version>
			<scope>provided</scope>
		</dependency>
		
		<dependency>
			<groupId>org.thymeleaf</groupId>
			<artifactId>thymeleaf</artifactId>
			<version>2.1.4.RELEASE</version>
		</dependency>
	</dependencies>
	<packaging>war</packaging>
</project>
```

### Création du web.xml

Nous utiliserons les annotations Servlet pour déclarer notre servlet. Par conséquent notre web.xml ne sert qu'à éviter que tomcat lance une erreur au démarrage de l'application.

```
<web-app 
    version="3.0"
    xmlns="http://java.sun.com/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
    >

</web-app>
```

### Création du template HTML hello-world

Template HTML `src/main/webapp/templates/hello-world.html` :

```
<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">

	<head>
		<title>Invivoo - Thymeleaf Servlet Exemple</title>
	</head>
	
	<body>		
		<div class="container">
			<h1>Invivoo - Thymeleaf Servlet Exemple</h1>
			
			<p>Hello ! My name is <span th:text="${name}">Bob</span> !</p>
		</div>
	</body>
</html>
```

La seule instruction Thymeleaf concerne notre balise `<span th:text="${name}">Bob</span>`. Il faudra donc fournir la variable "name" au contexte Thymeleaf.

### Instanciation du moteur thymeleaf.

Le moteur Thymeleaf sera instancé à l'aide la glace `com.invivoo.thymeleaf.servlet.TemplateEngineProvider`. La méthode `getTemplateEngine()` nous permet de récupérer le singleton représentant notre moteur. L'instanciation du moteur se fait en deux étape. Nous débuttons par instancier un `TemplateResolver` avec tous les paramètres nécessaire pour notre projet.

* Mode de templating ("XHTML" dans notre cas).
* Localisation racine des templates parmis notre classpath (ex : "/WEB-INF/templates/").
* Suffit à appliquer au nom du template afin de compléter la résolution du template dans le classpath (ex : ".html").
* Durée maximal des templates au seins du cache intégré (3 600 000 ms).

Ce qui est à retenir de cette configuration, c'est que pour interpréter le template hello-word.html, nous devrons fournir "hello-world" en tant que nom de template au moteur. Ce dernier s'occupera d'y joindre le préfix "/WEB-INF/templates/" et le suffix ".html" afin de résoudre au fichier final "/WEB-INF/templates/hello-world.html".

Avec ce TemplateResolver, nous pourrons instancier un TemplateEngine qui sera en mesure d'interpréter nos templates.

```
package com.invivoo.thymeleaf.servlet;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public abstract class TemplateEngineProvider. {
	private static TemplateEngine templateEngine;

	static {
		initializeTemplateEngine();
	}

	private static void initializeTemplateEngine() {

		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();

		templateResolver.setTemplateMode("XHTML");
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheTTLMs(3600000L);

		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
	}

	public static TemplateEngine getTemplateEngine() {
		return templateEngine;
	}
}

```

### Ecriture de la servlet

Comme expliqué au début de l'excercice, cette servlet devra exécuter le template passé en URL avec les paramètres présent dans la requête Http. Le servlet s'occupe donc d'instancier un WebContext dans lequel nous glissons tous les paramètres

```
package com.invivoo.thymeleaf.servlet;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.context.WebContext;

@WebServlet("/")
public class ThymeleafServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThymeleafServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Instanciation du context Thymeleaf.
		WebContext ctx = new WebContext(request, response, this.getServletContext(), request.getLocale());

		// Insertion des paramètres de la requêtes Http dans le context Thymeleaf.
		Collections.list(request.getParameterNames())
				.forEach(parameter -> ctx.setVariable(parameter, request.getParameter(parameter)));

		// Execution du template fourni dans le chemin de la requête Http.
		TemplateEngineProvider.getTemplateEngine().process(request.getServletPath().substring(0), ctx,
				response.getWriter());

	}
}

```

### Lancement de l'application

Nous lancerons notre application avec le plugin tomcat pour maven. Ce dernier va lancer un tomcat sur le port 8080 et y déployer notre application.

```
mvn tomcat7:run
```

Vous pouvez dorénavant consulter le template hello-world à l'url suivante : [localhost:8080/thymeleaf-servlet/hello-world?name=Daniel](http://localhost:8080/thymeleaf-servlet/hello-world?name=Daniel)


## Exemple 2 - Application Web Spring Boot avec HTML servi par Thymeleaf

Les sources de cet exemple sont disponible [ici](spring-boot-thymeleaf).
