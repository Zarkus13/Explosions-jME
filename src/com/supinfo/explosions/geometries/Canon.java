/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.explosions.geometries;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.supinfo.explosions.materials.MatBrick;

/**
 *
 * @author alexis
 */
public class Canon extends Node {
    
    public static final float CANON_BALL_RADIUS = 0.5f;
//    public static final float CANON_RADIUS = CANON_BALL_RADIUS + CANON_BALL_RADIUS * 0.1f;
    public static final float CANON_RADIUS = CANON_BALL_RADIUS;
    public static final float CANON_LENGTH = 3 * CANON_BALL_RADIUS;
    public static final float CANON_TANK_RADIUS = 2 * CANON_BALL_RADIUS;
    
    private final Geometry tank = new Geometry(
        "canon tank",
        new Sphere(32, 32, CANON_TANK_RADIUS));
    
    private final Geometry canon = new Geometry(
        "canon",
        new Box(
            CANON_RADIUS, 
            CANON_LENGTH, 
            CANON_RADIUS));
    
    public Canon(final AssetManager assetManager) {
        System.out.println("Canon radius : " + CANON_RADIUS);
        tank.setMaterial(new MatBrick(assetManager));
        canon.setMaterial(new MatBrick(assetManager));
        
        tank.rotate((float) Math.PI / 2, 0, 0);
        canon.setLocalTranslation(0, CANON_TANK_RADIUS, 0);
        
        this.attachChild(tank);
        this.attachChild(canon);
    }
    
}
