package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntityEdit;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import io.piotrjastrzebski.monster.game.components.Anim;
import io.piotrjastrzebski.monster.game.components.AnimDef;
import io.piotrjastrzebski.monster.utils.Assets;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class AnimInit extends EntitySystem {
	@Wire Assets assets;

	protected ComponentMapper<AnimDef> mAnimDef;

	public AnimInit () {
		super(Aspect.all(AnimDef.class));
		setPassive(true);
	}

	Array<TextureRegion> regions = new Array<>();
	@Override protected void inserted (int entityId) {
		regions.clear();
		AnimDef animDef = mAnimDef.get(entityId);
		EntityEdit edit = world.getEntity(entityId).edit();
		Anim anim = edit.create(Anim.class);
		anim.animation = new Animation(animDef.frame, assets.getRegions(animDef.path));
		anim.animation.setPlayMode(animDef.mode);
		anim.looping = animDef.looping;
	}

	@Override protected void processSystem () {

	}
}
