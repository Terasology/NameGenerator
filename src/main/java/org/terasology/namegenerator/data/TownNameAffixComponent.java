// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.data;

import com.google.common.collect.Lists;
import org.terasology.gestalt.entitysystem.component.Component;

import java.util.List;

/**
 * Contains affixes for town names.
 */
public class TownNameAffixComponent implements Component<TownNameAffixComponent> {
    /**
     * A list of pre-fixes such as "Old" and "Nether"
     */
    public List<String> prefixes;

    /**
     * A list of post-fixes such as "Heights" and "Beach"
     */
    public List<String> postfixes;

    @Override
    public void copy(TownNameAffixComponent other) {
        this.prefixes = Lists.newArrayList(other.prefixes);
        this.postfixes = Lists.newArrayList(other.postfixes);
    }
}
