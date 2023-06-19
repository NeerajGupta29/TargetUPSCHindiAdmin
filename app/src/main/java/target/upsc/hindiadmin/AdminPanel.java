package target.upsc.hindiadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminPanel extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_panel);
        mAuth= FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        if(mUser==null)
        {Intent intent=new Intent(AdminPanel.this, Login.class);
            startActivity(intent);
            finish();
            return;
        }

    }
    public void  AddNews(View view)
    { Intent intent=new Intent(this, AddNCG.class);
        intent.putExtra("tag",String.valueOf(view.getTag()));
        startActivity(intent);

    }
    public void  AddVideo(View view)
    { Intent intent=new Intent(this, ChooseCategory.class);
        intent.putExtra("tag",String.valueOf(view.getTag()));
        startActivity(intent);

    }
    public void  AddCurrentAffair(View view)
    {
        Intent intent=new Intent(this, AddNCG.class);
        intent.putExtra("tag",String.valueOf(view.getTag()));
        startActivity(intent);

    }
    public void  AddGK(View view)
    { Intent intent=new Intent(this, AddNCG.class);
        intent.putExtra("tag",String.valueOf(view.getTag()));
        startActivity(intent);

    }
    public void  AddQuize(View view)
    { Intent intent=new Intent(this, ChooseCategory.class);
        intent.putExtra("tag",String.valueOf(view.getTag()));
        startActivity(intent);

    }
    public void  DeleteNews(View view)
    { Intent intent=new Intent(this, DeleteNCG.class);
        intent.putExtra("tag",String.valueOf(view.getTag()));
        startActivity(intent);

    }
    public void  DeleteCurrentAffair(View view)
    { Intent intent=new Intent(this, DeleteNCG.class);
        intent.putExtra("tag",String.valueOf(view.getTag()));
        startActivity(intent);

    }
    public void  DeleteGK(View view)
    { Intent intent=new Intent(this, DeleteNCG.class);
        intent.putExtra("tag",String.valueOf(view.getTag()));
        startActivity(intent);

    }
    public void  DeleteVideo(View view)
    { Intent intent=new Intent(this, ChooseCategory.class);
        intent.putExtra("tag",String.valueOf(view.getTag()));
        startActivity(intent);

    }

    public void  DeleteQuize(View view)
    { Intent intent=new Intent(this, ChooseCategory.class);
        intent.putExtra("tag",String.valueOf(view.getTag()));
        startActivity(intent);

    }
    public void  ViewUsers(View view)
    { Intent intent=new Intent(this, Users.class);

        startActivity(intent);

    }
    public void  SendNotificationToAll(View view)
    { Intent intent=new Intent(this, SendNotificationToAll.class);

        startActivity(intent);

    }
}
