package commcare.capstone.comcare.util;

import android.app.Activity;
import android.os.Environment;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import commcare.capstone.comcare.R;
import commcare.capstone.comcare.model.HouseVisit;
import commcare.capstone.comcare.view.DataCollectionAssistanceFormActivity;


/**
 * Created by yuan on 18/10/17.
 */

public class PDFGenerator {
    static Logger LOG = LoggerFactory.getLogger(DataCollectionAssistanceFormActivity.class);

    public static void generateDataCollectionPDF(Activity context, HouseVisit hv) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(context.getResources().openRawResource(R.raw.datacollection));
        File pdfFolder = new File(Environment.getExternalStorageDirectory()
                + "/generated/datacollection/");
        if (!pdfFolder.exists()) {
            LOG.info("Folder does not Exist");
            pdfFolder.mkdirs();
        }
        String dst = Environment.getExternalStorageDirectory()
                + "/generated/datacollection/DataCollection_"+hv.getResident().getNric()+"_"+new Date().getTime()+".pdf";
        LOG.info("Destination " + dst);
        File file = new File(dst);
        file.createNewFile();
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dst));
        AcroFields form = stamper.getAcroFields();

//        form.setField("Date", );
//        form.setField("Time", );
//        form.setField("Duration", );
        form.setField("HouseVisitBy", hv.getAssignedTo().getFullName());
