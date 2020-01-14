const express = require('express')
const router = express.Router()
const Obstacle = require('../models/obstacles')

// Afficher
router.get('/obstacles', function(req, res, next) {
    Obstacle.getAll().then((results) => {
    res.format({
    json: () => {
      res.send({
        data: results,
      })
    }
  })
}).catch(next)
})
 
router.get('/obstacles/:obstacleId', (req, res, next) => {
  console.log("GET obstacleId")
  Obstacle.get(req.params.obstacleId).then((obstacle) => {
    console.log(obstacle)
    if (!obstacle) return next()
    res.format({
      json: () => { res.send({ data: obstacle }) }
    })
  }).catch(next)

})

// Insert
router.post('/', (req, res, next) => {
  console.log(req.body)
  if (
    !req.body.name || req.body.name === '' ||
    !req.body.is_crossable || req.body.is_crossable === '' ||
    !req.body.effect || req.body.effect === '' ||
    !req.body.appearance || req.body.appearance === '' ||
    !req.body.min || req.body.min === '' ||
    !req.body.max || req.body.max === ''
  ) {
    let err = new Error('Error request')
    err.status = 400
    return next(err)
  }

  Obstacle.insert(req.body).then(() => {
    res.format({
      html: () => { res.redirect('/obstacles') },
      json: () => { res.status(201).send({message: 'success'}) }
    })
  }).catch(next)
})

// Update
router.put('/:obstacleId', (req, res, next) => {
    Obstacle.update(req.params.obstacleId, req.body).then(() => {
    res.format({
      json: () => { res.status(200).send({ message: 'success' }) }
    })
  }).catch(next)
})

// Delete
router.delete('/:obstacleId', (req, res, next) => {
    Obstacle.remove(req.params.obstacleId).then(() => {
    res.format({
      json: () => { res.status(200).send({ message: 'success' }) }
    })
  }).catch(next)
})

module.exports = router