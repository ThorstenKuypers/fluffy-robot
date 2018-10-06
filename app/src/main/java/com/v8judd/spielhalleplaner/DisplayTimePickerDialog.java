package com.v8judd.spielhalleplaner;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

public class DisplayTimePickerDialog implements View.OnClickListener {

    private Context _ctx;

    DisplayTimePickerDialog(Context ctx) {
        _ctx = ctx;
    }

    @Override
    public void onClick(View v) {
        Dialog dlg = new TimePickerDialog(_ctx, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(_ctx, "selected time: " + hourOfDay + ":" + minute, Toast.LENGTH_LONG).show();
            }
        }, 0, 0, true);

        dlg.show();
    }
}
