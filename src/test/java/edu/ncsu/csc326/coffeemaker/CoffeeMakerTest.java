/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modifications 
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to 
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import java.lang.ArrayIndexOutOfBoundsException;

/**
 * Unit tests for CoffeeMaker class.
 * 
 * @author Sarah Heckman
 */
public class CoffeeMakerTest {
	
	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker} 
	 * object we wish to test.
	 * 
	 * @throws RecipeException  if there was an error parsing the ingredient 
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		coffeeMaker = new CoffeeMaker();
		
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
	}
	
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
	}
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative 
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "asdf", "3");
	}
	
	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than 
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */

	@Test
	public void testMakeCoffee() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}


	@Test
	public void testMakeCoffee1() throws InventoryException {
		coffeeMaker.addInventory("10", "10", "10", "10");
		coffeeMaker.addRecipe(recipe2);
		assertEquals(0, coffeeMaker.makeCoffee(0, 75));
	}

	@Test
	public void testMakeCoffee2() {
		coffeeMaker.addRecipe(recipe3);
		assertEquals(25, coffeeMaker.makeCoffee(0, 125));
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException1() throws InventoryException {
		coffeeMaker.addInventory("4", "1", "asdf", "3");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException2() throws InventoryException {
		coffeeMaker.addInventory("asdf", "1", "0", "1");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException3() throws InventoryException {
		coffeeMaker.addInventory("1", "asdf", "1", "1");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException4() throws InventoryException {
		coffeeMaker.addInventory("1", "1", "1", "aasdf");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException5() throws InventoryException {
		coffeeMaker.addInventory("10", "1000", "-1000", "1000");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException6() throws InventoryException {
		coffeeMaker.addInventory("10", "1000", "1000", "-1000");
	}
	@Test(expected = InventoryException.class)
	public void testAddInventoryException7() throws InventoryException {
		coffeeMaker.addInventory("10", "-1000", "1000", "1000");
	}
	@Test(expected = InventoryException.class)
	public void testAddInventoryException8() throws InventoryException {
		coffeeMaker.addInventory("-10", "1000", "1000", "1000");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException9() throws InventoryException {
		coffeeMaker.addInventory("asdf", "asdf", "asdf", "asdf");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException10() throws InventoryException {
		coffeeMaker.addInventory("-1", "-1", "-1", "-1");
	}

	@Test
	public void testAddInventory2() throws InventoryException {
		coffeeMaker.addInventory("0","0","0","0");
	}

	@Test
	public void testAddInventory1() throws InventoryException {
		coffeeMaker.addInventory("10","10","10","10");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException11() throws InventoryException {
		coffeeMaker.addInventory("4", "1", "asdf", "3");
	}
	@Test(expected = InventoryException.class)
	public void testAddInventoryException12() throws InventoryException {
		coffeeMaker.addInventory("asdf", "1", "0", "1");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException13() throws InventoryException {
		coffeeMaker.addInventory("1", "asdf", "1", "1");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException14() throws InventoryException {
		coffeeMaker.addInventory("1", "1", "1", "aasdf");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException15() throws InventoryException {
		coffeeMaker.addInventory("11", "11", "1000", null);
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException16() throws InventoryException {
		coffeeMaker.addInventory("11", "11", null, "1");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException17() throws InventoryException {
		coffeeMaker.addInventory("11", null, "1000", "1");
	}

	@Test(expected = InventoryException.class)
	public void testAddInventoryException18() throws InventoryException {
		coffeeMaker.addInventory(null, "11", "1000", "1");
	}

	@Test
	public void testMakeCoffee55() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}

	@Test
	public void testMakeCoffee20() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(50, coffeeMaker.makeCoffee(0, 100));
	}

	@Test
	public void testMakeCoffee3() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(0, coffeeMaker.makeCoffee(0, 50));
	}

	@Test
	public void testMakeCoffee4() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(5, coffeeMaker.makeCoffee(0, 5));
	}

	@Test
	public void testMakeCoffee5() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(0, coffeeMaker.makeCoffee(0, 0));
	}

	@Test
	public void testMakeCoffee7() {
		coffeeMaker.addRecipe(recipe2);
		assertEquals(100, coffeeMaker.makeCoffee(1, 100));
	}

	@Test
	public void testMakeCoffee8() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		assertEquals(100, coffeeMaker.makeCoffee(1, 100));
	}


	@Test
	public void testMakeCoffee8_2() throws InventoryException {
		coffeeMaker.addInventory("100","100","100","100");
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		assertEquals(0, coffeeMaker.makeCoffee(0, 50));
		assertEquals(0, coffeeMaker.makeCoffee(1, 75));
		assertEquals(0, coffeeMaker.makeCoffee(2, 100));
		assertEquals(450, coffeeMaker.makeCoffee(0, 500));
		assertEquals(425, coffeeMaker.makeCoffee(1, 500));
		assertEquals(400, coffeeMaker.makeCoffee(2, 500));
	}

	@Test
	public void testMakeCoffee8_3() throws InventoryException {
		//coffeeMaker.addInventory("100","100","100","100");
		coffeeMaker.addRecipe(recipe2);
		assertEquals(50, coffeeMaker.makeCoffee(0, 50));
	}

	@Test
	public void testMakeCoffee14() throws InventoryException {
		coffeeMaker.addRecipe(recipe4);
		coffeeMaker.makeCoffee(-1, 65);
		assertEquals(65, coffeeMaker.makeCoffee(-1, 65));
	}

	@Test
	public void testMakeCoffee15() throws InventoryException {
		coffeeMaker.addRecipe(recipe4);
		assertEquals(1, coffeeMaker.makeCoffee(1, 1));
	}

	@Test
	public void testMakeCoffee6() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(5, coffeeMaker.makeCoffee(-1, 5));
	}

	@Test
	public void testMakeCoffee9_1() {
		coffeeMaker.addRecipe(recipe2);
		assertEquals(500, coffeeMaker.makeCoffee(0, 500));
	}

	@Test
	public void testMakeCoffee10() throws InventoryException {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(0, coffeeMaker.makeCoffee(0, 50));
	}

	@Test
	public void testMakeCoffee11() throws InventoryException {
		coffeeMaker.addInventory("10","10","10","10");
		coffeeMaker.addRecipe(recipe2);
		assertEquals(0, coffeeMaker.makeCoffee(0, 75));
	}

	@Test
	public void testMakeCoffee12() throws InventoryException {
		coffeeMaker.addRecipe(recipe3);
		assertEquals(0, coffeeMaker.makeCoffee(0, 100));
	}

	@Test
	public void testMakeCoffee13() throws InventoryException {
		coffeeMaker.addRecipe(recipe4);
		assertEquals(0, coffeeMaker.makeCoffee(0, 65));
	}

	@Test
	public void testGetrecipes111() throws InventoryException {
		coffeeMaker.addInventory("10","10","10","10");
		coffeeMaker.addRecipe(recipe1);
		Recipe[] recepieees = coffeeMaker.getRecipes();
		String a = recepieees[0].toString();
		String b =  recipe1.getName();
		assertEquals(a, b);
	}

	@Test
	public void testcheckInventory() throws InventoryException {
		coffeeMaker.addInventory("10","10","10","10");
		//String output = "Coffee: 25"+System.getProperty("line.separator")+"Milk: 25"+System.getProperty("line.separator")+"Sugar: 25"+System.getProperty("line.separator")+"Chocolate: 25"+System.getProperty("line.separator");
		String output = "Coffee: 25\nMilk: 25\nSugar: 25\nChocolate: 25\n";
		assertEquals(coffeeMaker.checkInventory(), output);
	}

	@Test
	public void testdeleteRecipe() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		Recipe[] recepieees = coffeeMaker.getRecipes();
		String a = recepieees[0].toString();
		coffeeMaker.deleteRecipe(0);
		Recipe[] recepieees2 = coffeeMaker.getRecipes();
		String b = recepieees[1].toString();
	//	System.out.println(b);
		assertEquals("Mocha", b);
	}

	@Test
	public void testEdit(){
		//coffeeMaker.editRecipe(0,recipe1);
		assertEquals(null, coffeeMaker.editRecipe(0,recipe1));
	}

	@Test
	public void testaddRecipe2(){
		//coffeeMaker.editRecipe(0,recipe1);

		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe1);
		Recipe[] recepieees2 = coffeeMaker.getRecipes();
		assertEquals(null, recepieees2[1]);

	}



	@Test
	public void testaddRecipe3(){
		coffeeMaker.addRecipe(recipe1);
		//coffeeMaker.addRecipe(recipe2);
		//coffeeMaker.addRecipe(recipe3);
		Recipe[] recepieees2 = coffeeMaker.getRecipes();
		//System.out.println(recepieees2[0]);
		//assertEquals(recepieees2, coffeeMaker.getRecipes());
		assertNotEquals(null,recepieees2[0]);

	}

	@Test
	public void testaddRecipe4(){
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		Recipe[] recepieees2 = coffeeMaker.getRecipes();
		assertNotEquals(null,recepieees2[1]);
	}

	@Test
	public void testaddRecipe5(){
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		Recipe[] recepieees2 = coffeeMaker.getRecipes();
		assertNotEquals(null,recepieees2[2]);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testaddRecipe55(){
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		coffeeMaker.addRecipe(recipe4);
		Recipe[] recepieees2 = coffeeMaker.getRecipes();
		assertEquals(null,recepieees2[3]);
	}

	@Test
	public void testdelete(){
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.deleteRecipe(0);
		Recipe[] recepieees2 = coffeeMaker.getRecipes();
		//System.out.println(recepieees2[2]);
		assertEquals(null,recepieees2[2]);
	}

	@Test
	public void testAddInventory22() throws InventoryException {
		coffeeMaker.addInventory("5","5","5","5");
		String a ="Coffee: 20\nMilk: 20\nSugar: 20\nChocolate: 20\n";
		assertEquals(a, coffeeMaker.checkInventory());
	}

	@Test
	public void testAddInventory23() throws InventoryException {
		coffeeMaker.addInventory("0","0","0","0");
		String a ="Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
		assertEquals(a, coffeeMaker.checkInventory());
	}

	@Test
	public void testAddInventory25() throws InventoryException {
		coffeeMaker.addInventory("10","10","10","10");
		String a ="Coffee: 25\nMilk: 25\nSugar: 25\nChocolate: 25\n";
		assertEquals(a, coffeeMaker.checkInventory());
	}

	@Test(expected = RecipeException.class)
	public void testBadRecipe() throws RecipeException {
		coffeeMaker.addRecipe(recipe1);
		recipe1.setAmtChocolate("-2");
	}

	@Test(expected = InventoryException.class)
	public void testTostring2() throws InventoryException {
		coffeeMaker.addInventory("o", "10", "10", "10");
		coffeeMaker.addRecipe(recipe2);
		assertNotEquals(null,coffeeMaker.checkInventory());
	}

	@Test(expected = InventoryException.class)
	public void testTostring3() throws InventoryException {
		coffeeMaker.addInventory("-1", "10", "10", "10");
	}
//for git
}
