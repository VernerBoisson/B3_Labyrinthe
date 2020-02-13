let mongoose = require('mongoose');
let Schema = mongoose.Schema;

var Obstacle = new Schema({
    rowid: {
        type: Number,
        required: true,
        unique: true
    },
    name: {
        type: String,
        required: [true, 'name is required'],
    },
    effect: {
        type: Number,
        required: true
    },
    appearance: {
        type: Date,
        default: Date.now,
        required: true
    },
    min: {
        type: Number,
        required: true
    },
    max: {
        type: Number,
        required: true
    },
}, {
    timestamps: true
});

module.exports = mongoose.model('Obstacles', Obstacle);