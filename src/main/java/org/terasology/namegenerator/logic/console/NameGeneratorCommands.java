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
package org.terasology.namegenerator.logic.console;

import java.util.List;

import org.terasology.asset.Assets;
import org.terasology.entitySystem.systems.ComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.console.Command;
import org.terasology.logic.console.CommandParam;
import org.terasology.namegenerator.data.NameGeneratorComponent;
import org.terasology.namegenerator.logic.generators.Markov2NameGenerator;
import org.terasology.namegenerator.logic.generators.NameGenerator;

/**
 * @author Tobias 'skaldarnar' Nett <skaldarnar@googlemail.com>
 */
@RegisterSystem
public class NameGeneratorCommands implements ComponentSystem {
    
    private static final long DEFAULT_SEED = 0xDEADBEEF;

    private NameGenerator nameGen;

    private void initializeDefaultNameGenerator() {
        List<String> training = Assets.getPrefab("elvenMaleNames").getComponent(NameGeneratorComponent.class).nameList;
        nameGen = new Markov2NameGenerator(DEFAULT_SEED, training);
    }

    @Override
    public void initialise() {
        // empty
    }

    @Override
    public void shutdown() {
        // empty
    }

    @Command(shortDescription = "Generate next random name.")
    public String nextName() {
        if (nameGen == null) {
            initializeDefaultNameGenerator();
        }
        return nameGen.nextName();
    }

    @Command(shortDescription = "Generate a list with random names.")
    public String generateNameList(@CommandParam("number of names") int length) {
        if (nameGen == null) {
            initializeDefaultNameGenerator();
        }
        StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            
            builder.append(nameGen.nextName());
        }
        
        return builder.toString();
    }
}
