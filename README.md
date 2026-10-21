# Controle continu - Civiscope (API)

API REST Spring Boot pour le module Java / Spring (Bachelor 3 EPSI). Le projet s'appuie sur le scenario CivicScope : regions, departements, elections presidentielles (donnees de reference dans le script SQL fourni).

## Stack

- Java 25
- Spring Boot 4.1
- Spring Web + Spring Data JPA
- PostgreSQL

## Prerequis

- JDK installe (le projet compile en Java 25)
- PostgreSQL en local
- Un client HTTP (navigateur, Postman, curl) pour tester les routes

## Base de donnees

1. Creer un utilisateur et une base `civiscope` (identifiants par defaut dans `src/main/resources/application-local.yaml` : user / mot de passe `civiscope`).
2. Importer le jeu de donnees :

```text
psql -U civicscope -d civicscope -h localhost -f civicscope_data.sql
```

Le profil actif au demarrage est `local` (voir `application.yaml`).

## Lancer l'application

Depuis la racine du projet, sous PowerShell :

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-25.0.4.1"
.\mvnw.cmd spring-boot:run
```

Adapter `JAVA_HOME` si ton JDK est installe ailleurs. Le serveur ecoute sur le port **8080**.

Test rapide : `GET http://localhost:8080/ping` doit renvoyer `pong`.

## Endpoints principaux

| Route | Description |
|-------|-------------|
| `GET /ping` | Sante de l'API |
| `GET /regions` | Liste des regions |
| `GET /regions/{code}` | Une region par code |
| `POST /regions` | Ajout |
| `PUT /regions` | Mise a jour (par code) |
| `DELETE /regions` | Suppression (body JSON avec le code) |
| `GET /elections` | Liste (param optionnel `limit`) |
| `GET /elections/{id}` | Detail |
| `POST /elections` | Creation |
| `PUT /elections/{id}` | Modification |
| `DELETE /elections/{id}` | Suppression |

Les corps de requete sont en JSON. Les reponses d'erreur suivent les codes HTTP habituels (400, 404, 409, etc.).

## Structure du code

Organisation classique en couches :

- `controller` : exposition HTTP
- `service` : regles metier
- `repository` : acces JPA
- `model` : entites (geo, election)

Point d'entree : `CiviscopeApplication.java`.

## Auteur

Raphael Angoula - depot GitHub : [controle-continue](https://github.com/angoularaphael/controle-continue)
