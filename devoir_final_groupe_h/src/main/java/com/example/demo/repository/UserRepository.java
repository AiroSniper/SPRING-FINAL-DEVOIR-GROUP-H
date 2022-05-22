package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Ticket;
import com.example.demo.model.User;

@Repository 
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);

	@Query("select t from Ticket t where t.developper=null and t.admin=null")
    List<Ticket> bugsNotAssigned(); 
	
	@Query("select t from Ticket t where t.admin.id=:id")
    List<Ticket> adminBugs(@Param("id") Long id); 
	
	@Query("select t from Ticket t where t.developper.id=:id")
    List<Ticket> devBugs(@Param("id") Long id); 
	
	@Query("select t from Ticket t where t.client.id=:id")
    List<Ticket> clientBugs(@Param("id") Long id); 
	
	
	@Query("select u from User u left join fetch u.roles r where r.nom='USER'")
    List<User> clients(); 
	
	@Query("select u from User u left join fetch u.roles r where r.nom='DEV'")
    List<User> devloppers(); 
	
	@Query("select u from User u left join fetch u.roles r where r.nom='ADMIN'")
    List<User> admins(); 
	
	@Query("select DISTINCT u from User u left join fetch u.roles r where r.nom='DEV' or r.nom='USER' or r=null")
    List<User> users(); 
	
	
	@Query("select u from User u left join fetch u.roles r where r.nom='USER' and u.id=:id")
	User client(@Param("id") Long id); 
	
	@Query("select u from User u left join fetch u.roles r where r.nom='DEV' and u.id=:id")
	User devlopper(@Param("id") Long id); 
	
	@Query("select u from User u left join fetch u.roles r where r.nom='ADMIN' and u.id=:id")
	User admin(@Param("id") Long id); 
	
	@Query("select u from User u where u.id=:id")
	User user(@Param("id") Long id); 
}
