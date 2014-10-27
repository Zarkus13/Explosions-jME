/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.threads;

import com.jme3.app.SimpleApplication;
import com.supinfo.explosions.ExplosionsApp;
import com.supinfo.explosions.geometries.Pigeon;
import java.util.concurrent.Callable;

/**
 *
 * @author alexis
 */
public class PigeonFactoryThread implements Runnable {
    
    public static final int NB_OF_PIGEONS = 1000;
    public static final int TIME_BETWEEN_PIGEONS = 1000;
    
    private ExplosionsApp app;
    private boolean running = true;
    
    public PigeonFactoryThread(final ExplosionsApp app) {
        this.app = app;
    }

    public void run() {
        try {
            for(int i = 0 ; i < NB_OF_PIGEONS && running ; i++) {
                System.out.println("pigeon !");
                
                final int id = i;
                app.enqueue(new Callable<Pigeon>() {

                    public Pigeon call() throws Exception {
                        Pigeon p = new Pigeon(
                            "pigeon " + id, 
                            app.getAssetManager(), 
                            app.getBulletAppState());
                        
                        app.getRootNode().attachChild(p);
                        
                        return p;
                    }
                });

                Thread.sleep(TIME_BETWEEN_PIGEONS);
            }
        }
        catch (Exception e) {
            System.out.println("Oups !");
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean run) {
        this.running = run;
    }
    
}
