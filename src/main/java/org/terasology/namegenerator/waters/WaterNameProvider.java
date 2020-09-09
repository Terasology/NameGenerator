// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.waters;

import org.terasology.engine.utilities.random.MersenneRandom;
import org.terasology.engine.utilities.random.Random;
import org.terasology.namegenerator.generators.MarkovNameGenerator;
import org.terasology.namegenerator.generators.NameGenerator;

import java.util.List;

/**
 * Provides access to generated names. Thread-safe.
 */
public class WaterNameProvider {

    private final NameGenerator nameGen;
    private final Random random;
    private final List<String> waterTypes;

    /**
     * Uses the default naming theme
     *
     * @param seed the seed
     */
    public WaterNameProvider(long seed) {
        this(seed, WaterAssetTheme.ENGLISH);
    }

    /**
     * @param seed the seed value
     * @param theme the naming theme
     */
    public WaterNameProvider(long seed, WaterTheme theme) {

        random = new MersenneRandom(seed);

        nameGen = new MarkovNameGenerator(seed, theme.getNames());
        waterTypes = theme.getWaterTypes();
    }

    /**
     * @return a water name with default affinities
     */
    public String generateName() {
        double type = random.nextDouble();
        return generateName(WaterAffinityVector.create().type(type));
    }

    /**
     * @param affinity the list of affinities
     * @return the water name
     */
    public synchronized String generateName(WaterAffinityVector affinity) {

        String name = nameGen.nextName();

        // add an affix?
        double type = affinity.getWaterType();

        int typeIndex = (int) (type * waterTypes.size());

        String postFix = waterTypes.get(typeIndex);

        return name + " " + postFix;
    }
}
