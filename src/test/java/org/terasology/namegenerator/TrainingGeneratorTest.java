// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.namegenerator;

import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.terasology.namegenerator.generators.TrainingGenerator;

import com.google.common.collect.Sets;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests {@link TrainingGenerator}
 */
public class TrainingGeneratorTest {

    /**
     * See if all names were generated when running over full size
     */
    @Test
    public void testEquals() {
        Set<String> trainingData = Sets.newHashSet(Arrays.asList(ElvenMaleNames.NAMES));
        TrainingGenerator tg = new TrainingGenerator(1234, trainingData);

        Set<String> gen = Sets.newHashSet();

        for (int i = 0; i < trainingData.size(); i++) {
            gen.add(tg.nextName());
        }

        assertEquals(trainingData, gen);
    }

    /**
     * See if all names were generated when running over full size twice
     */
    @Test
    public void testOverrun() {
        Set<String> trainingData = Sets.newHashSet(Arrays.asList(ElvenMaleNames.NAMES));
        TrainingGenerator tg = new TrainingGenerator(1234, trainingData);

        Set<String> gen = Sets.newHashSet();

        for (int i = 0; i < trainingData.size() * 2; i++) {
            gen.add(tg.nextName());
        }

        assertEquals(trainingData, gen);
    }
}
