package service.task;

import java.time.LocalDateTime;

/**
 * Created by Van Phan <vanthuyphan@gmail.com> on 5/15/18.
 */
public abstract class TimerTask<T> implements Runnable {
    private LocalDateTime executeTime;


    public LocalDateTime getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(LocalDateTime executeTime) {
        this.executeTime = executeTime;
    }

    public TimerTask(LocalDateTime executeTime) {
        this.executeTime = executeTime;
    }

    @Override
    public void run() {
        _run();
    }

    protected void _run() {
    }

}
