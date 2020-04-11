package com.mindofteam.game.states;

import com.mindofteam.game.util.KeyHandler;
import com.mindofteam.game.util.MouseHandler;

import java.awt.*;

public abstract class GameState
{

    private GameStateManager gsm;

    public GameState (GameStateManager g)
    {
        gsm = g;
    }

    public abstract void update ();
    public abstract void input (MouseHandler mouse, KeyHandler key);
    public abstract void render (Graphics2D g);

}
