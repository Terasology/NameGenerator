// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.creature;

import org.terasology.engine.logic.common.DisplayNameComponent;
import org.terasology.gestalt.entitysystem.component.Component;

/**
 * Defines a creature name
 */
public class CreatureNameComponent implements Component<CreatureNameComponent> {

    public String firstName;
    public String lastName;
    public String attr;

    private String fullName() {
        return (firstName + " " + lastName).trim();
    }

    @Override
    public String toString() {
        String name = fullName();

        if (attr != null && !attr.isEmpty()) {
            name += " the " + attr;
        }

        return name;
    }

    /**
     * @return a DIC that reflects the same information
     */
    public DisplayNameComponent toDisplayInformation() {
        DisplayNameComponent dic = new DisplayNameComponent();

        dic.name = fullName();

        if (attr != null && !attr.isEmpty()) {
            dic.description = "The " + attr;
        }

        return dic;
    }

    @Override
    public void copy(CreatureNameComponent other) {
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.attr = other.attr;

    }
}
