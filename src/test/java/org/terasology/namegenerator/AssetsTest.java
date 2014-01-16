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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.asset.Assets;
import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.namegenerator.logic.generators.NameGeneratorComponent;

/**
 * TODO Type description
 * @author Martin Steiger
 */
public class AssetsTest {

    
    private static final Logger logger = LoggerFactory.getLogger(AssetsTest.class);
    
    public static void main(String[] args) throws Exception {
        

        HeadlessEnvironment.setupEnvironment();
        
        Prefab prefab = Assets.getPrefab("elvenMaleNames");
        NameGeneratorComponent component = prefab.getComponent(NameGeneratorComponent.class);
        List<String> training = component.nameList;
        System.out.println(training);
        
    }
}
