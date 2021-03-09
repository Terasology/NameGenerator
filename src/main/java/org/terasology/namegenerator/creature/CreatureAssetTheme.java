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

package org.terasology.namegenerator.creature;

import org.terasology.engine.entitySystem.prefab.Prefab;
import org.terasology.engine.utilities.Assets;
import org.terasology.namegenerator.data.NameGeneratorComponent;

import java.util.Collections;
import java.util.List;

/**
 * A {@link CreatureTheme} that is based on Asset data
 */
public enum CreatureAssetTheme implements CreatureTheme {

    /**
     * Elven theme set
     */
    ELVEN("elvenMaleNames", "elvenFemaleNames", "elvenSurnames", "nobilityAttribs"),

    /**
     * Dwarf names - surnames are not properly defined
     */
    DWARF("dwarvenMaleNames", "dwarvenFemaleNames", "dwarvenMaleNames", "nobilityAttribs"),

    /**
     * Animal names
     */
    ANIMAL("animalMaleNames", "animalFemaleNames", null, "nobilityAttribs"),

    OLD_WEST("oldWestMaleNames", "oldWestFemaleNames","oldWestSurnames", null);

    /**
     * Returns the default theme
     */
    public static final CreatureAssetTheme DEFAULT = ELVEN;

    private final List<String> maleNames;
    private final List<String> femaleNames;
    private final List<String> surnames;
    private final List<String> nobAttribs;

    /**
     * @param maleNames name of a valid prefab with {@link NameGeneratorComponent}, or null for an empty name list
     * @param femaleNames name of a valid prefab with {@link NameGeneratorComponent}, or null for an empty name list
     * @param surnames name of a valid prefab with {@link NameGeneratorComponent}, or null for an empty name list
     * @param nobilityAttribs name of a valid prefab with {@link NameGeneratorComponent}, or null for an empty name list
     */
    CreatureAssetTheme(String maleNames, String femaleNames, String surnames, String nobilityAttribs) {
        this(fetchNameList(maleNames),
             fetchNameList(femaleNames),
             fetchNameList(surnames),
             fetchNameList(nobilityAttribs));
    }

    CreatureAssetTheme(List<String> maleNames, List<String> femaleNames, List<String> surnames, List<String> nobAttribs) {
        this.maleNames = maleNames;
        this.femaleNames = femaleNames;
        this.surnames = surnames;
        this.nobAttribs = nobAttribs;
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
        if (s == null) {
            return Collections.emptyList();
        } else {
            return Assets.getPrefab(s).map(prefab -> {
                NameGeneratorComponent comp = prefab.getComponent(NameGeneratorComponent.class);
                return Collections.unmodifiableList(comp.nameList);
            }).get();
        }
    }

    /**
     * @return the maleNames
     */
    @Override
    public List<String> getMaleNames() {
        return this.maleNames;
    }

    /**
     * @return the femaleNames
     */
    @Override
    public List<String> getFemaleNames() {
        return this.femaleNames;
    }

    /**
     * @return the surnames
     */
    @Override
    public List<String> getSurnames() {
        return this.surnames;
    }

    /**
     * @return the nobAttribs
     */
    @Override
    public List<String> getNobilityAttributes() {
        return this.nobAttribs;
    }


}
