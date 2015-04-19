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

package org.terasology.namegenerator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.HeadlessEnvironment;
import org.terasology.namegenerator.town.TownAffinityVector;
import org.terasology.namegenerator.town.TownNameProvider;
import org.terasology.naming.Name;

import static org.junit.Assert.*;

/**
 * Tests {@link TownNameProvider}
 */
public class TownNameProviderTest {

    private static final Logger logger = LoggerFactory.getLogger(TownNameProviderTest.class);
    private static HeadlessEnvironment env;

    /**
     * Setup headless environment
     */
    @BeforeClass
    public static void setUpClass() {
        env = new HeadlessEnvironment(new Name("NameGenerator"));
    }

    /**
     * Clean up
     * @throws Exception never
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
        env.close();
    }

    /**
     * Requires that original training data names do <b>NOT</b> contain any spaces
     */
    @Test
    public void testBase() {

        TownNameProvider townNameProv = new TownNameProvider(123455);

        for (int i = 0; i < 1000; i++) {
            String name = townNameProv.generateName();
            assertFalse(name.contains(" "));
        }

        for (int i = 0; i < 1000; i++) {
            TownAffinityVector aff = TownAffinityVector.create().prefix(1);
            String name = townNameProv.generateName(aff);
            assertTrue("The name \"" + name + "\" does not contain an affix", name.contains(" "));
        }

        for (int i = 0; i < 1000; i++) {
            TownAffinityVector aff = TownAffinityVector.create().postfix(1);
            String name = townNameProv.generateName(aff);
            assertTrue("The name \"" + name + "\" does not contain an affix", name.contains(" "));
        }
    }

    /**
     * Test affinity values != 1
     */
    @Test
    public void testAffinity() {
        TownNameProvider townNameProv = new TownNameProvider(123455);

        int hits = 0;
        for (int i = 0; i < 1000; i++) {
            TownAffinityVector aff = TownAffinityVector.create().postfix(0.5);
            String name = townNameProv.generateName(aff);
            if (name.contains(" ")) {
                hits++;
            }
        }

        logger.info("Towns with postfix out of 1000: " + hits);
        assertTrue(hits > 400 && hits < 600); // should be 50%
    }

    /**
     * Test combined affinity values
     */
    @Test
    public void testAffinityCombined() {
        TownNameProvider townNameProv = new TownNameProvider(123455);

        int hits = 0;
        for (int i = 0; i < 1000; i++) {
            TownAffinityVector aff = TownAffinityVector.create().prefix(0.5).postfix(0.5);
            String name = townNameProv.generateName(aff);
            if (name.contains(" ")) {
                hits++;
            }
        }

        logger.info("Towns with postfix out of 1000: " + hits);
        assertTrue(hits == 1000); // should be 50% + 50%
    }

    /**
     * Single affix > 1
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArgs1() {
        TownAffinityVector.create().prefix(1.5);
    }

    /**
     * Combined affixes > 1
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArgs2() {
        TownAffinityVector.create().prefix(0.5).postfix(0.7);
    }
}
