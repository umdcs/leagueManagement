package edu.umn.d.cs4531.leaguemanager;

import java.util.Calendar;
import java.util.LinkedList;

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
    }

    private void setFullSchedule(LinkedList<LinkedList<Match>> theList){
        for(LinkedList<Match> innerList: theList){
            for(Match match: innerList) {
                JSONMatch jsonMatch = new JSONMatch();
                jsonMatch.setTeamAName(match.getTeamA().getTeamName());
                jsonMatch.setTeamBName(match.getTeamB().getTeamName());
                jsonMatch.setLane(match.getLane());
                jsonMatch.setWinner(match.getWinnerInt());
                jsonMatch.setPlayTime(match.getPlayTime());
                jsonMatch.setTeamAScore(match.getTeamAScore());
                jsonMatch.setTeamBScore(match.getTeamBScore());
                fullSchedule.add(jsonMatch);
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

            String[] roster = new String[team.getPlayerList().size()];
            int i = 0;
            for(String player : team.getPlayerList()){
                roster[i] = player;
                ++i;
            }

            int[] upcomingMatches = new int[team.getSchedule().size()];
            for(Match match : team.getSchedule()){
                boolean found = false;
                int fullScheduleIndex = 0;
                while(!found && fullSchedule.get(fullScheduleIndex)!=null){
                    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                }
            }
        }
    }

    private class JSONTeam {
        private int wins = 0;
        private int losses = 0;
        private int ties = 0;
        private String teamName="";
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