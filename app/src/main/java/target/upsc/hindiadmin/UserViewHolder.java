package target.upsc.hindiadmin;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView uname, email, phoneno,aim;
    public Button button;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        uname = itemView.findViewById(R.id.textView5);
        email = itemView.findViewById(R.id.textView7);
        phoneno = itemView.findViewById(R.id.textView9);
        aim=itemView.findViewById(R.id.textView11);
        button=itemView.findViewById(R.id.button);
    }
}
