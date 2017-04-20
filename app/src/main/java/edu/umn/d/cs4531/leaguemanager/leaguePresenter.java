package edu.umn.d.cs4531.leaguemanager;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

/**
 * Created by Jeff Vang on 3/17/2017.
 */

public class leaguePresenter implements MVPComponents.Presenter {

    private MVPComponents.Model leagueModel;
    private MVPComponents.View leagueView;

    public leaguePresenter(MVPComponents.View view) {
        leagueView = view;
        leagueModel = new leagueModel(this);
    }
    public leaguePresenter getPresenter()
    {
        return this;
    }
    @Override
    public ArrayList<String> getLeagues() {

        ArrayList<String> leagueArray = new ArrayList<String>();
        LinkedList<League> leagues = new LinkedList<League> (leagueModel.getLeagues());
        for (int i = 0; i < leagues.size(); i++) {
            leagueArray.add(leagues.get(i).getLeagueName());
        }
        return leagueArray;
    }

    @Override
    public ArrayList<String> getTeams() {
        ArrayList<String> teamArray = new ArrayList<String>();
        LinkedList<Team> teamLinked = new LinkedList<Team> (leagueModel.getTeams());
        for (int i = 0; i < teamLinked.size(); i++) {
            teamArray.add(teamLinked.get(i).getTeamName());
        }
        return teamArray;
    }

    @Override
    public void leagueInput(String leagueName) {
        leagueModel.setSelectedLeague(leagueName);
    }

    @Override
    public void teamInput(String teamName) {
        leagueModel.setSelectedTeam(teamName);

    }
    @Override
    public void scoreInput(String scoreA, String scoreB) {
        leagueModel.setSelectedInputtedScore(scoreA, scoreB);

    }
    @Override
    public boolean teamIDVerification(String teamID) {
        String mID = leagueModel.getSelectedTeam().getTeamName();
        if (teamID.equalsIgnoreCase(mID)) return true;
        else return false;
    }
    @Override
    public String getOtherTeam(String team) {
        Match thisMatch = leagueModel.getSelectedTeam().peekMatch();
        Team otherTeam = thisMatch.getOtherTeam(leagueModel.getSelectedTeam());
        return otherTeam.getTeamName();
    }
    @Override
    public void run()
        {
            leagueModel.inputData();
        };

    public void setModel(leagueModel model)
    {
        this.leagueModel = model;
    }

    @Override
    public ArrayList<String> getSchedule(int week) {
        ArrayList<String> returnArray = new ArrayList<String>();
        LinkedList<Match> schedule = leagueModel.getSchedule(week);

        for (Match match : schedule) {
            returnArray.add(match.toString());
        }
        return returnArray;
    }

    @Override
    public String[][] getScoreboard() {
        Match[][] matchScoreboard = leagueModel.getScoreboard();
        int bounds = matchScoreboard.length;
        String[][] ssb = new String[bounds][bounds+1];
        for (int row = 0; row < bounds; row ++) {
            ssb[row][0] = leagueModel.getTeams().get(row).getTeamName();
            for (int col = 1; col < bounds + 1; col ++) {
                if (matchScoreboard[row][col-1] != null) {
                    if (matchScoreboard[row][col-1].getTeamA() == leagueModel.getTeams().get(row)) {
                        ssb[row][col] = "| " + matchScoreboard[row][col-1].getTeamAScore() + " : " + matchScoreboard[row][col-1].getTeamBScore() + " ";
                    }
                    else {
                        ssb[row][col] = "| " + matchScoreboard[row][col-1].getTeamBScore() + " : " + matchScoreboard[row][col-1].getTeamAScore() + " ";
                    }
                }
                else {
                    ssb[row][col] = "| - : - ";
                }
            }
        }
        return ssb;
    }

    @Override
    public void addTeam(String name, LinkedList<String> members, String ID) {
        leagueModel.getSelectedLeague().addTeam(name);
        leagueModel.setSelectedTeam(name);
        for (String member: members) {
            leagueModel.getSelectedTeam().addPlayer(member);
        }
        leagueModel.getSelectedTeam().setPassword(ID);
    }

    @Override
    public void createLeague(String name, Calendar cal) {
        leagueModel.createLeague(name, cal);
    }
}
