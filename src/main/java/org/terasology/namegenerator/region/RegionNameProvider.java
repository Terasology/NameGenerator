// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.region;

import org.terasology.namegenerator.generators.MarkovNameGenerator;
import org.terasology.namegenerator.generators.NameGenerator;

/**
 * Provides access to generated names. Thread-safe.
 */
public class RegionNameProvider {

    private final NameGenerator nameGen;

    /**
     * Uses the default naming theme
     *
     * @param seed the seed
     */
    public RegionNameProvider(long seed) {
        this(seed, RegionAssetTheme.REAL);
    }

    /**
     * @param seed the seed value
     * @param theme the naming theme
     */
    public RegionNameProvider(long seed, RegionTheme theme) {

        nameGen = new MarkovNameGenerator(seed, theme.getNames());
    }

    /**
     * @return a region name without any affinities
     */
    public String generateName() {
        return generateName(new RegionAffinityVector());
    }

    /**
     * @param affinity the list of affinities
     * @return the region name
     */
    public synchronized String generateName(RegionAffinityVector affinity) {

        String name = nameGen.nextName();

        return name;
    }
}
