// Copyright 2022 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.namegenerator;

import org.junit.jupiter.api.Test;
import org.terasology.namegenerator.region.RegionNameProvider;
import org.terasology.engine.integrationenvironment.jupiter.IntegrationEnvironment;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Tests {@link RegionNameProvider}
 */
@IntegrationEnvironment(dependencies = {"NameGenerator"})
public class RegionNameProviderTest {

    /**
     * Requires that original training data names do <b>NOT</b> contain any spaces
     */
    @Test
    public void testBase() {
        RegionNameProvider prov = new RegionNameProvider(123455);

        for (int i = 0; i < 100; i++) {
            String name = prov.generateName();
            assertFalse(name.isEmpty());
        }
    }

}
