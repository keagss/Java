package com.keagan.Tools.getAccountById;

import org.json.simple.JSONObject;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Created by kbc on 26/06/2017.
 */
public class GUI  {
    JFrame f;
    GUI() throws IOException{
        f=new JFrame();//creating instance of JFrame

        final getAccountById account = new getAccountById();
        final saveAccount save = new saveAccount();

        //initiating JObjects
        final JTextField Id = new JTextField();
        final JTextField name = new JTextField();
        final JTextField course = new JTextField();

        JLabel idLabel = new JLabel();
        JLabel nameLabel = new JLabel();
        JLabel courseLabel = new JLabel();

        JButton saveAccount = new JButton();

        //positioning/sizing text boxes
        Id.setBounds(170,30,130, 40);
        name.setBounds(170,90,130, 40);
        course.setBounds(170,150,130, 40);

        //positioning/sizing labels
        idLabel.setBounds(60,30,80, 40);
        nameLabel.setBounds(60,90,80, 40);
        courseLabel.setBounds(60,150,80, 40);

        saveAccount.setBounds(60,400,80, 40);

        ArrayList accountArray = new ArrayList();
        accountArray=account.array();


        //setting text boxes to account details
        Id.setText(accountArray.get(0).toString());
        name.setText(accountArray.get(1).toString());
        course.setText(accountArray.get(2).toString());

        //adding text to labels
        idLabel.setText("ID");
        nameLabel.setText("Name");
        courseLabel.setText("Course");
        saveAccount.setText("Post");


        //adding button in JFrame
        f.add(Id);
        f.add(name);
        f.add(course);
        f.add(idLabel);
        f.add(nameLabel);
        f.add(courseLabel);
        f.add(saveAccount);

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible


        f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //saveAccount.
        saveAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    save.saveAccount1(Id.getText(),name.getText(),course.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


    }


    public static void main(String[] args) throws IOException {
        new GUI();

    }
}
