package target.upsc.hindiadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddQuize extends AppCompatActivity {
    private TextInputLayout textInputQuestion,textInputOption1,textInputOption2,
            textInputOption3,textInputOption4,textInputAnswer,textInputExplanation;
    private String question,option1,option2,option3,option4,answer,explanation;
    private DatabaseReference mRef;
    private ProgressBar mProgressBar;
    String key1,key2,key3,key4;
    SimpleDateFormat currentTime,currentDate;
    Calendar calendar;
    String uploadTime,uploadDate,uploadSamay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_quize);
        Intent intent=getIntent();
        key1=intent.getStringExtra("key1");
        key2=intent.getStringExtra("key2");
        key3=intent.getStringExtra("key3");
        key4=intent.getStringExtra("key4");

        mRef= FirebaseDatabase.getInstance().getReference().child("Quize").child(key1).child(key2).child(key3).child(key4);

        mProgressBar=findViewById(R.id.progress_bar);
        textInputQuestion = findViewById(R.id.edtext1);
        textInputOption1 = findViewById(R.id.edtext2);
        textInputOption2 = findViewById(R.id.edtext3);
        textInputOption3 = findViewById(R.id.edtext4);
        textInputOption4= findViewById(R.id.edtext5);
        textInputAnswer = findViewById(R.id.edtext6);
        textInputExplanation = findViewById(R.id.edtext7);
        calendar=Calendar.getInstance();
        currentTime=new SimpleDateFormat("HH:mm:ss a");
        currentDate= new SimpleDateFormat("dd MMM, yyyy | ");
    }
    public void Upload(View view) {

        question = textInputQuestion.getEditText().getText().toString().trim();
        option1=textInputOption1.getEditText().getText().toString().trim();
        option2=textInputOption2.getEditText().getText().toString().trim();
        option3=textInputOption3.getEditText().getText().toString().trim();
        option4=textInputOption4.getEditText().getText().toString().trim();
        answer=textInputAnswer.getEditText().getText().toString().trim();
        explanation=textInputExplanation.getEditText().getText().toString().trim();

        if (question.isEmpty()) {
            textInputQuestion.setError("Field can't be empty");
            return ;
        }
        else {
            textInputQuestion.setError(null);

        }
        if (option1.isEmpty()) {
            textInputOption1.setError("Field can't be empty");
            return ;
        }
        else {
            textInputOption1.setError(null);

        }
        if (option2.isEmpty()) {
            textInputOption2.setError("Field can't be empty");
            return ;
        }
        else {
            textInputOption2.setError(null);

        }
        if (option3.isEmpty()) {
            textInputOption3.setError("Field can't be empty");
            return ;
        }
        else {
            textInputOption3.setError(null);

        }
        if (option3.isEmpty()) {
            textInputOption3.setError("Field can't be empty");
            return ;
        }
        else {
            textInputOption3.setError(null);

        }
        if (option4.isEmpty()) {
            textInputOption4.setError("Field can't be empty");
            return ;
        }
        else {
            textInputOption4.setError(null);

        }
        if (answer.isEmpty()) {
            textInputAnswer.setError("Field can't be empty");
            return ;
        }
        else {
            textInputAnswer.setError(null);

        }
        if (explanation.isEmpty()) {
            textInputExplanation.setError("Field can't be empty");
            return ;
        }
        else {
            textInputExplanation.setError(null);
            mProgressBar.setVisibility(View.VISIBLE);
            SaveProductInfoToDb();
            textInputQuestion.getEditText().setText("");
            textInputOption1.getEditText().setText("");
            textInputOption2.getEditText().setText("");
            textInputOption3.getEditText().setText("");
            textInputOption4.getEditText().setText("");
            textInputAnswer.getEditText().setText("");
            textInputExplanation.getEditText().setText("");

        }



    }

    private void SaveProductInfoToDb() {
        uploadDate=currentDate.format(calendar.getTime());
        uploadTime=currentTime.format(calendar.getTime());
        uploadSamay=uploadDate+" "+uploadTime;

        QuizeInfo quizeInfo=new QuizeInfo(question,option1,option2,option3,option4,answer,explanation);

        long time=System.currentTimeMillis();
        quizeInfo.setPid(String.valueOf(time));

        mRef.child(String.valueOf(time)).setValue(quizeInfo);

        mProgressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(AddQuize.this,"Uploaded",Toast.LENGTH_LONG).show();


    }
}
