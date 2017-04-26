# LeagueManager
League Manager application is developed to manage and input scores for the Duluth Curling Club (DCC) in Duluth, MN. Scores should added only by team captains (skips) and no other for a team. League Manager manages by leagues and each team in a league. League info is shown on ukko.d.umn.edu:3246/Leagues.

Purpose:
The purpose of this project was to develop an application that would be used to enter scores  and manage leagues for the Duluth Curling Club in Duluth, MN.

Primary User Stores:
As a team skip (captain), I want to be able to input scores after matches so that the manger does not have to do them manually.
As a player, I want to be able to view scores, matchups, and schedules on the website.
As a player, I want to have a schedule created automatically, and viewable on the website.



Connection to Node Server:
Node needs to be install on your machine.
Server can be on localhost:3246/Leagues or ukko.d.umn.edu:3246/Leagues
"npm install" will need to be applied to the package.json for its individual dependencies. 
To run on ukko or akka, use the screen command in the following procedure.
screen
nodejs index.js
ctrl-a-d (Used to detach from screen)
screen -ls (shows all processes running in background)
screen -r (resumes most recent screen)

Forever is currently not used but is included in the dependency.

The app contains dummy values ofr league data, currently not using data from DCC.

Future Goals:
Prevent teams from entering scores until the scheduled date.
Create a database to store all league data.
More efficiently transfer data back/from app & server.




//TESTING//
Our unit test tests out League, Match and Team classes for functionality. League checks for the ability to add and remove teams from a league. It also tests how the schedule is created and if the scoreboard is being updated correctly. The Team class tests for schedule updates and adding and removing of players in a team. In addition the adding of scores for each team's matches. The match class tests for tests for determining the winner of a match.


Made a simple script that curl -GET all paths from the server, then attempted to post something and read what it posted
***************Script****************
#!/bin/bash
# Testing index.js using a bunch of curl

echo "Starting Testing on the server"
echo
echo "GET on the root page"
echo
curl --header "Content-type: application/json" --request GET ukko.d.umn.edu:3246/
echo
echo "GET on /listLeagues"
echo
curl --header "Content-type: application/json" --request GET ukko.d.umn.edu:3246/listLeagues
echo
echo "\n GET on /Leagues \n"
echo
curl --header "Content-type: application/json" --request GET ukko.d.umn.edu:3246/Leagues
echo
echo "\n ************ POST ************\n"
echo
curl --header "Content-type: application/json" --request POST --data '{"LeagueName" : "AwesomeLeague" , "TeamName" : "Best NA" , "ScoreA" : "7" , "ScoreB" : "arg"}' ukko.d.umn.edu:3246/Leagues
echo
echo "Should see {"LeagueName" : "AwesomeLeague" , "TeamName" : "Best NA" , "ScoreA" : "7" , "ScoreB" : "arg"}"
echo
curl --header "Content-type: application/json" --request GET ukko.d.umn.edu:3246/Leagues

echo
curl --header "Content-type: application/json" --request POST --data '{"LeagueName" : "AwesomeLeague" , "TeamName" : "Worst NA" , "ScoreA" : "890g" , "ScoreB" : "argfds./"}' ukko.d.umn.edu:3246/Leagues

echo
curl --header "Content-type: application/json" --request GET ukko.d.umn.edu:3246/Leagues
echo

*****************Console Output from script*********************
hunter@hunter-X550LA:~/Documents/cs4531/LeagueManager/server$ ./testing
Starting Testing on the server

GET on the root page

<!DOCTYPE html><HTML>
  <head>
    <title>Duluth Curling Club</title>
    <img src="https://www.duluthcurlingclub.org/wp-content/uploads/2015/07/duluth-curling-club.jpg" alt="Duluth Curling Club" align="center">
    <h1 align="center">Duluth Curling Club</h1>
    <ul>
      <li>
	<a name="league"></a>
	<a href="/Leagues">Leagues</a>
      </li>
      <li>
	<a href=About.html>About</a>
      </li>
    </ul>

  </head>
  </HTML>

GET on /listLeagues

Sorry cant find that!
\n GET on /Leagues \n

{"History":[]}
\n ************ POST ************\n

"AwesomeLeague"
Should see {LeagueName : AwesomeLeague , TeamName : Best NA , ScoreA : 7 , ScoreB : arg}

{"History":[{"LeagueName":"AwesomeLeague","TeamName":"Best NA","ScoreA":"7","ScoreB":"arg"}]}hunter@hunter-X550LA:~/Documents/cs4531/LeagueManager/server$ emacs testing
hunter@hunter-X550LA:~/Documents/cs4531/LeagueManager/server$ ./testing
Starting Testing on the server

GET on the root page

<!DOCTYPE html><HTML>
  <head>
    <title>Duluth Curling Club</title>
    <img src="https://www.duluthcurlingclub.org/wp-content/uploads/2015/07/duluth-curling-club.jpg" alt="Duluth Curling Club" align="center">
    <h1 align="center">Duluth Curling Club</h1>
    <ul>
      <li>
	<a name="league"></a>
	<a href="/Leagues">Leagues</a>
      </li>
      <li>
	<a href=About.html>About</a>
      </li>
    </ul>

  </head>
  </HTML>

GET on /listLeagues

Sorry cant find that!
\n GET on /Leagues \n

{"History":[{"LeagueName":"AwesomeLeague","TeamName":"Best NA","ScoreA":"7","ScoreB":"arg"}]}
\n ************ POST ************\n

"AwesomeLeague"
Should see {LeagueName : AwesomeLeague , TeamName : Best NA , ScoreA : 7 , ScoreB : arg}

{"History":[{"LeagueName":"AwesomeLeague","TeamName":"Best NA","ScoreA":"7","ScoreB":"arg"},{"LeagueName":"AwesomeLeague","TeamName":"Best NA","ScoreA":"7","ScoreB":"arg"}]}
"AwesomeLeague"
{"History":[{"LeagueName":"AwesomeLeague","TeamName":"Worst NA","ScoreA":"890g","ScoreB":"argfds./"},{"LeagueName":"AwesomeLeague","TeamName":"Worst NA","ScoreA":"890g","ScoreB":"argfds./"},{"LeagueName":"AwesomeLeague","TeamName":"Worst NA","ScoreA":"890g","ScoreB":"argfds./"}]}
