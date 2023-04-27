package Logical;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNrServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNrServers,int maxTasksPerServer)
    {
        servers=new ArrayList<>();
        //this.maxNrServers=maxNrServers;
        //this.maxTasksPerServer=maxTasksPerServer;
        this.strategy=new TimeStrategy();
        for(int i=0;i<maxNrServers;i++)
        {
            Server s=new Server();
            s.initializeQueue(maxTasksPerServer);
            servers.add(s);
            Thread t = new Thread(s);
            t.start();

        }
    }
    public void dispatchTask(Task t)
    {
        Collections.sort(servers);
        strategy.addTask(servers,t);
    }

    public List<Server> getServers()
    {
        return servers;
    }

}
