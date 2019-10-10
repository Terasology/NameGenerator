/*
 * Copyright 2019 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.namegenerator.region.adjective;

import org.terasology.utilities.random.MersenneRandom;
import org.terasology.utilities.random.Random;

import java.util.List;

public class RegionAdjectiveProvider {
    private final RegionAdjectiveTheme theme;
    private final Random random;
    private final List<String> prefixes;
    private final List<String> postfixes;
    private final RegionAdjectiveAffinityVector defaultAffinity = RegionAdjectiveAffinityVector.create();

    /**
     * Uses the default naming theme
     *
     * @param seed the seed
     */
    public RegionAdjectiveProvider(long seed) {
        this(seed, RegionAdjectiveAssetTheme.DEFAULT);
    }

    /**
     * @param seed  the seed value
     * @param aTheme the naming theme
     */
    public RegionAdjectiveProvider(long seed, RegionAdjectiveTheme aTheme) {

        random = new MersenneRandom(seed);
        theme = aTheme;
        prefixes = theme.getPrefixes();
        postfixes = theme.getPostfixes();
    }

    /**
     * @return a region adjective with default affinities
     */
    public String generateName() {
        return generateName(defaultAffinity.type(random.nextDouble(), random.nextDouble()));
    }

    /**
     * @param affinity the list of affinities
     * @return the region adjective
     */
    public synchronized String generateName(RegionAdjectiveAffinityVector affinity) {
        List<String> names = theme.getNames();
        String name = names.get((int)(names.size() * random.nextDouble()));

        int prefixIndex = (int) (affinity.getPrefixAffinity() * prefixes.size());
        String prefix = prefixes.get(prefixIndex);
        return (random.nextBoolean() && random.nextBoolean()) ? prefix + " " + name : name;

    }
}
