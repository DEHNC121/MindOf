package com.mindofteam.game.entity;

import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.states.PlayState;
import com.mindofteam.game.util.KeyHandler;
import com.mindofteam.game.util.MouseHandler;
import com.mindofteam.game.util.Vector2f;

import java.awt.*;

public class Player extends Entity
{
    public Player (Sprite sprite, Vector2f orgin, int size)
    {
        super (sprite, orgin, size);
        acc = 2f;
        maxSpeed = 3f;
        bounds.setWidth (42);
        bounds.setHeight (20);
        bounds.setxOffset (12);
        bounds.setyOffset (40);
    }

    public void move ()
    {
        if (up && !down)
        {
            dy -= acc;
            if (dy < -maxSpeed) { dy = -maxSpeed; }
        }
        else
        {
            if (dy < 0)
            {
                dy += deacc;
                if (dy > 0)
                {
                    dy = 0;
                }
            }
        }


        if (down && !up)
        {
            dy += acc;
            if (dy > maxSpeed) { dy = maxSpeed; }
        }
        else
        {
            if (dy > 0)
            {
                dy -= deacc;
                if (dy < 0)
                {
                    dy = 0;
                }
            }
        }


        if (left && !right)
        {
            dx -= acc;
            if (dx < -maxSpeed) { dx = -maxSpeed; }
        }
        else
        {
            if (dx < 0)
            {
                dx += deacc;
                if (dx > 0)
                {
                    dx = 0;
                }
            }
        }


        if (right && !left)
        {
            dx += acc;
            if (dx > maxSpeed) { dx = maxSpeed; }
        }
        else
        {
            if (dx > 0)
            {
                dx -= deacc;
                if (dx < 0)
                {
                    dx = 0;
                }
            }
        }

        //dx = (float) (dx / (Math.sqrt (dx * dx + dy * dy)));
        //dy = (float) (dy / (Math.sqrt (dx * dx + dy * dy)));
    }
    public void update ()
    {
        super.update ();
        move ();
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

    @Override
    public void render (Graphics2D g)
    {
        g.setColor (Color.blue);
        g.drawRect ((int) (pos.getWorldVar ().x + bounds.getXOffset ()), (int) (pos.getWorldVar ().y + bounds.getYOffset ()), (int) bounds.getWidth (), (int) bounds.getHeight ());
        g.drawImage (ani.getImage (), (int) (pos.getWorldVar ().x), (int) (pos.getWorldVar ().y), size, size, null);
    }

    public void input (MouseHandler mouse, KeyHandler key)
    {

        if (mouse.getButton () == 1)
        {
            System.out.println ("Player: " + pos.x + ", " + pos.y);
        }

        if (key.up.down)
        {
            up = true;
        }
        else
        {
            up = false;
        }
        if (key.down.down)
        {
            down = true;
        }
        else
        {
            down = false;
        }
        if (key.left.down)
        {
            left = true;
        }
        else
        {
            left = false;
        }
        if (key.right.down)
        {
            right = true;
        }
        else
        {
            right = false;
        }
        if (key.attack.down)
        {
            attack = true;
        }
        else
        {
            attack = false;
        }
    }
}
