package service.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Van Phan <vanthuyphan@gmail.com> on 5/15/18.
 */
public abstract class TimerTask<T> implements Runnable {
    private LocalDate executeDate;
    private LocalTime executeTime;
    private boolean isDailyTask;


    public TimerTask(boolean isDailyTask) {
        this.isDailyTask = isDailyTask;
    }

    public LocalTime getExecuteTime() {
        return executeTime;
    }

    public TimerTask setExecuteTime(LocalTime executeTime) {
        this.executeTime = executeTime;
        return this;
    }

    public LocalDate getExecuteDate() {
        return executeDate;
    }

    public TimerTask setExecuteDate(LocalDate executeDate) {
        this.executeDate = executeDate;
        return this;
    }

    public boolean isDailyTask() {
        return isDailyTask;
    }

    public TimerTask setDailyTask(boolean dailyTask) {
        isDailyTask = dailyTask;
        return this;
    }

    @Override
    public void run() {
        _run();
    }

    protected void _run() {
    }

}
