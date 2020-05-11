package com.mindofteam.game.tiles;

import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.tiles.blocks.Block;
import com.mindofteam.game.tiles.blocks.HoleBlock;
import com.mindofteam.game.tiles.blocks.InfoBlock;
import com.mindofteam.game.tiles.blocks.ObjBlock;
import com.mindofteam.game.util.Vector2f;

import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;

public class TileMapObj extends TileMap
{

    public static HashMap <String, Block> tmo_blocks;

    public TileMapObj (String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns)
    {
        Block tempBlock;

        tmo_blocks = new HashMap<String, Block>();

        String [] block = data.split(",");
        for (int i = 0; i < (width * height); ++i)
        {
            int temp = Integer.parseInt(block [i].replaceAll("\\s+", ""));
            if (temp != 0)
            {
                //System.out.println(temp+"  <-");
                if (temp != 807)
                {
                    tempBlock = new ObjBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int)((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
                }
                else
                {
                    tempBlock = new ObjBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int)((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
                }
                tmo_blocks.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int) (i / height)), tempBlock);
            }
        }
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
