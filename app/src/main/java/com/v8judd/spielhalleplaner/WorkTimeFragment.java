package com.v8judd.spielhalleplaner;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.v8judd.spielhalleplaner.databinding.WorkingTimesFragmentBinding;

import java.util.Calendar;

public class WorkTimeFragment extends Fragment {

    //private WorkingTimesViewModel mViewModel;

    private WorkingTimesFragmentBinding _binding;

    public WorkTimeFragment() {

    }
//    public static WorkTimeFragment newInstance() {
//        return new WorkTimeFragment();
//    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.working_times_fragment, container, false);
        _binding = WorkingTimesFragmentBinding.inflate(inflater, container, false);
        _binding.setVm(new String("Monday"));

        //rootView.findViewById(R.id.monday_start_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
        _binding.mondayStartTime.setOnClickListener(new DisplayTimePickerDialog(getContext()));
        rootView.findViewById(R.id.tuesday_start_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
        rootView.findViewById(R.id.wednesday_start_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
        rootView.findViewById(R.id.thursday_start_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
        rootView.findViewById(R.id.friday_start_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
        rootView.findViewById(R.id.saturday_start_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));

        rootView.findViewById(R.id.monday_end_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
        rootView.findViewById(R.id.tuesday_end_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
        rootView.findViewById(R.id.wednesday_end_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
        rootView.findViewById(R.id.thursday_end_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
        rootView.findViewById(R.id.friday_end_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
        rootView.findViewById(R.id.saturday_end_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));

        final Calendar currentDate = Calendar.getInstance();
        rootView.findViewById(R.id.monday_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dlg = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Toast.makeText(getContext(), "week: " + Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) + "+selected date: " + dayOfMonth + "." + month + "." + year, Toast.LENGTH_LONG).show();
                    }
                }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));

                dlg.show();
            }
        });
        return _binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(WorkingTimesViewModel.class);
        // TODO: Use the ViewModel
    }
}

class DisplayTimePickerDialog implements View.OnClickListener {

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
