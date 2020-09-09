// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.namegenerator.town;

import com.google.common.base.Preconditions;
import org.terasology.namegenerator.AffinityVector;

/**
 * Defines different attribute affinities for town names
 */
public class TownAffinityVector implements AffinityVector {

    private double prefixAffinity;
    private double postfixAffinity;

    /**
     * Initializes default values
     */
    protected TownAffinityVector() {
        // default values are 0

        // avoid direct instantiation
    }

    /**
     * @return an instance with default values
     */
    public static TownAffinityVector create() {
        return new TownAffinityVector();
    }

    /**
     * @return the affinity to prefixes in [0..1]
     */
    public double getPrefixAffinity() {
        return prefixAffinity;
    }

    /**
     * @return the affinity to postfixes in [0..1]
     */
    public double getPostfixAffinity() {
        return postfixAffinity;
    }

    /**
     * @param aff the affinity to affixes in [0..1]
     * @return this
     */
    public TownAffinityVector prefix(double aff) {
        double combAff = aff + postfixAffinity;
        Preconditions.checkArgument(aff >= 0 && aff <= 1, "Affinity must be in [0..1], but is " + aff);
        Preconditions.checkArgument(combAff >= 0 && combAff <= 1,
                "Pre + postfix affinity must be in [0..1], but is " + combAff);

        this.prefixAffinity = aff;

        return this;
    }

    /**
     * @param aff the affinity to affixes in [0..1]
     * @return this
     */
    public TownAffinityVector postfix(double aff) {
        double combAff = aff + prefixAffinity;
        Preconditions.checkArgument(aff >= 0 && aff <= 1, "Affinity must be in [0..1], but is " + aff);
        Preconditions.checkArgument(combAff >= 0 && combAff <= 1,
                "Pre + postfix affinity must be in [0..1], but is " + combAff);

        this.postfixAffinity = aff;

        return this;
    }


}
