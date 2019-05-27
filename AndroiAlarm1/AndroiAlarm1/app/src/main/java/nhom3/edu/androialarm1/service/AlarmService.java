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
import android.media.AudioManager;



import nhom3.edu.androialarm1.R;
import nhom3.edu.androialarm1.receiver.AlarmReceiver;
import nhom3.edu.androialarm1.ultil.Constants;

public class AlarmService extends Service {
    MediaPlayer mediaPlayer; // this object to manage media
    int startId;
    boolean isRunning;
    private Vibrator vibrator;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 3000, 1000};
        //TODO: processing on and off ringtone
        // get string from intent
        String on_Off = intent.getExtras().getString("ON_OFF");
        Integer whale_sound_choice = intent.getExtras().getInt("whale_choice");
        Log.e("AAASV",whale_sound_choice+" ");
        assert on_Off != null;
        switch (on_Off) {
            case Constants.ADD_INTENT:
                startId = 1;
                break;
            case Constants.OFF_INTENT:
                startId = 0;
                Log.e("Start ID is ", on_Off);
                break;
            default:
                startId = 0;
                break;
        }
        // if else statements

        // if there is no music playing, and the user pressed "alarm on"
        // music should start playing
        if (!this.isRunning && startId == 1) {
            Log.e("there is no music, ", "and you want start");

            this.isRunning = true;
            this.startId = 0;
            if (whale_sound_choice == 0) {
                // play a randomly picked audio file

                int minimum_number = 1;
                int maximum_number = 13;

                Random random_number = new Random();
                int whale_number = random_number.nextInt(maximum_number + minimum_number);
                Log.e("random number is ", String.valueOf(whale_number));


                if (whale_number == 1) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_bubblenet_and_vocals);
                    mediaPlayer.start();

                } else if (whale_number == 2) {
                    // create an instance of the media player
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_contact_call_moo);
                    mediaPlayer.start();

                } else if (whale_number == 3) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_contact_call_whup);
                    mediaPlayer.start();
                } else if (whale_number == 4) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_feeding_call);
                    mediaPlayer.start();
                } else if (whale_number == 5) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_flipper_splash);
                    mediaPlayer.start();
                } else if (whale_number == 6) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_tail_slaps_with_propeller_whine);
                    mediaPlayer.start();
                } else if (whale_number == 7) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_whale_song);
                    mediaPlayer.start();
                } else if (whale_number == 8) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_whale_song_with_outboard_engine_noise);
                    mediaPlayer.start();
                } else if (whale_number == 9) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_wheeze_blows);
                    mediaPlayer.start();
                } else if (whale_number == 10) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_echolocation_clicks);
                    mediaPlayer.start();
                } else if (whale_number == 11) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_offshore);
                    mediaPlayer.start();
                } else if (whale_number == 12) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_resident);
                    mediaPlayer.start();
                } else if (whale_number == 13) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_transient);
                    mediaPlayer.start();
                } else {
                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_resident);
                    mediaPlayer.start();
                }
            } else if (whale_sound_choice == 1) {
                // create an instance of the media player
                mediaPlayer = MediaPlayer.create(this, R.raw.humpback_bubblenet_and_vocals);
                // start the ringtone
                mediaPlayer.start();
            } else if (whale_sound_choice == 2) {
                // create an instance of the media player
                mediaPlayer = MediaPlayer.create(this, R.raw.humpback_contact_call_moo);
                // start the ringtone
                mediaPlayer.start();
            } else if (whale_sound_choice == 3) {
                mediaPlayer = MediaPlayer.create(this, R.raw.humpback_contact_call_whup);
                mediaPlayer.start();
            } else if (whale_sound_choice == 4) {
                mediaPlayer = MediaPlayer.create(this, R.raw.humpback_feeding_call);
                mediaPlayer.start();
            } else if (whale_sound_choice == 5) {
                Log.e("AAA","Hong nhan");
                mediaPlayer = MediaPlayer.create(this, R.raw.humpback_flipper_splash);
                mediaPlayer.start();
            } else if (whale_sound_choice == 6) {
                mediaPlayer = MediaPlayer.create(this, R.raw.humpback_tail_slaps_with_propeller_whine);
                mediaPlayer.start();
            } else if (whale_sound_choice == 7) {
                mediaPlayer = MediaPlayer.create(this, R.raw.humpback_whale_song);
                mediaPlayer.start();
            } else if (whale_sound_choice == 8) {
                mediaPlayer = MediaPlayer.create(this, R.raw.humpback_whale_song_with_outboard_engine_noise);
                mediaPlayer.start();
            } else if (whale_sound_choice == 9) {
                mediaPlayer = MediaPlayer.create(this, R.raw.humpback_wheeze_blows);
                mediaPlayer.start();
            } else if (whale_sound_choice == 10) {
                mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_echolocation_clicks);
                mediaPlayer.start();
            } else if (whale_sound_choice == 11) {
                mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_offshore);
                mediaPlayer.start();
            } else if (whale_sound_choice == 12) {
                mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_resident);
                mediaPlayer.start();
            } else if (whale_sound_choice == 13) {
                mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_transient);
                mediaPlayer.start();
            } else {
                mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_resident);
                mediaPlayer.start();
            }


        }
        else if (this.isRunning && startId == 0) {
            Log.e("there is music, ", "and you want end");

            // stop the ringtone
            mediaPlayer.stop();
            mediaPlayer.reset();

            this.isRunning = false;
            this.startId = 0;
        }

        // these are if the user presses random buttons
        // just to bug-proof the app
        // if there is no music playing, and the user pressed "alarm off"
        // do nothing
        else if (!this.isRunning && startId == 0) {
            Log.e("there is no music, ", "and you want end");

            this.isRunning = false;
            this.startId = 0;

        }

        // if there is music playing and the user pressed "alarm on"
        // do nothing
        else if (this.isRunning && startId == 1) {
            Log.e("there is music, ", "and you want start");

            this.isRunning = true;
            this.startId = 1;

        }

        // can't think of anything else, just to catch the odd event
        else {
            Log.e("else ", "somehow you reached this");

        }



        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        Log.e("on Destroy called", "ta da");

        super.onDestroy();
        this.isRunning = false;
    }






