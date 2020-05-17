package com.mindofteam.game.tiles.blocks;

import com.mindofteam.game.util.AABB;
import com.mindofteam.game.util.Vector2f;

import java.awt.image.BufferedImage;

public class BedrockBlock extends Block{
    public BedrockBlock(BufferedImage img, Vector2f pos, int w, int h) {
        super(img, pos, w, h);
    }

    @Override
    public boolean update(AABB p) {
        return true;
    }

    @Override
    public boolean isInside(AABB p) {
        return false;
    }
}
