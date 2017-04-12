package edu.umn.d.cs4531.leaguemanager;

import android.os.Parcelable;

import java.util.ArrayList;
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
        int bounds = leagueModel.getTeams().size();
        String[][] ssb = new String[bounds + 1][bounds + 1];

        for (int i = 1; i < bounds+1; i++) {
            ssb[0][i] = leagueModel.getTeams().get(i-1).getTeamName();
        }
        for (int i = 1; i < bounds+1; i++) {
            ssb[i][0] = leagueModel.getTeams().get(i-1).getTeamName();
        }
        for (int i = 0; i < bounds+1; i++) {
            for (int j = 0; j < bounds+1; i++) {
                ssb[i+1][j+1] = Integer.toString(matchScoreboard[i][j].getTeamAScore()) + " : "
                        + Integer.toString(matchScoreboard[i][j].getTeamBScore());
            }
        }
        return ssb;
    }
}
