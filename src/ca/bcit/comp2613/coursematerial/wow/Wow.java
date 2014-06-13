package ca.bcit.comp2613.coursematerial.wow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * 
 * @author Henry
 * 
 *         Copy this package to your repo (a package can copied via 1) highlighting the package
 *         folder, 2) right click and then 3) copy).  If that fails, copy the files one by one ;)
 *         
 *         
 *         So here's the problem. How powerful are my
 *         characters? 
 * 
 *         1) Modify the Race enum to include HUMAN x
 *         
 *         2) Implement rollCharacters (generates 100 Random Characters) PROPERLY
 *         
 *         3) Sort the characters by level, strength, armour 
 *         
 *         4) Print out the characters in a
 *         readable format (modify printCharacters)
 *         
 *         5) Implement checkAndRemoveAnyHordeSpies (hint: ORCs are Horde)
 *         
 *         6) Print out the
 *         characters again after we remove those pesky Horde spies
 *         
 *         7) Modify checkForPandaren : If we have any Pandaren races in our characters list, throw a DontHaveTheExpansionException
 *         
 *         
 *         When you are finished, please commit this package as
 *         ca.bcit.comp2613.<your student id>.wow in your GitHub project folder 
 *         note that package name should *always* be in lowercase 
 *         
 *        
 */
public class Wow {
	private static Random rand = new Random();
	public static void main(String[] args) {
		ArrayList<Character> characters = rollCharacters();
		Comparator<Character> characterComparator = createCharacterComparator();
		Collections.sort(characters, characterComparator);
		printCharacters(characters);
		checkAndRemoveAnyHordeSpies(characters);
		printCharacters(characters);
		try{
			checkForPandaren(characters);
		}catch (DontHaveTheExpansionException e){
			System.out.println(e);
		}
		
	}

	private static Race getRandomRace() {
		Race retval = null;
		Race[] races = Race.values();		
		retval = races[rand.nextInt(races.length)];
		return retval;
	}
	
	private static int getRandomLevel() {
		return rand.nextInt(86); // TODO is this right? Changed from 101 to 86
	}

	private static int getRandomStrength() {
		return rand.nextInt(101); // returns a number between 0 and 100
	}
	
	private static int getRandomArmour() {
		return rand.nextInt(101); // returns a number between 0 and 100
	}
	
	private static ArrayList<Character> rollCharacters() {
		// TODO create 100 Random Characters 
				// The Race's assigned should be pretty random
				// Each Character's level must be between 0 and 85
				// strength, 0-100
				// armour, 0-100
				// find and fix the bug
		ArrayList<Character> retval = new ArrayList<Character>();
		for (int i = 0; i < 100; i++) {
			Race race = getRandomRace();
			int level = getRandomLevel();
			int strength = getRandomStrength();
			int armour = getRandomArmour();
			Character character = new Character(race, level, strength, armour);
			retval.add(character);
		}
		return retval;
	}

	

	private static Comparator<Character> createCharacterComparator() {
		
		return new Comparator<Character>() {
			@Override
			public int compare(Character character1, Character character2) {
				// TODO supposed compares Characters by level, strength and armour
				// note that this soln isn't quite correct,
				// find the bug and fix it
				int retval = Integer.valueOf(character1.getLevel()).compareTo(character2.getLevel());
				if (retval == 0) {
					retval = Integer.valueOf(character1.getStrength()).compareTo(character2.getStrength());
					if (retval == 0) {
						retval = Integer.valueOf(character1.getArmour()).compareTo(character2.getArmour());
					}
				}
				return retval;
			}
		};
	}

	private static void checkAndRemoveAnyHordeSpies(ArrayList<Character> characters) {
		// TODO loop through each character in the characters list
		// if the character is of Race: ORC, remove that character from the
		// list
		Iterator<Character> iterator = characters.iterator();
		while (iterator.hasNext()) {
			Character character = iterator.next();
			if (character.getRace() == Race.ORC) { //TODO not quite right is it?
				iterator.remove();
			}
		}
	}

	private static void printCharacters(ArrayList<Character> characters) {
		for (Character character : characters) {
			//TODO how do I print out the character's armour?
			System.out.println("Level: " + character.getLevel() + ", Strength " + character.getStrength() + ", Armour: " +character.getArmour() + ", Race: " + character.getRace());
		}

	}
	
	private static void checkForPandaren(ArrayList<Character> characters) throws DontHaveTheExpansionException{
		// TODO if there are any PANDAREN races in our List, throw a DontHaveTheExpansionException
		for (Character character : characters) {
			if (character.getRace() == Race.PANDAREN) { 
				throw new DontHaveTheExpansionException();
			}
		}
	}

}
