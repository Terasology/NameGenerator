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

package org.terasology.namegenerator.creature.species.adjective;

import com.google.common.base.Preconditions;
import org.terasology.namegenerator.AffinityVector;

/**
 * Defines different attribute affinities for water names
 */
public class AnimalAdjectiveAffinityVector implements AffinityVector {

    private double prefixAffinity;
    private double postfixAffinity;

    /**
     * Initializes default values
     */
    protected AnimalAdjectiveAffinityVector() {
        // default values are 0

        // avoid direct instantiation
    }

    /**
     * @return an instance with default values
     */
    public static AnimalAdjectiveAffinityVector create() {
        return new AnimalAdjectiveAffinityVector();
    }

    /**
     * @return the prefix type affinity in [0..1]
     */
    public double getPrefixAffinity() {
        return prefixAffinity;
    }

    /**
     * @return the postfix type affinity in [0..1]
     */
    public double getPostfixAffinity() {
        return postfixAffinity;
    }

    /**
     * @param prefix the prefix in [0..1]
     * @param postfix the postfix in [0..1]
     * @return this
     */
    public AnimalAdjectiveAffinityVector type(double prefix, double postfix) {
        Preconditions.checkArgument(prefix >= 0 && prefix <= 1, "Prefix must be in [0..1], but is " + prefix);
        Preconditions.checkArgument(postfix >= 0 && postfix <= 1, "Postfix must be in [0..1], but is " + postfix);

        this.prefixAffinity = prefix;
        this.postfixAffinity = postfix;

        return this;
    }

}
