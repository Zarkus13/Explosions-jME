package com.supinfo.explosions;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.supinfo.explosions.geometries.Pigeon;
import com.supinfo.explosions.geometries.Wall;
import com.supinfo.threads.PigeonFactoryThread;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * test
 * @author normenhansen
 */
public class ExplosionsApp extends SimpleApplication {
    
    public static final float SCENE_DISTANCE = -60;
    public static final float SCENE_HEIGHT = 20;
    public static final float SCENE_LENGTH = 40;
    
    private final BulletAppState bulletAppState = new BulletAppState();
    
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
    private PigeonFactoryThread pigeonFactoryThread;

    public static void main(String[] args) {
        ExplosionsApp app = new ExplosionsApp();
        app.setShowSettings(false);
        
        AppSettings settings = new AppSettings(true);
        settings.setResolution(1000, 600);
        settings.setBitsPerPixel(32);
        
        app.setSettings(settings);
        
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setEnabled(false);
        stateManager.attach(bulletAppState);

        Wall.initWalls(assetManager, rootNode, bulletAppState);
        
        this.pigeonFactoryThread = new PigeonFactoryThread(this);
        executor.execute(this.pigeonFactoryThread);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    @Override
    public void destroy() {
        super.destroy();
        this.pigeonFactoryThread.setRunning(false);
        executor.shutdown();
    }

    public BulletAppState getBulletAppState() {
        return bulletAppState;
    }
}
