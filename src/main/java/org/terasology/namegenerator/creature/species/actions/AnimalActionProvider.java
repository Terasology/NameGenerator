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
package org.terasology.namegenerator.creature.species.actions;

import org.terasology.utilities.random.MersenneRandom;
import org.terasology.utilities.random.Random;

import java.util.List;

public class AnimalActionProvider {
    private final AnimalActionTheme theme;
    private final Random random;
    /**
     * Uses the default naming theme
     *
     * @param seed the seed
     */
    public AnimalActionProvider(long seed) {
        this(seed, AnimalActionAssetTheme.ENGLISH);
    }

    /**
     * @param seed  the seed value
     * @param aTheme the naming theme
     */
    public AnimalActionProvider(long seed, AnimalActionTheme aTheme) {

        random = new MersenneRandom(seed);
        theme = aTheme;
    }

    /**
     * @return the action
     */
    public synchronized String generateName() {
        List<String> names = theme.getWords();
        List<String> objects = theme.getObjects();
        String name = names.get((int)(names.size() * random.nextDouble()));
        String object = objects.get((int)(objects.size() * random.nextDouble()));

        return object + "-" + name;
    }
}
