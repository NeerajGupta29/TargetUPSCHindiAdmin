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

public class AddVideo extends AppCompatActivity {
    private TextInputLayout textInputHeadLine, textInputVideoId;
    private String headLine,videoId,uploadTime,uploadDate,uploadSamay,key;
    private DatabaseReference mRef;
    private ProgressBar mProgressBar;
    SimpleDateFormat currentTime,currentDate;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_video);
        Intent intent=getIntent();
        key = intent.getStringExtra("key");
        calendar=Calendar.getInstance();
        currentTime=new SimpleDateFormat("HH:mm:ss a");
        currentDate= new SimpleDateFormat("dd MMM, yyyy | ");

        mProgressBar=findViewById(R.id.progress_bar);
        textInputHeadLine = findViewById(R.id.edtext);
        textInputVideoId=findViewById(R.id.edtext2);
        mRef= FirebaseDatabase.getInstance().getReference().child("YoutubeVideo").child(key);

    }
    public void Upload(View view) {
        headLine = textInputHeadLine.getEditText().getText().toString().trim();
        videoId = textInputVideoId.getEditText().getText().toString().trim();

        if (headLine.isEmpty()) {
            textInputHeadLine.setError("Field can't be empty");
            return ;
        }
        else {
            textInputHeadLine.setError(null);
            textInputHeadLine.getEditText().setText("");
        }
        if (videoId.isEmpty()) {
            textInputVideoId.setError("Field can't be empty");
            return ;
        }
        else {
            textInputVideoId.setError(null);
            mProgressBar.setVisibility(View.VISIBLE);
            SaveProductInfoToDb();
            textInputHeadLine.getEditText().setText("");
            textInputVideoId.getEditText().setText("");
        }


    }
    private void SaveProductInfoToDb()
    {
        uploadDate=currentDate.format(calendar.getTime());
        uploadTime=currentTime.format(calendar.getTime());
        uploadSamay=uploadDate+" "+uploadTime;

        YoutubeListInfo youtubeListInfo=new YoutubeListInfo(headLine,videoId,uploadSamay);

        long time=System.currentTimeMillis();
        youtubeListInfo.setPid(String.valueOf(time));
        mRef.child(String.valueOf(time)).setValue(youtubeListInfo);
        mProgressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(AddVideo.this,"Uploaded",Toast.LENGTH_SHORT).show();

    }
}
