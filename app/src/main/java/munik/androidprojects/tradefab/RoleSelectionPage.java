package munik.androidprojects.tradefab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class RoleSelectionPage extends AppCompatActivity {
    ImageView merchantPageviewRequired;
    ImageView buyerPageviewRequired;
    //declaring the instance variable of FirebaseAuth and FirebaseFirestore type
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    //id is required to retrive data
    String userId;
    public boolean z;
    int check=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection_page);

        merchantPageviewRequired=findViewById(R.id.merchantimagetosell);
        buyerPageviewRequired=findViewById(R.id.userpageforbuying);
        //initializing FirebaseAuth and FirebaseFirestore fields
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        //initializing the present user id
        userId=fAuth.getCurrentUser().getUid();
//retriving data
        DocumentReference documentReference=fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if((documentSnapshot.getString("bankAccountNO").equals("")&&(documentSnapshot.getString("IFCS code")).equals(""))){
                    Log.i("info","error occured");
                check=1;}
            }
        });
        merchantPageviewRequired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check==0)
                toMerchantPage();
                else{
                  //  Toast.makeText(RoleSelectionPage.this,"Error !",Toast.LENGTH_SHORT).show();
                    ShowingDialogForErrorMssege showingDialogForErrorMssege=new ShowingDialogForErrorMssege();
                showingDialogForErrorMssege.show(getSupportFragmentManager(),"123");
                }
            }
        });
       /*buyerPageviewRequired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBuyerPage();
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        logout();
        super.onBackPressed();
}

    public void logout(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LoginPage.class));
        finish();
    }
   /* public void toBuyerPage(){
        Intent intent = new Intent(this,BuyerPage.class);
        startActivity(intent);
        finish();
    }*/
    public void toMerchantPage(){
        Intent intent = new Intent(this,SellerPage.class);
        startActivity(intent);
        finish();
    }

}
