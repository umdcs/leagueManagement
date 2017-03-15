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
    League(String name){ LeagueName = name; }

    //Accessors and Basic Mutators
    public String getLeagueName() { return LeagueName; }

    public LinkedList<Team> getTeams() { return Teams; }

    //Other Methods
    //Adds a team to a league only if the league schedule has not yet been finalized.
    // If schedule is already finalized, return false indicating that adding a team is unsuccessful
    // @param name: name of team to be added, typically the name of the skip.
    //@return true if team was added successfully. False otherwise.
    public boolean addTeam(String name) {
        if(!scheduleFinalized) Teams.add(new Team(name));
        return !scheduleFinalized;
    }
    //Removes a team from a league only if the team name exists in the league.
    // Duplicated teams are also removed.
    // @param name: name of team to be removed, typically the name of the skip.
    //@return true if team was removed successfully. False otherwise.
    public boolean removeTeam(String name) {
        boolean removed = false;
        for (Team team:Teams) {
            if(Team.getTeamName().equals(name)){
                Teams.remove(team);
                removed = true;}
        }
        return removed;
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

        return null;
    }

    private LinkedList<LinkedList<Match>> createAllWeeks(){

        return null;
    }
}
