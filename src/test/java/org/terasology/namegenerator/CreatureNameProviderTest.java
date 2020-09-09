// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.terasology.HeadlessEnvironment;
import org.terasology.gestalt.naming.Name;
import org.terasology.namegenerator.creature.CreatureAffinityVector;
import org.terasology.namegenerator.creature.CreatureNameProvider;

import static org.junit.Assert.assertTrue;

/**
 * Tests {@link CreatureNameProvider}
 */
public class CreatureNameProviderTest {

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
     *
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

        CreatureNameProvider prov = new CreatureNameProvider(123455);

        for (int i = 0; i < 100; i++) {
            String name = prov.generateName();
            assertTrue(name.contains(" "));
        }

        for (int i = 0; i < 100; i++) {
            CreatureAffinityVector aff = CreatureAffinityVector.create().maleOnly().nobility(1);
            String name = prov.generateName(aff);
            assertTrue("Name \"" + name + "\" has not the form <FIRST> <LAST> the <ATTR>", name.matches("[\\S]+ " +
                    "[\\S]+ the [\\S]+"));
        }
    }

}
