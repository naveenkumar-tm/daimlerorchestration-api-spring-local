/**
 * This package contain  class as Service is used to call the Generic Services
 */
package org.orchestration.genericService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.orchestration.genericDao.OrchestrationGenericDao;
/**
 * To Import Classes to access their functionality
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * This class use as Service to call all the Generic Dao Method in all possible
 * cases to interact with Databases for their different CRUD and other
 * Functionality
 * 
 * @author Ankita Shrothi
 *
 */
@Service
@Transactional(readOnly = false)
@SuppressWarnings("rawtypes")
public class OrchestrationGenericService {

	/*
	 * Autowired is used to inject the object dependency implicitly.It's a
	 * specific functionality of spring which requires less code.
	 */
	@Autowired
	private OrchestrationGenericDao genericDao;
	Logger logger = Logger.getLogger(OrchestrationGenericService.class);
	/*
	 * This method is required to save the data in the object format.
	 */
	public Object save(Object object) throws Exception {
		return genericDao.save(object);
	}

	/*
	 * This method is used to find specific data on the basis of id.
	 */
	public Object findByID(Class clz, Object object) throws Exception {
		return genericDao.findByID(clz, object);
	}

	/*
	 * This method is used to find specific data on the column basis.
	 */
	public Object findByColumn(Class clz, String key, Object object) throws Exception {
		return genericDao.findByColumn(clz, key, object);
	}

	/*
	 * This method is used to find all data
	 */
	public Object findAll(Class clz) throws Exception {
		return genericDao.findAll(clz);
	}

	/*
	 * This method id required to update the data.
	 */
	public Object update(Object object) throws Exception {
		return genericDao.update(object);
	}

	/*
	 * This method is required to find data by column.
	 */
	public Object findByColumnUnique(Class clz, String key, Object value) throws Exception {
		return genericDao.findByColumnUnique(clz, key, value);
	}

	/*
	 * This method is required to execute sql query like select,create or update
	 * queries of Sql.
	 */
	public Object executeSqlQuery(String sql) throws Exception {
		return genericDao.executeSqlQuery(sql);
	}

	/*
	 * This method is required to execute Sql Queries like create queries.
	 */
	public Object executeAnySqlQuery(String sql) throws Exception {
		return genericDao.executeAnySqlQuery(sql);
	}

	/*
	 * This method is required to execute Sql queries.
	 */
	
	@SuppressWarnings("deprecation")
	public Object executeOrchestrationProcesure(Class clz, String sql, Object... objects) throws Exception {
		try {
			if (sql.contains("()")) {

				// return the response retrieved from procedure

				return genericDao.executeProcesure(clz, sql, objects);
			}
			if (sql.contains("?")) {

				// return the response retrieved from procedure

				return genericDao.executeProcesure(clz, sql, objects);
			} else {

				// Initializing the string builder
				StringBuilder builder = new StringBuilder();

				// Break the sql in requiref format
				String newSql = sql.substring(0, sql.indexOf("(") + 1);
				builder.append(newSql);

				// Calculate the number of commas in procedure
				int count = StringUtils.countMatches(sql, ",");

				// Append the '?' in sql helps to call procedure
				for (int i = 0; i < count + 1; i++) {
					String comma = "?,";

					// Append in builder
					builder.append(comma);
				}

				// Append the data in builder as per requirement
				builder.deleteCharAt(builder.lastIndexOf(","));
				builder.append(")");

				// return the response retrieved from procedure
				return genericDao.executeProcesure(clz, builder.toString(), objects);
			}
		} catch (Exception e) {
			logger.setPriority(Priority.ERROR);
			logger.error(e);
			e.printStackTrace();
			return null;
		}

	}

}
