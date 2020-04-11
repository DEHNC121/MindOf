package com.mindofteam.game.entity;

import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.util.KeyHandler;
import com.mindofteam.game.util.MouseHandler;
import com.mindofteam.game.util.Vector2f;

import java.awt.*;

public class Player extends Entity
{
    public Player(Sprite sprite, Vector2f orgin, int size)
    {
        super(sprite, orgin, size);
    }

    public void move ()
    {
        if (up)
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


        if (down)
        {
            dy += acc;
            if (dy < maxSpeed) { dy = maxSpeed; }
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


        if (left)
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


        if (right)
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
    }

    public void update ()
    {
        super.update();
        move ();
        pos.x += dx;
        pos.y += dy;
    }

    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(ani.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
    }

    public void input (MouseHandler mouse, KeyHandler key)
    {

        if (mouse.getButton() == 1)
        {
           System.out.println("Player: " + pos.x + ", " + pos.y);
        }

        if (key.up.down)
        {
            up = true;
        }
        else
        {
            up = down;
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
