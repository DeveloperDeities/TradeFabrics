package munik.androidprojects.tradefab;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword extends AppCompatActivity {
    Button resetpass;
    ProgressBar progressBarforgotpass;
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
                progressBarforgotpass.setVisibility(View.VISIBLE);
            }
        });
    }
}
