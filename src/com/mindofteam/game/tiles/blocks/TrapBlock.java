package com.mindofteam.game.tiles.blocks;

import com.mindofteam.game.states.PlayState;
import com.mindofteam.game.tiles.TileMapObj;
import com.mindofteam.game.util.AABB;
import com.mindofteam.game.util.Vector2f;

import java.awt.image.BufferedImage;

public class TrapBlock extends Block {
    int i;

    public static boolean done;

    public TrapBlock(BufferedImage img, Vector2f pos, int w, int h,int i) {
        super(img, pos, w, h);
        done=true;
        this.i=i;
    }

    @Override
    public boolean update(AABB p) {

        if (done&&i==0)
        {
            done=false;
        }
        if (i==0)
        {
            PlayState.notify("if you are reading this you never again get out of the cave");
            return false;
        }else return !done && i == 1;

    }

    @Override
    public boolean isInside(AABB p) {
        return false;
    }
}
