// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.town;

import java.util.Arrays;
import java.util.List;

/**
 * Simple theme that works without assets
 */
public final class DebugTownTheme implements TownTheme {
    @Override
    public List<String> getPrefixes() {
        return Arrays.asList("Old", "New", "Market", "Upper", "Nether", "Little", "Lower", "Great", "Green");

    }

    @Override
    public List<String> getPostfixes() {
        return Arrays.asList("Crossing", "Cross", "Downs", "Island", "Bridge", "Barrens", "Point",
                "Shore", "Pond", "Barrow", "Hedge", "Crags", "Cliff",
                "Coast", "Edge", "Mill", "Field", "Bush", "Forest");
    }

    @Override
    public List<String> getNames() {
        return Arrays.asList("Dunley", "Woolford", "Guildcaster", "Keldchester", "Kirmere", "Otterhaven", "Westcaster"
                , "Kinhey",
                "Oulley", "Ilchester", "Oxpool", "Langthwaite", "Kirhaven", "Middleden", "Redwich", "Hazelthorpe",
                "Langmouth", "Hophall", "Hartfold", "Barleigh", "Norley", "Westmarsh", "Hazelburgh", "Southhurst",
                "Otterwick");
    }
}
