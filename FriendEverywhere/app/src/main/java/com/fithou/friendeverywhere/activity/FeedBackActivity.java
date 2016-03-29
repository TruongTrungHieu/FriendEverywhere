package com.fithou.friendeverywhere.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.object.FeedbackObject;
import com.fithou.friendeverywhere.ultis.StringSupport;

import java.util.Date;

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_feedback_subject, et_feedback_content;
    private Button btn_feedback_send;
    private FeedbackObject feedbackObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        inflateView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    protected void inflateView() {
        et_feedback_content = (EditText) findViewById(R.id.et_feedback_content);
        et_feedback_subject = (EditText) findViewById(R.id.et_feedback_subject);
        btn_feedback_send = (Button) findViewById(R.id.btn_send_feedback);

        btn_feedback_send.setOnClickListener(this);

        et_feedback_subject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                et_feedback_subject.setError(null);
            }
        });

        et_feedback_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                et_feedback_content.setError(null);
            }
        });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_send_feedback) {
            if (checkValidate()) {
                // TODO: send Feedback
                Toast.makeText(this, "Feedback has been sent", Toast.LENGTH_LONG);
                onBackPressed();
            }
        }
    }

    protected boolean checkValidate() {
        if (feedbackObject == null) {
            feedbackObject = new FeedbackObject();
        }
        feedbackObject.setContent(et_feedback_content.getText().toString().trim());
        feedbackObject.setSubject(et_feedback_subject.getText().toString().trim());
        feedbackObject.setCreated_date(new Date());
        if (StringSupport.isNullOrEmpty(feedbackObject.getSubject())) {
            et_feedback_subject.setError(getString(R.string.feed_back_err_null_subject));
            et_feedback_subject.requestFocus();
            return false;
        }
        if (StringSupport.isNullOrEmpty(feedbackObject.getContent())) {
            et_feedback_content.setError(getString(R.string.feed_back_err_null_content));
            et_feedback_content.requestFocus();
            return false;
        }
        return true;
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
