package edu.umn.d.cs4531.leaguemanager;

import org.junit.Test;

import java.util.Calendar;

import static junit.framework.Assert.*;

/**
 * Created by Jeff Vang on 3/15/2017.
 */

public class MatchClassTester extends Match {
        public Match match1 = new Match(new Team("team1"),new Team("team2"),1,null);
        Team winningTeam = null;
        @Test
        public void determineWinnerTest()
        {
            match1.setTeamAScore(5);
            match1.setTeamBScore(2);
            winningTeam = match1.getWinner();
            assertTrue(winningTeam.getTeamName().equals("team1"));

            match1.setTeamAScore(2);
            match1.setTeamBScore(5);
            winningTeam = match1.getWinner();
            assertTrue(winningTeam.getTeamName().equals("team2"));

        }





}
