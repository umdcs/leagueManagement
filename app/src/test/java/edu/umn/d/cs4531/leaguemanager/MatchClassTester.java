package edu.umn.d.cs4531.leaguemanager;

import org.junit.Test;

import java.util.GregorianCalendar;

import static junit.framework.Assert.*;

/**
 * Created by Jeff Vang on 3/15/2017.
 */

public class MatchClassTester extends Match {
        GregorianCalendar time = new GregorianCalendar(2017, 9, 20, 17, 30);//Should Be October 20th, 2017 at 5:30PM
        Match match1 = new Match(new Team("team1"),new Team("team2"),1,time);
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

        @Test
        public void stringOutput()
        {
            match1.setTeamAScore(3);
            match1.setTeamBScore(2);
            System.out.println(match1.toString());
        }





}
