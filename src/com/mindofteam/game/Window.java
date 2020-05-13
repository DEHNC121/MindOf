package com.mindofteam.game;

import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame
{
    public Window ()
    {
        Dimension ekran=Toolkit.getDefaultToolkit().getScreenSize();
        setTitle ("MindOf");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane (new GamePanel ((int)ekran.getWidth(), (int)ekran.getHeight()));
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
