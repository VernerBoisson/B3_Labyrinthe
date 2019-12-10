// Require Collections
var levels = require('./levels');
var obstacles = require('./obstacles');
var tests = require('./tests');

var mongoose = require('mongoose');
var express = require('express');

var options = {
     useNewUrlParser: true,
     useUnifiedTopology: true
};

// Connection
mongoose.connect('mongodb://localhost:27017/mazerunner', options);

// Gestion
var db = mongoose.connection;

db.on('error', console.error.bind(console, 'connection error:'));

db.once('open', function () {
     console.log("Connection Successful!");
});