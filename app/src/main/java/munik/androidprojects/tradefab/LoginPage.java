package munik.androidprojects.tradefab;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity{
    public EditText mUsername,mPassword;
    public Button mButton;
    public TextView mSignup,mForgot_password;
    public ProgressBar mProgressBar;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mUsername=findViewById(R.id.username);
        mPassword=findViewById(R.id.password);
        mButton=findViewById(R.id.signin);
        mSignup=findViewById(R.id.signup);
        mForgot_password=findViewById(R.id.forgotpassword);
        mProgressBar=findViewById(R.id.progressBar);
        fAuth=FirebaseAuth.getInstance();
        //handelingClicks
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=mUsername.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                //check whether the usename and password are empty or not?
                if(TextUtils.isEmpty(username)) {
                    mUsername.setError("username is required");
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    mPassword.setError("password is required");
                    return;
                }
                if(password.length()<6)
                {
                    mPassword.setError("the password must be more than 6 charaters");
                    return;
                }
                mProgressBar.setVisibility(View.VISIBLE);
                //programme for FireBase
                fAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginPage.this,"Loggid in successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),RoleSelectionPage.class));
                        }else{
                            Toast.makeText(LoginPage.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            mProgressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowingDialogForChoice showdialog=new ShowingDialogForChoice();
                showdialog.show(getSupportFragmentManager(),"");

            }
        });
        mForgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                take_to_forgot_password();
            }
        });
    }
    public void take_to_Merchant_Login(){
        Intent intent=new Intent(this,SignupForMerchant.class);
        startActivity(intent);
    }
    public void take_to_buyer_Login(){
        Intent intent=new Intent(this,SignupForByer.class);
        startActivity(intent);
    }
    public void take_to_forgot_password() {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}