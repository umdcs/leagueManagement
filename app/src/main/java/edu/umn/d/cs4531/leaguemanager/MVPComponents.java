package edu.umn.d.cs4531.leaguemanager;

import java.util.LinkedList;

/**
 * Created by Hunter on 3/16/2017.
 */

public interface MVPComponents {

    interface View
    {

    }

    interface Presenter
    {

        /**
         * gets a list of available leagues in the sport selected
         * Will be removed upon later versions when sport selection is implemented
         */
        String getLeagues();

        /**
         * Takes a string leagueName selected from a list of available leagues
         * on the league select screen
         * @param leagueName
         * @return an array of strings of the teams in the selected league
         */
        String[] leagueInput(String leagueName);

        /**
         * Takes a string of the team selected ********************************
         * @param teamName
         * @return
         */
        String teamInput(String teamName);

        /**
         * Takes ints of the score for the previous game between teams
         * @param winnerScore
         * @param loserScore
         */
        void scoreInput(int winnerScore, int loserScore);
    }

    interface Model
    {
        /**
         * returns a list of teams in the league selected
         * @param leagueName
         * @return String[] teams in selected league
         */
        LinkedList getTeams(String leagueName);

        /**
         * Gets all relevant info on the team selected so it can be displayed in the view
         * @param teamName
         * @return
         */
        String[] teamData(String teamName);

        /**
         * will have static leagues to get that will have teams inside and junk.
         * will be implemented differently depending on a server or not.
         * @return
         */
        LinkedList getLeagues();

        void createLeague(String name);
    }
}
