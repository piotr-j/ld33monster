package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import io.piotrjastrzebski.monster.game.processors.physics.Physics;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class PhysDef extends PooledComponent {
	public BodyDef.BodyType type = BodyDef.BodyType.StaticBody;
	public float restitution;
	public float friction;
	public float density;
	public float linearDamping;
	public boolean circle;
	public Vector2 velocity = new Vector2();
	public Physics.UserData userData;

	@Override protected void reset () {
		restitution = 0;
		friction = 0;
		density = 0;
		linearDamping = 0;
		type = BodyDef.BodyType.StaticBody;
		userData = null;
		velocity.setZero();
		circle = false;
	}

	public PhysDef set (float restitution, float friction, float density) {
		this.restitution = restitution;
		this.friction = friction;
		this.density = density;
		return this;
	}

	public PhysDef type (BodyDef.BodyType type) {
		this.type = type;
		return this;
	}

	public PhysDef linearDamping (float linearDamping) {
		this.linearDamping = linearDamping;
		return this;
	}

	public PhysDef userData (Physics.UserData userData) {
		this.userData = userData;
		return this;
	}

	public PhysDef velocity (float vx, float vy) {
		velocity.set(vx, vy);
		return this;
	}

	public PhysDef circle () {
		circle = true;
		return this;
	}
}
