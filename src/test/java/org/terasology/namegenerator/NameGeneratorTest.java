/*
 * Copyright 2013 MovingBlocks
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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;
import org.terasology.namegenerator.logic.generators.Markov2NameGenerator;

/**
 * Runs a few tests on {@link Markov2NameGenerator}.
 * @author Martin Steiger
 */
public class NameGeneratorTest {

    private static final long DEFAULT_SEED = 0xDEADBEEF;

    /**
     * Checks if the generated names have the specified length
     */
    @Test
    public void testLength() {
        Markov2NameGenerator nameGen = new Markov2NameGenerator(DEFAULT_SEED, Arrays.asList(ElvenMaleNames.NAMES));

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
        Markov2NameGenerator nameGen = new Markov2NameGenerator(DEFAULT_SEED, Arrays.asList(ElvenMaleNames.NAMES));

        for (int i = 0; i < 1000; i++) {
            String name = nameGen.nextName();
        
            assertNotNull(name);
            assertTrue(!name.isEmpty());
        }
    }    
}
