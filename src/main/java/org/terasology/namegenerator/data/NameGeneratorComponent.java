// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.data;

import org.terasology.engine.entitySystem.Component;

import java.util.List;

/**
 * Provides a list of training data items.
 */
public class NameGeneratorComponent implements Component {
    /**
     * A list of training data names
     */
    public List<String> nameList;
}
