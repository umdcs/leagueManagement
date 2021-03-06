package edu.umn.d.cs4531.leaguemanager;

import java.util.ArrayList;
import java.util.Calendar;
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
        ArrayList<String> getLeagues();

        /**
         * Returns a list of the names of the teams in the league that was specified
         * in the leagueInput function
         * @param
         * @return
         */
        ArrayList<String> getTeams();

        /**
         * Takes a string leagueName selected from a list of available leagues
         * on the league select screen
         * @param leagueName
         * @return an array of strings of the teams in the selected league
         */
        void leagueInput(String leagueName);

        /**
         * Takes a string of the team selected ********************************
         * @param teamName
         * @return
         */
        void teamInput(String teamName);

        /**
         * Takes ints of the score for the previous game between teams
         * @param winnerScore
         * @param loserScore
         */
        void scoreInput(String winnerScore, String loserScore);

        /**
         * Returns true when the ID inputted by user and stored in the team is identical
         * @param teamID
         * @return
         */
        boolean teamIDVerification(String teamID);

        String getOtherTeam(String team);

        ArrayList<String> getSchedule(int week);

        String[][] getScoreboard();

        void run();

        void addTeam(String name, LinkedList<String> members, String ID);
        void createLeague(String name);
        void setLeagueCalendar(Calendar calendar);
        void setRoundsLanes(int rounds, int lanes);
        void uploadLeague(String name);
    }

    interface Model
    {
        /**
         * returns a list of team objects in the league specified by the setSelectedLeague function
         * @param
         * @return LinkedList<team> of teams in selected league
         */
        LinkedList<Team> getTeams();

        void addTeam(String name, LinkedList<String> members, String ID);

        /**
         * will have static leagues to get that will have teams inside and junk.
         * will be implemented differently depending on a server or not.
         * @return
         */
        LinkedList getLeagues();

        void addTeam(String name);

        Team getSelectedTeam();

        League getSelectedLeague();

        void createLeague(String name);
        void uploadLeague(String name);

        void setSelectedLeague(String leagueName);

        void setSelectedTeam(String teamName);
        void setSelectedInputtedScore(String score_A, String score_B);

        void inputData();

        LinkedList<Match> getSchedule(int week);
        Match[][] getScoreboard();

    }
}
