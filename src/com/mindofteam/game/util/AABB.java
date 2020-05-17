package com.mindofteam.game.util;

import com.mindofteam.game.entity.Entity;
import com.mindofteam.game.tiles.TileMapObj;
import com.mindofteam.game.tiles.blocks.Block;
import com.mindofteam.game.tiles.blocks.HoleBlock;
import com.mindofteam.game.tiles.blocks.MessageBlock;

public class AABB
{
    private Vector2f pos;
    private float xOffset = 0;
    private float yOffset = 0;
    private float w;
    private float h;
    private Entity e;

    private float r;
    private int size;

    public AABB (Vector2f pos, int w, int h)
    {
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }

    public AABB (Vector2f pos, int r, Entity e)
    {
        this.pos = pos;
        this.r = r;
        this.e = e;

        size = r;

    }

    public Vector2f getPos () { return pos; }

    public float getRadius () { return r; }

    public float getWidth () { return w; }

    public float getHeight () { return h; }

    public void setBox (Vector2f pos, int w, int h)
    {
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }

    public void setCircle (Vector2f pos, int r)
    {
        this.pos = pos;
        this.r = r;

        size = r;
    }

    public void setWidth (float f) { w = f; }
    public void setHeight (float f) { h = f; }

    public void setxOffset (float f) { xOffset = f; }
    public void setyOffset (float f) { yOffset = f; }

    public float getXOffset () { return xOffset; }
    public float getYOffset () { return yOffset; }

    public boolean collides (AABB bBox)
    {
        float ax = ((pos.getWorldVar().x + (xOffset)) + (w / 2));
        float ay = ((pos.getWorldVar().y) + (yOffset) + (h / 2));
        float bx = ((bBox.getPos ().getWorldVar().x + (bBox.xOffset / 2)) + (w / 2));
        float by = ((bBox.getPos ().getWorldVar().y + (bBox.yOffset / 2)) + (h / 2));

        if (Math.abs(ax - bx) < (this.w / 2) + (bBox.w / 2))
        {
            if (Math.abs(ay - by) < (this.h / 2) + (bBox.h / 2))
            {
                return true;
            }
        }
        return false;
    }

    public boolean colCircleBox (AABB aBox)
    {
        float cx = (float) (pos.getWorldVar().x + r / Math.sqrt (2) - e.getSize () / Math.sqrt(2));
        float cy = (float) (pos.getWorldVar().y + r / Math.sqrt (2) - e.getSize () / Math.sqrt(2));

        float xDelta = cx - Math.max(aBox.pos.getWorldVar().x + (aBox.getWidth() / 2), Math.min(cx, aBox.pos.getWorldVar().x));
        float yDelta = cy - Math.max(aBox.pos.getWorldVar().y + (aBox.getWidth() / 2), Math.min(cx, aBox.pos.getWorldVar().y));

        if (xDelta * xDelta + yDelta * yDelta < (this.r / Math.sqrt(2)) * (this.r / Math.sqrt(2)) )
        {
            return true;
        }
        return false;
    }

    public boolean collisionTile (float ax, float ay)
    {
        for (int c = 0; c < 4; c++)
        {
            int xt = (int) (e.getBounds ().getPos ().x + ax + (c % 2) * e.getBounds ().getWidth () + e.getBounds ().getXOffset () / 64);
            int yt = (int) (e.getBounds ().getPos ().y + ay + (c / 2) * e.getBounds ().getWidth () + e.getBounds ().getYOffset () / 64);
            if (TileMapObj.event_blocks [xt + yt * TileMapObj.height] instanceof Block)
            {
                Block block = TileMapObj.event_blocks[xt + (yt * TileMapObj.height)];
                if (block instanceof HoleBlock)
                {
                    return collisionHole (ax, ay, xt, yt, block);
                }
                return block.update (e.getBounds ());
            }
        }
        return false;
    }
    private boolean collisionHole (float ax, float ay, float xt, float yt, Block block)
    {
        int nextXt = (int) ((( (e.getBounds ().getPos ().x + ax) + e.getBounds ().getXOffset () / 64) + e.getBounds ().getWidth () / 64));
        int nextYt = (int) ((( (e.getBounds ().getPos ().y + ay) + e.getBounds ().getYOffset () / 64) + e.getBounds ().getHeight () / 64));

        if (block.isInside (e.getBounds ()))
        {
            e.setFallen (true);
            return false;
        }
        else if ((nextXt == yt + 1) || (nextXt == xt + 1) || (nextYt == yt - 1) || (nextYt == xt - 1))
        {
            if (TileMapObj.event_blocks [nextXt + (nextYt * TileMapObj.height)] instanceof HoleBlock)
            {
                if (e.getBounds ().getPos ().x > block.getPos ().x)
                {
                    e.setFallen (true);
                }
                return false;
            }
        }
        e.setFallen (false);
        return false;

    }

}
