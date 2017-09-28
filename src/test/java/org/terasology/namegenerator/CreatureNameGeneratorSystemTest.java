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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.entity.internal.EngineEntityManager;
import org.terasology.entitySystem.entity.lifecycleEvents.OnAddedComponent;
import org.terasology.logic.common.DisplayNameComponent;
import org.terasology.namegenerator.creature.CreatureNameComponent;
import org.terasology.namegenerator.creature.CreatureNameGeneratorComponent;
import org.terasology.namegenerator.creature.CreatureNameGeneratorSystem;
import org.terasology.registry.CoreRegistry;

/**
 * Tests {@link CreatureNameGeneratorSystem}
 */
public class CreatureNameGeneratorSystemTest extends NameGeneratorTestingEnvironment {

    private static final Logger logger = LoggerFactory.getLogger(CreatureNameGeneratorSystemTest.class);

    /**
     * Test subroutine.  Generates {@code n} names and logs them.  Asserts nothing.
     *
     * @param genComp the {@code Component} configuring the name generator
     * @param n the number of names to generate
     */
    private void generateNames(CreatureNameGeneratorComponent genComp, int n) {
        CreatureNameGeneratorSystem cngs = new CreatureNameGeneratorSystem();
        cngs.initialise();

        EngineEntityManager mgr = CoreRegistry.get(EngineEntityManager.class);

        for (int i = 0; i < n; i++) {
            OnAddedComponent event = OnAddedComponent.newInstance();

            EntityRef entityRef = mgr.create();

            cngs.onAdded(event, entityRef, genComp);

            CreatureNameComponent nameComp = entityRef.getComponent(CreatureNameComponent.class);

            DisplayNameComponent comp = nameComp.toDisplayInformation();

            logger.info(comp.toString());
        }

        cngs.shutdown();
    }

    @Test
    public void testDwarfNames() {
        CreatureNameGeneratorComponent genComp = new CreatureNameGeneratorComponent();
        genComp.genderRatio = 0.5;
        genComp.nobility = 0.1;
        genComp.theme = "DwaRf";

        generateNames(genComp, 10);
    }

    @Test
    public void testAnimalNames() {
        CreatureNameGeneratorComponent genComp = new CreatureNameGeneratorComponent();
        genComp.genderRatio = 0.5;
        genComp.nobility = 0.1;
        genComp.theme = "Animal";

        generateNames(genComp, 10);
    }
}
