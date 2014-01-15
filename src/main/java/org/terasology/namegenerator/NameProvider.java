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

import org.terasology.namegenerator.logic.generators.Markov2NameGenerator;
import org.terasology.namegenerator.logic.generators.NameGenerator;

/**
 * Provides access to generated names. Thread-safe.
 * @author Martin Steiger
 */
public class NameProvider {

    private final long seed;
    private final Theme theme;

    private NameGenerator tng;

    public NameProvider(long seed, Theme theme) {
        this.seed = seed;
        this.theme = theme;
    }
    
    public String generateTownName() {
        return generateTownName(new TownAffinityVector());
    }
    
    public synchronized String generateTownName(TownAffinityVector affinity) {
        if (tng == null) {
            tng = new Markov2NameGenerator(seed, theme.getTownNames());
        }
        
        String name = tng.nextName();
        
        if (affinity.getLakeAffinity() > 0.5) {
            name = name + " Beach";
        }
        
        if (affinity.getHeightAffinity() > 0.5) {
            name = "Upper " + name;
        } else

        if (affinity.getHeightAffinity() < -0.5) {
            name = "Lower " + name;
        }

        return name;
    }
}
