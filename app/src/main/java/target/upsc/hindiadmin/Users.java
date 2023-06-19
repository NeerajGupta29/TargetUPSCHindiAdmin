package target.upsc.hindiadmin;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Users extends AppCompatActivity {
    private DatabaseReference mRef;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView mRecyclerView;
    TextView textView;
    int currentItems,totalItems,scrollOutItems;
    Boolean isScrolling =false;
    UserAdapter adapter;
    String mkey="";
    boolean flag=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users);
        mRef= FirebaseDatabase.getInstance().getReference().child("Users");
        mRecyclerView=findViewById(R.id.list900);
        textView=findViewById(R.id.textView2);
        layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter=new UserAdapter(this);
        mRecyclerView.setAdapter(adapter);

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                   textView.setText(String.valueOf(dataSnapshot.getChildrenCount()));

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
        mRef.orderByKey().limitToFirst(20).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    List<UserDetails> list=new ArrayList<>();
                    for(DataSnapshot userSnapShot:dataSnapshot.getChildren())
                    {
                        list.add(userSnapShot.getValue(UserDetails.class));
                    }
                    if(list.size()>1)
                    {  mkey=list.get(list.size()-1).getUid();
                    list.remove(list.size()-1);}
                    adapter.addAll(list);

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                isScrolling=true;
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems=layoutManager.getChildCount();
                totalItems=layoutManager.getItemCount();
                scrollOutItems=((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                if(isScrolling &&(currentItems+scrollOutItems==totalItems)&&flag)
                {
                    isScrolling=false;
                    mRef.orderByKey().startAt(mkey).limitToFirst(20).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChildren()) {
                                List<UserDetails> list=new ArrayList<>();
                                for(DataSnapshot userSnapShot:dataSnapshot.getChildren())
                                {
                                    list.add(userSnapShot.getValue(UserDetails.class));
                                }
                                if(list.size()==1)
                                {
                                    flag=false;
                                }
                                if(list.size()>1)
                                {  mkey=list.get(list.size()-1).getUid();
                                    list.remove(list.size()-1);

                                }
                                adapter.addAll(list);

                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {


                        }
                    });
                }
            }
        });


    }

}
