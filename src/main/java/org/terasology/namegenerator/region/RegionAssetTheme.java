// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.region;

import org.terasology.engine.entitySystem.prefab.Prefab;
import org.terasology.engine.utilities.Assets;
import org.terasology.namegenerator.data.NameGeneratorComponent;

import java.util.Collections;
import java.util.List;

/**
 * A {@link RegionTheme} that is based on Asset data
 */
public enum RegionAssetTheme implements RegionTheme {

    /**
     * Theme set based on real country names
     */
    REAL("countries");

    private final List<String> names;

    /**
     * @param names valid prefab with {@link NameGeneratorComponent}
     */
    RegionAssetTheme(String names) {
        this(Assets.getPrefab(names).get());
    }

    /**
     * @param namesPf valid prefab with {@link NameGeneratorComponent}
     */
    RegionAssetTheme(Prefab namesPf) {
        NameGeneratorComponent comp = namesPf.getComponent(NameGeneratorComponent.class);
        names = Collections.unmodifiableList(comp.nameList);
    }

    /**
     * @return the maleNames
     */
    @Override
    public List<String> getNames() {
        return this.names;
    }

}
