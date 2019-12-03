const express = require('express')
const app = express()
 
app.get('/', function (req, res) {
  res.json({1:'Hello World'})
})

module.exports = app;