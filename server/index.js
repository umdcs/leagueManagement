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
//list of leagues pulled from database.
var listOfLeagues={
    listOfLeagues:[{'LeagueName':'Sunday 3:30PM FAF'},{'LeagueName':'Sunday 5PM Open'},{'LeagueName':'Sunday 7PM Open'},{'LeagueName':'Monday 5PM Open'},{'LeagueName':'Monday 7PM Mixed'},{'LeagueName':'Tuesday 6PM Mens'},{'LeagueName':'Tuesday 8PM Open'},{'LeagueName':'Wednesday 4PM 2v2'},{'LeagueName':'Wednesday 5PM Mixed'},{'LeagueName':'Wednesday 7PM Womens'},{'LeagueName':'Thursday 4PM Open'},{'LeagueName':'Thursday 6PM Open'},{'LeagueName':'Thursday 8PM'},{'LeagueName':'Friday 5:30PM Open'}]
}
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
    History:[{}]
};
var leagueThree = {
    History:[{}]
};
var leagueFour = {
    History:[{}]
};
var leagueFive = {
    History:[{}]
};
var leagueSix = {
    History:[{}]
};
var leagueSeven = {
    History:[{}]
};
var leagueEight = {
    History:[{}]
};
var leagueNine = {
    History:[{}]
};
var leagueTen = {
    History:[{}]
};
var leagueEleven = {
    History:[{}]
};
var leagueTwelve = {
    History:[{}]
};
var leagueThirteen = {
    History:[{}]
};
var leagueFourteen = {
    History:[{}]
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

app.get('/leagueOne', function(request, response)
{
    response.json(leagueOne);
    console.log('List of Leagues sent');
});


 
app.get('/getLeagues', function(request, response){
  console.log('getLeagues accessed');
  // var returnArray = {
  //   History : [{}]
  // }
  // var basicLeagueData = {
  //   'LeagueName' : '',
  //   TeamNameArray : ['']
  // };
  // if(inputHistory.History.length){
  //   for(var i = 0;i < inputHistory.History.length; i++){
  //     basicLeagueData.LeagueName = inputHistory.History[i].leagueName;
  //     if(inputHistory.History[i].teams.length){
  //       for(var j = 0;j < inputHistory.History[i].teams.length; j++){
  //         basicLeagueData.TeamNameArray.push(inputHistory.History[i].teams[j].teamName);
  //       };
  //     };
  //     returnArray.History.push(basicLeagueData);
  //   };
  //
  //   response.json(JSON.stringify(returnArray));
  // };
  response.status(200).send(JSON.stringify(inputHistory.History));
  console.log('getLeagues success');
});

app.get('/Leagues', function(request, response)
	{
	     response.sendFile(path.join(__dirname +'/league.html'));

	    console.log('GET REQUEST: Test Server with JSON');
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

	     leagueOne = input;
switch(name) {
    case "Sunday 3:30PM FAF":
        var leagueAlreadyInput = false;
       
        //if(leagueOne.leagueName == input.leagueName){

            leagueOne = input;
	
leagueAlreadyInput = true;
          break;
       // }
      
    if(!leagueAlreadyInput){
	 console.log(input);
        leagueOne = input;//CHECK FOR ERROR
      }
        break;
    case "Sunday 5PM Open":
        var leagueAlreadyInput = false;
      for(var i = 0; i < leagueTwo.History.length; i++) {
        if(leagueTwo.History[i].leagueName == input.leagueName){
          leagueTwo.History[i] = input;

leagueAlreadyInput = true;
          break;
        }
      }
      if(!leagueAlreadyInput){
        leagueTwo.History.push(input);//CHECK FOR ERROR
      }
        break;
 case "Sunday 7PM Open":
        var leagueAlreadyInput = false;
      for(var i = 0; i < leagueThree.History.length; i++) {
        if(leagueThree.History[i].leagueName == input.leagueName){
          leagueThree.History[i] = input;

leagueAlreadyInput = true;
          break;
        }
      }
      if(!leagueAlreadyInput){
        leagueThree.History.push(input);//CHECK FOR ERROR
      }
        break;
 case "Monday 5PM Open":
        var leagueAlreadyInput = false;
      for(var i = 0; i < leagueFour.History.length; i++) {
        if(leagueFour.History[i].leagueName == input.leagueName){
          leagueFour.History[i] = input;

leagueAlreadyInput = true;
          break;
        }
      }
      if(!leagueAlreadyInput){
        leagueFour.History.push(input);//CHECK FOR ERROR
      }
        break;
 case "Monday 7PM Mixed":
        var leagueAlreadyInput = false;
      for(var i = 0; i < leagueFive.History.length; i++) {
        if(leagueFive.History[i].leagueName == input.leagueName){
          leagueFive.History[i] = input;

leagueAlreadyInput = true;
          break;
        }
      }
      if(!leagueAlreadyInput){
        leagueFive.History.push(input);//CHECK FOR ERROR
      }
        break;
 case "Tuesday 6PM Mens":
        var leagueAlreadyInput = false;
      for(var i = 0; i < leagueSix.History.length; i++) {
        if(leagueSix.History[i].leagueName == input.leagueName){
          leagueSix.History[i] = input;

leagueAlreadyInput = true;
          break;
        }
      }
      if(!leagueAlreadyInput){
        leagueSix.History.push(input);//CHECK FOR ERROR
      }
        break;
 case "Tuesday 8PM Open":
        var leagueAlreadyInput = false;
      for(var i = 0; i < leagueSeven.History.length; i++) {
        if(leagueSeven.History[i].leagueName == input.leagueName){
          leagueSeven.History[i] = input;

leagueAlreadyInput = true;
          break;
        }
      }
      if(!leagueAlreadyInput){
        leagueSeven.History.push(input);//CHECK FOR ERROR
      }
        break;
 case "Wednesday 4PM 2v2":
        var leagueAlreadyInput = false;
      for(var i = 0; i < leagueEight.History.length; i++) {
        if(leagueEight.History[i].leagueName == input.leagueName){
          leagueEight.History[i] = input;

leagueAlreadyInput = true;
          break;
        }
      }
      if(!leagueAlreadyInput){
        leagueEight.History.push(input);//CHECK FOR ERROR
      }
        break;
 case "Wednesday 5PM Mixed":
        var leagueAlreadyInput = false;
      for(var i = 0; i < leagueNine.History.length; i++) {
        if(leagueNine.History[i].leagueName == input.leagueName){
          leagueNine.History[i] = input;

leagueAlreadyInput = true;
          break;
        }
      }
      if(!leagueAlreadyInput){
        leagueNine.History.push(input);//CHECK FOR ERROR
      }
        break;
 case "Wednesday 7PM Womens":
        var leagueAlreadyInput = false;
      for(var i = 0; i < leagueTen.History.length; i++) {
        if(leagueTen.History[i].leagueName == input.leagueName){
          leagueTen.History[i] = input;

leagueAlreadyInput = true;
          break;
        }
      }
      if(!leagueAlreadyInput){
        leagueTen.History.push(input);//CHECK FOR ERROR
      }
        break;
 case "Thursday 4PM Open":
        var leagueAlreadyInput = false;
      for(var i = 0; i < leagueEleven.History.length; i++) {
        if(leagueEleven.History[i].leagueName == input.leagueName){
          leagueEleven.History[i] = input;

leagueAlreadyInput = true;
          break;
        }
      }
      if(!leagueAlreadyInput){
        leagueEleven.History.push(input);//CHECK FOR ERROR
      }
        break;
 case "Thursday 6PM Open":
        var leagueAlreadyInput = false;
      for(var i = 0; i < leagueTwelve.History.length; i++) {
        if(leagueTwelve.History[i].leagueName == input.leagueName){
          leagueTwelve.History[i] = input;

leagueAlreadyInput = true;
          break;
        }
      }
      if(!leagueAlreadyInput){
        leagueTwelve.History.push(input);//CHECK FOR ERROR
      }
        break;
 case "Thursday 8PM":
        var leagueAlreadyInput = false;
      for(var i = 0; i < leagueThirteen.History.length; i++) {
        if(leagueThirteen.History[i].leagueName == input.leagueName){
          leagueThirteen.History[i] = input;

leagueAlreadyInput = true;
          break;
        }
      }
      if(!leagueAlreadyInput){
        leagueThirteen.History.push(input);//CHECK FOR ERROR
      }
        break;
 case "Friday 5:30PM Open":
       var leagueAlreadyInput = false;
      for(var i = 0; i < leagueFourteen.History.length; i++) {
        if(leagueFourteen.History[i].leagueName == input.leagueName){
          leagueFourteen.History[i] = input;

leagueAlreadyInput = true;
          break;
        }
      }
      if(!leagueAlreadyInput){
        leagueFourteen.History.push(input);//CHECK FOR ERROR
      }
        break;
    default:
        break;
}

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
