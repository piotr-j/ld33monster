package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.piotrjastrzebski.monster.game.components.*;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class PhysUpdater extends EntityProcessingSystem {
	protected ComponentMapper<Position> mPosition;
	protected ComponentMapper<Bounds> mBounds;
	protected ComponentMapper<Phys> mPhys;
	protected ComponentMapper<Movement> mMovement;

	public PhysUpdater () {
		super(Aspect.all(Position.class, Bounds.class, Phys.class, Movement.class));
	}

	@Override protected void process (Entity e) {
		Phys phys = mPhys.get(e);
		Vector2 pos = phys.body.getPosition();
		Position position = mPosition.get(e);
		position.pos.set(pos);
		Bounds bounds = mBounds.get(e);
		bounds.bounds.setPosition(pos);
		Movement movement = mMovement.get(e);
		movement.vel.set(phys.body.getLinearVelocity());
	}
}
