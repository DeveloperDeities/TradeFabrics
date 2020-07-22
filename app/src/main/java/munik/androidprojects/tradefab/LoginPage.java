package munik.androidprojects.tradefab;

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

    }
}