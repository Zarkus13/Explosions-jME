package com.supinfo.explosions;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.collision.CollisionResults;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.supinfo.explosions.geometries.Canon;
import com.supinfo.explosions.geometries.CanonBall;
import com.supinfo.explosions.geometries.Wall;
import com.supinfo.listeners.CollisionListener;
import com.supinfo.listeners.MoveCanonListener;
import com.supinfo.listeners.ShootListener;
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
    
    private Canon canon;
    private Geometry frontWall;

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
//        flyCam.setMoveSpeed(10);
        stateManager.attach(bulletAppState);
        
        bulletAppState.getPhysicsSpace().addCollisionListener(new CollisionListener(this));
        
//        initCrossHairs();
        
//        inputManager.addMapping(
//            "move", 
//            new MouseAxisTrigger(MouseInput.AXIS_X, true),
//            new MouseAxisTrigger(MouseInput.AXIS_X, false),
//            new MouseAxisTrigger(MouseInput.AXIS_Y, true),
//            new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputManager.addListener(new MoveCanonListener(this), "move");
        
        inputManager.addMapping("shoot", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(new ShootListener(this), "shoot");

        initFrontWall();
        Wall.initWalls(assetManager, rootNode, bulletAppState);
        
        canon = new Canon(assetManager);
        canon.setLocalTranslation(0, -(SCENE_HEIGHT - 2 * Wall.THICKNESS), SCENE_DISTANCE);
        rootNode.attachChild(canon);
        
        this.pigeonFactoryThread = new PigeonFactoryThread(this);
        executor.execute(this.pigeonFactoryThread);
    }
    
    private void initFrontWall() {
        final Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        
        frontWall = new Geometry(
            "frontwall", 
            new Box(SCENE_LENGTH, SCENE_HEIGHT, 0)
        );
        
        frontWall.setMaterial(mat);
        frontWall.setLocalTranslation(0, 0, SCENE_DISTANCE + Wall.DEPTH);
        frontWall.setCullHint(Spatial.CullHint.Always);
        
        final RigidBodyControl control = new RigidBodyControl(0);
        frontWall.addControl(control);
        bulletAppState.getPhysicsSpace().add(control);
        
        rootNode.attachChild(frontWall);
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

    public Canon getCanon() {
        return canon;
    }
}
