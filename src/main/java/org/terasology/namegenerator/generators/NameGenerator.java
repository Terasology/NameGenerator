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

/**
 * Common interface for name generators.
 * <br>
 * A name generator produces pseudo random names. The desired
 * length of names can be specified as well.
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
