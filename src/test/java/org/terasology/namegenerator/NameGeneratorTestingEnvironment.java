/*
 * Copyright 2016 MovingBlocks
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
package org.terasology.namegenerator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.terasology.HeadlessEnvironment;
import org.terasology.context.Context;
import org.terasology.engine.module.ModuleManager;
import org.terasology.entitySystem.metadata.ComponentLibrary;
import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.entitySystem.prefab.PrefabData;
import org.terasology.entitySystem.prefab.internal.PojoPrefab;
import org.terasology.entitySystem.prefab.internal.PrefabFormat;
import org.terasology.gestalt.assets.AssetFactory;
import org.terasology.gestalt.assets.management.AssetManager;
import org.terasology.gestalt.assets.module.ModuleAwareAssetTypeManager;
import org.terasology.gestalt.naming.Name;
import org.terasology.persistence.typeHandling.TypeHandlerLibrary;

public class NameGeneratorTestingEnvironment {
    private static HeadlessEnvironment env;

    /**
     * Setup headless environment
     */
    @BeforeClass
    public static void setUpClass() {
        env = new HeadlessEnvironment(new Name("NameGenerator"));

        Context context = env.getContext();
        ModuleManager moduleManager = context.get(ModuleManager.class);

        // Taken from: org.terasology.TerasologyTestingEnvironment
        ModuleAwareAssetTypeManager assetTypeManager = new ModuleAwareAssetTypeManager();
        assetTypeManager.registerCoreAssetType(Prefab.class,
                (AssetFactory<Prefab, PrefabData>) PojoPrefab::new, "prefabs");

        ComponentLibrary componentLibrary = context.get(ComponentLibrary.class);
        TypeHandlerLibrary typeSerializationLibrary = context.get(TypeHandlerLibrary.class);
        PrefabFormat prefabFormat = new PrefabFormat(componentLibrary, typeSerializationLibrary);
        assetTypeManager.registerCoreFormat(Prefab.class, prefabFormat);

        assetTypeManager.switchEnvironment(moduleManager.getEnvironment());
        context.put(AssetManager.class, assetTypeManager.getAssetManager());
    }

    /**
     * Clean up
     * @throws Exception never
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
        env.close();
    }
}
