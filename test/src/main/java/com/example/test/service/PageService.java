package com.example.test.service;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.test.page.PageBuider;

@Service
public class PageService {

	public PageRequest pageEmployee(Optional<Integer> size, 
	Optional<Integer> page) {
		
		PageBuider pageBuider = new PageBuider();
		if (size.isPresent()) pageBuider.setSize(size.get());
		if(page.isPresent()) pageBuider.setPage(page.get());
		return pageBuider.buid();
	}
}
