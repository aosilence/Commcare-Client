package commcare.capstone.comcare.view;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import commcare.capstone.comcare.R;
import commcare.capstone.comcare.biz.DataBiz;
import commcare.capstone.comcare.model.datacollection.GenogramObj;

import static android.R.string.cancel;
import static commcare.capstone.comcare.R.id.layout1;


public class GenoDrawDialog extends Dialog
{
    Logger LOG = LoggerFactory.getLogger(GenoDrawDialog.class);
    DataCollectionGenoFormActivity parentAct;
    ArrayList<GenogramObj> parentGeno;
    LinearLayout layout1;
    GenoDrawView genoView;
    boolean exitable;
    public GenoDrawDialog(DataCollectionGenoFormActivity parentAct, ArrayList<GenogramObj> parentGeno, boolean exitable)
    {
        super(parentAct);
        this.exitable = exitable;
        this.parentAct = parentAct;
        this.parentGeno = parentGeno;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_geno_draw_dialog);
        setupButtons();
        setupSelections();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    private void setupSelections() {
        layout1 = (LinearLayout) findViewById(R.id.layout1);
        genoView = new GenoDrawView(parentAct, parentGeno);
        layout1.addView(genoView);

  }

    private void setupButtons() {

        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        if (exitable)
        {
            cancelButton.setVisibility(Button.INVISIBLE);
            saveButton.setText("Close");
            saveButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
        else
        {
            saveButton.setText("Next");
            saveButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Date now = new Date();
                    String dateOfImg = new SimpleDateFormat("ddMMyyyyHHmmssSSS", Locale.ENGLISH).format(now);
                    File imageFolder = new File(Environment.getExternalStorageDirectory()
                            + "/generated/datacollection/tree");
                    if (!imageFolder.exists()) {
                        LOG.info("Folder does not Exist");
                        imageFolder.mkdirs();
                    }
                    String current = dateOfImg + "tree.png";
                    File imgpath= new File(imageFolder,current);

                    save(genoView, imgpath);
                    DataBiz.getInstance().getSelectedHV().getDataCollectionForm().setGenoPath(imgpath.toString());
                    dismiss();
                    Intent intent = new Intent(parentAct, DataCollectionIssuesFormActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    parentAct.startActivity(intent);
                }
            });

            cancelButton.setVisibility(Button.VISIBLE);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                   dismiss();
                }
            });
        }


    }

    public void save(View v, File mypath)
    {
        Bitmap bitmap =  Bitmap.createBitmap (v.getWidth(), v.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        try
        {
            FileOutputStream mFileOutStream = new FileOutputStream(mypath);
            v.draw(canvas);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, mFileOutStream);
            mFileOutStream.flush();
            mFileOutStream.close();
        }
        catch(Exception e)
        {
            LOG.error(e.toString());
        }
    }
}
