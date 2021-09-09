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

import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.entitySystem.entity.lifecycleEvents.OnAddedComponent;
import org.terasology.engine.entitySystem.systems.BaseComponentSystem;
import org.terasology.engine.entitySystem.systems.RegisterSystem;
import org.terasology.gestalt.entitysystem.event.ReceiveEvent;

import java.util.Locale;

/**
 * Triggers the generation of a name if a {@link CreatureNameGeneratorComponent} is present
 */
@RegisterSystem
public class CreatureNameGeneratorSystem extends BaseComponentSystem {

    @ReceiveEvent
    public void onAdded(OnAddedComponent event, EntityRef entityRef, CreatureNameGeneratorComponent genComp) {
        CreatureNameComponent nameComp = entityRef.getComponent(CreatureNameComponent.class);

        if (isNullOrEmpty(nameComp)) {
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
            entityRef.addComponent(nameComp.toDisplayInformation());
        }
    }

    private boolean isNullOrEmpty(CreatureNameComponent nameComp) {
        if (nameComp == null) {
            return true;
        }

        if (nameComp.firstName == null) {
            return true;
        }

        if (nameComp.lastName == null) {
            return true;
        }

        return false;
    }
}

