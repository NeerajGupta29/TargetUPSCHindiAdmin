package target.upsc.hindiadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseCategory3 extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    DatabaseReference mRef;
    RecyclerView.LayoutManager layoutManager;
    String tag,key1,key2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_category_3);
        Intent intent=getIntent();
        tag = intent.getStringExtra("tag");
        key1= intent.getStringExtra("key1");
        key2= intent.getStringExtra("key2");


        mRef = FirebaseDatabase.getInstance().getReference().child("QuizeCategoryStruct").child(key1).child(key1).child(key2).child(key2);



        mRecyclerView=findViewById(R.id.recyclerView1);
        button=findViewById(R.id.button1);
        mRecyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        if(tag.equals("deletequize"))
            button.setVisibility(View.GONE);

        final FirebaseRecyclerOptions<CategoryListInfo> options=new FirebaseRecyclerOptions.Builder<CategoryListInfo>()
                .setQuery(mRef,CategoryListInfo.class).build();
        final FirebaseRecyclerAdapter<CategoryListInfo,CategoryListHolder> adapter=
                new FirebaseRecyclerAdapter<CategoryListInfo, CategoryListHolder>(options) {
                    @Override
                    protected void onBindViewHolder
                            (@NonNull CategoryListHolder holder, final int position, @NonNull final CategoryListInfo model) {
                        holder.pname.setText(model.getName());
                        if(tag.equals("deletequize")||tag.equals("deletevideo"))
                        {
                            holder.button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String key=model.getPid();
                                    mRef.child(key).removeValue();
                                }
                            });
                        }
                        else
                            holder.button.setVisibility(View.GONE);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String key3=model.getPid();
                                if(tag.equals("addquize"))
                                { Intent intent=new Intent(ChooseCategory3.this, ChooseCategory4.class);
                                    intent.putExtra("key1",key1);
                                    intent.putExtra("key2",key2);
                                    intent.putExtra("key3",key3);
                                    intent.putExtra("tag","addquize");
                                    startActivity(intent);}
                                if(tag.equals("deletequize"))
                                {Intent intent=new Intent(ChooseCategory3.this, ChooseCategory4.class);
                                    intent.putExtra("key1",key1);
                                    intent.putExtra("key2",key2);
                                    intent.putExtra("key3",key3);
                                    intent.putExtra("tag","deletequize");
                                    startActivity(intent);

                                }

                            }
                        });



                    }

                    @NonNull
                    @Override
                    public CategoryListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list,parent,false);
                        CategoryListHolder holder=new CategoryListHolder(view);
                        return holder;
                    }
                };
        mRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public void  AddCategory(View view)
    { Intent intent=new Intent(this, AddCategory.class);
        intent.putExtra("tag",tag);
        intent.putExtra("key1",key1);
        intent.putExtra("key2",key2);

        startActivity(intent);

    }
}
