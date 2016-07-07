/**
 * 
 */
package com.oriaxx77.hibernateplay.envers.model;

import javax.persistence.Embeddable;

/**
 * Represents a feeding time / food description of an animal
 * 
 */
@Embeddable
public class FeedingEntry
{
	/**
	 * Specify the feeding hour in a day.
	 */
	private int hourOftheDay;
	/**
	 * Description  of  the food the animal gets at the specified time.
	 */
	private String food;

	/**
	 * @return the hourOftheDay
	 */
	public int getHourOftheDay()
	{
		return hourOftheDay;
	}

	/**
	 * @param hourOftheDay the hourOftheDay to set
	 */
	public void setHourOftheDay(int hourOftheDay)
	{
		this.hourOftheDay = hourOftheDay;
	}

	/**
	 * @return the food
	 */
	public String getFood()
	{
		return food;
	}

	/**
	 * @param food the food to set
	 */
	public void setFood(String food)
	{
		this.food = food;
	} 
	
	
	
}
