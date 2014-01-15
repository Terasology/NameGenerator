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

import java.util.Arrays;
import java.util.List;

/**
 * The (default) theme based on traditional English names 
 * @author Martin Steiger
 */
public enum EnglishTheme implements Theme {
    /**
     * The only instance (ensured by enum)
     */
    INSTANCE;
    
    private final List<String> townNames = Arrays.asList(
            "Dunley", "Woolford", "Guildcaster", "Keldchester", "Kirmere", "Otterhaven", 
            "Westcaster", "Kinhey", "Oulley", "Ilchester", "Oxpool", "Langthwaite", "Kirhaven", 
            "Middleden", "Redwich", "Hazelthorpe", "Market Langmouth", "Hophall", "Hartfold", 
            "Barleigh", "Norley", "Westmarsh", "Hazelburgh", "Southhurst", "Henchester", "Foxport", 
            "Northburn", "Guildchester", "Overworth", "Norholm", "Foxhampton", "Upper Kirhall",
            "Guildhalgh", "Dunpool", "Guildholm", "Middledale", "Langbeck", "Presbeck", "Birchbrook", 
            "Kelddale", "Woolwike", "Northwick", "Marsden", "Blackhalgh", "Normere",
            "Redhey", "Dunhampton", "Whiteburgh", "Guildburgh", "Northmouth", "Ildon", "Ilbeck", 
            "Whiteholm", "Ilport", "Marswike", "Portshampton", "Woolwich", "Kirton", "Middledon", "Otterwick");

    private final List<String> lakeNames = Arrays.asList(
            "Agnes", "Agnus", "Alice", "Alisceon", "Alison", "Alisone", "Allison", "Alson", "Alys", 
            "Alyson", "Alysone", "Ameis", "Amfelice", "Amphelice", "Amphelicia", "Amphillis", "Ancreta", 
            "Ankerita", "Ann", "Anna", "Anne", "Audry", "Avice", "Avis", "Barbery", "Beatrix", "Beautrice",
            "Berta", "Blanch", "Catherine", "Cecilie", "Cecily", "Christian", "Clemens", "Cristina", "Diana", 
            "Dorathea", "Dorothy", "Dorythye", "Elen", "Elene", "Elezabeth", "Elianora", "Elison", 
            "Elizabeth", "Elizabethe", "Ellin", "Ellyn", "Elysabeth", "Elyzabeth", "Emma", "Emme", "Esabell", 
            "Eustace", "Eve", "Ezabell", "Felice", "Fillys", "Godlefe", "Grace", "Gresilda", "Helen", "Helene", 
            "Helysoune", "Hylde", "Ibbet", "Imyne", "Ione", "Isabel", "Isata", "Isobel", "Isott", "Issabell",
            "Issobell", "Jane", "Janet", "Jenefer", "Jenet", "Jennet", "Joan", "Joane", "Joanna", "Johanna", 
            "Johne", "Jone", "Joyse", "Katerina", "Katerine", "Kateryn", "Kateryne", "Katherine", "Katheryn", 
            "Kynborough", "Lovdie", "Malie", "Margaret", "Margareta", "Margarita", "Margat", "Margerie", 
            "Margery", "Margerye", "Marget", "Margyt", "Mariora", "Marjorie", "Markaret", "Martha", "Mary", 
            "Matilda", "Maud", "Maude", "Mawde", "Mawdelyn", "Merget", "Mergret", "Moulde", "Nicholina",
            "Olyff", "Olyffe", "Parnell", "Roos", "Rosa", "Rose", "Rosemunda", "Sanche", "Sicillia", "Susane", 
            "Sybby", "Sybill", "Sybyll", "Sysley", "Thomeson");
    
    @Override
    public List<String> getTownNames() {
        return townNames;
    }
    
    @Override
    public List<String> getLakeNames() {
        return lakeNames;
    }
}
