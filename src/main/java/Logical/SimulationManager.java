package Logical;

import GUI.Controller;
import GUI.View;
import GUI.View1;
import Model.Server;
import Model.Task;

import java.text.DecimalFormat;
import java.util.*;

public class SimulationManager implements Runnable {
    public int timeLimit ;
    public int maxArTime ;
    public int minArTime ;
    public int maxSrvTime ;
    public int minSrvTime ;
    public int Q ;
    public int N ;
    private List<Task> gTasks;
    private Scheduler sch;
    private Controller controller;


    public SimulationManager(int Q, int timeLimit, int maxSrvTime, int minSrvTime, int maxArTime, int minArTime, int N, Controller controller) {
        this.timeLimit=timeLimit;
        this.maxArTime=maxArTime;
        this.minArTime=minArTime;
        this.maxSrvTime=maxSrvTime;
        this.minSrvTime=minSrvTime;
        this.Q=Q;
        this.N=N;
        this.controller=controller;
        gTasks = genereateNRandomTasks();
        sch = new Scheduler(Q, N);

    }

    public List<Task> genereateNRandomTasks() {
        Random r = new Random();
        int arTime, srvTime;
        List<Task> gTasks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arTime = r.nextInt(maxArTime - minArTime + 1) + minArTime;
                srvTime = r.nextInt(maxSrvTime - minSrvTime + 1) + minSrvTime;

            Task t = new Task(i + 1, arTime, srvTime);
            gTasks.add(t);
        }
        Collections.sort(gTasks);

        return gTasks;
    }
    public float getAverageServiceTime()
    {
        float sum=0;
        for(Task i : gTasks)
        {
            sum+=i.getServiceTime();
        }
        float rez=sum/gTasks.size();
        return rez;
    }
    public int getPeakHour()
    {
        int s=0;
        for(Server server:sch.getServers())
        {
            Task[] t=server.getTasks();
            s+=t.length;
        }
        return s;
    }


    @Override
    public void run() {
        int curr = 0;
        int maxNo=0;
        int peakHour=0;
        float avgWaitT=0;
        int size=gTasks.size();
        while (curr < timeLimit) {
            List<Task> arr = new ArrayList<>();
            for (Task i : gTasks) {
                if (i.getArrivalTime() == curr) {
                    arr.add(i);
                }
            }
            for (Task i : arr) {
                sch.dispatchTask(i);
                gTasks.remove(i);
            }

            if(maxNo<getPeakHour())
            {
                maxNo=getPeakHour();
                peakHour=curr;
            }
            controller.view1.clearTextArea();
            controller.printSim(gTasks,sch.getServers(),curr);

            curr++;
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        avgWaitT= controller.getAvgWaitingTime();
        avgWaitT=avgWaitT/(float)size;
        DecimalFormat d=new DecimalFormat("0.00");
        controller.view1.clearTextArea();
        controller.view1.setTextArea("Simulation is done!\n");
        controller.view1.setTextArea("Average Service Time: " + d.format(controller.getAvgSvTime()) + " \n");
        controller.view1.setTextArea("Average Waitnig Time: " + avgWaitT + "\n");
        controller.view1.setTextArea("Peak hour is " + peakHour + " with " + maxNo + " clients in queue");
        controller.printFinish(peakHour,maxNo,avgWaitT);
        controller.closeFile();
    }


    public static void main(String[] args) {
        View v=new View();
        View1 v1=new View1();
        new Controller(v,v1);
    }
}



