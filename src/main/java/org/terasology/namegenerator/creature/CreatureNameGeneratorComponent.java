// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.creature;

import org.terasology.gestalt.entitysystem.component.Component;

/**
 * Defines a creature affinity vector
 */
public class CreatureNameGeneratorComponent implements Component<CreatureNameGeneratorComponent> {

    public double genderRatio;
    public double nobility;
    public String theme;

    @Override
    public void copyFrom(CreatureNameGeneratorComponent other) {
        this.genderRatio = other.genderRatio;
        this.nobility = other.nobility;
        this.theme = other.theme;
    }
}
