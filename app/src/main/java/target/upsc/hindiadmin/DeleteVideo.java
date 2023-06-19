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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

public class DeleteVideo extends AppCompatActivity {
    String string;
    private RecyclerView mRecyclerView;
    DatabaseReference mRef;
    RecyclerView.LayoutManager layoutManager;
    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_video);
        decorView=getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility==0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });
        Intent intent=getIntent();
        string = intent.getStringExtra("key");
        mRef= FirebaseDatabase.getInstance().getReference().child("YoutubeVideo").child(string);
        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);
        final FirebaseRecyclerOptions<YoutubeListInfo> options=
                new FirebaseRecyclerOptions.Builder<YoutubeListInfo>()
                        .setQuery(mRef,YoutubeListInfo.class).build();

        final FirebaseRecyclerAdapter<YoutubeListInfo,YoutubeListHolder> adapter=
                new FirebaseRecyclerAdapter<YoutubeListInfo,YoutubeListHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull YoutubeListHolder holder, final int position, @NonNull final YoutubeListInfo model)
                    {
                        getLifecycle().addObserver(holder.youTubePlayerView);
                        holder.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady(YouTubePlayer youTubePlayer) {
                                youTubePlayer.loadVideo(model.getVideoId(),0);

                            }
                        });

                        holder.headLine.setText(model.getHeadLine());
                        holder.uploadSamay.setText(model.getUploadSamay());
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
                    public YoutubeListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_list,parent,false);
                        YoutubeListHolder holder=new YoutubeListHolder(view);
                        return holder;
                    }
                };
        mRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }
    private int hideSystemBars(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }
}
