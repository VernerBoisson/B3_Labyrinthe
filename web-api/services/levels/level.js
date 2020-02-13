const express = require('express');
const Level = require('../../models/level');

const getLevel = async (req, res, next) => {
    try {

        let levels = await Level.find({});

        if (levels.length > 0) {
            return res.status(200).json({
                'data': levels
            });
        }

        return res.status(404).json({
            'code': 'BAD_REQUEST_ERROR',
            'description': 'No levels found in the system'
        });
    } catch (error) {
        return res.status(500).json({
            'code': 'SERVER_ERROR',
            'description': 'something went wrong, Please try again'
        });
    }
}

const getLevelById = async (req, res, next) => {
    try {
        let level = await Level.findById(req.params.id);
        if (level) {
            return res.status(200).json({
                'message': `level with id ${req.params.id} fetched successfully`,
                'data': level
            });
        }

        return res.status(404).json({
            'code': 'BAD_REQUEST_ERROR',
            'description': 'No levels found in the system'
        });

    } catch (error) {

        return res.status(500).json({
            'code': 'SERVER_ERROR',
            'description': 'something went wrong, Please try again'
        });
    }
}

const createLevel = async (req, res, next) => {
    try {
        const {
            name,
            created_at,
            updated_at,
            composition:{
                walls,
                obstacles:{
                    traps,
                    mud,
                    start,
                    finish,
                }
            }
        } = req.body;

        if (name === undefined || name === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'name is required',
                'field': 'name'
            });
        }

        
        if (created_at === undefined || created_at === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'created_at is required',
                'field': 'created_at'
            });
        }

        if (updated_at === undefined || updated_at === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'updated_at is required',
                'field': 'updated_at'
            });
        }

        if (walls === undefined || walls === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'walls is required',
                'field': 'walls'
            });
        }
        
        if (traps === undefined || traps === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'traps is required',
                'field': 'traps'
            });
        }
        
        if (mud === undefined || mud === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'mud is required',
                'field': 'mud'
            });
        }

        if (start === undefined || start === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'start is required',
                'field': 'start'
            });
        }

        if (finish === undefined || finish === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'finish is required',
                'field': 'finish'
            });
        }

        let IdExists = await Level.findOne({
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
            name: name,
            created_at: created_at,
            updated_at: updated_at,
            composition:{
                walls: walls,
                obstacles:{
                    traps: traps,
                    mud: mud,
                    start: start,
                    finish: finish,
                }
            }
        }

        let newLevel = await Level.create(temp);

        if (newLevel) {
            return res.status(201).json({
                'message': 'level created successfully',
                'data': newLevel
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

const updateLevel = async (req, res, next) => {
    try {
        const levelId = req.params.id;

        const {
            name,
            created_at,
            updated_at,
            composition:{
                walls,
                obstacles:{
                    traps,
                    mud,
                    start,
                    finish,
                }
            }
        } = req.body;

        if (name === undefined || name === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'name is required',
                'field': 'name'
            });
        }

        if (created_at === undefined || created_at === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'created_at is required',
                'field': 'created_at'
            });
        }

        if (updated_at === undefined || updated_at === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'updated_at is required',
                'field': 'updated_at'
            });
        }

        if (walls === undefined || walls === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'walls is required',
                'field': 'walls'
            });
        }
        
        if (traps === undefined || traps === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'traps is required',
                'field': 'traps'
            });
        }
        
        if (mud === undefined || mud === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'mud is required',
                'field': 'mud'
            });
        }

        if (start === undefined || start === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'start is required',
                'field': 'start'
            });
        }

        if (finish === undefined || finish === '') {
            return res.status(422).json({
                'code': 'REQUIRED_FIELD_MISSING',
                'description': 'finish is required',
                'field': 'finish'
            });
        }

        let IdExists = await Level.findById(levelId);

        if (!IdExists) {
            return res.status(404).json({
                'code': 'BAD_REQUEST_ERROR',
                'description': 'No level found in the system'
            });
        }

        const temp = {
            name: name,
            created_at: created_at,
            updated_at: updated_at,
            composition:{
                walls: walls,
                obstacles:{
                    traps: traps,
                    mud: mud,
                    start: start,
                    finish: finish,
                }
            }
        }

        let updateLevel = await Level.findByIdAndUpdate(levelId, temp, {
            new: true
        });

        if (updateLevel) {
            return res.status(200).json({
                'message': 'level updated successfully',
                'data': updateLevel
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

const deleteLevel = async (req, res, next) => {
    try {
        let level = await Level.findByIdAndRemove(req.params.id);
        if (level) {
            return res.status(204).json({
                'message': `level with id ${req.params.id} deleted successfully`
            });
        }

        return res.status(404).json({
            'code': 'BAD_REQUEST_ERROR',
            'description': 'No levels found in the system'
        });

    } catch (error) {

        return res.status(500).json({
            'code': 'SERVER_ERROR',
            'description': 'something went wrong, Please try again'
        });
    }
}

module.exports = {
    getLevel: getLevel,
    getLevelById: getLevelById,
    createLevel: createLevel,
    updateLevel: updateLevel,
    deleteLevel: deleteLevel
}