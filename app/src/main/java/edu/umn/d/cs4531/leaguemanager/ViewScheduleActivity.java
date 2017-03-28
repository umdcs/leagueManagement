package edu.umn.d.cs4531.leaguemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);

        setupList();
    }

    private void setupList() {
        ArrayList<String> scheduleArray = ScheduleList.getInstance().getWeekSchedule();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scheduleArray);

        ListView listView = (ListView) findViewById(R.id.activity_view_schedule);
        listView.setAdapter(adapter);
    }
}
