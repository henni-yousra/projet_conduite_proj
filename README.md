# Scrumming
Un outil pour gérer la production logicielle en suivant la méthode Scrum.

## Équipe
- DREZEN
- DIALLO
- HENNI-MANSOUR

## [Release Repo](https://github.com/dokabdou/projet_conduite_proj_release)

### Backlog

| ID   | En tant que...   | Je souhaite pouvoir...                                                                                     | Afin de...                                     | Priorité | Difficulté | Sprint | Statut   | Critères d'acceptation                                                                 |
|------|-------------------|-----------------------------------------------------------------------------------------------------------|------------------------------------------------|----------|------------|--------|----------|-----------------------------------------------------------------------------------------|
| US1  | Visiteur          | Accéder à la page d'accueil avec l'option de me connecter ou de créer un compte                            | Démarrer l'utilisation de la plateforme        | Haute    | 1          | 0      | fait   | L'utilisateur peut accéder à la page d'accueil et voir les options de connexion/inscription. |
| US2  | Visiteur          | Créer un compte et me connecter ensuite                                                                   | Avoir un accès personnalisé à l'application    | Haute    | 2          | 0      | fait   | L'utilisateur peut s'inscrire et se connecter avec succès.                             |
| US3  | Utilisateur       | Être redirigé vers mon tableau de bord après connexion                                                    | Naviguer vers l'espace utilisateur             | Haute    | 1          | 0      | fait   | Après une connexion réussie, l'utilisateur est redirigé vers son tableau de bord.      |
| US4  | Utilisateur       | Voir l'option de créer un projet (devenant par défaut chef de projet) ou d'accéder à un projet existant   | Gérer ou rejoindre un projet                   | Haute    | 2          | 1      | fait  | L'utilisateur voit l'option de création de projet ou d'accès à un projet existant.     |
| US5  | Utilisateur       | Visualiser la liste de mes projets sur le tableau de bord                                                 | Voir mes projets actifs                        | Moyenne  | 1          | 1      | fait  | L'utilisateur voit une liste des projets auxquels il est associé.                     |
| US6  | Utilisateur       | Cliquer sur "Créer un nouveau projet" et être redirigé vers une page avec un formulaire (titre, description) | Initier un nouveau projet                    | Haute    | 2          | 1      | fait  | L'utilisateur peut créer un nouveau projet en remplissant un formulaire.               |
| US7  | Utilisateur       | Cliquer sur "Accéder à un projet existant", entrer un code PIN et un rôle pour y accéder                  | Rejoindre un projet déjà créé                  | Haute    | 3          | 1      | À faire  | L'utilisateur peut rejoindre un projet existant en entrant un code PIN et un rôle.     |
| US8  | Utilisateur       | Cliquer sur un projet dans ma liste pour accéder à sa page dédiée                                         | Voir les détails et gérer le projet            | Moyenne  | 1          | 1      | À faire  | L'utilisateur peut accéder à la page d'un projet spécifique.                           |
| US9  | Utilisateur       | Accéder aux sections "Besoins", "Tâches", "Documentation", "Release" et "Sprints" via une barre de navigation | Gérer les différents aspects du projet      | Haute    | 2          | 1      | À faire  | L'utilisateur peut naviguer entre les différentes sections du projet via un menu.     |
| US10 | Utilisateur       | Visualiser la description et les membres du projet sur la page dédiée                                     | Obtenir une vue d'ensemble du projet           | Moyenne  | 2          | 1      | À faire  | L'utilisateur peut voir la description du projet et les membres associés.              |
| US11 | Chef de projet    | Ajouter et supprimer des membres du projet                                                                | Gérer la composition de l'équipe               | Haute    | 3          | 1      | À faire  | Le chef de projet peut ajouter ou supprimer des membres du projet.                     |
| US12 | Utilisateur       | Cliquer sur le bouton "Besoins" pour afficher une liste de besoins                                        | Visualiser et gérer les besoins                | Moyenne  | 1          | 1      | À faire  | L'utilisateur peut voir et accéder à une liste de besoins pour le projet.              |
| US13 | Utilisateur       | Créer, modifier et supprimer des besoins dans la liste des besoins                                        | Adapter les besoins en fonction des évolutions | Haute    | 3          | 1      | À faire  | L'utilisateur peut ajouter, modifier ou supprimer des besoins.                         |
| US14 | Utilisateur       | Cliquer sur le bouton "Tâches" pour afficher une liste de tâches                                          | Visualiser et organiser les tâches             | Moyenne  | 1          | 1      | À faire  | L'utilisateur peut voir et accéder à la liste des tâches.                              |
| US15 | Utilisateur       | Créer, modifier et supprimer des tâches dans la liste des tâches                                          | Adapter les tâches en fonction des évolutions  | Haute    | 3          | 1      | À faire  | L'utilisateur peut ajouter, modifier ou supprimer des tâches dans la liste.            |

## Qualification des Besoins

- **Types** : Tous les besoins sont qualifiés comme de nouvelles fonctionnalités, car ils introduisent des éléments essentiels pour l'application.
- **Importance**: Les priorités sont basées sur leur impact sur l'expérience utilisateur et la fonctionnalité globale de la plateforme.
- **Difficulté**: Les difficultés sont estimées en fonction de la complexité des tâches et de l'intégration dans le système existant.

### Diagrammes

![Diagramme de US1 à US8](/assests/Maquettes%20CDP-1.jpg)
![Diagramme de US9 à US11](/assests/Maquettes%20CDP-2.jpg)

### Choix des Technologies

- **Architecture 3 tiers :**
  - **UI :** Angular
  - **Modèle :** Spring-Boot
  - **BDD :** PostgreSQL

### Sprints

- [21/10 - 03/11](Sprint0.md): [Tâches](Task0.md)
- [04/10 - 18/11](Sprint1.md): [Tâches](Task1.md)
- [19/10 - 01/12](Sprint2.md): [Tâches](Task2.md)

### Releases

- [RELEASE-1.0](Release1.0md)
- [RELEASE-1.1](Release1.1.md)
