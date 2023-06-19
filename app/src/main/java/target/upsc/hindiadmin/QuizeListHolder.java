package target.upsc.hindiadmin;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class QuizeListHolder extends RecyclerView.ViewHolder {
    public TextView question,explanation;
    Button option1,option2,option3,option4,button;



    public QuizeListHolder(@NonNull View itemView) {
        super(itemView);
        question= itemView.findViewById(R.id.textView1);
        option1= itemView.findViewById(R.id.button5);
        option2= itemView.findViewById(R.id.button6);
        option3= itemView.findViewById(R.id.button7);
        option4= itemView.findViewById(R.id.button8);
        explanation= itemView.findViewById(R.id.textView6);
        button=itemView.findViewById(R.id.button12);


    }


}
