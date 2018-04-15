
package onboardinganimation.ketki.com.onboardinganim;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.LinearLayout;

/**
 * Created by ketki on 03/06/16.
 */
public class MarqueeLayout extends LinearLayout {

    public MarqueeLayout(Context context, Animation animation) {
        super(context);
    }

    public MarqueeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(0, 0);
    }

    public MarqueeLayout(Context context, AttributeSet attrs, int defStyleAttr, Animation animation) {
        super(context, attrs, defStyleAttr);
    }


    public MarqueeLayout(Context context) {
        super(context);
    }
}


