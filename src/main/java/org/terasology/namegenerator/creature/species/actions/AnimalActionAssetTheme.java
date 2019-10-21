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

package org.terasology.namegenerator.creature.species.actions;

import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.namegenerator.data.NameGeneratorComponent;
import org.terasology.namegenerator.data.NameGeneratorListComponent;
import org.terasology.namegenerator.data.TownNameAffixComponent;
import org.terasology.utilities.Assets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Asset-based themes for common animal names
 */
public enum AnimalActionAssetTheme implements AnimalActionTheme {

    /**
     * Conventional English names
     */
    ENGLISH("actionVerbs", "listAnimalNames");

    private final List<String> names;
    private final List<String> objects;

    /**
     * @param namePrefab valid prefab with {@link NameGeneratorComponent}
     */
    AnimalActionAssetTheme(String namePrefab, String listPrefab) {
        this(Assets.getPrefab(namePrefab).get(), Assets.getPrefab(listPrefab).get());
    }

    /**
     * @param namePrefab valid prefab with {@link NameGeneratorComponent}
     */
    AnimalActionAssetTheme(Prefab namePrefab, Prefab listPrefab) {
        NameGeneratorComponent basenames = namePrefab.getComponent(NameGeneratorComponent.class);
        names = Collections.unmodifiableList(basenames.nameList);

        List<String> listObjects = listPrefab.getComponent(NameGeneratorListComponent.class).nameLists;
        List<String> modifiableList = new ArrayList<>();
        for (String val : listObjects)
        {
            try {
                Prefab prefab = Assets.getPrefab(val).get();
                modifiableList.addAll(prefab.getComponent(NameGeneratorComponent.class).nameList);
            } catch (Exception e) {
                System.out.println("Unable to find prefab " + val);
            }
        }
        objects = Collections.unmodifiableList(modifiableList);
    }

    @Override
    public List<String> getWords() {
        return this.names;
    }

    @Override
    public List<String> getObjects() {
        return this.objects;
    }


}
