// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.terasology.namegenerator.creature.CreatureAffinityVector;
import org.terasology.namegenerator.creature.CreatureNameProvider;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests {@link CreatureNameProvider}
 */
@Tag("TteTest")
public class CreatureNameProviderTest extends NameGeneratorTestingEnvironment {

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
            assertTrue(name.matches("[\\S]+ [\\S]+ the [\\S]+"),
                    "Name \"" + name + "\" has not the form <FIRST> <LAST> the <ATTR>");
        }
    }
}
