package munik.androidprojects.tradefab;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity{
    public EditText mUsername,mPassword;
    public Button mButton;
    public TextView mSignup,mForgot_password;
    public ProgressBar mProgressBar;

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
            }
        });
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowingDialogForChoice showdialog=new ShowingDialogForChoice();
                showdialog.show(getSupportFragmentManager(),"");

            }
        });
    }
    public void abc(){
        Intent intent=new Intent(this,SignupForMerchant.class);
        startActivity(intent);
    }
}