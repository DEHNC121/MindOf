package com.mindofteam.game.tiles.blocks;

import com.mindofteam.game.GamePanel;
import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.states.PlayState;
import com.mindofteam.game.tiles.Message;
import com.mindofteam.game.util.AABB;
import com.mindofteam.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MessageBlock extends Block
{
    private  boolean visited;
    public static int count=0;
    public int id;
    public MessageBlock(BufferedImage img, Vector2f pos, int w, int h) {
        super(img, pos, w, h);
        this.id=count;
        count++;
        visited=false;
    }

    @Override
    public boolean update(AABB p) {
        System.out.println("HOLE");
        Message.print(this.id);
        return false;
    }

    public boolean isInside (AABB p) {
        if (p.getPos ().x + p.getXOffset () < pos.x) { return false; }
        if (p.getPos ().y + p.getYOffset () < pos.y) { return false; }
        if (w + pos.x < p.getWidth () + (p.getPos ().x + p.getXOffset ())) { return false; }
        if (h + pos.y < p.getHeight () + (p.getPos ().y + p.getYOffset ())) { return false; }
        return true;
    }

    public void render (Graphics2D g)
    {
        super.render(g);
        g.setColor(Color.green);
        g.drawRect((int) pos.getWorldVar().x, (int) pos.getWorldVar().y, w, h);
    }
}