//        form.setField("GRLStaff", );
//        form.setField("GRLStaffContact", );
//        form.setField("EndorsedBy", );
//        form.setField("Genogram", );
        //Resident
        form.setField("Full Name", hv.getResident().getFullName());
        form.setField("NRIC", hv.getResident().getNric());
        form.setField("Sex", hv.getResident().getSex());
        form.setField("Date of Birth", hv.getResident().getDob());
        form.setField("Age", hv.getResident().getAge());
        form.setField("Contact Number", hv.getResident().getCtcNumber());
        form.setField("Address", hv.getResident().getAddress());
        form.setField("Nationality", hv.getResident().getNationality());
        form.setField("MaritalStatus", hv.getResident().getMaritalStatus());
        form.setField("Race", hv.getResident().getRace());
        form.setField("Flat", hv.getResident().getTypeOfFlat());
        form.setField("SpokenLanguage", hv.getResident().getSpokenLanguage());
        form.setField("Religion", hv.getResident().getReligion());
        form.setField("Occupation", hv.getResident().getOccupation());
        form.setField("HouseholdIncome", hv.getResident().getHouseholdIncome());
        String householdMembers = hv.getResident().getHouseholdMember();
        if (householdMembers != null)
        {
            String[] members = householdMembers.split("\\|");
            String houseHoldMemberString = "Senior Age 55 & Above: " + members[0];
            houseHoldMemberString += System.getProperty("line.separator") + "Adult Age 21 & Above: " + members[1];
            houseHoldMemberString += System.getProperty("line.separator") + "Young Children (Age 0 – 20): " + members[2];

            form.setField("HouseholdMembers", houseHoldMemberString);
        }
        form.setField("EducationQualification", hv.getResident().getHighestEducation());
        form.setField("VehicleOwner", hv.getResident().getVehOwner());

        //Issues
        if (hv.getDataCollectionForm().getIssues().isFinancial())
        {
            form.setField("IssueFinancial", "Yes", true);

        }
        if (hv.getDataCollectionForm().getIssues().isGambling())
        {
            form.setField("IssueGambling", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isParenting())
        {
            form.setField("IssueParenting", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isCaregiving())
        {
            form.setField("IssueCaregiving", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isHealth())
        {
            form.setField("IssueHealth", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isPartner())
        {
            form.setField("IssuePartner", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isChildcare())
        {
            form.setField("IssueChildCare", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isHousing())
        {
            form.setField("IssueHousing", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isRetrenchment())
        {
            form.setField("IssueRetrenchment", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isElderly())
        {
            form.setField("IssueElderly", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isInterpersonal())
        {
            form.setField("IssueInterpersonal", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isSubstance())
        {
            form.setField("IssueSubstance", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isEmployment())
        {
            form.setField("IssueEmployment", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isJuvenile())
        {
            form.setField("IssueJuvenile", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isYouth())
        {
            form.setField("IssueYouth", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isConflict())
        {
            form.setField("IssueConflict", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isMental())
        {
            form.setField("IssueMental", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isOthers())
        {
            form.setField("IssueOthers", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isViolence())
        {
            form.setField("IssueViolence", "Yes", true);
        }
        if (hv.getDataCollectionForm().getIssues().isSchool())
        {
            form.setField("IssueSchool", "Yes", true);
        }

        //Health
        if (hv.getDataCollectionForm().getIssues().isSenior())
        {
            form.setField("IssueSenior", "Yes", true);
        }
        form.setField("IssueSeniorMore", hv.getDataCollectionForm().getIssues().getSeniorMore());
        if (hv.getDataCollectionForm().getIssues().isAdult())
        {
            form.setField("IssueAdult", "Yes", true);
        }
        form.setField("IssueAdultMore", hv.getDataCollectionForm().getIssues().getAdultMore());
        if (hv.getDataCollectionForm().getIssues().isVisual())
        {
            form.setField("IssueVisual", "Yes", true);
        }
        form.setField("IssueVisualMore", hv.getDataCollectionForm().getIssues().getVisualMore());
        if (hv.getDataCollectionForm().getIssues().isHearing())
        {
            form.setField("IssueHearing", "Yes", true);
        }
        form.setField("IssueHearingMore", hv.getDataCollectionForm().getIssues().getHearingMore());
        if (hv.getDataCollectionForm().getIssues().isThree())
        {
            form.setField("IssueThree", "Yes", true);
        }
        form.setField("IssueThreeMore", hv.getDataCollectionForm().getIssues().getThreeMore());
        if (hv.getDataCollectionForm().getIssues().isMedical())
        {
            form.setField("IssueMedical", "Yes", true);
        }
        form.setField("IssueMedicalMore", hv.getDataCollectionForm().getIssues().getMedicalMore());
        if (hv.getDataCollectionForm().getIssues().isOthers2())
        {
            form.setField("IssueOthers2", "Yes", true);
        }
        form.setField("IssueOthers2More", hv.getDataCollectionForm().getIssues().getOthers2More());

        //Financial
        if (hv.getDataCollectionForm().getIssues().isUnemployement())
        {
            form.setField("IssueUnemployment", "Yes", true);
        }
        form.setField("IssueUnemploymentMore", hv.getDataCollectionForm().getIssues().getUnemployementMore());
        if (hv.getDataCollectionForm().getIssues().isUnfit())
        {
            form.setField("IssueUnfit", "Yes", true);
        }
        form.setField("IssueUnfitMore", hv.getDataCollectionForm().getIssues().getUnfitMore());
        if (hv.getDataCollectionForm().getIssues().isFamilyIllness())
        {
            form.setField("IssueFamilyIllness", "Yes", true);
        }
        form.setField("IssueFamilyIllnessMore", hv.getDataCollectionForm().getIssues().getFamilyIllnessMore());
        if (hv.getDataCollectionForm().getIssues().isLargeWithElder())
        {
            form.setField("IssueLargeWithElder", "Yes", true);
        }
        form.setField("IssueLargeWithElderMore", hv.getDataCollectionForm().getIssues().getLargeWithElderMore());
        if (hv.getDataCollectionForm().getIssues().isLargeWithChild())
        {
            form.setField("IssueLargeWithChild", "Yes", true);
        }
        form.setField("IssueLargeWithChildMore", hv.getDataCollectionForm().getIssues().getLargeWithChildMore());
        if (hv.getDataCollectionForm().getIssues().isSso())
        {
            form.setField("IssueSSO", "Yes", true);
        }
        form.setField("IssueSSOMore", hv.getDataCollectionForm().getIssues().getSsoMore());
        if (hv.getDataCollectionForm().getIssues().isAgency())
        {
            form.setField("IssueAgency", "Yes", true);
        }
        form.setField("IssueAgencyMore", hv.getDataCollectionForm().getIssues().getAgencyMore());
        if (hv.getDataCollectionForm().getIssues().isSchoolFund())
        {
            form.setField("IssueSchoolFund", "Yes", true);
        }
        form.setField("IssueSchoolFundMore", hv.getDataCollectionForm().getIssues().getSchoolFundMore());

        //Other Information
        form.setField("OtherInformation", hv.getDataCollectionForm().getIssues().getOtherInformation());

        if (hv.getDataCollectionForm().getAssistances().isWheels())
        {
            form.setField("AssistWheels", "Yes", true);
        }
        if (hv.getDataCollectionForm().getAssistances().isFood())
        {
            form.setField("AssistFood", "Yes", true);
        }
        if (hv.getDataCollectionForm().getAssistances().isGrant())
        {
            form.setField("AssistGrant", "Yes", true);
        }
        if (hv.getDataCollectionForm().getAssistances().isTingkat())
        {
            form.setField("AssistTingkat", "Yes", true);
        }
        if (hv.getDataCollectionForm().getAssistances().isUtility())
        {
            form.setField("AssistUtility", "Yes", true);
        }
        if (hv.getDataCollectionForm().getAssistances().isLongTerm())
        {
            form.setField("AssistLongTerm", "Yes", true);
        }
        if (hv.getDataCollectionForm().getAssistances().isHomeFix())
        {
            form.setField("AssistHomeFix", "Yes", true);
        }
        if (hv.getDataCollectionForm().getAssistances().isMobility())
        {
            form.setField("AssistMobility", "Yes", true);
        }
        if (hv.getDataCollectionForm().getAssistances().isShortTerm())
        {
            form.setField("AssistShortTerm", "Yes", true);
        }
        if (hv.getDataCollectionForm().getAssistances().isBefriend())
        {
            form.setField("AssistBefriend", "Yes", true);
        }
        form.setField("OtherAssistance", hv.getDataCollectionForm().getAssistances().getOtherAssistance());

        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();

    }

}
