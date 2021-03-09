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

package org.terasology.namegenerator.creature;

import org.terasology.engine.utilities.random.MersenneRandom;
import org.terasology.engine.utilities.random.Random;
import org.terasology.namegenerator.generators.EmptyNameGenerator;
import org.terasology.namegenerator.generators.MarkovNameGenerator;
import org.terasology.namegenerator.generators.NameGenerator;
import org.terasology.namegenerator.generators.TrainingGenerator;

import java.util.List;

/**
 * Provides access to generated names. Thread-safe.
 */
public class CreatureNameProvider {

    private static final long SALT = 1337;

    private final NameGenerator surnameGen;
    private final Random random;
    private final NameGenerator nobilityGen;
    private final NameGenerator maleNameGen;
    private final NameGenerator femaleNameGen;

    /**
     * Uses the default naming theme
     * @param seed the seed
     */
    public CreatureNameProvider(long seed) {
        this(seed, CreatureAssetTheme.ELVEN);
    }

    /**
     * @param seed the seed value
     * @param theme the naming theme
     */
    public CreatureNameProvider(long seed, CreatureTheme theme) {

        random = new MersenneRandom(seed);

        final boolean markov = true;
        final boolean noMarkov = false;

        long saltedSeed = seed + SALT;
        maleNameGen = createNameGenerator(saltedSeed, theme.getMaleNames(), markov);

        saltedSeed += SALT;
        femaleNameGen = createNameGenerator(saltedSeed, theme.getFemaleNames(), markov);

        saltedSeed += SALT;
        surnameGen = createNameGenerator(saltedSeed, theme.getSurnames(), markov);

        saltedSeed += SALT;
        nobilityGen = createNameGenerator(saltedSeed, theme.getSurnames(), noMarkov);
    }

    /**
     * Factory method for {@code NameGenerator}s.
     *
     * @param seed the seed for the {@code NameGenerator}
     * @param nameList the list of names for the {@code NameGenerator}; if null or empty, a {@link EmptyNameGenerator} is used, regardless of {@code useMarkov}
     * @param useMarkov whether a {@link MarkovNameGenerator} should be used, or just a {@link TrainingGenerator}
     * @return an appropriately-constructed {@code NameGenerator}
     */
    private static NameGenerator createNameGenerator(long seed, List<String> nameList, boolean useMarkov) {
        if (nameList == null || nameList.isEmpty()) {
            return new EmptyNameGenerator();
        } else if (useMarkov) {
            return new MarkovNameGenerator(seed, nameList);
        } else {
            return new TrainingGenerator(seed, nameList);
        }
    }

    /**
     * @return a creature name without any affinities
     */
    public String generateName() {
        return generateName(new CreatureAffinityVector());
    }

    /**
     * @param affinity the list of affinities
     * @return the creature name
     */
    public String generateName(CreatureAffinityVector affinity) {
        return generateNameComponent(affinity).toString();
    }

    /**
     * @param affinity the list of affinities
     * @return the creature name
     */
    public synchronized CreatureNameComponent generateNameComponent(CreatureAffinityVector affinity) {

        CreatureNameComponent comp = new CreatureNameComponent();
        comp.lastName = surnameGen.nextName();

        // check for female names
        if (random.nextDouble() < affinity.getGenderRatio()) {
            comp.firstName = maleNameGen.nextName();
        } else {
            comp.firstName = femaleNameGen.nextName();
        }

        if (random.nextDouble() < affinity.getNobility()) {
            comp.attr = nobilityGen.nextName();
        }

        return comp;
    }
}
