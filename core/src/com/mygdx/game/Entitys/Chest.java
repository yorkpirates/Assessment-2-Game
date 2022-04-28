package com.mygdx.game.Entitys;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Renderable;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Managers.ResourceManager;

/**
 * Simple entity shown on locate quests origin
 */
public class Chest extends Entity {
    public Chest() {
        super(2);
        Transform t = new Transform();

        Renderable r;
        if(Application.ApplicationType.HeadlessDesktop == Gdx.app.getType()){
            r =new Renderable();
        }
        else{
            r = new Renderable(ResourceManager.getId("Chest.png"), RenderLayer.Transparent);
        }

        addComponents(t, r);
    }

    public void setPosition(Vector2 pos) {
        getComponent(Transform.class).setPosition(pos);
    }
}
