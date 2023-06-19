package target.upsc.hindiadmin;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class YoutubeListHolder extends RecyclerView.ViewHolder {
    public TextView headLine,uploadSamay;
    public YouTubePlayerView youTubePlayerView;
    public Button button;

    public YoutubeListHolder(@NonNull View itemView) {
        super(itemView);
        youTubePlayerView= itemView.findViewById(R.id.youtube_player_view);
        headLine= itemView.findViewById(R.id.textView1);
        uploadSamay=itemView.findViewById(R.id.textView5);
        button=itemView.findViewById(R.id.button12);



    }


}
