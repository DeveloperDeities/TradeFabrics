package munik.androidprojects.tradefab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    Button resetpass;
    EditText username;
    EditText phoneno;
    EditText email;
    ProgressBar progressBarforgotpass;
    String emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        resetpass=findViewById(R.id.otpForgotPassGen);
        progressBarforgotpass=findViewById(R.id.progressBarForgotpassk);
        username = findViewById(R.id.name_forgot_password);
        phoneno = findViewById(R.id.phone_forgot_password);
        email = findViewById(R.id.email_forgot_password);
        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {emailAddress = email.getText().toString().trim();
                progressBarforgotpass.setVisibility(View.VISIBLE);
                sendmail();
            }
        });
    }

    private void sendmail() {
        //page to link  for otp verification
        //this sends mail to user for resetting the password
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // do something when mail was sent successfully.
                            Toast.makeText(ForgotPassword.this, "recovery mail sent", Toast.LENGTH_SHORT).show();
                            progressBarforgotpass.setVisibility(View.INVISIBLE);
                        } else {
                            // ...
                        }
                    }
                });
    }
}
