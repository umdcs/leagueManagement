package edu.umn.d.cs4531.leaguemanager;

import java.util.Calendar;
import java.util.LinkedList;

/**
 * Created by Mark W on 3/14/2017.
 */

public class League {

    //Variables
    private static String LeagueName;
    private static LinkedList<Team> Teams;
    private static boolean scheduleFinalized; //Prevents teams and schedules being added and removed after the schedule is made

    //Constructor
    public League(String name){ LeagueName = name; }

    //Accessors and Basic Mutators
    public String getLeagueName() { return LeagueName; }

    public LinkedList<Team> getTeams() { return Teams; }

    //Other Methods
    public boolean addTeam(String name) {
        if(!scheduleFinalized) Teams.add(new Team(name));
        return !scheduleFinalized;
    }

    public boolean removeTeam(String name) {
        return false;  //--Implement--
    }

    public LinkedList<Match> getFullSchedule() {
        return null;  //--Implement--
    }

    //public scoreboard getLeagueStandings

    //public scoreboard getFullResults

    //public schedule createSchedule

    //public schedule createPlayoffs

    //Private Methods
    /*
     */
    private LinkedList<Match> createOneWeekOfMatches(LinkedList<Team> currRotation){
        double listSize = currRotation.size() / 2;
        int testOdd = listSize;
        return null;
    }

    private LinkedList<LinkedList<Match>> createAllWeeks(){

        return null;
    }
}
