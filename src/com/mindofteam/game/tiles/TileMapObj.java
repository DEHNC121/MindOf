package com.mindofteam.game.tiles;

import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.tiles.blocks.*;
import com.mindofteam.game.util.AABB;
import com.mindofteam.game.util.Vector2f;

import java.awt.*;
import java.util.HashMap;



public class TileMapObj extends TileMap
{

    public static HashMap <String, Block> event_blocks;

    private int tileWidth;
    private int tileHeight;

    public static int width;
    public static int height;


    public TileMapObj (String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns)
    {
        Block tempBlock;
        event_blocks = new HashMap<String, Block>();


        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        TileMapObj.width = width;
        TileMapObj.height = height;

        String [] block = data.split(",");
        for (int i = 0; i < (width * height); ++i)
        {
            int temp = Integer.parseInt(block [i].replaceAll("\\s+", ""));
            if (temp != 0) {
                if (temp == 807) {
                    //No entry allowed

                    tempBlock = new ObjBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);

                } else if (temp == 1) {

                    tempBlock = new MessageBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
                } else if (temp == 2) {
                    tempBlock = new MoneyBlock("gold,not key,closed,1",sprite,sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight,(int) (i % width), (int) (i / height));

                }
                else if (temp == 3) {
                    tempBlock = new MoneyBlock("gold,key,closed,1",sprite,sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight,(int) (i % width), (int) (i / height));

                }
                else if (temp == 4) {
                    tempBlock = new MoneyBlock("silver,not key,closed,1",sprite,sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight,(int) (i % width), (int) (i / height));

                }
                else if (temp == 5) {
                    tempBlock = new MoneyBlock("silver,key,closed,1",sprite,sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight,(int) (i % width), (int) (i / height));

                }
                else if(temp==6){
                    tempBlock = new KeyBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight, sprite,(int) (i % width), (int) (i / height));
                }
                else if(temp==7){
                    tempBlock = new BedrockBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
                }
                else if(temp==8){
                    tempBlock = new JaskiniaBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
                }
                else if(temp==9){
                    tempBlock = new TrapBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight,0);
                }
                else if(temp==10){
                    tempBlock = new TrapBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight,1);
                }
                else {
                    //test
                    tempBlock = new NormBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);

                }

                event_blocks.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int) (i / height)), tempBlock);
            }
        }
    }


    public void render(Graphics2D g, AABB cam)
    {
        int x = (int) (cam.getPos ().getCamVar ().x / tileWidth);
        int y = (int) (cam.getPos ().getCamVar ().y / tileHeight);
        for (int i = x; i < x + (cam.getWidth () / tileWidth); i++)
        {
            for (int j = y; j < y + (cam.getHeight () / tileHeight); j++)
            {
                if (event_blocks.get(String.valueOf(i) + "," + String.valueOf(j))!= null)
                {
                    event_blocks.get(String.valueOf(i) + "," + String.valueOf(j)).render (g);
                }
            }
        }
    }
}
