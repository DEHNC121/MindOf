package com.mindofteam.game.tiles;

import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.tiles.blocks.Block;
import com.mindofteam.game.tiles.blocks.NormBlock;
import com.mindofteam.game.util.Vector2f;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class TileMapNorm extends TileMap
{

    public HashMap<String, Block> tmo_blocks;

    public TileMapNorm (String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns)
    {
        Block tempBlock;

        tmo_blocks = new HashMap<String, Block>();
        String [] block = data.split(",");
        for (int i = 0; i < (width * height); ++i)
        {
            int temp = Integer.parseInt(block [i].replaceAll("\\s+", ""));
            if (temp != 0)
            {
                tempBlock = new NormBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int)((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
                tmo_blocks.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int) (i / height)), tempBlock);
            }
        }

    }

    public HashMap<String, Block> getTmo_blocks() {
        return tmo_blocks;
    }

    @Override
    public void render(Graphics2D g)
    {
        for (Block block : tmo_blocks.values())
        {
            block.render (g);
        }
    }
}
