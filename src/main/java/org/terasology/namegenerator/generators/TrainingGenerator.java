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

package org.terasology.namegenerator.generators;

import com.google.common.collect.Lists;
import org.terasology.utilities.random.FastRandom;
import org.terasology.utilities.random.Random;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Returns the training data set as-is in randomized order
 * @author Martin Steiger
 */
public class TrainingGenerator implements NameGenerator {

    private final List<String> names;
    private int index;

    /**
     * @param seed the random seed
     * @param training the training data
     */
    public TrainingGenerator(long seed, Collection<String> training) {

        this.names = Lists.newArrayList(training);
        Random random = new FastRandom(seed);

        for (int i = names.size(); i > 1; i--) {
            Collections.swap(names, i - 1, random.nextInt(i));
        }
    }
    
    @Override
    public String nextName() {
        String name = names.get(index);
        
        index = (index + 1) % names.size();
        
        return name;
    }

    @Override
    public String getName(final String seed) {
        int index = Math.abs(seed.hashCode()) % names.size();

        return names.get(index);
    }


}
