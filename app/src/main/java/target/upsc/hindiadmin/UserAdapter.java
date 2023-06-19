package target.upsc.hindiadmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>  {
    Context context;
    List<UserDetails> list;

    public UserAdapter(Context context) {
        this.context = context;
        this.list=new ArrayList<>();
    }

    public void addAll(List<UserDetails> newlist)
    {
        int i=list.size();
        list.addAll(newlist);
        notifyItemRangeChanged(i,newlist.size());

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.users_layout,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int  position) {

        holder.uname.setText(list.get(position).getUname());
        holder.email.setText(list.get(position).getEmail());
        holder.phoneno.setText(list.get(position).getPhoneno());
        holder.aim.setText(list.get(position).getAim());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = list.get(position).getUid();
                Intent intent = new Intent(context, SendNotification.class);
                intent.putExtra("key", key);
                context.startActivity(intent);
            }
        });



    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class  MyViewHolder extends RecyclerView.ViewHolder{

        public TextView uname, email, phoneno,aim;
        public Button button;
        public MyViewHolder(View itemView){
            super(itemView);
            uname = itemView.findViewById(R.id.textView5);
            email = itemView.findViewById(R.id.textView7);
            phoneno = itemView.findViewById(R.id.textView9);
            aim=itemView.findViewById(R.id.textView11);
            button=itemView.findViewById(R.id.button);
        }
    }
}

