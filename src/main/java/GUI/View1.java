package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View1 {
    private JFrame frame;
    private JTextArea textArea;
    private JButton stopButton;
    private JButton backButton;

    public View1() {
        frame = new JFrame();
        frame.setBounds(100, 100, 441, 294);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 10, 350, 200);
        frame.getContentPane().add(scrollPane);

        stopButton = new JButton("Stop");
        stopButton.setBounds(150, 250, 117, 34);
        backButton=new JButton("Back");
        backButton.setBounds(150, 250, 117, 34);
        frame.getContentPane().add(stopButton, BorderLayout.SOUTH);
        frame.getContentPane().add(backButton,BorderLayout.NORTH);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setTextArea(String rez) {
        this.textArea.append(rez);
    }
    public void clearTextArea()
    {
        this.textArea.setText("");
    }
    public void stopAction(ActionListener stop)
    {
        this.stopButton.addActionListener(stop);
    }
    public void backAction(ActionListener back)
    {
        this.backButton.addActionListener(back);
    }

}
