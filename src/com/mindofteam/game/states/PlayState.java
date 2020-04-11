package com.mindofteam.game.states;

import com.mindofteam.game.entity.Player;
import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.util.KeyHandler;
import com.mindofteam.game.util.MouseHandler;
import com.mindofteam.game.util.Vector2f;
import com.mindofteam.game.graphics.Font;

import java.awt.*;
import java.util.Collection;

public class PlayState extends GameState
{
    private Font font;
    private Player player;

    public PlayState (GameStateManager gsm)
    {
        super(gsm);
        font = new Font ("font/ZeldaFont.png", 16, 16);
        player = new Player(new Sprite("entity/linkFormatted.png"), new Vector2f(300, 300), 64);

    }

    @Override
    public void update()
    {
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
        Sprite.drawArray(g, font, "MIND OF", new Vector2f (100, 100), 32, 32, 16, 0);
        player.render(g);
    }
}
