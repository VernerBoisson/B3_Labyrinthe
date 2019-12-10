const express = require('express')
const app = express()
 
app.use('/levels', require('./routes/levels'))

app.get('/', function (req, res) {
  res.redirect('/levels')
})


module.exports = app;