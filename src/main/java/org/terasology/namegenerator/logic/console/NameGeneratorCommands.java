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

import org.terasology.entitySystem.systems.ComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.console.Command;
import org.terasology.logic.console.CommandParam;
import org.terasology.namegenerator.logic.generators.MarkovNameGenerator;
import org.terasology.namegenerator.logic.generators.NameGenerator;

/**
 * @author Tobias 'skaldarnar' Nett <skaldarnar@googlemail.com>
 */
@RegisterSystem
public class NameGeneratorCommands implements ComponentSystem {

    private NameGenerator nameGen;

    @Override
    public void initialise() {
    }

    @Override
    public void shutdown() {
    }

    @Command(shortDescription = "Initialize a new name generator with the specified prefab.")
    public String initNameGen(@CommandParam("prefabName") String prefabName) {
        nameGen = new MarkovNameGenerator(prefabName);
        return "Markov name generator initialized.";
    }

    @Command(shortDescription = "Generate next random name.")
    public String nextName() {
        if (nameGen == null) {
            nameGen = new MarkovNameGenerator("NameGenerator:elvenMaleNames");
        }
        return nameGen.nextName();
    }

    @Command(shortDescription = "Generate next random name with length >= minLength and <= maxLength.")
    public String nextName(@CommandParam("minLength") int minLength, @CommandParam("maxLength") int maxLength) {
        if (nameGen == null) {
            nameGen = new MarkovNameGenerator("NameGenerator:elvenMaleNames");
        }
        return nameGen.nextName(minLength, maxLength);
    }
}
