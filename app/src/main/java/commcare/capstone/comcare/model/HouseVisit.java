package commcare.capstone.comcare.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import commcare.capstone.comcare.model.datacollection.DataCollectionForm;

/**
 * Created by yuan on 15/10/17.
 */
@DatabaseTable(tableName = "housevisit")
public class HouseVisit {

    Logger LOG = LoggerFactory.getLogger(HouseVisit.class);

    public static final String CANCEL = "CANCEL_PENDING";
    public static final String CANCELLED = "CANCELED";
    public static final String NEW = "NEW";
    public static final String COMPLETED = "COMPLETED";
    public static final String SUBMITTED = "SUBMITTED";
    public static final String REVERTED = "REVERTED";

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true) long id;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
    User assignedTo;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
    DataCollectionForm dataCollectionForm;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
    Resident resident;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
    User createdBy;
    @DatabaseField(columnName = "date_created", canBeNull = true)Date dateCreated;
    @DatabaseField(columnName = "status", canBeNull = true) String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }
    public DataCollectionForm getDataCollectionForm() {
        return dataCollectionForm;
    }
    public void setDataCollectionForm(DataCollectionForm dataCollectionForm) {
        this.dataCollectionForm = dataCollectionForm;
    }
    public Resident getResident() {
        return resident;
    }
    public void setResident(Resident resident) {
        this.resident = resident;
    }
    public User getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int compareToDate(HouseVisit hv)
    {
        return dateCreated.compareTo(hv.dateCreated);
    }


    public void importhv(commcare.capstone.comcare.webservice.HouseVisit vi) {
        setId(vi.id);
        setStatus(vi.status);

        setAssignedTo(new User(vi.assignedTo));
        setResident(new Resident(vi.resident));
        if (vi.dataCollectionForm != null)
        {
            setDataCollectionForm(new DataCollectionForm(vi.dataCollectionForm, getResident()));
        }
        setCreatedBy(new User(vi.createdBy));
        try
        {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(vi.dateCreated);
            setDateCreated(date);
        }
        catch(Exception e)
        {
            LOG.error("Date parse error for "+ vi.dateCreated);
        }


    }
}
