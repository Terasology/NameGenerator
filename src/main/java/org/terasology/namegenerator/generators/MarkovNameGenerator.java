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
package org.terasology.namegenerator.generators;

import com.google.common.base.Preconditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.markovChains.MarkovChain;
import org.terasology.markovChains.TrainingAlgorithms;
import org.terasology.utilities.random.FastRandom;
import org.terasology.utilities.random.Random;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of the {@link org.terasology.namegenerator.generators.NameGenerator} interface, using Markov chain model.
 * The look-ahead for analysis and generation is two characters.
 * @author Tobias 'skaldarnar' Nett <skaldarnar@googlemail.com>
 */
public class MarkovNameGenerator implements NameGenerator {

    private static final Logger logger = LoggerFactory.getLogger(MarkovNameGenerator.class);

    private static final char TERMINATOR = '\0';

    private final FastRandom random;

    private MarkovChain<Character> markovChain;

    /**
     * Create a new name generator, using the given list as example source.
     *
     * @param seed the seed for the random number generator
     * @param sourceNames a list of example names
     */
    public MarkovNameGenerator(long seed, final List<String> sourceNames) {

        random = new FastRandom(seed);

        // initialize the list of used characters
        List<Character> characters = determineUsedChars(sourceNames);
        characters.add(TERMINATOR);

        // wrap training data
        List<Character[]> sampleSequences = new AbstractList<Character[]>() {

            @Override
            public Character[] get(int index) {
                return wrapChars(sourceNames.get(index));
            }

            @Override
            public int size() {
                return sourceNames.size();
            }
        };

        markovChain = TrainingAlgorithms.forwardAlgorithm(2, characters, sampleSequences, TERMINATOR);
        markovChain.setRandom(new FastRandom(seed));
    }

    private static Character[] wrapChars(final String name) {
        Character[] arr = new Character[name.length()];
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            char lc = Character.toLowerCase(ch);
            arr[i] = Character.valueOf(lc);
        }
        return arr;
    }

    /**
     * Determines all characters used in the source name list. New names will be made of the chars appearing in the
     * example list.
     *
     * @param sourceNames list of example names
     * @return list of used characters, all lower case
     */
    private static List<Character> determineUsedChars(List<String> sourceNames) {
        final Set<Character> chars = new HashSet<>();
        for (String name : sourceNames) {
            for (char c : name.toLowerCase().toCharArray()) {
                chars.add(c);
            }
        }
        return new ArrayList<>(chars);
    }

    /**
     * Generate the next name using the given random number generator.
     * @param minLength minimal length of generated name [0..12]
     * @param maxLength maximal length of generated name
     * @param rand random number generator to use for generation
     * @return the next character based on the last two characters and probability matrix
     */
    private String generateNameWithGenerator(int minLength, int maxLength, Random rand) {
        Preconditions.checkArgument(maxLength >= minLength);
        StringBuilder sb = new StringBuilder(maxLength);
        markovChain.setRandom(rand);
        markovChain.resetHistory();
        char next;
        int tries = 0;
        int maxTries = maxLength + 100;
        do {
            next = markovChain.next();
            if (next != TERMINATOR) {

                if (sb.length() == 0) {
                    sb.append(Character.toUpperCase(next));        // first letter is uppercase
                } else {
                    sb.append(next);
                }
            }
            tries++;
            // it would be better, if the probability of TERMINATOR was
            // continuously increased as the name gets longer. Truncating can
            // produce ugly names.
        } while ((next != TERMINATOR || sb.length() < minLength) && sb.length() < maxLength && tries < maxTries);
        // cut of trailing whitespace
        String name = sb.toString().trim();

        if (tries == maxTries) {
            logger.warn("Could not generate name of desired length - result: " + name);
        }

        return name;
    }

    /**
     * Generates a new pseudo random name.
     *
     * @param minLength minimal length of generated name [0..12]
     * @param maxLength maximal length of generated name
     * @return a pseudo random name
     */
    public String nextName(int minLength, int maxLength) {
        return generateNameWithGenerator(minLength, maxLength, random);
    }

    /**
     * Generates a new pseudo random name, based on the given seed.
     *
     * @param minLength minimal length of generated name [0..12]
     * @param maxLength maximal length of generated name
     * @param seed      the seed value to use for this name
     * @return a pseudo random name
     */
    public String getName(int minLength, int maxLength, long seed) {
        return generateNameWithGenerator(minLength, maxLength, new FastRandom(seed));
    }

    @Override
    public String nextName() {
        return nextName(4, 12);
    }

    @Override
    public String getName(long seed) {
        return getName(4, 16, seed);
    }

}
