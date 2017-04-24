package edu.umn.d.cs4531.leaguemanager;

import android.support.annotation.Nullable;
import android.util.Log;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by Mark W on 3/14/2017.
 */

public class Team {

    //Variables


    private int wins = 0;
    private int losses = 0;
    private int ties = 0;
    private String teamName="";
    private String password="";
    private LinkedList<String> PlayerList = new LinkedList<>();
    private LinkedList<Match> Schedule = new LinkedList<>();
    private LinkedList<Match> FinishedMatches = new LinkedList<>();


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

    public void addFinishedMatches(LinkedList<Match> matches) { FinishedMatches = matches;}

    public void setPassword(String pass) { password = pass;}

    public String getPassword() { return password;}

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

    public Match peekMatch() {
        if(Schedule.size() > 0) return Schedule.peek();
        else return null;
    }

    public LinkedList<Match> getSchedule() { return Schedule; }

    public LinkedList<Match> getFinishedMatches() { return FinishedMatches; }

    //Other Methods

    public void setAllScores(int win, int loss, int tie){
        wins = win;
        losses = loss;
        ties = tie;
    }

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
//        Log.d("Team: ", "My team is " + teamName);
//        Log.d("Team: ", "My opponent is " + currentMatch.getOtherTeam(this));
        if (currentMatch.getTeamA().peekMatch() == currentMatch.getTeamB().peekMatch()) { //Make sure the other team's next match is equal to this team's next match
            if (currentMatch.getTeamA().equals(this) || currentMatch.getOtherTeam(this).equals(this)) { //Make sure this team is in this match
                if (currentMatch.getTeamA().equals(this)) {
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
            } else {
                removeMatch();
            }
        }
    }
}

