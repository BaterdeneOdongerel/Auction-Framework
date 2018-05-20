package service.task;

import org.omg.PortableInterceptor.INACTIVE;
import utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

/**
 * Created by Van Phan <vanthuyphan@gmail.com> on 5/15/18.
 */
public class TaskQueue extends Thread {


    private static TaskQueue INSTANCE;
    private static Object MUTEX = new Object();
    private LinkedList<TimerTask> tasks = new LinkedList<>();

    private TaskQueue() {
    }

    public static TaskQueue getInstance() {
        synchronized (MUTEX) {
            if (INSTANCE == null) {
                INSTANCE = new TaskQueue();
            }
            return INSTANCE;
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (tasks) {
                    while (tasks.isEmpty()) tasks.wait();
                    service.task.TimerTask task = tasks.peek();
                    boolean timeToExecuteDailyTask = task.isDailyTask() && Utils.isTime(task.getExecuteTime());
                    boolean timeToExecuteNonConcurrentTask = !task.isDailyTask() && Utils.isTime(task.getExecuteTime()) && Utils.isDate(task.getExecuteDate());
                    if (timeToExecuteNonConcurrentTask || timeToExecuteDailyTask) {
                        ((Runnable) () -> task.run()).run();
                        tasks.remove(task);
                    } else {
                        tasks.remove(task);
                        tasks.addLast(task);
                    }

                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Utils.logEvent(e.getMessage());
            }
        }
    }

    public void addTask(TimerTask task) {
        synchronized (tasks) {
            tasks.add(task);
            tasks.notify();
        }
    }

    public void removeTask(TimerTask task) {
        tasks.remove(task);
    }

    public static void main(String[] args) {
        TaskQueue queue = TaskQueue.getInstance();
        queue.start();
        LocalTime executeTime = LocalTime.of(8, 19, 10);
        LocalTime executeTime2 = LocalTime.of(8, 20, 10);
        queue.addTask(new TimerTask(true) {
            @Override
            public void _run() {
                System.out.println("Second");
            }
        }.setExecuteTime(executeTime));
        queue.addTask(new TimerTask(true) {
            @Override
            public void _run() {
                System.out.println("First");
            }
        }.setExecuteTime(executeTime2));

    }
}
