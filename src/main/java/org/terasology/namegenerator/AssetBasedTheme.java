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
import org.terasology.namegenerator.logic.generators.NameGeneratorComponent;

/**
 * TODO Type description
 * @author Martin Steiger
 */
public class AssetBasedTheme {
    
    private final List<String> names;
    
    /**
     * Create a new name generator, using the specified asset as example list.
     * <p/>
     * The asset URI must point to a valid name list prefab which has a {@link NameGeneratorComponent}.
     * 
     * @param seed the seed for the random number generator
     * @param assetUri valid asset uri with NameGeneratorComponent
     */
    public AssetBasedTheme(Prefab assetUri) {
        NameGeneratorComponent nameGenComp = assetUri.getComponent(NameGeneratorComponent.class);
        names = nameGenComp.nameList;
    }

    /**
     * Create a new name generator, using the given String as asset uri.
     * The String must denote a simple uri and the asset must contain a {@link NameGeneratorComponent}.
     * @param simpleUri simple asset uri as string
     */
    public AssetBasedTheme(String simpleUri) {
        this(Assets.getPrefab(simpleUri));
    }
}
