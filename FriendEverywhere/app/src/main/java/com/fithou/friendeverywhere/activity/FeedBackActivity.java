package com.fithou.friendeverywhere.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fithou.friendeverywhere.R;

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_feedback_subject, et_feedback_content;
    private Button btn_feedback_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        inflateView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    protected void inflateView(){
        et_feedback_content = (EditText)findViewById(R.id.et_feedback_content);
        et_feedback_subject = (EditText)findViewById(R.id.et_feedback_subject);
        btn_feedback_send = (Button)findViewById(R.id.btn_send_feedback);

        btn_feedback_send.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId()==R.id.btn_send_feedback){
            Toast.makeText(this, "Feedback has been sent", Toast.LENGTH_LONG);
            this.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feed_back_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
