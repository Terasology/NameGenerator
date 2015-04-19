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

package org.terasology.namegenerator.town;

import org.terasology.namegenerator.generators.MarkovNameGenerator;
import org.terasology.namegenerator.generators.NameGenerator;
import org.terasology.namegenerator.generators.TrainingGenerator;
import org.terasology.utilities.random.MersenneRandom;
import org.terasology.utilities.random.Random;

/**
 * Provides access to generated names. Thread-safe.
 */
public class TownNameProvider {

    private final NameGenerator nameGen;
    private final Random random;
    private final NameGenerator prefixGen;
    private final NameGenerator postfixGen;

    /**
     * Uses the default naming theme
     * @param seed the seed
     */
    public TownNameProvider(long seed) {
        this(seed, TownAssetTheme.ENGLISH);
    }

    /**
     * @param seed the seed value
     * @param theme the naming theme
     */
    public TownNameProvider(long seed, TownTheme theme) {

        random = new MersenneRandom(seed);

        nameGen = new MarkovNameGenerator(seed, theme.getNames());
        prefixGen = new TrainingGenerator(seed, theme.getPrefixes());
        postfixGen = new TrainingGenerator(seed, theme.getPostfixes());
    }

    /**
     * @return a town name without any affinities
     */
    public String generateName() {
        return generateName(new TownAffinityVector());
    }

    /**
     * @param affinity the list of affinities
     * @return the town name
     */
    public synchronized String generateName(TownAffinityVector affinity) {

        String name = nameGen.nextName();

        // add an affix?
        if (random.nextDouble() < affinity.getPrefixAffinity() + affinity.getPostfixAffinity()) {
            // try prefixes first
            if (random.nextDouble() < affinity.getPrefixAffinity()) {
                String postFix = postfixGen.nextName();
                return name + " " + postFix;
            }

            // then postfixes
            String preFix = prefixGen.nextName();
            return preFix + " " + name;
        }

        return name;
    }
}
