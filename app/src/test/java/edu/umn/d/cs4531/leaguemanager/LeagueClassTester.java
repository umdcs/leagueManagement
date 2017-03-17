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
        getTeams().clear();
        assertTrue(getTeams().size()==0);
        addTeam("Team 1");
        assertTrue(getTeams().peekLast().getTeamName().equals("Team 1"));
        assertTrue(getTeams().size()==1);
        addTeam("Team 2");
        assertTrue(getTeams().peekFirst().getTeamName().equals("Team 1"));
        assertTrue(getTeams().peekLast().getTeamName().equals("Team 2"));
        assertTrue(getTeams().size()==2);
        addTeam("Team 3");
        assertTrue(getTeams().peekFirst().getTeamName().equals("Team 1"));
        assertTrue(getTeams().peekLast().getTeamName().equals("Team 3"));
        assertTrue(getTeams().size()==3);
    }
    @Test
    public void teamRemover() throws Exception {

        getTeams().clear();
        addTeam("Team 1");
        addTeam("Team 2");
        addTeam("Team 3");
        assertTrue(removeTeam("Team 3"));
        assertTrue(getTeams().peekFirst().getTeamName().equals("Team 1"));
        assertTrue(getTeams().peekLast().getTeamName().equals("Team 2"));
        assertTrue(getTeams().size()==2);

        getTeams().clear();
        addTeam("Team 1");
        addTeam("Team 2");
        addTeam("Team 3");
        assertTrue(removeTeam("Team 2"));
        assertTrue(getTeams().peekFirst().getTeamName().equals("Team 1"));
        assertTrue(getTeams().peekLast().getTeamName().equals("Team 3"));
        assertTrue(getTeams().size()==2);


        getTeams().clear();
        addTeam("Team 1");
        addTeam("Team 2");
        addTeam("Team 3");
        assertTrue(removeTeam("Team 1"));
        assertTrue(getTeams().peekFirst().getTeamName().equals("Team 2"));
        assertTrue(getTeams().peekLast().getTeamName().equals("Team 3"));
        assertTrue(getTeams().size()==2);
    }
}