
# Installation & lancement du serveur

## Base de données 
(l'accès depuis l’extérieur aux serveurs ne se fait que via un tunnel ssh)

    $ ssh idnum@ssh.emi.u-bordeaux.fr -L 5432:pgsql:5432

## Backend

    $ mvn clean install
    $ mvn spring-boot:run

le back est lancé en local sur le port 5000, et est accessible avec un navigateur à l'adresse <localhost:5000>.

# Frontend

    $ npm install
    $ ng serve

l'app angular est lancée sur le port 42OO

## Docker
```
sudo docker compose up
```

<localhost:5000/index.html>

### Diagrammes

![Diagramme de US1 à US8](/assests/Maquettes%20CDP-1.jpg)
![Diagramme de US9 à US11](/assests/Maquettes%20CDP-2.jpg)


# Définition des rôles

## Visiteur
Un visiteur est une personne qui arrive sur le site mais n'est pas encore inscrite ou identifiée.

## Utilisateur
Ce rôle représente toutes les personnes qui ont un compte actif et qui sont connectées sur le site. Le rôle "utilisateur" existe en dehors des projets, c'est-à-dire sur la page d'accueil et dans la navigation générale du site. Ce rôle regroupe le chef de projet et les membres d'un projet. Sur certains aspects du site, ils ont les mêmes droits.

## Chef de Projet
Le chef de projet est un utilisateur qui a créé un nouveau projet. En tant que créateur et responsable, il gère l'administration du projet et dispose de plus de droits que les autres membres. Il a la possibilité d'ajouter des membres.

## Membre
Ce rôle regroupe tous les membres d'un projet qui ne sont pas chefs de projet. Ils ont des droits plus limités que ceux du chef et ne peuvent rejoindre le projet qu'avec son accord (après demande).


![Diagramme de rôles](/assests/Maquettes%20CDP-3.jpg)