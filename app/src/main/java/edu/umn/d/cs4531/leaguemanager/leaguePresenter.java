package edu.umn.d.cs4531.leaguemanager;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Jeff Vang on 3/17/2017.
 */

public class leaguePresenter implements MVPComponents.Presenter{

    private MVPComponents.Model leagueModel;
    private MVPComponents.View leagueView;

    public leaguePresenter(MVPComponents.View view)
    {
        leagueView = view;
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
    public String[] leagueInput(String leagueName) {
        return new String[0];
    }

    @Override
    public String teamInput(String teamName) {
        return null;
    }

    @Override
    public void scoreInput(int winnerScore, int loserScore) {

    }

    public void setModel(leagueModel model)
    {
        this.leagueModel = model;
    }
}
