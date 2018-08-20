package com.iigo.library;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * @author SamLeung
 * @e-mail 729717222@qq.com
 * @github https://github.com/samlss
 * @csdn https://blog.csdn.net/Samlss
 * @description A cube 3D rotation loading view.
 */
public class CubeLoadingView extends ViewGroup{
    private final static int DEFAULT_SIZE = 200; //200 px
    private final static int FIXED_CHILD_COUNT = 5; //The fixed child count.

    private final static int DEFAULT_FIRST_COLOR = Color.parseColor("#F8C66B");
    private final static int DEFAULT_SECOND_COLOR = Color.parseColor("#00AACF");
    private final static int DEFAULT_THIRD_COLOR = Color.parseColor("#214C59");
    private final static int DEFAULT_FOURTH_COLOR = Color.parseColor("#00B39F");

    private int mWidth;
    private int mHeight;

    private Camera mCamera;
    private Matrix mMatrix;

    private ValueAnimator mValueAnimator;
    private int mAnimatedValue;
    private long mAnimatorPlayTime;

    private View mSide1View;
    private View mSide2View;
    private View mSide3View;
    private View mSide4View;
    private View mSide5View;

    private int mFirstSideColor  = DEFAULT_FIRST_COLOR;
    private int mSecondSideColor = DEFAULT_SECOND_COLOR;
    private int mThirdSideColor  = DEFAULT_THIRD_COLOR;
    private int mFourthSideColor = DEFAULT_FOURTH_COLOR;

    public CubeLoadingView(Context context) {
        this(context, null);
    }

    public CubeLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CubeLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        parseAttr(attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CubeLoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        parseAttr(attrs);
        init();
    }

