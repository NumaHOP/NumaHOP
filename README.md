# NumaHop (Plate-forme de gestion de contenus numérisés)

[NumaHOP](https://www.numahop.fr/) permet de gérer une chaîne de numérisation de documents de l’import des notices et du constat d’état des documents physiques à la diffusion et à l’archivage grâce à un interfaçage largement automatisé entre les différentes étapes de la numérisation impliquant les acteurs concernés (prestataires de numérisation, bibliothèques, diffuseurs, CINES).
Le bénéfice de cette réalisation est triple :

-   privilégier l’usage de formats normalisés
-   favoriser la standardisation des méthodes de travail
-   permettre la mutualisation et l’échange des savoir-faire entre les établissements qui utilisent cette plate-forme.

NumaHOP est composé de plusieurs modules fonctionnels permettant :

-   de convertir des notices au format UNIMARC ou EAD dans des formats interopérables : Dublin Core, Dublin Core qualifié
-   de réaliser des constats d’état pour les lots de documents à numériser envoyés vers les prestataires de numérisation
-   de recevoir les lots numérisés par le prestataire (images et métadonnées) et de les contrôler
-   d’utiliser des fonctions de workflow, de contrôle et de structuration des projets
-   de valider les unités documentaires numérisées (images + métadonnées) et de les exporter vers les diffuseurs et les archiveurs
-   de produire des fichiers OCR, METS, images dérivées...

NumaHOP offre la possibilité de disséminer largement et de manière automatisée les contenus numérisés, à la fois sous l’identité des établissements à travers leurs bibliothèques numériques, mais aussi vers des plates-formes externes telles qu’Internet Archive ou OMEKAS.

## Commencer

NumaHop est une application SpringBoot construite avec [Maven](https://maven.apache.org/).

Vous pouvez cloner le repository.

Les instructions suivantes permettent d'installer NumaHop sur un poste de développement.

### Prérequis

Numahop nécessite au préalable l'installation des outils listés ci-dessous.

-   OpenJdk 17
-   MariaDB
-   Elasticsearch 8
-   Tesseract
-   ImageMagick
-   Exiftool

Les versions proposées sont valides dans un environnement Debian 12.

Il vous faudra également un user linux dédié disposant d'un repository maven (.m2).

Pour des recherches Z39.50, il faudra également installer la librairie libyaz4 - Voir [yaz4.md](yaz4.md)

### Première mise en oeuvre

Configuration minimale : **application.yml** | **application-[PROFILE].yml**

Les fichiers de configuration **.yml** doivent être mis à jour pour se conformer à votre installation.

Le profil "dev" est dédié à la mise en place d'un environnement de developpement.

**Pour simplement lancer NumaHop, l'utilisation du profil "prod" est préconisée.**

-   MariaDB

Il faut simplement créer une base et un utilisateur autorisé.
La structure et les données paramétrées seront créées lors du 1er démarrage de l'application.

```
spring:
    datasource:
        driverClassName: org.mariadb.jdbc.Driver
        url: jdbc:mariadb://**urlDataBase**
        username: **userName**
        password: **userpassword**
        maximum-pool-size: 20
```

-   elasticsearch

```
spring:
    elasticsearch:
        uris:
            - http://localhost:9200

elasticsearch:
    bulk_size: 1000
    index:
        name: **indexName**
```

-   ImageMagick

```
imageMagick:
    convert: **imConvertPath**
    identify: **imIdentifyPath**
```

-   exifTool

```
exifTool:
    process: **exiftoolPath**
    quot_char: ''
```

-   tesseract

```
tesseract:
    process: **tesseractPath**
```

-   Autres paramètres obligatoires

Certains paramètres supplémentaires doivent être également renseignés

```
# lister les bibliotheques utilisatrices comme suit: library_identifier1, library_identifier2, etc..
instance:
    libraries: library_bibliotheque

# répertoires de stockage images
storage:
    binaries: **path to Image Dir repository**
    digest: MD5
    depth: 3

# répertoires de travail **path to workBaseDir**
uploadPath:
    condition_report: **path to workBaseDir**/upload/condition_report
    ead: **path to workBaseDir**/upload/ead
    import: **path to workBaseDir**/upload/import
    library: **path to workBaseDir**/upload/library
    user: **path to workBaseDir**/upload/user
    template: **path to workBaseDir**/upload/templates

export:
    rdf:
        default_uri: http://numahop.fr/
    ssh:
        knownHosts: **path to workBaseDir**/config/known_hosts
        strictHostKeyChecking: yes
# Services externes
services:
    cines:
        aip: **path to workBaseDir**/cines/aip
        cache: **path to workBaseDir**/cines/cache
        facile: https://facile.cines.fr/xml
        xsd:
           sip: **path to workBaseDir**/xsd/sip.xsd
    archive:
        alto: **path to workBaseDir**/archive/alto
    metaDatas:
        path: **path to workBaseDir**/metadatas
    deliveryreporting:
        path: **path to workBaseDir**/deliveryReporting
    omeka:
        cache: **path to workBaseDir**/omeka/cache
    ftpexport:
        cache: **path to workBaseDir**/ftpexport/cache
    digitalLibraryDiffusion:
        cache: **path to workBaseDir**/digitalLibraryDiffusion/cache
```

### Démarrage

Pour démarrer l'application:

-   se connecter avec votre utilisateur dédié disposant d'un repository maven
-   se positionner à la racine du projet
-   exécuter la commande suivante:

```
$ mvn clean package spring-boot:run -Pprod -Drun.jvmArguments="-Dspring.profiles.active=prod"
```

En cas de problème, une commande alternative :

```
$  mvn spring-boot:run -P prod  -Drun.arguments="--spring.profiles.active=prod,--spring.config.additional-location=file:/opt/pgcn/src/main/resources/config/"
```

En fin de build, l'application est lancée sur le port 8080.
Vous pouvez vous logger en admin / admin afin d'effectuer le paramétrage de base, créer des utilisateurs autorisés etc..

## Les acteurs du projet

## Maîtrise d'ouvrage

-   Bibliothèque Sainte-Geneviève
-   Bibliothèque de Sciences Po
-   BULAC

## Maîtrise d'oeuvre

-   **TECH'advantage** - https://www.tech-advantage.com/

## Contribution

TECH'advantage reste responsable de la version principale.
Les issues et/ou merge requests doivent nous être adressées.

TECH'advantage étudiera également toute demande d'évolution de l'application.

## Licence

Le projet est sous licence AGPLv3 - Voir le fichier [LICENSE.md](LICENSE) pour plus d'informations.
