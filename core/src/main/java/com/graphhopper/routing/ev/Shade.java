package com.graphhopper.routing.ev;

import com.graphhopper.util.Helper;

public enum Shade {
    YES, NO, PARTIAL, MISSING, OTHER;

    public static final String KEY = "shade";

    public static EnumEncodedValue<Shade> create() {
        return new EnumEncodedValue<>(KEY, Shade.class);
    }

    @Override
    public String toString() {
        return Helper.toLowerCase(super.toString());
    }

    public static Shade find(String name) {
        if (name == null)
            return MISSING;
        try {
            return Shade.valueOf(Helper.toUpperCase(name));
        } catch (IllegalArgumentException ex) {
            return OTHER;
        }
    }
}
