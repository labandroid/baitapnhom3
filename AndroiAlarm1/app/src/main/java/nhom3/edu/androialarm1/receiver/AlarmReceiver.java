package nhom3.edu.androialarm1.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import nhom3.edu.androialarm1.service.AlarmService;
import nhom3.edu.androialarm1.ultil.Constants;

public class AlarmReceiver extends BroadcastReceiver {
    // this hold pendingIntend id if one pendingIntend trigger. The PendingIntent'id is alarm'id
    public static int pendingId;
    @Override
    public void onReceive(Context context, Intent intent) {

        // TODO: This method is called when the BroadcastReceiver is receiving

        if (intent != null) {
            // dữ information để service
            Intent intentToService = new Intent(context, AlarmService.class);
            try {
                // nhận intent key "intentType"
                String intentType = intent.getExtras().getString("intentType");
                switch (intentType) {
                    case Constants.ADD_INTENT:
                        // chỉ định  pendingId
                        pendingId = intent.getExtras().getInt("PendingId");
                        intentToService.putExtra("ON_OFF", Constants.ADD_INTENT);
                        context.startService(intentToService);

                        break;
                    case Constants.OFF_INTENT:
                        // nhận alarm'id từ extras
                        int alarmId = intent.getExtras().getInt("AlarmId");
                        // sending to AlarmService
                        intentToService.putExtra("ON_OFF", Constants.OFF_INTENT);
                        intentToService.putExtra("AlarmId", alarmId);
                        context.startService(intentToService);

                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Integer your_whale_choice = intent.getExtras().getInt("whale_choice");
            intentToService.putExtra("whale_choice", your_whale_choice);
            context.startService(intentToService);

        }

    }

}