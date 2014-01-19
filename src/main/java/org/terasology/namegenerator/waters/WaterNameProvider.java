/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.namegenerator.waters;

import java.util.List;

import org.terasology.namegenerator.generators.MarkovNameGenerator;
import org.terasology.namegenerator.generators.NameGenerator;
import org.terasology.utilities.random.MersenneRandom;
import org.terasology.utilities.random.Random;

/**
 * Provides access to generated names. Thread-safe.
 * @author Martin Steiger
 */
public class WaterNameProvider {

    private final NameGenerator nameGen;
    private final Random random;
    private final List<String> waterTypes;

    /**
     * Uses the default naming theme
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
     * @return the town name
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
