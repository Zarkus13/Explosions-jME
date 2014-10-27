/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.listeners;

import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.AnalogListener;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.supinfo.explosions.ExplosionsApp;

/**
 *
 * @author alexis
 */
public class MoveCanonListener implements AnalogListener {
    
    private ExplosionsApp app;
    
    public MoveCanonListener(ExplosionsApp app) {
        this.app = app;
    }
    
    public void onAnalog(String name, float value, float tpf) {
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

            double tan = Math.tan(
                Math.abs(seg.y)/Math.abs(seg.x)
            );

            double rad = Math.atan(tan);

            System.out.println("contactPoint : " + contactPoint.x + " " + contactPoint.y);
            System.out.println("canon : " + canonPos.x + " " + canonPos.y);
            System.out.println("seg : " + seg.x + " " + seg.y);

//                    System.out.println("rad : " + tan + " " + rad + " " + Math.toDegrees(rad));

            Quaternion q = new Quaternion();
            q.fromAngleAxis((float) rad, new Vector3f(0, 0, 1));
            app.getCanon().setLocalRotation(q);

//                    System.out.println("angle : " + canon.getLocalTranslation().angleBetween(contactPoint));
//                    canon.setLocalRotation(new Quaternion(0, 0, canon.getLocalTranslation().angleBetween(contactPoint), 0));
        }
    }
}
