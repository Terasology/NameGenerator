// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.town;

import org.terasology.engine.entitySystem.prefab.Prefab;
import org.terasology.engine.utilities.Assets;
import org.terasology.namegenerator.data.NameGeneratorComponent;
import org.terasology.namegenerator.data.TownNameAffixComponent;

import java.util.Collections;
import java.util.List;

/**
 * Asset-based themes for towns
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
     * @param affixPrefab valid prefab with {@link TownNameAffixComponent}
     */
    TownAssetTheme(String namePrefab, String affixPrefab) {
        this(Assets.getPrefab(namePrefab).get(), Assets.getPrefab(affixPrefab).get());
    }

    /**
     * @param namePrefab valid prefab with {@link NameGeneratorComponent}
     * @param affixPrefab valid prefab with {link {@link TownNameAffixComponent}
     */
    TownAssetTheme(Prefab namePrefab, Prefab affixPrefab) {
        NameGeneratorComponent basenames = namePrefab.getComponent(NameGeneratorComponent.class);
        names = Collections.unmodifiableList(basenames.nameList);

        TownNameAffixComponent affixes = affixPrefab.getComponent(TownNameAffixComponent.class);
        prefixes = Collections.unmodifiableList(affixes.prefixes);
        postfixes = Collections.unmodifiableList(affixes.postfixes);
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
