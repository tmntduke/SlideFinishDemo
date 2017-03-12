package tmnt.example.slidefinishdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by tmnt on 2017/3/12.
 */

public class SlideFinishLayout extends RelativeLayout {

    private Scroller mScroller;
    private ViewGroup mViewGroup;
    private int parentWidth;
    private OnSlideFinishListener mOnSlideFinishListener;

    private int downX = 0;
    private int dowmY = 0;
    private int move;
    private boolean isFinish;

    private static final String TAG = "SlideFinishLayout";
    private int mLastMotionY;
    private int mLastMotionX;

    public void setOnSlideFinishListener(OnSlideFinishListener onSlideFinishListener) {
        mOnSlideFinishListener = onSlideFinishListener;
    }

    public SlideFinishLayout(Context context) {
        super(context);
        init(context);
    }

    public SlideFinishLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlideFinishLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            mViewGroup = (ViewGroup) getParent();
            parentWidth = getWidth();
            Log.i(TAG, "onLayout: " + parentWidth);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getRawX();
                dowmY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int currX = (int) event.getRawX();
                int currY = (int) event.getRawY();
                Log.i(TAG, "onTouchEvent: " + currX);

                if (currX - downX >= 0) {
                    mViewGroup.scrollTo(-(currX - downX), 0);
                    postInvalidate();
                }


                break;
            case MotionEvent.ACTION_UP:
                int currX1 = (int) event.getRawX();
                int currY1 = (int) event.getRawY();
                move = currX1 - downX;
                Log.i(TAG, "onTouchEvent: move-->" + move);
                int s = parentWidth / 3;
                if (move > s && move > 0) {
                    finish();
                } else if (move < s && move > 0) {
                    close();
                }

                break;

        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int y = (int) ev.getRawY();
        int x = (int) ev.getRawX();
        boolean resume = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 发生down事件时,记录y坐标
                mLastMotionY = y;
                mLastMotionX = x;
                resume = false;
                break;
            case MotionEvent.ACTION_MOVE:
                // deltaY > 0 是向下运动,< 0是向上运动
                int deltaY = y - mLastMotionY;
                int deleaX = x - mLastMotionX;

                if (Math.abs(deleaX) > Math.abs(deltaY)) {
                    resume = true;
                } else {
                    resume = false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return resume;
    }

    private void close() {
        isFinish = false;
        mScroller.startScroll(mViewGroup.getScrollX(), 0, move, 0, 1500);
        postInvalidate();

    }

    private void finish() {
        isFinish = true;
        Log.i(TAG, "finish: " + mViewGroup.getScrollX());
        mScroller.startScroll(mViewGroup.getScrollX(), 0, -(parentWidth + mViewGroup.getScrollX()) + 1, 0, 1500);
        postInvalidate();

    }

    private boolean getLayoutChilden(View view1) {
        int count = ((ViewGroup) view1).getChildCount();

        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (view instanceof ViewGroup && !(view instanceof AbsListView)) {
                getLayoutChilden(view);
            } else if (view instanceof AbsListView) {
                return true;
            }

        }
        return false;
    }

    interface OnSlideFinishListener {
        void slideFinish();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            mViewGroup.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();

            if (mScroller.isFinished() && isFinish) {
                if (mOnSlideFinishListener != null) {
                    mOnSlideFinishListener.slideFinish();
                }
            }
        }
    }
}
