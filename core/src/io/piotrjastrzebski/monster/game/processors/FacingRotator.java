package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.piotrjastrzebski.monster.game.components.Movement;
import io.piotrjastrzebski.monster.game.components.Player;
import io.piotrjastrzebski.monster.game.components.Rotation;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class FacingRotator extends EntityProcessingSystem {
	protected ComponentMapper<Movement> mMovement;
	protected ComponentMapper<Rotation> mRotation;

	public FacingRotator () {
		super(Aspect.all(Rotation.class, Movement.class));
	}

	@Override protected void process (Entity e) {
		Movement movement = mMovement.get(e);
		Rotation rotation = mRotation.get(e);
		Vector2 vel = movement.vel;
		if (vel.isZero()) return;
		if (Math.abs(vel.x) > Math.abs(vel.y)) {
			if (vel.x > 0) {
				rotation.rotation = 270;
			} else {
				rotation.rotation = 90;
			}
		} else {
			if (vel.y > 0) {
				rotation.rotation = 0;
			} else {
				rotation.rotation = 180;
			}
		}
	}
}
