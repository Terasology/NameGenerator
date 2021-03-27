// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.namegenerator;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.terasology.namegenerator.region.RegionNameProvider;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Tests {@link RegionNameProvider}
 */
@Tag("TteTest")
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
