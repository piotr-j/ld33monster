package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import io.piotrjastrzebski.monster.game.components.*;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class Mover extends EntityProcessingSystem {
	protected ComponentMapper<Movement> mVelocity;
	protected ComponentMapper<Phys> mPhys;

	public Mover () {
		super(Aspect.all(Position.class, Movement.class, Phys.class));
	}

	@Override protected void process (Entity e) {
		Movement movement = mVelocity.get(e);

		Phys phys = mPhys.get(e);
		if (!movement.acc.isZero()) {
			phys.body.applyLinearImpulse(movement.acc, phys.body.getWorldCenter(), true);
		}
	}
}
