// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.creature;

import org.terasology.engine.entitySystem.Component;
import org.terasology.engine.logic.common.DisplayNameComponent;

/**
 * Defines a creature name
 */
public class CreatureNameComponent implements Component {

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
}
