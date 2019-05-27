package nhom3.edu.androialarm1.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import nhom3.edu.androialarm1.database.DataBaseManager;
import nhom3.edu.androialarm1.model.Alarm;
import nhom3.edu.androialarm1.receiver.AlarmReceiver;
import nhom3.edu.androialarm1.ultil.Constants;
import nhom3.edu.androialarm1.view.AlarmAdapter;
import nhom3.edu.androialarm1.R;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AlarmMainActivity extends AppCompatActivity implements AlarmAdapter.CallBack {


    @BindView(R.id.openAdd)
    Button button;
    @BindView(R.id.alarmView)
    RecyclerView recyclerView;
    // này để quản lý cơ sở dữ liệu
    private DataBaseManager dataBaseManager;
    // cái này để quản lý alarmdapter như ArrayList
    private AlarmAdapter alarmAdapter;
    Intent intent;
    private int songId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    // TODO: this initialize view for activity
    private void initView() {
        // đặt layout cho recycle view
        //hasFixedSize true nếu adapter thay đổi không ảnh hưởng tới kích thước the RecyclerView
        recyclerView.setHasFixedSize(true);
        // bố cục này có thê Vertival hoặc holltit bằng cách thay đổi hàm số thứ 2 của linearlayoutManager
        // of LinearLayoutManager, and display up to down by set the third param false
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        importData();
        // đặt adapter cho recycle view
        recyclerView.setAdapter(alarmAdapter);
    }
    @OnClick(R.id.openAdd)
    public void onOpenAddAlarm(View view) {
        //TODO: xử lí khi người dùng click on "+" button bắt đầu new intent với request code
        Intent intent = new Intent(getApplicationContext(), AddAlarmActivity.class);
        intent.putExtra("screenType", "add");
        startActivityForResult(intent, Constants.REQUEST_ADD);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO: Nhận biểu mẫu dữ liệu trả về Thêm hoặc Chỉnh sửa hoạt động để xử lý thêm hoặc chỉnh sửa báo thức
        Alarm alarm;
        // kiểm tra nếu request code and result code giống nhau  sent và return result
        if (requestCode == Constants.REQUEST_ADD && resultCode == RESULT_OK) {
            // lấy data và đặt a new alarm theo function setAlarm
            alarm = (Alarm) data.getSerializableExtra("Alarm");
            // kiểm tra xem thời gian cài đặt đã tồn tại chưa
            boolean containAlarm = checkAlarm(alarm);
            if (!containAlarm) {
                //add alarm to adapter
                alarmAdapter.add(alarm);
                // refresh adapter
                alarmAdapter.notifyDataSetChanged();
                // add it to database
                dataBaseManager.insert(alarm);
                songId = data.getExtras().getInt("whale_choice");
                // set new PendingIntent
                setAlarm(alarm, 0);
            }


        } else if (requestCode == Constants.REQUEST_EDIT && resultCode == RESULT_OK) {
            // lấy đối tượng alarm từ AddAlarmActivity
            alarm = (Alarm) data.getSerializableExtra("Alarm");
            // kiểm tra xem thời gian cài đặt đã tồn tại chưa
            boolean containAlarm = checkAlarm(alarm);
            if (!containAlarm) {
                // nhận alarm's position
                int position = data.getExtras().getInt("position");
                // update alarm at position
                alarmAdapter.updateAlarm(alarm, position);
                // giúp refresh display
                alarmAdapter.notifyDataSetChanged();
                // update alarm to database
                dataBaseManager.update(alarm);
                // if alarm.getOnOff ==1 đặt alarm else not
                if (alarm.getOnOff() == 1) {
                    // nhận dữ liệu và đặt báo thức mới theo chức năng setAlarm với cập nhật cờ hiện tại vi
                    // PendingIntent này đã tồn tại
                    setAlarm(alarm, PendingIntent.FLAG_UPDATE_CURRENT);
                }
            }

        }
    }




    @Override
    public void onMenuAction(Alarm alarm, MenuItem item, int position) {
        // TODO: this function is a function of callBack interface which was created in alarm adapter
        //TODO: process edit or delete based on user option
        switch (item.getItemId()) {
            case R.id.edit:
                // gửi edit intent tới AddAlarmActivity để edit điều này sẽ trả lại alarm and position
                Intent intent = new Intent(this, AddAlarmActivity.class);
                // đặt screenType để đặt hiển thị cho AddAlarmActivity
                intent.putExtra("screenType", "edit");
                // đặt báo thức cần chỉnh sửa
                intent.putExtra("AlarmEdit", alarm);
                // đặt alarm's position
                intent.putExtra("position", position);
                // Điều này bắt đầu AddAlarmActivity và thay đổi màn hình và thay đổi chi tiết trở lại
              // một kết quả trên "onActivityResult" về
                startActivityForResult(intent, Constants.REQUEST_EDIT);
                break;

            case R.id.delete:
                // when user click edit remove alarm
                alarmAdapter.removeAlarm(position);
                // refresh
                alarmAdapter.notifyDataSetChanged();
                // lấy alarm id để delete alarm  trong database
                int alarmId = (int) alarm.getId();
                // xóa alarm from database
                dataBaseManager.delete(alarmId);
                // cancel pendingIntent
                deleteCancel(alarm);
                break;
        }

    }


    @Override
    public void startAlarm(Alarm alarm) {
        //TODO: Xử lý truyền thông tin giờ hẹn cho AlarmReceiver
        // khi toggle button click on set alarm on
        alarm.setOnOff(1);
        // update database
        dataBaseManager.update(alarm);
        // đặt PendingIntent cho alarm này
        setAlarm(alarm, 0);

    }


    @Override
    public void cancelAlarm(Alarm timeItem) {
        //TODO: Gửi thông tin giờ hẹn cần hủy sang cho AlarmReceiver
        // khi người dùng click cancel toggle button
        // đặt  alarm off
        timeItem.setOnOff(0);
        // update database
        dataBaseManager.update(timeItem);
        // cancel this Alarm PendingIntent
        deleteCancel(timeItem);
        // nếu báo thức được kích hoạt và đổ chuông, hãy gửi chi tiết báo động này đến AlertReceiver
        // sau đó AlertReceiver gửi chi tiết đến dịch vụ để dừng nhạc
        sendIntent(timeItem, Constants.OFF_INTENT);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    // TODO: kiểm tra này nếu Báo thức đã tồn tại
    private boolean checkAlarm(Alarm alarm) {
        boolean contain = false;
        for (Alarm alar : alarmAdapter.getmAlarms()) {
            if (alar.getHour_x() == alarm.getHour_x() && alar.getMinute_x() == alarm.getMinute_x())
                contain = true;
        }
        if (contain) {
            Toast.makeText(this, "You have already set this Alarm", Toast.LENGTH_SHORT).show();
        }
        return contain;
    }

    // TODO: nhập dữ liệu từ dataBase và tạo AlarmAdapter
    private void importData() {
        // nếu alarmAdapter null có nghĩa là data chưa import hoặc database trống
        if (alarmAdapter == null) {
            // khởi tạo database manager
            dataBaseManager = new DataBaseManager(this);
            // lấy  Alarm ArrayList từ database
            ArrayList<Alarm> arrayList = dataBaseManager.getAlarmList();
            // tạo Alarm adapter to display detail through RecyclerView
            alarmAdapter = new AlarmAdapter(arrayList, this);

        }
    }

    // TODO: điều này sẽ gửi intent tới AlarmReceiver
    private void sendIntent(Alarm alarm, String intentType ) {

        //Integer get_your_whale_choice = intent.getExtras().getInt("whale_choice",0);
        // intent1 gửi đến AlarmReceiver
        Intent intent1 = new Intent(AlarmMainActivity.this, AlarmReceiver.class);
        // đặt loại intent Constants.ADD_INTENT or Constants.OFF_INTENT
        intent1.putExtra("intentType", intentType);
        //intent1.putExtra("whale_choice", get_your_whale_choice);
        // đặt alarm'id  để so sánh với pendingIntent'id trong  AlarmService
        intent1.putExtra("AlarmId", (int) alarm.getId());
        // this sent broadCast right a way
        sendBroadcast(intent1);
    }

    // TODO: this sets pendingIntent for alarm
    private void setAlarm(Alarm alarm, int flags) {
        //điều này đặt báo thức dựa trên TimePicker vì vậy chúng ta cần đặt Lịch calendar  như
        // thời gian kích hoạt
        // get instant of Calendar
        Calendar myCalendar = Calendar.getInstance();
        Calendar calendar = (Calendar) myCalendar.clone();
        // đặt giờ hiện tại cho calendar
        calendar.set(Calendar.HOUR_OF_DAY, alarm.getHour_x());
        // đặt phút hiện tại
        calendar.set(Calendar.MINUTE, alarm.getMinute_x());
        // set current second for calendar
        calendar.set(Calendar.SECOND, 0);
        // cộng thêm một ngày nếu thời gian được đặt ít hơn thời gian hiện tại của Lịch
        if (calendar.compareTo(myCalendar) <= 0) {
            calendar.add(Calendar.DATE, 1);
        }
        // nhận id of alarm và đặt cho PendingIntent to multiply multiple PendingIntent for cancel
        // time, điều này cũng được đưa vào PendingIntent để so sánh với việc  cancel Alarm's id=
        int alarmId = (int) alarm.getId();
        // thực hiện intent broadCast
        Intent intent = new Intent(AlarmMainActivity.this, AlarmReceiver.class);
        // đặt loại intent để kiểm tra xem mục đích kích hoạt  add or cancel
        intent.putExtra("intentType", Constants.ADD_INTENT);

        intent.putExtra("whale_choice", songId);
        // đặt id vào intent
        intent.putExtra("PendingId", alarmId);


        // this pendingIntent include alarm id  to manage
        PendingIntent alarmIntent = PendingIntent.getBroadcast(AlarmMainActivity.this, alarmId,
                intent, flags);
        // tạo alarm manager ALARM_SERVICE
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // đặt alarm,tại thời điểm kích hoạt  "calandar.getTimeInMillis" pendingIntent sẽ
        // được gửi tới AlarmReceiver và sau đó được gửi đến alarm service để phát nhạc
        // "AlarmManager.INTERVAL_DAY" có nghĩa là sẽ đặt 1 báo thức mới vào thời điểm kích hoạt
        // setInExactRepeating sẽ đặt báo thức nhiều lần và điều này có thể không
        // trigger at the right time( at the first second start) but this will save the battery.
        // "AlarmManager.RTC_WAKEUP" cho phép ứng dụng này từ thời gian rảnh và thời gian này dựa trên thiết bị
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);

    }
    // TODO:  hủy pendingIntent của the alarm
    private void deleteCancel(Alarm alarm) {
        // nếu user clicl delete or cancel alarm  thì the pendingIntent cũng sẽ canceled bởi AlarmManager
        //  PendingIntent này canceled dựa trên alarm's ID đã được đặt cho nó.pendingIntent is
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // lấy alarm id
        int alarmId = (int) alarm.getId();
        // tạp intent
        Intent intent = new Intent(AlarmMainActivity.this, AlarmReceiver.class);
        // this retrieve the pendingIntent đã được thiết lập
        PendingIntent alarmIntent = PendingIntent.getBroadcast(AlarmMainActivity.this, alarmId, intent, 0);
        // cancel pendingIntent
        alarmManager.cancel(alarmIntent);
    }
}
