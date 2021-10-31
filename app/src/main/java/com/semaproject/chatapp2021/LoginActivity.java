package com.semaproject.chatapp2021;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends Activity {

    EditText etResisterID, etResisterPassword;

    private FirebaseAuth mAuth;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        etResisterID = (EditText) findViewById(R.id.etResisterID);
        etResisterPassword = (EditText) findViewById(R.id.etResisterPassWord);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_2);

        Button btSkipResister = (Button) findViewById(R.id.btSkipResister);

        btSkipResister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TutorialActivity.class);
                startActivity(intent);
            }
        });

        Button btRegisterFinish = (Button) findViewById(R.id.btRegisterFinish);
        btRegisterFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stEmail = etResisterID.getText().toString();
                String stPassword = etResisterPassword.getText().toString();


                if (stEmail.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "이메일을 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                if (stPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "패스워드를 입력해주세요", Toast.LENGTH_LONG).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(stEmail, stPassword)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //  Log.d(TAG, "createUserWithEmail:success");
                                    Toast.makeText(LoginActivity.this, "회원가입, 성공!",
                                            Toast.LENGTH_LONG).show();
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Intent in = new Intent(LoginActivity.this, TutorialActivity.class);
                                    in.putExtra("이메일: ", stEmail);
                                    startActivity(in);
//                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //              Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "회원가입에 실패하셨습니다.",
                                            Toast.LENGTH_SHORT).show();
//                                    updateUI(null);
                                }

                                // ...
                            }
                        });

            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//            updateUI(currentUser);
    }
}