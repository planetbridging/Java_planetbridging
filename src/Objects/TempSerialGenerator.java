/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.util.Random;

/**
 *
 * @author R3DN3T
 */
public class TempSerialGenerator {
    private static final String CharRandom = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final String[] RandomColors = 
        {
            "Blue",
            "Red",
            "Green",
            "Yellow",
            "Orange",
            "Brown",
            "Purple",
            "Pink"
        };
    
    private static final String[] RandomAnimals = 
        {
            "Bird",
            "Dog",
            "Cat",
            "Worm",
            "Fish",
            "Lizard",
            "Elephant",
            "Bear"
        };
    
    public String RandomKey(final int length) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            /*char c = (char)(r.nextInt((int)(Character.MAX_VALUE)));
            System.out.println(c);
            sb.append(c);*/
            
            //int RandomCharAt = randInt(0, 35);
            Random ran = new Random();
            int x = ran.nextInt(36);
            char c = CharRandom.charAt(x);
            sb.append(c);
        }

        return sb.toString();
    }
    
    public String GetRandomClientName(){
        String GuestBuilding = "";
        
        Random rancolor = new Random();
        int xcolor = rancolor.nextInt(8);
        String RColor = RandomColors[xcolor];
        
        Random rananimal = new Random();
        int xanimal = rananimal.nextInt(8);
        String RAniaml = RandomAnimals[xanimal];
        
        GuestBuilding = RColor + RAniaml +"Guest" + RandomKey(16);
        
        return GuestBuilding;
    }
}
