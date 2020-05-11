package com.mindofteam.game;

import javax.swing.JFrame;

public class Window extends JFrame
{
    public Window ()
    {
        setTitle ("MindOf");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane (new GamePanel (680, 420));
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
