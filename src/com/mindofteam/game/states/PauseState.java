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

public class PauseState extends GameState
{
    private static GameStateManager gsm;
    private static Vector2f v;
    private Font font;
    private int k;
    public static Vector2f map;

    public PauseState(GameStateManager gsm, Vector2f v, Vector2f map) {
        super(gsm);
        this.gsm=gsm;
        this.v=v;
        this.map=map;
        Vector2f.setWorldVar(map.x, map.y);
        this.font = new Font ("font/font.png", 10, 10);
        this.k=0;
    }
    public int pause;
    @Override
    public void update()
    {
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if(key.down.down || key.up.down){
            k=1-k;
            try{
                Thread.sleep(20);
            } catch (Exception e) {}
        }
        if(key.enter.down){
            if(k==0) unPause();
            if(k==1) restart();
        }
    }
    public static void unPause(){
        gsm.set(new PlayState(gsm, v, map));
    }

    public static void restart(){
        gsm.set(new PlayState(gsm));
    }

    @Override
    public void render(Graphics2D g) {
        //tm.render(g);
        if(k==0) {
            Sprite.drawArray(g, font, ">RESUME", new Vector2f (GamePanel.width /2-100, GamePanel.height/2-30), 62, 62, 42, 0);
            Sprite.drawArray(g, font, " RESTART", new Vector2f (GamePanel.width /2-100, GamePanel.height/2+20), 62, 62, 42, 0);
        }
        else{
            Sprite.drawArray(g, font, " RESUME", new Vector2f (GamePanel.width /2-100, GamePanel.height/2-30), 62, 62, 42, 0);
            Sprite.drawArray(g, font, ">RESTART", new Vector2f (GamePanel.width /2-100, GamePanel.height/2+20), 62, 62, 42, 0);
        }
        //player.render(g);
    }
}
