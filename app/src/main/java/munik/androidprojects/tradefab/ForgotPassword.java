package munik.androidprojects.tradefab;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    Button resetpass;
    ProgressBar progressBarforgotpass;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    String emailAddress = "user@example.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        resetpass=findViewById(R.id.otpForgotPassGen);
        progressBarforgotpass=findViewById(R.id.progressBarForgotpassk);
        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //page to link  for otp verification
                //this sends mail to user for resetting the password
                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPassword.this, "verification link sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                progressBarforgotpass.setVisibility(View.VISIBLE);
            }
        });
    }
}
