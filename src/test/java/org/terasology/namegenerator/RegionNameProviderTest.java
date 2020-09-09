// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator;

import org.junit.Test;
import org.terasology.namegenerator.region.RegionNameProvider;

import static org.junit.Assert.assertFalse;

/**
 * Tests {@link RegionNameProvider}
 */
public class RegionNameProviderTest extends NameGeneratorTestingEnvironment {

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
