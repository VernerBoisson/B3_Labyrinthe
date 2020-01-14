const express = require('express')
const router = express.Router()
const Level = require('../models/levels')

// Afficher
router.get('/levels', function(req, res, next) {
    Level.getAll().then((results) => {
    res.format({
    json: () => {
      res.send({
        data: results,
      })
    }
  })
}).catch(next)
})
 
router.get('/levels/:levelId', (req, res, next) => {
  console.log("GET levelId")
  Level.get(req.params.levelId).then((level) => {
    console.log(level)
    if (!level) return next()
    res.format({
      json: () => { res.send({ data: level }) }
    })
  }).catch(next)

})

// Insert
router.post('/', (req, res, next) => {
  console.log(req.body)
  if (
    !req.body.name || req.body.name === '' ||
    !req.body.created_at ||req.body.created_at === '' ||
    !req.body.updated_at ||req.body.updated_at === '' ||
    !req.body.composition ||req.body.composition === '' ||
    !req.body.walls ||req.body.walls === '' ||
    !req.body.traps ||req.body.traps === '' ||
    !req.body.mud ||req.body.mud === '' ||
    !req.body.start ||req.body.start === '' ||
    !req.body.finish ||req.body.finish === '' 
  ) {
    let err = new Error('Bad Request')
    err.status = 400
    return next(err)
  }

  Level.insert(req.body).then(() => {
    res.format({
      html: () => { res.redirect('/levels') },
      json: () => { res.status(201).send({message: 'success'}) }
    })
  }).catch(next)
})

// Update
router.put('/:levelId', (req, res, next) => {
  Level.update(req.params.levelId, req.body).then(() => {
    res.format({
      json: () => { res.status(200).send({ message: 'success' }) }
    })
  }).catch(next)
})

// Delete
router.delete('/:levelId', (req, res, next) => {
  Level.remove(req.params.levelId).then(() => {
    res.format({
      json: () => { res.status(200).send({ message: 'success' }) }
    })
  }).catch(next)
})

module.exports = router