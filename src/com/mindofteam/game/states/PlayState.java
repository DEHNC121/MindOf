package com.mindofteam.game.states;

import com.mindofteam.game.GamePanel;
import com.mindofteam.game.entity.Player;
import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.tiles.Message;
import com.mindofteam.game.tiles.TileManager;
import com.mindofteam.game.util.KeyHandler;
import com.mindofteam.game.util.MouseHandler;
import com.mindofteam.game.util.Vector2f;
import com.mindofteam.game.graphics.Font;

import java.awt.*;

public class PlayState extends GameState
{
    private Font font;
    private static Font staticFont;
    protected static Player player;
    private static TileManager tm;
    private static GameStateManager gsm;
    private Message message;
    private static String currentMessage;
    public static boolean do_id=false;

    public static Vector2f map;

    public static TileManager getTm() {
        return tm;
    }

    public static Font getStaticFont() {
        return staticFont;
    }

    public PlayState (GameStateManager gsm)
    {
        super(gsm);
        this.gsm=gsm;
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);

        tm = new TileManager ("tile/Map.xml");
        font = new Font ("font/font.png", 10, 10);
        staticFont=new Font ("font/font.png", 10, 10);
        player = new Player(new Sprite("entity/hero.png"), new Vector2f(0 + (GamePanel.width / 2) - 32, 0 + (GamePanel.height / 2) - 32), 86);
        message=new Message();
        currentMessage=null;
    }

    public PlayState (GameStateManager gsm, Vector2f map, TileManager tm, Player p) {
        super(gsm);
        this.gsm=gsm;
        this.map = map;
        Vector2f.setWorldVar(map.x, map.y);
        this.tm=tm;
        this.font=new Font ("font/font.png", 10, 10);
        staticFont=new Font ("font/font.png", 10, 10);
        message=new Message();
        player = new Player(new Sprite("entity/hero.png"),  p.getPos(), 86);
        player.setGold(p.getGold());
        currentMessage=null;
        Vector2f.setWorldVar(map.x, map.y);
    }

    @Override
    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        player.update();
        player.stamina();
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

    private int gold=0;
    private int add=0;
    private int i=0;
    @Override
    public void render(Graphics2D g)
    {
        tm.render(g);
        StringBuilder sb=new StringBuilder();
        for(int i=0; i<20-player.getStamina(); i++) sb.append(" ");
        for(int i=0; i<player.getStamina(); i++) sb.append("+");
        Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f (GamePanel.width - 192, 52), 32, 32, 24, 0);
        Sprite.drawArray(g, font, sb.toString(), new Vector2f (GamePanel.width - 492, 10), 32, 32, 24, 0);
        Sprite.drawArray(g, font, "Gold: "+player.getGold(), new Vector2f (10, 10), 32, 32, 24, 0);
        if (gold!=player.getGold() || i>=0){
            if (gold!=player.getGold()){
                i=20;
                add=player.getGold()-gold;
            }
            Sprite.drawArray(g, font, (add < 0) ? String.valueOf(add) : "+ "+String.valueOf(add), new Vector2f (15, 40), 32, 32, 24, 0);
            gold=player.getGold();
            i--;
        }
        player.render(g);
        System.out.println(player.getPos().x+" "+player.getPos().y);
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

    public static void pause(){
        gsm.set(new PauseState(gsm, map, tm, player));
    }
}
