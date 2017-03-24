package edu.umn.d.cs4531.leaguemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import static android.R.attr.id;

public class AddScoreActivity extends AppCompatActivity implements MVPComponents.View {

    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) message = extras.getString("edu.umn.d.cs4531.leaguemanager.MESSAGE");
        else message = "fuck";
        Log.d("SCORE ACTIVITY", "This is what we got" + message);
        TextView textview = (TextView) findViewById(R.id.Team1Name);
        textview.setText(message);

    }
}
