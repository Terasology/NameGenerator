// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.waters;

import com.google.common.base.Preconditions;
import org.terasology.namegenerator.AffinityVector;

/**
 * Defines different attribute affinities for water names
 */
public class WaterAffinityVector implements AffinityVector {

    private double typeAffinity;

    /**
     * Initializes default values
     */
    protected WaterAffinityVector() {
        // default values are 0

        // avoid direct instantiation
    }

    /**
     * @return an instance with default values
     */
    public static WaterAffinityVector create() {
        return new WaterAffinityVector();
    }

    /**
     * @return the water type affinity in [0..1]
     */
    public double getWaterType() {
        return typeAffinity;
    }

    /**
     * @param type the water type in [0..1]
     * @return this
     */
    public WaterAffinityVector type(double type) {
        Preconditions.checkArgument(type >= 0 && type <= 1, "Type must be in [0..1], but is " + type);

        this.typeAffinity = type;

        return this;
    }

}
