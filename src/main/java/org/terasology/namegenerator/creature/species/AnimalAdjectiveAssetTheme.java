/*
 * Copyright 2019 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.namegenerator.creature.species;

import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.namegenerator.data.NameGeneratorComponent;
import org.terasology.namegenerator.data.TownNameAffixComponent;
import org.terasology.utilities.Assets;

import java.util.Collections;
import java.util.List;

/**
 * Asset-based themes for common animal names
 */
public enum AnimalAdjectiveAssetTheme implements AnimalAdjectiveTheme {

    /**
     * Conventional English names
     */
    ENGLISH("animalAdjectives", "animalAdjectives");

    private final List<String> names;
    private final List<String> postfixes;
    private final List<String> prefixes;

    /**
     * @param namePrefab valid prefab with {@link NameGeneratorComponent}
     * @param affixPrefab valid prefab with {@link org.terasology.namegenerator.data.TownNameAffixComponent}
     */
    AnimalAdjectiveAssetTheme(String namePrefab, String affixPrefab) {
        this(Assets.getPrefab(namePrefab).get(), Assets.getPrefab(affixPrefab).get());
    }

    /**
     * @param namePrefab valid prefab with {@link NameGeneratorComponent}
     * @param affixPrefab valid prefab with {@link TownNameAffixComponent}
     */
    AnimalAdjectiveAssetTheme(Prefab namePrefab, Prefab affixPrefab) {
        NameGeneratorComponent basenames = namePrefab.getComponent(NameGeneratorComponent.class);
        names = Collections.unmodifiableList(basenames.nameList);

        TownNameAffixComponent typeComp = affixPrefab.getComponent(TownNameAffixComponent.class);
        postfixes  = Collections.unmodifiableList(typeComp.postfixes);
        prefixes = Collections.unmodifiableList(typeComp.prefixes);
    }

    @Override
    public List<String> getNames() {
        return this.names;
    }

    @Override
    public List<String> getPrefixes() {
        return this.prefixes;
    }

    @Override
    public List<String> getPostfixes() {
        return this.postfixes;
    }

}
