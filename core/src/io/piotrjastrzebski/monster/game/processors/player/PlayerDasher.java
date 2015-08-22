package io.piotrjastrzebski.monster.game.processors.player;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.MathUtils;
import io.piotrjastrzebski.monster.game.components.*;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class PlayerDasher extends EntityProcessingSystem {
	protected ComponentMapper<Dash> mDash;
	protected ComponentMapper<PlayerDash> mPlayerDash;
	protected ComponentMapper<Position> mPosition;
	protected ComponentMapper<Bounds> mBounds;
	protected ComponentMapper<Rotation> mRotation;
	protected ComponentMapper<Movement> mMovement;

	@Wire InputMultiplexer multiplexer;

	public PlayerDasher () {
		super(Aspect.all(Player.class, Position.class, Bounds.class, Rotation.class, Dash.class, Movement.class));
	}

	@Override protected void initialize () {
		super.initialize();
	}

	@Override protected void process (Entity e) {
		Dash dash = mDash.get(e);
		dash.timer -= world.delta;
		if (!mPlayerDash.has(e)) return;
		e.edit().remove(PlayerDash.class);

		// TODO energy?
		if (dash.timer > 0) return;
		dash.timer = dash.delay;

		// how do we dash? :(
		Gdx.app.log("", "Dash!");
		Movement movement = mMovement.get(e);
		int dirX = mRotation.get(e).dirX;
		int dirY = mRotation.get(e).dirY;
		if (dirX > 0) {
			movement.acc.x += dash.dst;
		} else if (dirX < 0) {
			movement.acc.x -= dash.dst;
		} else if (dirY > 0) {
			movement.acc.y += dash.dst;
		} else if (dirY < 0) {
			movement.acc.y -= dash.dst;
		}
	}
}
