package commcare.capstone.comcare.model.datacollection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "assistances")
public class Assistances {

	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true) long id;
	@DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
	DataCollectionForm parent;
	@DatabaseField(columnName = "wheels", canBeNull = true) boolean wheels;
	@DatabaseField(columnName = "food", canBeNull = true) boolean food;
	@DatabaseField(columnName = "grant", canBeNull = true) boolean grant;
	@DatabaseField(columnName = "tingkat", canBeNull = true) boolean tingkat;
	@DatabaseField(columnName = "utility", canBeNull = true) boolean utility;
	@DatabaseField(columnName = "longTerm", canBeNull = true) boolean longTerm;
	@DatabaseField(columnName = "homeFix", canBeNull = true) boolean homeFix;
	@DatabaseField(columnName = "mobility", canBeNull = true) boolean mobility;
	@DatabaseField(columnName = "shortTerm", canBeNull = true) boolean shortTerm;
	@DatabaseField(columnName = "befriend", canBeNull = true) boolean befriend;
	@DatabaseField(columnName = "otherAssistance", canBeNull = true) String otherAssistance;

	public Assistances(){

	}
	public Assistances(commcare.capstone.comcare.webservice.Assistances a, DataCollectionForm d) {
		setId(a.id);
		setParent(d);
		setWheels(a.wheels);
		setFood(a.food);
		setGrant(a.grant);
		setTingkat(a.tingkat);
		setUtility(a.utility);
		setLongTerm(a.longTerm);
		setHomeFix(a.homeFix);
		setMobility(a.mobility);
		setShortTerm(a.shortTerm);
		setBefriend(a.befriend);
		setOtherAssistance(a.otherAssistance);
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public DataCollectionForm getParent() {
		return parent;
	}
	public void setParent(DataCollectionForm parent) {
		this.parent = parent;
	}
	public boolean isWheels() {
		return wheels;
	}
	public void setWheels(boolean wheels) {
		this.wheels = wheels;
	}
	public boolean isFood() {
		return food;
	}
	public void setFood(boolean food) {
		this.food = food;
	}
	public boolean isGrant() {
		return grant;
	}
	public void setGrant(boolean grant) {
		this.grant = grant;
	}
	public boolean isTingkat() {
		return tingkat;
	}
	public void setTingkat(boolean tingkat) {
		this.tingkat = tingkat;
	}
	public boolean isUtility() {
		return utility;
	}
	public void setUtility(boolean utility) {
		this.utility = utility;
	}
	public boolean isLongTerm() {
		return longTerm;
	}
	public void setLongTerm(boolean longTerm) {
		this.longTerm = longTerm;
	}
	public boolean isHomeFix() {
		return homeFix;
	}
	public void setHomeFix(boolean homeFix) {
		this.homeFix = homeFix;
	}
	public boolean isMobility() {
		return mobility;
	}
	public void setMobility(boolean mobility) {
		this.mobility = mobility;
	}
	public boolean isShortTerm() {
		return shortTerm;
	}
	public void setShortTerm(boolean shortTerm) {
		this.shortTerm = shortTerm;
	}
	public boolean isBefriend() {
		return befriend;
	}
	public void setBefriend(boolean befriend) {
		this.befriend = befriend;
	}
	public String getOtherAssistance() {
		return otherAssistance;
	}
	public void setOtherAssistance(String otherAssistance) {
		this.otherAssistance = otherAssistance;
	}
	
	
}
