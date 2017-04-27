package edu.umn.d.cs4531.leaguemanager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.Reader;
//import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import java.net.URLEncoder;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.content.Intent;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.view.ViewGroup;
//import android.net.Uri;
import android.os.AsyncTask;
//import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jeff Vang on 3/17/2017.
 */

public class leagueModel implements MVPComponents.Model{
    LinkedList<League> listOfLeagues;
    private MVPComponents.Presenter Presenter;
    private String selectedLeague;
    private String selectedTeam;
    private String inputtedScoreA;
    private String inputtedScoreB;
    private League mLeague;         //the current league we can pull info from
    private Team mTeam;         //The current team we can pull from
    private  ArrayList<String> stringOfLeagues = new ArrayList<String>();

    public leagueModel(MVPComponents.Presenter Presenter)
    {
        listOfLeagues = new LinkedList<League>();


        //Dummy list of leagues to send to the view for testing purposes DELETE AFTER TEST
        listOfLeagues.add(new League("Sunday 3:30PM FAF"));
        listOfLeagues.add(new League("Sunday 5PM Open"));
        listOfLeagues.add(new League("Sunday 7PM Open"));
        listOfLeagues.add(new League("Monday 5PM Open"));
        listOfLeagues.add(new League("Monday 7PM Mixed"));
        listOfLeagues.add(new League("Tuesday 6PM Mens"));
        listOfLeagues.add(new League("Tuesday 8PM Open"));
        listOfLeagues.add(new League("Wednesday 4PM 2v2"));
        listOfLeagues.add(new League("Wednesday 5PM Mixed"));
        listOfLeagues.add(new League("Wednesday 7PM Womens"));
        listOfLeagues.add(new League("Thursday 4PM Open"));
        listOfLeagues.add(new League("Thursday 6PM Open"));
        listOfLeagues.add(new League("Thursday 8PM"));
        listOfLeagues.add(new League("Friday 5:30PM Open"));

        for (League leagues: listOfLeagues)
        {
            leagues.addTeam("Team 1");
            leagues.addTeam("Team 2");
            leagues.addTeam("Team 3");
            leagues.addTeam("Team 4");

            leagues.setStartDate(21);
            leagues.setStartMonth(0);
            leagues.setStartYear(2016);
            leagues.setStartHour(17);
            leagues.setStartMinute(0);
            leagues.setMaxRounds(3);
            leagues.setNumberOfLanes(8);
            boolean success = leagues.createSchedule();

        }

        //adding these unique teams to the lists to differentiate between the leagues.
        //listOfLeagues.get(0).addTeam("Team Awesome");
        //listOfLeagues.get(1).addTeam("Team Incredible");
        //listOfLeagues.get(2).addTeam("Team Ludacris");

        //--------------------------------------------------------------------------------


        this.Presenter = Presenter;

    }
    @Override
    public LinkedList<Team> getTeams() {
        return mLeague.getTeams();
    }

    @Override
    public Team getSelectedTeam() {
        return mTeam;
    }

    public String[] teamData(String teamName) {
        return new String[0];
    }

    @Override
    public LinkedList<League> getLeagues() {
        return listOfLeagues;
    }

    @Override
    public League getSelectedLeague() { return mLeague;}

    @Override
    public void createLeague(String name)
    {
        Log.d("Model", "Before" + name);
        listOfLeagues.add(new League(name));
        setSelectedLeague(name);
        Log.d("Model", "After" + getSelectedLeague().getLeagueName());
    }

    /**
     * takes in a string of the league selected and sets the League member object to it
     * @param selectedLeague
     */
    public void setSelectedLeague(String selectedLeague) {
        this.selectedLeague = selectedLeague;
        for (League leagues: listOfLeagues) {
            if (leagues.getLeagueName().equals(selectedLeague)) mLeague = leagues;
        }
    }


    public void setSelectedTeam(String selectedTeam) {
        this.selectedTeam = selectedTeam;
        for(Team team: mLeague.getTeams())
        {
            if(team.getTeamName().equals(selectedTeam)) mTeam = team;
        }
    }
    public void setSelectedInputtedScore(String inputtedScoreA, String inputtedScoreB) {
        this.inputtedScoreA = inputtedScoreA;
        this.inputtedScoreB = inputtedScoreB;
    }
    //Processes the data of league name, team name, scoreA, scoreB into back-end data.
    // Updates matchups to include score entered and sends matchup data to the servers on a restful POST call.
    //@param leagueName, teamName, scoreA, scoreB
       public void inputData()
    {
        for(int i=0;i<listOfLeagues.size();++i) {
            if(listOfLeagues.get(i).getLeagueName().equals(selectedLeague)){//if selected league is in the list
               //Log.d("Model: ", "Selected Team is " + selectedTeam);
               listOfLeagues.get(i).inputData(selectedTeam, Integer.parseInt(inputtedScoreA),Integer.parseInt(inputtedScoreB));
            }
        }

        restPOST();
    }

    @Override
    public LinkedList<Match> getSchedule(int week) {
        LinkedList<LinkedList<Match>> fullSchedule = mLeague.getFullSchedule();
        return fullSchedule.get(week);
    }

    @Override
    public Match[][] getScoreboard() {
        return mLeague.getScoreboard();
    }

    @Override
    public void addTeam(String name) {
        mLeague.addTeam(name);
    }

