// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.namegenerator.generators;

/**
 * Common interface for name generators.
 * <br>
 * A name generator produces pseudo random names. The desired length of names can be specified as well.
 */
public interface NameGenerator {

    /**
     * Generates a pseudo random name.
     *
     * @return a pseudo random name
     */
    String nextName();

    /**
     * Generates a pseudo random name, using the given seed. Requesting two names with the same seed will result in the
     * same name.
     *
     * @param seed the seed to use
     * @return a pseudo random name based on the seed
     */
    String getName(long seed);
}
