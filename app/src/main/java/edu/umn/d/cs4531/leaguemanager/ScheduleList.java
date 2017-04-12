package edu.umn.d.cs4531.leaguemanager;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Hunter on 3/28/2017.
 */
public class ScheduleList {

    private ArrayList<String> weekSchedule;
    private String[][] mScoreboard;

    private static ScheduleList ourInstance = new ScheduleList();

    public static ScheduleList getInstance() {
        return ourInstance;
    }

    private ScheduleList() {
    }

    public ArrayList<String> getWeekSchedule() {
        return weekSchedule;
    }

    public String[][] getScoreboard() { return mScoreboard;}

    public void setScheduleList(ArrayList<String> list) {
        weekSchedule = list;
    }

    public void setScoreboard(String[][] scoreboard) { this.mScoreboard = scoreboard;}
}
