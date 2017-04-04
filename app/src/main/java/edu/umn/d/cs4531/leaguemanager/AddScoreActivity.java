package edu.umn.d.cs4531.leaguemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.id;

public class AddScoreActivity extends AppCompatActivity implements MVPComponents.View {

    Intent intent;
    private String message;
    private String NO_PREF = "nopref";
    private String teamIDString;
    SharedPreferences sharedPref;
    EditText teamIDET;
    private MVPComponents.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);
        //setupPresenter();
        intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) message = extras.getString("edu.umn.d.cs4531.leaguemanager.MESSAGE");
        else message = "fuck";
        Log.d("SCORE ACTIVITY", "This is what we got" + message);
        TextView textview = (TextView) findViewById(R.id.Team1Name);
        textview.setText(message);
        teamIDET = (EditText) findViewById(R.id.teamID);
        // Shared Preferences
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        teamIDString = sharedPref.getString("TeamIDPref", "InitialString");
        if (!teamIDString.equalsIgnoreCase("InitialString")) {
            teamIDET.setText(teamIDString);
        }

    }
    void setupPresenter() {
        mPresenter = new leaguePresenter(this);
    }
    public void sendData(View view) {
        EditText scoreA = (EditText) findViewById(R.id.enterTeam1Score);
        EditText scoreB = (EditText) findViewById(R.id.enterTeam2Score);
        // Handle ID
        if (!teamIDET.getText().toString().equalsIgnoreCase(teamIDString)) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("TeamIDPref", teamIDET.getText().toString());
            editor.commit();
            Log.d("Shared Pref sendData", "Stored value" + sharedPref.getString("TeamIDPref", "Nothing here"));
        }

        String returnData = scoreA.getText().toString() + ";" + scoreB.getText().toString()
                            + ";" + teamIDET.getText().toString();
        intent.putExtra("edu.umn.d.cs4531.leaguemanager.MESSAGE", returnData);
        Log.d("AddScore: ", returnData);
        setResult(Activity.RESULT_OK, intent);
        //mPresenter.scoreInput(scoreA.getText().toString(), scoreB.getText().toString());
        //mPresenter.run();
        finish();
    }
}
