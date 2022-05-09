package com.example.quan_ly_cham_cong.sign_in;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.quan_ly_cham_cong.R;
import com.example.quan_ly_cham_cong.databinding.ActivitySignInBinding;
import com.example.quan_ly_cham_cong.main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    private FirebaseAuth mAth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        mAth = FirebaseAuth.getInstance();

        binding.dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(binding.mail.getText().toString(), binding.mk.getText().toString());
            }
        });
    }

    private  void signUp(String email, String mk){

        if(email.isEmpty() || mk.isEmpty()){
            Toast.makeText(this, "Bạn chưa nhập tài khoản mật khẩu!", Toast.LENGTH_SHORT).show();
        }else {
            mAth.signInWithEmailAndPassword(email,mk).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"mk sai",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

}
