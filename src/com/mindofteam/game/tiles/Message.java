package com.mindofteam.game.tiles;

import com.mindofteam.game.GamePanel;
import com.mindofteam.game.graphics.Font;
import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.states.PlayState;
import com.mindofteam.game.util.Vector2f;

import java.awt.*;

public class Message {
    private static String[] tab={
            "Adam Kabka\n1995-2020",
            "Adam Kabka\n1995-2020",
            "This is the\n game called MindOf",
            "This is the\n game called MindOf",
            "Marta Kabka",
            "Marta Kabka",
            "Danger\nplace",
            "Danger\nplace",
            "You can run by pressing 'K'",
            "You can run by pressing 'K'"
    };

    private static Font font= PlayState.getStaticFont();

    public static void print(int i){
       if(i<tab.length) PlayState.notify(tab[i]);
    }
}
