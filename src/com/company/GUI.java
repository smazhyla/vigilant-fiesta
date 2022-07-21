package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI implements ActionListener {
    private static JFrame frame;
    private static  JButton outputLable;
    private static JPanel panel;
    private static Back backend;
    private static JButton button;
    private static JScrollPane scrollPane;
    public static void main(String args[]) throws Exception {
         frame = new JFrame("MC");
         panel = new JPanel();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setVisible(true);
            frame.add(panel);
            panel.setLayout(null);
            backend = new Back();
            output();




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!e.getActionCommand().equals("Get back")){
        String filepath=e.getActionCommand();
        backend.changePath(filepath);


        panel.removeAll();
        File file = new File(backend.getPath());
        if (file.isDirectory()){
        try {
            output();
        } catch (Exception ex) {
            throw new RuntimeException(ex);

          }
        }else {
            backend.openFile();
            try {
                backend.getBack();
                output();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        }
        else{
            panel.removeAll();
            try {
                backend.getBack();
                output();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }

    }
    public static void output() throws Exception {
        int y=25;
        button = new JButton();
        button.setText("Get back");
        button.setBounds(10,10,200,24);
        button.addActionListener(new GUI());
        panel.add(button);
        for (int i = 0; i < backend.runCommand().size(); i++) {
            outputLable  = new JButton();
            y = y + 20;
            outputLable.setText(backend.runCommand().get(i));
            outputLable.setName(backend.runCommand().get(i));
            outputLable.addActionListener(new GUI());
            outputLable.setBounds(10, y, 200, 24);
            panel.add(outputLable);
        }

        panel.updateUI();

    }
}