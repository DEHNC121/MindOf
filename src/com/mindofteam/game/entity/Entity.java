package com.mindofteam.game.entity;

import com.mindofteam.game.graphics.Animation;
import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.util.AABB;
import com.mindofteam.game.util.KeyHandler;
import com.mindofteam.game.util.MouseHandler;
import com.mindofteam.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity
{
    private int UP = 7;
    private int DOWN = 6;
    private int RIGHT = 4;
    private int LEFT = 5;

    protected Animation ani;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    protected int currentAnimation;


    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attack;
    protected boolean run;
    protected int attackSpeed;
    protected int attackDuration;

    protected float dx;
    protected float dy;

    protected float maxSpeed = 3f;
    protected float acc = 1f;
    protected float deacc = 1f;
    float bonus=0f;

    protected AABB hitBounds;
    protected AABB bounds;

    public Entity (Sprite sprite, Vector2f orgin, int size)
    {
        this.sprite = sprite;
        pos = orgin;
        this.size = size;

        bounds = new AABB (orgin, size, size);
        hitBounds = new AABB (new Vector2f(orgin.x + (size / 2), orgin.y), size, size);

        ani = new Animation();
        setAnimation (RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

    public void setSprite (Sprite sprite) { this.sprite = sprite; }
    public void setSize (int i) { size = i; }
    public void setMaxSpeed (float f) { maxSpeed = f; }

    public void setAcc (float f) { acc = f; }
    public void setDeacc (float f) { deacc = f; }

    public AABB getBounds () { return bounds; }

    public int getSize () { return size; }
    public Animation getAnimation () { return ani; }

    public void setAnimation (int i, BufferedImage [] frames, int delay)
    {
        currentAnimation = i;
        ani.setFrames (frames);
        ani.setDelay (delay);
    }

    public void animate ()
    {
        if (up)
        {
            if (currentAnimation != UP || ani.getDelay() == -1)
            {
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        }
        else if (down)
        {
            if (currentAnimation != DOWN || ani.getDelay() == -1)
            {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        }
        else if (left)
        {
            if (currentAnimation != LEFT || ani.getDelay() == -1)
            {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        }
        else if (right)
        {
            if (currentAnimation != RIGHT || ani.getDelay() == -1)
            {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        }
        else
        {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    private void setHitBoxDirection ()
    {
        if (up)
        {
            hitBounds.setyOffset(-size / 2);
            hitBounds.setxOffset(-size / 2);
        }
        else if (down)
        {
            hitBounds.setyOffset(size / 2);
            hitBounds.setxOffset(-size / 2);
        }
        else if (left)
        {
            hitBounds.setyOffset(0);
            hitBounds.setxOffset(-size);
        }
        else if (right)
        {
            hitBounds.setyOffset(0);
            hitBounds.setxOffset(0);
        }
    }

    public void update ()
    {
        animate ();
        setHitBoxDirection ();
        ani.update();
    }

    public abstract void render (Graphics2D g);
    public void input (KeyHandler key, MouseHandler mouse)
    {

    }

}
