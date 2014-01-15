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
 * TODO Type description
 * @author Martin Steiger
 */
public class TownAffinityVector implements AffinityVector {

    private double heightAffinity;
    private double lakeAffinity;
    
    /**
     * @return the heightAffinity
     */
    public double getHeightAffinity() {
        return this.heightAffinity;
    }
    /**
     * @param heightAffinity the heightAffinity to set
     */
    public void setHeightAffinity(double heightAffinity) {
        Preconditions.checkArgument(heightAffinity >= -1 && heightAffinity <= 1);
        
        this.heightAffinity = heightAffinity;
    }
    /**
     * @return the lakeAffinity
     */
    public double getLakeAffinity() {
        return this.lakeAffinity;
    }
    /**
     * @param lakeAffinity the lakeAffinity to set
     */
    public void setLakeAffinity(double lakeAffinity) {
        Preconditions.checkArgument(lakeAffinity >= -1 && lakeAffinity <= 1);

        this.lakeAffinity = lakeAffinity;
    }
    
    
}
