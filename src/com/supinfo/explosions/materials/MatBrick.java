/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.explosions.materials;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;

/**
 *
 * @author alexis
 */
public class MatBrick extends Material {
    
    public MatBrick(final AssetManager assetManager) {
        super(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        
        this.setTexture(
            "ColorMap", 
            assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg")
        );
    }
}
