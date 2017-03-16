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
        addTeam("Team 1");
        assertTrue(getTeams().peekLast().getTeamName().equals("Team 1"));
        assertTrue(getTeams().size()==1);
        addTeam("Team 2");
        assertTrue(getTeams().size()==2);
        assertTrue(getTeams().peekLast().getTeamName().equals("Team 1"));
        assertTrue(getTeams().peekLast().getTeamName().equals("Team 2"));

    }
    @Test
    public void teamRemover() throws Exception {
        addTeam("Team1");
        addTeam("Team2");
        for (Team team:getTeams()) {
            System.out.println(team.getTeamName());
        }
        assertTrue(removeTeam("Team2"));

        assertTrue(getTeams().size()==1);
        assertTrue(addTeam("Team3"));
        assertTrue(addTeam("Team4"));
        assertTrue(getTeams().size()==3);
        assertTrue(removeTeam("Team1"));

    }
}