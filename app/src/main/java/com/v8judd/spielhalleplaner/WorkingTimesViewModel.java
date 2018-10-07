package com.v8judd.spielhalleplaner;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Date;

public class WorkingTimesViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;

    public WorkingTimesViewModel(Application app) {
        super(app);

        WorkingDays = new Date[6];
    }

    public Date[] WorkingDays;

    public ObservableField<String> MondayDateText = new ObservableField<>();
    public ObservableField<String> TuesdayDateText = new ObservableField<>();
    public ObservableField<String> WednesdayDateText = new ObservableField<>();
    public ObservableField<String> ThursdayDateText = new ObservableField<>();
    public ObservableField<String> FridayDateText = new ObservableField<>();
    public ObservableField<String> SaturdayDateText = new ObservableField<>();
    public ObservableField<String> btnSelectDateText = new ObservableField<>();

    public void SelectWeek(Calendar calendar) {

        int x = Calendar.MONDAY - calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_MONTH, x);
        for (int i = 0; i < 6; i++) {
            WorkingDays[i] = new Date(calendar.getTime().getTime());

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        String f, t;
        calendar.setTime(WorkingDays[MONDAY]);
        MondayDateText.set(String.format("Montag\n%02d.%02d.", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1));
        MondayDateText.notifyChange();
        f = String.format("%02d.%02d.", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1);

        calendar.setTime(WorkingDays[TUESDAY]);
        TuesdayDateText.set(String.format("Dienstag\n%02d.%02d.", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1));
        TuesdayDateText.notifyChange();

        calendar.setTime(WorkingDays[WEDNESDAY]);
        WednesdayDateText.set(String.format("Mittwoch\n%02d.%02d.", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1));
        WednesdayDateText.notifyChange();

        calendar.setTime(WorkingDays[THURSDAY]);
        ThursdayDateText.set(String.format("Donnerstag\n%02d.%02d.", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1));
        ThursdayDateText.notifyChange();

        calendar.setTime(WorkingDays[FRIDAY]);
        FridayDateText.set(String.format("Freitag\n%02d.%02d.", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1));
        FridayDateText.notifyChange();

        calendar.setTime(WorkingDays[SATURDAY]);
        SaturdayDateText.set(String.format("Samstag\n%02d.%02d.", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1));
        SaturdayDateText.notifyChange();
        t = String.format("%02d.%02d.", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1);

        btnSelectDateText.set(String.format("KW: %d (%s - %s)", calendar.get(Calendar.WEEK_OF_YEAR), f, t));
    }
}
