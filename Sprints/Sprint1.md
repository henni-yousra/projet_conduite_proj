### Planification : 
01/11 - 15/11

# Sprint 1 - Développement Initial et Connexion des Composants

## Objectifs du Sprint
Ce sprint vise à établir les bases fonctionnelles du système, notamment l'inscription, la connexion des utilisateurs, et l'accès au tableau de bord.

## Contenu du Sprint 1

### 1. Backend
   - Mise en place de l’architecture backend avec Spring Boot et PostgreSQL.
   - Développement des entités, repos et services nécessaires pour gérer les utilisateurs.
   - Création des API REST pour l'inscription, la connexion, et la gestion des utilisateurs.

### 2. Frontend
   - Configuration initiale du projet Angular.
   - Développement des composants pour la page d'accueil, l'inscription, et la connexion.
   - Connexion des API REST backend au frontend pour permettre les fonctionnalités d'inscription et de connexion.

### 3. Tableau de Bord
   - Implémentation de la redirection vers le tableau de bord après connexion.
   - Début du développement du tableau de bord pour afficher les projets de l'utilisateur.

### 4. Tests et Validation
   - Tests unitaires et manuels des fonctionnalités principales : inscription, connexion, redirection vers le tableau de bord.
   - Détection et correction des bugs identifiés.

## User Stories incluses dans ce sprint

| ID   | Description                                                                                     | 
|------|-------------------------------------------------------------------------------------------------|
| US1  | En tant que visiteur, je souhaite pouvoir accéder à la page d'accueil avec l'option de me connecter ou de créer un compte, afin de démarrer l'utilisation de la plateforme. |
| US2  | En tant que visiteur, je souhaite pouvoir créer un compte et me connecter ensuite, afin d'avoir un accès personnalisé à l'application. |
| US3  | En tant qu'utilisateur, je souhaite être redirigé vers mon tableau de bord après ma connexion, afin de pouvoir naviguer vers l'espace utilisateur. |
| US4  | En tant qu'utilisateur, je souhaite visualiser la liste de mes projets sur le tableau de bord, afin de voir mes projets actifs. |
| US5  | En tant qu'utilisateur, je souhaite cliquer sur "Créer un nouveau projet" afin d'avoir un formulaire (titre, description) pour initier un nouveau projet. |



## Livrables
- `application.properties` : Configuration du backend (Spring Boot, PostgreSQL).
- Fonctionnalités de base pour l'inscription et la connexion (backend et frontend).
- Début de l’implémentation du tableau de bord utilisateur.

### [Tâches](Task1.md)
