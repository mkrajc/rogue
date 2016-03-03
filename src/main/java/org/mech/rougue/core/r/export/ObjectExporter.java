package org.mech.rougue.core.r.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

import org.mech.rogue.game.export.Exportable;
import org.mech.rogue.game.model.map.Map;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.r.model.area.Area;
import org.mech.rougue.core.r.model.combat.dmg.PhysicalDamage;
import org.mech.rougue.core.r.model.door.Door;
import org.mech.rougue.core.r.model.inv.ItemMapObject;
import org.mech.rougue.core.r.model.inv.item.weapon.OneHandedWeapon;
import org.mech.rougue.core.r.model.inv.item.weapon.WeaponType;
import org.mech.rougue.core.r.model.map.gate.MapGate;
import org.mech.rougue.core.r.model.trap.DamageTrap;
import org.mech.rougue.utils.IOUtils;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectExporter<T extends Exportable> extends AbstractObjectManipulator {

    private final static Logger LOG = LoggerFactory.getLogger(ObjectExporter.class);

    public ObjectExporter(final String folder) {
        super(folder);
    }

    public String serialize(final T obj) {
        return serialize(obj, obj.objectId());
    }

    public String serialize(final T obj, final String name) {
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;
        final String filename = getFilename(name);
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

        final ObjectExporter exporter = new ObjectExporter(Folders.MAP_DEFAULT_FOLDER);
        exporter.serialize(getMap());
        //		final Map newMap = new ObjectImporter("bin/").deserialize(name, Map.class);

        //		System.out.println(newMap);
        //		System.out.println(newMap.getSize());
        //		System.out.println(newMap.getGameObjects().size());
        //		System.out.println(newMap.getGameObjects().get(0));
    }

    public static Map getMap() {
        final Area area = new Area();
        area.setAreaId("north.forest");
        area.setTheme("north.forest");
        final Map map = new Map("test_2", Dimension.of(50), area, Tiles.ROOM_GROUND);



        final MapGate gate = new MapGate(Position.at(15, 14), Position.at(9, 9), "test_1", "test_2");
        map.add(gate.twoWay());

        for (int i = 0; i < 7; i++) {
            createDoor(map);
            createDagger(map);
            createTrap(map);
        }


        return map;
    }

    private static void createTrap(Map map) {
        int x = new Random().nextInt(map.size().getWidth());
        int y = new Random().nextInt(map.size().getHeight());
        final DamageTrap damageTrap = new DamageTrap(new PhysicalDamage(10));
        final Position at = Position.at(x, y);
        damageTrap.setPosition(at);
        map.add(damageTrap);
    }

    private static void createDoor(Map map) {
        int x = new Random().nextInt(map.size().getWidth());
        int y = new Random().nextInt(map.size().getHeight());
        final Door door1 = new Door();
        door1.setPosition(Position.at(x, y));
        door1.setOpen(false);
        map.add(door1);
    }

    private static void createDagger(Map map) {
        int x = new Random().nextInt(map.size().getWidth());
        int y = new Random().nextInt(map.size().getHeight());

        final Position at = Position.at(x, y);
        final OneHandedWeapon dagger = new OneHandedWeapon();
        dagger.setWeaponType(WeaponType.DAGGER);
        dagger.setName("dagger " + at);

        final ItemMapObject itemMapObject = new ItemMapObject(dagger);
        itemMapObject.setPosition(at);

        map.add(itemMapObject);
    }
}
