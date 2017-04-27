package edu.umn.d.cs4531.leaguemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;

/**
 * Created by hunter on 4/20/17.
 */

public class CreateTeam extends AppCompatActivity implements MVPComponents.View {

    private MVPComponents.Presenter mPresenter;
    private String teamName;
    private LinkedList<String> members;
    private String ID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_create_team);

        mPresenter = ScheduleList.getInstance().getPresenter();
        members = new LinkedList<String>();
    }

    public void addTeamMember(View view) {
        EditText member = (EditText) findViewById(R.id.teamMemberET);
        members.add(member.getText().toString());
        Toast.makeText(getBaseContext(), "Added member " + member.getText().toString(), Toast.LENGTH_LONG).show();
        member.setText("");
    }

    public void finalizeTeam(View view) {
        EditText teamNameET = (EditText) findViewById(R.id.teamNameET);
        teamName = teamNameET.getText().toString();
        EditText teamIDET = (EditText) findViewById(R.id.teamIDET);
        ID = teamIDET.getText().toString();

        mPresenter.addTeam(teamName, members, ID);

        finish();
    }
}
