package com.oriaxx77.hibernateplay.envers.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.envers.Audited;


/**
 * An animal in the zoo. It has some named queries that can be cached.
 * It has a many-to-one reference to the owner {@link Zoo}. It has a one-to-one reference to the step parent of the 
 * animal.
 * It has a collection of possible feeding entrites.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name="animalByName",
        query="SELECT a FROM Animal a WHERE a.name = ?1"),
    @NamedQuery(name="animalByZooName",
        query="SELECT a FROM Animal a WHERE a.zoo.name = :zooName",
        hints={@QueryHint(name="org.hibernate.cacheable",value="true")})
})
@Cacheable(value=true)
@Table(name="Animal",
	   uniqueConstraints={@UniqueConstraint(columnNames={"name","zoo_id"})},
	   indexes={@Index(name="idx_animalbyZoo_id",columnList="zoo_id"), @Index(name="idx_animalbyNname",columnList="name")})
@Audited(withModifiedFlag=true)
public class Animal  implements Serializable
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
	 * Name of the animal.
	 */
	private String name;
	
	/**
	 * Many-to-one (Many animal belongs to one zoo)
	 */
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="zoo_id") // JoinColumn is optional. The join column determines the owning side of the relationship.
	private Zoo zoo;

	/**
	 * Creates an animal
	 */
	public Animal()
	{
		
	}
	
	/**
	 * Creates the animal with it's given name.
	 * @param name Name of the animal.
	 */
	public Animal( String name )
	{
		this.name = name;
	}
	
	/**
	 * One-to-one relation to the stepparent.
	 */
	@OneToOne
	@JoinColumn(name="parent_id") // JoinColumn is optional. The join column determines the owning side of the relationship.
	private StepParent stepParent;
	
	/**
	 * Feeding entries of the animal.
	 */
	@ElementCollection
	private Collection<FeedingEntry> feedingEntries;
	
	/**
	 * Nicknames of the animal.
	 */
	@ElementCollection
	private Set<String> nickNames;
	
	/**
	 * @return the stepParent
	 */
	public StepParent getStepParent()
	{
		return stepParent;
	}

	/**
	 * @param stepParent the stepParent to set
	 */
	public void setStepParent(StepParent stepParent)
	{
		this.stepParent = stepParent;
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
	 * @return the zoo
	 */
	public Zoo getZoo() 
	{
		return zoo;
	}

	/**
	 * @param zoo the zoo to set
	 */
	public void setZoo(Zoo zoo) 
	{
		this.zoo = zoo;
	}

	/**
	 * @return the feedingEntries
	 */
	public Collection<FeedingEntry> getFeedingEntries()
	{
		return feedingEntries;
	}

	/**
	 * @param feedingEntries the feedingEntries to set
	 */
	public void setFeedingEntries(Collection<FeedingEntry> feedingEntries)
	{
		this.feedingEntries = feedingEntries;
	}

	/**
	 * @return the nickNames
	 */
	public Set<String> getNickNames()
	{
		return nickNames;
	}

	/**
	 * @param nickNames the nickNames to set
	 */
	public void setNickNames(Set<String> nickNames)
	{
		this.nickNames = nickNames;
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

    @Override
    public String toString()
    {
        return "Animal [id=" + id + ", name=" + name + "]";
    }
	
	
	
}
