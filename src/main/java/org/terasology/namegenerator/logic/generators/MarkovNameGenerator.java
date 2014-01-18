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
package org.terasology.namegenerator.logic.generators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.terasology.utilities.random.FastRandom;

import com.google.common.base.Preconditions;

/**
 * Implementation of the {@link org.terasology.namegenerator.logic.generators.NameGenerator} interface, using Markov chain model.
 * <p/>
 * The look-ahead for analysis and generation is two characters.
 * <p/>
 * This class is based on David Legare's NameGenerator, licensed under Creative Commons
 * Attribution-ShareAlike 3.0 Unported License.
 * It has been adopted to the use in Terasology. You can find the original project on github:
 * <p/>
 * <a href="https://github.com/excaliburHisSheath/NameGenerator">NameGenerator by David Legare</a>.
 *
 * @author Tobias 'skaldarnar' Nett <skaldarnar@googlemail.com>
 */
public class MarkovNameGenerator implements NameGenerator {

    private static final char TERMINATOR = '\0';
    
    private final FastRandom random;

    private final int[][][] probabilities;

    private List<Character> characters;
 
    /**
     * Create a new name generator, using the given list as example source.
     *
     * @param seed the seed for the random number generator
     * @param sourceNames a list of example names
     */
    public MarkovNameGenerator(long seed, List<String> sourceNames) {

        random = new FastRandom(seed);

        // initialize result set
        // initialize the list of used characters
        characters = determineUsedChars(sourceNames);
        characters.add(TERMINATOR);
        // initialize probability matrix
        probabilities = new int[characters.size()][characters.size()][characters.size()];
        // build up the probability table from the given source names
        for (final String name : sourceNames) {
            addStringToProbability(name);
        }
    }

    /**
     * Determines all characters used in the source name list. New names will be made of the chars appearing in the
     * example list.
     *
     * @param sourceNames list of example names
     * @return list of used characters, all lower case
     */
    private List<Character> determineUsedChars(List<String> sourceNames) {
        final Set<Character> chars = new HashSet<>();
        for (String name : sourceNames) {
            for (char c : name.toLowerCase().toCharArray()) {
                chars.add(c);
            }
        }
        return new ArrayList<>(chars);
    }

    /**
     * Update the internal probability matrix with the given name. The given name example is analyzed in the sense of
     * the Markov model.
     *
     * @param name example name to anaylse
     */
    private void addStringToProbability(final String name) {
        String lowerName = name.toLowerCase();
        char last1 = TERMINATOR;
        char last2 = TERMINATOR;
        int index = 0;
        while (index < lowerName.length()) {
            if (characters.indexOf(lowerName.charAt(index)) != -1) {
                char current = lowerName.charAt(index);
                probabilities[characters.indexOf(last1)][characters.indexOf(last2)][characters.indexOf(current)]++;
                last1 = last2;
                last2 = current;
                index++;
            } else {
                index++;
            }
        }
        char current = TERMINATOR;
        probabilities[characters.indexOf(last1)][characters.indexOf(last2)][characters.indexOf(current)]++;
    }

    /**
     * Chooses a random character from the probability matrix based on the previous two characters.
     * Note that {@code last1} and {@code last2} have to be recognized characters that have previously appeared in that
     * particular order.
     *
     * @param last1 the second last character
     * @param last2 the last character
     * @return the next character based on the last two characters and probability matrix
     */
    private char nextCharByLast(char last1, char last2) {
        int total = 0;
        for (int i : probabilities[characters.indexOf(last1)][characters.indexOf(last2)]) {
            total += i;
        }
        total = random.nextInt(total);
        int index = 0;
        int subTotal = 0;
        do {
            subTotal += probabilities[characters.indexOf(last1)][characters.indexOf(last2)][index++];
        } while (subTotal <= total);
        return (characters.get(--index));
    }

    @Override
    public String nextName() {
        StringBuilder sb = new StringBuilder();
        char last1 = TERMINATOR;
        char last2 = TERMINATOR;
        do {
            char temp = nextCharByLast(last1, last2);
            last1 = last2;
            last2 = temp;
            if (last2 != TERMINATOR) {
                if (sb.length() == 0) {
                    sb.append(Character.toUpperCase(last2));        // first letter is uppercase
                } else {
                    sb.append(last2);
                }
            }
        } while (last2 != TERMINATOR);
        
        return sb.toString();
    }


    /**
     * Generates a new pseudo random name.
     *
     * @param minLength minimal length of generated name [0..12]
     * @param maxLength maximal length of generated name
     * @return a pseudo random name
     */
    public String nextName(int minLength, int maxLength) {
        
        Preconditions.checkArgument(minLength >= 0 && minLength <= 12);
        Preconditions.checkArgument(maxLength >= minLength);
        
        String name = nextName();
        while (name.length() < minLength || name.length() > maxLength) {
            name = nextName();
        }
        if (name.length() >= minLength && name.length() <= maxLength) {
            return name;
        }
        return null;
    }

}
