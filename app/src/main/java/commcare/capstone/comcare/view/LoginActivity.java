package commcare.capstone.comcare.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import commcare.capstone.comcare.R;
import commcare.capstone.comcare.biz.DataBiz;
import commcare.capstone.comcare.biz.EnviromentBiz;
import commcare.capstone.comcare.model.LastLogin;
import commcare.capstone.comcare.model.User;

public class LoginActivity extends Activity {

    Logger LOG = LoggerFactory.getLogger(LoginActivity.class);

    EditText userName;
    EditText password;
    ProgressBar mProgress;
    Button loginButton;
    TextView versionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DataBiz.getInstance().getDb(this);
        DataBiz.getInstance().getWs();

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        mProgress = (ProgressBar) findViewById(R.id.progressBar1);
        versionTextView = (TextView) findViewById(R.id.versionTextView);

        TextWatcher tw = new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                for(int i = s.length(); i > 0; i--){

                    if(s.subSequence(i-1, i).toString().equals("\n"))
                    {
                        s.replace(i-1, i, "");
                        password.requestFocus();
                    }
                }
            }
        };

        userName.addTextChangedListener(tw);
        if (EnviromentBiz.getInstance().isTest())
        {

            versionTextView.setText("Version "+EnviromentBiz.getInstance().getVersion()+ " (Test)");
        }
        else if (EnviromentBiz.getInstance().isProduction())
        {
            versionTextView.setText("Version "+EnviromentBiz.getInstance().getVersion()+ " (Production)");
        }
        else
        {
            versionTextView.setText("Version "+EnviromentBiz.getInstance().getVersion()+ " (Staging)");
        }
        addListenerOnButton();
    }

    public void showLoadingDialog() {
        mProgress.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);
        userName.setEnabled(false);
        password.setEnabled(false);
    }

    public void dismissLoadingDialog() {
        runOnUiThread (new Thread(new Runnable() {
            public void run() {
                mProgress.setVisibility(View.INVISIBLE);
                loginButton.setVisibility(View.VISIBLE);
                userName.setEnabled(true);
                password.setEnabled(true);
            }
        }));

    }

    public void toast(String msg) {
        final String mesg = msg;
        runOnUiThread (new Thread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), mesg, Toast.LENGTH_SHORT).show();
            }
        }));

    }

    public void addListenerOnButton() {

        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showLoadingDialog();
                userName.setText(userName.getText().toString().trim());
                final String user = userName.getText().toString();
                final String pw = password.getText().toString();
                login(user, pw);
            }
        });
    }

    private void login(final String user, final String pw) {
        try {
            final Context context = this;

            new AsyncTask<Void, Void, User>() {

                @Override
                protected User doInBackground(Void... params) {
                    try{

                    LOG.debug("Logging in");
                    User u = DataBiz.getInstance().login(user, pw);
                    return u;
                    }
                    catch (Exception ex) {
                        dismissLoadingDialog();
                        toast(ex.getMessage());
                        LOG.error("LoginActivity LoginError",ex);
                        ex.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(User u) {
                    LOG.debug("onPostExecute");
                    if (u != null) {
                        LOG.debug("onPostExecute !- null");
                        DataBiz.getInstance().getDb(LoginActivity.this).getLastLoginRuntimeDAO().delete(DataBiz.getInstance().getDb(LoginActivity.this).getLastLoginRuntimeDAO().queryForAll());
                        LastLogin ll = new LastLogin();
                        ll.setId(1);
                        ll.setPw(pw);
                        ll.setToken(u.getToken());
                        ll.setName(user);
                        ll.setDisplayName(user);
                        ll.setLoggedIn(true);
                        DataBiz.getInstance().getDb(LoginActivity.this).getLastLoginRuntimeDAO().create(ll);
                        Intent intent = new Intent(context, LoadActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        dismissLoadingDialog();
                    } else {
                        dismissLoadingDialog();
                    }
                }
            }.execute();
        }
        catch (Exception ex) {
            dismissLoadingDialog();
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            LOG.error("LoginActivity LoginError",ex);
            ex.printStackTrace();
        }
    }


    protected void onResume() {
        List<LastLogin> lastlogin = DataBiz.getInstance().getDb(this).getLastLoginRuntimeDAO().queryForAll();
        if (lastlogin.size()>0)
        {
            if (lastlogin.get(0).isLoggedIn())
            {
                userName.setText(lastlogin.get(0).getName());
                dismissLoadingDialog();
                Intent intent = new Intent(LoginActivity.this, LoadActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }

        }

        super.onResume();
    }

    @Override
    public void onBackPressed() {
    }
}
