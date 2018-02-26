package model;

import java.beans.ConstructorProperties;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity

public class Customer {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String firstName;
	@Column
	private String surName;
	@Column
	private String phoneNumber;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="customerId")
	private Set<PhoneNumber> phoneNumbers;

	private String address;
	
	/* @OneToMany says: 'one' person can have 'many' email addresses.
	 * When you store the EmailAddress objects in a Set, hibernate will 
	 * create a table called EmailAddress and insert an entry in that
	 * table for each email address.
	 *  
	 * 
	 * @JoinColumn creates an extra column in the EmailAddress table
	 * called 'customerId' and inserts the primary key from this table
	 * into that new column. E.g. Joe Bloggs' id is 1 and Joe has two
	 * email addresses, the email addresses will both have a personId
	 * of 1. personId is the foreign key. 
	 * 
	 * cascade=CascadeType.ALL
	 * If I delete a Person from the 'one' table, it will cascade into
	 * the EmailAddress table, i.e. all email address entries for that
	 * person will be deleted too.
	 * 
	 * A Set is a list that won't store duplicates. A Set is an interface,
	 * to create a Set, you should instantiate the HashSet class (which
	 * implements the Set interface).
	 * 
	 * Person
	 * id	firstname surname phoneNumber   
	 * 1    Joe       Bloggs  0862134567
	 * 
	 * EmailAddress
	 * id	email 				  personId
	 * 100  joe@bloggs.ie		  1
	 * 101  joebloggs@gmail.com   1
	 * 102	jb@me.com			  1
	 * 
	 * */
	
	
	
	/*@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="customerId")
	private Set<EmailAddress> emails;*/
	
	@Column
	private String description;
	@Column 
	private String recommendedBy;
	@Column
	private String year;
	@Column
	private String startdate;
	@Column
	private String finishdate;
	
	public Customer() {}
	
	
	
}

	
	
	
	