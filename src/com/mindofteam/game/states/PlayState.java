package com.mindofteam.game.states;

import com.mindofteam.game.GamePanel;
import com.mindofteam.game.entity.Player;
import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.tiles.Message;
import com.mindofteam.game.tiles.TileManager;
import com.mindofteam.game.util.*;
import com.mindofteam.game.graphics.Font;

import java.awt.*;

public class PlayState extends GameState
{
    private Font font;
    private static Font staticFont;
    public static Player player;
    private static TileManager tm;
    private static GameStateManager gsm;
    private Message message;
    private static String currentMessage;
    public static boolean do_id=false;

    public static Camera cam;

    public static Vector2f map;

    public static Player getPlayer() {
        return player;
    }

    public static TileManager getTm() {
        return tm;
    }

    public static void setTm(TileManager tm) {
        PlayState.tm = tm;
    }

    public static Font getStaticFont() {
        return staticFont;
    }

    public PlayState (GameStateManager gsm)
    {
        super(gsm);
        this.gsm=gsm;
        map = new Vector2f(MenuState.startX-GamePanel.width/2,MenuState.startY-GamePanel.height/2);
        Vector2f.setWorldVar(map.x, map.y);

        cam = new Camera (new AABB (new Vector2f (), GamePanel.width, GamePanel.height));
        //cam = new Camera (new AABB (new Vector2f (), 900, 600));



        tm = new TileManager ("tile/Map.xml", cam);
        font = new Font ("font/font.png", 10, 10);
        staticFont=new Font ("font/font.png", 10, 10);
        player = new Player(new Sprite("entity/hero.png"), new Vector2f(MenuState.startX , MenuState.startY ), 86);
        message=new Message();
        currentMessage=null;
    }

    public PlayState (GameStateManager gsm, Player p) {
        super(gsm);
        Vector2f.setWorldVar(map.x, map.y);

        cam = new Camera (new AABB (new Vector2f (), GamePanel.width, GamePanel.height));


        this.font=PlayState.staticFont;
        message=new Message();
        player = new Player(p.getSprite(),  p.getPos(), 86);
        player.setGold(p.getGold());
        player.setStamina(p.getStamina());
        player.setKeys(p.getKeys());
        currentMessage=null;
        Vector2f.setWorldVar(map.x, map.y);
    }

    @Override
    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        player.update();
        player.stamina();
        cam.update ();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
        if (key.do_it.down){
            do_id=true;
        }else {
            do_id=false;
        }
    }
    private int plus=0;
    private int keys=0;
    private int gold=0;
    private int add=0;
    private int i=0;
    private int j=0;
    @Override
    public void render(Graphics2D g)
    {
        tm.render(g);
        StringBuilder sb=new StringBuilder();
        for(int i=0; i<20-player.getStamina(); i++) sb.append(" ");
        for(int i=0; i<player.getStamina(); i++) sb.append("+");
        Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f (GamePanel.width - 192, 52), 32, 32, 24, 0);
        Sprite.drawArray(g, font, sb.toString(), new Vector2f (GamePanel.width - 492, 10), 32, 32, 24, 0);
        String goldStr="Gold: "+player.getGold();
        String keyStr="Keys: "+player.getKeys();
        Sprite.drawArray(g, font, goldStr, new Vector2f (10, 10), 32, 32, 24, 0);
        Sprite.drawArray(g, font, keyStr, new Vector2f (10, 40), 32, 32, 24, 0);
        if (gold!=player.getGold() || i>=0){
            if (gold!=player.getGold()){
                i=20;
                add=player.getGold()-gold;
            }
            Sprite.drawArray(g, font, (add < 0) ? String.valueOf(add) : "+ "+String.valueOf(add), new Vector2f (goldStr.length()*32, 10), 32, 32, 24, 0);
            gold=player.getGold();
            i--;
        }
        if(keys!=player.getKeys() || j>=0){
            if (keys!=player.getKeys()){
                j=20;
                plus=player.getKeys()-keys;
            }
            Sprite.drawArray(g, font, (plus>0)? "+"+String.valueOf(plus) : String.valueOf(plus) ,new Vector2f (keyStr.length()*32, 40), 32, 32, 24, 0);
            keys=player.getKeys();
            j--;
        }
        player.render(g);
        cam.render (g);
        if(currentMessage!=null) print(currentMessage,g);
        currentMessage=null;
    }
    public static void notify(String s){
        currentMessage=s;
    }
    public static void notify(int g){
        player.setGold(g);
    }
    public static void print(String s, Graphics2D g){
        Sprite.drawArray(g, staticFont, s, new Vector2f(GamePanel.width/2-(s.length()*10) , GamePanel.height-50), 25, 35, 20, 0);
    }
    public static void addKey(){
        player.addKey();
    }
    public static void takeKey(){
        player.takeKey();
    }
    public static boolean checkKey(){ return player.getKeys()>0; }
    public static boolean checkDoubleKey(){ return player.getKeys()>1; }
    public static void pause(){
        gsm.set(new PauseState(gsm, map, tm, player));
    }
}
