package com.example.test.page;
import org.springframework.data.domain.PageRequest;

public class PageBuider {
	
	int size = 10;
	int page = 0;
	
	public PageBuider setSize(int size) {
		this.size = size;
		return this;
	}
	
	public PageBuider setPage(int page) {
		this.page = page;
		return this;
	}
	
	public PageRequest buid() {
		return PageRequest.of(page, size);
	}
	
}
