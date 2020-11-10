package com.project.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.Likes;

@Repository
@Transactional
public class LikesDao {

		private SessionFactory sessfact;
		
		public LikesDao() {}
		
		@Autowired
		public LikesDao(SessionFactory sessfact) {
			super();
			this.sessfact = sessfact;
		}
		
		public List<Likes> findAll() {
			return sessfact.openSession().createNativeQuery("select * from social.likes", Likes.class).list();
		}
		
		public int findCountByPostId(int post_id) {
			Query q = sessfact.openSession().createNativeQuery("select count(likes.user_id) from social.likes where post_id = ?1");
			q.setParameter(1, post_id);
			return (int) q.getSingleResult();
		}
		
		public Likes update(Likes t) {
			Session sess = sessfact.openSession();
			Transaction tx = sess.beginTransaction();
			sess.update(t);
			tx.commit();
			return t;
		}
		
		public Likes save(Likes t) {
			Session sess = sessfact.openSession();
			Transaction tx = sess.beginTransaction();
			sess.save(t);
			tx.commit();
			return t;
		}
		
		public Likes delete(Likes t) {
			Session sess = sessfact.openSession();
			Transaction tx = sess.beginTransaction();
			sess.delete(t);
			tx.commit();
			return t;
		}
}
