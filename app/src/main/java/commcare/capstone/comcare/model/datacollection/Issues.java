package commcare.capstone.comcare.model.datacollection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "issues")
public class Issues {


	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true) long id;
	@DatabaseField(columnName = "financial", canBeNull = true) boolean financial;
	@DatabaseField(columnName = "gambling", canBeNull = true) boolean gambling;
	@DatabaseField(columnName = "parenting", canBeNull = true) boolean parenting;
	@DatabaseField(columnName = "caregiving", canBeNull = true) boolean caregiving;
	@DatabaseField(columnName = "health", canBeNull = true) boolean health;
	@DatabaseField(columnName = "partner", canBeNull = true) boolean partner;
	@DatabaseField(columnName = "childcare", canBeNull = true) boolean childcare;
	@DatabaseField(columnName = "housing", canBeNull = true) boolean housing;
	@DatabaseField(columnName = "retrenchment", canBeNull = true) boolean retrenchment;
	@DatabaseField(columnName = "elderly", canBeNull = true) boolean elderly;
	@DatabaseField(columnName = "interpersonal", canBeNull = true) boolean interpersonal;
	@DatabaseField(columnName = "substance", canBeNull = true) boolean substance;
	@DatabaseField(columnName = "employment", canBeNull = true) boolean employment;
	@DatabaseField(columnName = "juvenile", canBeNull = true) boolean juvenile;
	@DatabaseField(columnName = "youth", canBeNull = true) boolean youth;
	@DatabaseField(columnName = "conflict", canBeNull = true) boolean conflict;
	@DatabaseField(columnName = "mental", canBeNull = true) boolean mental;
	@DatabaseField(columnName = "others", canBeNull = true) boolean others;
	@DatabaseField(columnName = "violence", canBeNull = true) boolean violence;
	@DatabaseField(columnName = "school", canBeNull = true) boolean school;

	@DatabaseField(columnName = "senior", canBeNull = true) boolean senior;
	@DatabaseField(columnName = "seniorMore", canBeNull = true) String seniorMore;
	@DatabaseField(columnName = "adult", canBeNull = true) boolean adult;
	@DatabaseField(columnName = "adultMore", canBeNull = true) String adultMore;
	@DatabaseField(columnName = "visual", canBeNull = true) boolean visual;
	@DatabaseField(columnName = "visualMore", canBeNull = true) String visualMore;
	@DatabaseField(columnName = "hearing", canBeNull = true) boolean hearing;
	@DatabaseField(columnName = "hearingMore", canBeNull = true) String hearingMore;
	@DatabaseField(columnName = "three", canBeNull = true) boolean three;
	@DatabaseField(columnName = "threeMore", canBeNull = true) String threeMore;
	@DatabaseField(columnName = "medical", canBeNull = true) boolean medical;
	@DatabaseField(columnName = "medicalMore", canBeNull = true) String medicalMore;
	@DatabaseField(columnName = "others2", canBeNull = true) boolean others2;
	@DatabaseField(columnName = "others2More", canBeNull = true) String others2More;

	@DatabaseField(columnName = "unemployement", canBeNull = true) boolean unemployement;
	@DatabaseField(columnName = "unemployementMore", canBeNull = true) String unemployementMore;
	@DatabaseField(columnName = "unfit", canBeNull = true) boolean unfit;
	@DatabaseField(columnName = "unfitMore", canBeNull = true) String unfitMore;
	@DatabaseField(columnName = "familyIllness", canBeNull = true) boolean familyIllness;
	@DatabaseField(columnName = "familyIllnessMore", canBeNull = true) String familyIllnessMore;
	@DatabaseField(columnName = "largeWithElder", canBeNull = true) boolean largeWithElder;
	@DatabaseField(columnName = "largeWithElderMore", canBeNull = true) String largeWithElderMore;
	@DatabaseField(columnName = "largeWithChild", canBeNull = true) boolean largeWithChild;
	@DatabaseField(columnName = "largeWithChildMore", canBeNull = true) String largeWithChildMore;
	@DatabaseField(columnName = "sso", canBeNull = true) boolean sso;
	@DatabaseField(columnName = "ssoMore", canBeNull = true) String ssoMore;
	@DatabaseField(columnName = "agency", canBeNull = true) boolean agency;
	@DatabaseField(columnName = "agencyMore", canBeNull = true) String agencyMore;
	@DatabaseField(columnName = "schoolFund", canBeNull = true) boolean schoolFund;
	@DatabaseField(columnName = "schoolFundMore", canBeNull = true) String schoolFundMore;
	@DatabaseField(columnName = "otherInformation", canBeNull = true) String otherInformation;
	@DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, maxForeignAutoRefreshLevel = 3)
	DataCollectionForm parent;

	public Issues(){

	}
	public Issues(commcare.capstone.comcare.webservice.Issues i, DataCollectionForm d) {
		setId(i.id);
		setParent(d);
		setFinancial(i.financial);
		setGambling(i.gambling);
		setParenting(i.parenting);
		setCaregiving(i.caregiving);
		setHealth(i.health);
		setPartner(i.partner);
		setChildcare(i.childcare);
		setHousing(i.housing);
		setRetrenchment(i.retrenchment);
		setElderly(i.elderly);
		setInterpersonal(i.interpersonal);
		setSubstance(i.substance);
		setEmployment(i.employment);
		setJuvenile(i.juvenile);
		setYouth(i.youth);
		setConflict(i.conflict);
		setMental(i.mental);
		setOthers(i.others);
		setViolence(i.violence);
		setSchool(i.school);
		setSenior(i.senior);
		setSeniorMore(i.seniorMore);
		setAdult(i.adult);
		setAdultMore(i.adultMore);
		setVisual(i.visual);
		setVisualMore(i.visualMore);
		setHearing(i.hearing);
		setHearingMore(i.hearingMore);
		setThree(i.three);
		setThreeMore(i.threeMore);
		setMedical(i.medical);
		setMedicalMore(i.medicalMore);
		setOthers2(i.others2);
		setOthers2More(i.others2More);
		setUnemployement(i.unemployement);
		setUnemployementMore(i.unemployementMore);
		setUnfit(i.unfit);
		setUnfitMore(i.unfitMore);
		setFamilyIllness(i.familyIllness);
		setFamilyIllnessMore(i.familyIllnessMore);
		setLargeWithElder(i.largeWithElder);
		setLargeWithElderMore(i.largeWithElderMore);
		setLargeWithChild(i.largeWithChild);
		setLargeWithChildMore(i.largeWithChildMore);
		setSso(i.sso);
		setSsoMore(i.ssoMore);
		setAgency(i.agency);
		setAgencyMore(i.agencyMore);
		setSchoolFund(i.schoolFund);
		setSchoolFundMore(i.schoolFundMore);
		setOtherInformation(i.otherInformation);

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
	public boolean isFinancial() {
		return financial;
	}
	public void setFinancial(boolean financial) {
		this.financial = financial;
	}
	public boolean isGambling() {
		return gambling;
	}
	public void setGambling(boolean gambling) {
		this.gambling = gambling;
	}
	public boolean isParenting() {
		return parenting;
	}
	public void setParenting(boolean parenting) {
		this.parenting = parenting;
	}
	public boolean isCaregiving() {
		return caregiving;
	}
	public void setCaregiving(boolean caregiving) {
		this.caregiving = caregiving;
	}
	public boolean isHealth() {
		return health;
	}
	public void setHealth(boolean health) {
		this.health = health;
	}
	public boolean isPartner() {
		return partner;
	}
	public void setPartner(boolean partner) {
		this.partner = partner;
	}
	public boolean isChildcare() {
		return childcare;
	}
	public void setChildcare(boolean childcare) {
		this.childcare = childcare;
	}
	public boolean isHousing() {
		return housing;
	}
	public void setHousing(boolean housing) {
		this.housing = housing;
	}
	public boolean isRetrenchment() {
		return retrenchment;
	}
	public void setRetrenchment(boolean retrenchment) {
		this.retrenchment = retrenchment;
	}
	public boolean isElderly() {
		return elderly;
	}
	public void setElderly(boolean elderly) {
		this.elderly = elderly;
	}
	public boolean isInterpersonal() {
		return interpersonal;
	}
	public void setInterpersonal(boolean interpersonal) {
		this.interpersonal = interpersonal;
	}
	public boolean isSubstance() {
		return substance;
	}
	public void setSubstance(boolean substance) {
		this.substance = substance;
	}
	public boolean isEmployment() {
		return employment;
	}
	public void setEmployment(boolean employment) {
		this.employment = employment;
	}
	public boolean isJuvenile() {
		return juvenile;
	}
	public void setJuvenile(boolean juvenile) {
		this.juvenile = juvenile;
	}
	public boolean isYouth() {
		return youth;
	}
	public void setYouth(boolean youth) {
		this.youth = youth;
	}
	public boolean isConflict() {
		return conflict;
	}
	public void setConflict(boolean conflict) {
		this.conflict = conflict;
	}
	public boolean isMental() {
		return mental;
	}
	public void setMental(boolean mental) {
		this.mental = mental;
	}
	public boolean isOthers() {
		return others;
	}
	public void setOthers(boolean others) {
		this.others = others;
	}
	public boolean isViolence() {
		return violence;
	}
	public void setViolence(boolean violence) {
		this.violence = violence;
	}
	public boolean isSchool() {
		return school;
	}
	public void setSchool(boolean school) {
		this.school = school;
	}
	public boolean isSenior() {
		return senior;
	}
	public void setSenior(boolean senior) {
		this.senior = senior;
	}
	public String getSeniorMore() {
		return seniorMore;
	}
	public void setSeniorMore(String seniorMore) {
		this.seniorMore = seniorMore;
	}
	public boolean isAdult() {
		return adult;
	}
	public void setAdult(boolean adult) {
		this.adult = adult;
	}
	public String getAdultMore() {
		return adultMore;
	}
	public void setAdultMore(String adultMore) {
		this.adultMore = adultMore;
	}
	public boolean isVisual() {
		return visual;
	}
	public void setVisual(boolean visual) {
		this.visual = visual;
	}
	public String getVisualMore() {
		return visualMore;
	}
	public void setVisualMore(String visualMore) {
		this.visualMore = visualMore;
	}
	public boolean isHearing() {
		return hearing;
	}
	public void setHearing(boolean hearing) {
		this.hearing = hearing;
	}
	public String getHearingMore() {
		return hearingMore;
	}
	public void setHearingMore(String hearingMore) {
		this.hearingMore = hearingMore;
	}
	public boolean isThree() {
		return three;
	}
	public void setThree(boolean three) {
		this.three = three;
	}
	public String getThreeMore() {
		return threeMore;
	}
	public void setThreeMore(String threeMore) {
		this.threeMore = threeMore;
	}
	public boolean isMedical() {
		return medical;
	}
	public void setMedical(boolean medical) {
		this.medical = medical;
	}
	public String getMedicalMore() {
		return medicalMore;
	}
	public void setMedicalMore(String medicalMore) {
		this.medicalMore = medicalMore;
	}
	public boolean isOthers2() {
		return others2;
	}
	public void setOthers2(boolean others2) {
		this.others2 = others2;
	}
	public String getOthers2More() {
		return others2More;
	}
	public void setOthers2More(String others2More) {
		this.others2More = others2More;
	}
	public boolean isUnemployement() {
		return unemployement;
	}
	public void setUnemployement(boolean unemployement) {
		this.unemployement = unemployement;
	}
	public String getUnemployementMore() {
		return unemployementMore;
	}
	public void setUnemployementMore(String unemployementMore) {
		this.unemployementMore = unemployementMore;
	}
	public boolean isUnfit() {
		return unfit;
	}
	public void setUnfit(boolean unfit) {
		this.unfit = unfit;
	}
	public String getUnfitMore() {
		return unfitMore;
	}
	public void setUnfitMore(String unfitMore) {
		this.unfitMore = unfitMore;
	}
	public boolean isFamilyIllness() {
		return familyIllness;
	}
	public void setFamilyIllness(boolean familyIllness) {
		this.familyIllness = familyIllness;
	}
	public String getFamilyIllnessMore() {
		return familyIllnessMore;
	}
	public void setFamilyIllnessMore(String familyIllnessMore) {
		this.familyIllnessMore = familyIllnessMore;
	}
	public boolean isLargeWithElder() {
		return largeWithElder;
	}
	public void setLargeWithElder(boolean largeWithElder) {
		this.largeWithElder = largeWithElder;
	}
	public String getLargeWithElderMore() {
		return largeWithElderMore;
	}
	public void setLargeWithElderMore(String largeWithElderMore) {
		this.largeWithElderMore = largeWithElderMore;
	}
	public boolean isLargeWithChild() {
		return largeWithChild;
	}
	public void setLargeWithChild(boolean largeWithChild) {
		this.largeWithChild = largeWithChild;
	}
	public String getLargeWithChildMore() {
		return largeWithChildMore;
	}
	public void setLargeWithChildMore(String largeWithChildMore) {
		this.largeWithChildMore = largeWithChildMore;
	}
	public boolean isSso() {
		return sso;
	}
	public void setSso(boolean sso) {
		this.sso = sso;
	}
	public String getSsoMore() {
		return ssoMore;
	}
	public void setSsoMore(String ssoMore) {
		this.ssoMore = ssoMore;
	}
	public boolean isAgency() {
		return agency;
	}
	public void setAgency(boolean agency) {
		this.agency = agency;
	}
	public String getAgencyMore() {
		return agencyMore;
	}
	public void setAgencyMore(String agencyMore) {
		this.agencyMore = agencyMore;
	}
	public boolean isSchoolFund() {
		return schoolFund;
	}
	public void setSchoolFund(boolean schoolFund) {
		this.schoolFund = schoolFund;
	}
	public String getSchoolFundMore() {
		return schoolFundMore;
	}
	public void setSchoolFundMore(String schoolFundMore) {
		this.schoolFundMore = schoolFundMore;
	}
	public String getOtherInformation() {
		return otherInformation;
	}
	public void setOtherInformation(String otherInformation) {
		this.otherInformation = otherInformation;
	}
	
	
	
}
