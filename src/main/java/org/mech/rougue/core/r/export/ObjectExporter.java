package org.mech.rougue.core.r.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.r.model.area.Area;
import org.mech.rougue.core.r.model.combat.dmg.PhysicalDamage;
import org.mech.rougue.core.r.model.map.Map;
import org.mech.rougue.core.r.model.trap.DamageTrap;
import org.mech.rougue.utils.IOUtils;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;

public class ObjectExporter extends AbstractObjectManipulator{

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
		} catch (final IOException i) {
			i.printStackTrace();
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(fileOut);
		}
		
		return filename;
	}
	
	public static void main(final String[] args) {
		final Map map = new Map(Dimension.of(30), Tiles.ROOM_GROUND);
		map.setMapId("test_1");
		final Area area = new Area();
		area.setAreaId("north.forest");
		area.setTheme("north.forest");
		map.setArea(area);
		
		final DamageTrap damageTrap = new DamageTrap(new PhysicalDamage(10));
		damageTrap.setPosition(Position.at(2, 2));
		map.getGameObjects().add(damageTrap);
			
		
		 final ObjectExporter exporter = new ObjectExporter(Folders.MAP_DEFAULT_FOLDER);
		 exporter.serialize(map);
//		final Map newMap = new ObjectImporter("bin/").deserialize(name, Map.class);
		
//		System.out.println(newMap);
//		System.out.println(newMap.getSize());
//		System.out.println(newMap.getGameObjects().size());
//		System.out.println(newMap.getGameObjects().get(0));
	}
}
