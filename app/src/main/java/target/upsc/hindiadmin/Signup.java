package target.upsc.hindiadmin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextInputLayout textInputName, textInputEmail, textInputPhoneNo;
    private String uname, email, phoneno, aim;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    public ProgressBar mProgressBar;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        mAuth=FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference("Users");
        textInputName = findViewById(R.id.edtext);
        textInputEmail = findViewById(R.id.edtext2);
        textInputPhoneNo = findViewById(R.id.edtext4);
        spinner=findViewById(R.id.spinner);
        mProgressBar=findViewById(R.id.progress_bar);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource
                (this,R.array.PrepareFor,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    public boolean register(final View View) {

        uname = textInputName.getEditText().getText().toString().trim();
        email = textInputEmail.getEditText().getText().toString().trim();
        phoneno = textInputPhoneNo.getEditText().getText().toString().trim();



        if (uname.isEmpty()) {
            textInputName.setError("Field can't be empty");
            return false;
        }
        else {
            textInputName.setError(null);
        }
        if (uname.length()<3||uname.length()>20) {
            textInputName.setError("Name must have 3-20 characters");
            return false;
        }
        else {
            textInputName.setError(null);
        }

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
            textInputPhoneNo.setError("Incorrect MobileNo");
            return false;
        }
        else {
            textInputPhoneNo.setError(null);
        }






        mProgressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,phoneno)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            UserDetails userDetails=new UserDetails(uname,email,aim,phoneno);
                            userDetails.setUid(mAuth.getCurrentUser().getUid());
                            mRef.child(mAuth.getCurrentUser().getUid()).setValue(userDetails);
                            textInputEmail.getEditText().setText("");
                            textInputName.getEditText().setText("");
                            textInputPhoneNo.getEditText().setText("");
                            Toast.makeText(Signup.this,"Register Successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Signup.this, Login.class);
                            startActivity(intent);
                            mAuth.signOut();
                            finish();

                        }
                        else
                        {
                            mProgressBar.setVisibility(View.INVISIBLE);
                            textInputEmail.getEditText().setText("");
                            Toast.makeText(Signup.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });

        return  true;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        aim=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
