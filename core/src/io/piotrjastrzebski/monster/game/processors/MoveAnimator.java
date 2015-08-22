package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import io.piotrjastrzebski.monster.game.components.Anim;
import io.piotrjastrzebski.monster.game.components.Movement;
import io.piotrjastrzebski.monster.game.components.Renderable;
import io.piotrjastrzebski.monster.game.components.Rotation;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class MoveAnimator extends EntityProcessingSystem {
	protected ComponentMapper<Movement> mMovement;
	protected ComponentMapper<Anim> mAnim;
	protected ComponentMapper<Renderable> mRenderable;
	public MoveAnimator () {
		super(Aspect.all(Renderable.class, Anim.class, Movement.class));
	}

	@Override protected void process (Entity e) {
		Movement movement = mMovement.get(e);
		if (movement.vel.isZero(0.01f)) return;
		Anim anim = mAnim.get(e);
		anim.time += world.delta;
		Renderable renderable = mRenderable.get(e);
		renderable.sprite.setRegion(anim.animation.getKeyFrame(anim.time, anim.looping));
	}
}
