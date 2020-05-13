package com.mindofteam.game.tiles.blocks;

import com.mindofteam.game.states.PlayState;
import com.mindofteam.game.util.AABB;
import com.mindofteam.game.util.Vector2f;

import java.awt.image.BufferedImage;

public class MoneyBlock extends Block{
    private boolean visited;

    public MoneyBlock (BufferedImage img, Vector2f pos, int w, int h) {
        super(img, pos, w,  h);
        visited=false;
    }

    @Override
    public boolean update(AABB p) {
        if(!visited) PlayState.notify(10);
        visited=true;
        return false;
    }

    @Override
    public boolean isInside(AABB p) {
        return false;
    }
}
