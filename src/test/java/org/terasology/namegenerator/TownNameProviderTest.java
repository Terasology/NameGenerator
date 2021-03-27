// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.namegenerator.town.TownAffinityVector;
import org.terasology.namegenerator.town.TownNameProvider;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests {@link TownNameProvider}
 */
@Tag("TteTest")
public class TownNameProviderTest extends NameGeneratorTestingEnvironment {

    private static final Logger logger = LoggerFactory.getLogger(TownNameProviderTest.class);

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
            assertTrue(name.contains(" "), "The name \"" + name + "\" does not contain an affix");
        }

        for (int i = 0; i < 1000; i++) {
            TownAffinityVector aff = TownAffinityVector.create().postfix(1);
            String name = townNameProv.generateName(aff);
            assertTrue(name.contains(" "), "The name \"" + name + "\" does not contain an affix");
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
    @Test
    public void testInvalidArgs1() {
        assertThrows(IllegalArgumentException.class, () -> {
            TownAffinityVector.create().prefix(1.5);
        });
    }

    /**
     * Combined affixes > 1
     */
    @Test
    public void testInvalidArgs2() {
        assertThrows(IllegalArgumentException.class, () -> {
            TownAffinityVector.create().prefix(0.5).postfix(0.7);
        });
    }
}
