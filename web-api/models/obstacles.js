var mongoose = require('mongoose');

var obstacles = mongoose.Schema({
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
});

module.exports = mongoose.model('obstacle', obstacles);