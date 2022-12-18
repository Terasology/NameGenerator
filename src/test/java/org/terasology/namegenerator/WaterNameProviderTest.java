// Copyright 2022 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.namegenerator;

import org.junit.jupiter.api.Test;
import org.terasology.engine.integrationenvironment.jupiter.IntegrationEnvironment;
import org.terasology.namegenerator.waters.WaterNameProvider;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Tests {@link WaterNameProvider}
 */
@IntegrationEnvironment(dependencies = "NameGenerator")
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
