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

package org.terasology.namegenerator.region;

import org.terasology.utilities.Assets;
import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.namegenerator.data.NameGeneratorComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A {@link RegionTheme} that is based on Asset data
 */
public enum RegionAssetTheme implements RegionTheme {

    //names from 1600-1900 Edo Japan
    EDO("edoRegionNames"),
    //Greek regions
    GREEK("greekRegionNames"),
    //Theme set based on real country names
    REAL("countries"),
    //Roman provinces
    ROMAN("romanRegionNames"),
    //Spanish provinces
    SPANISH("spanishRegionNames");

    private final List<String> names;

    /**
     * @param names valid prefab with {@link NameGeneratorComponent}
     */
    RegionAssetTheme(String names) {
        this(Assets.getPrefab(names));
    }

    RegionAssetTheme(Optional<Prefab> namesO) {
        Prefab namesPf = null;
        try {
            namesPf = namesO.get();
        } catch (Exception e) {
            System.out.println("WARNING: Could not define prefab " + this.name());
            e.printStackTrace();
        }
        NameGeneratorComponent comp = namesPf.getComponent(NameGeneratorComponent.class);
        names = Collections.unmodifiableList(comp.nameList);
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
