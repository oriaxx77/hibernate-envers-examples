/**
 * 
 */
package com.oriaxx77.hibernateplay.envers.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;

/**
 * Represents a StepParent of an animal.
 * 
 */
@Audited(withModifiedFlag=true)
@Entity
public class StepParent implements Serializable
{
	/**
	 * Serial version id for Serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identifier
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * Name of this StepParent.
	 */
	private String name;

	/**
	 * One-to-one with an animal.
	 * The animal is the owning side of the relationship because
	 * the animal defines the @JoinColumn
	 */
	@OneToOne(mappedBy="stepParent")
	private Animal animal;
	
	/**
	 * @return the animal
	 */
	public Animal getAnimal()
	{
		return animal;
	}

	/**
	 * @param animal the animal to set
	 */
	public void setAnimal(Animal animal)
	{
		this.animal = animal;
	}

	/**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "StepParent [id=" + id + ", name=" + name + "]";
    }
	

	
}
