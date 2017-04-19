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
var networkIORef = require= socketio.listen(httpServer);



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
    History:[]
};

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

app.get('/listLeagues', function(request, response)
{
    response.json(listOfLeagues);
    console.log('List of Leagues sent');
});
app.get('/Leagues', function(request, response)
	{
	    response.json(inputHistory);
	    console.log('GET REQUEST: Test Server with JSON');
	});
app.post('/Leagues', function(req, res)
	 {
	     if(!req.body) return response.sendStatus(400);
       //Parsing JSON string into javascript object
       var jsonString = req.body;
       var inputJson = JSON.stringify(jsonString);
       var input = JSON.parse(inputJson);

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
