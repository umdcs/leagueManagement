package edu.umn.d.cs4531.leaguemanager;

/**
 * Created by Jeff Vang on 3/16/2017.
 */

import org.junit.Test;

import java.util.LinkedList;
import static org.junit.Assert.*;

public class TeamClassTester extends Team{

    Team team1 = new Team("Team 1");
    Team team2 = new Team("Team 2");
    Team team3 = new Team("Team 3");
    Match match1 = new Match(team1, team2, 1, null);
    Match match2 = new Match(team2, team3, 1, null);
    Match match3 = new Match(team3, team1, 1, null);

    //This test adds matches and checks to see if schedule is updated.
    @Test
    public void teamScheduleTest() throws Exception{
        team1.addMatch(match1);
        assertTrue(team1.peekMatch() == match1);
        team1.addMatch(match3);
        assertTrue(team1.getSchedule().peekLast() == match3);
        assertTrue(team1.removeMatch() == match1);
        assertTrue(team1.removeMatch() == match3);
        assertTrue(team1.removeMatch() == null);
    }
//This test adds players and checks for update.
    @Test
    public void playerListTest() throws Exception{
        team1.addPlayer("Player1");
        team1.addPlayer("Player2");
        team1.addPlayer("Player3");
        assertTrue(team1.getPlayerList().get(0) == "Player1");
        assertTrue(team1.getPlayerList().get(1) == "Player2");
        assertTrue(team1.getPlayerList().get(2) == "Player3");
        assertTrue(team1.removePlayer("Player2"));
        assertTrue(team1.getPlayerList().get(0) == "Player1");
        assertTrue(team1.getPlayerList().get(1) == "Player3");
        assertFalse(team1.removePlayer("Player4"));
    }
    //This test adds scores and checks for update.
    @Test
    public void enterScoreTest() throws Exception{
        team1.addMatch(match1);
        team1.addMatch(match3);
        assertTrue(team1.peekMatch() == match1);
        team2.addMatch(match1);
        assertTrue(team2.peekMatch() == match1);

        team1.enterScore(4,1);

        assertTrue(match1.getTeamAScore() == 4);
        assertTrue(match1.getTeamBScore() == 1);
        assertTrue(team1.peekMatch() == match3);
        assertTrue(team2.peekMatch() == null);
    }




}
