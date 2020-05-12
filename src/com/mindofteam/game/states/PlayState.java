package com.mindofteam.game.states;

import com.mindofteam.game.GamePanel;
import com.mindofteam.game.entity.Player;
import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.tiles.TileManager;
import com.mindofteam.game.util.KeyHandler;
import com.mindofteam.game.util.MouseHandler;
import com.mindofteam.game.util.Vector2f;
import com.mindofteam.game.graphics.Font;

import java.awt.*;

public class PlayState extends GameState
{
    private Font font;
    protected static Player player;
    private TileManager tm;
    private static GameStateManager gsm;

    public static Vector2f map;

    public PlayState (GameStateManager gsm)
    {
        super(gsm);
        this.gsm=gsm;
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);

        tm = new TileManager ("tile/Map.xml");
        font = new Font ("font/font.png", 10, 10);
        player = new Player(new Sprite("entity/hero.png"), new Vector2f(0 + (GamePanel.width / 2) - 32, 0 + (GamePanel.height / 2) - 32), 86);

    }
    public PlayState (GameStateManager gsm, Vector2f v, Vector2f map)
    {
        super(gsm);
        this.gsm=gsm;
        this.map = map;
        Vector2f.setWorldVar(map.x, map.y);

        tm = new TileManager ("tile/Map.xml");
        font = new Font ("font/font.png", 10, 10);
        player = new Player(new Sprite("entity/hero.png"), v,86);

    }

    @Override
    public void update()
    {
        Vector2f.setWorldVar(map.x, map.y);
        player.update();
        player.stamina();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key)
    {
        player.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g)
    {
        tm.render(g);
        StringBuilder sb=new StringBuilder();
        for(int i=0; i<20-player.stamina; i++) sb.append(" ");
        for(int i=0; i<player.stamina; i++) sb.append("+");
        Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f (GamePanel.width - 192, 52), 32, 32, 24, 0);
        Sprite.drawArray(g, font, sb.toString(), new Vector2f (GamePanel.width - 492, 10), 32, 32, 24, 0);
        player.render(g);
    }

    public static void pause(){
        gsm.set(new PauseState(gsm, player.getPos(), map));
    }
}
