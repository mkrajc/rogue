package org.mech.rougue.core;


import org.mech.rogue.game.model.map.Map;
import org.mech.rougue.core.game.model.map.generator.MapGenerator;
import org.mech.rougue.core.r.model.area.Area;

import org.mech.rougue.core.r.model.map.loader.MapExporter;
import org.mech.terminator.geometry.Dimension;

public class MapExporterTest01 {

    public static void main(String[] args) {
        MapExporter me = new MapExporter();
        Map map = MapGenerator.generateRoom("initial", Dimension.of(15));
        me.saveDefault(map, "initial");
    }
}
