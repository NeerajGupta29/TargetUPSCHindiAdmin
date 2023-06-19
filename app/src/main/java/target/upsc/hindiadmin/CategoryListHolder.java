package target.upsc.hindiadmin;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CategoryListHolder extends RecyclerView.ViewHolder {
    public TextView pname;
    public Button button;


    public CategoryListHolder(@NonNull View itemView) {
        super(itemView);
        pname= itemView.findViewById(R.id.textView1);
        button=itemView.findViewById(R.id.button12);
    }


}
