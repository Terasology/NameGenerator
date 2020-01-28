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

package org.terasology.namegenerator.town;

import org.terasology.utilities.Assets;
import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.namegenerator.data.NameGeneratorComponent;
import org.terasology.namegenerator.data.TownNameAffixComponent;

import java.util.Collections;
import java.util.List;

/**
 * Asset-based themes for towns
 */
public enum TownAssetTheme implements TownTheme {

    //Names from 1600-1900 Edo Japan
    EDO("edoTownNames", null),
    //Names from Ancient Egypt
    EGYPT("egyptianTownNames", null),
    //Conventional English names
    ENGLISH("englishTownNames", "englishTownNameAffixes"),
    //Fantasy names, English affixes
    FANTASY("fantasyTownNames", "englishTownNameAffixes"),
    //Modern names from France
    FRENCH("frenchTownNames", "frenchTownNameAffixes"),
    //Ancient Greek names
    GREEK("greekTownNames", null),
    //Hindu names from northern India
    HINDU("hinduTownNames", "hinduTownNames"),
    //Latin names from Ancient Rome
    ROMAN("romanTownNames", null),
    //Modern names from Spain
    SPANISH("spanishTownNames", "spanishTownNameAffixes");

    private final List<String> names;
    private final List<String> prefixes;
    private final List<String> postfixes;

    /**
     * @param namePrefab valid prefab with {@link NameGeneratorComponent}
     * @param affixPrefab valid prefab with {@link TownNameAffixComponent}
     */
    TownAssetTheme(String namePrefab, String affixPrefab) {
        this(fetchNameList(namePrefab), fetchPrefixesList(affixPrefab), fetchPostfixesList(affixPrefab));
    }

    TownAssetTheme(List<String> namePrefab, List<String> prefixPrefab, List<String> postfixPrefab) {
        names = namePrefab;
        prefixes = prefixPrefab;
        postfixes  = postfixPrefab;
    }

    /**
     * Fetch a name list from a String naming a {@code Prefab}.
     *
     * The named {@link Prefab} should contain a {@link NameGeneratorComponent}.
     *
     * @param s the name of a prefab, null returns an empty list
     * @return the list of names associated with the named prefab
     * @throws java.util.NoSuchElementException if {@code s} is not null but does not have an associated prefab
     */
    private static List<String> fetchNameList(String s) {
        return
                Assets.getPrefab(s)
                        .map(prefab -> prefab.getComponent(NameGeneratorComponent.class))
                        .map(comp -> comp.nameList)
                        .orElse(Collections.emptyList());
    }

    private static List<String> fetchPrefixesList(String s) {
        return
                Assets.getPrefab(s)
                        .map(prefab -> prefab.getComponent(TownNameAffixComponent.class))
                        .map(comp -> comp.prefixes)
                        .orElse(Collections.emptyList());
    }

    private static List<String> fetchPostfixesList(String s) {
        return
                Assets.getPrefab(s)
                        .map(prefab -> prefab.getComponent(TownNameAffixComponent.class))
                        .map(comp -> comp.postfixes)
                        .orElse(Collections.emptyList());
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
