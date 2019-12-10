const router = require("express").Router()

router.get('/', (req, res, next) => {
    res.json({1:"get level"})
})

router.post('/', (req, res, next) => {
    res.json({1:"post level"})
})

module.exports = router