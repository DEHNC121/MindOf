package com.mindofteam.game.tiles;

import com.mindofteam.game.util.AABB;
import com.mindofteam.game.util.Camera;

import java.awt.*;

public abstract class TileMap
{
    public abstract void render (Graphics2D g, AABB cam);
}
