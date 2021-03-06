const mongoose = require('mongoose');
const config = require('./config');
const autoIncrement = require('mongoose-auto-increment')
const mongoDB = config.database;

mongoose.connect(mongoDB, { useNewUrlParser: true, useUnifiedTopology: true, useFindAndModify:false, useCreateIndex: true });
const db = mongoose.connection;
db.on('error', console.error.bind(console, 'MongoDB connection error:'));

const schema = new mongoose.Schema({
    id: {type: Number, required: true, unique: true},
    title: {type:String},
    author: {type:String},
    maze: [[{type:String}]],
    createdAt: {type: Date, default: new Date()},
    timer: {type: Number},
    movement: {type: Number},
})

autoIncrement.initialize(mongoose.connection);
schema.plugin(autoIncrement.plugin, {
    model: 'Maze',
    field: 'id',
    startAt: 1,
});

const maze = mongoose.model('Maze', schema);

module.exports = {
    getAll: () =>{
        return maze.find();
    },

    get: (mazeId) => {
        return maze.findOne({id:mazeId})
    },

    insert: (params) => {
        const addMaze = new maze({
            title: params.title ? params.title : "No Title",
            author: params.author ? params.author : "Anonymous",
            maze: params.maze,
            timer: 0,
            movement: 0,
        })
        return addMaze.save();
    },

    update: (mazeId, params) => {
        const filter = { id: mazeId };
        let updateParams = {};
        if(params.title && params.author && params.maze){
            updateParams = {
                title: params.title,
                author: params.author,
                maze: params.maze,
                
            }
        }else if(params.timer && params.movement){
            updateParams = {
                timer: params.timer,
                movement: params.movement,
            }
        }
        return maze.findOneAndUpdate(filter,updateParams)
    },

    remove: (mazeId) => {
        return maze.deleteOne({id: mazeId});
    },

}