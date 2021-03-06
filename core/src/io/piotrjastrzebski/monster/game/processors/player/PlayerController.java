package io.piotrjastrzebski.monster.game.processors.player;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import io.piotrjastrzebski.monster.game.components.*;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class PlayerController extends EntityProcessingSystem implements InputProcessor {
	protected ComponentMapper<Player> mPlayer;
	protected ComponentMapper<Movement> mMovement;

	@Wire InputMultiplexer multiplexer;

	public PlayerController () {
		super(Aspect.all(Player.class, Movement.class));
	}

	@Override protected void initialize () {
		super.initialize();
		multiplexer.addProcessor(this);
	}

	// TODO gradual response to mimic pad?
	float moveX;
	float moveY;
	boolean attack;
	boolean dash;
	boolean shoot;
	@Override protected void process (Entity e) {
		Player player = mPlayer.get(e);
		Movement movement = mMovement.get(e);
		Vector2 acc = movement.acc;
		if (moveY > 0.01f) {
			acc.y = player.accel;
		} else if (moveY < -0.01f) {
			acc.y = -player.accel;
		} else {
			acc.y = 0;
		}

		if (moveX > 0.01f) {
			acc.x = player.accel;
		} else if (moveX < -0.01f) {
			acc.x = -player.accel;
		} else {
			acc.x = 0;
		}
		acc.limit(player.accel);
		acc.scl(speed);
		if (attack) {
			e.edit().create(PlayerAttack.class);
		}
		if (dash) {
			e.edit().create(PlayerDash.class);
		}
		if (shoot) {
			e.edit().create(PlayerShoot.class);
		}
 	}

	float speed = 1;
	@Override public boolean keyDown (int keycode) {
		switch (keycode) {
		case Input.Keys.UP:
		case Input.Keys.W: {
			moveY += 1;
			break;
		}
		case Input.Keys.DOWN:
		case Input.Keys.S: {
			moveY += -1;
			break;
		}
		case Input.Keys.LEFT:
		case Input.Keys.A: {
			moveX += -1;
			break;
		}
		case Input.Keys.RIGHT:
		case Input.Keys.D: {
			moveX += 1;
			break;
		}
		case Input.Keys.CONTROL_LEFT:
		case Input.Keys.CONTROL_RIGHT: {
			dash = true;
			break;
		}
		case Input.Keys.Q:
		case Input.Keys.E: {
			shoot = true;
			break;
		}
		case Input.Keys.SPACE: {
			attack = true;
			break;
		}
		case Input.Keys.SHIFT_LEFT:
		case Input.Keys.SHIFT_RIGHT: {
			speed = 5;
			break;
		}
		}
		return true;
	}

	@Override public boolean keyUp (int keycode) {
		switch (keycode) {
		case Input.Keys.UP:
		case Input.Keys.W: {
			moveY -= 1;
			break;
		}
		case Input.Keys.DOWN:
		case Input.Keys.S: {
			moveY -= -1;
			break;
		}
		case Input.Keys.LEFT:
		case Input.Keys.A: {
			moveX -= -1;
			break;
		}
		case Input.Keys.RIGHT:
		case Input.Keys.D: {
			moveX -= 1;
			break;
		}
		case Input.Keys.CONTROL_LEFT:
		case Input.Keys.CONTROL_RIGHT: {
			dash = false;
			break;
		}
		case Input.Keys.Q:
		case Input.Keys.E: {
			shoot = false;
			break;
		}
		case Input.Keys.SPACE: {
			attack = false;
			break;
		}
		case Input.Keys.SHIFT_LEFT:
		case Input.Keys.SHIFT_RIGHT: {
			speed = 1;
			break;
		}
		}
		return true;
	}

	@Override public boolean keyTyped (char character) {
		return false;
	}

	@Override public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override public boolean touchDragged (int screenX, int screenY, int pointer) {
		return false;
	}

	@Override public boolean mouseMoved (int screenX, int screenY) {
		return false;
	}

	@Override public boolean scrolled (int amount) {
		return false;
	}
}
