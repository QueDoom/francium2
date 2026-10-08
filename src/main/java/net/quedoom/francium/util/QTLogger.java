package net.quedoom.francium.util;

import net.quedoom.francium.Francium;

public class QTLogger {
    public static <T> T logAndReturn(T value) {
        Francium.LOGGER.info(value.toString());
        return value;
    }
}
