package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable, Comparable<Server> {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private float avgSvTime;

    public Server() {
        this.waitingPeriod = new AtomicInteger(0);

    }

    public void addTask(Task t) {
        //System.out.println("Added tried by " + Thread.currentThread().getName());

        tasks.add(t);

        waitingPeriod.addAndGet(t.getServiceTime());

    }

    public void run() {
        while (true) {
            Task t;
            try {
                if (!tasks.isEmpty()) {
                    t = tasks.peek();
                    Thread.sleep(1000* t.getServiceTime());
                    tasks.poll();
                    waitingPeriod.addAndGet(-t.getServiceTime());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Task[] getTasks() {
        Task[] v = new Task[tasks.size()];
        tasks.toArray(v);
        return v;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void initializeQueue(int max) {
        this.tasks = new LinkedBlockingQueue<>(max);
    }


    @Override
    public int compareTo(Server o) {
        //return this.getWaitingPeriod().get() - o.getWaitingPeriod().get();
        return Integer.compare(this.getWaitingPeriod().get(),o.getWaitingPeriod().get());
    }

}
