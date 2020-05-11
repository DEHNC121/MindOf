package com.mindofteam.game.entity;

import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.states.PlayState;
import com.mindofteam.game.util.KeyHandler;
import com.mindofteam.game.util.MouseHandler;
import com.mindofteam.game.util.Vector2f;

import java.awt.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Player extends Entity
{
    public int stamina;
    public int pause;
    private Executor staminaThread;
    public boolean paused;
    public Player (Sprite sprite, Vector2f orgin, int size)
    {
        super (sprite, orgin, size);
        paused=false;
        acc = 2f;
        maxSpeed = 3f;
        bounds.setWidth (42);
        bounds.setHeight (20);
        bounds.setxOffset (12);
        bounds.setyOffset (40);
        stamina=10;
        staminaThread= Executors.newSingleThreadExecutor();
        stamina();
    }

    public void move ()
    {
        if(run && stamina>3){ bonus=4f; }
        else { bonus=0f; }
        if (up && !down) {
            dy -= acc+bonus;
            if (dy < -maxSpeed-bonus) { dy = -maxSpeed-bonus; }
        }
        else {
            if (dy < 0) {
                dy += deacc;
                if (dy > 0) dy = 0;
            }
        }
        if (down && !up) {
            dy += acc+bonus;
            if (dy > maxSpeed+bonus) { dy = maxSpeed+bonus; }
        }
        else {
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) dy = 0;
            }
        }
        if (left && !right) {
            dx -= acc+bonus;
            if (dx < -maxSpeed-bonus) { dx = -maxSpeed-bonus; }
        }
        else {
            if (dx < 0) {
                dx += deacc;
                if (dx > 0) dx = 0;
            }
        }
        if (right && !left) {
            dx += acc+bonus;
            if (dx > maxSpeed+bonus) { dx = maxSpeed+bonus; }
        }
        else {
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) dx = 0;
            }
        }
        //dx = (float) (dx / (Math.sqrt (dx * dx + dy * dy)));
        //dy = (float) (dy / (Math.sqrt (dx * dx + dy * dy)));
    }

    public void update ()
    {
        if(!paused){
            super.update ();
            move ();
            stamina();
            if (!bounds.collisionTile (dx, 0))
            {
                PlayState.map.x += dx;
                pos.x += dx;
            }

            if (!bounds.collisionTile (0, dy))
            {
                PlayState.map.y += dy;
                pos.y += dy;
            }
        }
    }

    @Override
    public void render (Graphics2D g)
    {
        if(!paused){
            g.setColor (Color.blue);
            g.drawRect ((int) (pos.getWorldVar ().x + bounds.getXOffset ()), (int) (pos.getWorldVar ().y + bounds.getYOffset ()), (int) bounds.getWidth (), (int) bounds.getHeight ());
            g.drawImage (ani.getImage (), (int) (pos.getWorldVar ().x), (int) (pos.getWorldVar ().y), size, size, null);
        }
    }


    public void input (MouseHandler mouse, KeyHandler key)
    {
        if (key.pause.down && !paused){
            PlayState.pause();
            paused=true;
        }
        if(!paused){
            boolean moving=false;
            if (mouse.getButton () == 1) {
                System.out.println ("Player: " + pos.x + ", " + pos.y);
            }
            if (key.up.down) {
                up = true;
                moving=true;
            }
            else {
                up = false;
            }
            if (key.down.down) {
                down = true;
                moving=true;
            }
            else {
                down=false;
            }
            if (key.left.down) {
                left = true;
                moving=true;
            }
            else {
                left = false;
            }
            if (key.right.down) {
                right = true;
                moving=true;
            }
            else {
                right=false;
            }
            if(key.run.down && moving) {
                run=true;
            }
            else {
                run=false;
            }
            if (key.attack.down && !key.run.down) {
                attack = true;
            }
            else {
                attack = false;
            }
        }
    }
    public void stamina() {
            staminaThread.execute(()->{
                try {
                    Thread.sleep(400);
                    if (stamina > 0 && run) stamina--;
                    else if (stamina < 10) stamina++;
                } catch (Exception e) {}
            });
    }
}
