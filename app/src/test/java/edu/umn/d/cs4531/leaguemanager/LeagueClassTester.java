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

    @Test
    public void teamAdder() throws Exception {
        assertTrue(getTeams().size()==0);
        addTeam("Team1");
        assertTrue(getTeams().size()==1);
        addTeam("Team2");
        assertTrue(getTeams().size()==2);
        addTeam("Team3");
        assertTrue(getTeams().size()==3);
    }
    @Test
    public void teamRemover() throws Exception {
        assertTrue(getTeams().size()==3);
        removeTeam("Team1");
        assertTrue(getTeams().size()==2);
        removeTeam("Team2");
        assertTrue(getTeams().size()==1);
        removeTeam("Team3");
        assertTrue(getTeams().size()==0);
    }
}