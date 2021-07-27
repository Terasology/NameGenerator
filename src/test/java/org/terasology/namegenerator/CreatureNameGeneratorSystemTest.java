// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.namegenerator;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.entitySystem.entity.internal.EngineEntityManager;
import org.terasology.engine.entitySystem.entity.lifecycleEvents.OnAddedComponent;
import org.terasology.engine.logic.common.DisplayNameComponent;
import org.terasology.engine.registry.In;
import org.terasology.moduletestingenvironment.MTEExtension;
import org.terasology.moduletestingenvironment.extension.Dependencies;
import org.terasology.namegenerator.creature.CreatureNameComponent;
import org.terasology.namegenerator.creature.CreatureNameGeneratorComponent;
import org.terasology.namegenerator.creature.CreatureNameGeneratorSystem;

/**
 * Tests {@link CreatureNameGeneratorSystem}
 */
@ExtendWith(MTEExtension.class)
@Dependencies("NameGenerator")
@Tag("MteTest")
public class CreatureNameGeneratorSystemTest {

    private static final Logger logger = LoggerFactory.getLogger(CreatureNameGeneratorSystemTest.class);

    @In
    EngineEntityManager mgr;

    /**
     * Test subroutine.  Generates {@code n} names and logs them.  Asserts nothing.
     *
     * @param genComp the {@code Component} configuring the name generator
     * @param n the number of names to generate
     */
    private void generateNames(CreatureNameGeneratorComponent genComp, int n) {
        CreatureNameGeneratorSystem cngs = new CreatureNameGeneratorSystem();
        cngs.initialise();

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
