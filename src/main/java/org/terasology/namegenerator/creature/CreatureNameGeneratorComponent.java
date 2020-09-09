// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.creature;

import org.terasology.engine.entitySystem.Component;

/**
 * Defines a creature affinity vector
 */
public class CreatureNameGeneratorComponent implements Component {

    public double genderRatio;
    public double nobility;
    public String theme;

}
