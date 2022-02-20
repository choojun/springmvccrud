package edu.wou.choojun.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.wou.choojun.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
	List<User> findByName(String name);
}
