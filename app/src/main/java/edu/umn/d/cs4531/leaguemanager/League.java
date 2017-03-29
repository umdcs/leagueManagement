package edu.umn.d.cs4531.leaguemanager;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * Created by Mark W on 3/14/2017.
 */

public class League implements LMTInterface.L{

    //Variables
    private String leagueName;
    private LinkedList<Team> teams = new LinkedList<>();
    private LinkedList<LinkedList<Match>> fullSchedule = new LinkedList<>();
    private boolean scheduleFinalized=false; //Prevents teams and schedules being added and removed after the schedule is made
    private Calendar initialCalendar = null; // Useable as a different was to set calendar
    private int numberOfLanes; // Need to implement functionality
    private int startDate = -1;
    private int startMonth = -1;
    private int startYear = -1;
    private int startHour = -1;
    private int startMinute = -1;
    private int maxRounds = -1;

    //Default Constructor
    League(){};
    //Constructor
    League(String name){ leagueName = name; }


    //Accessors and Basic Mutators
    public String getLeagueName() { return leagueName; }

    public LinkedList<Team> getTeams() { return teams; }

    public void setNumberOfLanes(int lanes) { numberOfLanes = lanes; }

    public int getNumberOfLanes() { return numberOfLanes; }

    public void setInitialCalendar(Calendar calendar) { initialCalendar = calendar; }

    public void setStartDate(int date) { startDate = date; }

    public void setStartMonth(int month) { startMonth = month; } //0-11 scale

    public void setStartYear(int year) { startYear = year; } //4-digit

    public void setStartHour(int hour) { startHour = hour; } //Military time

    public void setStartMinute(int minute) { startMinute = minute; }

    public void setMaxRounds(int rounds) { maxRounds = rounds; }
    //Other Methods
    //Adds a team to the bottom of a league linkedlist<Team> only if the league schedule has not yet been finalized.
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
            for(int i=0;i<teams.size();++i) {
                if(teams.get(i).getTeamName().equals(name)){
                    removed = teams.remove(teams.get(i));
                }
            }
        }
        return removed;
    }

    //Returns a linked list of linked list of matches
    //Inside linked list: One week of matches.
    public LinkedList<LinkedList<Match>> getFullSchedule() {
        return fullSchedule;
    }

    //public scoreboard getLeagueStandings

    //public scoreboard getFullResults

    //Creates a full round robin schedule based on variables set prior to calling the function.
    // Requires scheduleFinalized to be false, and startYear,startMonth,startDate,startHour,startMinute,and maxRounds must have been changed from default
    //@return true if schedule was created successfully. False otherwise.
    public boolean createSchedule(){
        boolean success = false;
        if(/*numberOfLanes != 0 && */startYear != -1 && startMonth != -1 && startDate != -1 && startHour != -1 && startMinute != -1 && !scheduleFinalized) {
            initialCalendar = new GregorianCalendar(startYear, startMonth, startDate, startHour, startMinute);
            fullSchedule = createAllWeeks();
            scheduleFinalized = true;
            success = true;
        }
        else if(initialCalendar != null && !scheduleFinalized){
            fullSchedule = createAllWeeks();
            scheduleFinalized = true;
            success = true;
        }
        return success;
    }


    //public schedule createPlayoffs

    //Private Methods
    /*
     */
    private LinkedList<Match> createOneWeekOfMatches(LinkedList<Team> currRotation, Calendar time){

        LinkedList<Match> matchList = new LinkedList<Match>();
        int listSize = currRotation.size() / 2;
        for (int i = 0; i < listSize; i++){
            Match newestMatch = new Match(currRotation.get(i), currRotation.get(i+listSize), i+1, time);
            matchList.add(newestMatch);
            currRotation.get(i).addMatch(newestMatch);
            currRotation.get(i+listSize).addMatch(newestMatch);
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
        if(numberOfTeams == 1) { numberOfRounds = 0;}
        if(numberOfRounds > maxRounds) { numberOfRounds = maxRounds;}
        LinkedList<Team> currRotation = (LinkedList<Team>)teams.clone();
        GregorianCalendar currTime = (GregorianCalendar)initialCalendar.clone();
        for(int i = 0; i < numberOfRounds; i++){
            fullSchedule.add(createOneWeekOfMatches(currRotation, currTime));
            currTime.add(Calendar.DAY_OF_MONTH, 7);
            Team tempTeam = currRotation.remove(2);
            currRotation.addLast(tempTeam);
        }
        return fullSchedule;
    }
    //Gets called by model to input teamName, scoreA and scoreB into team.java and match.java
    //@param teamName, scoreA. scoreB
    public void inputData(String teamName, int scoreA, int scoreB) {
        for(int i=0;i<teams.size();++i) {
            if(teams.get(i).getTeamName().equals(teamName)){//if selected league is in the list
                teams.get(i).enterScore(scoreA,scoreB);
            }
        }
    }
}
