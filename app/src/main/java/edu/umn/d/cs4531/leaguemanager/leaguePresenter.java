package edu.umn.d.cs4531.leaguemanager;

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
    public String getLeagues() {
        return leagueModel.getLeagues().toString();
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
