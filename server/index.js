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
    extended true
}));
app.use(bodyParser.json());

//Data of a player
var player = {
    "Name":""
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
//Single matchup score to be inputed into server.
var matchScore = {
    "ScoreA":0,
    "ScoreB":0
};
//List of Matchup Scores
var matchupScores = {
    matchupScoresList:[]
};
//Data of a league
var league = {
    leagueStandings:[],
    teams:[],
    fullSchedule:[]    
};
//List of League Standings
var leagueStandings = {
    leagueStandingsList:[]
};

app.get('/', function(request, response)
	{
	    response.writeHead(200, {'Content-Type': 'text/html'});
	    response.write('<!DOCTYPE html><head><title>League Manager</title></head></body>');
	    response.write('<H1>League Test Server</H1>');
	    response.write('<p>Report: Client sends all testing data to display on this server. This information is to display on the main page; information includes leagues, teams, rosters and more.</p>');
	    response.write('</body></html>');
	    response.end();
	    console.log('Recieved Dashboard request!');
	});
app.get('/leagues', function(request, response)
	{
	    response.json(league);
	    
	    console.log('GET REQUEST: Test Server with JSON');

	});

app.use(function(req, res, next){
    res.status(404).send('Sorry cant find that!');
});

app.use(function(req, res, next){
    console.eror(err.stack);
    res.status(500).send('Something broke!');
});

app.listem(app.get("port"), funtion() {
    console.log("League Manager Testing Server Ready on port: ", app.get("port"))});


