package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.piotrjastrzebski.monster.game.components.Bounds;
import io.piotrjastrzebski.monster.game.components.Position;
import io.piotrjastrzebski.monster.game.components.Renderable;
import io.piotrjastrzebski.monster.game.components.Rotation;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class DebugRenderer extends EntityProcessingSystem {
	protected ComponentMapper<Position> mPosition;
	protected ComponentMapper<Rotation> mRotation;
	protected ComponentMapper<Bounds> mBounds;

	@Wire(name = "game-cam") OrthographicCamera camera;
	@Wire ShapeRenderer renderer;

	public DebugRenderer () {
		super(Aspect.all(Position.class, Rotation.class, Bounds.class));
	}

	@Override protected void begin () {
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeRenderer.ShapeType.Line);
	}

	@Override protected void process (Entity e) {
		Vector2 pos = mPosition.get(e).pos;
		float rotation = mRotation.get(e).rotation;
		Rectangle bounds = mBounds.get(e).bounds;
		renderer.rect(pos.x, pos.y, bounds.width / 2, bounds.height / 2, bounds.width, bounds.height, 1, 1, rotation);
	}

	@Override protected void end () {
		renderer.end();
	}
}
