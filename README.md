[![Total alerts](https://img.shields.io/lgtm/alerts/g/dil-classroom/projet-wichoud_logan_dancona.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/dil-classroom/projet-wichoud_logan_dancona/alerts/)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/dil-classroom/projet-wichoud_logan_dancona.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/dil-classroom/projet-wichoud_logan_dancona/context:java)

# Statique

Voici un nouveau générateur de site statique.

## Manuel Utilisateur

Un générateur de site statique est un outil permettant de générer automatiquement un site web à partir de fichier de configuration. Le générateur prend comme source un fichier markdown contenant le contenu du site web et/ou un fichier yaml contenant la configuration du site

## Installation

Installer java

    sudo apt-get install openjdk-17-jre

Compiler l'application

    mvn clean install
    unzip -o target/statique.zip

Ajouter l'application au PATH

    export PATH=$PATH:`pwd`/statique/bin

## Commandes

### Initialiser un site statique

`statique init <nom>`

Cette commande permet de créer un projet avec un nom. Elle va créer un fichier de configuration .YAML contenant des informations générales liées au site (titre, description, domaine, etc.) et un fichier Markdown index.md contenant une page d’accueil avec des métadonnées et du contenu.

### Compiler un site statique

`statique build <nom>`

Cette commande permet de compiler un site. En somme elle va créer un dossier /mon/nom/build contenant des fichiers HTML
correspondant au contenu de chaque page du site statique.

Si l'option --watch est spécifiée, le site sera recompilé lorsqu'un changement est détecté sur le disque.

### Nettoyer un site statique

`statique clean <nom>`

Cette commande permet de nettoyer le dossier build pour le site <nom>.

### Servir un site statique

`statique serve <nom>`

Cette commande permet de servir le site web <nom> dans un naviguateur.

Si l'option --watch est spécifiée, le site sera actualisé lorsqu'un changement est détecté sur le disque.

## Utilisation

### Contenu du site

Le contenu du site peut être intégré dans un fichier markdown (.md). Voici les fonctionnalités supportées

#### Titre

`#Mon titre` => permet de définir un titre. (Ajouter des dièses correspond à faire un sous-titre de niveau inférieur)

#### Image

`![légende](uri)` => génére une image

#### Liste

    - item 1
    - item 2

Va créer une liste à puce.

#### Paragraphe

Il suffit d'ajouter un retour à la ligne dans le texte pour générer un paragraphe

#### Moteur de template

Grâce au moteur de template, on peut intégrer des bouts de code grâce à la syntaxe `{% bout_de_code.html}` ou des informations de configuration de la manière suivante: `{{site.titre}}`

### Configuration du site

Grâce au fichier yaml on peut donner les informations générales du site grâce à la syntaxe suivante:

    clé1: valeur
    clé2: valeur
