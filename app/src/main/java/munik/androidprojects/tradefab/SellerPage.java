package munik.androidprojects.tradefab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.view.ActionMode;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class SellerPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnLongClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;
     //private SharedPreferences mPrefer;
    String list;
    private List<Person> noteList;
    private List<Person> noteList1;

    boolean isContextualModeUnable=false;
   // String tem;
   // DocumentReference documentReference;
   private RecyclerView recyclerView;
    private NoteAdapter mAdapter;
    private ActionMode mActionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogForMerchantsPuttingData showdialog=new ShowDialogForMerchantsPuttingData();
                showdialog.show(getSupportFragmentManager(),"");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //for setting the name and email in the nevigation activity
        View headerView=navigationView.getHeaderView(0);
       final TextView name=headerView.findViewById(R.id.item);
       final TextView email=headerView.findViewById(R.id.emailid);
        //email=(TextView)findViewById(R.id.emailid);
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        //initializing the present user id
        userId=fAuth.getCurrentUser().getUid();
        DocumentReference documentReference=fstore.collection("users").document(userId);
        if(documentReference==null)
            Toast.makeText(SellerPage.this,"Loggid in successfully",Toast.LENGTH_SHORT).show();
        else{
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                name.setText((documentSnapshot.getString("name")));
                email.setText((documentSnapshot.getString("E-mail")));
                list=documentSnapshot.getString("list of items to be selled");
                if(list.length()>2){
                recyclerView = (RecyclerView)
                        findViewById(R.id.recyclerView);
                noteList=new ArrayList<>();
                String k=list.substring(0,list.length()-1);
                String h="";
                for(int i=1;i<k.length();i++){
                    h=h+k.charAt(i);
                }
                String[] parts=h.split("],");
                for(int i=0;i<parts.length;i++){
                    String cv="";
                    for(int j=1;j<parts[i].length();j++){
                        cv=cv+parts[i].charAt(j);
                    }
                    String[] parts1=cv.split(",");
                    Person person=new Person(parts1[0],parts1[1],parts1[2],parts1[3]);
                    noteList.add(person);
                }
               abc(noteList);

                //String a=documentSnapshot.getString("name");
                //String b=documentSnapshot.getString("E-mail");
                //mEditor.putString("NAME",a);
                //mEditor.putString("EMAIL",b);
                // mEditor.commit();
            }}
        });}

        //mPrefer=getSharedPreferences("Login",MODE_PRIVATE);
        // TextView name=(TextView)findViewById(R.id.name);
        // TextView email_id=(TextView)findViewById(R.id.emailid);
        // name.setText(mPrefer.getString("NAME","abc"));
         //email_id.setText(mPrefer.getString("EMAIL","ABC"));
        //initializing the present user id
        // tem=return_list();
        noteList1=new ArrayList<>();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.seller_page, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           // super.onBackPressed();
            return;
        }
    }
   /* @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        int id = item.getItemId();
        if (id == R.id.nav_logout) {
            // Create a new fragment of the appropriate type
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this,LoginPage.class));
            finish();
        }



        transaction.addToBackStack(null);

        // Implement the change
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
   public String return_list(){
        return list;
   }
   public void addition(String a){
       Map<String,Object> user=new HashMap<>();
       user.put("list of items to be selled",a);
       DocumentReference documentReference=fstore.collection("users").document(userId);
       documentReference.set(user, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
               Log.i("info","documents upDated");
           }
       });
   }
   public void abc(List<Person> y){
       mAdapter = new NoteAdapter(this,y);
       RecyclerView.LayoutManager mLayoutManager =
               new LinearLayoutManager(getApplicationContext());
       recyclerView.setLayoutManager(mLayoutManager);
       recyclerView.setItemAnimator(new DefaultItemAnimator());

// set the adapter
       recyclerView.setAdapter(mAdapter);
       recyclerView.addItemDecoration(
               new DividerItemDecoration(
                       this, LinearLayoutManager.VERTICAL));
   }
   @Override
    public boolean onLongClick(View v){
        if(mActionMode!=null){
            return false;
        }

        mActionMode=startSupportActionMode(mActionModecallback);

                return true;
   }
   private ActionMode.Callback mActionModecallback=new ActionMode.Callback() {
       @Override
       public boolean onCreateActionMode(ActionMode mode, Menu menu) {
           mode.getMenuInflater().inflate(R.menu.example_menu,menu);
           mode.setTitle("Choose your option");
           isContextualModeUnable=true;
           mAdapter.notifyDataSetChanged();
           return true;
       }

       @Override
       public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
           return false;
       }

       @Override
       public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
           switch(item.getItemId()){
               case R.id.delete_option:
                   Toast.makeText(SellerPage.this,"Option 1 selected",Toast.LENGTH_SHORT).show();
                   mAdapter.removeItem(noteList1);
                   addition(mAdapter.removal());
                   mode.finish();
                   return true;
               default:
                   return false;
           }

       }

       @Override
       public void onDestroyActionMode(ActionMode mode) {
           mActionMode=null;
           isContextualModeUnable=false;
           mAdapter.notifyDataSetChanged();
       }
   };

    public void MAkeSelectItem(View v, int adapterPosition) {
        if(((CheckBox)v).isChecked()){
            noteList1.add(noteList.get(adapterPosition));
        }
        else{
            noteList1.remove(noteList.get(adapterPosition));
        }
    }

}
