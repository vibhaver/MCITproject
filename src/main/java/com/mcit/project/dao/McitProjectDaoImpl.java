package com.mcit.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mcit.project.model.McitProject;
import com.mcit.project.util.HibernateUtil;

@Repository
public class McitProjectDaoImpl implements McitProjectDao {

	private static Session session = HibernateUtil.getSessionFactory().openSession();

//	@SuppressWarnings("unchecked")
//	@Override
//	public int deleteProjectById(Integer projectId) {
//		String hql = "DELETE FROM MCIT_PROJECT WHERE PROJECT_ID = :projectId";
//		Query<McitProject> q = session.createQuery(hql).setParameter("projectId", projectId);
//		return q.executeUpdate();
//	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<McitProject> findAllByCreatorId(Integer creatorId) {
		Criteria query = session.createCriteria(McitProject.class);
		query.add(Restrictions.eq("creatorId", creatorId));
		return query.list();
	}

	@Override
	public void saveOrUpdateProject(McitProject project) {
		session.clear();
		session.beginTransaction();
		session.saveOrUpdate(project);
		session.flush();
		session.getTransaction().commit();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<McitProject> findAll() {
		Criteria query = session.createCriteria(McitProject.class);
		return query.list();
	}

	@SuppressWarnings("deprecation")
	@Override
	public McitProject findById(Integer projectId) {
		Criteria query = session.createCriteria(McitProject.class);
		query.add(Restrictions.eq("projectId", projectId));
		return (McitProject) query.uniqueResult();
	}

	@Override
	public void deleteProject(McitProject project) {
		session.clear();
		session.beginTransaction();
		session.delete(project);
		session.flush();
		session.getTransaction().commit();
	}
}
