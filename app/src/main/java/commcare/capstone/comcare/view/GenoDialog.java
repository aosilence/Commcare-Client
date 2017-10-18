package commcare.capstone.comcare.view;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import commcare.capstone.comcare.R;
import commcare.capstone.comcare.biz.DataBiz;
import commcare.capstone.comcare.model.GenogramObj;

import static commcare.capstone.comcare.R.id.radioFemale;
import static commcare.capstone.comcare.R.id.radioMale;


public class GenoDialog extends Dialog
{
    Logger LOG = LoggerFactory.getLogger(GenoDialog.class);
    DataCollectionGenoFormActivity parentAct;
    GenogramObj parentGeno;

    TextView relationTV;
    RadioButton radioParent;
    RadioButton radioSibling;
    RadioButton radioSpouse;
    RadioButton radioChild;
    RadioButton radioMale;
    RadioButton radioFemale;
    EditText fullNameET;
    EditText ageET;
    EditText occupationET;
    EditText salaryET;
    EditText illnessET;

    public GenoDialog(DataCollectionGenoFormActivity parentAct, GenogramObj parentGeno)
    {
        super(parentAct);
        this.parentAct = parentAct;
        this.parentGeno = parentGeno;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_geno_dialog);
        setupButtons();
        setupSelections();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    private void setupSelections() {
        relationTV = (TextView) findViewById(R.id.relationTV);
        radioParent = (RadioButton) findViewById(R.id.radioParent);
        radioSibling = (RadioButton) findViewById(R.id.radioSibling);
        radioSpouse = (RadioButton) findViewById(R.id.radioSpouse);
        radioChild = (RadioButton) findViewById(R.id.radioChild);
        radioMale = (RadioButton) findViewById(R.id.radioMale);
        radioFemale = (RadioButton) findViewById(R.id.radioFemale);
        fullNameET = (EditText) findViewById(R.id.fullNameET);
        ageET = (EditText) findViewById(R.id.ageET);
        occupationET = (EditText) findViewById(R.id.occupationET);
        salaryET = (EditText) findViewById(R.id.salaryET);
        illnessET = (EditText) findViewById(R.id.illnessET);
    }

    private void setupButtons() {
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setText("Cancel");
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setText("Save");
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            GenogramObj geno = new GenogramObj();
            geno.setRelateTo(parentGeno);
            if (radioParent.isChecked())
            {
                geno.setRelation(GenogramObj.RELATIONSHIP_PARENT);
            }
            else if (radioSibling.isChecked())
            {
                geno.setRelation(GenogramObj.RELATIONSHIP_SIBLING);
            }
            else if(radioSpouse.isChecked())
            {
                geno.setRelation(GenogramObj.RELATIONSHIP_SPOUSE);
            }
            else if (radioChild.isChecked())
            {
                geno.setRelation(GenogramObj.RELATIONSHIP_CHILD);
            }
            if (radioMale.isChecked())
            {
                geno.setSex("Male");
            }
            if (radioFemale.isChecked())
            {
                geno.setSex("Female");
            }
            geno.setFullName(fullNameET.getText().toString());
            geno.setAge(ageET.getText().toString());
            geno.setOccupation(occupationET.getText().toString());
            geno.setSalary(salaryET.getText().toString());
            geno.setIllness(illnessET.getText().toString());
            geno.setParent(DataBiz.getInstance().getSelectedHV().getDataCollectionForm());
            DataBiz.getInstance().getDb().getGenogramObjRuntimeDAO().createOrUpdate(geno);
            parentAct.updateUI();
            dismiss();
            }
        });
    }
}
