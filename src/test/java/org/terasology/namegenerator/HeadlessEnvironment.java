/*
 * Copyright 2013 MovingBlocks
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

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.nio.file.FileSystem;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.nio.file.ShrinkWrapFileSystems;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.terasology.asset.AssetFactory;
import org.terasology.asset.AssetManager;
import org.terasology.asset.AssetType;
import org.terasology.asset.AssetUri;
import org.terasology.asset.sources.ClasspathSource;
import org.terasology.audio.AudioManager;
import org.terasology.audio.nullAudio.NullAudioManager;
import org.terasology.classMetadata.reflect.ReflectionReflectFactory;
import org.terasology.config.Config;
import org.terasology.engine.ComponentSystemManager;
import org.terasology.engine.CoreRegistry;
import org.terasology.engine.EngineTime;
import org.terasology.engine.TerasologyConstants;
import org.terasology.engine.TerasologyEngine;
import org.terasology.engine.Time;
import org.terasology.engine.bootstrap.EntitySystemBuilder;
import org.terasology.engine.modes.loadProcesses.LoadPrefabs;
import org.terasology.engine.module.Module;
import org.terasology.engine.module.ModuleManager;
import org.terasology.engine.module.ModuleManagerImpl;
import org.terasology.engine.module.ModuleSecurityManager;
import org.terasology.engine.paths.PathManager;
import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.entitySystem.prefab.PrefabData;
import org.terasology.entitySystem.prefab.internal.PojoPrefab;
import org.terasology.network.NetworkSystem;
import org.terasology.network.internal.NetworkSystemImpl;
import org.terasology.physics.CollisionGroupManager;
import org.terasology.rendering.nui.skin.UISkin;
import org.terasology.rendering.nui.skin.UISkinData;
import org.terasology.world.block.BlockManager;
import org.terasology.world.block.family.AlignToSurfaceFamilyFactory;
import org.terasology.world.block.family.DefaultBlockFamilyFactoryRegistry;
import org.terasology.world.block.family.HorizontalBlockFamilyFactory;
import org.terasology.world.block.internal.BlockManagerImpl;
import org.terasology.world.block.loader.WorldAtlas;
import org.terasology.world.block.shapes.BlockShape;
import org.terasology.world.block.shapes.BlockShapeData;
import org.terasology.world.block.shapes.BlockShapeImpl;

/**
 * Setup a headless ( = no graphics ) environment.
 * Based on TerasologyTestingEnvironment.
 * @author Martin Steiger
 */
public abstract class HeadlessEnvironment {

