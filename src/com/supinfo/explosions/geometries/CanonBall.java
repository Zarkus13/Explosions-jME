/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.explosions.geometries;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.supinfo.explosions.ExplosionsApp;

/**
 *
 * @author alexis
 */
public class CanonBall extends Geometry {
    
    private RigidBodyControl control = new RigidBodyControl(0.5f);
    
    public CanonBall(final Vector3f impulse, final AssetManager assetManager, final BulletAppState bulletAppState) {
        super("ball", new Sphere(32, 32, Canon.CANON_BALL_RADIUS));
        
        final Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor(
            "Color", 
            ColorRGBA.randomColor());
        
        this.setMaterial(mat);
        
        this.setLocalTranslation(
            0, 
            -(ExplosionsApp.SCENE_HEIGHT - 2 * Wall.THICKNESS), 
            ExplosionsApp.SCENE_DISTANCE);
        
        this.addControl(control);
        bulletAppState.getPhysicsSpace().add(control);
        
        control.applyImpulse(impulse, Vector3f.ZERO);
    }
}
