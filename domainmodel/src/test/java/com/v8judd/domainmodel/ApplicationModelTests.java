package com.v8judd.domainmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

interface IWorkerRepository {
    Worker findByName(String name);
}

class WorkConstraint {

    public WorkConstraint(Date begin, Date end) {
        Begin = begin;
        End = end;
    }

    public Date Begin;
    public Date End;
}

class Worker {

    private String _name;
    private Vector<WorkConstraint> _workConstraints;

    public Worker(String name) {

        _name = name;
        _workConstraints = new Vector<>();
    }


    public String getName() {
        return _name;
    }

    public void setTimeConstraint(Date begin, Date end) {

        _workConstraints.add(new WorkConstraint(begin, end));
    }

    public WorkConstraint[] getAllWorkConstraints() {
        return _workConstraints.toArray(new WorkConstraint[_workConstraints.size()]);
    }

    public WorkConstraint getWorkConstraint(int index) {

        if (index > -1 && index < _workConstraints.size()) {
            return _workConstraints.get(index);
        }

        return null;
    }
}

class ApplicationService {

    private final IWorkerRepository _repo;

    public ApplicationService(IWorkerRepository repo) {
        _repo = repo;
    }

    public void setTimeConstraintForWorker(String forName, Date begin, Date end) {

        Worker worker = _repo.findByName(forName);
        if (worker != null) {

            worker.setTimeConstraint(begin, end);
        }
    }

    public void updateTimeConstraintForWorker(String forName, int index, Date begin, Date end) {

        Worker worker = _repo.findByName(forName);
        if (worker != null) {

            WorkConstraint wc = worker.getWorkConstraint(index);
            if (wc != null) {
                wc.Begin = begin;
                wc.End = end;
            }
        }
    }
}

public class ApplicationModelTests {

    IWorkerRepository repo;
    ApplicationService service;
    String testWorkerName = "Claudia";
    Worker testWorker;

    @Before
    public void SetUp() {

        testWorker = new Worker(testWorkerName);
        repo = mock(IWorkerRepository.class);
        when(repo.findByName(testWorkerName)).thenReturn(testWorker);

        service = new ApplicationService(repo);
    }

    @After
    public void TearDown() {
        service = null;
        repo = null;
    }

    public void SetConstraintTime(GregorianCalendar cal, int hour, int minute) {
        cal.set(2018, Calendar.NOVEMBER, 10, hour, minute);
    }

    @Test
    public void shouldSetTimeConstraintForWorker() {

        GregorianCalendar cal = new GregorianCalendar();
        SetConstraintTime(cal, 9, 0);
        Date begin = cal.getTime();
        SetConstraintTime(cal, 14, 0);
        Date end = cal.getTime();

        service.setTimeConstraintForWorker(testWorkerName, begin, end);

        Worker res = repo.findByName(testWorkerName);
        assertThat(res.getWorkConstraint(0).Begin.getTime(), is(equalTo(begin.getTime())));
        assertThat(res.getWorkConstraint(0).End.getTime(), is(equalTo(end.getTime())));
    }

    @Test
    public void shouldUpdateFirstExistingTimeConstraint() {
        GregorianCalendar cal = new GregorianCalendar();
        SetConstraintTime(cal, 9, 0);
        Date begin = cal.getTime();
        SetConstraintTime(cal, 14, 0);
        Date end = cal.getTime();
        service.setTimeConstraintForWorker(testWorkerName, begin, end);

        SetConstraintTime(cal, 10, 15);
        Date updatedBegin = cal.getTime();
        SetConstraintTime(cal, 11, 45);
        Date updatedEnd = cal.getTime();
        service.updateTimeConstraintForWorker(testWorkerName, 0, updatedBegin, updatedEnd);

        Worker res = repo.findByName(testWorkerName);
        assertThat(res.getWorkConstraint(0).Begin.getTime(), is(equalTo(updatedBegin.getTime())));
        assertThat(res.getWorkConstraint(0).End.getTime(), is(equalTo(updatedEnd.getTime())));
    }
}