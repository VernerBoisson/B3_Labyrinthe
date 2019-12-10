var mongoose = require('mongoose');

var tests = mongoose.Schema({
   test_date: {
      type: Date,
      default: Date.now,
      required: true,
   },
   result: {
      type: Object,
      required: true
   },
   level_id: {
      type: mongoose.Schema.ObjectId,
      required: true
   }
});

module.exports = mongoose.model('test', tests);