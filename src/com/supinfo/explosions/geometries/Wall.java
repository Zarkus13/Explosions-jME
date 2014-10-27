/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.explosions.geometries;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import static com.supinfo.explosions.ExplosionsApp.SCENE_DISTANCE;
import static com.supinfo.explosions.ExplosionsApp.SCENE_HEIGHT;
import static com.supinfo.explosions.ExplosionsApp.SCENE_LENGTH;
import com.supinfo.explosions.materials.MatBrick;

/**
 *
 * @author alexis
 */
public class Wall extends Geometry {
    
    public static final float THICKNESS = 0.5f;
    public static final float DEPTH = 1f;
    
    private final RigidBodyControl control = new RigidBodyControl(0.0f);
    
    public Wall(
            final String name, 
            final Vector3f dim,
            final Vector3f pos,
            final AssetManager assetManager,
            final BulletAppState bulletAppState) {
        
        super(name, new Box(dim.x, dim.y, dim.z));
        this.setMaterial(new MatBrick(assetManager));
        
        this.setLocalTranslation(pos);
        
        this.addControl(control);
        bulletAppState.getPhysicsSpace().add(control);
    }
    
    public static void initWalls(final AssetManager assetManager, final Node node, final BulletAppState bulletAppState) {
        final RigidBodyControl control = new RigidBodyControl(0);
        
        final Wall bottomWall = new Wall(
            "Bottom wall", 
            new Vector3f(
                SCENE_LENGTH, 
                Wall.THICKNESS, 
                Wall.DEPTH
            ),
            new Vector3f(
                0, 
                -(SCENE_HEIGHT - Wall.THICKNESS), 
                SCENE_DISTANCE
            ),
            assetManager,
            bulletAppState);
        
        final Vector3f sideWallsDim = new Vector3f(
            Wall.THICKNESS, 
            SCENE_HEIGHT, 
            Wall.DEPTH
        );
        
        final Wall rightWall = new Wall(
            "Right wall", 
            sideWallsDim, 
            new Vector3f(
                SCENE_LENGTH - Wall.THICKNESS, 
                0, 
                SCENE_DISTANCE
            ),
            assetManager,
            bulletAppState);
        
        final Wall leftWall = new Wall(
            "Left wall", 
            sideWallsDim,
            new Vector3f(
                -(SCENE_LENGTH - Wall.THICKNESS),
                0, 
                SCENE_DISTANCE
            ),
            assetManager,
            bulletAppState);
        
        node.attachChild(bottomWall);
        node.attachChild(rightWall);
        node.attachChild(leftWall);
    }
    
}
