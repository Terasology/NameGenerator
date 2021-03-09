// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.namegenerator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.terasology.HeadlessEnvironment;
import org.terasology.assets.AssetFactory;
import org.terasology.assets.management.AssetManager;
import org.terasology.assets.module.ModuleAwareAssetTypeManager;
import org.terasology.engine.context.Context;
import org.terasology.engine.core.module.ModuleManager;
import org.terasology.engine.entitySystem.metadata.ComponentLibrary;
import org.terasology.engine.entitySystem.prefab.Prefab;
import org.terasology.engine.entitySystem.prefab.PrefabData;
import org.terasology.engine.entitySystem.prefab.internal.PojoPrefab;
import org.terasology.engine.entitySystem.prefab.internal.PrefabFormat;
import org.terasology.naming.Name;
import org.terasology.persistence.typeHandling.TypeHandlerLibrary;

public class NameGeneratorTestingEnvironment {
    private static HeadlessEnvironment env;

    /**
     * Setup headless environment
     */
    @BeforeAll
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
    @AfterAll
    public static void tearDownClass() throws Exception {
        env.close();
    }
}
