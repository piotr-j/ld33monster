package io.piotrjastrzebski.monster.game.processors.physics;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import io.piotrjastrzebski.monster.game.components.*;

/**
 * Created by EvilEntity on 15/08/2015.
 */
@Wire
public class PhysMaker extends EntitySystem {
	protected ComponentMapper<Bounds> mBounds;
	protected ComponentMapper<Position> mPosition;
	protected ComponentMapper<Rotation> mRotation;
	protected ComponentMapper<PhysDef> mPhysDef;
	protected ComponentMapper<Phys> mPhys;

	@Wire Physics physics;

	public PhysMaker () {
		super(Aspect.all(Bounds.class, Position.class, Rotation.class, PhysDef.class));
		setPassive(true);
	}

	BodyDef bodyDef;
	PolygonShape shape;
	FixtureDef fixtureDef;
	@Override protected void inserted (int eid) {
		Position position = mPosition.get(eid);
		Rotation rotation = mRotation.get(eid);
		Bounds bounds = mBounds.get(eid);
		PhysDef physDef = mPhysDef.get(eid);

		Phys phys = world.getEntity(eid).edit().create(Phys.class);
		if (bodyDef == null) bodyDef = new BodyDef();

		bodyDef.type = physDef.type;
		bodyDef.position.set(position.pos.x + bounds.bounds.width / 2, position.pos.y + bounds.bounds.height / 2);
		bodyDef.angle = rotation.rotation * MathUtils.degreesToRadians;
		bodyDef.linearDamping = physDef.linearDamping;

		phys.body = physics.getWorld().createBody(bodyDef);
		phys.body.setUserData(physDef.userData);

		if (shape == null) shape = new PolygonShape();
		shape.setAsBox(bounds.bounds.width / 2, bounds.bounds.height / 2);

		if (fixtureDef == null) fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.restitution = physDef.restitution;
		fixtureDef.friction = physDef.friction;

		phys.body.createFixture(fixtureDef);
	}

	@Override protected void processSystem () {
		// do nothing
	}

	@Override protected void removed (int entityId) {
		Phys phys = mPhys.get(entityId);
		physics.getWorld().destroyBody(phys.body);
	}

	@Override protected void dispose () {
		if (shape != null) shape.dispose();
	}
}
