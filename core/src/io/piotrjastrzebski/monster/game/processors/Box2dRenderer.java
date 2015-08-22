package io.piotrjastrzebski.monster.game.processors;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import io.piotrjastrzebski.monster.game.processors.physics.Physics;
import io.piotrjastrzebski.monster.screens.Game;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class Box2dRenderer extends BaseSystem {
	@Wire Physics physics;
	@Wire(name = Game.WIRE_GAME_CAM) OrthographicCamera camera;
	Box2DDebugRenderer renderer;

	@Override protected void initialize () {
		super.initialize();
		renderer = new Box2DDebugRenderer();
	}

	@Override protected void processSystem () {
		renderer.render(physics.getWorld(), camera.combined);
	}
}
