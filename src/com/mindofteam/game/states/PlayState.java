package com.mindofteam.game.states;

import com.mindofteam.game.GamePanel;
import com.mindofteam.game.entity.Player;
import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.tiles.TileManager;
import com.mindofteam.game.util.KeyHandler;
import com.mindofteam.game.util.MouseHandler;
import com.mindofteam.game.util.Vector2f;
import com.mindofteam.game.graphics.Font;
import com.mindofteam.game.tiles.TileManager;
import java.awt.*;
import java.util.Collection;

public class PlayState extends GameState
{
    private Font font;
    private Player player;
    private TileManager tm;

    public static Vector2f map;

    public PlayState (GameStateManager gsm)
    {
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);

        tm = new TileManager ("tile/tilemap.xml");
        font = new Font ("font/font.png", 10, 10);
        player = new Player(new Sprite("entity/linkFormatted.png"), new Vector2f(0 + (GamePanel.width / 2) - 32, 0 + (GamePanel.height / 2) - 32), 64);

    }

    @Override
    public void update()
    {
        Vector2f.setWorldVar(map.x, map.y);
        player.update();
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
        Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f (GamePanel.width - 192, 32), 32, 32, 24, 0);
        player.render(g);
    }
}
