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
//Data on a single team.
var singleTeam ={
    "RecordWins":"",
    "RecordLoss":"",
    "RecordTie":"",
    playersList:[],
    teamSchedule:[],
    "skip":"",
    "PhoneNumber":"",
    "currentMatchup":""
   
};
//List of teams
var teamList = {
    teamList:[]
};
//List of different leagues in a sport.
var leagueListData = {
    leagueList:[]
};

//List of Matchup Scores
var matchupScores = {
    matchupScoresList:[]
};
//Data of a league
var league = {
    "leagueName":"",
    leagueStandings:[],
    teams:[]    
};
//List of League Standings
var leagueStandings = {
    leagueStandingsList:[]
};

app.get('/', function(request, response)
	{
	    response.writeHead(200, {'Content-Type': 'text/html'});
	    response.write('<!DOCTYPE html><head><title>Duluth Curling Club</title></head></body>');
	    response.write('<H1>Duluth Curling Club</H1>');
	    response.write('<p>Report: Client sends all testing data to display on this server. This information is to display on the main page; information includes leagues, teams, rosters and more.</p>');
	    response.write('</body></html>');
	   
	    response.end();
	    
	    console.log('Recieved Dashboard request!');
	});
app.get('/dash', function(request, response)
	{
	    response.json(inputHistory);
	    
	    console.log('GET REQUEST: Test Server with JSON');

	});
app.post('/dash', function(req, res)
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


