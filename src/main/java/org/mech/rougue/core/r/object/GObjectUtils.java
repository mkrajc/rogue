package org.mech.rougue.core.r.object;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class GObjectUtils {

    public static <T> List<T> getObjectsOfType(List gObjects, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (gObjects != null) {
            for (Object gObject : gObjects) {
                if (clazz.isAssignableFrom(gObject.getClass())) {
                    list.add((T)gObject);
                }
            }
        }

        return list;
    }

    public static <T> T getObjectOfType(List gObjects, final Class<T> clazz) {
        final List<T> objs = getObjectsOfType(gObjects, clazz);
        if (objs != null && objs.size() > 1) {
            throw new IllegalStateException("More objects in context");
        }
        return objs == null || objs.isEmpty() ? null : objs.get(0);
    }

}
