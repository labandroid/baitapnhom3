package nhom3.edu.androialarm1.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;
import java.util.Random;
import android.util.Log;
import android.os.Vibrator;


import nhom3.edu.androialarm1.R;
import nhom3.edu.androialarm1.receiver.AlarmReceiver;
import nhom3.edu.androialarm1.ultil.Constants;

public class AlarmService extends Service {
    MediaPlayer mediaPlayer; // this object to manage media

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO: processing on and off ringtone
        // get string from intent
        String on_Off = intent.getExtras().getString("ON_OFF");
        Integer whale_sound_choice = intent.getExtras().getInt("whale_choice");

        switch (on_Off) {
            case Constants.ADD_INTENT: // if string like this set start media
                int min = 1;
                int max = 13;

                Random r = new Random();
                int random_number = r.nextInt(max - min + 1) + min;
                Log.e("random number is ", String.valueOf(random_number));


                if (random_number == 1) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_bubblenet_and_vocals);

                }
                else if (random_number == 2) {
                    // create an instance of the media player
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_contact_call_moo);

                }
                else if (random_number == 3) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_contact_call_whup);
                }
                else if (random_number == 4) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_feeding_call);

                }
                else if (random_number == 5) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_flipper_splash);
                }
                else if (random_number == 6) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_tail_slaps_with_propeller_whine);
                }
                else if (random_number == 7) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_whale_song);
                }
                else if (random_number == 8) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_whale_song_with_outboard_engine_noise);
                }
                else if (random_number == 9) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_wheeze_blows);
                }
                else if (random_number == 10) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_echolocation_clicks);
                }
                else if (random_number == 11) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_offshore);
                }
                else if (random_number == 12) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_resident);
                }
                else if (random_number == 13) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_transient);
                }
                else {
                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_resident);
                }
                if (whale_sound_choice == 1) {
                    // create an instance of the media player
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_bubblenet_and_vocals);
                    // start the ringtone
                }
                else if (whale_sound_choice == 2) {
                    // create an instance of the media player
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_contact_call_moo);
                }
                else if (whale_sound_choice == 3) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_contact_call_whup);
                }
                mediaPlayer.start();
                break;

            case Constants.OFF_INTENT:
                // this check if user pressed cancel
                // get the alarm cancel id to check if equal the
                // pendingIntent'trigger id(pendingIntent request code)
                // the AlarmReceiver.pendingIntentId is taken from AlarmReceiver
                // when one pendingIntent trigger
                int alarmId = intent.getExtras().getInt("AlarmId");
                // check if mediaPlayer created or not and if media is playing and id of
                // alarm and trigger pendingIntent is same  then stop music and reset it
                if (mediaPlayer != null && mediaPlayer.isPlaying() && alarmId == AlarmReceiver.pendingId) {
                    // stop media
                    mediaPlayer.stop();
                    // reset it
                    mediaPlayer.reset();
                }
                break;




        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //TODO: Xử lý logic tắt nhạc chuông
        mediaPlayer.stop();
        mediaPlayer.reset();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}