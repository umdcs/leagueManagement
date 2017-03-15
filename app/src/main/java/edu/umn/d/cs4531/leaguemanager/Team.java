package edu.umn.d.cs4531.leaguemanager;

import java.util.LinkedList;
import java.util.Queue;
/**
 * Created by Mark W on 3/14/2017.
 */

public class Team {

    //Variables
    private int wins = 0;
    private int losses = 0;
    private int ties = 0;
    private LinkedList<String> PlayerList = null;
    private Queue<Match> Schedule = null;
    private String teamName;

    //Accessors and Basic Mutators
    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTies() {
        return ties;
    }

    public void addPlayer(String player) {
        PlayerList.add(player);
    }

    public boolean removePlayer(String player) {
        return PlayerList.remove(player);
    }

    public LinkedList<String> getPlayerList() {
        return PlayerList;
    }

    public void addMatch(Match match) {
        Schedule.add(match);
    }

    public void addSchedule(Queue<Match> matches) {
        Schedule = matches;
    }

    public Match removeMatch() {
        return Schedule.remove();
    }

    public Match peekMatch() {
        return Schedule.peek();
    }

    //Other Methods
    public void enterScore(int teamScore, int opponentScore) {

    }


}
