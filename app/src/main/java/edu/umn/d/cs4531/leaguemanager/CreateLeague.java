package edu.umn.d.cs4531.leaguemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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


}
