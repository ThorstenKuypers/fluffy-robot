package com.v8judd.spielhalleplaner;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

public class WorkingTimesViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public ObservableField<String> Test = new ObservableField<>("Default Test Text");

    public void t() {
        Test.notifyChange();
    }
}
