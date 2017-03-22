package edu.umn.d.cs4531.leaguemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ScoreInput extends AppCompatActivity implements MVPComponents.View {

    private String teamName;
    private String otherTeam;
    private int scoreA;
    private int scoreB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_input);

        
    }
}
