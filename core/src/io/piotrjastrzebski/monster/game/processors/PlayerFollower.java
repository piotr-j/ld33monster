package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.piotrjastrzebski.monster.game.components.Bounds;
import io.piotrjastrzebski.monster.game.components.Movement;
import io.piotrjastrzebski.monster.game.components.Player;
import io.piotrjastrzebski.monster.game.components.Position;
import io.piotrjastrzebski.monster.screens.Game;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class PlayerFollower extends EntityProcessingSystem {
	protected ComponentMapper<Position> mPosition;
	protected ComponentMapper<Bounds> mBounds;
	@Wire(name = Game.WIRE_GAME_CAM) OrthographicCamera camera;
	public PlayerFollower () {
		super(Aspect.all(Player.class, Position.class, Bounds.class));
	}

	@Override protected void process (Entity e) {
		Vector2 pos = mPosition.get(e).pos;
		Rectangle bounds = mBounds.get(e).bounds;
		camera.position.set(pos.x + bounds.width / 2, pos.y + bounds.height /2, 0);
		camera.update();
	}
}
