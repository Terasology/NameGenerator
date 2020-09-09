// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.creature;

import com.google.common.base.Preconditions;
import org.terasology.namegenerator.AffinityVector;

/**
 * Defines affinities to certain creature names
 */
public class CreatureAffinityVector implements AffinityVector {

    private double genderRatio = 0.5;
    private double nobility;

    /**
     * Initializes default values
     */
    protected CreatureAffinityVector() {
        // empty
    }

    /**
     * @return an instance with default values
     */
    public static CreatureAffinityVector create() {
        return new CreatureAffinityVector();
    }

    /**
     * @param ratio Gender Ratio in [0..1] representing [male..female]
     * @return this
     */
    public CreatureAffinityVector genderRatio(double ratio) {
        Preconditions.checkArgument(ratio >= 0 && ratio <= 1, "Ratio must be in [0..1], but is " + ratio);

        this.genderRatio = ratio;
        return this;
    }

    /**
     * @param aff nobility in [0..1]
     * @return this
     */
    public CreatureAffinityVector nobility(double aff) {
        Preconditions.checkArgument(aff >= 0 && aff <= 1, "Ratio must be in [0..1], but is " + aff);

        this.nobility = aff;
        return this;
    }

    /**
     * Create male names only
     *
     * @return this
     */
    public CreatureAffinityVector maleOnly() {
        return genderRatio(0);
    }

    /**
     * Create female names only
     *
     * @return this
     */
    public CreatureAffinityVector femaleOnly() {
        return genderRatio(1);
    }

    /**
     * @return the gender ratio in [0..1] representing [male..female]
     */
    public double getGenderRatio() {
        return this.genderRatio;
    }

    /**
     * @return the nobility
     */
    public double getNobility() {
        return this.nobility;
    }

}
