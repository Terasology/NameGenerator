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

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

import org.terasology.namegenerator.logic.generators.MarkovNameGenerator;

/**
 * Runs a few tests on {@link MarkovNameGenerator}.
 * @author Martin Steiger
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
        List<String> list = nameGen.generateList(1000, minLen, maxLen);
        
        for (String name : list) {
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

        int count = 1000;
        List<String> list = nameGen.generateList(count, 2, 8);
        
        assertTrue(list.size() == count);
    }    
}
