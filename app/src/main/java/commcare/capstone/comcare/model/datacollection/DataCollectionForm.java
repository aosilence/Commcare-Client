package commcare.capstone.comcare.model.datacollection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import commcare.capstone.comcare.biz.DataBiz;
import commcare.capstone.comcare.model.Resident;
import commcare.capstone.comcare.model.User;

@DatabaseTable(tableName = "datacollection")
public class DataCollectionForm {

	Logger LOG = LoggerFactory.getLogger(DataCollectionForm.class);

	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true) long id;
	@DatabaseField(columnName = "date", canBeNull = true) Date date;
	@DatabaseField(columnName = "duration", canBeNull = true) String duration;
	@DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 1)
	User doneBy;
	@DatabaseField(columnName = "grlstaffname", canBeNull = true) String GRLStaffName;
	@DatabaseField(columnName = "grlstaffcontact", canBeNull = true) String GRLStaffContact;
	@DatabaseField(columnName = "endorsedby", canBeNull = true) String endorsedBy;
	@DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
	Resident resident;
	@DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
	Issues issues;
	@DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
	Assistances assistances;
	ArrayList<GenogramObj> genos;
	String genoPath;

	public DataCollectionForm()
	{

	}
	public DataCollectionForm(commcare.capstone.comcare.webservice.DataCollectionForm d, Resident r) {
		setId(d.id);
		try
		{
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(d.dateCreated);
			setDate(date);
		}
		catch(Exception e)
		{
			LOG.error("Date parse error for "+ d.dateCreated);
		}
		setDuration(d.duration);
		if(d.doneBy != null)
		{
			setDoneBy(new User(d.doneBy));
		}
		setGRLStaffName(d.gRLStaffName);
		setGRLStaffContact(d.gRLStaffContact);
		setEndorsedBy(d.endorsedBy);
		setResident(r);
		if(d.issues != null)
		{
			setIssues(new Issues(d.issues,this));
		}
		if(d.doneBy != null)
		{
			setAssistances(new Assistances(d.assistances,this));
		}


	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public User getDoneBy() {
		return doneBy;
	}
	public void setDoneBy(User doneBy) {
		this.doneBy = doneBy;
	}
	public String getGRLStaffName() {
		return GRLStaffName;
	}
	public void setGRLStaffName(String gRLStaffName) {
		GRLStaffName = gRLStaffName;
	}
	public String getGRLStaffContact() {
		return GRLStaffContact;
	}
	public void setGRLStaffContact(String gRLStaffContact) {
		GRLStaffContact = gRLStaffContact;
	}
	public String getEndorsedBy() {
		return endorsedBy;
	}
	public void setEndorsedBy(String endorsedBy) {
		this.endorsedBy = endorsedBy;
	}
	public Resident getResident() {
		return resident;
	}
	public void setResident(Resident resident) {
		this.resident = resident;
	}
	public Issues getIssues() {
		return issues;
	}
	public void setIssues(Issues issues) {
		this.issues = issues;
		issues.setParent(this);
	}
	public Assistances getAssistances() {
		return assistances;
	}
	public void setAssistances(Assistances assistances) {
		this.assistances = assistances;
		assistances.setParent(this);
	}

	public ArrayList<GenogramObj> getGenos() {
		genos = new ArrayList<GenogramObj>(DataBiz.getInstance().getDb().getGenogramObjRuntimeDAO().queryForEq("parent_id",this.getId()));
		return genos;
	}

	public String getGenoPath() {
		return genoPath;
	}

	public void setGenoPath(String genoPath) {
		this.genoPath = genoPath;
	}

	public void setGenos(ArrayList<GenogramObj> genos) {
		this.genos = genos;
	}
}
