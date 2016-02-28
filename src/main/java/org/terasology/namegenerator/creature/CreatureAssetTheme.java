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

import org.terasology.utilities.Assets;
import org.terasology.entitySystem.prefab.Prefab;
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
    DWARF("dwarvenMaleNames", "dwarvenFemaleNames", "dwarvenMaleNames", "nobilityAttribs");


    /**
     * Returns the default theme
     */
    public static final CreatureAssetTheme DEFAULT = ELVEN;

    private final List<String> maleNames;
    private final List<String> femaleNames;
    private final List<String> surnames;
    private final List<String> nobAttribs;

    /**
     * @param maleNames valid prefab with {@link NameGeneratorComponent}
     * @param femaleNames valid prefab with {@link NameGeneratorComponent}
     * @param surnames valid prefab with {@link NameGeneratorComponent}
     * @param nobilityAttribs valid prefab with {@link NameGeneratorComponent}
     */
    CreatureAssetTheme(String maleNames, String femaleNames, String surnames, String nobilityAttribs) {
        this(Assets.getPrefab(maleNames).get(),
                Assets.getPrefab(femaleNames).get(),
                Assets.getPrefab(surnames).get(),
                Assets.getPrefab(nobilityAttribs).get());
    }

    /**
     * @param maleNamesPf valid prefab with {@link NameGeneratorComponent}
     * @param femaleNamesPf valid prefab with {@link NameGeneratorComponent}
     * @param surnamesPf valid prefab with {@link NameGeneratorComponent}
     * @param nobilityAttribsPf valid prefab with {@link NameGeneratorComponent}
     */
    CreatureAssetTheme(Prefab maleNamesPf, Prefab femaleNamesPf, Prefab surnamesPf, Prefab nobilityAttribsPf) {
        NameGeneratorComponent comp;

        comp = maleNamesPf.getComponent(NameGeneratorComponent.class);
        maleNames = Collections.unmodifiableList(comp.nameList);

        comp = femaleNamesPf.getComponent(NameGeneratorComponent.class);
        femaleNames = Collections.unmodifiableList(comp.nameList);

        comp = surnamesPf.getComponent(NameGeneratorComponent.class);
        surnames = Collections.unmodifiableList(comp.nameList);

        comp = nobilityAttribsPf.getComponent(NameGeneratorComponent.class);
        nobAttribs = Collections.unmodifiableList(comp.nameList);
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
