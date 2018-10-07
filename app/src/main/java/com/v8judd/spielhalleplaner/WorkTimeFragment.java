package com.v8judd.spielhalleplaner;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.v8judd.spielhalleplaner.databinding.WorkingTimesFragmentBinding;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.v8judd.spielhalleplaner.databinding.WorkingTimesFragmentBinding.inflate;

public class WorkTimeFragment extends Fragment {

    private WorkingTimesViewModel mViewModel;

    private WorkingTimesFragmentBinding _binding;

    public WorkTimeFragment() {

    }
//    public static WorkTimeFragment newInstance() {
//        return new WorkTimeFragment();
//    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = ViewModelProviders.of(this).get(WorkingTimesViewModel.class);

        _binding = inflate(inflater, container, false);
        _binding.setVm(mViewModel);

        //rootView.findViewById(R.id.monday_start_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
        //_binding.mondayStartTime.setOnClickListener(new DisplayTimePickerDialog(getContext()));
//        rootView.findViewById(R.id.tuesday_start_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
//        rootView.findViewById(R.id.wednesday_start_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
//        rootView.findViewById(R.id.thursday_start_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
//        rootView.findViewById(R.id.friday_start_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
//        rootView.findViewById(R.id.saturday_start_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
//
//        rootView.findViewById(R.id.monday_end_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
//        rootView.findViewById(R.id.tuesday_end_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
//        rootView.findViewById(R.id.wednesday_end_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
//        rootView.findViewById(R.id.thursday_end_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
//        rootView.findViewById(R.id.friday_end_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));
//        rootView.findViewById(R.id.saturday_end_time).setOnClickListener(new DisplayTimePickerDialog(getContext()));

        final Calendar currentDate = Calendar.getInstance();
        _binding.btnSelectWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dlg = new DatePickerDialog(getContext());
                dlg.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Calendar cal = new GregorianCalendar();
                        cal.set(year, month, dayOfMonth);

                        mViewModel.SelectWeek(cal);
                    }
                });
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

