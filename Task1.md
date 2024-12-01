# Tâches du Sprint 1

## Tâches Détails

| Tâche ID | Description                                                                                         | Liée à US     | Dépendances        | Statut   | Responsable          |
|----------|-----------------------------------------------------------------------------------------------------|---------------|--------------------|----------|----------------------|
| T1.1     | Créer la page d'accueil avec les options de connexion et création de projet.                        | US1           | T0.1               | Terminé  | DREZEN               |
| T1.2     | Créer le formulaire d'inscription et de connexion.                                                  | US2           | T0.2, T1.1         | Terminé  | Henni-Mansour               |
| T1.3     | Implémenter la redirection vers le tableau de bord après une connexion réussie.                     | US3           | T1.2               | Terminé  | Henni-Mansour               |
| T1.4     | Mettre en place les tests de base pour vérifier les fonctionnalités de connexion et d'inscription.  | US1, US2, US3 | T1.2, T1.3         | En cours | Henni-Mansour        |
| T1.5     | Créer les entités, les repos, et les services nécessaires pour gérer les projets.                  | US1, US2      | T0.2               | Terminé  | DIALLO               |
| T1.6     | Développer les API REST pour l'inscription, la connexion, et la gestion des utilisateurs.           | US1, US2      | T1.5               | Terminé  | Henni-Mansour               |
| T1.7     | Créer les composants Angular pour la page d’accueil, l'inscription, et la connexion.                | US1, US2      | T1.1               | Terminé  | DREZEN & Henni-Mansour               |
| T1.8     | Connecter le frontend aux API backend pour l’inscription et la connexion des utilisateurs.         | US1, US2      | T1.7, T1.6         | Terminé  | Henni-Mansour                |
| T1.9     | Documenter les fonctionnalités implémentées et les tests effectués.                                 | US1, US2, US3 | T1.4, T1.8         | Terminé  | Henni-Mansour        |
| T1.10    | Refactoriser le code en fonction des retours des tests et des besoins d'optimisation.               | US1, US2, US3 | T1.9               | Terminé  | Henni-Mansour        |

## Suivi d'avancement
- La configuration initiale du backend et du frontend est terminée.
- Les fonctionnalités d'inscription, de connexion, et de redirection vers le tableau de bord ont été implémentées et sont opérationnelles.
- Les tests de base pour la connexion et l'inscription ont été effectués avec succès.
- Les tâches liées à la création des composants frontend sont en cours.

## Prochaines Étapes
- Finaliser le développement de la page de tableau de bord utilisateur.
- Tester et documenter toutes les fonctionnalités du sprint 1 pour garantir la stabilité de la version 1.0.
