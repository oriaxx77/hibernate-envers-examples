/**
 * 
 */
package com.oriaxx77.hibernateplay.envers.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;


/**
 * Zoo entity.
 * It can have many animals, sponsors.
 * 
 */
@Entity
@Audited(withModifiedFlag=true)
public class Zoo  implements Serializable
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
	 * Name of  the zoo.
	 */
	private String name;
	
	/**
	 * One-to-many relationship.
	 * One zoo can belong to many animals.
	 * The Animal is the owning side because it 
	 * defines the @JoinColumn annotation.
	 */
	@OneToMany(mappedBy="zoo",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<Animal> animals = new HashSet<>();
	
	/**
	 * Many-to-many relationship.
	 * A sponsor can support more zoos and one zoo can have many supporters.
	 * This is the inverse side. The sponsor has the owning side.
	 */
	@ManyToMany(fetch=FetchType.LAZY , cascade=CascadeType.ALL)
	@JoinTable(name="zoo_sponsor") // This is the owning side
	// @NotAudited
	private Set<Sponsor> sponsors = new HashSet<>();

	/**
	 * An embedded address.
	 */
	@Embedded
	private Address address;
	
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
	 * @return the animals
	 */
	public Collection<Animal> getAnimals()
	{
		return animals;
	}

	/**
	 * @param animals the animals to set
	 */
	public void setAnimals(Set<Animal> animals)
	{
		this.animals = animals;
	}

	/**
	 * @return the sponsors
	 */
	public Collection<Sponsor> getSponsors()
	{
		return sponsors;
	}

	/**
	 * @param sponsors the sponsors to set
	 */
	public void setSponsors(Set<Sponsor> sponsors)
	{
		this.sponsors = sponsors;
	}

	

	/**
	 * @return the address
	 */
	public Address getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address)
	{
		this.address = address;
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

	/**
	 * Adds an animal to the zoo.
	 * @param animal The new animal of this zoo.
	 */
	public void addAnimal( Animal animal )
	{
		animal.setZoo( this );
		animals.add( animal );
	}

    @Override
    public String toString()
    {
        return "Zoo [id=" + id + ", name=" + name + ", animals=" + animals + ", address=" + address
                + "]";
    }

	/**
	 * Adds a sponsort to this zoo
	 * @param sponsor Sponsor to add.
	 */
	public void addSponsor( Sponsor sponsor )
	{
	    if ( sponsor != null && !sponsors.contains( sponsor ) )
	    {
	        sponsors.add( sponsor );
	        sponsor.getZoos().add( this );
	    }
	}
	
	
}
