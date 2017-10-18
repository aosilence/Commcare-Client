package commcare.capstone.comcare.sqlhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.Serializable;
import java.sql.SQLException;

import commcare.capstone.comcare.model.GenogramObj;
import commcare.capstone.comcare.model.HouseVisit;
import commcare.capstone.comcare.model.LastLogin;
import commcare.capstone.comcare.model.Resident;
import commcare.capstone.comcare.model.User;
import commcare.capstone.comcare.model.datacollection.Assistances;
import commcare.capstone.comcare.model.datacollection.DataCollectionForm;
import commcare.capstone.comcare.model.datacollection.Issues;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 *
 * https://github.com/practika/wincor-TicketingSystem-android-client.git
 *
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper implements Serializable {

	private static final String DATABASE_NAME = "ComCaredb.db";
	private static final int DATABASE_VERSION = 1;

	private RuntimeExceptionDao<User, Integer> userRuntimeDAO = null;
	private RuntimeExceptionDao<LastLogin, Integer> lastLoginRuntimeDAO = null;
	private RuntimeExceptionDao<HouseVisit, Integer> houseVisitRuntimeDAO = null;
	private RuntimeExceptionDao<DataCollectionForm, Integer> dataCollectionFormRuntimeDAO = null;
	private RuntimeExceptionDao<Issues, Integer> issuesRuntimeDAO = null;
	private RuntimeExceptionDao<Assistances, Integer> assistancesRuntimeDAO = null;
	private RuntimeExceptionDao<Resident, Integer> residentRuntimeDAO = null;
	private RuntimeExceptionDao<GenogramObj, Integer> genogramObjRuntimeDAO = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTableIfNotExists(connectionSource, User.class);
			TableUtils.createTableIfNotExists(connectionSource, LastLogin.class);
			TableUtils.createTableIfNotExists(connectionSource, HouseVisit.class);
			TableUtils.createTableIfNotExists(connectionSource, DataCollectionForm.class);
			TableUtils.createTableIfNotExists(connectionSource, Issues.class);
			TableUtils.createTableIfNotExists(connectionSource, Assistances.class);
			TableUtils.createTableIfNotExists(connectionSource, Resident.class);
			TableUtils.createTableIfNotExists(connectionSource, GenogramObj.class);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, User.class, true);
			TableUtils.dropTable(connectionSource, LastLogin.class, true);
			TableUtils.dropTable(connectionSource, HouseVisit.class, true);
			TableUtils.dropTable(connectionSource, DataCollectionForm.class, true);
			TableUtils.dropTable(connectionSource, Issues.class, true);
			TableUtils.dropTable(connectionSource, Assistances.class, true);
			TableUtils.dropTable(connectionSource, Resident.class, true);
			TableUtils.dropTable(connectionSource, GenogramObj.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public RuntimeExceptionDao<GenogramObj, Integer> getGenogramObjRuntimeDAO() {
		if (genogramObjRuntimeDAO == null) {
			genogramObjRuntimeDAO = getRuntimeExceptionDao(GenogramObj.class);
		}
		return genogramObjRuntimeDAO;
	}
	public RuntimeExceptionDao<DataCollectionForm, Integer> getDataCollectionFormRuntimeDAO() {
		if (dataCollectionFormRuntimeDAO == null) {
			dataCollectionFormRuntimeDAO = getRuntimeExceptionDao(DataCollectionForm.class);
		}
		return dataCollectionFormRuntimeDAO;
	}
	public RuntimeExceptionDao<Issues, Integer> getIssuesRuntimeDAO() {
		if (issuesRuntimeDAO == null) {
			issuesRuntimeDAO = getRuntimeExceptionDao(Issues.class);
		}
		return issuesRuntimeDAO;
	}
	public RuntimeExceptionDao<Assistances, Integer> getAssistancesRuntimeDAO() {
		if (assistancesRuntimeDAO == null) {
			assistancesRuntimeDAO = getRuntimeExceptionDao(Assistances.class);
		}
		return assistancesRuntimeDAO;
	}
	public RuntimeExceptionDao<Resident, Integer> getResidentRuntimeDAO() {
		if (residentRuntimeDAO == null) {
			residentRuntimeDAO = getRuntimeExceptionDao(Resident.class);
		}
		return residentRuntimeDAO;
	}
	public RuntimeExceptionDao<HouseVisit, Integer> getHouseVisitRuntimeDAO() {
		if (houseVisitRuntimeDAO == null) {
			houseVisitRuntimeDAO = getRuntimeExceptionDao(HouseVisit.class);
		}
		return houseVisitRuntimeDAO;
	}
	public RuntimeExceptionDao<LastLogin, Integer> getLastLoginRuntimeDAO() {
		if (lastLoginRuntimeDAO == null) {
			lastLoginRuntimeDAO = getRuntimeExceptionDao(LastLogin.class);
		}
		return lastLoginRuntimeDAO;
	}
	public RuntimeExceptionDao<User, Integer> getUserRuntimeDAO() {
		if (userRuntimeDAO == null) {
			userRuntimeDAO = getRuntimeExceptionDao(User.class);
		}
		return userRuntimeDAO;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		userRuntimeDAO = null;
		lastLoginRuntimeDAO = null;
	}
}