    /**
     * Setup a headless ( = no graphics ) environment
     * @throws IOException should never occur (virtual file system)
     */
    public static void setupEnvironment() throws IOException {

        final JavaArchive homeArchive = ShrinkWrap.create(JavaArchive.class);
        final FileSystem vfs = ShrinkWrapFileSystems.newFileSystem(homeArchive);
        PathManager.getInstance().useOverrideHomePath(vfs.getPath(""));

        ModuleManagerImpl moduleManager = new ModuleManagerImpl(new ModuleSecurityManager());
        moduleManager.applyActiveModules();
        CoreRegistry.put(ModuleManager.class, moduleManager);

        AssetManager assetManager = new AssetManager(moduleManager);
        CoreRegistry.put(AssetManager.class, assetManager);
        AssetType.registerAssetTypes(assetManager);
        assetManager.addAssetSource(new ClasspathSource(TerasologyConstants.ENGINE_MODULE, TerasologyEngine.class.getProtectionDomain().getCodeSource(),
                TerasologyConstants.ASSETS_SUBDIRECTORY, TerasologyConstants.OVERRIDES_SUBDIRECTORY));
        assetManager.addAssetSource(new ClasspathSource("unittest", HeadlessEnvironment.class.getProtectionDomain().getCodeSource(), TerasologyConstants.ASSETS_SUBDIRECTORY,
                TerasologyConstants.OVERRIDES_SUBDIRECTORY));

        Config config = new Config();
        CoreRegistry.put(Config.class, config);

        NullAudioManager audioManager = new NullAudioManager();

        assetManager.setAssetFactory(AssetType.PREFAB, new AssetFactory<PrefabData, Prefab>() {

            @Override
            public Prefab buildAsset(AssetUri uri, PrefabData data) {
                return new PojoPrefab(uri, data);
            }
        });
        assetManager.setAssetFactory(AssetType.SHAPE, new AssetFactory<BlockShapeData, BlockShape>() {

            @Override
            public BlockShape buildAsset(AssetUri uri, BlockShapeData data) {
                return new BlockShapeImpl(uri, data);
            }
        });

        assetManager.setAssetFactory(AssetType.UI_SKIN, new AssetFactory<UISkinData, UISkin>() {
            @Override
            public UISkin buildAsset(AssetUri uri, UISkinData data) {
                return new UISkin(uri, data);
            }
        });

        assetManager.setAssetFactory(AssetType.SOUND, audioManager.getStaticSoundFactory());
        assetManager.setAssetFactory(AssetType.MUSIC, audioManager.getStreamingSoundFactory());
        
        // mock graphics-related asset factories
        assetManager.setAssetFactory(AssetType.SHADER, mock(AssetFactory.class));
        assetManager.setAssetFactory(AssetType.MESH, mock(AssetFactory.class));
        assetManager.setAssetFactory(AssetType.MATERIAL, mock(AssetFactory.class));
        assetManager.setAssetFactory(AssetType.TEXTURE, mock(AssetFactory.class));
        assetManager.setAssetFactory(AssetType.SUBTEXTURE, mock(AssetFactory.class));
        assetManager.setAssetFactory(AssetType.ATLAS, mock(AssetFactory.class));

        DefaultBlockFamilyFactoryRegistry blockFamilyFactoryRegistry = new DefaultBlockFamilyFactoryRegistry();
        blockFamilyFactoryRegistry.setBlockFamilyFactory("horizontal", new HorizontalBlockFamilyFactory());
        blockFamilyFactoryRegistry.setBlockFamilyFactory("alignToSurface", new AlignToSurfaceFamilyFactory());
        WorldAtlas worldAtlas = mock(WorldAtlas.class);
        BlockManagerImpl blockManager = new BlockManagerImpl(worldAtlas, blockFamilyFactoryRegistry);
        CoreRegistry.put(BlockManager.class, blockManager);

        CoreRegistry.put(AudioManager.class, audioManager);

        CollisionGroupManager collisionGroupManager = new CollisionGroupManager();
        CoreRegistry.put(CollisionGroupManager.class, collisionGroupManager);
        PathManager.getInstance().setCurrentSaveTitle("world1");

        
        CoreRegistry.put(ModuleManager.class, moduleManager);

        EngineTime mockTime = mock(EngineTime.class);
        CoreRegistry.put(Time.class, mockTime);
        NetworkSystemImpl networkSystem = new NetworkSystemImpl(mockTime);
        CoreRegistry.put(NetworkSystem.class, networkSystem);

        EntitySystemBuilder engineEntityManager = new EntitySystemBuilder();
        engineEntityManager.build(CoreRegistry.get(ModuleManager.class), networkSystem, new ReflectionReflectFactory());

        // The StorageManager creates a thread pool (through TaskMaster) which isn't closed automatically
        // we don't need it, so we don't create it and avoid the clean-up hassle
        
//        CoreRegistry.put(StorageManager.class, new StorageManagerInternal(moduleManager, engineEntityManager));

        ComponentSystemManager componentSystemManager = new ComponentSystemManager();
        CoreRegistry.put(ComponentSystemManager.class, componentSystemManager);
        LoadPrefabs prefabLoadStep = new LoadPrefabs();

        boolean complete = false;
        prefabLoadStep.begin();
        while (!complete) {
            complete = prefabLoadStep.step();
        }
        CoreRegistry.get(ComponentSystemManager.class).initialise();
        
        // activate all modules
        for (Module m : moduleManager.getModules()) {
            moduleManager.enableModule(m);
        }

        moduleManager.applyActiveModules();
        assetManager.clear();
        assetManager.applyOverrides();        
    }

}
