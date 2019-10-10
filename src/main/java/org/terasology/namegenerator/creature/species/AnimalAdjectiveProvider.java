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
package org.terasology.namegenerator.creature.species;

import org.terasology.namegenerator.generators.MarkovNameGenerator;
import org.terasology.namegenerator.generators.NameGenerator;
import org.terasology.utilities.random.MersenneRandom;
import org.terasology.utilities.random.Random;

import java.util.List;

public class AnimalAdjectiveProvider {
    private final AnimalAdjectiveTheme theme;
    private final Random random;
    private final List<String> prefixes;
    private final List<String> postfixes;
    private final AnimalAdjectiveAffinityVector defaultAffinity = AnimalAdjectiveAffinityVector.create();

    /**
     * Uses the default naming theme
     *
     * @param seed the seed
     */
    public AnimalAdjectiveProvider(long seed) {
        this(seed, AnimalAdjectiveAssetTheme.ENGLISH);
    }

    /**
     * @param seed  the seed value
     * @param aTheme the naming theme
     */
    public AnimalAdjectiveProvider(long seed, AnimalAdjectiveTheme aTheme) {

        random = new MersenneRandom(seed);
        theme = aTheme;
        prefixes = theme.getPrefixes();
        postfixes = theme.getPostfixes();
    }

    /**
     * @return a creature adjective with default affinities
     */
    public String generateName() {
        return generateName(defaultAffinity.type(random.nextDouble(), random.nextDouble()));
    }

    /**
     * @param affinity the list of affinities
     * @return the adjective
     */
    public synchronized String generateName(AnimalAdjectiveAffinityVector affinity) {
        List<String> names = theme.getNames();
        String name = names.get((int)(names.size() * random.nextDouble()));

        if (random.nextBoolean()) {
            int postfixIndex = (int) (affinity.getPostfixAffinity() * postfixes.size());
            String postfix = postfixes.get(postfixIndex);

            if (random.nextBoolean()) {
                int prefixIndex = (int) (affinity.getPrefixAffinity() * prefixes.size());
                String prefix = prefixes.get(prefixIndex);
                return random.nextBoolean() ? prefix + "-" + postfix : prefix + "-" + postfix + " " + name;
            } else {
                return name + "-" + postfix;
            }
        } else {
            return name;
        }
    }
}
