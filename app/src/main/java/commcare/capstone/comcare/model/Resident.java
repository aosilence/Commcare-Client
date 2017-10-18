package commcare.capstone.comcare.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import commcare.capstone.comcare.model.datacollection.DataCollectionForm;

@DatabaseTable(tableName = "resident")
public class Resident {

	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true) long id;
	@DatabaseField(columnName = "fullName", canBeNull = true) String fullName;
	@DatabaseField(columnName = "nric", canBeNull = true) String nric;
	@DatabaseField(columnName = "sex", canBeNull = true) String sex;
	@DatabaseField(columnName = "dob", canBeNull = true) String dob;
	@DatabaseField(columnName = "age", canBeNull = true) String age;
	@DatabaseField(columnName = "ctcNumber", canBeNull = true) String ctcNumber;
	@DatabaseField(columnName = "Address", canBeNull = true) String Address;
	@DatabaseField(columnName = "Nationality", canBeNull = true) String Nationality;
	@DatabaseField(columnName = "maritalStatus", canBeNull = true) String maritalStatus;
	@DatabaseField(columnName = "race", canBeNull = true) String race;
	@DatabaseField(columnName = "typeOfFlat", canBeNull = true) String typeOfFlat;
	@DatabaseField(columnName = "spokenLanguage", canBeNull = true) String spokenLanguage;
	@DatabaseField(columnName = "religion", canBeNull = true) String religion;
	@DatabaseField(columnName = "occupation", canBeNull = true) String occupation;
	@DatabaseField(columnName = "salary", canBeNull = true) String salary;
	@DatabaseField(columnName = "householdIncome", canBeNull = true) String householdIncome;
	@DatabaseField(columnName = "householdMember", canBeNull = true) String householdMember;
	@DatabaseField(columnName = "highestEducation", canBeNull = true) String highestEducation;
	@DatabaseField(columnName = "vehOwner", canBeNull = true) String vehOwner;
	@DatabaseField(columnName = "illnesses", canBeNull = true) String illnesses;

	public Resident()
	{

	}
	public Resident(commcare.capstone.comcare.webservice.Resident r) {
		setId(r.id);
		setFullName(r.fullName);
		setNric(r.nric);
		setSex(r.sex);
		setDob(r.dob);
		setAge(r.age);
		setCtcNumber(r.ctcNumber);
		setAddress(r.address);
		setNationality(r.nationality);
		setMaritalStatus(r.maritalStatus);
		setRace(r.race);
		setTypeOfFlat(r.typeOfFlat);
		setSpokenLanguage(r.spokenLanguage);
		setReligion(r.religion);
		setOccupation(r.occupation);
		setHouseholdIncome(r.householdIncome);
		setHouseholdMember(r.householdMember);
		setHighestEducation(r.highestEducation);
		setVehOwner(r.vehOwner);
		//setSalary
		//setIllnesses
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getNric() {
		return nric;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCtcNumber() {
		return ctcNumber;
	}
	public void setCtcNumber(String ctcNumber) {
		this.ctcNumber = ctcNumber;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getNationality() {
		return Nationality;
	}
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getTypeOfFlat() {
		return typeOfFlat;
	}
	public void setTypeOfFlat(String typeOfFlat) {
		this.typeOfFlat = typeOfFlat;
	}
	public String getSpokenLanguage() {
		return spokenLanguage;
	}
	public void setSpokenLanguage(String spokenLanguage) {
		this.spokenLanguage = spokenLanguage;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getHouseholdIncome() {
		return householdIncome;
	}
	public void setHouseholdIncome(String householdIncome) {
		this.householdIncome = householdIncome;
	}
	public String getHouseholdMember() {
		return householdMember;
	}
	public void setHouseholdMember(String householdMember) {
		this.householdMember = householdMember;
	}
	public String getHighestEducation() {
		return highestEducation;
	}
	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}
	public String getVehOwner() {
		return vehOwner;
	}
	public void setVehOwner(String vehOwner) {
		this.vehOwner = vehOwner;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getIllnesses() {
		return illnesses;
	}

	public void setIllnesses(String illnesses) {
		this.illnesses = illnesses;
	}
}
