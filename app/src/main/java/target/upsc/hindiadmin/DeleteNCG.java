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
import com.squareup.picasso.Picasso;

public class DeleteNCG extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    DatabaseReference mRef;
    RecyclerView.LayoutManager layoutManager;
    String tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_ncg);
        Intent intent=getIntent();
        tag = intent.getStringExtra("tag");

        if(tag.equals("deletenews"))
            mRef= FirebaseDatabase.getInstance().getReference().child("News");
        if(tag.equals("deletecurrentaffair"))
            mRef= FirebaseDatabase.getInstance().getReference().child("CurrentAffair");
        if(tag.equals("deletegk"))
            mRef= FirebaseDatabase.getInstance().getReference().child("GK");

        mRecyclerView=findViewById(R.id.list20);
        mRecyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        final FirebaseRecyclerOptions<ProductInfoShort> options=new FirebaseRecyclerOptions.Builder<ProductInfoShort>()
                .setQuery(mRef,ProductInfoShort.class).build();
        final FirebaseRecyclerAdapter<ProductInfoShort,ProductViewHolder> adapter=new FirebaseRecyclerAdapter<ProductInfoShort, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, final int position, @NonNull final ProductInfoShort model) {
                holder.pname.setText(model.getName());
                holder.uploadSamay.setText(model.uploadSamay);
                Picasso.get().load(model.getUri()).into(holder.imageView);
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
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.current_gk_layout,parent,false);
                ProductViewHolder holder=new ProductViewHolder(view);
                return holder;
            }
        };
        mRecyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}
