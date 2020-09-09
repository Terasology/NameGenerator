// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.namegenerator.commands;

import org.terasology.engine.entitySystem.systems.BaseComponentSystem;
import org.terasology.engine.entitySystem.systems.RegisterSystem;
import org.terasology.engine.logic.console.commandSystem.annotations.Command;
import org.terasology.namegenerator.creature.CreatureNameProvider;
import org.terasology.namegenerator.region.RegionNameProvider;
import org.terasology.namegenerator.town.TownNameProvider;

/**
 * A set of commands, mostly used for debugging.
 */
@RegisterSystem
public class NameGeneratorCommands extends BaseComponentSystem {

    private static final long DEFAULT_SEED = 0xDEADBEEF;

    private final CreatureNameProvider creatureNameGen = new CreatureNameProvider(DEFAULT_SEED);
    private final RegionNameProvider regionNameGen = new RegionNameProvider(DEFAULT_SEED);
    private final TownNameProvider townNameGen = new TownNameProvider(DEFAULT_SEED);

    /**
     * @return a random creature name.
     */
    @Command(shortDescription = "Generate next random creature name.")
    public String nextCreatureName() {
        return creatureNameGen.generateName();
    }

    /**
     * @return a random region name.
     */
    @Command(shortDescription = "Generate next random region name.")
    public String nextRegionName() {
        return regionNameGen.generateName();
    }

    /**
     * @return a random town name.
     */
    @Command(shortDescription = "Generate next random town name.")
    public String nextTownName() {
        return townNameGen.generateName();
    }
}


