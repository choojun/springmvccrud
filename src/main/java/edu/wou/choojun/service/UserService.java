package edu.wou.choojun.service;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.wou.choojun.entity.User;

@Service
public class UserService
{
	public Page<User> findPaginated(int currentPage, int pageSize, List<User> books)
	{
		int startItem = currentPage * pageSize;
		
		List<User> list;
		int size = 0;
		if (books.size() < startItem)
		{
			list = Collections.emptyList();
		} else
		{
			int toIndex = Math.min(startItem + pageSize, books.size());
			list = books.subList(startItem, toIndex);
			size = books.size();
		}
		Page<User> bookPage = new PageImpl<User>(list, PageRequest.of(currentPage, pageSize), size);

		return bookPage;
	}
}
