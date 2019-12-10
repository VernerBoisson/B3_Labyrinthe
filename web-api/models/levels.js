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

/*module.exports = mongoose.model('level', levels);*/
const level = mongoose.model('level', levels)

/* insert uniquement name et creator */
level1 = new level({'name': '11111', 'creator': '22222'})

level1.save()
.then(res => console.log(res))
.catch(error => console.log(err))