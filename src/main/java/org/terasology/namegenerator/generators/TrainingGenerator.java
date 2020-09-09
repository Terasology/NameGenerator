// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.generators;

import com.google.common.collect.Lists;
import org.terasology.engine.utilities.random.FastRandom;
import org.terasology.engine.utilities.random.Random;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Returns the training data set as-is in randomized order
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
    public String getName(final long seed) {
        int idx = (int) Math.abs(seed % names.size());

        return names.get(idx);
    }


}
