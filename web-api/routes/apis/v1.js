const userController = require('../../controllers/apis/user');
const levelController = require('../../controllers/apis/level')
const obstacleController = require('../../controllers/apis/obstacle')

const express = require('express');
let router = express.Router();
router.use('/users', userController);
router.use('/level', levelController);
router.use('/obstacle', obstacleController)
module.exports = router;