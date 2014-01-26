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

import java.util.Locale;

import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.entity.lifecycleEvents.OnAddedComponent;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.ComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;

/**
 * Triggers the generation of a name if a {@link CreatureNameGeneratorComponent} is present
 * @author synopia
 * @author Martin Steiger
 */
@RegisterSystem
public class CreatureNameGeneratorSystem implements ComponentSystem {
    
    @Override
    public void initialise() {
        // nothing to do
    }

    @Override
    public void shutdown() {
        // nothing to do
    }

    @ReceiveEvent
    public void onAdded(OnAddedComponent event, EntityRef entityRef, CreatureNameGeneratorComponent genComp) {
        CreatureNameComponent nameComp = entityRef.getComponent(CreatureNameComponent.class);

        if (nameComp != null) {
            // this makes the generation deterministic
            long seed = entityRef.hashCode();
            
            CreatureTheme theme;
            try {
                String normThemeName = genComp.theme.toUpperCase(Locale.ENGLISH);
                theme = Enum.valueOf(CreatureAssetTheme.class, normThemeName);
            } catch (IllegalArgumentException | NullPointerException e) {
                theme = CreatureAssetTheme.DEFAULT;
            }
            
            CreatureNameProvider p = new CreatureNameProvider(seed, theme);
            CreatureAffinityVector affinity = CreatureAffinityVector.create();
            affinity.genderRatio(genComp.genderRatio);
            affinity.nobility(genComp.nobility);
            
            nameComp = p.generateNameComponent(affinity);

            entityRef.addComponent(nameComp);
        }

    }
}

