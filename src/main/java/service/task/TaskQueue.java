package service.task;

import utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

/**
 * Created by Van Phan <vanthuyphan@gmail.com> on 5/15/18.
 */
public enum TaskQueue implements Runnable {

    TASK_QUEUE;
    private LinkedList<TimerTask> tasks = new LinkedList<>();

    private TaskQueue() {
        new Runnable() {
            @Override
            public void run() {
                TaskQueue.this.run();
            }
        }.run();
    }

    @Override
    public void run() {
        while (true) {
            try {
            if (tasks.isEmpty()) {
                Thread.sleep(1000);
            } else {
                service.task.TimerTask task = tasks.peekFirst();
                System.out.println("Checking time");
                if (Utils.isTime(task.getExecuteTime())) {
                    new Runnable() {
                        @Override
                        public void run() {
                            task.run();
                        }
                    }.run();
                    tasks.remove(task);
                }
            }
            Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addTask(TimerTask task) {
        System.out.println("Adding tasks");
        tasks.add(task);
        run();
    }

    public void removeTask(TimerTask task) {
        tasks.remove(task);
    }

    public static void main(String[] args) {

        TASK_QUEUE.addTask(new TimerTask(LocalDateTime.of(LocalDate.of(2018, 5, 15), LocalTime.of(12, 41, 10))) {
            @Override
            public void _run() {
                System.out.println("Second");
            }
        });

        TASK_QUEUE.addTask(new TimerTask(LocalDateTime.of(LocalDate.of(2018, 5, 15), LocalTime.of(12, 42, 10))) {
            @Override
            public void _run() {
                System.out.println("First");
            }
        });

    }
}
