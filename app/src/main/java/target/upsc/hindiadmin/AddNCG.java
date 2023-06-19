package target.upsc.hindiadmin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNCG extends AppCompatActivity {
    private  static final  int GalleryPicks=1;
    private Uri ImageUri;
    private TextInputLayout textInputName,textInputDesc;
    private String name,desc;
    private ImageView InputProductImage;
    private StorageReference mImageRef;
    private DatabaseReference mRef;
    private ProgressBar mProgressBar;
    String uri,uploadTime,uploadDate,uploadSamay,tag;
    SimpleDateFormat currentTime,currentDate;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ncg);
        mProgressBar=findViewById(R.id.progress_bar);
        InputProductImage=findViewById(R.id.imageView3);
        textInputName = findViewById(R.id.edtext);
        textInputDesc = findViewById(R.id.edtext2);

        calendar=Calendar.getInstance();
        currentTime=new SimpleDateFormat("HH:mm:ss a");
        currentDate= new SimpleDateFormat("dd MMM, yyyy | ");
        Intent intent=getIntent();
        tag = intent.getStringExtra("tag");

        if(tag.equals("addnews"))
        {  mRef= FirebaseDatabase.getInstance().getReference().child("News");
            mImageRef= FirebaseStorage.getInstance().getReference().child("NewsImages");
        }

        if(tag.equals("addcurrentaffair"))
        {   mRef= FirebaseDatabase.getInstance().getReference().child("CurrentAffair");
            mImageRef= FirebaseStorage.getInstance().getReference().child("CurrentAffairImages");
        }
        if(tag.equals("addgk"))
        {  mRef= FirebaseDatabase.getInstance().getReference().child("GK");
            mImageRef= FirebaseStorage.getInstance().getReference().child("GKImages");
        }



    }
    public void choose_image(View view)
    {
        openGallery();
    }
    private  void  openGallery()
    {
        Intent galleryIntent =new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GalleryPicks);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GalleryPicks && resultCode==RESULT_OK && data!=null)
        {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }
    }
    public  boolean Upload(View view)
    {   name = textInputName.getEditText().getText().toString().trim();
        desc = textInputDesc.getEditText().getText().toString().trim();



        if (name.isEmpty()) {
            textInputName.setError("Field can't be empty");
            return false;
        }
        else {
            textInputName.setError(null);
        }

        if (desc.isEmpty()) {
            textInputDesc.setError("Field can't be empty");
            return false;
        }
        else {
            textInputDesc.setError(null);
        }





        if(ImageUri== null)
        {
            Toast.makeText(AddNCG.this,"Please Select Image",Toast.LENGTH_LONG).show();
            return false;
        }

        else
        {
            mProgressBar.setVisibility(View.VISIBLE);



            final StorageReference filePath=mImageRef.child(ImageUri.getLastPathSegment()+".jpg");

            final UploadTask uploadTask=filePath.putFile(ImageUri);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e)
                {
                    String message=e.toString();
                    Toast.makeText(AddNCG.this,message,Toast.LENGTH_LONG).show();
                    mProgressBar.setVisibility(View.INVISIBLE);

                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Task<Uri> urlTask =uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                        {
                            if(!task.isSuccessful())
                            {
                                throw task.getException();

                            }
                            uri=filePath.getDownloadUrl().toString();
                            return filePath.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if(task.isSuccessful())
                            {   uri=task.getResult().toString();
                                SaveProductInfoToDb();
                                Toast.makeText(AddNCG.this,"Uploaded",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    ImageUri=null;
                    textInputName.getEditText().setText("");
                    textInputDesc.getEditText().setText("");
                    InputProductImage.setImageResource(R.drawable.camera);
                    mProgressBar.setVisibility(View.INVISIBLE);

                }
            });
        }
        return true;}
    private void SaveProductInfoToDb()
    {   uploadDate=currentDate.format(calendar.getTime());
        uploadTime=currentTime.format(calendar.getTime());
        uploadSamay=uploadDate+" "+uploadTime;

        ProductInfo productInfo=new ProductInfo(name,desc,uri,uploadSamay);


        long time=System.currentTimeMillis();
        productInfo.setPid(String.valueOf(time));
        mRef.child(String.valueOf(time)).setValue(productInfo);



    }
}
