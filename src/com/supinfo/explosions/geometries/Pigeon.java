/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.explosions.geometries;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import static com.supinfo.explosions.ExplosionsApp.SCENE_DISTANCE;
import static com.supinfo.explosions.ExplosionsApp.SCENE_HEIGHT;
import static com.supinfo.explosions.ExplosionsApp.SCENE_LENGTH;
import java.util.Random;

/**
 *
 * @author alexis
 */
public class Pigeon extends Geometry {
    public static final float WIDTH = 0.7f;
    
    private RigidBodyControl control = new RigidBodyControl(1);
    
    public Pigeon(final String name, final AssetManager assetManager, final BulletAppState bulletAppState) {
//        super(name, new Box(WIDTH, WIDTH, WIDTH));
        super(name, new Sphere(32, 32, WIDTH));
        
        final Random rand = new Random();
        
        final Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor(
            "Color", 
            new ColorRGBA(
                rand.nextFloat(), 
                rand.nextFloat(), 
                rand.nextFloat(), 
                1));
        
        this.setMaterial(mat);
        
        final float x = (rand.nextFloat() * SCENE_LENGTH) - WIDTH - Wall.THICKNESS;
        
        this.setLocalTranslation(
            rand.nextBoolean() ? x : -x, 
            SCENE_HEIGHT, 
            SCENE_DISTANCE
        );
        
        this.addControl(control);
        bulletAppState.getPhysicsSpace().add(control);
    }
    
}
