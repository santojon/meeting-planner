var express = require('express');
var Cloudant = require('cloudant');
var app = express();

var me = 'thiagoald'
var password = 'kRB~fWU&{ZD(7]J'

app.get('/', function (req, res) {
  console.log(req);
  // Cloudant({account:me, password:password}, function(er, cloudant) {
  //   if (er)
  //     return console.log('Error connecting to Cloudant account %s: %s', me, er.message)

  //   // Clean up the database we created previously.
  //   cloudant.db.destroy('alice', function() {
  //     // Create a new database.
  //     cloudant.db.create('alice', function() {
  //       // specify the database we are going to use
  //       var alice = cloudant.use('alice')
  //       // and insert a document in it
  //       alice.insert({ crazy: true }, 'rabbit', function(err, body, header) {
  //         if (err)
  //           return console.log('[alice.insert] ', err.message)

  //         console.log('you have inserted the rabbit.')
  //         console.log(body)
  //       })
  //     })
  //   })
  // })
  res.send('E aew Rafael!');
});

app.post('/', function (req, res) {
  console.log(req);
  // Cloudant({account:me, password:password}, function(er, cloudant) {
  //   if (er)
  //     return console.log('Error connecting to Cloudant account %s: %s', me, er.message)

  //   // Clean up the database we created previously.
  //   cloudant.db.destroy('alice', function() {
  //     // Create a new database.
  //     cloudant.db.create('alice', function() {
  //       // specify the database we are going to use
  //       var alice = cloudant.use('alice')
  //       // and insert a document in it
  //       alice.insert({ crazy: true }, 'rabbit', function(err, body, header) {
  //         if (err)
  //           return console.log('[alice.insert] ', err.message)

  //         console.log('you have inserted the rabbit.')
  //         console.log(body)
  //       })
  //     })
  //   })
  // })
  res.send('E aew Rafael!');
});

var server = app.listen(3000, function () {

  var host = server.address().address;
  var port = server.address().port;

  console.log('Example app listening at http://%s:%s', host, port);

});