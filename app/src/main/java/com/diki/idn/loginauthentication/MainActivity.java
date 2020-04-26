package com.diki.idn.loginauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ImageView ivProfile;
    TextView tvName, tvEmail;
    FirebaseAuth mAuth;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        ivProfile = findViewById(R.id.iv_profile);
        tvName = findViewById(R.id.tv_name_profile);
        tvEmail = findViewById(R.id.tv_email_profile);
        logout = findViewById(R.id.btn_log_out);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signout();
            }
        });
        Glide.with(this).load(user.getPhotoUrl()).into(ivProfile);
        tvName.setText(user.getDisplayName());
        tvEmail.setText(user.getEmail());
    }

    private void signout() {
        mAuth.getInstance().signOut();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null){
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
