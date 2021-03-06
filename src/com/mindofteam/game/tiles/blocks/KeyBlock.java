package com.mindofteam.game.tiles.blocks;

import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.states.PlayState;
import com.mindofteam.game.tiles.TileMapNorm;
import com.mindofteam.game.util.AABB;
import com.mindofteam.game.util.Vector2f;

import java.awt.image.BufferedImage;

public class KeyBlock extends Block{
    private int x,y;
    private boolean taken;
    private BufferedImage img;

    public KeyBlock(BufferedImage img, Vector2f pos, int w, int h, Sprite sprite,int x,int y) {

        super(img, pos, w, h);
        this.x=x;
        this.y=y;
        taken=false;
        this.img=sprite.getSprite(11, 39);
    }

    @Override
    public boolean update(AABB p) {
        if(!taken){
            PlayState.notify("Press 'E' to pick up the key");
            if (PlayState.do_id){
                PlayState.addKey();
                ((TileMapNorm) PlayState.getTm().getTm(4)).blocks.remove(String.valueOf(x) + "," + String.valueOf(y));
                ((TileMapNorm) PlayState.getTm().getTm(5)).blocks.remove(String.valueOf(x) + "," + String.valueOf(y));
                ((TileMapNorm) PlayState.getTm().getTm(6)).blocks.remove(String.valueOf(x) + "," + String.valueOf(y));

                taken=true;
            }
        }
        return false;
    }

    @Override
    public boolean isInside(AABB p) {
        return false;
    }
}