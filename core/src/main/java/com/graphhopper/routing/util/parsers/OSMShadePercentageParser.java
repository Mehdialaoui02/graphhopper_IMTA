package com.graphhopper.routing.util.parsers;

import com.graphhopper.reader.ReaderWay;
import com.graphhopper.routing.ev.EdgeIntAccess;
import com.graphhopper.routing.ev.IntEncodedValue;
import com.graphhopper.storage.IntsRef;

public class OSMShadePercentageParser implements TagParser {
    private final IntEncodedValue shadePercentageEnc;

    public OSMShadePercentageParser(IntEncodedValue shadePercentageEnc) {
        this.shadePercentageEnc = shadePercentageEnc;
    }

    @Override
    public void handleWayTags(int edgeId, EdgeIntAccess edgeIntAccess, ReaderWay readerWay, IntsRef relationFlags) {
        String percentage = readerWay.getTag("shade:percentage");
        int shade_percentage = -1;
        if (percentage != null) {
            try {
                if (percentage.endsWith("%")) {
                    shade_percentage = Integer.parseInt(percentage.substring(0, percentage.length() - 1));
                } else {
                    shade_percentage = Integer.parseInt(percentage);
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        if (shade_percentage >= 0 && shade_percentage <= 100) {
            shadePercentageEnc.setInt(false, edgeId, edgeIntAccess, shade_percentage);
        }
    }
}
