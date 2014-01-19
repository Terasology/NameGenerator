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

import org.terasology.namegenerator.generators.MarkovNameGenerator;
import org.terasology.namegenerator.generators.NameGenerator;
import org.terasology.namegenerator.generators.TrainingGenerator;
import org.terasology.utilities.random.MersenneRandom;
import org.terasology.utilities.random.Random;

/**
 * Provides access to generated names. Thread-safe.
 * @author Martin Steiger
 */
public class CreatureNameProvider {

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
    public CreatureNameProvider(long seed, CreatureAssetTheme theme) {

        random = new MersenneRandom(seed);
        
        maleNameGen = new MarkovNameGenerator(seed, theme.getMaleNames());
        femaleNameGen = new MarkovNameGenerator(seed, theme.getFemaleNames());
        surnameGen = new MarkovNameGenerator(seed, theme.getSurnames());
        nobilityGen = new TrainingGenerator(seed, theme.getNobilityAttributes());
    }
    
    /**
     * @return a town name without any affinities
     */
    public String generateName() {
        return generateName(new CreatureAffinityVector());
    }
    
    /**
     * @param affinity the list of affinities
     * @return the town name
     */
    public synchronized String generateName(CreatureAffinityVector affinity) {
        
        String surname = surnameGen.nextName();
        String firstName;

        // check for female names
        if (random.nextDouble() < affinity.getGenderRatio()) {
            firstName = maleNameGen.nextName();
        } else {
            firstName = femaleNameGen.nextName();
        }

        String name = firstName + " " + surname;
        
        if (random.nextDouble() < affinity.getNobility()) {
            name += " the " + nobilityGen.nextName();
        }
        
        return name;
    }
}
