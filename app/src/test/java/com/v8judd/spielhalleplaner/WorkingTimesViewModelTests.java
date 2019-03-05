package com.v8judd.spielhalleplaner;

import org.junit.Test;

import java.util.GregorianCalendar;

import static java.util.Calendar.NOVEMBER;
import static java.util.Calendar.OCTOBER;
import static org.junit.Assert.assertEquals;

public class WorkingTimesViewModelTests {

    @Test
    public void selectWeek_shouldCalculateDatesOfWeekdaysFromArbitraryDate() {

        WorkingTimesViewModel vm = new WorkingTimesViewModel(null);
        vm.SelectWeek(new GregorianCalendar(2018, OCTOBER, 24));

        assertEquals(new GregorianCalendar(2018, OCTOBER, 22).getTime(), vm.WorkingDays[WorkingTimesViewModel.MONDAY]);
        assertEquals(new GregorianCalendar(2018, OCTOBER, 23).getTime(), vm.WorkingDays[WorkingTimesViewModel.TUESDAY]);
        assertEquals(new GregorianCalendar(2018, OCTOBER, 24).getTime(), vm.WorkingDays[WorkingTimesViewModel.WEDNESDAY]);
        assertEquals(new GregorianCalendar(2018, OCTOBER, 25).getTime(), vm.WorkingDays[WorkingTimesViewModel.THURSDAY]);
        assertEquals(new GregorianCalendar(2018, OCTOBER, 26).getTime(), vm.WorkingDays[WorkingTimesViewModel.FRIDAY]);
        assertEquals(new GregorianCalendar(2018, OCTOBER, 27).getTime(), vm.WorkingDays[WorkingTimesViewModel.SATURDAY]);
    }

    @Test
    public void selectWeek_shouldCalculateCorrectDateAcrossMonthBoundary() {

        WorkingTimesViewModel vm = new WorkingTimesViewModel(null);
        vm.SelectWeek(new GregorianCalendar(2018, OCTOBER, 31));

        assertEquals(new GregorianCalendar(2018, OCTOBER, 29).getTime(), vm.WorkingDays[WorkingTimesViewModel.MONDAY]);
        assertEquals(new GregorianCalendar(2018, OCTOBER, 30).getTime(), vm.WorkingDays[WorkingTimesViewModel.TUESDAY]);
        assertEquals(new GregorianCalendar(2018, OCTOBER, 31).getTime(), vm.WorkingDays[WorkingTimesViewModel.WEDNESDAY]);
        assertEquals(new GregorianCalendar(2018, NOVEMBER, 1).getTime(), vm.WorkingDays[WorkingTimesViewModel.THURSDAY]);
        assertEquals(new GregorianCalendar(2018, NOVEMBER, 2).getTime(), vm.WorkingDays[WorkingTimesViewModel.FRIDAY]);
        assertEquals(new GregorianCalendar(2018, NOVEMBER, 3).getTime(), vm.WorkingDays[WorkingTimesViewModel.SATURDAY]);
    }

    @Test
    public void selectWeek_verifyCorrectWorkingDayText() {

        WorkingTimesViewModel vm = new WorkingTimesViewModel(null);
        vm.SelectWeek(new GregorianCalendar(2018, OCTOBER, 31));

        assertEquals("Montag\n29.10.", vm.MondayDateText.get());
        assertEquals("Dienstag\n30.10.", vm.TuesdayDateText.get());
        assertEquals("Mittwoch\n31.10.", vm.WednesdayDateText.get());
        assertEquals("Donnerstag\n01.11.", vm.ThursdayDateText.get());
        assertEquals("Freitag\n02.11.", vm.FridayDateText.get());
        assertEquals("Samstag\n03.11.", vm.SaturdayDateText.get());
    }

    @Test
    public void selectWeek_shouldSetDateSelectionButtonText() {

        WorkingTimesViewModel vm = new WorkingTimesViewModel(null);
        vm.SelectWeek(new GregorianCalendar(2018, OCTOBER, 31));

        assertEquals("KW: 44 (29.10. - 03.11.)", vm.btnSelectDateText.get());
    }

}