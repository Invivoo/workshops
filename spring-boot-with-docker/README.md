# Packager et déployer une application Spring Boot avec Docker

## Table des matières
1. [Présentation de l'exercice](#présentation-de-lexercice)
1. [Introduction à Spring Boot et Docker](#introduction-à-spring-boot-et-docker)
  1. [Spring Boot](#spring-boot)
  1. [Docker](#docker)
1. [Stratégie de déploiement](#stratégie-de-déploiement)
1. [Préparation du packaging](#préparation-du-packaging)
  1. [Fichiers Docker](#fichier-docker)
  1. [Préparation au déploiement](#préparation-au-déploiement)
1. [Compilation et déploiement du conteneur Docker](#compilation-et-déploiement-du-conteneur-docker)
1. [Et Après ?](#et-après-)

## Présentation de l'exercice

Spring boot nous permet de créer des applications très rapidement et de les packager entièrement sous la forme d'un unique jar. Cette approche nous simplifie donc le déploiement de notre application puisque cette dernière embarque toutes les dépendences qui lui sont nécessaire. Inutile donc de devoir préparer un conteneur web prêt à accueillir notre application. Elle est automnome. Il s'agit en mon sens une recette très arriver à développer et déployer de manière efficace une architecture orienté Micro Services.

L'application que nous packagerons correspond à l'API Rest Sécurisé que nous avons construit lors du dernier workshop. Les sources sont disponible [ici](secured-restfull-api-docker).

## Prérequis
* Connaissances de base sur Spring Boot.
* Connaissances de base sur Docker.
* Une machine avec Docker, Maven et un client git d'installé.

## Introduction à Spring Boot et Docker

### Spring Boot

Spring Boot a fait l'objet d'un workshop précédemment. Vous pouvez le consulter [ici](../spring-boot). Notre exercice aura pour but de packager une application web qui se lance directement avec `java -jar notre-application.jar`. Spring Boot s'intégrera donc automatiquement à notre phase de packaging maven.

### Docker

Docker constitue une formidable solution pour les problématique de Continous Delivery et d'architecture Micro Services. Il permet de packager notre application avec toutes les dépendences OS nécessaire pour son déploiement. L'echosystème de l'application sera donc identique peu importe l'environment dans laquelle elle est déployée. Pour plus d'information sur docker, référez-vous au portail officiel de Docker. Il contient énormément de resources pour débuter sur l'outil. [https://www.docker.com](https://www.docker.com/).

## Stratégie de déploiement

Il est obligatoire d'effectuer une compilation de notre image de conteneur à chaque fois que notre application doit être déployé. 

Le déploiement sera effectué directement par notre serveur hébergeant Docker. Ce dernier devra effectuer les étapes suivantes dans l'ordre :

1. Clonage (ou rebase) du dépôt git contenant les sources de notre application.
1. Application des configurations dédié pour l'environment cible.
1. Commiter sur un dépôt adapté les configurations dédiés à l'environment cible.
1. Lancement de la compilation Maven qui packagera notre application avec les fichier nécessaire à la compilation Docker.
1. Extraction de l'archive pour la compilation Docker.
1. Lancement de la compilation Docker.
1. Déploiement de la nouvelle version du conteneur Docker.

La compilation du projet secured-restfull-api-docker abouti à la génération d'un jar `secured-restfull-api-docker.jar` prêt pour être exécuter. 

## Préparation du packaging

### Assembly Maven

Nous configurerons Maven pour construire une archive contenant toutes les dépendences nécessaire à la compilation de notre conteneur Docker. Pour ce faire, nous allons déclarer une assemnly `docker.zip` contenant les fichiers suivants :

* Jar compilé de notre application (secured-restfull-api-docker.jar)
* Fichier Dockerfile nécessaire à la compilation de notre conteneur.
* Le script de lancement Docker `run.sh`  responsable de lancer notre application au démarrage du conteneur.

Création du fichier `src/docker-resources/image/Dockerfile` :

```
FROM dockerfile/java:oracle-java8

MAINTAINER Daniel Lavoie <daniel.lavoie@invivoo.com>

ADD secured-rest-api-docker.jar /secured-rest-api-docker/secured-rest-api-docker.jar
ADD run.sh /run.sh

RUN chmod +x /*.sh

VOLUME [/"data"]

EXPOSE 8080

CMD ["/run.sh"]
```

Ce fichier sert à compiler une image de notre conteneur. La compilation se traduira par les instructions suivante à Docker :
* Ajout de notre jar secured-rest-api-docker.jar au file system du conteneur.
* Ajout de notre script de démarrage de conteneur `run.sh` au file systel du conteneur.
* Attribution des droits d'exécution sur le script `run.sh`.
* Montage d'un volume `/data` pour le conteneur.
* Ouverture du port 8080.
* Exécution de `run.sh` au démarrage du conteneur.

Nous poursuivons avec la création du script `src/docker-resources/image/run.sh` servant à l'initialisation du conteneur Docker : 

```
#!/bin/bash

java -jar /secured-rest-api-docker/secured-rest-api-docker.jar | tee /var/secured-rest-api-docker.log
```

Ce script sera appélé par la commande `CMD ["/run.sh"]` de notre Dockerfile. C'est notre instruction pour indiquer à Docker que notre application devra être démarré toute suite après l'initialisation du conteneur.

On procède avec la création du fichier d'assemblage `src/main/assembly/docker-assembly.xml` pour l'archive de compilation Docker.

```
<assembly
	xmlns="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/plugins/maven-assembly-plugin/assembly/1.1.2 http://maven.apache.org/xsd/assembly-1.1.2.xsd">
	<id>docker</id>
	<formats>
		<format>zip</format>
	</formats>
	<fileSets>
		<fileSet>
			<directory>target</directory>
			<outputDirectory>/</outputDirectory>
			<includes>
				<include>*.jar</include>
			</includes>
		</fileSet>
		
		<fileSet>
			<directory>docker-resources/image</directory>
			<outputDirectory>/</outputDirectory>
			<includes>
				<include>*</include>
			</includes>
		</fileSet>
	</fileSets>
</assembly>
```

Lors de la compilation Maven, une archive `docker.zip` sera généré dans le répertoire de sortie de compilation. Cette archive pourra donc être dézipé et compilé depuis le serveur qui sera responsable d'héberger notre conteneur Docker.

### Préparation au déploiement



## Compilation et déploiement du conteneur Docker

La suite de cet exercice nécessite de se déployer à un serveur Linux sous lequel Docker est installé. il est aussi préalable d'avoir un jdk, le client git et maven d'installé. Finalement notre prépérerons un répertoire de travail pour préparer le déploiement. Nous préparerons aussi un répertoire pour monter le volume de notre futur conteneur.

```
$ mkdir /docker-workspace
$ mkdir /docker-volumes
$ cd /docker-workspace
```

#### Clonage (ou rebase) du dépôt git contenant les sources de notre application.

Comme le déploiement se fait directement à partir des sources. Nous devrons cloner les sources de notre dépôt.

```
$ git clone https://github.com/daniellavoie/workshops.git
```

Nous nous déplacerons par la suite à la racine des sources de notre application.

```
cd workshops/spring-boot-with-docker/secured-restfull-api-docker
```

#### Application des configurations dédié pour l'environment cible.

Avant de compiler notre application, nous devons appliquer les configurations qui sont propre à cet environment cible. Les fichiers de configuration sont normalement disponible dans `src/main/resources`. Pour notre exercice, nous mettre à jour le profil Spring de notre application pour le passer à `production`.

src/main/resources/application.properties

```
spring.profiles.active=production
```

#### Commiter sur un dépôt adapté les configurations dédiés à l'environment cible.

En pratique, le déploiement ne sera pas nécessairement préparé par l'équipe de développement. Il a fort à parié que vous devez être tenu de ne pas connaitre les configurations propre à la production (ex : accès à la base de donnée de production). Par conséquent, les équipes qui prépare les configurations avant déploiement pourront contribué sous un dépôt git privé et seulement consultable par les équipes de production. Pour notre exercice, nous nous contenterons de contribuer ces configurations sous le dépôt git locale au serveur Docker (pas de push sur un remote privé).

```
$ git add src/main/resources/application.properties
$ git commit -m "Applied Production Configuration"
```

#### Lancement de la compilation Maven qui packagera notre application avec les fichier nécessaire à la compilation Docker.

Notre application est prêt pour le déploiement. Nous pouvons la compiler et packager avec Maven.

```
$ mvn clean install
```

#### Extraction de l'archive pour la compilation Docker.

Le résultat du packaging Maven nous produira une archive contenant tous fichiers nécessaire à notre compilation du Docker. Il faut donc extraire l'archive.

```
$ unzip -o target/docker.zip -d target
```

#### Lancement de la compilation Docker.

Notre application est prête pour le packaging Docker. Une fois notre image compilé, il sera déployé dans le registre Docker locale. Nous pourrons ensuite procédé au déploiement du conteneur.

```
$ docker build -t invivoo/secured-rest-api-docker target/docker
```

#### Déploiement de la nouvelle version du conteneur Docker.

Il n'y a plus qu'à lancer le conteneur.

```
$ docker run -d -p 8080:8080 -v /docker-volumes/secured-rest-api-docker:/data --name secured-rest-api-docker invivoo/secured-rest-api-docker

$ docker logs -f secured-rest-api-docker
```

## Et Après ?

Les étapes pour déployer le conteneurs sont plutôt simple mais tout de même relativement nombreuse. Par conséquent, je vous suggère de créer des scripts de déploiement propre à votre application qui automatise les étapes précédentes. Avec cette approche, vous atteindrez un déploiement automatique en une seule commande.
