/*
*Node Server uses express and node.js to create a test server 
*for the league manager application.
*
*
*
*/
var express = require('express');
var app = express();

//Set port number for http connection
app.set("port",3246);


app.listem(app.get("port"), funtion() {
    console.log("League Manager Testing Server Ready on port: ", app.get("port"))});


