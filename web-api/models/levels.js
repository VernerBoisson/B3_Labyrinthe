const mongoose = require('mongoose')
var Schema = mongoose.Schema

const levelSchema = mongoose.Schema({
  _id: mongoose.Schema.ObjectId,
  rowid: String,
  name: String,
  created_at: Date,
  updated_at: Date,
  composition: {
   walls: Array,
   obstacles: {
      traps: Array,
      mud: Array,
      start: Array,
      finish: Array
   }
}
}, {collection: 'levels'})

var level = mongoose.model('level', levelSchema);
/*var mongo = mongoose.connect('mongodb://localhost:27017/mazerunner', {
  useNewUrlParser: true,
  useUnifiedTopology: true
})

var db = mongoose.connection

db.on('error', console.error.bind(console, 'MongoDB connection error:'))
*/
module.exports = {

  get: async(levelId) => {
    var result = level.findOne({"rowid": levelId})
    return await result;
  },


  getAll: async() => {
    return result = level.find({})
  },

  insert: async(params) =>{
    var newid = require('uuid').v4()
    console.log(params)
    var newlevel = new level({
      _id: new mongoose.Types.ObjectId(),
      rowid: newid,
      name: params.name,
      created_at: params.created_at,
      updated_at: params.updated_at,
      composition: params.composition,
      walls: params.walls,
      obstacles: params.obstacles,
      traps: params.traps,
      mud: params.mud,
      start: params.start,
      finish: params.finish,
    });
    newlevel.save()
    .then(result => {
      console.log(result);
    })
    .catch(err => {
      console.log(err);
    });
  },

  update: async(levelId, params) => {
    const upLevel = await level.findOne({rowid: levelId});
    upLevel.name = params.name;
    upLevel.created_at = params.created_at;
    upLevel.updated_at = params.updated_at;
    upLevel.composition = params.composition;
    upLevel.walls = params.walls;
    upLevel.obstacles = params.obstacles;
    upLevel.traps = params.traps;
    upLevel.mud = params.mud;
    upLevel.start = params.start;
    upLevel.finish = params.finish;

    await upLevel.save();
  },
  
  remove: async(levelId) => {
    console.log(levelId)
    await level.deleteOne({rowid: levelId});
  }

}