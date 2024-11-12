package com.graphhopper.routing.util.parsers;

import com.graphhopper.reader.ReaderWay;
import com.graphhopper.routing.ev.EdgeIntAccess;
import com.graphhopper.routing.ev.EnumEncodedValue;
import com.graphhopper.routing.ev.Shade;
import com.graphhopper.storage.IntsRef;

public class OSMShadeParser implements TagParser {

    private final EnumEncodedValue<Shade> shadeEnc;

    public OSMShadeParser(EnumEncodedValue<Shade> shadeEnc) {
        this.shadeEnc = shadeEnc;
    }

    @Override
    public void handleWayTags(int edgeId, EdgeIntAccess edgeIntAccess, ReaderWay readerWay, IntsRef relationFlags) {
        String shadeTag = readerWay.getTag("shade");
        Shade shade = Shade.find(shadeTag);

        shadeEnc.setEnum(false, edgeId, edgeIntAccess, shade);
    }
}
