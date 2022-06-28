# Welcome to restAPI_JEE project

Salut et bienvenue sur mon projet d'étude. 
Vous trouverez un projet **JEE8** qui s'execute avec *maven* via les commandes suivantes.
1. **mvn wildfly:run** 
2. **mvn wildfly:redeploy**


# Les APIs
## Authentification, Login, Logout
Il existe 3 API disponible sans avoir besoin du token d'authentification.
Ce sont les API 
 1. authent : Permet de créer un compte dans le système
 2. login : Permet d'initialiser qui sera utile pour exécuter les autres API
 3. logout : Permet de supprimer son token afin de limiter l'accès

### authent (POST)
path : 
```http://[SERVER]:[PORT]/RESTAPI/resources/departement/authent```
body :
```yaml
{ "email : "username@domaine.tld", 
"password" : "thePassword"}
```
### login  (POST)
path :
 ```http://[SERVER]:[PORT]/RESTAPI/resources/departement/login ```
body :
```yaml
{ "email : "username@domaine.tld", 
"password" : "thePassword"}
```

### logout  (POST)
path : 
```http://[SERVER]:[PORT]/RESTAPI/resources/departement/logout```
body :
```yaml
{ "email : "username@domaine.tld", 
"password" : "thePassword"}
```
## Les API REST en GET
Il existe 2 API GET disponible 
 1. departement : Permet de récupéré les informations des départements enregistrés.
 2. getById : Permet de récupéré les informations d'un département en fonction de son numéro de département.

### departement (GET)
path : 
```http://[SERVER]:[PORT]/RESTAPI/resources/departement/```
authorization : 
```yaml
{ "bearerToken: "6a115178-e6ae-11ec-8fea-0242ac120002"}
```
### getById (GET)
path : 
```http://[SERVER]:[PORT]/RESTAPI/resources/departement/getByID/59```
authorization : 
```yaml
{ "bearerToken: "6a115178-e6ae-11ec-8fea-0242ac120002"}
```
## L'API REST en POST
Elle permet d'ajouter un département en base de donnée
### addDep(POST)
path : 
```http://[SERVER]:[PORT]/RESTAPI/resources/departement/addDep```
authorization : 
```yaml
{ "bearerToken: "6a115178-e6ae-11ec-8fea-0242ac120002"}
```
body :
```yaml
{ "nomDepartement: "Pas de calais", 
"idDepartement" : "59",
"prefecture" : "Lille",
"region" : "HDF"}
```
## L'API REST en PUT
Elle permet de mettre à jour un département en base de donnée en fonction de son numéro de département
### update(PUT)
path : 
```http://[SERVER]:[PORT]/RESTAPI/resources/departement/update```
authorization : 
```yaml
{ "bearerToken: "6a115178-e6ae-11ec-8fea-0242ac120002"}
```
body :
```yaml
{ "nomDepartement: "Nord", 
"idDepartement" : "59"}
```
## L'API REST en DELETE
Elle permet de supprimer un département en base de donnée en fonction de son numéro de département
### deleteDep(DELETE)
path : 
```http://[SERVER]:[PORT]/RESTAPI/resources/departement/deleteDep/59```
authorization : 
```yaml
{ "bearerToken: "6a115178-e6ae-11ec-8fea-0242ac120002"}
```
