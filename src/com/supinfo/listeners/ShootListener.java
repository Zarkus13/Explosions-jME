/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.listeners;

import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.supinfo.explosions.ExplosionsApp;
import com.supinfo.explosions.geometries.CanonBall;

/**
 *
 * @author alexis
 */
public class ShootListener implements ActionListener {
    
    private ExplosionsApp app;

    public ShootListener(final ExplosionsApp app) {
        this.app = app;
    }

    public void onAction(String name, boolean isPressed, float tpf) {
        if(isPressed) {
            final CollisionResults results = new CollisionResults();

            Vector2f pos2d = app.getInputManager().getCursorPosition();
            Vector3f pos3d = app.getCamera().getWorldCoordinates(new Vector2f(pos2d.x, pos2d.y), 0f).clone();
            Vector3f dir = app.getCamera().getWorldCoordinates(new Vector2f(pos2d.x, pos2d.y), 1f).subtractLocal(pos3d).normalizeLocal();

            Ray ray = new Ray(pos3d, dir);

            app.getRootNode().collideWith(ray, results);

            if(results.getClosestCollision() != null) {
                Vector3f contactPoint = results.getClosestCollision().getContactPoint();
                Vector3f canonPos = app.getCanon().getLocalTranslation();

                Vector3f seg = new Vector3f(contactPoint.x - canonPos.x, contactPoint.y - canonPos.y, 0);
                
                float dist = Vector3f.ZERO.distance(seg);
                
                float x1 = seg.x * 1.0f / dist;
                float y1 = seg.y * 1.0f / dist;
                
                Vector3f impulse = new Vector3f(x1, y1, 0).mult(20);

                CanonBall ball = new CanonBall(impulse, app.getAssetManager(), app.getBulletAppState());
                app.getRootNode().attachChild(ball);
            }
        }
    }
    
}
