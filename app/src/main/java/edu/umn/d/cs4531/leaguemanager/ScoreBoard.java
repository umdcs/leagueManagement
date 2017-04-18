package edu.umn.d.cs4531.leaguemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        setupScoreboard();
    }

    private void setupScoreboard() {
        String[][] scoreboard = ScheduleList.getInstance().getScoreboard();
        ArrayList<String> listArray = new ArrayList<String>();
        for (int i = 0; i < scoreboard.length; i++) {
            String row = "";
            for (int j = 0; j < scoreboard.length; j++) {
                row += scoreboard[i][j];
            }
            listArray.add(row);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listArray);
        ListView listView = (ListView) findViewById(R.id.activity_score_board);
        listView.setAdapter(adapter);
    }
}
