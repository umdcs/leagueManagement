package edu.umn.d.cs4531.leaguemanager;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by hunter on 4/19/17.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListenerSet {

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {
        // Use current time
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        //Create new instance of timePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));

    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //Do something with the time chosen by the user
    }
}