    @Override
    public void addTeam(String name, LinkedList<String> members, String ID) {

        mLeague.addTeam(name);
        setSelectedTeam(name);
        for (String member: members) {
            mTeam.addPlayer(member);
        }
        mTeam.setPassword(ID);
    }

    @Override
    public void uploadLeague(String name) {
        mLeague.setLeagueName(name);
        mLeague.createSchedule();
        restPOST();
    }


    /*Model Connection to Server************************************************/

    public void restGETLeagues() {

        new HTTPAsyncTask().execute("http://localhost:3246/listLeagues", "GET");

    }
    public void restPOST() {

        JSONObject jsonParam = null;
        try {

            //Create JSONObject here
            jsonParam = new JSONObject();
            jsonParam.put("LeagueName",selectedLeague);
            jsonParam.put("TeamName",selectedTeam);
            jsonParam.put("ScoreA",inputtedScoreA);//mTeam.peekMatch().getTeamAScore());
            jsonParam.put("ScoreB",inputtedScoreB);//mTeam.peekMatch().getTeamBScore());

        } catch (JSONException e) {
            e.printStackTrace();
        }
 ///      new HTTPAsyncTask().execute("http://10.0.0.1:3246/Leagues", "POST", jsonParam.toString());
        String displayString = mLeague.createJson();
        System.out.println(displayString);
       //new HTTPAsyncTask().execute("http://ukko.d.umn.edu:3246/Leagues", "POST", displayString);
        new HTTPAsyncTask().execute("http://ukko.d.umn.edu:3246/Leagues", "POST", displayString);

    }
    private class HTTPAsyncTask extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... params) {
/* The String... params argument represents an arbitrary sized
             * array.  In this case, we might have 2 or 3 parameters depending
             * on if the request type is a GET or POST
             *
             * params[0] contains the HOST and port number and route of the URI
             */
            //Log.d("Debug:", "Attempting to connect to: " + params[0]);

            /* Java class to create a network connection to a HTTP
             * Server. InputStreams are used to process the incoming
             * network data.
             */
            HttpURLConnection serverConnection = null;
            InputStream is = null;

            try {
                /* The Java URL class is used to hold the URI */
                URL url = new URL(params[0]);

                /* We can open a connection to this URL now */
                serverConnection = (HttpURLConnection) url.openConnection();

                /* The second parameter, params[1] contains the TYPE of the HTTP
                 * request. It can be GET, POST, PUT or DELETE.
                 */
                serverConnection.setRequestMethod(params[1]);

                /* If the TYPE is POST, PUT or DELETE then we need to take
                 * the third parameter params[2] which contains the JSON data
                 * we need to place in the body of the HTTP message, and write
                 * that JSON data as a string to the network connection to the
                 * HTTP server.
                 */
                if (params[1].equals("POST") ||
                        params[1].equals("PUT") ||
                        params[1].equals("DELETE")) {
                    //Log.d("DEBUG POST/PUT/DELETE:", "In post: params[0]=" + params[0] + ", params[1]=" + params[1] + ", params[2]=" + params[2]);

                    /* Various server parameters need to set on HTTP connections that indicate the type
                     * of data that will be sent. In our case, we are sending JSON as output so need to
                     * set the Content-Type header attribute.
                     */
                    serverConnection.setDoOutput(true);
                    serverConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

                    /* Since params[2] contains the JSON String to send, we must also calculate the
                     * byte length of this data and set the Content-Length header attribute as well.
                     */
                    serverConnection.setRequestProperty("Content-Length", "" +
                            Integer.toString(params[2].toString().getBytes().length));

                    /* Finally, the JSON data can be written out to the server by using
                     * a DataOutputStream that is created with the server's output stream.
                     */
                    DataOutputStream out = new DataOutputStream(serverConnection.getOutputStream());
                    /* Write the json string data to the network */
                    out.writeBytes(params[2].toString());

                    /* flush and close the output stream buffer */
                    out.flush();
                    out.close();
                }

                /* ************************
                 * HTTP RESPONSE Section
                 * All requests are followed by a response with HTTP
                 *
                 * Get the response code and determine what to do.
                 */
                int responseCode = serverConnection.getResponseCode();

                //Log.d("Debug: ", "HTTP Response Code : " + responseCode);

                /* Get the input stream (what's coming from our server to the Android client)
                 * process the JSON data that's contained with it.
                 */
                is = serverConnection.getInputStream();

                /* This implementation is built so that ALL Responses send back a JSON data, as either
                 * the data you want from a GET Request or as confirmation of receiving the data
                 * on a POST, PUT, or DELETE Request.
                 */
                StringBuilder sb = new StringBuilder();
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                /* At this point, the StringBuilder sb contains all the data that was in the
                 * body of the Response. Since we expect JSON to be in this, the string hopefully
                 * contains valid JSON data.  We need to return this string out of this
                 * function and the onPostExecute function will process it.
                 */
                System.out.println(sb.toString());
                return sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                serverConnection.disconnect();

            }

            /* If you receive this statement, you know there is an error */
            return "Should not get to this if the data has been sent/received correctly!";
        }

        /**
         *
         * @param result the result from the query
         */
        protected void onPostExecute(String result) {

            /* Take the result (a String) returned from the doInBackground function and
             * convert it to a JSONObject to test that it was a valid JSON data format.
             *
             * Then, simply set the text in the view to be the JSON Object as a
             * simple means to show the output.
             */

            try {
                JSONObject jsonData = new JSONObject( result );
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

}
