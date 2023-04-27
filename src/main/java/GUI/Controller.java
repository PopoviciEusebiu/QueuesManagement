package GUI;

import Logical.SimulationManager;
import Model.Server;
import Model.Task;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {
    public View view;
    public View1 view1;

    private PrintWriter file;
    private Thread t;
    private float avgSvTime;
    private float avgWaitingTime;

    public Controller(View view,View1 view1) {
        this.view = view;
        this.view1 = view1;
        try {
            this.file = new PrintWriter("a.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        view.startAction(new startActiune());
        view1.stopAction(new stopActiune());
        view1.backAction(new backActiune());
    }

    public class startActiune implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == view.getStart()) {
                try {
                    SimulationManager s = new SimulationManager(Integer.parseInt(view.getNoQ().getText()),
                            Integer.parseInt(view.getTimeL().getText()),
                            Integer.parseInt(view.getMaxSrv().getText()),
                            Integer.parseInt(view.getMinSrv().getText()),
                            Integer.parseInt(view.getMaxArv().getText()),
                            Integer.parseInt(view.getMinArv().getText()),
                            Integer.parseInt(view.getNoC().getText()),Controller.this);
                    avgSvTime=s.getAverageServiceTime();
                    t = new Thread(s);
                    t.start();
                    view.getFrame().setVisible(false);
                    view1.getFrame().setVisible(true);

                }catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(new JButton("OK!"), "Expresia nu este valida");
                }
            }
        }
    }

    public class stopActiune implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (t != null && t.isAlive()) {
                t.stop();
            }
        }
    }
    public class backActiune implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view1.getFrame().setVisible(false);
            view.getFrame().setVisible(true);
            view.getMaxArv().setText("");
            view.getMinArv().setText("");
            view.getMaxSrv().setText("");
            view.getMinSrv().setText("");
            view.getNoQ().setText("");
            view.getNoC().setText("");
            view.getTimeL().setText("");
        }
    }
    public void printSim(List<Task> gtasks,List<Server> servers,int time)
    {
        view1.setTextArea("Time:" + time + '\n');
        view1.setTextArea("Waiting:" + gtasks.toString()+'\n');
        file.println("Time:" + time);
        file.println("Waiting: " + gtasks.toString());
        for (Server server : servers) {
            view1.setTextArea("Queue: ");
            file.print("Queue: ");
            Task[] tasks = server.getTasks();
            if (tasks.length > 0) {
                for (Task t : tasks) {
                    if(t.getServiceTime()==1)
                    {
                        avgWaitingTime+=time-t.getArrivalTime()+1;
                    }
                    view1.setTextArea(t + "  ");
                    file.print(t + "  ");
                }
                Task firstTask = tasks[0];
                firstTask.setServiceTime(firstTask.getServiceTime() - 1);
            } else {
                view1.setTextArea("closed");
                file.print("closed");
            }
            view1.setTextArea("\n");
            file.println();
        }
        view1.setTextArea("\n");
        file.println();
        file.flush();

    }

    public void printFinish(int peak,int max,float waiting)
    {
        DecimalFormat d=new DecimalFormat("0.00");
        file.println("Simulation is done!");
        file.println("Average Service Time: " + d.format(avgSvTime));
        file.println("Average Waiting Time: " + d.format(waiting));
        file.println("Peak hour is " + peak + " with " + max + " clients in queue");

    }
    public void closeFile()
    {
        file.close();
    }
    public float getAvgSvTime()
    {
        return avgSvTime;
    }

    public float getAvgWaitingTime() {
        return avgWaitingTime;
    }
}


