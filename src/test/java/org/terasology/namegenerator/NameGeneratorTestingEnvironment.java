// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.namegenerator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.terasology.engine.HeadlessEnvironment;
import org.terasology.engine.context.Context;
import org.terasology.engine.core.module.ModuleManager;
import org.terasology.engine.entitySystem.metadata.ComponentLibrary;
import org.terasology.engine.entitySystem.prefab.Prefab;
import org.terasology.engine.entitySystem.prefab.PrefabData;
import org.terasology.engine.entitySystem.prefab.internal.PojoPrefab;
import org.terasology.engine.entitySystem.prefab.internal.PrefabFormat;
import org.terasology.gestalt.assets.AssetType;
import org.terasology.gestalt.assets.management.AssetManager;
import org.terasology.gestalt.assets.module.ModuleAwareAssetTypeManager;
import org.terasology.gestalt.assets.module.ModuleAwareAssetTypeManagerImpl;
import org.terasology.gestalt.naming.Name;
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
        ModuleAwareAssetTypeManager assetTypeManager = new ModuleAwareAssetTypeManagerImpl();
        AssetType<Prefab, PrefabData> prefabDataAssetType = assetTypeManager.createAssetType(Prefab.class, PojoPrefab::new, "prefabs");


        ComponentLibrary componentLibrary = context.get(ComponentLibrary.class);
        TypeHandlerLibrary typeSerializationLibrary = context.get(TypeHandlerLibrary.class);
        PrefabFormat prefabFormat = new PrefabFormat(componentLibrary, typeSerializationLibrary);
        assetTypeManager.getAssetFileDataProducer(prefabDataAssetType).addAssetFormat( prefabFormat);

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
