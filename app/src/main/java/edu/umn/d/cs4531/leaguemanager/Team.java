package edu.umn.d.cs4531.leaguemanager;

import java.util.LinkedList;

/**
 * Created by Mark W on 3/14/2017.
 */

public class Team {

    //Variables


    private static int wins = 0;
    private static int losses = 0;
    private static int ties = 0;
    private static LinkedList<String> PlayerList = null;
    private static LinkedList<Match> Schedule = null;
    private static LinkedList<Match> FinishedMatches;
    private static String teamName;

    //Constructor
    public Team(String name){
        teamName = name;
    }

    //Copy Constructor
    public Team(Team team){
        wins = team.getWins();
        losses = team.getLosses();
        ties = team.getTies();
        PlayerList = team.getPlayerList();
        Schedule = team.getSchedule();
    }

    //Accessors and Basic Mutators
    public static String getTeamName(){ return teamName; }

    public int getWins() { return wins; }

    public int getLosses() { return losses; }

    public int getTies() { return ties; }

    public void addPlayer(String player) { PlayerList.add(player); }

    public boolean removePlayer(String player) { return PlayerList.remove(player); }

    public LinkedList<String> getPlayerList() { return PlayerList; }

    public void addMatch(Match match) { Schedule.add(match); }

    public void addSchedule(LinkedList<Match> matches) { Schedule = matches; }

    public Match removeMatch() {
        FinishedMatches.add(Schedule.peek());
        return Schedule.remove();
    }

    public Match peekMatch() { return Schedule.peek(); }

    public LinkedList<Match> getSchedule() { return Schedule; }

    public LinkedList<Match> getFinishedMatches() { return FinishedMatches; }

    //Other Methods
    public void enterScore(int teamScore, int opponentScore) {
        //--Implement--
    }


}

