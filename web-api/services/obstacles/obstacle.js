const express = require('express');
const Obstacle = require('../../models/obstacle');

const getObstacle = async (req, res, next) => {
    try {

        let obstacles = await Obstacle.find({});

        if (obstacles.length > 0) {
            return res.status(200).json({
                'data': obstacles
            });
        }

        return res.status(404).json({
            'code': 'BAD_REQUEST_ERROR',
            'description': 'No obstacles found in the system'
        });
    } catch (error) {
        return res.status(500).json({
            'code': 'SERVER_ERROR',
            'description': 'something went wrong, Please try again'
        });
    }
}

const getObstacleId = async (req, res, next) => {
    try {
        let obstacle = await Obstacle.findById(req.params.id);
        if (obstacle) {
            return res.status(200).json({
                'message': `obstacle with id ${req.params.id} fetched successfully`,
                'data': obstacle
            });
        }

        return res.status(404).json({
            'code': 'BAD_REQUEST_ERROR',
            'description': 'No obstacles found in the system'
        });

    } catch (error) {

        return res.status(500).json({
            'code': 'SERVER_ERROR',
            'description': 'something went wrong, Please try again'
        });
    }
}

const createObstacle = async (req, res, next) => {
    try {
        const {
            _id,
            name,
            appearance,
            effect,
            min,
            max
        } = req.body;

        if (name === undefined || name === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'name is required',
                'field': 'name'
            });
        }
        
        if (appearance === undefined || appearance === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'appearance is required',
                'field': 'appearance'
            });
        }

        if (effect === undefined || effect === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'effect is required',
                'field': 'effect'
            });
        }

        if (min === undefined || min === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'min is required',
                'field': 'min'
            });
        }

        if (max === undefined || max === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'max is required',
                'field': 'stamaxrt'
            });
        }

        let IdExists = await Obstacle.findOne({
            "_id": _id
        });

        if (IdExists) {
            return res.status(409).json({
                'code': 'ENTITY_ALREAY_EXISTS',
                'description': '_id already exists',
                'field': '_id'
            });
        }

        const temp = {
            _id, _id,
            name: name,
            effect: effect,
            appearance: appearance,
            min: min,
            max: max
        }

        let newObstacle = await Obstacle.create(temp);

        if (newObstacle) {
            return res.status(201).json({
                'message': 'obstacle created successfully',
                'data': newObstacle
            });
        } else {
            throw new Error('something went worng');
        }
    } catch (error) {
        return res.status(500).json({
            'code': 'SERVER_ERROR',
            'description': 'something went wrong, Please try again'
        });
    }
}

const updateObtsacle = async (req, res, next) => {
    try {
        const obstacleId = req.params.id;

        const {
            _id,
            name,
            appearance,
            effect,
            min,
            max
        } = req.body;

        if (name === undefined || name === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'name is required',
                'field': 'name'
            });
        }

        if (appearance === undefined || appearance === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'appearance is required',
                'field': 'appearance'
            });
        }

        if (effect === undefined || effect === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'effect is required',
                'field': 'effect'
            });
        }

        if (min === undefined || min === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'min is required',
                'field': 'min'
            });
        }

        if (max === undefined || max === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'max is required',
                'field': 'max'
            });
        }
        let IdExists = await Obstacle.findById(obstacleId);

        if (!IdExists) {
            return res.status(404).json({
                'code': 'BAD_REQUEST_ERROR',
                'description': 'No obstacle found in the system'
            });
        }

        const temp = {
            _id, _id,
            name: name,
            effect: effect,
            appearance: appearance,
            min: min,
            max: max
        }

        let updateObtsacle = await Obstacle.findByIdAndUpdate(obstacleId, temp, {
            new: true
        });

        if (updateObtsacle) {
            return res.status(200).json({
                'message': 'obstacle updated successfully',
                'data': updateObtsacle
            });
        } else {
            throw new Error('something went worng');
        }
    } catch (error) {

        return res.status(500).json({
            'code': 'SERVER_ERROR',
            'description': 'something went wrong, Please try again'
        });
    }
}

const deleteObstacle = async (req, res, next) => {
    try {
        let obstacle = await Obstacle.findByIdAndRemove(req.params.id);
        if (obstacle) {
            return res.status(204).json({
                'message': `obstacle with id ${req.params.id} deleted successfully`
            });
        }

        return res.status(404).json({
            'code': 'BAD_REQUEST_ERROR',
            'description': 'No obstacles found in the system'
        });

    } catch (error) {

        return res.status(500).json({
            'code': 'SERVER_ERROR',
            'description': 'something went wrong, Please try again'
        });
    }
}

module.exports = {
    getObstacle: getObstacle,
    getObstacleId: getObstacleId,
    createObstacle: createObstacle,
    updateObtsacle: updateObtsacle,
    deleteObstacle: deleteObstacle
}