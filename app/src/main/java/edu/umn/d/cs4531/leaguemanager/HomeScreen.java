package edu.umn.d.cs4531.leaguemanager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements MVPComponents.View {

    private MVPComponents.Presenter mPresenter;
    private String leagueSelected;
    private String teamSelected;
    private String returnData;
    private Intent scoreIntent;
    public static final String EXTRA_MESSAGE = "edu.umn.d.cs4531.leaguemanager.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        setupPresenter();
        setupSpinner();
        Toast.makeText(getBaseContext(), returnData, Toast.LENGTH_LONG);
        //Log.d("Home: ", "onCreate" + returnData);
    }

    /**
     * Creates a presenter object instance.
     */
    void setupPresenter() {
        mPresenter = new leaguePresenter(this);
    }

    void setupSpinner() {
        ArrayList<String> leagueArray = mPresenter.getLeagues();

        Spinner spinner = (Spinner) findViewById(R.id.leagueSpinner);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, leagueArray));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedString = parent.getItemAtPosition(position).toString();
                //Log.d("Main", "the item selected is: " + selectedString);
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
                leagueSelected = parent.getItemAtPosition(position).toString();
                mPresenter.leagueInput(leagueSelected);
                setupTeamSpinner();
                //setupSchedule();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setupTeamSpinner() {
        ArrayList<String> teamArray = mPresenter.getTeams();

        Spinner teamSpinner = (Spinner) findViewById(R.id.teamSpinner);
        teamSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, teamArray));
        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
                teamSelected = parent.getItemAtPosition(position).toString();
                mPresenter.teamInput(teamSelected);
                teamSelected = teamSelected + ";" + mPresenter.getOtherTeam(teamSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void addScore(View view) {
        scoreIntent = new Intent(this, AddScoreActivity.class);
        scoreIntent.putExtra(EXTRA_MESSAGE, teamSelected);
        startActivityForResult(scoreIntent, 100);
    }

    public void viewSchedule(View view) {
        Intent scheduleIntent = new Intent(this, ViewScheduleActivity.class);
//        ArrayList<String> arrayList = new ArrayList<String>();
//        arrayList.add("Something");
//        arrayList.add("Something else");
//        arrayList.add("Something so crazy it couldn't possibly fit on one line");
        ScheduleList.getInstance().setScheduleList(mPresenter.getSchedule(0));
        //ScheduleList.getInstance().setScheduleList(arrayList);
        startActivity(scheduleIntent);
    }

    public void goToWebsite(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.duluthcurlingclub.org/"));
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (100): {
                if (resultCode == Activity.RESULT_OK) {
                    returnData = data.getStringExtra("edu.umn.d.cs4531.leaguemanager.MESSAGE");
                    Log.d("Home: ", "we in onActResult" + returnData);
                    //parse the data into three strings
                    String[] splitted = returnData.split(";");
                    Log.d("onActRes: ", "first " + splitted[0] + "second " + splitted[1]);


                    mPresenter.scoreInput(splitted[0], splitted[1]);
                    if (mPresenter.teamIDVerification(splitted[2])) mPresenter.run();
                    else
                        Toast.makeText(getBaseContext(), "The team ID: " + splitted[2] + " Did not match out records", Toast.LENGTH_LONG).show();
                }
                if (resultCode == 101) {
                    Toast.makeText(getBaseContext(), "Please enter a number for the score", Toast.LENGTH_LONG).show();
                    startActivityForResult(scoreIntent, 100);
                }
            }
        }
    }
}
