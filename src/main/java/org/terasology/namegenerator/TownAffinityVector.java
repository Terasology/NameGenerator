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

import com.google.common.base.Preconditions;

/**
 * Defines different attribute affinities for town names
 * @author Martin Steiger
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
     * @param affinity the affinity to affixes in [0..1]
     * @return this
     */
    public TownAffinityVector prefix(double affinity) {
        Preconditions.checkArgument(affinity >= 0 && affinity <= 1);

        this.prefixAffinity = affinity;
        
        return this;
    }

    /**
     * @param affinity the affinity to affixes in [0..1]
     * @return this
     */
    public TownAffinityVector postfix(double affinity) {
        Preconditions.checkArgument(affinity >= 0 && affinity <= 1);

        this.postfixAffinity = affinity;
        
        return this;
    }

    
}
