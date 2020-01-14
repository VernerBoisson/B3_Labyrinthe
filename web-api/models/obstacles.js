const mongoose = require('mongoose')
var Schema = mongoose.Schema

const obstacleSchema = mongoose.Schema({
   rowid: String,
   is_crossable: {
      type: Boolean,
      required: true,
   },
   name: {
      type: String,
      required: true
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
   }
}, {collection: 'obstacles'})

var obstacle = mongoose.model('obstacle', obstacleSchema);
/*var mongo = mongoose.connect('mongodb://localhost:27017/mazerunner', {
  useNewUrlParser: true,
  useUnifiedTopology: true
})*

var db = mongoose.connection

db.on('error', console.error.bind(console, 'MongoDB connection error:'))
*/
module.exports = {

  get: async(obstacleId) => {
    var result = obstacle.findOne({"rowid": obstacleId})
    return await result;
  },


  getAll: async() => {
    return result = obstacle.find({})
  },

  insert: async(params) =>{
    //var newid = require('uuid').v4()
    console.log(params)
    var newObstacle = new obstacle({
     // _id: new mongoose.Types.ObjectId(),
      rowid: newid,
      name: params.name,
      is_crossable: params.is_crossable,
      effect: params.effect,
      appearance: params.appearance,
      min: params.min,
      max: params.max,
    });
    newObstacle.save()
    .then(result => {
      console.log(result);
    })
    .catch(err => {
      console.log(err);
    });
  },

  update: async(obstacleId, params) => {
    const upObstacle = await obstacle.findOne({rowid: obstacleId});
    upObstacle.name = params.name;
    upObstacle.is_crossable = params.is_crossable;
    upObstacle.effect = params.effect;
    upObstacle.appearance = params.appearance;
    upObstacle.min = params.min;
    upObstacle.max = params.max;
    await upObstacle.save();
  },
  
  remove: async(obstacleId) => {
    console.log(obstacleId)
    await obstacle.deleteOne({rowid: obstacleId});
  }

}