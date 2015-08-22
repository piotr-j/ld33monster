package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntityEdit;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.piotrjastrzebski.monster.game.components.Renderable;
import io.piotrjastrzebski.monster.game.components.RenderableDef;
import io.piotrjastrzebski.monster.utils.Assets;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class AssetInit extends EntitySystem {
	@Wire Assets assets;

	protected ComponentMapper<RenderableDef> mRenderableDef;

	public AssetInit () {
		super(Aspect.all(RenderableDef.class));
	}

	@Override protected void inserted (int entityId) {
		RenderableDef def = mRenderableDef.get(entityId);
		EntityEdit edit = world.getEntity(entityId).edit();
		Renderable renderable = edit.create(Renderable.class);
		renderable.sprite = new TextureAtlas.AtlasSprite(assets.getRegion(def.path));
	}

	@Override protected void processSystem () {

	}
}
