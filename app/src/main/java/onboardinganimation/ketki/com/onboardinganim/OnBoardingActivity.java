package onboardinganimation.ketki.com.onboardinganim;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Timer;
import java.util.TimerTask;

public class OnBoardingActivity extends Activity {

    private Integer[] boardingImages = new Integer[]{R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6};
    private int count = 0, textCount = 0;
    private final int INITIAL_DELAY = 1000;
    private final int ANIMATION_INTERVAL = 1400;
    private MarqueeLayout ml1, ml2;
    private ImageView iv1, iv2;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);
        initUI();
    }

    private void initUI() {
        ml1 = (MarqueeLayout) findViewById(R.id.ml_image1);
        ml2 = (MarqueeLayout) findViewById(R.id.ml_image2);
        iv1 = (ImageView) findViewById(R.id.iv_image1);
        iv2 = (ImageView) findViewById(R.id.iv_image2);
        lv = (ListView) findViewById(R.id.lv_titles);
    }

    @Override
    protected void onResume() {
        super.onResume();
        count = 0;
        textCount = boardingImages.length - 1;
        iv1.setBackgroundResource(boardingImages[count]);

        //init Title ListView
        lv.setAdapter(new OnBoardingBottomTextAdapter(this));
        lv.setSelection(boardingImages.length - 1);
        startAnimation();

    }

    private void startLoginActivity() {

    }

    private void startAnimation() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                createRightAnimator();
                createLeftAnimator();
            }
        }, INITIAL_DELAY);

        final Timer textTimer = new Timer();
        textTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (textCount == 0) {
                            textTimer.cancel();
                            showInfoScreen();
                            return;
                        }
                        if (textCount <= boardingImages.length - 1) {
                            textCount = textCount - 1;
                            lv.smoothScrollToPositionFromTop(textCount, 0, ANIMATION_INTERVAL / 2);
                        }

                    }
                });
            }
        }, INITIAL_DELAY + ANIMATION_INTERVAL, 2100);
    }

    public class ClickHandler {
        public void onParentCLick(View view) {
            showInfoScreen();
        }

        public void onGetStartedClick(View view) {
            startLoginActivity();
        }
    }

    public synchronized void createRightAnimator() {
        count = count + 1;


        iv1.setBackgroundResource(boardingImages[count - 1]);
        if (count == 1) {
            ml1.setAlpha(1f);
            ml1.setX(0f);
            ml1.animate().alpha(0f).setDuration(1400).x(-100f).setDuration(1400).setInterpolator(new AccelerateDecelerateInterpolator())
                    .setListener(null).setListener(new Animator.AnimatorListener() {
                                                       @Override
                                                       public void onAnimationStart(Animator animation) {

                                                       }

                                                       @Override
                                                       public void onAnimationEnd(Animator animation) {
                                                           createRightAnimator();
                                                       }

                                                       @Override
                                                       public void onAnimationCancel(Animator animation) {

                                                       }

                                                       @Override
                                                       public void onAnimationRepeat(Animator animation) {

                                                       }
                                                   }
            );
            ;
        } else {
            ml1.setAlpha(0f);
            ml1.setX(0f);
            ml1.animate().alpha(1f).setDuration(ANIMATION_INTERVAL).setListener(null).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ml1.setAlpha(1f);
                    ml1.animate().alpha(0f).setDuration(ANIMATION_INTERVAL).setListener(null).setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            if (count < boardingImages.length - 1)
                                createRightAnimator();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).x(-100f).setDuration(ANIMATION_INTERVAL * 2).setInterpolator(new AccelerateInterpolator());
        }
    }

    public synchronized void createLeftAnimator() {
        count = count + 1;
        iv2.setBackgroundResource(boardingImages[count - 1]);
        ml2.setAlpha(0f);
        ml2.setX(-100f);
        ml2.animate().alpha(1f).setDuration(ANIMATION_INTERVAL).setListener(null).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ml2.setAlpha(1f);
                ml2.animate().alpha(0f).setDuration(ANIMATION_INTERVAL).setListener(null).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (count < boardingImages.length)
                            createLeftAnimator();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).x(0f).setDuration(ANIMATION_INTERVAL * 2).setInterpolator(new AccelerateDecelerateInterpolator());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAffinity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        clearAnimation();
    }

    public void showInfoScreen() {
        clearAnimation();
       /* PreferenceKeeper.setBoardingSeen(true);
        activityOnBoardingBinding.rlImages.setVisibility(View.GONE);
        activityOnBoardingBinding.rlInfo.rlInfo.setVisibility(View.VISIBLE);

        //init info list
        activityOnBoardingBinding.rlInfo.rvInfoList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        activityOnBoardingBinding.rlInfo.rvInfoList.setLayoutManager(llm);
        activityOnBoardingBinding.rlInfo.rvInfoList.setAdapter(new OnBoardingInfoListAdapter(this));*/
    }

    private void clearAnimation() {
        iv1.clearAnimation();
        iv2.clearAnimation();
    }
}
