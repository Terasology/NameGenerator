// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.namegenerator;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.terasology.moduletestingenvironment.MTEExtension;
import org.terasology.moduletestingenvironment.extension.Dependencies;
import org.terasology.namegenerator.waters.WaterNameProvider;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Tests {@link WaterNameProvider}
 */
@Tag("MteTest")
@ExtendWith(MTEExtension.class)
@Dependencies("NameGenerator")
public class WaterNameProviderTest {

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
