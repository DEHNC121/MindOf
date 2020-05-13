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

    private int stamina;
    public int pause;
    private Executor staminaThread;
    public boolean paused;
    private int gold;

    public Player (Sprite sprite, Vector2f orgin, int size)
    {
        super (sprite, orgin, size);
        paused=false;
        acc = 2f;
        maxSpeed = 3f;
        bounds.setWidth (30);
        bounds.setHeight (13);
        bounds.setxOffset (20);
        bounds.setyOffset (70);
        stamina=10;
        staminaThread= Executors.newSingleThreadExecutor();
        stamina();
        gold=0;
    }

    public void move ()
    {
        if(run){ bonus=3f; }
        else { bonus=0f; }

        // up_left
        if (up && !down && left&& !right) {
            dy -= acc+(bonus/2);
            dx -= acc+(bonus/2);
            if (dy < -maxSpeed-(bonus/2)) { dy = -maxSpeed-(bonus/2); }
            if (dx < -maxSpeed-(bonus/2)) { dx = -maxSpeed-(bonus/2); }
        }

        // up_right
        else if (up && !down && !left&& right) {
            dy -= acc+(bonus/2);
            dx += acc+(bonus/2);
            if (dy < -maxSpeed-(bonus/2)) { dy = -maxSpeed-(bonus/2); }
            if (dx > maxSpeed+(bonus/2)) { dx = maxSpeed+(bonus/2); }
        }

        // down_left
        else if (!up && down && left&& !right) {
            dy += acc+(bonus/2);
            dx -= acc+(bonus/2);
            if (dy > maxSpeed+(bonus/2)) { dy = maxSpeed+(bonus/2); }
            if (dx < -maxSpeed-(bonus/2)) { dx = -maxSpeed-(bonus/2); }
        }

        // down_right
        else if (!up && down && !left&& right) {
            dy += acc+(bonus/2);
            dx += acc+(bonus/2);
            if (dy > maxSpeed+(bonus/2)) { dy = maxSpeed+(bonus/2); }
            if (dx > maxSpeed+(bonus/2)) { dx = maxSpeed+(bonus/2); }
        }

        // up
        else if (up && !down && !left&& !right) {
            dy -= acc+bonus;
            if (dy < -maxSpeed-bonus) { dy = -maxSpeed-bonus; }
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) dx = 0;
            }
            else if (dx < 0) {
                dx += deacc;
                if (dx > 0) dx = 0;
            }
        }

        //down
        else if (down && !up && !left&& !right) {
            dy += acc+bonus;
            if (dy > maxSpeed+bonus) { dy = maxSpeed+bonus; }
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) dx = 0;
            }
            else if (dx < 0) {
                dx += deacc;
                if (dx > 0) dx = 0;
            }
        }


        //left
        else if (!down && !up &&left && !right) {
            dx -= acc+bonus;
            if (dx < -maxSpeed-bonus) { dx = -maxSpeed-bonus; }
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) dy = 0;
            }
            else if (dy < 0) {
                dy += deacc;
                if (dy > 0) dy = 0;
            }
        }


        //right
        else if (!down && !up &&right && !left) {
            dx += acc+bonus;
            if (dx > maxSpeed+bonus) { dx = maxSpeed+bonus; }
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) dy = 0;
            }
            else if (dy < 0) {
                dy += deacc;
                if (dy > 0) dy = 0;
            }
        }

        //stop
        else {
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) dy = 0;
            }
            else if (dy < 0) {
                dy += deacc;
                if (dy > 0) dy = 0;
            }
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) dx = 0;
            }
            else if (dx < 0) {
                dx += deacc;
                if (dx > 0) dx = 0;
            }
        }
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
            g.setColor (Color.red);
            g.drawRect ((int) (pos.getWorldVar ().x + bounds.getXOffset ()), (int) (pos.getWorldVar ().y + bounds.getYOffset ()), (int) bounds.getWidth (), (int) bounds.getHeight ());
            g.drawImage (ani.getImage (), (int) (pos.getWorldVar ().x), (int) (pos.getWorldVar ().y), size, size, null);

        }
        System.out.println(gold);
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

    public void setGold(int g){
        gold+=g;
        if(gold<0) gold=0;
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
    public int getGold(){
        return gold;
    }
    public int getStamina(){
        return stamina;
    }
}
