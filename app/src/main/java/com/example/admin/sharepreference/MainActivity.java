package com.example.admin.sharepreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.sharepreference.data.local.dao.UserDao;
import com.example.admin.sharepreference.data.local.room.MyDatabase;
import com.example.admin.sharepreference.data.local.room.entity.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText edname,edpass;
    UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edname=findViewById(R.id.edname);
        edpass=findViewById(R.id.edpass);

        //get user Dao
        userDao=MyDatabase.getDatabase(this).getUserDao();

        findViewById(R.id.btnsave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences=getSharedPreferences("USER",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();

                editor.putString("name",edname.getText().toString());
                editor.putString("pass",edpass.getText().toString());
                editor.apply();
                edname.setText("");
                edpass.setText("");
            }
        });

        findViewById(R.id.btnshow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences=getSharedPreferences("USER",MODE_PRIVATE);
                String name=sharedPreferences.getString("name","");
                String pass=sharedPreferences.getString("pass","");
                edname.setText(name);
                edpass.setText(pass);
            }
        });

    }

    void saveUser(View view){
        User user=new User(edname.getText().toString(),
                            edpass.getText().toString());

        userDao.save(user);
        Toast.makeText(this, "saved success!", Toast.LENGTH_SHORT).show();
    }

    private static final String TAG = "MainActivity";
    void getUser(View view){
        List<User> users=userDao.getUser();
        Log.e(TAG, "getUser: "+users.toString() );
    }
}
