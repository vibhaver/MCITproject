package com.mcit.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<McitUser> getLeaders() {
		Criteria query = session.createCriteria(McitUser.class);
		query.add(Restrictions.eq("userRole", UserRoleEnum.LEADER.toString()));
		return query.list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<McitUser> getMembers() {
		Criteria query = session.createCriteria(McitUser.class);
		query.add(Restrictions.eq("userRole", UserRoleEnum.MEMBER.toString()));
		return query.list();
	}

}
