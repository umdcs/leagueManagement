/*
*Node Server uses express and node.js to create a test server
*for the league manager application.
*
*
*
*/
//General requirements
var bodyParser = require('body-parser');
var express = require('express');
var app = express();
var path = require('path');

//Socket.io Requirements
var socketio = require('socket.io');
var http = require('http');
var httpServer = http.createServer(app);
var networkIORef = socketio.listen(httpServer);

//Mongo Requirements
//var mongodb =require('./mongoDBFunctions.js');


//Set port number for http connection
app.set("port",3246);
app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json());
//httpServer.listen(app.get("port"), function () {
  //  console.log('node app listening on port: ', app.get("port"));
//});

var allLeagues = {leagueOne,leagueTwo,leagueThree,leagueFour,leagueFive,leagueSix,leagueSeven,leagueEight,leagueNine,leagueTen,leagueEleven,leagueTwelve,leagueThirteen,leagueFourteen};

var inputHistory = {
    History:[{}]
};
var leagueOne = {
    "leagueName":"Sunday 3:30PM FAF",
    "teamName":"",
    "wins":0,
    "losses":0,
    "ties":0
};
var leagueTwo = {
    "leagueName":"Sunday 5PM Open",
    "teamName":"",
    "wins":0,
    "losses":0,
    "ties":0
};
var leagueThree = {
    "leagueName":"Sunday 7PM Open",
    "teamName":"",
    "wins":0,
    "losses":0,
    "ties":0
};
var leagueFour = {
 "leagueName":"Monday 5PM Open",
    "teamName":"",
    "wins":0,
    "losses":0,
    "ties":0
};
var leagueFive = {
 "leagueName":"Monday 7PM Mixed",
    "teamName":"",
    "wins":0,
    "losses":0,
    "ties":0
};
var leagueSix = {
 "leagueName":"Tuesday 6PM Mens",
    "teamName":"",
    "wins":0,
    "losses":0,
    "ties":0
};
var leagueSeven = {
 "leagueName":"Tuesday 8PM Open",
    "teamName":"",
    "wins":0,
    "losses":0,
    "ties":0
};
var leagueEight = {
 "leagueName":"Wednesday 4PM 2v2",
    "teamName":"",
    "wins":0,
    "losses":0,
    "ties":0
};
var leagueNine = {
 "leagueName":"Wednesday 5PM Mixed",
    "teamName":"",
    "wins":0,
    "losses":0,
    "ties":0
};
var leagueTen = {
 "leagueName":"Wednesday 7PM Womens ",
    "teamName":"",
    "wins":0,
    "losses":0,
    "ties":0
};
var leagueEleven = {
 "leagueName":"Thursday 4PM Open",
    "teamName":"",
    "wins":0,
    "losses":0,
    "ties":0
};
var leagueTwelve = {
 "leagueName":"Thursday 6PM Open",
    "teamName":"",
    "wins":0,
    "losses":0,
    "ties":0
};
var leagueThirteen = {
 "leagueName":"Thursday 8PM",
    "teamName":"",
    "wins":0,
    "losses":0,
    "ties":0
};
var leagueFourteen = {
 "leagueName":"Friday 5:30PM Open",
    "teamName":"",
    "wins":0,
    "losses":0,
    "ties":0
};

var pathto = require("path");
app.use(express.static(pathto.join(__dirname, '/resources')));

app.get('/', function(request, response)

	{
	    response.sendFile(path.join(__dirname +'/home.html'));

	    console.log('Recieved Dashboard request!');
	});

networkIORef.on('connection', function(socket) {
    console.log('user connected');

    socket.on('log message', function(msg) {
	networkIORef.emit('log message', msg);
    });

    socket.on('disconnect', function(){
	console.log('user now disconnected');
    });
});

app.get('/allLeagues', function(request, response)
{
    response.json(allLeagues);
    console.log('List of Leagues sent');
});


app.get('/getLeagues', function(request, response){

  response.status(200).send(JSON.stringify(inputHistory.History));
  console.log('getLeagues success');
});
app.get('/Leagues', function(request, response)
	{
	     response.sendFile(path.join(__dirname +'/league.html'));
	});
app.get('/history', function(request, response)
	{
	    response.send(inputHistory);
	    console.log('GET REQUEST: History');
	});
app.post('/Leagues', function(req, res)
	 {
	     if(!req.body) return response.sendStatus(400);
       //Parsing JSON string into javascript object
       var jsonString = req.body;
       var inputJson = JSON.stringify(jsonString);
       var input = JSON.parse(inputJson);
	var name = req.body.leagueName;
//	     leagueOne = input;
	     if(leagueOne.leagueName == input.leagueName)
		 allLeagues.leagueOne = input;
	      else if(leagueTwo.leagueName == input.leagueName)
		  allLeagues.leagueTwo = input;
	      else if(leagueThree.leagueName == input.leagueName)
		  allLeagues.leagueThree = input;
	      else if(leagueFour.leagueName == input.leagueName)
		  allLeagues.leagueFour = input;
	      else if(leagueFive.leagueName == input.leagueName)
		  allLeagues.leagueFive = input;
	      else if (leagueSix.leagueName == input.leagueName)
		  allLeagues.leagueSix = input;
	      else if(leagueSeven.leagueName == input.leagueName)
		  allLeagues.leagueSeven = input;
	      else if(leagueEight.leagueName == input.leagueName)
		  allLeagues.leagueEight = input;
	      else if(leagueNine.leagueName == input.leagueName)
		  allLeagues.leagueNine = input;
	      else if(leagueTen.leagueName == input.leagueName)
		  allLeagues.leagueTen = input;
	      else if(leagueEleven.leagueName == input.leagueName)
		  allLeagues.leagueEleven = input;
	     else if(leagueTwelve.leagueName == input.leagueName)
		 allLeagues.leagueTwelve = input;
	      else if(leagueThirteen.leagueName == input.leagueName)
		  allLeagues.leagueThirteen = input;
	      else
		  allLeagues.leagueFourteen = input;

      //Testing
      var leagueAlreadyInput = false;
      for(var i = 0; i < inputHistory.History.length; i++) {
        if(inputHistory.History[i].leagueName == input.leagueName){
          inputHistory.History[i] = input;

leagueAlreadyInput = true;
          break;
        }
      }
      if(!leagueAlreadyInput){
        inputHistory.History.push(input);//CHECK FOR ERROR
      }

	     var statusMessage = {'status':"OK"	};

             var timestamp = new Date().valueOf();
	     var logstr = '';
	     for(var elemName in req.body) {
		 if(elemName = req.body.LeagueName)
		     logstr = logstr + "[" + elemName + ": " + req.body[elemName] + "] ";
		 if(elemName = req.body.TeamName)
	logstr = logstr + "[" + elemName + ": " + req.body[elemName] + "] ";
    }
networkIORef.emit('log message', timestamp + ': Received /POST' + logstr);
	     //res.json(input);
 console.log('Match Input Posted');
	     res.status(200).send('OK');
	 });


//Other routes
app.use(function(req, res, next){
    res.status(404).send('Sorry cant find that!');
});

app.use(function(req, res, next){
    console.eror(err.stack);
    res.status(500).send('Something broke!');
});

//app.listen(app.get("port"), function() {
  //  console.log("League Manager Testing Server Ready on port: ", app.get("port//"))});
httpServer.listen(app.get("port"), function () {
    console.log('node app listening on port: ', app.get("port"));
});
