package com.fithou.friendeverywhere.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;

public class AboutUsActivity extends AppCompatActivity {

    private Button btn_tiep;
    private TextView tv_aboutus;
    private String about_us = " Xin chân thành cảm ơn sự hướng dẫn, ủng hộ và chỉ bảo của thầy Đỗ Xuân Chợ nói riêng và các thầy cô trong Khoa Công Nghệ Thông Tin nói chung đã tận tình giảng dạy, cung cấp các kiến thức cần thiết để tôi có thể vận dụng trong quá trình xây dựng ứng dụng.\n" +
            "   Ứng dụng Friend Everywhere sử dụng công nghệ OTT trên nền tảng hệ điều hành Android là sản phẩm tôi xây dựng và thiết kế dựa trên những kiến thức đã được học trên ghế nhà trường. Ứng dụng này được thực hiện không chỉ với mục đích tốt nghiệp mà em còn muốn sản phẩm này được sử dụng trong thực tế, giúp đỡ mọi người có cơ hội kết bạn và giao tiếp một cách nhanh chóng, tiện lợi.\n" +
            "   So với các ứng dụng đã có trên thị trường hiện nay, ứng dụng của tôi khác với các ứng dụng khác như sau:\n" +
            "-\tĐối với Viber, ứng dụng FE cho phép người dùng có thể tìm kiếm và kết bạn ở khu vực xung quanh theo yêu cầu như tuổi, giới tính, sở thích…\n" +
            "-\tĐối với Zalo, ứng dụng FE cung cấp thêm bản đồ để người dùng có thể biết vị trí của người bạn của mình hiện tại ở đâu.\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        tv_aboutus = (TextView)findViewById(R.id.tv_aboutus);
        tv_aboutus.setText(about_us);

    }
}
