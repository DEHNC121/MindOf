package com.mindofteam.game.tiles.blocks;

import com.mindofteam.game.states.PlayState;
import com.mindofteam.game.util.AABB;
import com.mindofteam.game.util.Vector2f;

import java.awt.image.BufferedImage;

public class KeyBlock extends Block{

    public KeyBlock(BufferedImage img, Vector2f pos, int w, int h) {
        super(img, pos, w, h);
    }

    @Override
    public boolean update(AABB p) {
        PlayState.notify("Pressing 'E' to get");
        return false;
    }

    @Override
    public boolean isInside(AABB p) {
        return false;
    }
}
