/*
*Node Server uses express and node.js to create a test server 
*for the league manager application.
*
*
*
*/
var bodyParser = require('body-parser');
var express = require('express');
var app = express();
var path = require('path');
    //Set port number for http connection
app.set("port",3246);


app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json());

//Data of a player
var listOfLeagues = {
    listOfLeagues:['Monday', 'Tuesday', 'Wednesday']
};
//League Data
var league = {
    "leagueName":""
}
var team
var inputTestData = {
    "LeagueName":"",
    "TeamName":"",
    "ScoreA":"",
    "ScoreB":""
}
var inputHistory = {
    History:[]
};

app.get('/', function(request, response)
	{
	    response.sendFile(path.join(__dirname +'/home.html'));
//	    response.writeHead(200, {'Content-Type': 'text/html'});
//	    response.write('<!DOCTYPE html><head><title>Duluth Curling Club</title></head></body>');
//	    response.write('<H1 align="center">Duluth Curling Club</H1>');
//	    response.write('<a href="localhost:3246/Leagues">Leagues </a>');
  //      response.write('<a href="localhost:3246/Teams">Teams </a>');		   
//	    response.write('</body></html>');
	   
//	    response.end();
	    
	    console.log('Recieved Dashboard request!');
	});
app.get('/league.html', function(request, response)
	{
		response.sendFile(path.join(__dirname +'/league.html'));
		console.log('test for second page');
		//response.json(inputHistory);
		console.log('GET REQUEST: Test Server with JSON');
	});

app.post('/leagues.html', function(req, res)
	 {
	     if(!req.body) return response.sendStatus(400);

	     inputTestData.LeagueName= req.body.LeagueName;
	     inputTestData.TeamName=req.body.TeamName;
	     inputTestData.ScoreA=req.body.ScoreA;
	     inputTestData.ScoreB=req.body.ScoreB;
	    

	     inputHistory.History.push(inputTestData);//CHECK FOR ERROR
	     console.log('Match Input Posted'); 

	     var statusMessage = {'status':"OK"
				 };
	     res.json(inputTestData.LeagueName);
	 });
app.use(function(req, res, next){
    res.status(404).send('Sorry cant find that!');
});

app.use(function(req, res, next){
    console.eror(err.stack);
    res.status(500).send('Something broke!');
});

app.listen(app.get("port"), function() {
    console.log("League Manager Testing Server Ready on port: ", app.get("port"))});


