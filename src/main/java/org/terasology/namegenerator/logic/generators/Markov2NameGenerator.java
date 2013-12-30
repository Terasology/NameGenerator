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

import org.terasology.asset.AssetUri;
import org.terasology.asset.Assets;
import org.terasology.utilities.random.FastRandom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class Markov2NameGenerator implements NameGenerator {

    private static final char TERMINATOR = '\0';
    
    private final FastRandom random;

    private final Set<String> results = new HashSet<>();
    private List<Character> characters;
    private int[][][] probabilities;

    /**
     * Create a new name generator, using the specified asset as example list.
     * <p/>
     * The asset URI must point to a valid name list prefab which has a {@link NameGeneratorComponent}.
     * 
     * @param seed the seed for the random number generator
     * @param assetUri valid asset uri with NameGeneratorComponent
     */
    public Markov2NameGenerator(long seed, AssetUri assetUri) {
        this(seed, assetUri.toNormalisedSimpleString());
    }

    /**
     * Create a new name generator, using the given String as asset uri.
     * <p/>
     * The String must denote a simple uri and the asset must contain a {@link NameGeneratorComponent}.
     *
     * @param seed the seed for the random number generator
     * @param simpleUri simple asset uri as string
     */
    public Markov2NameGenerator(long seed, String simpleUri) {
        
        random = new FastRandom(seed);
        
        // load example names list from asset uri
        NameGeneratorComponent nameGenComp = Assets.getPrefab(simpleUri).getComponent(NameGeneratorComponent.class);
        // check if given uri was valid
        if (nameGenComp != null) {
            // initialize the list of used characters
            characters = determineUsedChars(nameGenComp.nameList);
            characters.add(TERMINATOR);
            // initialize probability matrix
            probabilities = new int[characters.size()][characters.size()][characters.size()];
            // build up the probability table from the given source names
            for (final String name : nameGenComp.nameList) {
                addStringToProbability(name);
            }
        } else {
            throw new IllegalArgumentException("assetUri is not a valid name list:" + simpleUri);
        }
    }

    /**
     * Create a new name generator, using the given list as example source.
     *
     * @param seed the seed for the random number generator
     * @param sourceNames a list of example names
     */
    public Markov2NameGenerator(long seed, List<String> sourceNames) {

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
        String result = "";
        char last1 = TERMINATOR;
        char last2 = TERMINATOR;
        do {
            char temp = nextCharByLast(last1, last2);
            last1 = last2;
            last2 = temp;
            if (last2 != TERMINATOR) {
                result += Character.toString(last2);
            }
        } while (last2 != TERMINATOR);
        return result.substring(0, 1).toUpperCase() + result.substring(1);
    }

    @Override
    public String nextName(int minLength, int maxLength) {
        String name = nextName();
        while (name.length() < minLength || name.length() > maxLength) {
            name = nextName();
        }
        if (name.length() >= minLength && name.length() <= maxLength) {
            results.add(name);
            return name;
        }
        return null;
    }

    @Override
    public List<String> generateList(int size) {
        final List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(nextName());
        }
        return list;
    }

    @Override
    public List<String> generateList(int size, int minLength, int maxLength) {
        final List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(nextName(minLength, maxLength));
        }
        return list;
    }
}
