package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="Users")
public class User extends BaseEntity {

	@Column(name="first_name", nullable=false, length=255)
	private String firstName;
	
	@Column(name="last_name", nullable=false, length=255)
	private String lastName;
	
	@Column(name="email", nullable=false, unique=true)
	@Email(message="wrong email format")
	private String email; 
	
	@Column(name="passwrod", nullable=false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role", nullable=false)
	private Role role = Role.CUSTOMER;

	public User() {
	
	}

	public User(String firstName, String lastName, String email, String password, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
