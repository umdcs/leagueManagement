package edu.umn.d.cs4531.leaguemanager;

import java.util.LinkedList;

/**
 * Created by Jeff Vang on 3/17/2017.
 */

public class leagueModel implements MVPComponents.Model{
    LinkedList<League> listOfLeagues;
    private MVPComponents.Presenter Presenter;

    public leagueModel(MVPComponents.Presenter Presenter)
    {
        listOfLeagues = new LinkedList<League>();
        this.Presenter = Presenter;

    }
    @Override
    public String[] getTeams(String leagueName) {
        return new String[0];
    }

    @Override
    public String[] teamData(String teamName) {
        return new String[0];
    }

    @Override
    public String[] getLeagues() {
        return new String[0];
    }
    @Override
    public void createLeague(String name)
    {
       listOfLeagues.add(new League("name"));
    }
}
