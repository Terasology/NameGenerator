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

import java.util.List;

/**
 * Common interface for name generators.
 * <p/>
 * A name generator produces pseudo random names, either as a single name or as a list of multiple names. The desired
 * length of names can be specified as well.
 *
 * @author Tobias 'skaldarnar' Nett <skaldarnar@googlemail.com>
 */
public interface NameGenerator {

    /**
     * Generates a pseudo random name.
     *
     * @return a pseudo random name
     */
    String nextName();

    /**
     * Generates a new pseudo random name.
     *
     * @param minLength minimal length of generated name
     * @param maxLength maximal length of generated name
     * @return a pseudo random name
     */
    String nextName(final int minLength, final int maxLength);

    /**
     * Generate a list of pseudo random names.
     *
     * @param size the size of the list
     * @return a list with the specified number of names
     */
    List<String> generateList(final int size);

    /**
     * Generate a list of pseudo random names.
     *
     * @param size      the size of the list
     * @param minLength minimal length of generated name
     * @param maxLength maximal length of generated name
     * @return a list with the specified number of names
     */
    List<String> generateList(final int size, final int minLength, final int maxLength);
}
