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

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;
import org.terasology.namegenerator.logic.generators.TrainingGenerator;

import com.google.common.collect.Sets;

/**
 * Tests {@link TrainingGenerator}
 * @author Martin Steiger
 */
public class TrainingGeneratorTest {

    /**
     * See if all names were generated when running over full size
     */
    @Test
    public void testEquals() {
        Set<String> trainingData = Sets.newHashSet(Arrays.asList(ElvenMaleNames.NAMES));
        TrainingGenerator tg = new TrainingGenerator(1234, trainingData);
        
        Set<String> gen = Sets.newHashSet();
        
        for (int i = 0; i < trainingData.size(); i++) {
            gen.add(tg.nextName());
        }
        
        assertEquals(trainingData, gen);
    }
    
    /**
     * See if all names were generated when running over full size twice
     */
    @Test
    public void testOverrun() {
        Set<String> trainingData = Sets.newHashSet(Arrays.asList(ElvenMaleNames.NAMES));
        TrainingGenerator tg = new TrainingGenerator(1234, trainingData);
        
        Set<String> gen = Sets.newHashSet();
        
        for (int i = 0; i < trainingData.size() * 2; i++) {
            gen.add(tg.nextName());
        }
        
        assertEquals(trainingData, gen);
    }
}
