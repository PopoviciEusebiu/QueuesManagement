package Logical;

import Model.Server;
import Model.Task;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeStrategy implements Strategy{

    @Override
    public void addTask(List<Server> servers, Task t) {
//        AtomicInteger minim=new AtomicInteger(10000);
//        Server s=new Server();
//        for(Server i: servers )
//        {
//            AtomicInteger x=i.getWaitingPeriod();
//            if(x.get()<minim.get())
//            {
//                minim.set(x.get());
//                s=i;
//            }
//        }
//
//        s.addTask(t);
        servers.get(0).addTask(t);
    }
}
