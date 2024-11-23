package com.example.test_app_basketball;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {

    EditText emailAddress;
    EditText password;
    FirebaseAuth auth;
    Intent GPS, Video, Registration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Registration = new Intent(this, Registration.class);
        Video = new Intent(this, Video.class);
        GPS = new Intent(this, GPS_Cordinates.class);
        emailAddress = findViewById(R.id.EmailAddress);
        password = findViewById(R.id.Password);
        auth = FirebaseAuth.getInstance();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String st = item.getTitle().toString();

        if (st.equals("Registration")) {
            startActivity(Registration);
        } else if (st.equals("Video")) {
            startActivity(Video);
        } else if (st.equals("GPS_SPOT")) {
            startActivity(GPS);
        } else if(st.equals("Sign_Places")){
             Intent intent = new Intent(this, Sign_Places.class);
             startActivity(intent);
        }else if(st.equals("Signs2")){
             /*Intent intent = new Intent(this, Signs2.class);
             startActivity(intent);*/
        }

        return super.onContextItemSelected(item);
    }



    public void SignInUp(View view) {
        auth.signInWithEmailAndPassword(emailAddress.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "signedUp", Toast.LENGTH_SHORT).show();
                }
                else {
                    SignUp();
                }
            }
        });
    }
    public void SignUp()
    {
        auth.createUserWithEmailAndPassword(emailAddress.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "signedUp", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