//        switch (on_Off) {
//            case Constants.ADD_INTENT: // if string like this set start media
//                int min = 1;
//                int max = 13;
//
//                Random r = new Random();
//                int random_number = r.nextInt(max - min + 1) + min;
//                Log.e("random number is ", String.valueOf(random_number));
//
//
//                if (random_number == 1) {
//                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_bubblenet_and_vocals);
//
//                }
//                else if (random_number == 2) {
//                    // create an instance of the media player
//                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_contact_call_moo);
//
//                }
//                else if (random_number == 3) {
//                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_contact_call_whup);
//                }
//                else if (random_number == 4) {
//                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_feeding_call);
//
//                }
//                else if (random_number == 5) {
//                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_flipper_splash);
//                }
//                else if (random_number == 6) {
//                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_tail_slaps_with_propeller_whine);
//                }
//                else if (random_number == 7) {
//                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_whale_song);
//                }
//                else if (random_number == 8) {
//                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_whale_song_with_outboard_engine_noise);
//                }
//                else if (random_number == 9) {
//                    mediaPlayer = MediaPlayer.create(this, R.raw.humpback_wheeze_blows);
//                }
//                else if (random_number == 10) {
//                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_echolocation_clicks);
//                }
//                else if (random_number == 11) {
//                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_offshore);
//                }
//                else if (random_number == 12) {
//                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_resident);
//                }
//                else if (random_number == 13) {
//                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_transient);
//                }
//                else {
//                    mediaPlayer = MediaPlayer.create(this, R.raw.killerwhale_resident);
//                }
//                mediaPlayer.start();
//                break;
//
//            case Constants.OFF_INTENT:
//                // this check if user pressed cancel
//                // get the alarm cancel id to check if equal the
//                // pendingIntent'trigger id(pendingIntent request code)
//                // the AlarmReceiver.pendingIntentId is taken from AlarmReceiver
//                // when one pendingIntent trigger
//                int alarmId = intent.getExtras().getInt("AlarmId");
//                // check if mediaPlayer created or not and if media is playing and id of
//                // alarm and trigger pendingIntent is same  then stop music and reset it
//                if (mediaPlayer != null && mediaPlayer.isPlaying() && alarmId == AlarmReceiver.pendingId) {
//                    // stop media
//                    mediaPlayer.stop();
//                    // reset it
//                    mediaPlayer.reset();
//                }
//                break;


//
//        }
//        return START_STICKY;


//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //TODO: Xử lý logic tắt nhạc chuông
//        mediaPlayer.stop();
//        mediaPlayer.reset();
//    }
//
 public IBinder onBind(Intent intent) {
   return null;
  }
}