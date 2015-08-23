package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import io.piotrjastrzebski.monster.game.components.Phys;
import io.piotrjastrzebski.monster.game.components.PhysSteerable;

/**
 * Created by PiotrJ on 23/08/15.
 */
@Wire
public class Steering extends EntityProcessingSystem {
	protected ComponentMapper<PhysSteerable> mPhysSteerable;
	protected ComponentMapper<Phys> mPhys;

	public Steering () {
		super(Aspect.all(PhysSteerable.class, Phys.class));
	}

	public static final SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<>(new Vector2());

	@Override protected void process (Entity e) {
		PhysSteerable steerable = mPhysSteerable.get(e);
		if (steerable.behaviour == null) return;

		Phys phys = mPhys.get(e);
		Body body = phys.body;
		steerable.setBody(body);

		steerable.behaviour.calculateSteering(steeringOutput);

		boolean anyAccelerations = false;

		// Update position and linear velocity.
		if (!steeringOutput.linear.isZero()) {
			// this method internally scales the force by deltaTime
			body.applyForceToCenter(steeringOutput.linear, true);
			anyAccelerations = true;
		}

		// Update orientation and angular velocity
		if (steerable.isIndependentFacing()) {
			if (steeringOutput.angular != 0) {
				// this method internally scales the torque by deltaTime
				body.applyTorque(steeringOutput.angular, true);
				anyAccelerations = true;
			}
		} else {
			// If we haven't got any velocity, then we can do nothing.
			Vector2 linVel = steerable.getLinearVelocity();
			if (!linVel.isZero(steerable.getZeroLinearSpeedThreshold())) {
				float newOrientation = vectorToAngle(linVel);
				body.setAngularVelocity((newOrientation - steerable.getAngularVelocity()) * world.delta); // this is superfluous if independentFacing is always true
				body.setTransform(body.getPosition(), newOrientation);
			}
		}

		if (anyAccelerations) {
			// Cap the linear speed
			Vector2 velocity = body.getLinearVelocity();
			float currentSpeedSquare = velocity.len2();
			float maxLinearSpeed = steerable.getMaxLinearSpeed();
			if (currentSpeedSquare > maxLinearSpeed * maxLinearSpeed) {
				body.setLinearVelocity(velocity.scl(maxLinearSpeed / (float)Math.sqrt(currentSpeedSquare)));
			}

			// Cap the angular speed
			float maxAngVelocity = steerable.getMaxAngularSpeed();
			if (body.getAngularVelocity() > maxAngVelocity) {
				body.setAngularVelocity(maxAngVelocity);
			}
		}
	}

	private float vectorToAngle (Vector2 vector) {
		return (float)Math.atan2(-vector.x, vector.y);
	}

	private Vector2 angleToVector (Vector2 outVector, float angle) {
		outVector.x = -(float)Math.sin(angle);
		outVector.y = (float)Math.cos(angle);
		return outVector;
	}
}
