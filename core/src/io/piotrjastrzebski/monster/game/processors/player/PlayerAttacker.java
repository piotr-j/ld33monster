package io.piotrjastrzebski.monster.game.processors.player;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.utils.Array;
import io.piotrjastrzebski.monster.game.components.*;
import io.piotrjastrzebski.monster.game.processors.physics.Physics;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class PlayerAttacker extends EntityProcessingSystem {
	protected ComponentMapper<Attack> mAttack;
	protected ComponentMapper<PlayerAttack> mPlayerAttack;
	protected ComponentMapper<Position> mPosition;
	protected ComponentMapper<Bounds> mBounds;
	protected ComponentMapper<Rotation> mRotation;
	protected ComponentMapper<Movement> mMovement;
	protected ComponentMapper<Status> mStatus;
	protected ComponentMapper<Dead> mDead;

	@Wire InputMultiplexer multiplexer;
	@Wire Physics physics;

	public PlayerAttacker () {
		super(Aspect.all(Player.class, Position.class, Bounds.class, Rotation.class, Attack.class, Movement.class));
	}

	@Override protected void initialize () {
		super.initialize();
	}

	Array<Fixture> fixtures = new Array<>();
	RayCastCallback callback = new RayCastCallback() {
		@Override public float reportRayFixture (Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
			fixtures.add(fixture);
			return 1;
		}
	};

	Vector2 start = new Vector2();
	Vector2 target = new Vector2();
	@Override protected void process (Entity e) {
		Attack attack = mAttack.get(e);
		attack.timer -= world.delta;
		if (!mPlayerAttack.has(e)) return;
		e.edit().remove(PlayerAttack.class);

		// TODO energy?
		if (attack.timer > 0) return;
		attack.timer = attack.delay;

		// how do we attack? :(
		Gdx.app.log("", "Attack!");

		Rectangle bounds = mBounds.get(e).bounds;

		start.set(bounds.x + bounds.width / 2, bounds.y + bounds.height /2);
		target.set(start);
		int dirX = mRotation.get(e).dirX;
		int dirY = mRotation.get(e).dirY;
		if (dirX > 0) {
			target.x += attack.dst;
		} else if (dirX < 0) {
			target.x -= attack.dst;

		} else if (dirY > 0) {
			target.y += attack.dst;

		} else if (dirY < 0) {
			target.y -= attack.dst;

		} else {
			target.y += attack.dst;
		}

		fixtures.clear();
		physics.getWorld().rayCast(callback, start, target);
		Gdx.app.log("", "found " + fixtures.size);
		for (Fixture fixture : fixtures) {
			Physics.UserData userData = (Physics.UserData)fixture.getBody().getUserData();
			if (userData == null) continue;
			if (e.id == userData.entity) continue;
			if (mDead.has(userData.entity)) continue;

			Status status = mStatus.getSafe(userData.entity);
			if (status == null) continue;
			status.health -= attack.dmg;
			Gdx.app.log("", "attacked " + userData.entity);
		}
	}
}
