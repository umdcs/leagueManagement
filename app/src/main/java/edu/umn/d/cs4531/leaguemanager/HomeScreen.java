package edu.umn.d.cs4531.leaguemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements MVPComponents.View {

    private MVPComponents.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        setupPresenter();
        setupSpinner();
    }

    /**
     * Creates a presenter object instance.
     */
    void setupPresenter() {
        mPresenter = new leaguePresenter(this);
    }

    void setupSpinner() {
        //RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.activity_home_screen);
        //ArrayList<String> leagueArray = mPresenter.getLeagues();
        ArrayList<String> leagueArray = new ArrayList<String>();
        leagueArray.add("League1");
        leagueArray.add("League2");
        leagueArray.add("League3");

        Spinner spinner = (Spinner) findViewById(R.id.leagueSpinner);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, leagueArray));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedString = parent.getItemAtPosition(position).toString();
                Log.d("Main", "the item selected is: " + selectedString);
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
