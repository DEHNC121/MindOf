package com.mindofteam.game.tiles.blocks;

import com.mindofteam.game.GamePanel;
import com.mindofteam.game.graphics.Font;
import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.util.AABB;
import com.mindofteam.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InfoBlock extends Block{
    private final Font font;
    public InfoBlock (BufferedImage img, Vector2f pos, int w, int h)
    {
        super(img, pos, w, h);
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
        this.font = new Font ("res/font/font.png", 10, 10);
    }
    @Override
    public boolean update(AABB p) {
        return false;
    }

    public boolean isInside (AABB p) {
        if (p.getPos ().x + p.getXOffset () < pos.x) { return false; }
        if (p.getPos ().y + p.getYOffset () < pos.y) { return false; }
        if (w + pos.x < p.getWidth () + (p.getPos ().x + p.getXOffset ())) { return false; }
        if (h + pos.y < p.getHeight () + (p.getPos ().y + p.getYOffset ())) { return false; }
        return true;
    }

    public void render (Graphics2D g) {
        super.render (g);
        g.setColor(Color.white);
        Sprite.drawArray(g, font, "INFOBLOCK", new Vector2f (GamePanel.width /2-100, GamePanel.height/2-30), 62, 62, 42, 0);
        g.drawRect((int) pos.getWorldVar().x, (int) pos.getWorldVar().y, w, h);
    }
}
