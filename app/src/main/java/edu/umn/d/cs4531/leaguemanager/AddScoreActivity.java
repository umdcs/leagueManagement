package edu.umn.d.cs4531.leaguemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.R.attr.id;

public class AddScoreActivity extends AppCompatActivity implements MVPComponents.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);

        Intent intent = getIntent();
        String message = intent.getStringExtra(HomeScreen.EXTRA_MESSAGE);
        TextView textview = (TextView) findViewById(R.id.Team1Name);
        textview.setText(message);

    }
}