    private void parseAttr(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CubeLoadingView);
        mFirstSideColor  = typedArray.getColor(R.styleable.CubeLoadingView_firstSideColor, DEFAULT_FIRST_COLOR);
        mSecondSideColor = typedArray.getInteger(R.styleable.CubeLoadingView_secondSideColor, DEFAULT_SECOND_COLOR);
        mThirdSideColor  = typedArray.getInteger(R.styleable.CubeLoadingView_thirdSideColor, DEFAULT_THIRD_COLOR);
        mFourthSideColor = typedArray.getInteger(R.styleable.CubeLoadingView_fourthSideColor, DEFAULT_FOURTH_COLOR);
        typedArray.recycle();
    }

    private void init(){
        mCamera = new Camera();
        mMatrix = new Matrix();

        addChildViews();
    }

    private void addChildViews(){
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mSide1View = new View(getContext());
        mSide2View = new View(getContext());
        mSide3View = new View(getContext());
        mSide4View = new View(getContext());
        mSide5View = new View(getContext());

        mSide1View.setBackgroundColor(mFirstSideColor);
        mSide5View.setBackgroundColor(mFirstSideColor);
        mSide2View.setBackgroundColor(mSecondSideColor);
        mSide3View.setBackgroundColor(mThirdSideColor);
        mSide4View.setBackgroundColor(mFourthSideColor);

        addViewInLayout(mSide1View, -1, layoutParams);
        addViewInLayout(mSide2View, -1, layoutParams);
        addViewInLayout(mSide3View, -1, layoutParams);
        addViewInLayout(mSide4View, -1, layoutParams);
        addViewInLayout(mSide5View, -1, layoutParams);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSize  = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int mWidth = DEFAULT_SIZE;
        int mHeight = DEFAULT_SIZE;

        boolean isWidthWrap  = getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean isHeightWrap = getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT;

        if (!isWidthWrap && !isHeightWrap){
            return;
        }

        setMeasuredDimension(isWidthWrap ? mWidth : widthSize, isHeightWrap ? mHeight : heightSize);
    }

    @Override
    protected void onLayout(boolean b, int left, int top, int right, int bottom) {
        int childTop = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                child.layout(0, childTop,
                        child.getMeasuredWidth(), childTop + child.getMeasuredHeight());
                childTop = childTop + child.getMeasuredHeight();
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        release();
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;

        setupAnimator();
    }

    private void setupAnimator(){
        if (getChildCount() < FIXED_CHILD_COUNT){
            return;
        }

        mValueAnimator = ValueAnimator.ofInt(0, mHeight * 4);

        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatedValue = (int) valueAnimator.getAnimatedValue();
                scrollTo(0, mAnimatedValue);
                invalidate();
            }
        });

        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                scrollTo(0, 0);
            }
        });

        mValueAnimator.setInterpolator(new AnticipateOvershootInterpolator(0.8f));
        mValueAnimator.setDuration(2500);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.start();
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        for (int i = 0; i < getChildCount(); i++) {
            drawChild(canvas, i, getChildAt(i));
        }
    }

    /**
     * Draw the child.
     *
     * @param canvas
     * @param index
     * @param child
     * */
    private void drawChild(Canvas canvas, int index, View child){
        int childTop = mHeight * index;

        if (getScrollY() + mHeight < childTop) {
            return;
        }
        if (childTop < getScrollY() - mHeight) {
            return;
        }

        float centerX = mWidth / 2;
        float centerY = (getScrollY() > childTop) ? childTop + mHeight : childTop;

        float degree = 90 * (getScrollY() - childTop) / mHeight;
        if (degree > 90 || degree < -90) {
            return;
        }

        canvas.save();
        mCamera.save();
        mCamera.rotateX(degree);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();

        mMatrix.preTranslate(-centerX, -centerY);
        mMatrix.postTranslate(centerX, centerY);

        canvas.concat(mMatrix);
        drawChild(canvas, child, getDrawingTime());
        canvas.restore();
    }

    public int getFirstSideColor() {
        return mFirstSideColor;
    }

    public int getSecondSideColor() {
        return mSecondSideColor;
    }

    public int getThirdSideColor() {
        return mThirdSideColor;
    }

    public int getFourthSideColor() {
        return mFourthSideColor;
    }

    /**
     * Set the color of first side.
     * */
    public void setFirstSideColor(int firstSideColor) {
        this.mFirstSideColor = firstSideColor;
        mSide1View.setBackgroundColor(mFirstSideColor);
        mSide5View.setBackgroundColor(mFirstSideColor);
        invalidate();
    }

    /**
     * Set the color of second side.
     * */
    public void setSecondSideColor(int secondSideColor) {
        this.mSecondSideColor = secondSideColor;
        mSide2View.setBackgroundColor(mSecondSideColor);
        invalidate();
    }

    /**
     * Set the color of third side.
     * */
    public void setThirdSideColor(int thirdSideColor) {
        this.mThirdSideColor = thirdSideColor;
        mSide3View.setBackgroundColor(mThirdSideColor);
        invalidate();
    }

    /**
     * Set the color of fourth side.
     * */
    public void setFourthSideColor(int fourthSideColor) {
        this.mFourthSideColor = fourthSideColor;
        mSide4View.setBackgroundColor(mFourthSideColor);
        invalidate();
    }

    /**
     * Pause the animation.
     * */
    public void pause(){
        if (mValueAnimator != null && mValueAnimator.isRunning()){
            mAnimatorPlayTime = mValueAnimator.getCurrentPlayTime();
            mValueAnimator.cancel();
        }
    }

    /**
     * Resume the animation.
     * */
    public void resume(){
        if (mValueAnimator != null && !mValueAnimator.isRunning()){
            mValueAnimator.setCurrentPlayTime(mAnimatorPlayTime);
            mValueAnimator.start();
        }
    }

    /**
     * Start the animation.
     * */
    public void start(){
        mAnimatorPlayTime = 0;
        if (mValueAnimator != null){
            mValueAnimator.start();
        }
    }

    /**
     * Cancel the animation.
     * */
    public void stop(){
        if (mValueAnimator != null){
            mValueAnimator.cancel();
        }
    }

    /**
     * Release this view when you do not need it.
     * */
    public void release(){
        stop();
        if (mValueAnimator != null){
            mValueAnimator.removeAllUpdateListeners();
            mValueAnimator.removeAllListeners();
        }
    }
}
