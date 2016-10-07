package com.ikon.alexx.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Mester {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;

	private String firstName;
	private String lastName;
	private String description;
	private Integer avgPrice;
	private Integer avgRating;

	@OneToOne
	private User user;

	@OneToOne(mappedBy = "mester", cascade = CascadeType.ALL)
	private Contact contact;

	@OneToOne(mappedBy = "mester", cascade = CascadeType.ALL)
	private Location location;

	@OneToMany(mappedBy = "mester", cascade = CascadeType.ALL)
	private List<Review> reviews;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Speciality> specialities;

	public Mester() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Integer avgPrice) {
		this.avgPrice = avgPrice;
	}

	public Integer getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(Integer avgRating) {
		this.avgRating = avgRating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Set<Speciality> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(Set<Speciality> specialities) {
		this.specialities = specialities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mester other = (Mester) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mester [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", description="
				+ description + ", avgPrice=" + avgPrice + ", avgRating=" + avgRating + ", user=" + user + ", contact="
				+ contact + ", location=" + location + ", reviews=" + reviews + ", specialities=" + specialities + "]";
	}
}
