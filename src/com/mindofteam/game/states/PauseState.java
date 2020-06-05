package com.mindofteam.game.states;

import com.mindofteam.game.GamePanel;
import com.mindofteam.game.entity.Player;
import com.mindofteam.game.graphics.Font;
import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.tiles.TileManager;
import com.mindofteam.game.tiles.blocks.JaskiniaBlock;
import com.mindofteam.game.util.KeyHandler;
import com.mindofteam.game.util.MouseHandler;
import com.mindofteam.game.util.Vector2f;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PauseState extends GameState
{
    private static Sprite background;
    private static GameStateManager gsm;
    private Font font;
    private int k;
    private boolean one_down;
    private boolean one_up;
    private ArrayList<String> Mane_pause;
    private TileManager tm;
    private Vector2f map;
    private static Player player;
    private int stamina;

    public PauseState(GameStateManager gsm, Vector2f map, TileManager tm, Player p) {
        super(gsm);
        background=new Sprite("backgrounds/menu.png", 2000,1000);
        this.gsm=gsm;
        this.map=map;
        Vector2f.setWorldVar(map.x, map.y);
        this.font = PlayState.getStaticFont();
        this.k=0;
        this.one_down=true;
        this.one_up=true;
        this.tm=tm;
        player=p;
        this.Mane_pause=new ArrayList<String>();
        stamina=player.getStamina();

        Mane_pause.add("RESUME");
        Mane_pause.add("RESTART");
        Mane_pause.add("EXIT");
    }
    public int pause;
    @Override
    public void update()
    {
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if(key.down.down && one_down){
            k=(k+1)%Mane_pause.size();
            one_down=false;
        }
        else if(key.up.down && one_up){
            k=(k-1)%Mane_pause.size();
            if (k<0)k=Mane_pause.size()-1;
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
            if(k==2) exit();
        }
    }

    public void unPause(){
        player.setStamina(stamina);
        gsm.set(new PlayState(gsm, player));
    }



    public static void restart(){
        JaskiniaBlock.tm1=null;
        gsm.set(new PlayState(gsm));
    }

    public static void exit(){
        System.exit(0);
    }

    @Override
    public void render(Graphics2D g) {
        //tm.render(g);
        g.drawImage(background.getSpriteSheet(),0,0,GamePanel.width,GamePanel.height,null);
        for (int i=0;i<Mane_pause.size();i++){
            Sprite.drawArray(g, font,Mane_pause.get(i), new Vector2f (GamePanel.width /2-(Mane_pause.get(i).length()*25) , GamePanel.height/4+55*i), 62, 62, 42, 0);
        }

        Sprite.drawArray(g, font,">", new Vector2f (GamePanel.width /2-260 , GamePanel.height/4+55*k), 62, 62, 42, 0);

        //player.render(g);
    }
}