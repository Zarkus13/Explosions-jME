/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.listeners;

import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.effect.shapes.EmitterSphereShape;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.supinfo.explosions.ExplosionsApp;

/**
 *
 * @author alexis
 */
public class CollisionListener implements PhysicsCollisionListener {
    
    private ExplosionsApp app;

    public CollisionListener(ExplosionsApp app) {
        this.app = app;
    }

    public void collision(PhysicsCollisionEvent event) {
        final String nameA = event.getNodeA().getName();
        final String nameB = event.getNodeB().getName();
        
        if((nameA.equals("ball") && nameB.equals("pigeon")) || (nameA.equals("pigeon") && nameB.equals("ball"))) {
            ParticleEmitter roundspark = new ParticleEmitter("RoundSpark", ParticleMesh.Type.Point, 20 * 1);
            roundspark.setStartColor(new ColorRGBA(1f, 0.29f, 0.34f, (float) (1.0 / 1.0f)));
            roundspark.setEndColor(new ColorRGBA(0, 0, 0, (float) (0.5f / 1.0f)));
            roundspark.setStartSize(1.2f);
            roundspark.setEndSize(1.8f);
            roundspark.setShape(new EmitterSphereShape(Vector3f.ZERO, 2f));
            roundspark.setParticlesPerSec(0);
            roundspark.setGravity(0, -.5f, 0);
            roundspark.setLowLife(1.8f);
            roundspark.setHighLife(2f);
            roundspark.setInitialVelocity(new Vector3f(0, 3, 0));
            roundspark.setVelocityVariation(.5f);
            roundspark.setImagesX(1);
            roundspark.setImagesY(1);
            Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Particle.j3md");
            mat.setTexture("Texture", app.getAssetManager().loadTexture("Effects/Explosion/roundspark.png"));
            mat.setBoolean("PointSprite", true);
            roundspark.setMaterial(mat);
            app.getRootNode().attachChild(roundspark);
        }
    }

    
}
