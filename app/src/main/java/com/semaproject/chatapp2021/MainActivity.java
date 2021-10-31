package com.semaproject.chatapp2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    EditText etId, etPassword;
    private FirebaseAuth mAuth;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        etId = (EditText) findViewById(R.id.etId);
        etPassword = (EditText) findViewById(R.id.etPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        Button btLogin = (Button) findViewById(R.id.btLogin);
//        Button btMoveToChat = (Button) findViewById(R.id.btMoveToChat);
//        Button btResister = (Button) findViewById(R.id.btRegister);
//        Button btOpenExcel = (Button) findViewById(R.id.btOpenExcel);


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stEmail = etId.getText().toString();
                String stPassword = etPassword.getText().toString();
                if (stEmail.isEmpty()) {
                    Toast.makeText(MainActivity.this, "이메일을 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if (stPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "패스워드를 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(stEmail, stPassword)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    // Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    String stUserEmail = user.getEmail();
                                    String stUserName = user.getDisplayName();
                                    // Log.d(TAG, "stUserEmail: "+stUserEmail+"stUserName: "+stUserName);

                                    SharedPreferences sharedPref = getSharedPreferences("shared", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putString("email", stUserEmail);
                                    editor.commit();

                                    Intent in = new Intent(MainActivity.this, TapActivity.class);
                                    in.putExtra("email", stEmail);
                                    startActivity(in);
//                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //      Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "로그인에 실패하였습니다.",
                                            Toast.LENGTH_SHORT).show();
//                                    updateUI(null);
                                }
                            }
                        });

//                Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_LONG).show();

            }
        });


    }
}
