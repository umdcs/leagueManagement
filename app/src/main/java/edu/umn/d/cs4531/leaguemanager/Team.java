package edu.umn.d.cs4531.leaguemanager;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by Mark W on 3/14/2017.
 */

public class Team {

    //Variables


    private  int wins = 0;
    private  int losses = 0;
    private  int ties = 0;
    private  LinkedList<String> PlayerList = null;
    private  LinkedList<Match> Schedule = null;
    private  LinkedList<Match> FinishedMatches;
    private String teamName="";

    //Default Constructor
    Team(){};
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
    public  String getTeamName(){
        return teamName; }

    public int getWins() { return wins; }

    public int getLosses() { return losses; }

    public int getTies() { return ties; }

    public void addPlayer(String player) { PlayerList.add(player); }

    public boolean removePlayer(String player) { return PlayerList.remove(player); }

    public LinkedList<String> getPlayerList() { return PlayerList; }

    public void addMatch(Match match) { Schedule.add(match); }

    public void addSchedule(LinkedList<Match> matches) { Schedule = matches; }

    public Match removeMatch() {
        Match returnMatch;
        if(Schedule.size() > 0) {
            FinishedMatches.add(Schedule.peek());
            returnMatch = Schedule.remove();
        }
        else{
            returnMatch = null;
        }
        return returnMatch;
    }

    public Match peekMatch() { return Schedule.peek(); }

    public LinkedList<Match> getSchedule() { return Schedule; }

    public LinkedList<Match> getFinishedMatches() { return FinishedMatches; }

    //Other Methods

    //Class to be called ONLY by enterScore method
    protected void setResult(int result) {
        if(result == 0){
            wins++;
        }
        else if(result == 1){
            losses++;
        }
        else if(result == 2){
            ties++;
        }
        removeMatch();
    }
    public void enterScore(int teamScore, int opponentScore) {
        Match currentMatch = Schedule.peek();
        if (currentMatch.getTeamA() == this || currentMatch.getTeamB() == this) {
            if (currentMatch.getTeamA() == this) {
                currentMatch.setTeamAScore(teamScore);
                currentMatch.setTeamBScore(opponentScore);
            } else {
                currentMatch.setTeamAScore(opponentScore);
                currentMatch.setTeamBScore(teamScore);
            }
            int otherTeamResult; //0 = win, 1 = loss, 2 = draw
            if (currentMatch.getWinner() == this) {
                wins++;
                otherTeamResult = 1;
            } else if (currentMatch.getWinner().getTeamName() == "Draw") {
                ties++;
                otherTeamResult = 2;
            } else {
                losses++;
                otherTeamResult = 0;
            }
            removeMatch();
            if (currentMatch.getTeamA() == this) {
                currentMatch.getTeamB().setResult(otherTeamResult);
            } else {
                currentMatch.getTeamA().setResult(otherTeamResult);
            }
        }
        else {
            removeMatch();
        }
    }
}

