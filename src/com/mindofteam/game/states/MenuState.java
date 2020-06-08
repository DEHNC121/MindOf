package com.mindofteam.game.states;

import com.mindofteam.game.GamePanel;
import com.mindofteam.game.entity.Player;
import com.mindofteam.game.graphics.Font;
import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.tiles.TileManager;
import com.mindofteam.game.util.KeyHandler;
import com.mindofteam.game.util.MouseHandler;
import com.mindofteam.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MenuState extends GameState {
    private Sprite background;
    private Font font;
    private int k;
    private boolean one_down;
    private boolean one_up;
    private static ArrayList<String> menu;
    private GameStateManager gsm;
    public static int startX;
    public static int startY;

    public MenuState(GameStateManager gsm, int x, int y) {
        super(gsm);
        this.background=new Sprite("backgrounds/background.png", 2000,1000);
        this.font = new Font ("font/font.png", 10, 10);
        this.k = 0;
        this.one_down = true;
        this.one_up = true;
        this.menu = new ArrayList<String>();
        menu.add("NEW GAME");
        menu.add("EXIT");
        startX=x;
        startY=y;
        this.gsm=gsm;
    }

    @Override
    public void update() {
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if (key.down.down && one_down) {
            k = (k + 1) % menu.size();
            one_down = false;
        } else if (key.up.down && one_up) {
            k = (k - 1) % menu.size();
            if (k < 0) k = menu.size() - 1;
            one_up = false;
        }
        if (!key.down.down && !one_down) {
            one_down = true;
        }
        if (!key.up.down && !one_up) {
            one_up = true;
        }
        if (key.enter.down) {
            if (k == 0) start();
            if (k == 1) exit();
        }
    }

    public void start() {
        gsm.set(new PlayState(gsm));
    }


    public static void settings() {

    }

    public static void exit() {
        System.exit(0);
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(background.getSpriteSheet(),0,0,GamePanel.width,GamePanel.height,null);
        String s="MIND OF";
        Sprite.drawArray(g, font, s, new Vector2f(GamePanel.width / 2 - (s.length() * 25)-20, GamePanel.height / 4-100), 78, 78, 50, 0);
        for (int i = 0; i < menu.size(); i++) {
            Sprite.drawArray(g, font, menu.get(i), new Vector2f(GamePanel.width / 2 - (menu.get(i).length() * 25), GamePanel.height / 4 + 55 * i), 62, 62, 45, 0);
        }
        Sprite.drawArray(g, font, ">", new Vector2f(GamePanel.width / 2 - 260, GamePanel.height / 4 + 55 * k), 62, 62, 45, 0);
    }
}
