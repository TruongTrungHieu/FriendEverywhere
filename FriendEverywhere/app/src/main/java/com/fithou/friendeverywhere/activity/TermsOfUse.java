package com.fithou.friendeverywhere.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;

public class TermsOfUse extends AppCompatActivity {

    private TextView tv_terms;
    private String terms = "Vui lòng đọc kỹ Điều khoản Sử Dụng trước khi bạn tiến hành tải, cài đặt, sử dụng ứng dụng Friend Everywhere. Bạn chấp thuận và đồng ý bị ràng buộc bởi các quy định và điều kiện trong Điều khoản này khi thực hiện các thao tác trên đây. Trường hợp bạn không đồng ý với bất kỳ điều khoản sử dụng nào của Ứng dụng (phiên bản này và các phiên bản cập nhật), bạn vui lòng không tải, cài đặt, sử dụng Ứng dụng hoặc tháo gỡ Ứng Dụng ra khỏi thiết bị di động của bạn.\n" +
            "1.\tCập nhật\n" +
            "Điều khoản này có thể được cập nhật thường xuyên bởi Hùng. Phiên bản cập nhật sẽ thay thế cho các quy định và điều kiện trong Điều khoản ban đầu. Bạn có thể truy cập vào Ứng Dụng để xem nội dung chi tiết của phiên bản cập nhật.\n" +
            "2.\tGiới Thiệu Về Ứng Dụng\n" +
            "Friend Everywhere là ứng dụng nhắn tin và kết nối đa phương tiện dành riêng cho người dùng di động tại Việt Nam. Ứng dụng sử dụng số điện thoại và danh bạ làm nền tảng với các tính năng chính: (1) Nhắn tin bằng giọng nói, (2) Kết nối theo sở thích và địa điểm. Ứng dụng chỉ hỗ trợ nền tảng Android. \n" +
            "3.\tQuyền Sở Hữu Ứng Dụng\n" +
            "Ứng Dụng này được phát triển và sở hữu bởi Hùng, tất cả các quyền sở hữu trí tuệ liên quan đến Ứng Dụng và các tài liệu hướng dẫn liên quan sẽ thuộc quyền sở hữu duy nhất bởi Hùng và không cá nhân, tổ chức nào được phép sao chép, tái tạo, phân phối, hoặc hình thức khác xâm phạm tới quyền của chủ sở hữu nếu không có sự đồng ý và cho phép bằng văn bản của Hùng.\n" +
            "4.\tTài Khoản\n" +
            "Để sử dụng Ứng Dụng bạn phải tạo một tài khoản theo yêu cầu của Ứng dụng, bạn cam kết rằng việc sử dụng tài khoản phải tuân thủ các quy định của Ứng dụng, đồng thời tất cả các thông tin bạn cung cấp cho Ứng dụng là đúng, chính xác, đầy đủ với tại thời điểm được yêu cầu. Mọi quyền lợi và nghĩa vụ của bạn sẽ căn cứ trên thông tin tài khoản bạn đã đăng ký, do đó nếu có bất kỳ thông tin sai lệch nào Hùng sẽ không chịu trách nhiệm trong trường hợp thông tin đó làm ảnh hưởng hoặc hạn chế quyền lợi của bạn.\n" +
            "Bạn có quyền sử dụng Ứng Dụng và các dịch vụ khác mà Hùng cung cấp, tuy nhiên việc sử dụng đó sẽ không bao gồm các hành vi sau đây nếu không có sự đồng ý bằng văn bản của Hùng.\n" +
            "\uF0A7\tSao chép, chỉnh sửa, tái tạo, tạo ra sản phẩm mới hoặc phiên bản phái sinh trên cơ sở Ứng Dụng này\n" +
            "\uF0A7\tBán, chuyển giao, cấp quyền lại, tiết lộ hoặc hình thức chuyển giao khác hoặc đưa một phần hoặc toàn bộ Ứng Dụng cho bất kỳ bên thứ ba \n" +
            "\uF0A7\tSử dụng Ứng Dụng để cung cấp dịch vụ cho bất kỳ bên thứ ba (tổ chức, cá nhân) \n" +
            "\uF0A7\tDi chuyển, xóa bỏ, thay đổi bất kỳ thông báo chính đáng hoặc dấu hiệu nào của Ứng Dụng \n" +
            "\uF0A7\tThiết kế lại, biên dịch, tháo gỡ, chỉnh sửa, đảo lộn thiết kế của Ứng Dụng hoặc nội dung Ứng Dụng \n" +
            "\uF0A7\tThay đổi hoặc hủy bỏ trạng thái ban đầu của Ứng Dụng \n" +
            "\uF0A7\tSử dụng Ứng Dụng để thực hiện bất kỳ hành động gây hại cho hệ thống an ninh mạng của Hùng, bao gồm nhưng không giới hạn sử dụng dữ liệu hoặc truy cập vào máy chủ hệ thống hoặc tài khoản không được phép  truy cập vào hệ thống mạng để xóa bỏ, chỉnh sửa và thêm các thông tin; phát tán các chương trình độc hại, virus hoặc thực hiện bất kỳ hành động nào khác nhằm gây hại hoặc phá hủy hệ thống mạng \n" +
            "\uF0A7\tĐăng nhập và sử dụng Ứng Dụng bằng một phần mềm tương thích của bên thứ ba hoặc hệ thống không được phát triển, cấp quyền hoặc chấp thuận bởi Hùng \n" +
            "\uF0A7\tSử dụng, bán, cho mượn, sao chép, chỉnh sửa, kết nối tới, phiên dịch, phát hành, công bố các thông tin liên quan đến Ứng Dụng, xây dựng mirror website để công bố các thông tin này hoặc để phát triển các sản phẩm phái sinh, công việc hoặc dịch vụ \n" +
            "\uF0A7\tSử dụng Ứng Dụng để đăng tải, chuyển, truyền hoặc lưu trữ các thông tin vi phạm pháp luật, vi phạm thuần phong mỹ tục của dân tộc \n" +
            "\uF0A7\tSử dụng Ứng Dụng để sử dụng, đăng tải, chuyển, truyền hoặc lưu trữ bất kỳ nội dung vi phạm quyền sở hữu trí tuệ, bí mật kinh doanh hoặc quyền pháp lý của bên thứ ba \n" +
            "\uF0A7\tSử dụng Ứng Dụng hoặc các dịch vụ khác được cung cấp bởi Hùng trong bất kỳ hình thức vi phạm pháp luật nào, cho bất kỳ mục đích bất hợp pháp nào \n" +
            "\uF0A7\tCác hình thức vi phạm khác.\n" +
            "\n" +
            "5.\tXử Lý Vi Phạm\n" +
            "Trường hợp bạn vi phạm bất kỳ quy định nào trong Điều khoản này, Hùng có quyền ngay lập tức khóa tài khoản của bạn và/hoặc xóa bỏ toàn bộ các thông tin, nội dung vi phạm, đồng thời tùy thuộc vào tính chất, mức độ vi phạm bạn sẽ phải chịu trách nhiệm trước cơ quan có thẩm quyền, Hùng và bên thứ ba về mọi thiệt hại gây ra bởi hoặc xuất phát từ hành vi vi phạm của bạn.\n" +
            "6.\tQuyền Truy Cập và Thu Thập Thông Tin\n" +
            "Khi sử dụng Ứng Dụng, bạn thừa nhận rằng Hùng có quyền sử dụng những API hệ thống sau để truy cập vào dữ liệu trên điện thoại của bạn: \n" +
            "\uF0A7\tĐọc và ghi vào danh bạ điện thoại\n" +
            "\uF0A7\tLấy vị trí hiện tại của bạn khi được sự đồng ý \n" +
            "\uF0A7\tGhi dữ liệu của Ứng Dụng lên thẻ nhớ \n" +
            "\uF0A7\tTruy cập vào Internet từ thiết bị của bạn. Tất cả các truy cập này đều được Ứng dụng thực hiện sau khi có sự đồng ý của bạn, vì vậy bạn cam kết và thừa nhận rằng, khi bạn đã cấp quyền cho Ứng dụng, bạn sẽ không có bất kỳ khiếu nại nào đối với Hùng về việc truy cập này.\n" +
            "Cùng với quyền truy cập, Ứng dụng sẽ thu thập các thông tin sau của bạn\n" +
            "\uF0A7\tThông tin cá nhân: bao gồm các thông tin bạn cung cấp cho Ứng dụng để xác nhận tài khoản như tên, số điện thoại, địa chỉ email \n" +
            "\uF0A7\tThông tin chung: như các thông tin về cấu hình điện thoại của bạn, thông tin phiên bản Friend Everywhere mà bạn đang sử dụng cho điện thoại của mình \n" +
            "\uF0A7\tThông tin vị trí của bạn: dữ liệu về vị trí địa lý của bạn sẽ được lưu trữ trên máy chủ nhằm giúp bạn sử dụng chức năng tìm kiếm của Ứng Dụng \n" +
            "\uF0A7\tDanh bạ điện thoại: Ứng dụng sẽ lưu trữ danh bạ điện thoại của bạn trên máy chủ nhằm hỗ trợ tốt nhất cho bạn trong việc sử dụng Ứng Dụng và tránh trường hợp bạn bị mất dữ liệu. Ứng dụng cam kết sẽ tôn trọng và không sử dụng danh bạ điện thoại của bạn vì bất kỳ mục đích nào nếu không có sự đồng ý của bạn \n" +
            "\uF0A7\tỨng dụng không sử dụng bất kỳ biện pháp nào để theo dõi nội dung tin nhắn, trao đổi hoặc hình thức khác nhằm theo dõi người dùng khi sử dụng Ứng Dụng này.   \n" +
            "\n" +
            "7.\tCam Kết Bảo Mật Thông Tin\n" +
            "Hùng sử dụng các phương thức truyền tin an toàn https và mã hóa để truyền tải và lưu trữ các dữ liệu cá nhân và giao tiếp của bạn. Hùng cam kết giữ bí mật tất cả thông tin mà bạn cung cấp cho Ứng dụng hoặc thông tin thu thập từ bạn và không tiết lộ với bất kỳ bên thứ ba nào trừ khi có yêu cầu từ Cơ quan Nhà nước có thẩm quyền.\n" +
            "8.\tPhí Và Các Khoản Thu\n" +
            "Hùng cam kết không thu bất cứ khoản phí nào từ người dùng cho các dịch vụ cơ bản mà hiện tại ứng dụng đang cung cấp.\n" +
            "9.\tQuảng cáo và các nội dung thương mại được phân phối bởi Friend Everywhere\n" +
            "Khi sử dụng ứng dụng, bạn thừa nhận rằng Hùng có quyền sử dụng các thông tin không định danh của bạn nhằm cung cấp các nội dung quảng cáo đúng đối tượng.\n" +
            "10.\tLưu Ý Sử Dụng\n" +
            "Một số tính năng kết bạn theo sở thích hay địa điểm như: Tìm bạn trên bản đồ có thể gây phiền toái cho người sử dụng. Khi sử dụng tính năng này, bạn có thể tìm thấy được bạn mới và đồng thời bạn cũng sẽ được tìm thấy bởi người lạ.Bạn phải cẩn thận khi quyết định hẹn gặp một người lạ thông qua tính năng này, nếu bạn phát hiện người lạ có dấu hiệu lừa đảo hoặc phạm tội, xin hãy báo cáo lại cho Hùng hoặc cơ quan Pháp Luật gần nhất.Nếu bạn cảm thấy phiền toái sau khi sử dụng tính năng này, hãy chọn Ẩn vị trí để không bị làm phiền.\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_use);

        tv_terms = (TextView)findViewById(R.id.tv_terms);
        tv_terms.setText(terms);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.menu_terms_of_use, menu);
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
