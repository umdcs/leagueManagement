package edu.umn.d.cs4531.leaguemanager;

import org.junit.Test;

import java.util.LinkedList;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LeagueClassTester extends League{

    private LinkedList<Team> teamList= new LinkedList<Team>();


    public Team Team1=new Team("team1");
    public Team Team2=new Team("team2");
    public Team Team3=new Team("team3");


    @Test
    public void teamAdder() throws Exception {
        assertTrue(teamList.size()==0);

        teamList.add(Team1);
        assertTrue(teamList.size()==1);
        teamList.add(Team2);
        assertTrue(teamList.size()==2);
        teamList.add(Team3);
        assertTrue(teamList.size()==3);
    }
    @Test
    public void teamRemover() throws Exception {
        assertTrue(teamList.size()==3);
        teamList.remove();
        assertTrue(teamList.size()==2);
        teamList.add(Team2);
        assertTrue(teamList.size()==1);
        teamList.add(Team3);
        assertTrue(teamList.size()==0);
    }
}