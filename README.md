# LeagueManager
League Manager distributed application for creating and managing ongoing league play
#This is Jeff Vang's edit after cloning the repo.#
Mark's commit test

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