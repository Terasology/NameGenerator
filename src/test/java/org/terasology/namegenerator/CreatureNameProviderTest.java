/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.namegenerator;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.terasology.HeadlessEnvironment;
import org.terasology.namegenerator.creature.CreatureAffinityVector;
import org.terasology.namegenerator.creature.CreatureAssetTheme;
import org.terasology.namegenerator.creature.CreatureNameProvider;
import org.terasology.naming.Name;

/**
 * Tests {@link CreatureNameProvider}
 */
public class CreatureNameProviderTest extends NameGeneratorTestingEnvironment {

    /**
     * Requires that original training data names do <b>NOT</b> contain any spaces
     */
    @Test
    public void testBase() {

        CreatureNameProvider prov = new CreatureNameProvider(123455, CreatureAssetTheme.EDO);

        for (int i = 0; i < 100; i++) {
            String name = prov.generateName(CreatureAffinityVector.create());
            //System.out.println(name);
            assertTrue(name.contains(" "));
        }

        for (int i = 0; i < 100; i++) {
            CreatureAffinityVector aff = CreatureAffinityVector.create().maleOnly().nobility(1);
            String name = prov.generateName(aff);
            assertTrue("Name \"" + name + "\" has not the form <FIRST> <LAST> the <ATTR>", name.matches("[\\S]+ [\\S]+ [\\S]+"));
        }
    }

}
