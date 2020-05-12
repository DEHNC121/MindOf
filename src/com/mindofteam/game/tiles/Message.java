package com.mindofteam.game.tiles;

import com.mindofteam.game.GamePanel;
import com.mindofteam.game.graphics.Font;
import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.states.PlayState;
import com.mindofteam.game.util.Vector2f;

import java.awt.*;

public class Message {
    private static String[] tab={
            "This is the game called MindOf",
            "This is the game called MindOf",
            "You can run by pressing 'k'",
            "You can run by pressing 'k'"
    };

    private static Font font= new Font ("font/font.png", 10, 10);

    public static void print(int i){
        if(i<tab.length) PlayState.notify(tab[i]);
    }
}
