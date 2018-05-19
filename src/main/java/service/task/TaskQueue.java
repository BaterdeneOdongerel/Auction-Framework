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
                    if (Utils.isTime(task.getExecuteTime())) {
                        ((Runnable) () -> task.run()).run();
                        tasks.remove(task);
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

    /*public static void main(String[] args) {
        TaskQueue queue = TaskQueue.getInstance();
        queue.start();
        queue.addTask(new TimerTask(LocalDateTime.of(LocalDate.of(2018, 5, 16), LocalTime.of(10, 43, 10))) {
            @Override
            public void _run() {
                System.out.println("Second");
            }
        });
        queue.addTask(new TimerTask(LocalDateTime.of(LocalDate.of(2018, 5, 16), LocalTime.of(10, 44, 10))) {
            @Override
            public void _run() {
                System.out.println("First");
            }
        });

    }*/
}
