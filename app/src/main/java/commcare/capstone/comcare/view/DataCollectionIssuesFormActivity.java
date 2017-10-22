package commcare.capstone.comcare.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import commcare.capstone.comcare.R;
import commcare.capstone.comcare.biz.DataBiz;
import commcare.capstone.comcare.model.HouseVisit;
import commcare.capstone.comcare.model.datacollection.DataCollectionForm;
import commcare.capstone.comcare.model.datacollection.Issues;

import static commcare.capstone.comcare.R.id.careGivingCB;
import static commcare.capstone.comcare.R.id.childCareCB;
import static commcare.capstone.comcare.R.id.elderlyCB;
import static commcare.capstone.comcare.R.id.employmentCB;
import static commcare.capstone.comcare.R.id.familyCB;
import static commcare.capstone.comcare.R.id.financialCB;
import static commcare.capstone.comcare.R.id.gamblingCB;
import static commcare.capstone.comcare.R.id.healthCB;
import static commcare.capstone.comcare.R.id.housingCB;
import static commcare.capstone.comcare.R.id.interpersonalCB;
import static commcare.capstone.comcare.R.id.juvenileCB;
import static commcare.capstone.comcare.R.id.mentalCB;
import static commcare.capstone.comcare.R.id.othersCB;
import static commcare.capstone.comcare.R.id.parentingCB;
import static commcare.capstone.comcare.R.id.partnerCB;
import static commcare.capstone.comcare.R.id.retrenchCB;
import static commcare.capstone.comcare.R.id.schoolCB;
import static commcare.capstone.comcare.R.id.substanceCB;
import static commcare.capstone.comcare.R.id.violenceCB;


public class DataCollectionIssuesFormActivity extends BaseActivity {
	Logger LOG = LoggerFactory.getLogger(DataCollectionIssuesFormActivity.class);

