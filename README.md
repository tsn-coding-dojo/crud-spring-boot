# CRUD with Spring Boot
API Rest pour du CRUD sur un objet avec Spring Boot et en se basant sur une architecture logicielle en 3 couches.

## Prérequis
- Java 17
- Maven
- IDE : IntelliJ IDEA dans l'idéal et fortement conseillé, la version Community est gratuite. Sinon, Eclipse ou NetBeans.
- Accès à internet pour récupérer les librairies ou récupérer le repository correspondant au Dojo avec toutes les librairies nécessaires
- Postman ou curl

## CRUD
CRUD pour Create, Read, Update et Delete. Il caractérise les principales fonctionnalités permettant la persistance des données ainsi que les endpoints (API Rest) donnant accès aux utilisateurs à la gestion des données depuis l'extérieur.
Les endpoints sont caractérisés par des méthodes d'accès REST ou aussi appelés verbes associés aux fonctions CRUD : POST pour Create, GET pour Read, PUT pour Update et DELETE pour Delete.
Si plusieurs fonctionnalités sont nécessaires pour un même verbe, on peut les différencier par le chemin. Par exemple GET /application/element pour avoir tous les éléments et GET /application/element/{id} pour récupérer l'élément définit par son id.

## Architecture logicielle en couches
C'est une façon de structurer le code afin de le répartir suivant les rôles de chacune des parties de code.
- La couche Présentation : contient le code de l'IHM dans le cas d'un client lourd ou les points d'entrée des API (endpoints) dans le cas d'un backend
- La couche Métier ou aussi appelée Business : on y trouvera toute l'intelligence des traitements
- La couche Persistance : pour accéder aux éléments extérieurs (base de données, système de fichiers, requêtes API avec un client Rest, messages réseau), tout ce qui est en lien avec le stockage des données, en entrée et en sortie.

En annexes (à la fin), les avantages et les inconvénients

## Exercice 1 : Spring Initializr
Le site https://start.spring.io/ permet d'initialiser un projet Maven Java Spring Boot.
On peut définir un certain nombre de librairies que l'on veut mettre en dépendances :
- spring-boot-starter-parent : en tant que parent du projet pour utiliser le framework Spring Boot à minima
- spring-boot-starter-web : pour les fonctionnalités API Rest
- spring-boot-starter-actuator : pour monitorer l'API
- spring-boot-devtools : pour faciliter la phase de développement. Redémarrage rapide, ... .
- spring-boot-starter-data-jpa : pour persister en utilisant PostgreSql
- postgresql (scope : runtime) : driver JDBC vers PostgreSql
- spring-boot-configuration-processor : ?
- lombok : permet d'éviter de rédiger du code sans intelligence (boileplate : getters, setters et constructeurs) pour le modèle de donnée (DTO)
- spring-boot-starter-test (scope test) : pour rédiger des tests unitaires sur des développements utilisant Spring Boot.

Générer le projet et le récupérer en tant qu'archive Zip dans les téléchargements. Le décompresser dans le workspace.

Cette génération automatique permet de générer :
- le fichier pom (maven) du projet avec les principales dépendances Spring Boot permettant de démarrer. Le plugin spring-boot-maven-plugin est utilisé pour compiler. lombko est exclu.
- la structure de répertoires minimum
- la classe permettant de démarrer l'application
- le fichier de configuration Spring Boot

Le contrôleur et les principaux endpoint ne sont pas créés.
Il n'y a que le strict minimum. Il est préférable de partir d'un projet template qui apportera tous ces éléments et bien plus encore.

