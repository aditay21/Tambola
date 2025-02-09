package com.aditechnology.tambola;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    MyRecyclerViewAdapter adapter;
    Button button_restart;
    TextView clicktext;
    TextView coinTv;
    
    boolean isSpeaking;
    int last;
    TextView lastValue;
    ArrayList<Integer> list;
    final int max = 89;
    final int min = 0;
    /* access modifiers changed from: private */
    public SwitchCompat switch1;
    private InterstitialAd mInterstitialAd; //Infolancers01
    /* renamed from: t1 */
    TextToSpeech f47t1;
    private int mTotalRestartCount;

    public void onItemClick(View view, int i) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);


        MobileAds.initialize((Context) this, (OnInitializationCompleteListener) initializationStatus -> {

        });
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        AdView mAdView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        loadAds();


        this.coinTv = (TextView) findViewById(R.id.coinTv);
        this.clicktext = findViewById(R.id.text);
       
        TextView textView = findViewById(R.id.lastValue);
        textView.setOnClickListener(null);
        this.lastValue = textView;
        textView.setVisibility(View.VISIBLE);
        this.switch1 = findViewById(R.id.switch1);
        this.list = new ArrayList<>();
        String[] strArr = new String[90];
        this.button_restart = (Button) findViewById(R.id.button_restart);
        int i = 0;
        while (i <= 89) {
            int i2 = i + 1;
            String sb = RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED +
                    i2;
            strArr[i] = sb;
            i = i2;
        }
        this.button_restart.setOnClickListener(view -> {
            if (mTotalRestartCount >=1) {
                //  Toast.makeText(MainActivity.this,"Please watch add to unlock restart",Toast.LENGTH_SHORT);
                openDialog();
            }else {
                mTotalRestartCount = mTotalRestartCount+1;
                MainActivity.this.list.clear();
                MainActivity.this.adapter.updateAll();
                MainActivity.this.isSpeaking = false;
                MainActivity.this.coinTv.setText("click To Start");
                MainActivity.this.lastValue.setText("");
                last = 0;
            }
            });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 10));
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, strArr);
        this.adapter = myRecyclerViewAdapter;
        myRecyclerViewAdapter.setClickListener(this);
        recyclerView.setAdapter(this.adapter);
        this.f47t1 = new TextToSpeech(getApplicationContext(), i1 -> {
            if (i1 == 0) {
                if (MainActivity.this.f47t1.isLanguageAvailable(Locale.US) == 0) {
                    MainActivity.this.f47t1.setLanguage(Locale.US);
                }else{
                    MainActivity.this.f47t1.setLanguage(Locale.US);
                }
            } else if (i1 == -1) {
                Toast.makeText(MainActivity.this, "Sorry! Text To Speech failed...", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView gameimg = findViewById(R.id. gmZop);
        gameimg.setOnClickListener(view -> {
            final String url = "https://9712.play.gamezop.com/";
            final CustomTabsIntent customTabsIntent  = new CustomTabsIntent.Builder().build();
            customTabsIntent.launchUrl(this, Uri.parse(url));
        });
        clicktext.setOnClickListener(view -> {
            if (!MainActivity.this.isSpeaking) {
                MainActivity.this.isSpeaking = true;
                MainActivity.this.startGame();
                MainActivity.this.clicktext.setText("Please wait");
            }
        });
        this.coinTv.setOnClickListener(view -> {
            if (!MainActivity.this.isSpeaking) {
                MainActivity.this.isSpeaking = true;
                MainActivity.this.startGame();
                MainActivity.this.clicktext.setText("Please wait");
            }
        });
    }

    private void loadAds() {
        AdRequest adRequest = new AdRequest.Builder().build();
        String tokenString = "";
        if (BuildConfig.DEBUG) {
            // Debug build
            tokenString =  getString(R.string.ads_full_screen_ads_dummy);
            Log.d("BuildType", "Debug Build");
        } else {
            // Release build
            tokenString =  getString(R.string.ads_full_screen_ads);
            Log.d("BuildType", "Release Build");
        }

        InterstitialAd.load(this,tokenString, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("TAG", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i("TAG", loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });
    }

    public void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        //Uncomment the below code to Set the message and title from the strings.xml file
        builder.setMessage("Please watch ads to continue the add").setTitle("Watch Ads");

        //Setting message manually and performing action on button click
        builder.setMessage("Do you want to watch ?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(MainActivity.this);
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.d("TAG", "The ad was dismissed.");
                                mTotalRestartCount =0;
                                MainActivity.this.list.clear();
                                MainActivity.this.adapter.updateAll();
                                MainActivity.this.isSpeaking = false;
                                MainActivity.this.coinTv.setText("click To Start");
                                MainActivity.this.lastValue.setText("");
                                last = 0;
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d("TAG", "The ad failed to show.");
                                mTotalRestartCount =0;
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                mInterstitialAd = null;
                                mTotalRestartCount =0;
                                loadAds();
                                Log.d("TAG", "The ad was shown.");
                            }
                        });
                    } else {
                        mTotalRestartCount =0;
                        Toast.makeText(MainActivity.this,"Ads not prepared yet!!Enjoy our free service",Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                    }
                    dialog.cancel();
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();

                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Watch Ads to restart the game");
        alert.show();
    }

    /* access modifiers changed from: private */
    public void startGame() {
        char c;
        char c2;
        if (this.list.size() < 90) {
            int random = getRandom();
            if (this.last != 0) {
                this.lastValue.setVisibility(View.VISIBLE);
                TextView textView = this.lastValue;
                textView.setText("" + (this.last + 1));
            }
            this.last = random;
            TextView textView2 = this.coinTv;
            StringBuilder sb = new StringBuilder();
            sb.append(RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED);
            int i = random + 1;
            sb.append(i);
            textView2.setText(sb.toString());
            String num = Integer.toString(i);
            if (i > 9) {
                c = num.charAt(0);
                c2 = num.charAt(1);
            } else {
                c = num.charAt(0);
                c2 = 0;
            }
            int numericValue = Character.getNumericValue(c);
            int numericValue2 = Character.getNumericValue(c2);
            if (this.switch1.isChecked()) {
                TextToSpeech textToSpeech = this.f47t1;
              //  textToSpeech.speak(RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED + numericValue, 0, (HashMap) null);
                textToSpeech.speak(String.valueOf(numericValue), TextToSpeech.QUEUE_FLUSH, null, null);
                if (i > 9) {
                    speakSecond(numericValue2, random);
                } else {
                    this.clicktext.setText("Click Here");
                    this.isSpeaking = false;
                }
            } else {
                this.isSpeaking = false;
                this.clicktext.setText("Click Here");
            }
            this.adapter.updateView(random);
            return;
        }
        this.lastValue.setText("");

        this.coinTv.setText("Game\nOver");
    }

    private void speakSecond(final int i, final int i2) {
        new Handler().postDelayed(() -> {
            if (MainActivity.this.switch1.isChecked()) {
                TextToSpeech textToSpeech = MainActivity.this.f47t1;
               // textToSpeech.speak(RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED + i, 0, (HashMap) null);
                textToSpeech.speak(String.valueOf(i), TextToSpeech.QUEUE_FLUSH, null, null);

            }
            MainActivity.this.speakFinal(i2);
        }, 1000);
    }

    /* access modifiers changed from: private */
    public void speakFinal(final int i) {
        new Handler().postDelayed(() -> {
            if (MainActivity.this.switch1.isChecked()) {
                TextToSpeech textToSpeech = MainActivity.this.f47t1;
               // textToSpeech.speak(RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED + (i + 1), 0, (HashMap) null);
                int n = i+1;
                textToSpeech.speak(String.valueOf(n), TextToSpeech.QUEUE_FLUSH, null, null);

            }
            MainActivity.this.isSpeaking = false;
            MainActivity.this.clicktext.setText("Click Here");
        }, 1000);
    }

    private int getRandom() {
        int nextInt = new Random().nextInt(90);
        if (this.list.contains(nextInt)) {
            return getRandom();
        }
        this.list.add(nextInt);
        return nextInt;
    }
}
