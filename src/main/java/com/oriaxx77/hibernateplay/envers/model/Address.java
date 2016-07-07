/**
 * 
 */
package com.oriaxx77.hibernateplay.envers.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.envers.Audited;

/**
 * An embeddable address.
 * This will be part of the parent record in the DB.
 * It is used in the {@link Zoo}.
 * 
 */
@Audited(withModifiedFlag=true)
@Embeddable
public class Address implements Serializable
{
	/**
	 * Serial version UID. 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The city column
	 */
	@Column(name="CITY")
	private String city;
	
	/**
	 * Constructor
	 * @param city City of this address
	 */
	public Address(String city)
    {
        this.city = city;
    }
	
	/**
	 * Constructor.
	 */
	public Address()
	{
	    
	}

    /**
	 * @return the city
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * String representation of this Address.
	 */
    @Override
    public String toString()
    {
        return "Address [city=" + city + "]";
    }
	
	
	
}
