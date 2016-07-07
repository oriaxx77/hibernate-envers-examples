/**
 * 
 */
package com.oriaxx77.hibernateplay.envers.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.envers.Audited;

/**
 * Zoo sponsor entity.
 * It has a many-to-many relationships with {@link Zoo}
 * 
 */
@Entity
@Audited
public class Sponsor implements Serializable
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
	 * Name of the sponsor.
	 */
	private String name;
	
	/**
	 * Many-to-many relationship.
	 * A sponsor can support more zoos and one zoo can have many supporters.
	 * This is the owning side.
	 */
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="sponsors")
	private Set<Zoo> zoos = new HashSet<>();

	/**
	 * Constructor
	 */
	public Sponsor()
	{
	    
	}
	
	/**
	 * Constructor
	 * @param name Name of this Sponsor
	 */
	public Sponsor( String name )
	{
	    this.name = name;
	}

	/**
	 * @return the zoos
	 */
	public Collection<Zoo> getZoos()
	{
		return zoos;
	}

	/**
	 * @param zoos the zoos to set
	 */
	public void setZoos(Set<Zoo> zoos)
	{
		this.zoos = zoos;
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

	/**
	 * String representation of this sponsor.
	 */
    @Override
    public String toString()
    {
        return "Sponsor [id=" + id + ", name=" + name + "]";
    }
	
	
}
