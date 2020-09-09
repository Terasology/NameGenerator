// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator;

import org.junit.Test;
import org.terasology.namegenerator.generators.MarkovNameGenerator;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Runs a few tests on {@link MarkovNameGenerator}.
 */
public class NameGeneratorTest {

    private static final long DEFAULT_SEED = 0xDEADBEEF;

    /**
     * Checks if the generated names have the specified length
     */
    @Test
    public void testLength() {
        MarkovNameGenerator nameGen = new MarkovNameGenerator(DEFAULT_SEED, Arrays.asList(ElvenMaleNames.NAMES));

        int minLen = 2;
        int maxLen = 8;

        for (int i = 0; i < 1000; i++) {
            String name = nameGen.nextName(minLen, maxLen);

            assertTrue("Length must be >= " + minLen, name.length() >= minLen);
            assertTrue("Length must be <= " + maxLen, name.length() <= maxLen);
        }
    }

    /**
     * Checks if the number of generated names is correct
     */
    @Test
    public void testCount() {
        MarkovNameGenerator nameGen = new MarkovNameGenerator(DEFAULT_SEED, Arrays.asList(ElvenMaleNames.NAMES));

        for (int i = 0; i < 1000; i++) {
            String name = nameGen.nextName();

            assertNotNull(name);
            assertTrue(!name.isEmpty());
        }
    }
}
