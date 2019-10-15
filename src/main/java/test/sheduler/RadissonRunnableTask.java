package test.sheduler;

import java.io.Serializable;

public class RadissonRunnableTask implements Runnable, Serializable {

    private String task;

    public RadissonRunnableTask(String task) {
        this.task = task;
    }

    public void run() {
        System.out.println("Runned task 1");
    }


}
