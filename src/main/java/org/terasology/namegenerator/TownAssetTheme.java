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

package org.terasology.namegenerator;

import java.util.List;

import org.terasology.asset.Assets;
import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.namegenerator.data.NameGeneratorComponent;
import org.terasology.namegenerator.data.TownNameAffixComponent;

/**
 * TODO Type description
 * @author Martin Steiger
 */
public enum TownAssetTheme implements TownTheme {
    
    /**
     * Conventional English names
     */
    ENGLISH("englishTownNames", "englishTownNameAffixes"),
    
    /**
     * Fantasy names, English affixes
     */
    FANTASY("fantasyTownNames", "englishTownNameAffixes");
    
    private final List<String> names;
    private final List<String> prefixes;
    private final List<String> postfixes;

    /**
     * @param namePrefab valid prefab with {@link NameGeneratorComponent}
     * @param affixPrefab valid prefab with {link {@link TownNameAffixComponent}
     */
    TownAssetTheme(String namePrefab, String affixPrefab) {
        this(Assets.getPrefab(namePrefab), Assets.getPrefab(affixPrefab));
    }
    
    /**
     * @param namePrefab valid prefab with {@link NameGeneratorComponent}
     * @param affixPrefab valid prefab with {link {@link TownNameAffixComponent}
     */
    TownAssetTheme(Prefab namePrefab, Prefab affixPrefab) {
        NameGeneratorComponent nameGenComp = namePrefab.getComponent(NameGeneratorComponent.class);
        names = nameGenComp.nameList;
        
        TownNameAffixComponent affixes = affixPrefab.getComponent(TownNameAffixComponent.class);

        prefixes = affixes.prefixes;
        postfixes  = affixes.postfixes;
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
