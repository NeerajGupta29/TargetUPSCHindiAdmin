package target.upsc.hindiadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteQuize extends AppCompatActivity {
    RecyclerView mRecyclerView;
    DatabaseReference mRef;
    RecyclerView.LayoutManager layoutManager;
    String key1,key2,key3,key4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_quize);
        Intent intent=getIntent();
        key1=intent.getStringExtra("key1");
        key2=intent.getStringExtra("key2");
        key3=intent.getStringExtra("key3");
        key4=intent.getStringExtra("key4");
        mRef= FirebaseDatabase.getInstance().getReference().child("Quize").child(key1).child(key2).child(key3).child(key4);

        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);
        final FirebaseRecyclerOptions<QuizeInfo> options=new FirebaseRecyclerOptions.Builder<QuizeInfo>()
                .setQuery(mRef,QuizeInfo.class).build();
        final FirebaseRecyclerAdapter<QuizeInfo,QuizeListHolder> adapter=
                new FirebaseRecyclerAdapter<QuizeInfo,QuizeListHolder>(options) {
                    @Override
                    protected void onBindViewHolder
                            (@NonNull final QuizeListHolder holder, final int position, @NonNull final QuizeInfo model) {


                        holder.question.setText(model.getQuestion());
                        holder.option1.setText(model.getOption1());
                        holder.option2.setText(model.getOption2());
                        holder.option3.setText(model.getOption3());
                        holder.option4.setText(model.getOption4());
                        holder.explanation.setText(model.getExplanation());
                        holder.button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String key=model.getPid();
                                mRef.child(key).removeValue();
                            }
                        });








                    }

                    @NonNull
                    @Override
                    public QuizeListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.quize_list,parent,false);
                        QuizeListHolder holder=new QuizeListHolder(view);
                        return holder;
                    }
                };
        mRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
