package target.upsc.hindiadmin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class Login extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private TextInputLayout textInputEmail,textInputPhoneNo;
    private String email,phoneno;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth=FirebaseAuth.getInstance();

    }
    public boolean login(View view) {

        textInputEmail = findViewById(R.id.edtext);
        textInputPhoneNo = findViewById(R.id.edtext2);
        mProgressBar=findViewById(R.id.progress_bar);
        email = textInputEmail.getEditText().getText().toString().trim();
        phoneno = textInputPhoneNo.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        }
        else {
            textInputEmail.setError(null);
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textInputEmail.setError("Incorrect Email");
            return false;
        }
        else {
            textInputEmail.setError(null);
        }

        if (phoneno.isEmpty()) {
            textInputPhoneNo.setError("Field can't be empty");
            return  false;
        }
        else
        {
            textInputPhoneNo.setError(null);
        }
        if (phoneno.length()!=10) {
            textInputPhoneNo.setError("Incorrect Mobile No");
            return false;
        }
        else {
            textInputPhoneNo.setError(null);
        }
        mProgressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,phoneno)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            mProgressBar.setVisibility(View.INVISIBLE);
                            FirebaseMessaging.getInstance().subscribeToTopic(mAuth.getCurrentUser().getUid());
                            FirebaseMessaging.getInstance().subscribeToTopic("user");
                            Intent intent = new Intent(Login.this, AdminPanel.class);
                            startActivity(intent);
                            finish();



                        }
                        else
                        {
                            mProgressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(Login.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                        textInputEmail.getEditText().setText("");
                        textInputPhoneNo.getEditText().setText("");
                    }
                });

        return  true;
    }
    public  void signup(View view)
    {
        Intent intent=new Intent(this, Signup.class);
        startActivity(intent);

    }
}
