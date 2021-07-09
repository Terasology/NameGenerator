// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.data;

import com.google.common.collect.Lists;
import org.terasology.gestalt.entitysystem.component.Component;

import java.util.List;

/**
 * Provides a list of training data items.
 */
public class NameGeneratorComponent implements Component<NameGeneratorComponent> {
    /**
     * A list of training data names
     */
    public List<String> nameList;

    @Override
    public void copy(NameGeneratorComponent other) {
        this.nameList = Lists.newArrayList(other.nameList);
    }
}
