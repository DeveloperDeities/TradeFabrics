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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupForByer extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_for_byer);
        final EditText mName=(EditText)findViewById(R.id.name);
        final EditText mEmail=(EditText)findViewById(R.id.email);
        final EditText mPassword=(EditText)findViewById(R.id.passowrd);
        final EditText mConfirm_password=(EditText)findViewById(R.id.confirmpassword);
        final EditText mPhone=(EditText)findViewById(R.id.phone);
        final Button mButton=(Button)findViewById(R.id.register);
        final ProgressBar mProgressBar2=(ProgressBar)findViewById(R.id.progressBar2);
        mProgressBar2.setVisibility(View.INVISIBLE);
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        mProgressBar2.setVisibility(View.INVISIBLE);
        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),LoginPage.class));
            finish();
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final  String name=mName.getText().toString().trim();
               final String e_mail=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                String confirm_password=mConfirm_password.getText().toString().trim();
              final  String phone=mPhone.getText().toString().trim();

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
                //programme for FireBase
                fAuth.createUserWithEmailAndPassword(e_mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupForByer.this,"user created",Toast.LENGTH_SHORT).show();
                            userId=fAuth.getCurrentUser().getUid();
                            //putting other data like name ,email etc into the fire base collection name users
                            DocumentReference documentReference=fstore.collection("users").document(userId);
                            Map<String,Object> user=new HashMap<>();
                            user.put("name",name);
                            user.put("E-mail",e_mail);
                            user.put("PhoneNo",phone);
                            user.put("bankAccountNO","");
                            user.put("IFCS code","");
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.i("info","on success:user  profile is created"+userId);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),LoginPage.class));
                        }else{
                            Toast.makeText(SignupForByer.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            mProgressBar2.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();


        startActivity(new Intent(this,LoginPage.class));
    }
}
