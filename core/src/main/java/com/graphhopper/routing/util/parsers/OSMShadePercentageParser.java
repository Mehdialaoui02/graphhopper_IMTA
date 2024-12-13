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
        String shade = readerWay.getTag("shade");
        int shade_percentage = 101; // Missing input in OSM
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
        else if (shade != null) {
            System.out.println("shade: " + shade);
            shade = shade.toLowerCase();
            shade_percentage = switch (shade) {
                case "yes" -> 100;
                case "no" -> 0;
                case "partial" -> 50;
                default -> shade_percentage; // In case shade has a different value we keep 404
            };
        }
        if ((shade_percentage >= 0 && shade_percentage <= 100) || shade_percentage == 101) {
            shadePercentageEnc.setInt(false, edgeId, edgeIntAccess, shade_percentage);
        }
    }
}
