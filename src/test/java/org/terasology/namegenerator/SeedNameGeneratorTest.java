/*
 * Copyright 2014 MovingBlocks
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

package org.terasology.namegenerator;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.terasology.namegenerator.generators.MarkovNameGenerator;
import org.terasology.namegenerator.generators.TrainingGenerator;
import org.terasology.utilities.random.FastRandom;
import org.terasology.utilities.random.Random;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.*;

public class SeedNameGeneratorTest {

    private static final long DEFAULT_SEED = 0xDEADBEEF;

    @Test
    public void testTrainingGenerator() {
        Set<String> trainingData = Sets.newHashSet(Arrays.asList(ElvenMaleNames.NAMES));
        TrainingGenerator tg = new TrainingGenerator(1234, trainingData);

        Random random = new FastRandom(DEFAULT_SEED);

        for (int i = 0; i < 1000; i++) {
            int seed = random.nextInt();
            assertNotNull(tg.getName(seed));
            assertEquals(tg.getName(seed), tg.getName(seed));
        }
    }

    @Test
    public void testMarkovGenerator() {
        MarkovNameGenerator nameGen = new MarkovNameGenerator(DEFAULT_SEED, Arrays.asList(ElvenMaleNames.NAMES));

        Random random = new FastRandom(DEFAULT_SEED);

        for (int i = 0; i < 100; i++) {
            int seed = random.nextInt();
            assertNotNull(nameGen.getName(seed));
            assertEquals(nameGen.getName(seed), nameGen.getName(seed));
        }
    }
}
