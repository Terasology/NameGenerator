// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator;

import org.junit.Test;
import org.terasology.namegenerator.waters.WaterNameProvider;

import static org.junit.Assert.assertFalse;

/**
 * Tests {@link WaterNameProvider}
 */
public class WaterNameProviderTest extends NameGeneratorTestingEnvironment {

    /**
     * A very simple test
     */
    @Test
    public void testBase() {

        WaterNameProvider prov = new WaterNameProvider(123455);

        for (int i = 0; i < 100; i++) {
            String name = prov.generateName();
            assertFalse(name.isEmpty());
        }
    }

}
