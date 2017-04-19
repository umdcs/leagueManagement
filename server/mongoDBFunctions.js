//
// This file, mongoDBFunctions.js is treated like a Node.js module.
//
// 

// mongo wrapper to load up the MongoDB Javascript package
var mongojs = require("mongojs");

// The URL contains the connection to the MongoDB (which can be
// running on a different machine if you so choose). Here, we run it
// locally. Make sure Mongo is installed on your machine with this
// example.
var url = 'mongodb://localhost:27017/data'; //URL: this is for test purposes

var collections = ['documents']; //Array of known collections

var assert = require('assert');

// 
// This is an example of Node.js anonymous function. It is being used
// so that we can have a single connection to the MongoDB from your
// node.js server code and so that you can build up a set of functions
// to operate on the db in more useful ways, like you might if you had
// objects or other functions.
//
// Essentially, this is like a class with the functions below acting
// as member functions.
//

var mongoDBRef = mongojs(url, collections); //creation of the mongo connection
console.log("Connected to Mongo DB - all functions are now active.");

/** ********************************************************************
 * printDatabase - Prints the whole collection, for debugging purposes.
 * @param collectionName - the name of the collection
 * @param callback - need to provide a function to return the data
 */
module.exports.printDatabase = function(collectionName, callback) {
    
    // 
    // Collection look ups with find return a MongoDB 'cursor'. More info can be found here
    // https://docs.mongodb.com/v3.2/reference/glossary/#term-cursor
    // 
    var cursor = mongoDBRef.collection(collectionName).find(function(err, docs) {
	
        if(err || !docs) {
	    console.log("Cannot print database or database is empty\n");
	}
        else {
	    console.log(collectionName, docs);
	    callback(docs);
	}
    });
};


/**
 * findWeek - Finds specific information about a week
 * @param week
 * @param callback
 */
module.exports.findLeagueName = function(leagueId, callback) {

    console.log( 'Searching for leagueId: ' + leagueId );

    // Get the documents collection
    mongoDBRef.collection('documents').find({ LeagueName: Number(leagueId) }).toArray(function(err, docs) {

	// if (!err) {
	console.log("Found the following records");
	console.log(docs);
	callback(docs);
	// } else {
	// onErr(err, callback);
        // }

    });
};

/**
 * insertWeek - funcion that inserts a week record as a JSON object into 
 * the 'documents' collection.
 * If the 'documents' collection doesn't exist, it will be created.
 * @param weekData
 */
module.exports.insertLeague = function(leagueData) {

    mongoDBRef.collection('documents').save({League: leagueData}, function (err, result) {
        if(err || !result) console.log ("Week not saved in database.");
        else console.log("Inserted a week record into the documents collection.");
    });
};
