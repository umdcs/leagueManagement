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

    public League parseJson(String jsonString){
        Gson gson = new Gson();
        LeagueJSONObject parsedObject = gson.fromJson(jsonString, this.getClass());
        //Set simple values of the league
        League buildLeague = new League(parsedObject.leagueName);
        buildLeague.setMaxRounds(parsedObject.maxRounds);
        buildLeague.setNumberOfLanes(parsedObject.numberOfLanes);
        buildLeague.setInitialCalendar(parsedObject.initialCalendar);
        //Set the full schedule, with each match currently having no teams assigned
        LinkedList<LinkedList<Match>> fullInputSchedule = new LinkedList<>();
        int fullScheduleIndex = 0;
        Calendar testCalendar = parsedObject.initialCalendar;
        for(JSONMatch match : parsedObject.fullSchedule) {
            Match nextMatch = new Match();
            nextMatch.setPlayTime(match.playTime);
            nextMatch.setLane(match.lane);
            nextMatch.setTeamAScore(match.teamAScore);
            nextMatch.setTeamBScore(match.teamBScore);
            if(match.playTime == testCalendar){
                fullInputSchedule.get(fullScheduleIndex).add(nextMatch);
            }
            else {
                fullScheduleIndex++; //Since the JSON object is cretaed with each match in cronological order, when the calendars dont match up, it sets it to the next week and adds to the next index in the full schedule
                testCalendar = match.playTime;
                fullInputSchedule.get(fullScheduleIndex).add(nextMatch);
            }
            nextMatch.getWinner(); //Sets the winner and match played variables
        }


        //Set the full list of teams
        LinkedList<Team> teamList = new LinkedList<>();
        for(JSONTeam team : parsedObject.teams) {
            Team nextTeam = new Team(team.teamName);
            for (int index : team.getSchedule()){

            }
        }

        //Go back and properly assign each team to the matches




        return null;
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