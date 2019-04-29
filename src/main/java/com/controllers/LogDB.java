package com.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.util.HibernateUtility;

@Component
public class LogDB {

	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

	public static void insertLogDB(String module_Log, String operation_Log, HttpServletRequest request) {
		try {
			Session session = sessionFactory.openSession();
			session.getTransaction().begin();
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			java.util.Date date = new Date();
			String date_Log = formater.format(date);
			String user_Log = SecurityContextHolder.getContext().getAuthentication().getName();
			operation_Log = operation_Log.replace("'", "|");
			String hsql1 = "Insert into LogDB (id_Log, date_Log, user_Log, ip_Log, module_Log, operation_Log) values "
					+ "(LogDB_SEQ.NextVal,'" + date_Log + "','" + user_Log + "','" + getClientIp(request) + "','"
					+ module_Log + "','" + operation_Log + "')";
			System.out.println(hsql1);
			session.createSQLQuery(hsql1).executeUpdate();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String getClientIp(HttpServletRequest request) {

		String remoteAddr = "";

		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		return remoteAddr;
	}

}
