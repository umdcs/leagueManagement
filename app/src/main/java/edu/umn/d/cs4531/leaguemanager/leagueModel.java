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
        //Dummy list of leagues to send to the view for testing purposes DELETE AFTER TEST
        listOfLeagues.add(new League("League 1"));
        listOfLeagues.add(new League("League 2"));
        listOfLeagues.add(new League("League 3"));
        //--------------------------------------------------------------------------------
        this.Presenter = Presenter;

    }
    @Override
    public LinkedList<Team> getTeams(String leagueName) {
        return null;
    }

    @Override
    public String[] teamData(String teamName) {
        return new String[0];
    }

    @Override
    public LinkedList<League> getLeagues() {
        return listOfLeagues;
    }
    @Override
    public void createLeague(String name)
    {
       listOfLeagues.add(new League("name"));
    }
}
