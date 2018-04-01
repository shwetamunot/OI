package com.tcs.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tcs.model.Users;

@Repository
@Transactional
public class UserDaoImpl implements UserDAO{

	@Autowired
private SessionFactory sessionfactory;
	public void register(Users user) {
		
		Session session=sessionfactory.getCurrentSession();
		System.out.println("savess");
		session.save(user);
	}

	public boolean isEmailValid(String email) {
		System.out.println("emailvalid"+email);
	    Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from Users where email='"+ email +"'");
	    Users user=(Users)query.uniqueResult();
		if(user==null)	
			return true;
		else
		   return false;
	}

	public boolean isUsernameValid(String username) {
		Session session=sessionfactory.getCurrentSession();
		Users user=(Users)session.get(Users.class,username);

		if(user!=null)
		  {
			return false;
		  }
		else
		 {
			return true;
		 }
	}

	public Users login(Users user) {
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from Users where username=? and password=?");
	query.setString(0,user.getUsername());
		query.setString(1,user.getPassword());
		Users validuser=(Users)query.uniqueResult();

		return validuser;
		
	}

	public void updateUser(Users user) {
		Session session=sessionfactory.getCurrentSession();
		session.update(user);
			
	}

	public Users getUserByUsername(String username) {
		Session session=sessionfactory.getCurrentSession();
		Users user=(Users)session.get(Users.class,username);
		return user;
	}
}
