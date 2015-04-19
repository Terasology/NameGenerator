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

import org.terasology.namegenerator.AffinityVector;

import com.google.common.base.Preconditions;

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
     * @return this
     */
    public CreatureAffinityVector maleOnly() {
        return genderRatio(0);
    }

    /**
     * Create female names only
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
