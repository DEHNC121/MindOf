package com.mindofteam.game.tiles.blocks;

import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.states.PlayState;
import com.mindofteam.game.tiles.TileMapNorm;
import com.mindofteam.game.tiles.TileMapObj;
import com.mindofteam.game.util.AABB;
import com.mindofteam.game.util.Vector2f;

import java.awt.image.BufferedImage;
import java.awt.image.TileObserver;

public class MoneyBlock extends Block{
    private boolean visited;

    private int x,y;
    static BufferedImage[] img= new BufferedImage[2];


    public MoneyBlock (Sprite sprite, BufferedImage img, Vector2f pos, int w, int h, int x, int y) {
        super(img, pos, w,  h);
        visited=false;
        this.x = x;
        this.y = y;
        this.img[0]=sprite.getSprite(62, 40);
        this.img[1]=sprite.getSprite(63, 40);
    }

    public void setXY(int x,int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean update(AABB p) {
        if(!visited) PlayState.notify(10);
        visited=true;
        boolean right;
        if (
                ((TileMapObj)PlayState.getTm().getTm(0)).getTmo_blocks().containsKey(String.valueOf(x+1) + "," + String.valueOf(y)) &&
                        ((TileMapObj)PlayState.getTm().getTm(0)).getTmo_blocks().get(String.valueOf(x+1) + "," + String.valueOf(y)).getClass().equals(MoneyBlock.class)
            )
        {

            ((TileMapObj)PlayState.getTm().getTm(0)).getTmo_blocks().remove(String.valueOf(x+1) + "," + String.valueOf(y));

            ((TileMapNorm)PlayState.getTm().getTm(4)).getTmo_blocks().replace(String.valueOf(x) + "," + String.valueOf(y-1),new NormBlock(img[0], new Vector2f((int) x * 64, (int) (y-1) * 64), 64, 64));
            ((TileMapNorm)PlayState.getTm().getTm(4)).getTmo_blocks().replace(String.valueOf(x+1) + "," + String.valueOf(y-1),new NormBlock(img[1], new Vector2f((int) (x+1) * 64, (int) (y-1) * 64), 64, 64));
//            ((TileMapNorm)PlayState.getTm().getTm(4)).getTmo_blocks().remove(String.valueOf(x+1) + "," + String.valueOf(y));
//            ((TileMapNorm)PlayState.getTm().getTm(4)).getTmo_blocks().remove(String.valueOf(x) + "," + String.valueOf(y-1));
//            ((TileMapNorm)PlayState.getTm().getTm(4)).getTmo_blocks().remove(String.valueOf(x+1) + "," + String.valueOf(y-1));
        }
        else if (
                ((TileMapObj)PlayState.getTm().getTm(0)).getTmo_blocks().containsKey(String.valueOf(x-1) + "," + String.valueOf(y)) &&
                        ((TileMapObj)PlayState.getTm().getTm(0)).getTmo_blocks().get(String.valueOf(x-1) + "," + String.valueOf(y)).getClass().equals(MoneyBlock.class)
        )
        {

            ((TileMapObj)PlayState.getTm().getTm(0)).getTmo_blocks().remove(String.valueOf(x-1) + "," + String.valueOf(y));
        }


        ((TileMapObj)PlayState.getTm().getTm(0)).getTmo_blocks().remove(String.valueOf(x) + "," + String.valueOf(y));

        //((TileMapObj)PlayState.getTm().getTm(0)).getTmo_blocks().put();

        return false;
    }

    @Override
    public boolean isInside(AABB p) {
        return false;
    }
}
