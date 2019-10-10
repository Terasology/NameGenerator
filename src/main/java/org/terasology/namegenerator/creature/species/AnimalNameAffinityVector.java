/*
 * Copyright 2019 MovingBlocks
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

package org.terasology.namegenerator.creature.species;

import com.google.common.base.Preconditions;
import org.terasology.namegenerator.AffinityVector;

/**
 * Defines different attribute affinities for water names
 */
public class AnimalNameAffinityVector implements AffinityVector {

    private double regionalAffinity;
    private double adjectiveAffinity;

    /**
     * Initializes default values
     */
    protected AnimalNameAffinityVector() {
        // default values are 0

        // avoid direct instantiation
    }

    /**
     * @return an instance with default values
     */
    public static AnimalNameAffinityVector create() {
        return new AnimalNameAffinityVector();
    }

    /**
     * @return the prefix type affinity in [0..1]
     */
    public double getRegionalAffinity() {
        return regionalAffinity;
    }

    /**
     * @return the postfix type affinity in [0..1]
     */
    public double getAdjectiveAffinity() {
        return adjectiveAffinity;
    }

    /**
     * @param regional the regional in [0..1]
     * @param adjective the adjective in [0..1]
     * @return this
     */
    public AnimalNameAffinityVector type(double regional, double adjective) {
        Preconditions.checkArgument(regional >= 0 && regional <= 1, "Region must be in [0..1], but is " + regional);
        Preconditions.checkArgument(adjective >= 0 && adjective <= 1, "Adjective must be in [0..1], but is " + adjective);

        this.regionalAffinity = regional;
        this.adjectiveAffinity = adjective;

        return this;
    }

}
