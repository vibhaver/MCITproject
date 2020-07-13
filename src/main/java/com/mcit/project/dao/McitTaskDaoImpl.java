package com.mcit.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mcit.project.model.McitTask;
import com.mcit.project.util.HibernateUtil;

@Repository
public class McitTaskDaoImpl implements McitTaskDao {

	private static Session session = HibernateUtil.getSessionFactory().openSession();

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<McitTask> findAll() {
		Criteria query = session.createCriteria(McitTask.class);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<McitTask> findAllByAssignee(Integer assigneeId) {
		String hql = "SELECT T FROM McitTask T WHERE T.assignee.userId = :assigneeId";
		Query<McitTask> q = session.createQuery(hql).setParameter("assigneeId", assigneeId);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<McitTask> findAllByLeader(Integer leaderId) {
		String hql = "SELECT T FROM McitTask T WHERE T.project.projectId IN (SELECT P.projectId FROM McitProject P WHERE P.leader.userId = :leaderId)";
		Query<McitTask> q = session.createQuery(hql).setParameter("assigneeId", leaderId);
		return q.getResultList();
	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public McitTask findById(Integer taskId) {
		Criteria query = session.createCriteria(McitTask.class);
		query.add(Restrictions.eq("taskId", taskId));
		return (McitTask) query.uniqueResult();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<McitTask> findAllByProjectId(Integer projectId) {
		Criteria query = session.createCriteria(McitTask.class);
		query.add(Restrictions.eq("PROJECT_ID", projectId));
		return query.list();
	}

	@Override
	public void saveOrUpdateTask(McitTask task) {
		session.clear();
		session.beginTransaction();
		session.saveOrUpdate(task);
		session.flush();
		session.clear();
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int updateStatusByTaskId(String status, Integer taskId) {
		String hql = "UPDATE MCIT_TASK SET STATUS :status WHERE TASK_ID = :taskId";
		Query<McitTask> q = session.createQuery(hql).setParameter("taskId", taskId).setParameter("status", status);
		return q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int deleteTaskById(Integer taskId) {
		String hql = "DELETE FROM MCIT_TASK WHERE TASK_ID = :taskId";
		Query<McitTask> q = session.createQuery(hql).setParameter("taskId", taskId);
		return q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int deleteTaskByProjectId(Integer projectId) {
		String hql = "DELETE FROM MCIT_TASK WHERE PROJECT_ID = :projectId";
		Query<McitTask> q = session.createQuery(hql).setParameter("projectId", projectId);
		return q.executeUpdate();
	}
}
