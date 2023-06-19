package target.upsc.hindiadmin;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ProductViewHolder extends RecyclerView.ViewHolder {
    public TextView pname,uploadSamay;
    public ImageView imageView;
    public Button button;


    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView= itemView.findViewById(R.id.imageView5);
        pname= itemView.findViewById(R.id.textView1);
        uploadSamay=itemView.findViewById(R.id.textView5);
        button=itemView.findViewById(R.id.button12);



    }


}
