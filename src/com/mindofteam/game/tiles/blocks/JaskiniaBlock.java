package com.mindofteam.game.tiles.blocks;

import com.mindofteam.game.GamePanel;
import com.mindofteam.game.states.MenuState;
import com.mindofteam.game.states.PlayState;
import com.mindofteam.game.tiles.Message;
import com.mindofteam.game.tiles.TileManager;
import com.mindofteam.game.tiles.TileMap;
import com.mindofteam.game.tiles.TileMapObj;
import com.mindofteam.game.util.AABB;
import com.mindofteam.game.util.Camera;
import com.mindofteam.game.util.Vector2f;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class JaskiniaBlock extends Block {

    public static int goX;
    public static int goY;

    public static float X;
    public static float Y;

    public static int w;
    public static int h;

    public static Vector2f pos;

    private boolean do_one;

    public static HashMap<String, Block> hm;

    private static Camera cam1;

    public static Vector2f map1;
    private static TileManager tm1;

    public JaskiniaBlock(BufferedImage img, Vector2f pos, int w, int h) {
        super(img, pos, w, h);

    }

    public void go(){

        if (tm1==null)
        {
            w= TileManager.width;
            h= TileManager.height;
            hm= TileMapObj.event_blocks;
            goX=800;
            goY=1150;
            X=700;
            Y=1400;
            map1 = new Vector2f(goX- GamePanel.width/2,goY-GamePanel.height/2);
            //Vector2f.setWorldVar(map1.x, map1.y);
            pos=new Vector2f(goX , goY);
            cam1 = new Camera(new AABB (new Vector2f (), GamePanel.width, GamePanel.height));
            tm1 = new TileManager("tile/test1.xml", cam1);//
        }else {

            HashMap<String, Block> temphm;

            temphm=TileMapObj.event_blocks;
            TileMapObj.event_blocks=hm;
            hm= temphm;


            int tempW,tempH;

            tempH= TileManager.height;
            tempW= TileManager.width;

            TileManager.width=w;
            TileManager.height=h;

            w=tempW;
            h=tempH;
        }



//        Vector2f tempV;
//
//        tempV=PlayState.getPlayer().getPos();
//        PlayState.getPlayer().setPos(pos);
//        pos=tempV;

        int temp;

        temp=MenuState.startX;
        MenuState.startX=goX;
        goX=temp;


        temp=MenuState.startY;
        MenuState.startY=goY;
        goY=temp;

        Vector2f tempMap;

        tempMap=PlayState.map;
        PlayState.map=map1;
        map1=tempMap;

        //Vector2f.setWorldVar(PlayState.map.x, PlayState.map.y);

        Camera tempCam;

        tempCam=PlayState.cam;
        PlayState.cam=cam1;
        cam1=tempCam;

        TileManager tempTm;

        tempTm=PlayState.getTm();
        PlayState.setTm(tm1);
        tm1=tempTm;



        float tempY,tempX;

        tempX=PlayState.getPlayer().getPos().x;
        tempY=PlayState.getPlayer().getPos().y;

        PlayState.getPlayer().getPos().x=X;
        PlayState.getPlayer().getPos().y=Y;

        X=tempX;
        Y=tempY;




    }

    @Override
    public boolean update(AABB p) {
        if (!PlayState.do_id){
            do_one=true;
        }
        PlayState.notify("Press 'E' to go");
        if (PlayState.do_id && do_one){
            go();
            do_one=false;
        }
        return false;
    }

    @Override
    public boolean isInside(AABB p) {
        return false;
    }
}
