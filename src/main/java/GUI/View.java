package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private JFrame frame;
    private JTextField noQ;
    private JTextField timeL;
    private JTextField maxSrv;
    private JTextField maxArv;
    private JTextField minSrv;
    private JTextField minArv;
    private JTextField noC;
    private JLabel q;
    private JLabel maxA;
    private JLabel maxS;
    private JLabel minA;
    private JLabel minS;
    private JLabel tL;
    private JLabel c;
    private JButton start;

    public View()
    {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(1,1, 1));
        frame.setBounds(100, 100, 515, 364);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        q = new JLabel("No queues:");
        q.setForeground(Color.WHITE);
        q.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        q.setBounds(54, 26, 80, 25);
        frame.getContentPane().add(q);

        maxS = new JLabel("MaxServiceTime:");
        maxS.setForeground(Color.WHITE);
        maxS.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        maxS.setBounds(24, 73, 110, 14);
        frame.getContentPane().add(maxS);

        c = new JLabel("No clients:");
        c.setForeground(Color.WHITE);
        c.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        c.setBounds(292, 29, 80, 19);
        frame.getContentPane().add(c);

        minS = new JLabel("MinServiceTime:");
        minS.setForeground(Color.WHITE);
        minS.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        minS.setBounds(262, 73, 110, 14);
        frame.getContentPane().add(minS);

        maxA = new JLabel("MaxArrivalTime:");
        maxA.setForeground(Color.WHITE);
        maxA.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        maxA.setBounds(33, 112, 101, 25);
        frame.getContentPane().add(maxA);

        minA = new JLabel("MinArrivalTime:");
        minA.setForeground(Color.WHITE);
        minA.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        minA.setBounds(262, 112, 129, 25);
        frame.getContentPane().add(minA);

        tL = new JLabel("TimeLimit:");
        tL.setForeground(Color.WHITE);
        tL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tL.setBounds(60, 160, 74, 14);
        frame.getContentPane().add(tL);

        noQ = new JTextField();
        noQ.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        noQ.setBounds(144, 29, 86, 20);
        frame.getContentPane().add(noQ);

        timeL = new JTextField();
        timeL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        timeL.setBounds(144, 158, 86, 20);
        frame.getContentPane().add(timeL);

        maxSrv = new JTextField();
        maxSrv.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        maxSrv.setBounds(144, 71, 86, 20);
        frame.getContentPane().add(maxSrv);

        maxArv = new JTextField();
        maxArv.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        maxArv.setBounds(144, 115, 86, 20);
        frame.getContentPane().add(maxArv);

        minSrv = new JTextField();
        minSrv.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        minSrv.setBounds(376, 71, 86, 20);
        frame.getContentPane().add(minSrv);

        minArv = new JTextField();
        minArv.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        minArv.setBounds(376, 115, 86, 20);
        frame.getContentPane().add(minArv);

        noC = new JTextField();
        noC.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        noC.setBounds(376, 29, 86, 20);
        frame.getContentPane().add(noC);

        start = new JButton("START");
        start.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        start.setBounds(198, 236, 117, 34);
        frame.getContentPane().add(start);

        frame.setVisible(true);

    }
    public void startAction(ActionListener start)
    {
        this.start.addActionListener(start);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextField getNoQ() {
        return noQ;
    }

    public JTextField getTimeL() {
        return timeL;
    }

    public JTextField getMaxSrv() {
        return maxSrv;
    }

    public JTextField getMaxArv() {
        return maxArv;
    }

    public JTextField getMinSrv() {
        return minSrv;
    }

    public JTextField getMinArv() {
        return minArv;
    }

    public JTextField getNoC() {
        return noC;
    }

    public JButton getStart() {
        return start;
    }
}
