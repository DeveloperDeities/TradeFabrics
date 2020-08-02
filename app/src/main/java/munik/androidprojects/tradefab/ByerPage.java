package munik.androidprojects.tradefab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class ByerPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;
    String temp="";
    private List<Person> noteList;
    private RecyclerView recyclerView;
    private NoteAdapter1 mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byer_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
        final TextView name=headerView.findViewById(R.id.name2);
        final TextView email=headerView.findViewById(R.id.email2);
        //email=(TextView)findViewById(R.id.emailid);
        fAuth=FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();
        //initializing the present user id
        userId=fAuth.getCurrentUser().getUid();
        DocumentReference documentReference=fstore.collection("users").document(userId);
        //if(documentReference==null)
          //  Toast.makeText(SellerPage.this,"Loggid in successfully",Toast.LENGTH_SHORT).show();
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                name.setText((documentSnapshot.getString("name")));
                email.setText((documentSnapshot.getString("E-mail")));


                //String a=documentSnapshot.getString("name");
                //String b=documentSnapshot.getString("E-mail");
                //mEditor.putString("NAME",a);
                //mEditor.putString("EMAIL",b);
                // mEditor.commit();
            }
        });
        abcd();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.byer_page, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
          //  super.onBackPressed();
            return;
        }
    }
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
    public void abcd(){
        fstore.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               // Log.i("info", document.getId() + " => " + document.getData());
                                Map<String, Object> b=document.getData();
                               // b.get("list of items to be selled");
                                Object temp1=b.get("list of items to be selled");
                                String sc=(String)temp1;
                                if(sc!=null){
                                    if(sc.length()>2)
                                    {
                                        String jl=sc.substring(0,sc.length()-1);
                                        String h="";
                                        for(int j=1;j<jl.length();j++){
                                            h=h+jl.charAt(j);
                                        }
                                        temp=temp+h+",";
                                    }
                                }

                            }
                            Log.i("info","list"+temp);

                            recyclerView = (RecyclerView)
                                    findViewById(R.id.recyclerView1);
                            noteList=new ArrayList<>();
                            String[] parts=temp.split("],");
                            for(int i=0;i<parts.length;i++){
                                String cv="";
                                for(int j=1;j<parts[i].length();j++){
                                    cv=cv+parts[i].charAt(j);
                                }
                                String[] parts1=cv.split(",");
                                Person person=new Person(parts1[0],parts1[1],parts1[2],parts1[3]);
                                noteList.add(person);
                            }
                            if(noteList.isEmpty())
                                Log.i("info","error error");
                            abcde(noteList);

                        } else {
                            Log.i("info", "Error getting documents.", task.getException());
                        }
                    }
                });

    }
    public void abcde(List<Person> y){
        mAdapter = new NoteAdapter1(this,y);
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
}
