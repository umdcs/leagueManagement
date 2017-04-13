package edu.umn.d.cs4531.leaguemanager;

import java.util.Calendar;
/**
 * Created by Mark W on 3/14/2017.
 */

public class Match {

    //Variables

    private int teamAScore = 0;
    private int teamBScore = 0;
    private transient Team teamA;
    private transient Team teamB; //Transient prevents being called when Gson creates the Json string
    private Calendar playTime;
    private int lane = 0;
    private int winner = 2; //0 = teamA, 1 = teamB, 2 = draw
    private boolean matchPlayed = false;

    //Constructor
    public Match() {

        teamA = null;
        teamB = null;
        playTime = null;
    }

    public Match(Team A, Team B, int newLane, Calendar time) {
        teamA = A;
        teamB = B;
        playTime = time;
        lane = newLane;
    }

    //Copy Constructor
    public Match(Match match) {
        teamAScore = match.getTeamAScore();
        teamBScore = match.getTeamBScore();
        teamA = match.getTeamA();
        teamB = match.getTeamB();
        playTime = match.getPlayTime();
        lane = match.getLane();
    }

    //Accessors and Basic Mutators
    public void setTeamA(Team A) { teamA = A; }

    public void setTeamB(Team B) { teamB = B; }

    public Team getTeamA() { return teamA; }

    public Team getTeamB() { return teamB; }

    public Team getOtherTeam(Team homeTeam) {
        Team returnTeam = null;
        if(homeTeam == teamA) returnTeam = teamB;
        else if(homeTeam == teamB) returnTeam = teamA;
        return returnTeam;
    }

    public boolean setTeamAScore(int score) {
        if(teamA != null) {
            teamAScore = score;
            matchPlayed = true;
        }
        return matchPlayed;
    }

    public boolean setTeamBScore(int score) {
        if(teamB != null) {
            teamBScore = score;
            matchPlayed = true;
        }
        return matchPlayed;
    }
    public int getTeamAScore() { return teamAScore; }

    public int getTeamBScore() { return teamBScore; }

    public void setLane(int newLane) { lane = newLane; }

    public int getLane() { return lane; }

    public void setPlayTime(Calendar time) { playTime = (Calendar) time.clone(); }

    public Calendar getPlayTime() { return playTime; }

    public int getWinnerInt() {return winner;}

    public void setWinnerInt(int i) {winner = i;}

    //Debug toString function
    public String toString() {
        String debug;

        debug = "Team A: " + teamA.getTeamName() + "\n" +
                "Team B: " + teamB.getTeamName() + "\n" +
                "Team A Score: " + teamAScore + "\n" +
                "Team B Score: " + teamBScore + "\n" +
                "Play Time:\n" +
                (playTime.get(Calendar.MONTH)+1) + "/" + playTime.get(Calendar.DAY_OF_MONTH) + "/" + playTime.get(Calendar.YEAR) + "\n" +
                playTime.get(Calendar.HOUR) + ":";
        if (playTime.get(Calendar.MINUTE) < 10) { debug += "0";}
        debug += playTime.get(Calendar.MINUTE);
        if (playTime.get(Calendar.HOUR_OF_DAY) > 11) debug += "PM";
        else debug += "AM";
        return debug;
    }

    //Other Methods
    public Team getWinner() {
        determineWinner();
        Team winningTeam = new Team("Draw");
        if(winner == 0) winningTeam = teamA;
        else if(winner == 1) winningTeam = teamB;
        return winningTeam;
    }

    //Private Methods
    private void determineWinner(){
        if(teamAScore > teamBScore) winner = 0;
        else if(teamAScore < teamBScore) winner = 1;
        else winner = 2;
    }
}
