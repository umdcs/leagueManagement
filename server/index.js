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
// See that file for the details for what is provided.
//var mongodb = require('./mongoDBFunctions.js'); 

//Set port number for http connection
app.set("port",3246);
app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json());

//list of leagues pulled from database.
var listOfLeagues={
    listOfLeagues:[{'LeagueName':'Sunday 3:30PM FAF'},{'LeagueName':'Sunday 5PM Open'},{'LeagueName':'Sunday 7PM Open'},{'LeagueName':'Monday 5PM Open'},{'LeagueName':'Monday 7PM Mixed'},{'LeagueName':'Tuesday 6PM Mens'},{'LeagueName':'Tuesday 8PM Open'},{'LeagueName':'Wednesday 4PM 2v2'},{'LeagueName':'Wednesday 5PM Mixed'},{'LeagueName':'Wednesday 7PM Womens'},{'LeagueName':'Thursday 4PM Open'},{'LeagueName':'Thursday 6PM Open'},{'LeagueName':'Thursday 8PM'},{'LeagueName':'Friday 5:30PM Open'}]
}
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
	   
	    console.log('Recieved Dashboard request!');
	});
app.get('/listLeagues', function(request, response)
{
    response.json(listOfLeagues);
    console.log('List of Leagues sent');
});
app.get('/Leagues', function(request, response)
	{
	    response.json(inputHistory);

//var str = mongodb.printDatabase('documents', function(result) {
//	response.send('<HTML><BODY>' + JSON.stringify(result, null, 2) + '</BODY></HTML>');
	
//});
	    console.log('GET REQUEST: Test Server with JSON');

	});
app.post('/Leagues', function(req, res)
	 {
	     if(!req.body) return response.sendStatus(400);
	     var input =
		 {
		     "LeagueName":"",
		     "TeamName":"",
		     "ScoreA":"",
		     "ScoreB":"" 
		 }
	     input.LeagueName= req.body.LeagueName;
	     input.TeamName=req.body.TeamName;
	     input.ScoreA=req.body.ScoreA;
	     input.ScoreB=req.body.ScoreB;
	     inputHistory.History.push(input);//CHECK FOR ERROR
//	     mongodb.insertScore(req.body.ScoreA, req.body.ScoreB );
	     console.log('Match Input Posted'); 

	     var statusMessage = {'status':"OK"
				 };
	     res.json(input);
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
