package edu.umn.d.cs4531.leaguemanager;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Mark W on 4/13/2017.
 */

public class LeagueJSONObject {

    private String leagueName;
    private LinkedList<JSONTeam> teams = new LinkedList<>();
    private LinkedList<JSONMatch> fullSchedule = new LinkedList<>();
    private Calendar initialCalendar; // Useable as a different was to set calendar
    private int numberOfLanes;
    private int maxRounds = 16;
    private int[][] scoreboard;

    public LeagueJSONObject (League theLeague) {
        leagueName = theLeague.getLeagueName();
        setFullSchedule(theLeague.getFullSchedule());
        setTeams(theLeague.getTeams());
        initialCalendar = theLeague.getInitialCalendar();
        numberOfLanes = theLeague.getNumberOfLanes();
        maxRounds = theLeague.getMaxRounds();
    }

    //Accessors
//    public String getLeagueName() { return leagueName;}
//    public LinkedList<JSONTeam> getTeams() { return teams;}
//    public LinkedList<JSONMatch> getFullSchedule() { return fullSchedule;}
//    public Calendar getInitialCalendar() { return initialCalendar;}
//    public int getNumberOfLanes() { return numberOfLanes;}
//    public int getMaxRounds() { return maxRounds;}
//    public int[][] getScoreboard() { return scoreboard;}

    private void setFullSchedule(LinkedList<LinkedList<Match>> theList){
        for(LinkedList<Match> innerList: theList){
            for(Match match: innerList) { //Set the internal values for each match class
                JSONMatch jsonMatch = new JSONMatch();
                jsonMatch.setTeamAName(match.getTeamA().getTeamName());
                jsonMatch.setTeamBName(match.getTeamB().getTeamName());
                jsonMatch.setLane(match.getLane());
                jsonMatch.setWinner(match.getWinnerInt());
                jsonMatch.setPlayTime(match.getPlayTime());
                jsonMatch.setTeamAScore(match.getTeamAScore());
                jsonMatch.setTeamBScore(match.getTeamBScore());
                fullSchedule.add(jsonMatch); //Add to the schedule
            }
        }
    }

    private void setTeams (LinkedList<Team> teamList) {
        for(Team team : teamList){
            JSONTeam jsonTeam = new JSONTeam();
            jsonTeam.setWins(team.getWins());
            jsonTeam.setLosses(team.getLosses());
            jsonTeam.setTies(team.getTies());
            jsonTeam.setTeamName(team.getTeamName());
            jsonTeam.setPassword(team.getPassword());

            //Setting the Player Roster
            String[] roster = new String[team.getPlayerList().size()];
            int i = 0;
            for(String player : team.getPlayerList()){
                roster[i] = player;
                ++i;
            }
            jsonTeam.setPlayerList(roster);

            //Setting the upcoming matches
            jsonTeam.setSchedule(setSchedules(team.getSchedule()));

            //Setting the completed matches
            jsonTeam.setFinishedMatches(setSchedules(team.getFinishedMatches()));
            teams.add(jsonTeam);
        }
    }

    private int[] setSchedules(LinkedList<Match> matchList){
        int MatchesIndex = 0; //Index of the overall array, used to keep chronological order and to enable placing into array
        int[] Matches = new int[matchList.size()]; //Will be returned to set the team's upcoming matches
        for(Match match : matchList){
            boolean found = false;
            int fullScheduleIndex = 0; //Sets the match index for fast recovery after finding the match in the full schedule
            while(!found && fullScheduleIndex < fullSchedule.size()){
                if(match.getTeamA().getTeamName() == fullSchedule.get(fullScheduleIndex).getTeamAName() &&
                        match.getTeamB().getTeamName() == fullSchedule.get(fullScheduleIndex).getTeamBName()){
                    Matches[MatchesIndex] = fullScheduleIndex;
                }
                fullScheduleIndex++;
            }
            MatchesIndex++;
        }
        return Matches;
    }

