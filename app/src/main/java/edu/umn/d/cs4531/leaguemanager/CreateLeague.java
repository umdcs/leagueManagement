package edu.umn.d.cs4531.leaguemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by hunter on 4/19/17.
 */

public class CreateLeague extends AppCompatActivity {

    private MVPComponents.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_league);

        mPresenter = ScheduleList.getInstance().getPresenter();
    }

    private void addTeam(View view) {
        Intent teamIntent = new Intent(this, createTeam.class);
        startActivity(teamIntent);
    }

    private void setTime(View view) {
        //fragments for time picker and date picker

    }

}