	HouseVisit hv;
	CheckBox financialCB;
	CheckBox careGivingCB;
	CheckBox childCareCB;
	CheckBox elderlyCB;
	CheckBox employmentCB;
	CheckBox familyCB;
	CheckBox violenceCB;
	CheckBox gamblingCB;
	CheckBox healthCB;
	CheckBox housingCB;
	CheckBox interpersonalCB;
	CheckBox juvenileCB;
	CheckBox mentalCB;
	CheckBox schoolCB;
	CheckBox parentingCB;
	CheckBox partnerCB;
	CheckBox retrenchCB;
	CheckBox substanceCB;
	CheckBox youthCB;
	CheckBox othersCB;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datacollectionissues);
		setupPopupMenu();
		setupButtons();
		setupSelections();
	}

	private void setupSelections() {
		financialCB = (CheckBox) findViewById(R.id.financialCB);
		careGivingCB = (CheckBox) findViewById(R.id.careGivingCB);
		childCareCB = (CheckBox) findViewById(R.id.childCareCB);
		elderlyCB = (CheckBox) findViewById(R.id.elderlyCB);
		employmentCB = (CheckBox) findViewById(R.id.employmentCB);
		familyCB = (CheckBox) findViewById(R.id.familyCB);
		violenceCB = (CheckBox) findViewById(R.id.violenceCB);
		gamblingCB = (CheckBox) findViewById(R.id.gamblingCB);
		healthCB = (CheckBox) findViewById(R.id.healthCB);
		housingCB = (CheckBox) findViewById(R.id.housingCB);
		interpersonalCB = (CheckBox) findViewById(R.id.interpersonalCB);
		juvenileCB = (CheckBox) findViewById(R.id.juvenileCB);
		mentalCB = (CheckBox) findViewById(R.id.mentalCB);
		schoolCB = (CheckBox) findViewById(R.id.schoolCB);
		parentingCB = (CheckBox) findViewById(R.id.parentingCB);
		partnerCB = (CheckBox) findViewById(R.id.partnerCB);
		retrenchCB = (CheckBox) findViewById(R.id.retrenchCB);
		substanceCB = (CheckBox) findViewById(R.id.substanceCB);
		youthCB = (CheckBox) findViewById(R.id.youthCB);
		othersCB = (CheckBox) findViewById(R.id.othersCB);
	}

	private void setupButtons() {
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataCollectionIssuesFormActivity.this, DataCollectionGenoFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});

		Button leftBtn = (Button) findViewById(R.id.leftBtn);
		leftBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataCollectionIssuesFormActivity.this, DataCollectionGenoFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});
		Button rightBtn = (Button) findViewById(R.id.rightBtn);
		rightBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saveResult();
				Intent intent = new Intent(DataCollectionIssuesFormActivity.this, DataCollectionHealthFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});
	}

	private void saveResult() {
		if(hv.getDataCollectionForm() == null)
		{
			hv.setDataCollectionForm(new DataCollectionForm());
		}
		if(hv.getDataCollectionForm().getIssues() == null)
		{
			hv.getDataCollectionForm().setIssues(new Issues());
		}
		hv.getDataCollectionForm().getIssues().setFinancial(financialCB.isChecked());
		hv.getDataCollectionForm().getIssues().setCaregiving(careGivingCB.isChecked());
		hv.getDataCollectionForm().getIssues().setChildcare(childCareCB.isChecked());
		hv.getDataCollectionForm().getIssues().setElderly(elderlyCB.isChecked());
		hv.getDataCollectionForm().getIssues().setEmployment(employmentCB.isChecked());
		hv.getDataCollectionForm().getIssues().setConflict(familyCB.isChecked());
		hv.getDataCollectionForm().getIssues().setViolence(violenceCB.isChecked());
		hv.getDataCollectionForm().getIssues().setGambling(gamblingCB.isChecked());
		hv.getDataCollectionForm().getIssues().setHealth(healthCB.isChecked());
		hv.getDataCollectionForm().getIssues().setHousing(housingCB.isChecked());
		hv.getDataCollectionForm().getIssues().setInterpersonal(interpersonalCB.isChecked());
		hv.getDataCollectionForm().getIssues().setJuvenile(juvenileCB.isChecked());
		hv.getDataCollectionForm().getIssues().setMental(mentalCB.isChecked());
		hv.getDataCollectionForm().getIssues().setSchool(schoolCB.isChecked());
		hv.getDataCollectionForm().getIssues().setParenting(parentingCB.isChecked());
		hv.getDataCollectionForm().getIssues().setPartner(partnerCB.isChecked());
		hv.getDataCollectionForm().getIssues().setRetrenchment(retrenchCB.isChecked());
		hv.getDataCollectionForm().getIssues().setSubstance(substanceCB.isChecked());
		hv.getDataCollectionForm().getIssues().setYouth(youthCB.isChecked());
		hv.getDataCollectionForm().getIssues().setOthers(othersCB.isChecked());
		DataBiz.getInstance().getDb().getResidentRuntimeDAO().createOrUpdate(hv.getResident());
		DataBiz.getInstance().getDb().getIssuesRuntimeDAO().createOrUpdate(hv.getDataCollectionForm().getIssues());
		DataBiz.getInstance().getDb().getDataCollectionFormRuntimeDAO().createOrUpdate(hv.getDataCollectionForm());
		DataBiz.getInstance().getDb().getHouseVisitRuntimeDAO().createOrUpdate(hv);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		hv = DataBiz.getInstance().getSelectedHV();
		if (hv.getDataCollectionForm() != null && hv.getDataCollectionForm().getIssues() != null)
		{
			financialCB.setChecked(hv.getDataCollectionForm().getIssues().isFinancial());
			careGivingCB.setChecked(hv.getDataCollectionForm().getIssues().isCaregiving());
			childCareCB.setChecked(hv.getDataCollectionForm().getIssues().isChildcare());
			elderlyCB.setChecked(hv.getDataCollectionForm().getIssues().isElderly());
			employmentCB.setChecked(hv.getDataCollectionForm().getIssues().isEmployment());
			familyCB.setChecked(hv.getDataCollectionForm().getIssues().isConflict());
			violenceCB.setChecked(hv.getDataCollectionForm().getIssues().isViolence());
			gamblingCB.setChecked(hv.getDataCollectionForm().getIssues().isGambling());
			healthCB.setChecked(hv.getDataCollectionForm().getIssues().isHealth());
			housingCB.setChecked(hv.getDataCollectionForm().getIssues().isHousing());
			interpersonalCB.setChecked(hv.getDataCollectionForm().getIssues().isInterpersonal());
			juvenileCB.setChecked(hv.getDataCollectionForm().getIssues().isJuvenile());
			mentalCB.setChecked(hv.getDataCollectionForm().getIssues().isMental());
			schoolCB.setChecked(hv.getDataCollectionForm().getIssues().isSchool());
			parentingCB.setChecked(hv.getDataCollectionForm().getIssues().isParenting());
			partnerCB.setChecked(hv.getDataCollectionForm().getIssues().isPartner());
			retrenchCB.setChecked(hv.getDataCollectionForm().getIssues().isRetrenchment());
			substanceCB.setChecked(hv.getDataCollectionForm().getIssues().isSubstance());
			youthCB.setChecked(hv.getDataCollectionForm().getIssues().isYouth());
			othersCB.setChecked(hv.getDataCollectionForm().getIssues().isOthers());
		}
		else
		{
			financialCB.setChecked(false);
			careGivingCB.setChecked(false);
			childCareCB.setChecked(false);
			elderlyCB.setChecked(false);
			employmentCB.setChecked(false);
			familyCB.setChecked(false);
			violenceCB.setChecked(false);
			gamblingCB.setChecked(false);
			healthCB.setChecked(false);
			housingCB.setChecked(false);
			interpersonalCB.setChecked(false);
			juvenileCB.setChecked(false);
			mentalCB.setChecked(false);
			schoolCB.setChecked(false);
			parentingCB.setChecked(false);
			partnerCB.setChecked(false);
			retrenchCB.setChecked(false);
			substanceCB.setChecked(false);
			youthCB.setChecked(false);
			othersCB.setChecked(false);
		}
	}




}