    public String toJson(){
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        //Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public static League parseJson(String jsonString){
        Gson gson = new Gson();
        LeagueJSONObject parsedObject = gson.fromJson(jsonString, LeagueJSONObject.class);
        //Set simple values of the league
        League buildLeague = new League(parsedObject.leagueName);
        buildLeague.setMaxRounds(parsedObject.maxRounds);
        buildLeague.setNumberOfLanes(parsedObject.numberOfLanes);
        buildLeague.setInitialCalendar(parsedObject.initialCalendar);

        //Set the full schedule, with each match currently having no teams assigned
        LinkedList<LinkedList<Match>> fullInputSchedule = new LinkedList<>();
        LinkedList<Match> weekInputSchedule = new LinkedList<>();
        int fullScheduleIndex = 0;
        Calendar testCalendar = parsedObject.initialCalendar;
        for(JSONMatch match : parsedObject.fullSchedule) {
            Match nextMatch = new Match();
            nextMatch.setPlayTime(match.playTime);
            nextMatch.setLane(match.lane);
            nextMatch.setTeamAScore(match.getTeamAScore());
            nextMatch.setTeamBScore(match.getTeamBScore());
            nextMatch.setTeamAName(match.teamAName);
            nextMatch.setTeamBName(match.teamBName);
            if(match.playTime.get(Calendar.DAY_OF_MONTH) == testCalendar.get(Calendar.DAY_OF_MONTH)){
                weekInputSchedule.add(nextMatch);
            }
            else {
                fullScheduleIndex++; //Since the JSON object is cretaed with each match in cronological order, when the calendars dont match up, it sets it to the next week and adds to the next index in the full schedule
                testCalendar = match.playTime;
                fullInputSchedule.add((LinkedList<Match>)weekInputSchedule.clone());
                weekInputSchedule.clear();
                weekInputSchedule.add(nextMatch);
            }
            //the winner and matchPlayed values are set along with the teams
        }
        fullInputSchedule.add((LinkedList<Match>)weekInputSchedule.clone()); //Last week of matches is not automatically added, due to the if-success not addig weeks to fullInputSchedule
        buildLeague.setFullSchedule(fullInputSchedule);

        //Set the full list of teams
        LinkedList<Team> teamList = new LinkedList<>();
        for(JSONTeam team : parsedObject.teams) {
            //Set default values
            Team nextTeam = new Team(team.teamName);
            nextTeam.setAllScores(team.wins,team.losses,team.ties);
            nextTeam.setPassword(team.password);

            //Set roster
            for(String name:team.getPlayerList()){
                nextTeam.addPlayer(name);
            }

            //Set schedule and finished matches, with proper references to the matches
            int matchesPerWeek = buildLeague.getFullSchedule().get(0).size();
            LinkedList<Match> inputSchedule = new LinkedList<>();
            LinkedList<Match> inputFinishedMatches = new LinkedList<>();
            Match inputMatch;
            for (int index : team.getSchedule()){
                inputMatch = buildLeague.getFullSchedule().get(index/matchesPerWeek).get(index%matchesPerWeek); //Creates match pointer for easy reference
                inputSchedule.add(inputMatch); //Adds to schedule
                if(inputMatch.getTeamAName().equals(nextTeam.getTeamName())) {inputMatch.setTeamA(nextTeam);} //Sets itself to the proper team
                else if(inputMatch.getTeamBName().equals(nextTeam.getTeamName())) {inputMatch.setTeamB(nextTeam);}

            }
            for (int index : team.getFinishedMatches()){
                inputMatch = buildLeague.getFullSchedule().get(index/matchesPerWeek).get(index%matchesPerWeek);
                inputMatch.getWinner(); //Since the match is played, it sets the winner and matchPlayed values
                inputFinishedMatches.add(inputMatch); //Adds to schedule
                if(inputMatch.getTeamAName().equals(nextTeam.getTeamName())) {inputMatch.setTeamA(nextTeam);} //Sets itself to the proper team
                else if(inputMatch.getTeamBName().equals(nextTeam.getTeamName())) {inputMatch.setTeamB(nextTeam);}
            }

            nextTeam.addSchedule(inputSchedule);
            nextTeam.addFinishedMatches(inputFinishedMatches);
            teamList.add(nextTeam);
        }
        buildLeague.setTeams(teamList);

//        //Go back and properly assign each team to the matches
//        for(LinkedList<Match> week:buildLeague.getFullSchedule()){ //Gets each week
//            for(Match match: week){ //Gets each match of each week
//                int found = 0;
//                for (int index = 0; index < buildLeague.getTeams().size() && found < 2; index++){ //If proper teams are found or index extends beyond list size, exit
//                    if(buildLeague.getTeams().get(index).getTeamName() == match.getTeamAName()) {
//                        match.setTeamA(buildLeague.getTeams().get(index));
//                        found++; //One team is found, must find other team to exit
//                    }
//                    else if(buildLeague.getTeams().get(index).getTeamName() == match.getTeamBName()) {
//                        match.setTeamB(buildLeague.getTeams().get(index));
//                        found++; //Other team is found, must find first to exit
//                    }
//                }
//            }
//        }

        return buildLeague;
    }

    private class JSONTeam {
        private int wins = 0;
        private int losses = 0;
        private int ties = 0;
        private String teamName="";
        private String password="";
        private String[] PlayerList;
        private int[] Schedule; // Stores the index of each match in fullSchedule, instead of passing instances of match each time
        private int[] FinishedMatches;

        //Accessors and Mutators
        public void setWins (int w) { wins = w;}
        public int getWins () {return wins;}
        public void setLosses (int l) {losses = l;}
        public int getLosses () {return losses;}
        public void setTies (int t) {ties = t;}
        public int getTies () {return ties;}
        public void setTeamName (String n) {teamName = n;}
        public String getTeamName () {return teamName;}
        public void setPassword (String n) {password = n;}
        public String getPassword () {return password;}
        public void setPlayerList (String[] list) {PlayerList = list;}
        public String[] getPlayerList () {return PlayerList;}
        public void setSchedule (int[] list) {Schedule = list;}
        public int[] getSchedule () {return Schedule;}
        public void setFinishedMatches (int[] list) {FinishedMatches = list;}
        public int[] getFinishedMatches () {return FinishedMatches;}

    }

    private class JSONMatch {
        private int teamAScore = 0;
        private int teamBScore = 0;
        private String teamAName = null;
        private String teamBName = null;
        private Calendar playTime;
        private int lane = 0;
        private int winner = 2;
        private boolean matchPlayed = false;

        //Accessors and Mutators
        public void setTeamAScore (int w) { teamAScore = w;}
        public int getTeamAScore () {return teamAScore;}
        public void setTeamBScore (int w) { teamBScore = w;}
        public int getTeamBScore () {return teamBScore;}
        public void setLane (int w) { lane = w;}
        public int getLane () {return lane;}
        public void setWinner (int w) { winner = w;}
        public int getWinner () {return winner;}
        public void setTeamAName (String n) {teamAName = n;}
        public String getTeamAName () {return teamAName;}
        public void setTeamBName (String n) {teamBName = n;}
        public String getTeamBName () {return teamBName;}
        public void setPlayTime (Calendar c) {playTime = c;}
        public Calendar getPlayTime () {return playTime;}

    }
}