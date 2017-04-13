/*
*Node Server uses express and node.js to create a test server 
*for the league manager application.
*
*
*
*/
var bodyParser = require('body-parser');
var http = require('http');
var express = require('express');
var app = express();
var socketio = require('socket.io');
var httpServer = http.createServer(app);
var path = require('path');

var io = require('socket.io')(http);
var networkIORef = socketio.listen(httpServer);


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
var inputHistory = {
    History:[]
};

app.get('/', function(request, response)
	{
	    response.sendFile(path.join(__dirname +'/home.html'));
	    networkIORef.emit('livefeed',': Received / GET request ');
	    console.log('Recieved Dashboard request!');
	});

networkIORef.on('connection', function(socket) {
	console.log('a user has connected.');
	socket.on('livefeed', function(msg) {
	});
	socket.on('disconnect', function() {
		console.log('a user has disconnected.');
	});
});


io.on('connection', function(socket){
  socket.on('chat message', function(msg){
    console.log('message: ' + msg);
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
	     console.log('Match Input Posted'); 

	     var statusMessage = {'status':"OK"
				 };
	     res.json(input);

var feed = '';
	 for(var elemName in req.body) {
	feed = feed + "[" + elemName + ": " + req.body[elemName] + "] ";
	 }
	networkIORef.emit('livefeed', 'Received POST request ' + feed);
	updateStats();



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
