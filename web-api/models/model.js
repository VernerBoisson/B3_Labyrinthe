const mongoose = require('mongoose')
var url = "mongodb://localhost:27017/";

MongoClient.connect(url, function (err, db) {
     if (err) throw err;
     var dbo = db.db("labyrintheBDD");
     /* Colletion levels */
     db.createCollection('levels', {
          validator: {
               $jsonSchema: {
                    bsonType: 'object',
                    required: ['name', 'creator', 'created_at', 'updated_at', 'composition'],
                    properties: {
                         name: {
                              bsonType: 'string'
                         },
                         creator: {
                              bsonType: 'string'
                         },
                         created_at: {
                              bsonType: 'date'
                         },
                         updated_at: {
                              bsonType: 'date'
                         },
                         composition: {
                              bsonType: 'object',
                              required: ['walls', 'obstacles'],
                              properties: {
                                   walls: {
                                        bsonType: 'array',
                                        items: {
                                             bsonType: 'string'
                                        }
                                   },
                                   obstacles: {
                                        bsonType: 'object',
                                        required: ['traps', 'mud', 'start', 'finish'],
                                        properties: {
                                             traps: {
                                                  bsonType: 'array',
                                                  items: {}
                                             },
                                             mud: {
                                                  bsonType: 'array',
                                                  items: {}
                                             },
                                             start: {
                                                  bsonType: 'array',
                                                  items: {
                                                       bsonType: 'int'
                                                  }
                                             },
                                             finish: {
                                                  bsonType: 'array',
                                                  items: {
                                                       bsonType: 'int'
                                                  }
                                             }
                                        }
                                   }
                              }
                         }
                    }
               }
          }
     });
     /* Colletion Obstacles */
     db.createCollection('obstacles', {
          validator: {
               $jsonSchema: {
                    bsonType: 'object',
                    required: ['is_crossable', 'name', 'effect', 'appearance', 'min', 'max'],
                    properties: {
                         is_crossable: {
                              bsonType: 'bool'
                         },
                         name: {
                              bsonType: 'string'
                         },
                         effect: {
                              bsonType: 'decimal'
                         },
                         appearance: {
                              bsonType: 'string'
                         },
                         min: {
                              bsonType: 'int'
                         },
                         max: {
                              bsonType: 'int'
                         }
                    }
               }
          }
     });
     /* Collection tests */
     db.createCollection('tests', {
          validator: {
               $jsonSchema: {
                    bsonType: 'object',
                    required: ['test_date', 'result', 'levels'],
                    properties: {
                         test_date: {
                              bsonType: 'date'
                         },
                         result: {
                              bsonType: 'object'
                         },
                         levels: {
                              bsonType: 'objectId'
                         }
                    }
               }
          }
     });

});