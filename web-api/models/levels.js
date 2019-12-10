var mongoose = require('mongoose');

var levels = mongoose.Schema({
   name: {
      type: String,
      required: true,
   },
   creator: {
      type: String,
      required: true
   },
   created_at: {
      type: Date,
      default: Date.now,
      required: true
   },
   updated_at: {
      type: Date,
      default: Date.now,
      required: true
   },
   composition: {
      walls: Array,
      obstacles: {
         traps: Array,
         mud: Array,
         start: Array,
         finish: Array
      }
   }
});

module.exports = mongoose.model('level', levels);