# B3_Labyrinthe

Nous avions comme projet, de fin d’année de bachelor 3, à Ynov Bordeaux, d’effectuer un éditeur et un résolveur de labyrinthe en deux applications graphiques qui communique avec une API qui fait le pont avec une base de donnée. Tout cela hébergé en local.

## Installation

un docker-compose contenant MongoDB et Node avec le dossier web-api partagé et qui écoute sur le port 8080 a été mis en place.

### Lancement de MongoDB

Requiert MongoDB

```
systemctl start mongod
```

### Lancement de l'API

Requiert Node et NPM

```
cd web-api
npm i
node index.js
```

#### Lancement des IHM

Requiert Java 11

```
java -jar ihm-edit.jar
java -jar ihm-resolve.jar
```

### Auteurs

- [Mael Pena](https://github.com/Maelpena)
- [Verner Boisson](https://github.com/VernerBoisson)