package nhom3.edu.androialarm1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import nhom3.edu.androialarm1.R;
import nhom3.edu.androialarm1.model.Alarm;
import nhom3.edu.androialarm1.ultil.Constants;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAlarmActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.toolBarAdd)
    Toolbar toolBarAdd;
    @BindView(R.id.addAlarm)
    Button addAlarm;
    @BindView(R.id.time_Picker)
    TimePicker timePicker;
    @BindView(R.id.activityName)
    TextView activityName;
    @BindView(R.id.name_Alarm)
    EditText name_Alarm;
    int choose_whale_sound;
    // addScreen true if user press "+" button, false if user press edit in popup menu
    private boolean addScreen;
    // alarm can chinh sua
    private Alarm alarmEdit;
    // intent duoc gui toi AlarmMainActivity
    private Intent intentInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);
        initView();

        // create the spinner in the main UI
        Spinner spinner = (Spinner) findViewById(R.id.richard_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.whale_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        // Set an onclick listener to the onItemSelected method
        spinner.setOnItemSelectedListener(this);

    }

    // TODO: this initialize view for this  activity
    private void initView() {
        // Set back icon
        toolBarAdd.setNavigationIcon(R.drawable.ic_back);
        setScreen();
        backPressed();

    }

    // khi nguoi dung an backpressed
    private void backPressed() {
        toolBarAdd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set result cancel for on back button press
                setResult(Constants.RESULT_CANCEL);
                onBackPressed();
            }
        });
    }

    private void setScreen() {
        intentInfor = getIntent();
        String screenType = intentInfor.getStringExtra("screenType");
        if (screenType.contains("add")) {
            activityName.setText(R.string.add);
            addAlarm.setText(R.string.add);
            addScreen = true;

        } else if (screenType.contains("edit")) {
            try {
                alarmEdit = (Alarm) intentInfor.getExtras().getSerializable("AlarmEdit");
            } catch (Exception e) {
                Log.e("setScreen exception", e.getMessage() + " cause: " + e.getCause());
            }

            if (alarmEdit != null) {
                timePicker.setHour(alarmEdit.getHour_x());
                timePicker.setMinute(alarmEdit.getMinute_x());
                name_Alarm.setText(alarmEdit.getAlarm_Name());
                activityName.setText(R.string.edit);
                addAlarm.setText(R.string.edit);
            }

            addScreen = false;
        }

    }

    @OnClick(R.id.addAlarm)
    public void onClick(View v) {
        Intent intent = new Intent(this, AlarmMainActivity.class);
        Alarm alarm = initAlarm();

        if (addScreen) {
            alarm.setId((int) System.currentTimeMillis());
            intent.putExtra("Alarm", alarm);
            Log.e("AAAADD",choose_whale_sound+"");
            intent.putExtra("whale_choice", choose_whale_sound);
            setResult(RESULT_OK, intent);
            finish();

        } else {
            // in this get information from intent including alarm object and it's position
            // get the time and alarm'name changed and set to the edit alarm
            int position = intentInfor.getExtras().getInt("position");

            String name = alarm.getAlarm_Name();
            int hour = alarm.getHour_x();
            int minute = alarm.getMinute_x();

            alarmEdit.setAlarm_Name(name);
            alarmEdit.setHour_x(hour);
            alarmEdit.setMinute_x(minute);


            // gui lai bang bundle, bundle nay nen su sung neu can chuyen 1 big data
            Bundle bundle = new Bundle();
            bundle.putSerializable("Alarm", alarmEdit);
            bundle.putInt("position", position);

            intent.putExtras(bundle);
            // dat result tu  activity nay
            setResult(RESULT_OK, intent);
            // finish method is requires if this Activity was started by startActivityForResult
            finish();
        }


    }


    // TODO: Hẹn giờ... báo thức trả về này từ timePicker được bật bật theo mặc định
    private Alarm initAlarm() {
        // dat on va off theo mac dinh, 1 is on and 0 is off
        int toggleOn = 1;
        Alarm alarm;
        String name1 = null;
        // lay thoi gian hien tai tu timepicker
        int hour_x = 0;
        int minute_x = 0;

        try {
            hour_x = timePicker.getCurrentHour();
            minute_x = timePicker.getCurrentMinute();
            // lay name tu alarm toi EditText
            String name = name_Alarm.getText().toString();

            if (name.length() == 0) {
                // nếu tên báo động không được nhập, hãy đặt EditText'hint cho tên báo thức theo mặc định
                name1 = name_Alarm.getHint().toString();
            } else {
                name1 = name_Alarm.getText().toString();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        // khởi tạo và gán giá trị cảnh báo
        alarm = new Alarm(hour_x, minute_x, name1, toggleOn);

        return alarm;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        choose_whale_sound = (int) id;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}