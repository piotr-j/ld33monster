package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.piotrjastrzebski.monster.game.components.*;
import io.piotrjastrzebski.monster.screens.Game;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class Renderer extends EntityProcessingSystem {
	protected ComponentMapper<Position> mPosition;
	protected ComponentMapper<Rotation> mRotation;
	protected ComponentMapper<Bounds> mBounds;
	protected ComponentMapper<Renderable> mRenderable;

	@Wire SpriteBatch batch;
	@Wire (name = Game.WIRE_GAME_CAM) OrthographicCamera camera;

	public Renderer () {
		super(Aspect.all(Position.class, Rotation.class, Renderable.class, Bounds.class).exclude(Invisible.class));
	}

	@Override protected void begin () {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}

	@Override protected void process (Entity e) {
		// TODO cull
		Vector2 position = mPosition.get(e).pos;
		float rotation = mRotation.get(e).rotation;
		Rectangle bounds = mBounds.get(e).bounds;
		Renderable renderable = mRenderable.get(e);
		TextureAtlas.AtlasSprite sprite = renderable.sprite;
		sprite.setOrigin(bounds.width / 2 , bounds.height / 2);
		sprite.setBounds(position.x, position.y, bounds.width, bounds.height);
		sprite.setRotation(rotation);
		sprite.draw(batch);
//		batch.draw(region,
//			position.pos.x, position.pos.y,
//			bounds.bounds.width / 2, bounds.bounds.height / 2,
//			bounds.bounds.width, bounds.bounds.height, 1, 1, rotation.rotation
//		);
	}

	@Override protected void end () {
		batch.end();
	}
}