## Initialiser à partir d'un projet Template
La Coding Academy peut vous apporter ce projet Template. Le plus courant sera un CRUD protégé par une authentification SSO (Keycloak en l'occurance).
Il faudra :
- modifier le nom du projet dans le fichier pom.xml. A éditer avec n'importe quel éditeur de texte, avant d'ouvrir le projet dans l'IDE sinon l'IDE risque d'être perdu après le changement de nom du projet. Ca peut être chiant à récupérer si l'IDE est perdu.
- modifier le nom de la classe de démarrage xxxApplication
- modifier les noms de package

## Exercice 2 : Reprendre un projet Template et l'adapter à notre contexte
- Prendre la branche https://gitlab.thalesdigital.io/coding-academy/coding-dojo/crud-with-spring-boot/-/tree/exercice2-%C3%A9nonc%C3%A9?ref_type=heads
- pour voir si l'application est démarée : http://localhost:8080/api/actuator/health
- Compléter le Controller pour activer les endpoints CRUD. Mettre des logs dans chacun des endpoints pour voir les appels. Faire des appels aux différents endpoints avec Postman ou curl.
- La solution dans la branche https://gitlab.thalesdigital.io/coding-academy/coding-dojo/crud-with-spring-boot/-/tree/exercice2-solution?ref_type=heads

## Exercice 3 : Mettre en place les couches métiers (business en anglais ou Processus dans le code) et persistance (Service dans le code)
- Partir de la solution de l'exercice 2 dans la branche https://gitlab.thalesdigital.io/coding-academy/coding-dojo/crud-with-spring-boot/-/tree/exercice2-solution?ref_type=heads
- Instancier ElementsProcessus dans le controller. C'est une interface et son implémentation est la classe ElementsProcessusImpl. Avec l'annotation @Service et le mot implements, Spring Boot se débrouille tout seul et avec @Autowired, Spring Boot fait l'instanciation de la classe avec le contexte de la requête. Il est conseillé de faire l'Autowired par le constructeur.
- Dans les endpoints du controller, faire les appels à la couche métier et dans la coucne métier, faire les appels à la couche persistance.
- Déplacer les logs dans le processus.
- La solution dans la branche https://gitlab.thalesdigital.io/coding-academy/coding-dojo/crud-with-spring-boot/-/tree/exercice2-solution?ref_type=heads

## Exercice 4 : Utiliser l'implémentation de la persistance en mémoire
- Partir de la solution de l'exercice 3 dans la branche https://gitlab.thalesdigital.io/coding-academy/coding-dojo/crud-with-spring-boot/-/tree/exercice3-solution?ref_type=heads
- Utiliser le DAO ElementsInMemoryDAO pour avoir de la persistance en mémoire avec un singleton et une HashMap
- La solution dans la branche https://gitlab.thalesdigital.io/coding-academy/coding-dojo/crud-with-spring-boot/-/tree/exercice4-solution?ref_type=heads

## Exercice 5 : Remplacer la persistance en mémoire par une base de données H2
- Partir de la solution de l'exercice 4 dans la branche https://gitlab.thalesdigital.io/coding-academy/coding-dojo/crud-with-spring-boot/-/tree/exercice4-solution?ref_type=heads
- Remplacer la persistance en mémoire par l'utilisation d'une base de données H2
    - Modifier le fichier pom.xml pour ajouter les dépendances :
        - org.springframework.boot/spring-boot-starter-data-jpa : la librairie Spring Boot JPA pour toutes les facilités d'accès aux bases SQL
        - com.h2database/h2 : le driver H2
    - Ajouter la datasource dans le fichier de configuration. Avec url: jdbc:h2:mem:mydb, on a une base de données en mémoire. Il est possible d'utiliser un fichier au lieu de la mémoire avec url: jdbc:h2:file:/data/demo
    - Faire un DAO utilisant JPA
    - Adapter l'entity pour l'associer à la table element et que les noms de colonnes soient bien formatées
- Pour accéder à la console H2 :
    - dans le fichier de configuration :
      spring:
      h2:
      console.enabled: true
    - accéder dans un browser : http://localhost:8080/api/h2-console
        - dans JDBC url : jdbc:h2:mem:mydb
        - mettre les credentials : sa et password
    - jouez avec les requêtes API et avec la console H2
- La solution dans la branche https://gitlab.thalesdigital.io/coding-academy/coding-dojo/crud-with-spring-boot/-/tree/exercice5-solution?ref_type=heads

## Exercice 6 : Remplacer la base de données H2 par une base de données PostgreSql
- Partir de la solution de l'exercice 4 dans la branche https://gitlab.thalesdigital.io/coding-academy/coding-dojo/crud-with-spring-boot/-/tree/exercice5-solution?ref_type=heads
- Remplacer la base de données H2 par l'utilisation d'une base de données PostgreSql
    - Creer une base de données PostgreSql et reprendre le nom de la base de données et les credentials
    - Modifier le fichier pom.xml pour ajouter les dépendances :
        - org.postgresql/postgresql : le driver PostgreSql
    - Remplacer la datasource dans le fichier de configuration par une base de données PostgreSql
- La solution dans la branche https://gitlab.thalesdigital.io/coding-academy/coding-dojo/crud-with-spring-boot/-/tree/exercice6-solution?ref_type=heads

## Exercice 7 (bonus): Ajouter un intercepteur d'erreur pour générer des erreurs HTTP adaptées
- Partir de la solution de l'exercice 4 dans la branche https://gitlab.thalesdigital.io/coding-academy/coding-dojo/crud-with-spring-boot/-/tree/exercice6-solution?ref_type=heads
- Ajouter la classe RestApiExceptionHandler
    - Intercepter les TechnicalException et générer une erreur de serveur interne HttpStatus.INTERNAL_SERVER_ERROR
    - Intercepter les FunctionalException et générer une des erreurs
        - HttpStatus.UNAUTHORIZED
        - HttpStatus.FORBIDDEN
        - HttpStatus.NOT_FOUND
        - HttpStatus.INTERNAL_SERVER_ERROR si elle ne correspond pas à l'une des erreurs reconnues
- La solution dans la branche https://gitlab.thalesdigital.io/coding-academy/coding-dojo/crud-with-spring-boot/-/tree/exercice7-solution?ref_type=heads

## Exercice 8 (bonus) : Ajouter du code métier dans la bonne couche
- Ajouter un tri alphanumérique sur le nom par exemple ou chronologique sur la date de création ou lastupdated. Choix du tri avec un query parameter sur la requête get all

## Exercice 9 (bonus) : Ajouter la fonctionnalité search 
- Ajouter un endpoint avec la fonctionnalité search (sur le nom principalement et possiblement la description)
- Utiliser la méthode POST pour passer les critères dans un formulaire dans le body (format formulaire ou JSON). Rechercher le texte dans les champs name et/ou description

## Annexes pour Architecture logicielle en couches
### Avantages
L'architecture trois tiers facilite
- grandement l'implémentation des tests unitaires :
    - Le plus important à tester et je dirais même presque l'essentiel est le code métier. Donc de la couche Métier.
    - Le code de la couche Présentation n'est pas nécessairement à couvrir par les tests unitaires.
        - Si la couche Présentation consiste en une IHM client lourd ou GWT par exemple, elle peut être testée par des tests automatiques à l'aide d'outils comme Sélénium ou autres ... .
        - Si la couche Présentation consiste en une API Rest, elle peut être testée par des requêtes Http Rest dans différentes conditions (paramètres d'entrés, jeux de données de tests dédiés). Ca peut être automatisé.
- la compréhension du code
    - L'architecture en couches trois tiers sépare les différentes responsabilités de l'application en trois couches distinctes qui permet aux développeurs de se concentrer sur une seule couche à la fois, facilitant ainsi une meilleure compréhension du code et une plus grande clarté dans la répartition des tâches.
- l'intégration d'un nouveau développeur
    - si le développeur connaît déjà cette structuration de code, il aura moins de mal à l'appréhender, à rentrer dedans et à trouver ce qu'il cherche.
    - Même s'il ne connaît pas cette structuration de code, du fait du découpage, il saura déjà dans quel partie de code où chercher, intuitivement.
- la MCO
    - facilitée par une bonne compréhension du code
    - les évolutions sont facilité du fait de la ségrégation du code. Par exemple :
        - Le remplacement du fournisseur de la base de données PostgreSql par ElasticSearch ne touchera que la couche de persistance sans avoir besoin de toucher au code métier ou à la couche de présentation.
        - Le remplacement d'une IHM client lourd comme Swing par une API Rest pour y accéder depuis un Front Web (Angular ou React) ne touchera que la couche de présentation et non pas la couche métier ou persistance.
        - Le changement de règles ou traitement métier sera lui aussi localisé sans modifié ni la présentation (IHM ou API Rest), ni la persistance.
    - il est plus facile de comprendre les modifications à apporter ou les corrections à effectuer. Les erreurs ou les bogues peuvent être détectés et résolus plus rapidement, car il est plus facile de localiser la couche responsable du problème.
- la modularité et la réutilisabilité
    - la séparation en couches permet d'interchanger des éléments techniques d'une couche sans remettre en question les autres couches. On peut, par exemple, remplacer une base de données PostgreSql par ElasticSearch tout en gardant intacte la couche métier. Ou remplacer une IHM client lourd type Swing par une API Rest et une IHM Web.
    - chaque couche peut être développée de manière indépendante et être modifiée sans affecter les autres couches, ce qui permet une plus grande modularité du code.
    - il devient plus facile de réutiliser le code dans d'autres parties de l'application ou dans d'autres projets.
- Tests unitaires
  La séparation des responsabilités dans l'architecture en couches trois tiers rend les tests unitaires plus faciles à mettre en place. Cela participe à l'augmentation de la détection des erreurs et de la qualité du code.
    - En effet, le plus important et le plus pertinent à tester est la partie métier. L'isolation de la partie métier permet de concentrer les tests sur celle-ci. De pouvoir tester directement les méthodes métier sans se compliquer la vie à faire des requêtes. Les dépendances du métier vers la couche de persistance sont légères par l'utilisation de l'inversion de dépendances. Toutes ces dépendances extérieures peuvent être mockées. La séparation en couches facilite l'utilisation des mocks.
    - L'IHM ou les API Rest (endpoints) peuvent être testés par des tests fonctionnels automatiques (Selenium) ou des tests automatiques par des requêtes sur les endpoints.
    - La persistance impose d'avoir accès à des éléments extérieurs (base de données, fichiers, ...) et ne peuvent donc pas faire partie des tests unitaires mais plus des tests d'intégration voir des tests fonctionnels
    - Les différentes couches peuvent être testées individuellement, ce qui facilite la détection des erreurs et l'assurance de la qualité du code.

En résumé, l'architecture en couches trois tiers permet une meilleure compréhension du code en séparant les responsabilités, en favorisant la réutilisabilité, en simplifiant la maintenance, en offrant une plus grande évolutivité et en facilitant les tests unitaires. Ces avantages contribuent à un code plus clair, mieux structuré et plus facile à maintenir à long terme.

### Inconvénients
- plus de code à écrire
    - même si ce n'est que pour faire passe-plat. Certains feront des raccourcis en ne faisant qu'une couche en mettant des requêtes SQL directement dans la méthode du point d'entrée (endpoint) de la requête Rest. C'est à bannir, car la MCO et les évolutions futures en patiront. Si on change de cots (base de données), il faudra tout réécrire et tout le code sera impacté avec du code métier au milieu, ... .
- plus de règles. Il faut un minimum de rigueur pour respecter ces contraintes. Mais, le développement logiciel c'est de la rigueur. Si le développeur n'en veut pas ou n'en est pas capable, il s'est peut-être trompé de métier.
