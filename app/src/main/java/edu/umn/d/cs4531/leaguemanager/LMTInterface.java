package edu.umn.d.cs4531.leaguemanager;

/**
 * Created by Jeff Vang on 3/16/2017.
 */

public interface LMTInterface {

    interface L
    {
       boolean addTeam(String Name);
        boolean removeTeam(String name);

    }

    interface M
    {
        void determineWinner();
    }
    interface T
    {
        void addPlayer(String player);
        boolean removePlayer(String player);

    }


}
