// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.waters;

import org.terasology.engine.entitySystem.prefab.Prefab;
import org.terasology.engine.utilities.Assets;
import org.terasology.namegenerator.data.NameGeneratorComponent;

import java.util.Collections;
import java.util.List;

/**
 * Asset-based themes for waters
 */
public enum WaterAssetTheme implements WaterTheme {

    /**
     * Conventional English names
     */
    ENGLISH("humanFemaleOldEnglishNames3", "waterTypes");

    private final List<String> names;
    private final List<String> types;

    /**
     * @param namePrefab valid prefab with {@link NameGeneratorComponent}
     * @param typePrefab valid prefab with {@link NameGeneratorComponent}
     */
    WaterAssetTheme(String namePrefab, String typePrefab) {
        this(Assets.getPrefab(namePrefab).get(), Assets.getPrefab(typePrefab).get());
    }

    /**
     * @param namePrefab valid prefab with {@link NameGeneratorComponent}
     * @param affixPrefab valid prefab with {link NameGeneratorComponent}
     */
    WaterAssetTheme(Prefab namePrefab, Prefab affixPrefab) {
        NameGeneratorComponent basenames = namePrefab.getComponent(NameGeneratorComponent.class);
        names = Collections.unmodifiableList(basenames.nameList);

        NameGeneratorComponent typeComp = affixPrefab.getComponent(NameGeneratorComponent.class);
        types = Collections.unmodifiableList(typeComp.nameList);
    }

    @Override
    public List<String> getNames() {
        return this.names;
    }

    @Override
    public List<String> getWaterTypes() {
        return this.types;
    }


}
