package edu.wou.choojun.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Size(min = 3, max = 10)
	private String name;

	@Email
	private String email;

	public User()
	{
	}

	public User(String name, String email)
	{
		this.name = name;
		this.email = email;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getId()
	{
		return id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public String getEmail()
	{
		return email;
	}

	@Override
	public String toString()
	{
		return "User{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
	}
}
