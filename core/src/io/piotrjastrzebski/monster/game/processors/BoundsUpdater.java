package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.piotrjastrzebski.monster.game.components.*;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class BoundsUpdater extends EntityProcessingSystem {
	protected ComponentMapper<Position> mPosition;
	protected ComponentMapper<Bounds> mBounds;

	public BoundsUpdater () {
		super(Aspect.all(Position.class, Rotation.class, Bounds.class));
	}

	@Override protected void process (Entity e) {
		Vector2 position = mPosition.get(e).pos;
		Rectangle bounds = mBounds.get(e).bounds;
		bounds.setPosition(position);
	}
}
