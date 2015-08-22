package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import io.piotrjastrzebski.monster.game.components.*;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class Renderer extends EntityProcessingSystem {
	protected ComponentMapper<Position> mPosition;
	protected ComponentMapper<Rotation> mRotation;
	protected ComponentMapper<Bounds> mBounds;
	protected ComponentMapper<Renderable> mRenderable;



	public Renderer () {
		super(Aspect.all(Position.class, Rotation.class, Renderable.class, Bounds.class).exclude(Invisible.class));
	}

	@Override protected void begin () {

	}

	@Override protected void process (Entity e) {
		Position position = mPosition.get(e);
		Rotation rotation = mRotation.get(e);
		Bounds bounds = mBounds.get(e);
		Renderable renderable = mRenderable.get(e);
	}

	@Override protected void end () {

	}
}
