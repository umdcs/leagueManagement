package edu.umn.d.cs4531.leaguemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        setupScoreboard();
    }

    private void setupScoreboard() {
        String[][] scoreboard = ScheduleList.getInstance().getScoreboard();
        String text = scoreboard.toString();
        TextView tv = (TextView) findViewById(R.id.tvgetit);
        tv.setText(text);
    }
}
