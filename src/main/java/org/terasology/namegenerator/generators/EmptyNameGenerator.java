// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.namegenerator.generators;

/**
 * Trivial implementation of {@link NameGenerator} that always returns an empty string.
 */
public class EmptyNameGenerator implements NameGenerator {

    @Override
    public String nextName() {
        return "";
    }

    @Override
    public String getName(long seed) {
        return "";
    }
}
