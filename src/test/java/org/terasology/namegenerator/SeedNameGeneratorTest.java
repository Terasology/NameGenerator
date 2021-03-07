// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.terasology.namegenerator.generators.MarkovNameGenerator;
import org.terasology.namegenerator.generators.TrainingGenerator;
import org.terasology.utilities.random.FastRandom;
import org.terasology.utilities.random.Random;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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
