package edu.umn.d.cs4531.leaguemanager;

import android.app.Activity;
import android.content.Intent;
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

    }
    void setupPresenter() {
        mPresenter = new leaguePresenter(this);
    }
    public void sendData(View view) {
        EditText scoreA = (EditText) findViewById(R.id.enterTeam1Score);
        EditText scoreB = (EditText) findViewById(R.id.enterTeam2Score);
        String returnData = scoreA.getText().toString() + " " + scoreB.getText().toString();
        intent.putExtra("edu.umn.d.cs4531.leaguemanager.MESSAGE", returnData);
        Log.d("AddScore: ", returnData);
        setResult(Activity.RESULT_OK, intent);
        //mPresenter.scoreInput(scoreA.getText().toString(), scoreB.getText().toString());
        //mPresenter.run();
        finish();
    }
}
