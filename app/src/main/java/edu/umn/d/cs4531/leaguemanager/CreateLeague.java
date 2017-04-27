package edu.umn.d.cs4531.leaguemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by hunter on 4/19/17.
 */

public class CreateLeague extends AppCompatActivity {

    private MVPComponents.Presenter mPresenter;
    Calendar cal;
    int hours, minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_league);

        mPresenter = ScheduleList.getInstance().getPresenter();
        cal = new GregorianCalendar();
        Log.d("createLeague", "Before" + "kappa");
        mPresenter.createLeague("newLeague");
    }

    public void addTeam(View view) {
        Intent teamIntent = new Intent(this, CreateTeam.class);
        startActivity(teamIntent);
    }

    public void leagueTime(View view) {
        android.app.DialogFragment timeFrag = new TimePickerFragment();
        timeFrag.show(getFragmentManager(), "tag");
    }

    public void setTime(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public void setDate(int year, int month, int day) {
        cal.set(year, month, day, hours, minutes);
        mPresenter.setLeagueCalendar(cal);
    }

    public void uploadLeague(View view) {
        EditText leagueName = (EditText) findViewById(R.id.leagueNameET);
        EditText roundsET = (EditText) findViewById(R.id.roundsET);
        EditText lanesET = (EditText) findViewById(R.id.lanesET);

        int rounds = 3;
        int lanes = 8;

        try {
            rounds = Integer.parseInt(roundsET.getText().toString());
            lanes = Integer.parseInt(lanesET.getText().toString());
        }
        catch (NumberFormatException ex) {
            Toast.makeText(getBaseContext(), "Using default round/lanes", Toast.LENGTH_LONG).show();
        }
        mPresenter.setRoundsLanes(rounds, lanes);
        mPresenter.uploadLeague(leagueName.getText().toString());
    }
}
