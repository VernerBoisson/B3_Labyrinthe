const router = require('express').Router()

router.get('/', (request, response, next) => {
    return users.getAll()
    .then((values)=>{
        response.format({
            json: function(){
                response.json(values);
            }
        })
    }).catch((error)=>{
        response.status(500).send(error)
    })
})

module.exports = router