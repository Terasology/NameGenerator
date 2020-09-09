// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.data;

import org.terasology.engine.entitySystem.Component;

import java.util.List;

/**
 * Contains affixes for town names.
 */
public class TownNameAffixComponent implements Component {
    /**
     * A list of pre-fixes such as "Old" and "Nether"
     */
    public List<String> prefixes;

    /**
     * A list of post-fixes such as "Heights" and "Beach"
     */
    public List<String> postfixes;

}
