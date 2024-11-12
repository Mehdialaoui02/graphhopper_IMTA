package com.graphhopper.routing.ev;

public class ShadePercentage {
    public static final String KEY = "shade_percentage";

    public static IntEncodedValue create() {
        return new IntEncodedValueImpl(KEY, 7, false);
    }
}
