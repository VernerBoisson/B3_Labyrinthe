const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

module.exports = function () {
    let server = express(),
        create,
        start;

    create = (config, db) => {
        let routes = require('../routes');

        server.set('env', config.env);
        server.set('port', config.port);
        server.set('hostname', config.hostname);
        
        server.use(bodyParser.json());
        server.use(bodyParser.urlencoded({
            extended: false
        }));

        //connect the database
        mongoose.connect(
            db.database,
            { 
                useNewUrlParser: true,
                useCreateIndex: true
            }
        );

        //routes to mazerunner
        routes.init(server);
    };

    
    start = () => {
        let hostname = server.get('hostname'),
            port = server.get('port');
        server.listen(port, function () {
            console.log('Express server listening on - http://' + hostname + ':' + port);
        });

        server.get('/', function (req, res) {
            res.send('Bienvenue sur le Maze Runner, Look /mazerunner/level or /mazerunner/obstacle');
        })
    };
    return {
        create: create,
        start: start
    };
};