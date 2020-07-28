package munik.androidprojects.tradefab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public  class SellerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_page);

    }
    @Override
    public void onBackPressed() {
        logout();
        super.onBackPressed();
    }
    public void logout(){
        startActivity(new Intent(getApplicationContext(),LoginPage.class));
        FirebaseAuth.getInstance().signOut();
        finish();
    }

}
