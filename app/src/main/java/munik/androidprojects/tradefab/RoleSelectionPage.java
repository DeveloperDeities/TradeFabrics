package munik.androidprojects.tradefab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class RoleSelectionPage extends AppCompatActivity {
    ImageView merchantPageviewRequired;
    ImageView buyerPageviewRequired;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection_page);
        merchantPageviewRequired=findViewById(R.id.merchantimagetosell);
        buyerPageviewRequired=findViewById(R.id.userpageforbuying);
        merchantPageviewRequired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMerchantPage();
            }
        });
        buyerPageviewRequired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBuyerPage();
            }
        });
    }


    public void logout(View v){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LoginPage.class));
        finish();
    }
    public void toBuyerPage(){
        Intent intent = new Intent(this,BuyerPage.class);
        startActivity(intent);
        finish();
    }
    public void toMerchantPage(){
        Intent intent = new Intent(this,SellerPage.class);
        startActivity(intent);
        finish();
    }
}
