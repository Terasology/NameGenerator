/*
 * Copyright 2014 MovingBlocks
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

package org.terasology.namegenerator.creature;

import org.terasology.entitySystem.Component;
import org.terasology.logic.common.DisplayNameComponent;

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
