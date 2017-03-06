package com.example.zc_ch.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc_ch on 3/1/2017.
 */

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    ; private DBManager mgr;



    EditText emailText;
    EditText passwordText;
    EditText heightText;
    EditText weightText;

    Button signupButton;
    //    @InjectView(R.id.link_login)
    TextView loginLink;
    final List<Data> datas= new ArrayList<Data>();
    final List<Tag> tags= new ArrayList<Tag>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
//        ButterKnife.inject(this);
        signupButton=(Button) findViewById(R.id.btn_signup);
        emailText=(EditText) findViewById(R.id.input_email);
        passwordText = (EditText) findViewById(R.id.input_password);
        loginLink= (TextView) findViewById(R.id.link_login);

        heightText=(EditText)findViewById(R.id.input_height);
        weightText=(EditText)findViewById(R.id.input_weight);

        Log.d("saaa","the first is ok");
        mgr= new DBManager(this);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

//        String name = _nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String height= heightText.getText().toString();
        String weight=weightText.getText().toString();
        // TODO: Implement your own signup logic here.
        mgr.user_register(email,password,height,weight);
//        DB.SaveTag(email);
//        Data data=new Data("beef","ac1","23","meat","good")

        datas.add(new Data("sibao","duck,sausage,rice,egg","CityU AC1 Canteen 8th window","2-3",10,9));
        datas.add(new Data("Steamed Chicken with Chili Sauce","chicken,chili","CityU AC1 Canteen 8th window","3-4",2,2));
        tags.add(new Tag(email,"duck",4.0));
        tags.add(new Tag(email,"sausage",4.0));
        mgr.add(datas);
        mgr.addTag(tags);
        mgr.query();
        mgr.getTag(email);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

//        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        Log.d("email",email);
        String password = passwordText.getText().toString();
        String height= heightText.getText().toString();
        String weight=weightText.getText().toString();
//        if (name.isEmpty() || name.length() < 3) {
//            nameText.setError("at least 3 characters");
//            valid = false;
//        } else {
//            nameText.setError(null);
//        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
//            return
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        if (height.equals("") || weight.equals("")) {
            heightText.setError("enter a valid number");
            valid=false;
        }else
        {
            heightText.setError(null);
            weightText.setError(null);
        }

//        DB.onCreate();
//        if(DB.exist_user(email))
//        {
//            valid=false;
//            Toast.makeText(SignupActivity.this, "email already exist",
//                    Toast.LENGTH_LONG).show();
//
//        }


        return valid;
    }
}