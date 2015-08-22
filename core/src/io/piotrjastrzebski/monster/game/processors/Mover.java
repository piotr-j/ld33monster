package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import io.piotrjastrzebski.monster.game.components.*;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class Mover extends EntityProcessingSystem {
	protected ComponentMapper<Position> mPosition;
	protected ComponentMapper<Movement> mVelocity;


	public Mover () {
		super(Aspect.all(Position.class, Movement.class));
	}

	@Override protected void begin () {

	}

	Vector2 tmp = new Vector2();
	@Override protected void process (Entity e) {
		Vector2 position = mPosition.get(e).pos;
		Movement vel = mVelocity.get(e);
		Vector2 velocity = vel.vel;
		Vector2 acc = vel.acc;
		float friction = vel.friction;

		// apply friction
		velocity.add(tmp.set(velocity).scl(-friction));

		// calculate new position
		tmp.set(acc).scl(0.5f * world.delta * world.delta);
		position.add(tmp);
		tmp.set(velocity).scl(world.delta);
		position.add(tmp);

		// calculate new velocity
		tmp.set(acc).scl(world.delta);
		velocity.add(tmp);

/*
		Position position = mPosition.get(e);
		Movement movement = mMovement.get(e);

		// apply friction
		movement.vel.add(tmp.set(movement.vel).scl(movement.friction));

		// calculate new position
		tmp.set(movement.acc).scl(0.5f * world.delta * world.delta);
		position.pos.add(tmp);
		tmp.set(movement.vel).scl(world.delta);
		position.pos.add(tmp);

		// calculate new velocity
		tmp.set(movement.acc).scl(world.delta);
		movement.vel.add(tmp);
*/
	}

	@Override protected void end () {

	}
}
