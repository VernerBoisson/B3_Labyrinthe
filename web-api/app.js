const express = require('express')
const app = express()
 
require('./models/model')

app.use('/levels', require('./routes/levels'))

app.get('/', function (req, res) {
  res.redirect('/levels')
})

module.exports = app;