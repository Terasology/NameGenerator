/*
 * Copyright 2013 MovingBlocks
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
package org.terasology.namegenerator.commands;

import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.console.Command;
import org.terasology.namegenerator.creature.CreatureNameProvider;
import org.terasology.namegenerator.region.RegionNameProvider;
import org.terasology.namegenerator.town.TownNameProvider;

/**
 * @author Martin Steiger
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


