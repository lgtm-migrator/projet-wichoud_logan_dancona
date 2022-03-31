# projet-wichoud_logan_dancona

Client: Bertyl Chapuis & Miguel Santamaria
Product owner: Bertyl Chapuis & Miguel Santamaria

## Justification de la méthode

Nous allons utiliser le processus AGILE afin d'avoir un prototype rapidement et de pouvoir adapter notre fonctionnement avec les besoins du client.

## Description de l'équipe

### Nicolas
Nicolas est à l'aise avec les maths et la programmation. En revanche il a parfois de la peine à s'organiser à l'avance et à prendre des initiatives, mais quand on lui attribue une tâche bien définie il se donne corps et âme pour arriver à ses fins. Il a également de bonnes capacités sociales et n'a pas spécialement de difficultés pour s'intégrer dans un groupe de travail.

### Victoria
Victoria est à l'aise avec la rédaction et la programmation. Victoria a de très bonnes capacités sociales, elle comprend facilement les autres dans leurs besoins et a une aisance dans la gestion de conflits ou tout autre situation sujette à la communication. 
Aussi, elle il est facile pour elle de mettre en place des plans d'organisation clairs et pertinents, bien qu'elle peine parfois à les suivre par la suite.

### Olivier
Olivier est à l'aise avec l'organisation et la programmation. Souvent appliqué et consciencieux, Olivier saura motiver les troupes afin de se surpasser. En revanche, il a du mal à gérer la pression face aux échéances et au retard. 

## Description du mode de collaboration

Nous allons chaqun créer des issues et les lier à une branche pour chaque tâche. A la fin de chaque tâche, les acteurs effectueront une pull-request qui devra être examinée et validée par un pair.
La branche main est protégée. Afin de pousser un commit dessus, il faut qu'un collègue passe notre PR en revue. La branche main est protégée. Afin de pousser un commit dessus, il faut qu'un collègue passe notre PR en revue. Nous allons organiser une séance hebdomadaire et distribuer les tâches à celle-ci. De plus nous allons utiliser des étiquettes pour les issues.  

### Organisation

Nous utilisons l'outils Kanban afin de nous organiser. Une fois les issues créés, nous les déplaçons manuellement dans la colonne "in progress" lorsque nous commençons à travailler dessus. Une fois l'issue résolu et la pull-request associée fermée nous déplaçons la tâche dans la colonne terminé.

Nous avons 1 Kanban général et nous utiliserons 1 Kanban par sprint. 

### Création de fonctionnalité:
Pour chaque fonctionnalité nous allons suivre cette procédure.

0. Rédiger une histoire pour des utilisateurs spécifiques
1. Définir les spécifications
2. Séparer le problème en tâches solvables
3. Création de tests puis développement (ordre important)
4. Séances d'intégration

### Séance hebdomadaires

Chaque semaine nous nous retrouvons pour évaluer l'avancée du projet, les tâches restantes et la distribution de ces dernières en fonction des objectifs fixés.

les points à aborder sont:

* Examiner les progrès réalisés et comparer au progrès attendus
* Sélectionner les stories à mettre en oeuvre ensemble
* Diviser les stories en tâches
* Répartition du travail

#### Convention de nommage

* Constantes en majuscules et séparation de mot par un _. Exemple: VITESSE_LUMIERE
* Nom de variable: camelcase Exemple: plusPetitMultipleCommun
* Nom de méthodes: snake Exemple: calculer_superficie()
* Nom des paramètres: camelcase Exemple: calculer_superficie(nbCote,tailleCote)
* Release: notation semver lors de la fin de chaque sprint
* Langue: Français
* Autoformatage: formatage par défaut d'intelliJ

