const express = require('express');
const obstacleService = require('../../services/obstacles/obstacle');
let router = express.Router();

router.get('/', obstacleService.getObstacle);

router.get('/:id', obstacleService.getObstacleId);

router.post('/', obstacleService.createObstacle);

router.put('/:id', obstacleService.updateObtsacle);

router.delete('/:id', obstacleService.deleteObstacle);

module.exports = router;