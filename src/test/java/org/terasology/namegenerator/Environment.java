/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License"){ }
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

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.engine.CoreRegistry;

/**
 * Setup an Terasology environment
 * @author Martin Steiger
 */
public class Environment {

    private static final Logger logger = LoggerFactory.getLogger(Environment.class);

    /**
     * Default setup order
     * @throws IOException should never occur (virtual file system) 
     */
    public Environment() throws IOException {

        Object total = Profiler.start();
        Object id = Profiler.start();

        setupPathManager();

        logger.info("PathManager: " + Profiler.getAsStrindAndReset(id));

        setupModuleManager();

        logger.info("ModuleManager: " + Profiler.getAsStrindAndReset(id));

        setupConfig();

        logger.info("Config: " + Profiler.getAsStrindAndReset(id));

        setupAudio();

        logger.info("Audio: " + Profiler.getAsStrindAndReset(id));

        setupAssetManager();

        logger.info("Asset: " + Profiler.getAsStrindAndReset(id));

        setupBlockManager();

        logger.info("BlockManager: " + Profiler.getAsStrindAndReset(id));

        setupCollisionManager();

        logger.info("CollisionManager: " + Profiler.getAsStrindAndReset(id));

        setupNetwork();

        logger.info("NetworkManager: " + Profiler.getAsStrindAndReset(id));

        setupEntitySystem();

        logger.info("EntitySystem: " + Profiler.getAsStrindAndReset(id));

        // The StorageManager creates a thread pool (through TaskMaster) which
        // isn't closed automatically
        // we don't need it, so we don't create it and avoid the clean-up hassle
        setupStorageManager();

        logger.info("StorageManager: " + Profiler.getAsStrindAndReset(id));

        setupComponentManager();

        logger.info("ComponentManager: " + Profiler.getAsStrindAndReset(id));

        loadPrefabs();

        logger.info("loadPrefabs: " + Profiler.getAsStrindAndReset(id));

        activateAllModules();

        logger.info("Activate all modules: " + Profiler.getAsStrindAndReset(id));

        logger.info("Total time: " + Profiler.getAsStringAndStop(total));

        Profiler.stop(id);
    }

    protected void activateAllModules() {
        // empty
    }

    protected void loadPrefabs() {
        // empty
    }

    protected void setupComponentManager() {
        // empty
    }

    protected void setupPathManager() throws IOException {
        // empty
    }

    protected void setupModuleManager() {
        // empty
    }

    protected void setupConfig() {
        // empty
    }

    protected void setupAudio() {
        // empty
    }

    protected void setupAssetManager() {
        // empty
    }

    protected void setupEmptyAssetManager() {
        // empty
    }

    protected void setupBlockManager() {
        // empty
    }

    protected void setupCollisionManager() {
        // empty
    }

    protected void setupEntitySystem() {
        // empty
    }

    protected void setupNetwork() {
        // empty
    }

    protected void setupStorageManager() {
        // empty
    }

    /**
     * Cleans up all resources (similar to Autocloseable)
     * @throws Exception if something goes wrong
     */
    public void close() throws Exception {
        CoreRegistry.clear();
    }

}

