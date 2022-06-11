package com.linkedinjms.chapter3.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookOrder {
	
	/*
	 * Jackson has to know in what order to pass the field
	 * that's why we're using the following approach
	 * */
	
	@JsonCreator
	public BookOrder(
			@JsonProperty("bookOrderId") String bookOrderId,
			@JsonProperty("book") Book book,
			@JsonProperty("customer") Customer customer) {
		this.bookOrderId = bookOrderId;
		this.book = book;
		this.customer = customer;
	}
	
	private final String bookOrderId;
	private final Book book;
	private final Customer customer;
	
	public String getBookOrderId() {
		return bookOrderId;
	}
	
	public Book getBook() {
		return book;
	}
	
	public Customer getCustomer() {
		return customer;
	}
}
