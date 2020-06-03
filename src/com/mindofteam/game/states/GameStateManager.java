package com.mindofteam.game.states;

import com.mindofteam.game.GamePanel;
import com.mindofteam.game.util.KeyHandler;
import com.mindofteam.game.util.MouseHandler;
import com.mindofteam.game.util.Vector2f;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager
{

    private ArrayList <GameState> states;

    public  static Vector2f map;
    private GameState currentState;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;

    public GameStateManager () {
        map = new Vector2f(GamePanel.width, GamePanel.height);
        Vector2f.setWorldVar(map.x, map.y);
        currentState=new MenuState(this,1500,1270);
        //currentState=new MenuState(this,800,1150);
    }

    public void set(GameState g){
        currentState=g;
    }

    public void update ()
    {
        Vector2f.setWorldVar(map.x, map.y);
        currentState.update();
    }
    public void input (MouseHandler mouse, KeyHandler key) {
        currentState.input(mouse, key);
    }
    public void render (Graphics2D g) {
        currentState.render(g);
    }
}
