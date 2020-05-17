package com.mindofteam.game.tiles.blocks;

import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.states.PlayState;
import com.mindofteam.game.tiles.TileMapNorm;
import com.mindofteam.game.tiles.TileMapObj;
import com.mindofteam.game.util.AABB;
import com.mindofteam.game.util.KeyHandler;
import com.mindofteam.game.util.Vector2f;

import java.awt.image.BufferedImage;
import java.awt.image.TileObserver;

public class MoneyBlock extends Block{

    private int x,y;
    static BufferedImage[] img= new BufferedImage[8];

    public String status;
    private boolean do_one;

    public MoneyBlock (String s,Sprite sprite, BufferedImage img, Vector2f pos, int w, int h, int x, int y) {
        super(img, pos, w,  h);
        this.x = x;
        this.y = y;

        this.do_one = true;

        this.status=s;

        //(gold) open + gold
        this.img[0]=sprite.getSprite(66, 40);
        this.img[1]=sprite.getSprite(67, 40);

        //(gold) open
        this.img[2]=sprite.getSprite(62, 40);
        this.img[3]=sprite.getSprite(63, 40);

        //(silver) open + gold
        this.img[4]=sprite.getSprite(64, 40);
        this.img[5]=sprite.getSprite(65, 40);

        //(silver) open
        this.img[6]=sprite.getSprite(60, 40);
        this.img[7]=sprite.getSprite(61, 40);
    }

    public void open(int i){

        String[] status=this.status.split(",");
        this.status=status[0]+","+status[1]+",open"+","+status[3];
        ((MoneyBlock)TileMapObj.event_blocks[x+(y*h)]).open();

        if (status[0].equals("gold")){
            TileMapNorm.blocks[x+((y-1)*h)]=new NormBlock(img[(i < 0) ? 1 : 0], new Vector2f((int) x * 64, (int) (y - 1) * 64), 64, 64);
            TileMapNorm.blocks[(x+ i)+((y-1)*h)]=new NormBlock(img[(i < 0) ? 0 : 1], new Vector2f((int) (x + i) * 64, (int) (y - 1) * 64), 64, 64);
//            ((TileMapNorm) PlayState.getTm().getTm(4)).g getEvent_blocks()[x+(y*h)].replace(String.valueOf(x) + "," + String.valueOf(y - 1), new NormBlock(img[(i < 0) ? 1 : 0], new Vector2f((int) x * 64, (int) (y - 1) * 64), 64, 64));
//            ((TileMapNorm) PlayState.getTm().getTm(4)).getTmo_blocks().replace(String.valueOf(x + i) + "," + String.valueOf(y - 1), new NormBlock(img[(i < 0) ? 0 : 1], new Vector2f((int) (x + i) * 64, (int) (y - 1) * 64), 64, 64));

        }

        if (status[0].equals("silver")){
//            ((TileMapNorm) PlayState.getTm().getTm(4)).getTmo_blocks().replace(String.valueOf(x) + "," + String.valueOf(y - 1), new NormBlock(img[(i < 0) ? 5 : 4], new Vector2f((int) x * 64, (int) (y - 1) * 64), 64, 64));
//            ((TileMapNorm) PlayState.getTm().getTm(4)).getTmo_blocks().replace(String.valueOf(x + i) + "," + String.valueOf(y - 1), new NormBlock(img[(i < 0) ? 4 : 5], new Vector2f((int) (x + i) * 64, (int) (y - 1) * 64), 64, 64));
            TileMapNorm.blocks[x+((y-1)*h)]=new NormBlock(img[(i < 0) ? 5 : 4], new Vector2f((int) x * 64, (int) (y - 1) * 64), 64, 64);
            TileMapNorm.blocks[(x+ i)+((y-1)*h)]=new NormBlock(img[(i < 0) ? 4 : 5], new Vector2f((int) (x + i) * 64, (int) (y - 1) * 64), 64, 64);
        }
    }

    public void open(){

        String[] status=this.status.split(",");
        this.status=status[0]+","+status[1]+",open,"+status[3];

    }
    public void take(int i){

        String[] status=this.status.split(",");
        this.status=status[0]+","+status[1]+","+status[2]+",0";

        ((MoneyBlock)TileMapObj.event_blocks[(x+i)+(y*h)]).take();
//        ((MoneyBlock)((TileMapObj) PlayState.getTm().getTm(0)).getTmo_blocks().get(String.valueOf(x +i) + "," + String.valueOf(y))).take();

        if (status[0].equals("gold")){
            PlayState.notify((int) ((Math.random()*((100-25)+1))+25));
//            ((TileMapNorm) PlayState.getTm().getTm(4)).getTmo_blocks().replace(String.valueOf(x) + "," + String.valueOf(y - 1), new NormBlock(img[(i < 0) ? 3 : 2], new Vector2f((int) x * 64, (int) (y - 1) * 64), 64, 64));
//            ((TileMapNorm) PlayState.getTm().getTm(4)).getTmo_blocks().replace(String.valueOf(x + i) + "," + String.valueOf(y - 1), new NormBlock(img[(i < 0) ? 2 : 3], new Vector2f((int) (x + i) * 64, (int) (y - 1) * 64), 64, 64));

        }

        if (status[0].equals("silver")){

            PlayState.notify((int) ((Math.random()*((50-5)+1))+5));
//            ((TileMapNorm) PlayState.getTm().getTm(4)).getTmo_blocks().replace(String.valueOf(x) + "," + String.valueOf(y - 1), new NormBlock(img[(i < 0) ? 7 : 6], new Vector2f((int) x * 64, (int) (y - 1) * 64), 64, 64));
//            ((TileMapNorm) PlayState.getTm().getTm(4)).getTmo_blocks().replace(String.valueOf(x + i) + "," + String.valueOf(y - 1), new NormBlock(img[(i < 0) ? 6: 7], new Vector2f((int) (x + i) * 64, (int) (y - 1) * 64), 64, 64));

        }
    }

    public void take(){

        String[] status=this.status.split(",");
        this.status=status[0]+","+status[1]+","+status[2]+",0";

    }

    public void setXY(int x,int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean update(AABB p) {
        if (!PlayState.do_id){
            do_one=true;
        }

        String[] status=this.status.split(",");
        int s;
        if (
                TileMapObj.event_blocks[(x+1)+(y*h)]!=null &&
                        TileMapObj.event_blocks[(x+1)+(y*h)].getClass().equals(MoneyBlock.class)
        ) {
            s=1;

        } else
            {
            s=-1;
        }
        if (status[2].equals("closed")){
            PlayState.notify("Pressing 'E' to open");
            if (PlayState.do_id&& do_one){
                open(s);
                do_one=false;
            }
        }else {
            if (status[3].equals("1")){
                PlayState.notify("Pressing 'E' take gold");
                if (PlayState.do_id && do_one){
                    take(s);
                    do_one=false;
                }
            }else {

            }
        }


        return false;
    }

    @Override
    public boolean isInside(AABB p) {
        return false;
    }
}
