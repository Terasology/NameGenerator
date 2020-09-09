// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.namegenerator.creature;

import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.entitySystem.entity.lifecycleEvents.OnAddedComponent;
import org.terasology.engine.entitySystem.event.ReceiveEvent;
import org.terasology.engine.entitySystem.systems.BaseComponentSystem;
import org.terasology.engine.entitySystem.systems.RegisterSystem;

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

        return nameComp.lastName == null;
    }
}

