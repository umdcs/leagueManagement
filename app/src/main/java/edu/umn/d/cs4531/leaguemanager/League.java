package edu.umn.d.cs4531.leaguemanager;

import java.util.Calendar;
import java.util.LinkedList;

/**
 * Created by Mark W on 3/14/2017.
 */

public class League implements LMTInterface.L{

    //Variables
    private static String leagueName;
    private static LinkedList<Team> teams = new LinkedList<Team>();
    private static boolean scheduleFinalized=false; //Prevents teams and schedules being added and removed after the schedule is made
    private static int numberOfLanes;

    //Default Constructor
    League(){};
    //Constructor
    League(String name){ leagueName = name; }


    //Accessors and Basic Mutators
    public String getLeagueName() { return leagueName; }

    public LinkedList<Team> getTeams() { return teams; }

    public void setNumberOfLanes(int lanes){
        numberOfLanes = lanes;
    }

    public int getNumberOfLanes(){
        return numberOfLanes;
    }
    //Other Methods
    //Adds a team to a league only if the league schedule has not yet been finalized.
    // If schedule is already finalized, return false indicating that adding a team is unsuccessful
    // @param name: name of team to be added, typically the name of the skip.
    //@return true if team was added successfully. False otherwise.
    public boolean addTeam(String name) {

        if(!scheduleFinalized)
            teams.add(new Team(name));
        return !scheduleFinalized;
    }
    //Removes a team from a league only if the team name exists in the league.
    // Duplicated teams are also removed.
    // @param name: name of team to be removed, typically the name of the skip.
    //@return true if team was removed successfully. False otherwise.
    public boolean removeTeam(String name) {
        boolean removed = false;

        if (!scheduleFinalized){
            for (Team team:teams) {
                if(team.getTeamName().equals(name)){
                    removed = teams.remove(team);

                }
            }
        }
        return removed;
    }

    public LinkedList<Match> getFullSchedule() {
        return null;  //--Implement--
    }

    //public scoreboard getLeagueStandings

    //public scoreboard getFullResults

    /* public schedule createSchedule(start date and time meeting)
        create initial date and time, pass into createAllWeeks(), let that method create future weeks
    */

    //public schedule createPlayoffs

    //Private Methods
    /*
     */
    private LinkedList<Match> createOneWeekOfMatches(LinkedList<Team> currRotation, Calendar time){

        LinkedList<Match> matchList = new LinkedList<Match>();
        int listSize = currRotation.size() / 2;
        for (int i = 0; i < listSize; i++){
            matchList.add(new Match(currRotation.get(i), currRotation.get(i+listSize), i+1, time));
        }
        return matchList;
    }

    private LinkedList<LinkedList<Match>> createAllWeeks(){
        LinkedList<LinkedList<Match>> fullSchedule = new LinkedList<LinkedList<Match>>();
        int numberOfTeams = teams.size();
        int numberOfRounds = numberOfTeams - 1;
        if(numberOfTeams % 2 == 1){
            teams.add(new Team("Bye"));
            numberOfRounds++;
        }

        return fullSchedule;
    }
}
