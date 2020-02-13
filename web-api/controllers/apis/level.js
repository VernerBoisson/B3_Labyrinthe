const express = require('express');
const levelService = require('../../services/levels/level');
let router = express.Router();

router.get('/', levelService.getLevel);

router.get('/:id', levelService.getLevelById);

router.post('/', levelService.createLevel);

router.put('/:id', levelService.updateLevel);

router.delete('/:id', levelService.deleteLevel);

module.exports = router;