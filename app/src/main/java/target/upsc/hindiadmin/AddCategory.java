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

public class AddCategory extends AppCompatActivity {
    private TextInputLayout textInputName;
    private String name;
    private DatabaseReference mRef;
    private ProgressBar mProgressBar;
    String tag,key1=null,key2=null,key3=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_category);
        Intent intent=getIntent();
        tag = intent.getStringExtra("tag");
        key1= intent.getStringExtra("key1");
        key2= intent.getStringExtra("key2");
        key3= intent.getStringExtra("key3");
        mProgressBar=findViewById(R.id.progress_bar);
        textInputName = findViewById(R.id.edtext);
        if(key1==null)
            mRef= FirebaseDatabase.getInstance().getReference().child("QuizeCategoryStruct");
        else
                 if(key2==null)
                    mRef= FirebaseDatabase.getInstance().getReference().child("QuizeCategoryStruct").child(key1).child(key1);
                 else
                     if(key3==null)
                      mRef= FirebaseDatabase.getInstance().getReference().child("QuizeCategoryStruct")
                    .child(key1).child(key1).child(key2).child(key2);
                     else
                         mRef= FirebaseDatabase.getInstance().getReference().child("QuizeCategoryStruct")
                                 .child(key1).child(key1).child(key2).child(key2).child(key3).child(key3);

             if(tag.equals("addvideo"))
            mRef= FirebaseDatabase.getInstance().getReference().child("YoutubeVideoCategoryStruct");

    }
    public void Upload(View view)
    {
        name = textInputName.getEditText().getText().toString().trim();

        if (name.isEmpty()) {
            textInputName.setError("Field can't be empty");
            return ;
        }
        else {
            textInputName.setError(null);
            mProgressBar.setVisibility(View.VISIBLE);
            SaveProductInfoToDb();
            textInputName.getEditText().setText("");
        }


    }
    private void SaveProductInfoToDb()
    {

        CategoryListInfo categoryListInfo=new CategoryListInfo(name);

        long time=System.currentTimeMillis();
        categoryListInfo.setPid(String.valueOf(time));
        mRef.child(String.valueOf(time)).setValue(categoryListInfo);
        mProgressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(AddCategory.this,"Uploaded",Toast.LENGTH_LONG).show();

    }

}
