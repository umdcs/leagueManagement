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

    @Test
    public void scheduleTest() throws Exception {
        getTeams().clear();
        addTeam("Team 1");
        addTeam("Team 2");
        addTeam("Team 3");

        assertFalse(createSchedule());

        setStartYear(2017);
        setStartMonth(1);
        setStartDate(28);
        setStartHour(17);
        setStartMinute(45);
        setMaxRounds(4);

        assertTrue(createSchedule());

        assertTrue(getTeams().get(0).getSchedule().size() == 3);
        assertTrue(getTeams().get(1).getSchedule().size() == 3);
        assertTrue(getTeams().get(2).getSchedule().size() == 3);

        int testScore = 0;
        for(Team team: getTeams()){
            while(team.getSchedule().size() > 0){
                team.enterScore(testScore,++testScore);
            }
        }

        int i = 1;
        for(LinkedList<Match> matchList : getFullSchedule()){
            System.out.println("Week " + i);
            for(Match match : matchList){
                System.out.println("Team A: " + match.getTeamA().getTeamName());
                System.out.println("Team B: " + match.getTeamB().getTeamName());
                System.out.println("Team A Score: " + match.getTeamAScore());
                System.out.println("Team B Score: " + match.getTeamBScore());
                System.out.println("\n");
            }
            i++;
        }

        for(Team team : getTeams()){
            System.out.println(team.getTeamName());
            for(Match match : team.getFinishedMatches()){
                System.out.println(match.getTeamA().getTeamName() + " vs " + match.getTeamB().getTeamName());
                System.out.println(match.getTeamAScore() + ":" + match.getTeamBScore() + "\n");
            }
        }

    }
}