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

package org.terasology.namegenerator.waters;

import org.terasology.utilities.Assets;
import org.terasology.entitySystem.prefab.Prefab;
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
        types  = Collections.unmodifiableList(typeComp.nameList);
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
