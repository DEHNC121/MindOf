package com.mindofteam.game.tiles;


import com.mindofteam.game.graphics.Sprite;
import com.mindofteam.game.tiles.blocks.TrapBlock;
import com.mindofteam.game.util.Camera;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class TileManager
{

    public ArrayList <TileMap> tm;
    public Camera cam;

    int t=0;
    public int k=0;
    public static int width;
    public static int height;
    public TileMap getTm(int i) {
        return tm.get(i);
    }

    public TileManager ()
    {
        tm = new ArrayList<TileMap>();
    }

    public TileManager (String path, Camera cam)
    {
        tm = new ArrayList<TileMap>();
        addTileMap (path, 64, 64, cam);
    }

    private void addTileMap (String path, int blockWidth, int blockHeight, Camera cam)
    {
        String imagePath;
        int width = 0;
        int height = 0;
        int tileWidth;
        int tileHeight;
        int tileCount;
        int tileColumns;
        int layers = 0;
        Sprite sprite;
        this.cam = cam;

        String [] data = new String [10];

        try
        {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(getClass().getClassLoader().getResourceAsStream(path));
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName ("tileset");
            Node node = list.item(0);
            Element eElement = (Element) node;

            imagePath = eElement.getAttribute("name");
            tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));
            tileCount = Integer.parseInt(eElement.getAttribute("tilecount"));
            tileColumns = Integer.parseInt(eElement.getAttribute("columns"));

            sprite = new Sprite("tile/" + imagePath + ".png", tileWidth, tileHeight);

            list = doc.getElementsByTagName ("layer");
            layers = list.getLength();

            for (int i = 0; i < layers; ++i)
            {
                node = list.item(i);
                eElement = (Element) node;
                if (i <= 0)
                {
                    width = Integer.parseInt(eElement.getAttribute("width"));
                    this.width=width;
                    height = Integer.parseInt(eElement.getAttribute("height"));
                    this.height=height;

                }

                data [i] = eElement.getElementsByTagName ("data").item (0).getTextContent ();

                if (i >= 1)
                {
                    tm.add(new TileMapNorm (data [i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                }
                else
                {
                    tm.add(new TileMapObj (data [i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                }

            }

        }
        catch (Exception e)
        {
            System.out.println("ERROR in TileManager: cannot read tilemap");
            System.out.println(e);
        }

    }

    public void render (Graphics2D g)
    {
        if (cam == null)
        {
            return;
        }
        if (tm.size()==7)
        {
            for (int i = 1; i < tm.size()-1; ++i)
            {
                if (i==tm.size()-2 && k==0)
                {
                    tm.get(i).render(g, cam.getBounds ());
                    t++;
                    if (t==15){k++;k=k%3;t=0;}
                }else if (i==tm.size()-2 && k==1)
                {
                    t++;
                    tm.get(i+1).render(g, cam.getBounds ());
                    if (t==15){k++;k=k%3;t=0;}
                }else if (i==tm.size()-2 && k==2)
                {
                    t++;
                    if (t==15){k++;k=k%3;t=0;}
                }else
                {
                    tm.get(i).render(g, cam.getBounds ());
                }

            }
        }else
            {
                for (int i = 1; i < tm.size(); ++i)
                {
                    if (i==2 && TrapBlock.done)
                    {
                        tm.get(i).render(g, cam.getBounds ());
                        i=5;
                    }else tm.get(i).render(g, cam.getBounds ());

                }
            }

    }


}
