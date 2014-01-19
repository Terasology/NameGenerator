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

package org.terasology.namegenerator.waters;

import org.terasology.namegenerator.AffinityVector;

import com.google.common.base.Preconditions;

/**
 * Defines different attribute affinities for water names
 * @author Martin Steiger
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
