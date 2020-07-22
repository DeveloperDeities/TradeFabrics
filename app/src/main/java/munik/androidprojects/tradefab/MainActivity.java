package munik.androidprojects.tradefab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer time=new Timer();
        time.schedule(new TimerTask(){

            @Override
            public void run() {
                goTologinPage();
            }
        },3000);
    }

    private void goTologinPage() {
        Intent intend=new Intent(this,LoginPage.class);
        startActivity(intend);
    }
}
