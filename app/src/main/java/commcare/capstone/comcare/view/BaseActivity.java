package commcare.capstone.comcare.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import java.util.List;

import commcare.capstone.comcare.R;
import commcare.capstone.comcare.biz.DataBiz;

/**
 * Created by stevenyeo on 25/11/15.
 */
public class BaseActivity extends Activity
{
    public PopupWindow popupMenu;


    protected CharSequence[] convertStringsToCharSequenceArray(List<String> strings)
    {
        CharSequence[] returnSequence = new CharSequence[strings.size()];
        for (int i=0; i<strings.size(); i++)
        {
            String string = strings.get(i);
            returnSequence[i] = string.subSequence(0, string.length());
        }
        return returnSequence;
    }


    protected void setupPopupMenu()
    {
        ImageView view = (ImageView) findViewById(R.id.menu);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showStatusPopup(BaseActivity.this);
            }
        });
    }


    public void showStatusPopup(final Activity context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_menu, null);

        popupMenu = new PopupWindow(context);
        popupMenu.setContentView(layout);
        popupMenu.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupMenu.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupMenu.setFocusable(true);


        Button logoutButton = (Button) layout.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataBiz.getInstance().logout();
                Intent intent = new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                popupMenu.dismiss();
            }
        });

        Button homeButton = (Button) layout.findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                popupMenu.dismiss();
            }
        });
        //Clear the default translucent background
        popupMenu.setBackgroundDrawable(new BitmapDrawable());

        popupMenu.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
