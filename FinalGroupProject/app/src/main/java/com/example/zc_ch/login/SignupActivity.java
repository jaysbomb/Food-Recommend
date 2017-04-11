package com.example.zc_ch.login;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by zc_ch on 3/1/2017.
 */


public class SignupActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "SignupActivity";
    private SensorManager sensorManager;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Float s;
    public Integer healthy;
    EditText emailText;
    EditText passwordText;
    EditText heightText;
    EditText weightText;
    EditText usernameText;
    boolean activityRunning;

    Button signupButton;
    //    @InjectView(R.id.link_login)
    TextView loginLink;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupButton=(Button) findViewById(R.id.btn_signup);
        emailText=(EditText) findViewById(R.id.input_email);
        passwordText = (EditText) findViewById(R.id.input_password);
        loginLink= (TextView) findViewById(R.id.link_login);
        usernameText=(EditText) findViewById(R.id.input_username);
        heightText=(EditText)findViewById(R.id.input_height);
        weightText=(EditText)findViewById(R.id.input_weight);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {finish();
            }
        });
        FirebaseApp.initializeApp(SignupActivity.this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
            healthy = 0 ;
        }
    }

    @Override
    protected  void onPause() {
        super.onPause();
        activityRunning = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(activityRunning){
            s = event.values[0];
            if ( s < 3000 ){
                healthy = 1;
            }else if( s < 6000 ){
                healthy = 2;
            }else if( s < 9000 ){
                healthy = 3;
            }else if( s < 12000){
                healthy = 4;
            }else if( s < 15000){
                healthy = 5;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

//        signupButton.setEnabled(false);

//        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
//                R.style.AppTheme);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Creating Account...");
//        progressDialog.show();

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
         boolean flag = false;
        // TODO: Implement your own signup logic here.

        Task<AuthResult> LoginService = mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String myemail = emailText.getText().toString();
                        String username=usernameText.getText().toString();
                        String height= heightText.getText().toString();
                        String weight=weightText.getText().toString();
                        Integer healths = healthy;
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:failed:" + task.isSuccessful());

                            Toast.makeText(SignupActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            myemail=myemail.replace('.',',');
                            user new_user= new user(myemail,username,height,weight,healths);
                            RealtimeDatabase mydatabase = new RealtimeDatabase();
                            mydatabase.writeNewUser(new_user);
                            Tag user_tag = new Tag(myemail);
                            mydatabase.writeNewTag(user_tag);
                            Toast.makeText(SignupActivity.this, R.string.auth_successed,
                                    Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }
                });
//        Log.d("test", String.valueOf(LoginService.isSuccessful()));
//        if(LoginService.isSuccessful()){
//            Log.d("test",email);
//            email=email.replace('.',',');
//            user new_user= new user(email,username,height,weight);
//            RealtimeDatabase mydatabase = new RealtimeDatabase();
//            mydatabase.writeNewUser(new_user);
//        }
//
//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onSignupSuccess or onSignupFailed
//                        // depending on success
//                        onSignupSuccess();
//                        // onSignupFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);
    }


    public void onSignupSuccess() {
//        signupButton.setEnabled(true);
//        setResult(RESULT_OK, null);
//        finish();
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

        return valid;
    }
}