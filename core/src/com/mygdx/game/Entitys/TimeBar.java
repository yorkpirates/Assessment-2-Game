package com.mygdx.game.Entitys;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Components.Renderable;
import com.mygdx.game.Components.Transform;
import com.mygdx.game.Managers.RenderLayer;
import com.mygdx.game.Managers.ResourceManager;

/**
 * @author Rrryaan
 * @date 2022/3/5 16:36
 * @brief
 */
public class TimeBar extends Entity {

    public TimeBar() {
        super(2);
        Transform t = new Transform();
        Renderable r = new Renderable(ResourceManager.getId("timeline.jpg"), RenderLayer.Transparent);
        addComponents(t, r);
    }

    public void setPosition(Vector2 pos) {
        getComponent(Transform.class).setPosition(pos);
    }

}
