package com.mindofteam.game.states;

import com.mindofteam.game.GamePanel;
import com.mindofteam.game.entity.Player;
import com.mindofteam.game.graphics.Font;
import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.tiles.TileManager;
import com.mindofteam.game.util.KeyHandler;
import com.mindofteam.game.util.MouseHandler;
import com.mindofteam.game.util.Vector2f;

import javax.swing.*;
import java.awt.*;

public class PauseState extends GameState
{
    private static GameStateManager gsm;
    private Font font;
    private int k;
    private boolean one_down;
    private boolean one_up;
    private static int ILO=2;
    private String[] Mane_pause;
    private TileManager tm;
    private Vector2f map;
    private static Player player;

    public PauseState(GameStateManager gsm, Vector2f map, TileManager tm, Player p) {
        super(gsm);
        this.gsm=gsm;
        this.font = new Font ("font/font.png", 10, 10);
        this.map=map;
        this.k=ILO-1;
        this.one_down=true;
        this.one_up=true;
        this.tm=tm;
        player=p;
        this.Mane_pause=new String[ILO];
        this.Mane_pause[0]=" RESUME";
        this.Mane_pause[1]=" RESTART";
    }
    public int pause;
    @Override
    public void update()
    {
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if(key.down.down && one_down){
            k=(k-1)%ILO;
            if (k<0)k*=-1;
            one_down=false;
        }
        else if(key.up.down && one_up){
            k=(k+1)%ILO;
            one_up=false;
        }
        if(!key.down.down && !one_down){
            one_down=true;
        }
        if(!key.up.down && !one_up){
            one_up=true;
        }
        if(key.enter.down){
            if(k==0) unPause();
            if(k==1) restart();
        }
    }
    public void unPause(){
        gsm.set(new PlayState(gsm, map, tm, player));
    }

    public static void restart(){
        gsm.set(new PlayState(gsm));
    }

    @Override
    public void render(Graphics2D g) {
        //tm.render(g);
        Sprite.drawArray(g, font,Mane_pause[0], new Vector2f (GamePanel.width /2-(Mane_pause[0].length()*30) , GamePanel.height/4), 62, 62, 42, 0);
        Sprite.drawArray(g, font, Mane_pause[1], new Vector2f (GamePanel.width /2-(Mane_pause[1].length()*30), GamePanel.height/4+55), 62, 62, 42, 0);

        if(k==0) {
            Sprite.drawArray(g, font,">", new Vector2f (GamePanel.width /2-260 , GamePanel.height/4), 62, 62, 42, 0);

        }
        else if (k==1){
            Sprite.drawArray(g, font,">", new Vector2f (GamePanel.width /2-260 , GamePanel.height/4+55), 62, 62, 42, 0);

        }
        //player.render(g);
    }
}