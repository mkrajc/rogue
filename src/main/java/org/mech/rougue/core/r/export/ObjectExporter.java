package org.mech.rougue.core.r.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.r.model.area.Area;
import org.mech.rougue.core.r.model.combat.dmg.PhysicalDamage;
import org.mech.rougue.core.r.model.map.Map;
import org.mech.rougue.core.r.model.map.gate.MapGate;
import org.mech.rougue.core.r.model.trap.DamageTrap;
import org.mech.rougue.utils.IOUtils;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectExporter extends AbstractObjectManipulator {

	private final static Logger LOG = LoggerFactory.getLogger(ObjectExporter.class);

	public ObjectExporter(final String folder) {
		super(folder);
	}

	public String serialize(final Exportable obj) {
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		final String filename = getFilename(obj.getObjectId());
		final File f = new File(filename);

		f.getParentFile().mkdirs();

		try {
			fileOut = new FileOutputStream(filename);
			out = new ObjectOutputStream(fileOut);
			out.writeObject(obj);
			LOG.info("Object serialize into [" + filename + "]");
		} catch (final IOException i) {
			i.printStackTrace();
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(fileOut);
		}

		return filename;
	}
	public static void main(final String[] args) {
		final Map map = new Map(Dimension.of(10), Tiles.ROOM_GROUND);
		map.setMapId("test_2");
		final Area area = new Area();
		area.setAreaId("north.forest");
		area.setTheme("north.forest");
		map.setArea(area);

		final DamageTrap damageTrap = new DamageTrap(new PhysicalDamage(10));
		damageTrap.setPosition(Position.at(2, 2));
		map.getGameObjects().add(damageTrap);

		final MapGate gate = new MapGate(Position.at(15, 14), Position.at(9, 9), "test_1", "test_2");
		map.getGameObjects().add(gate.twoWay());

		final ObjectExporter exporter = new ObjectExporter(Folders.MAP_DEFAULT_FOLDER);
		exporter.serialize(map);
		//		final Map newMap = new ObjectImporter("bin/").deserialize(name, Map.class);

		//		System.out.println(newMap);
		//		System.out.println(newMap.getSize());
		//		System.out.println(newMap.getGameObjects().size());
		//		System.out.println(newMap.getGameObjects().get(0));
	}
}
