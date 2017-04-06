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
var player = {
    "Name":""
};
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
	   
//	    response.end();
	    
	    console.log('Recieved Dashboard request!');
	});
app.get('/Leagues', function(request, response)
	{
	    response.json(inputHistory);
	    
	    console.log('GET REQUEST: Test Server with JSON');

	});
app.post('/Leagues', function(req, res)
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
