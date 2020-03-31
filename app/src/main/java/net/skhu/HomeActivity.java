package net.skhu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    public static final String tag="myTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        setTitle("메인");
    }
    public void btnClick(View view) {
        if (view.getId() == R.id.btn) {
            //btn,searching
            Log.d("tag","seraching 버튼 눌렀어");
            EditText searchingText = (EditText) findViewById(R.id.searching);
            String searching = searchingText.getText().toString();
            Log.d("tag","검색:"+searching);

            new SongSearch(this).execute(searching);
            //Intent intent = new Intent(this, SearchingSongViewActivity.class);
            //intent.putExtra("result",songSearch.getSearchingResult()); /*송신*/
            //startActivity(intent);

        }

    }
}
