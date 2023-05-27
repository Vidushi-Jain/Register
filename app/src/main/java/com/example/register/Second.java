package com.example.register;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.register.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Second extends AppCompatActivity {

    EditText e1,editText;
    Button b1,button;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText) findViewById(R.id.editTextTextPersonName);
        editText=(EditText) findViewById(R.id.editTextTextPersonName2);
        editText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        b1=(Button) findViewById(R.id.button);
        button=(Button) findViewById(R.id.button2);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Second.this,Third.class);
                startActivity(intent);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=e1.getText().toString();
                String s2=editText.getText().toString();
                if(s1.isEmpty())
                {
                    e1.setError("Fill email id");
                    return;
                }
                else{
                    if(s2.isEmpty())
                    {
                        editText.setError("Fill Error");
                        return;
                    }
                    else {
                        progressBar.setVisibility(View.VISIBLE);
                        firebaseAuth.createUserWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(Second.this,"Database updated",Toast.LENGTH_SHORT);
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Intent k=new Intent(Second.this, Third.class);
                                    startActivity(k);
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}