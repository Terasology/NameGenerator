/*
 * Copyright 2019 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.namegenerator;

import org.junit.Test;
import org.terasology.namegenerator.creature.species.AnimalNameAssetTheme;
import org.terasology.namegenerator.creature.species.AnimalNameProvider;
import org.terasology.namegenerator.creature.species.adjective.AnimalAdjectiveProvider;

import static org.junit.Assert.assertFalse;

/**
 * Tests {@link AnimalAdjectiveProvider}
 */
public class AnimalNameProviderTest extends NameGeneratorTestingEnvironment {

    /**
     * A very simple test
     */
    @Test
    public void testBase() {

        AnimalNameProvider prov = new AnimalNameProvider(123456, AnimalNameAssetTheme.RODENT);

        for (int i = 0; i < 100; i++) {
            String name = prov.generateName();
            System.out.println(name);
            assertFalse(name.isEmpty());
        }
    }

}
