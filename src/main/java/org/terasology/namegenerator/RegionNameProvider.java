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

package org.terasology.namegenerator;

import org.terasology.namegenerator.logic.generators.MarkovNameGenerator;
import org.terasology.namegenerator.logic.generators.NameGenerator;

/**
 * Provides access to generated names. Thread-safe.
 * @author Martin Steiger
 */
public class RegionNameProvider {

    private final NameGenerator nameGen;

    /**
     * Uses the default naming theme
     * @param seed the seed
     */
    public RegionNameProvider(long seed) {
        this(seed, RegionAssetTheme.REAL);
    }
    
    /**
     * @param seed the seed value
     * @param theme the naming theme
     */
    public RegionNameProvider(long seed, RegionAssetTheme theme) {

        nameGen = new MarkovNameGenerator(seed, theme.getNames());
    }
    
    /**
     * @return a town name without any affinities
     */
    public String generateName() {
        return generateName(new RegionAffinityVector());
    }
    
    /**
     * @param affinity the list of affinities
     * @return the town name
     */
    public synchronized String generateName(RegionAffinityVector affinity) {
        
        String name = nameGen.nextName();
        
        return name;
    }
}
