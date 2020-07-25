package munik.androidprojects.tradefab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupForMerchant extends AppCompatActivity {
   FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_for_merchant);
        final EditText mName=(EditText)findViewById(R.id.name);
        final EditText mEmail=(EditText)findViewById(R.id.email);
        final EditText mPassword=(EditText)findViewById(R.id.passowrd);
        final EditText mConfirm_password=(EditText)findViewById(R.id.confirmpassword);
        final EditText mPhone=(EditText)findViewById(R.id.phone);
        final EditText mBank_account=(EditText)findViewById(R.id.bankaccount);
        final EditText mIFCS=(EditText)findViewById(R.id.ifcscode);
        final Button mButton=(Button)findViewById(R.id.register);
        final ProgressBar mProgressBar2=(ProgressBar)findViewById(R.id.progressBar2);
        fAuth=FirebaseAuth.getInstance();
        mProgressBar2.setVisibility(View.INVISIBLE);
        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),LoginPage.class));
            finish();
        }
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mName.getText().toString().trim();
                String e_mail=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                String confirm_password=mConfirm_password.getText().toString().trim();
                String phone=mPhone.getText().toString().trim();
                String bankAccount=mBank_account.getText().toString().trim();
                String ifcs_code=mIFCS.getText().toString().trim();
                //check whether the usename and password are empty or not?
                if((TextUtils.isEmpty(name))||(TextUtils.equals(name,"Name"))) {
                    mName.setError("name is required");
                    return;
                }
                if((TextUtils.isEmpty(password))||(TextUtils.equals(password,"Password"))) {
                    mPassword.setError("password is required");
                    return;
                }
                if((TextUtils.isEmpty(e_mail))||(TextUtils.equals(e_mail,"E-mail"))) {
                    mEmail.setError("email is required");
                    return;
                }
                if((TextUtils.isEmpty(confirm_password))||(TextUtils.equals(confirm_password,"E-mail"))) {
                    mConfirm_password.setError("confirm password is required");
                    return;
                }
                if((TextUtils.isEmpty(phone))||(TextUtils.equals(phone,"E-mail"))) {
                    mPhone.setError("phone is required");
                    return;
                }
                if((TextUtils.isEmpty(bankAccount))||(TextUtils.equals(bankAccount,"E-mail"))) {
                    mBank_account.setError("bankAccount is required");
                    return;
                }
                if((TextUtils.isEmpty(ifcs_code))||(TextUtils.equals(ifcs_code,"E-mail"))) {
                    mIFCS.setError("ifcs code is required");
                    return;
                }
                if(password.length()<6)
                {
                    mPassword.setError("the password must be more than 6 charaters");
                    return;
                }
                if(password.equals(confirm_password))
                {
                    Log.i("info","password matched");
                }
                else{
                    mPassword.setError("the password and the confirm password mismatched");
                    mConfirm_password.setError("the password and the confirm password mismatched");
                    return;
                }
                mProgressBar2.setVisibility(View.VISIBLE);
                //programme for FireBase
                fAuth.createUserWithEmailAndPassword(e_mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupForMerchant.this,"user created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginPage.class));
                        }else{
                            Toast.makeText(SignupForMerchant.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            mProgressBar2.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}
