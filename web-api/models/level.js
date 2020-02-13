let mongoose = require('mongoose');
let Schema = mongoose.Schema;

var Level = new Schema({
    name: {
        type: String,
        required : [ true, 'name is required'],
    },
    created_at: {
        type: String,
    },
    updated_at: {
        type: String,
    },
    composition: {
        walls: {
            type: Array,
        },
        obstacles: {
            traps:{
                type: Array,
            },
            mud:{
                type: Array,
            },
            start:{
                type: Array,
            },
            finish:{
                type: Array,
            }
        }
    },
}, {
    timestamps: true
});

module.exports = mongoose.model('Levels', Level);