package munik.androidprojects.tradefab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public TextView welcomebye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer time=new Timer();
        welcomebye=findViewById(R.id.welcomebyetext);
        time.schedule(new TimerTask(){

            @Override
            public void run() {
                goTologinPage();
            }
        },1500);
    }

    private void goTologinPage() {
        Intent intend=new Intent(this,LoginPage.class);
        startActivity(intend);
        finish();
    }
}
