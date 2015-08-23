package io.piotrjastrzebski.monster.game.processors.player;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.utils.Array;
import io.piotrjastrzebski.monster.game.components.*;
import io.piotrjastrzebski.monster.game.processors.physics.Physics;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class PlayerShooter extends EntityProcessingSystem {
	protected ComponentMapper<Shoot> mShoot;
	protected ComponentMapper<PlayerShoot> mPlayerShoot;
	protected ComponentMapper<Bounds> mBounds;
	protected ComponentMapper<Rotation> mRotation;
	protected ComponentMapper<Status> mStatus;
	protected ComponentMapper<Dead> mDead;

	public PlayerShooter () {
		super(Aspect.all(Player.class, Position.class, Bounds.class, Rotation.class, Shoot.class, Movement.class));
	}

	@Override protected void initialize () {
		super.initialize();
	}

	Vector2 start = new Vector2();
	@Override protected void process (Entity e) {
		Shoot shoot = mShoot.get(e);
		shoot.timer -= world.delta;
		if (!mPlayerShoot.has(e)) return;
		e.edit().remove(PlayerShoot.class);

		// TODO energy?
		if (shoot.timer > 0) return;
		shoot.timer = shoot.delay;


		Rectangle bounds = mBounds.get(e).bounds;

		start.set(bounds.x + bounds.width / 2, bounds.y + bounds.height /2);

		int dirX = mRotation.get(e).dirX;
		int dirY = mRotation.get(e).dirY;
		if (dirX > 0) {
			start.x += bounds.width;
		} else if (dirX < 0) {
			start.x -= bounds.width;
		} else if (dirY > 0) {
			start.y += bounds.height;
		} else if (dirY < 0) {
			start.y -= bounds.height;
		} else {
			start.y += bounds.height;
		}

		createProjectile(start.x, start.y, dirX * 3, dirY * 3, shoot.dmg, e.id);
	}

	private void createProjectile (float x, float y, float vx, float vy, final float dmg, final int id) {
		final EntityEdit edit = world.createEntity().edit();
		edit.create(Bounds.class).set(0, 0, 0.1f, 0.1f);
		edit.create(Rotation.class);
		edit.create(Position.class).set(x, y);
		edit.create(Projectile.class).dmg(dmg);
		edit.create(Facing.class);
		edit.create(DeleteAfter.class).after(2f);
		edit.create(PhysDef.class)
			.circle()
			.set(0.2f, 0.3f, 0f)
			.type(BodyDef.BodyType.DynamicBody)
			.linearDamping(0f)
			.velocity(vx, vy)
			.userData(new Physics.UserData(edit.getEntity()) {
				@Override public void onContact (Physics.UserData other) {
					world.getEntity(entity).deleteFromWorld();
					if (other == null)
						return;
					if (other.entity == id)
						return;
					if (mDead.has(other.entity))
						return;

					Status status = mStatus.getSafe(other.entity);
					if (status == null)
						return;
					status.health -= dmg;
				}
			});
	}
}
