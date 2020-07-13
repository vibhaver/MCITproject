package com.mcit.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mcit.project.model.McitUser;
import com.mcit.project.util.HibernateUtil;
import com.mcit.project.util.UserRoleEnum;

@Repository
public class McitUserDaoImpl implements McitUserDao {

	private static Session session = HibernateUtil.getSessionFactory().openSession();

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<McitUser> findAll() {
		Criteria query = session.createCriteria(McitUser.class);
		return query.list();
	}

	@Override
	public void saveOrUpdateUser(McitUser user) {
		session.clear();
		session.beginTransaction();
		session.saveOrUpdate(user);
		session.flush();
		session.clear();
		session.getTransaction().commit();
	}

	@Override
	public List<McitUser> findAllMembersByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return null;

	}

	@SuppressWarnings("deprecation")
	@Override
	public McitUser findById(Integer userId) {
		Criteria query = session.createCriteria(McitUser.class);
		query.add(Restrictions.eq("userId", userId));
		return (McitUser) query.uniqueResult();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<McitUser> getLeaders() {
		String hql = "SELECT U FROM McitUser U WHERE U.userId IN (SELECT A.mcitUser.userId FROM McitAuthorities A WHERE A.authority = :authority)";
		Query<McitUser> q = session.createQuery(hql).setParameter("authority", UserRoleEnum.LEADER.toString());
		return q.getResultList();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<McitUser> getMembers() {
		String hql = "SELECT U FROM McitUser U WHERE U.userId IN (SELECT A.mcitUser.userId FROM McitAuthorities A WHERE A.authority = :authority)";
		Query<McitUser> q = session.createQuery(hql).setParameter("authority", UserRoleEnum.MEMBER.toString());
		return q.getResultList();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	  public McitUser findUserByUsername(String username) {
		Criteria query = session.createCriteria(McitUser.class);
		query.add(Restrictions.eq("username", username));
		return (McitUser) query.uniqueResult();
	  }

}
