package target.upsc.hindiadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SendNotification extends AppCompatActivity {
    private TextInputLayout textInputNotification,textInputTitle;
    private String notification,URL,uid4,title;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_notification);
        Intent intent=getIntent();
        uid4=intent.getStringExtra("key");
        textInputNotification = findViewById(R.id.edtext);
        textInputTitle = findViewById(R.id.edtext2);


        URL="https://fcm.googleapis.com/fcm/send";
        requestQueue= Volley.newRequestQueue(this);
    }
    public boolean SendNotificationTo(View view){

        notification = textInputNotification.getEditText().getText().toString().trim();
        title= textInputTitle.getEditText().getText().toString().trim();

        if (title.isEmpty()) {
            textInputTitle.setError("Field can't be empty");
            return false;
        }
        else {
            textInputTitle.setError(null);
        }
        if (notification.isEmpty()) {
            textInputNotification.setError("Field can't be empty");
            return false;
        }
        else {
            textInputNotification.setError(null);
        }
        sendNotification();
        return true;
    }
    private  void  sendNotification(){
        JSONObject mainObj =new JSONObject();
        try {
            mainObj.put("to","/topics/"+uid4);
            JSONObject notificationObj =new JSONObject();
            notificationObj.put("title",title);
            notificationObj.put("body",notification);

            mainObj.put("notification",notificationObj);
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, URL,
                    mainObj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header=new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AAAASdCGzQw:APA91bENtEn_XygiBqG6jdD7JlWIcdrayuJ10LzkUBU_AuH1UFSHtZXPa_vQ5k3o1nOuJGVGuSxabzM9uqZYF5Aue0jOeFC92zMqPAbN1w6MqziiAu55DODbTCpiwqzBN2kwzP4ey0rP");
                    return header;
                }
            };
            requestQueue.add(request);
            Toast.makeText(SendNotification.this, "Send", Toast.LENGTH_SHORT).show();
            textInputNotification.getEditText().setText("");
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}
