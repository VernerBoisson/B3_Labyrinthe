const express = require('express')
const app = express()
const mongoose = require('mongoose')
 
require('./models/model')

app.use('/levels', require('./routes/levels'))

app.get('/', function (req, res) {
  res.redirect('/levels')
})

module.exports = app;