package com.anguomob.lib.view.round;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.StateSet;
import android.view.View;
import android.widget.TextView;

import com.liuan.lib.R;


public class RoundViewDelegate {
    private View view;
    private Context context;
    private GradientDrawable gd_background = new GradientDrawable();
    private GradientDrawable gd_background_press = new GradientDrawable();
    private int backgroundColor;
    private int backgroundPressColor;
    private int backgroundDisableColor;
    private int cornerRadius;
    private int cornerRadius_TL;
    private int cornerRadius_TR;
    private int cornerRadius_BL;
    private int cornerRadius_BR;
    private int strokeWidth;
    private int strokeColor;
    private int strokePressColor;
    private int strokeDisableColor;
    private int textPressColor;
    private int textDisableColor;
    private boolean isRadiusHalfHeight;
    private boolean isWidthHeightEqual;
    private boolean isRippleEnable;
    private float[] radiusArr = new float[8];

    public RoundViewDelegate(View view, Context context, AttributeSet attrs) {
        this.view = view;
        this.context = context;
        obtainAttributes(context, attrs);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundTextView);
        backgroundColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundColor, Color.TRANSPARENT);
        backgroundPressColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundPressColor, Integer.MAX_VALUE);
        backgroundDisableColor = ta.getColor(R.styleable.RoundTextView_rv_backgroundDisableColor, Integer.MAX_VALUE);
        cornerRadius = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius, 0);
        strokeWidth = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_strokeWidth, 0);
        strokeColor = ta.getColor(R.styleable.RoundTextView_rv_strokeColor, Color.TRANSPARENT);
        strokePressColor = ta.getColor(R.styleable.RoundTextView_rv_strokePressColor, Integer.MAX_VALUE);
        strokeDisableColor = ta.getColor(R.styleable.RoundTextView_rv_strokeDisableColor, Integer.MAX_VALUE);
        textPressColor = ta.getColor(R.styleable.RoundTextView_rv_textPressColor, Integer.MAX_VALUE);
        textDisableColor = ta.getColor(R.styleable.RoundTextView_rv_textDisableColor, Integer.MAX_VALUE);
        isRadiusHalfHeight = ta.getBoolean(R.styleable.RoundTextView_rv_isRadiusHalfHeight, false);
        isWidthHeightEqual = ta.getBoolean(R.styleable.RoundTextView_rv_isWidthHeightEqual, false);
        cornerRadius_TL = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_TL, 0);
        cornerRadius_TR = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_TR, 0);
        cornerRadius_BL = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_BL, 0);
        cornerRadius_BR = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_BR, 0);
        isRippleEnable = ta.getBoolean(R.styleable.RoundTextView_rv_isRippleEnable, true);

        ta.recycle();
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        setBgSelector();
    }

    public void setBackgroundPressColor(int backgroundPressColor) {
        this.backgroundPressColor = backgroundPressColor;
        setBgSelector();
    }

    public void setBackgroundDisableColor(int backgroundDisableColor) {
        this.backgroundDisableColor = backgroundDisableColor;
        setBgSelector();
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = dp2px(cornerRadius);
        setBgSelector();
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = dp2px(strokeWidth);
        setBgSelector();
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        setBgSelector();
    }

    public void setStrokePressColor(int strokePressColor) {
        this.strokePressColor = strokePressColor;
        setBgSelector();
    }

    public void setStrokeDisableColor(int strokeDisableColor) {
        this.strokeDisableColor = strokeDisableColor;
        setBgSelector();
    }

    public void setTextPressColor(int textPressColor) {
        this.textPressColor = textPressColor;
        setBgSelector();
    }

    public void setTextDisableColor(int textDisableColor) {
        this.textDisableColor = textDisableColor;
        setBgSelector();
    }

    public void setIsRadiusHalfHeight(boolean isRadiusHalfHeight) {
        this.isRadiusHalfHeight = isRadiusHalfHeight;
        setBgSelector();
    }

    public void setIsWidthHeightEqual(boolean isWidthHeightEqual) {
        this.isWidthHeightEqual = isWidthHeightEqual;
        setBgSelector();
    }

    public void setCornerRadius_TL(int cornerRadius_TL) {
        this.cornerRadius_TL = cornerRadius_TL;
        setBgSelector();
    }

    public void setCornerRadius_TR(int cornerRadius_TR) {
        this.cornerRadius_TR = cornerRadius_TR;
        setBgSelector();
    }

    public void setCornerRadius_BL(int cornerRadius_BL) {
        this.cornerRadius_BL = cornerRadius_BL;
        setBgSelector();
    }

    public void setCornerRadius_BR(int cornerRadius_BR) {
        this.cornerRadius_BR = cornerRadius_BR;
        setBgSelector();
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getBackgroundPressColor() {
        return backgroundPressColor;
    }

    public int getBackgroundDisableColor() {
        return backgroundDisableColor;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public int getStrokePressColor() {
        return strokePressColor;
    }

    public int getStrokeDisableColor() {
        return strokeDisableColor;
    }

    public int getTextPressColor() {
        return textPressColor;
    }

    public int getTextDisableColor() {
        return textDisableColor;
    }

    public boolean isRadiusHalfHeight() {
        return isRadiusHalfHeight;
    }

    public boolean isWidthHeightEqual() {
        return isWidthHeightEqual;
    }

    public int getCornerRadius_TL() {
        return cornerRadius_TL;
    }

    public int getCornerRadius_TR() {
        return cornerRadius_TR;
    }

    public int getCornerRadius_BL() {
        return cornerRadius_BL;
    }

    public int getCornerRadius_BR() {
        return cornerRadius_BR;
    }

    protected int dp2px(float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    protected int sp2px(float sp) {
        final float scale = this.context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    @TargetApi(21)
    private GradientDrawable createGradientDrawable(ColorStateList color, ColorStateList strokeColor) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);

        if (cornerRadius_TL > 0 || cornerRadius_TR > 0 || cornerRadius_BR > 0 || cornerRadius_BL > 0) {
            /**The corners are ordered top-left, top-right, bottom-right, bottom-left*/
            radiusArr[0] = cornerRadius_TL;
            radiusArr[1] = cornerRadius_TL;
            radiusArr[2] = cornerRadius_TR;
            radiusArr[3] = cornerRadius_TR;
            radiusArr[4] = cornerRadius_BR;
            radiusArr[5] = cornerRadius_BR;
            radiusArr[6] = cornerRadius_BL;
            radiusArr[7] = cornerRadius_BL;
            gd.setCornerRadii(radiusArr);
        } else {
            gd.setCornerRadius(cornerRadius);
        }

        gd.setStroke(strokeWidth, strokeColor);

        return gd;
    }

    private GradientDrawable createGradientDrawable(int color, int strokeColor) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);

        if (cornerRadius_TL > 0 || cornerRadius_TR > 0 || cornerRadius_BR > 0 || cornerRadius_BL > 0) {
            /**The corners are ordered top-left, top-right, bottom-right, bottom-left*/
            radiusArr[0] = cornerRadius_TL;
            radiusArr[1] = cornerRadius_TL;
            radiusArr[2] = cornerRadius_TR;
            radiusArr[3] = cornerRadius_TR;
            radiusArr[4] = cornerRadius_BR;
            radiusArr[5] = cornerRadius_BR;
            radiusArr[6] = cornerRadius_BL;
            radiusArr[7] = cornerRadius_BL;
            gd.setCornerRadii(radiusArr);
        } else {
            gd.setCornerRadius(cornerRadius);
        }

        gd.setStroke(strokeWidth, strokeColor);

        return gd;
    }

    public void setBgSelector() {
        StateListDrawable bg = new StateListDrawable();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && isRippleEnable) {

            ColorStateList bgColorStateList = getColorSelector(backgroundColor,
                    getValidColor(backgroundPressColor, backgroundColor),
                    getValidColor(backgroundDisableColor, backgroundColor));

            ColorStateList strokeColorStateList = getColorSelector(strokeColor,
                    getValidColor(strokePressColor, strokeColor),
                    getValidColor(strokeDisableColor, strokeColor));

            GradientDrawable gd = createGradientDrawable(bgColorStateList, strokeColorStateList);
            RippleDrawable rippleDrawable = new RippleDrawable(
                    ColorStateList.valueOf(Color.parseColor("#33000000")),
                    gd,
                    null);
            view.setBackground(rippleDrawable);
        } else {
            if (backgroundPressColor != Integer.MAX_VALUE || strokePressColor != Integer.MAX_VALUE) {
                GradientDrawable gd = createGradientDrawable(
                        getValidColor(backgroundPressColor, backgroundColor),
                        getValidColor(strokePressColor, strokeColor));
                bg.addState(new int[]{android.R.attr.state_pressed}, gd);
            }

            if (backgroundDisableColor != Integer.MAX_VALUE || strokeDisableColor != Integer.MAX_VALUE) {
                GradientDrawable gd = createGradientDrawable(
                        getValidColor(backgroundDisableColor, backgroundColor),
                        getValidColor(strokeDisableColor, strokeColor));
                bg.addState(new int[]{-android.R.attr.state_enabled}, gd);
            }
            if (backgroundColor != Integer.MAX_VALUE || strokeColor != Integer.MAX_VALUE) {
                GradientDrawable gd = createGradientDrawable(backgroundColor, strokeColor);
                bg.addState(StateSet.WILD_CARD, gd);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) { // 16
                view.setBackground(bg);
            } else {
                //noinspection deprecation
                view.setBackgroundDrawable(bg);
            }
        }

        if (view instanceof TextView) {
            if (textPressColor != Integer.MAX_VALUE || textDisableColor != Integer.MAX_VALUE) {
                ColorStateList textColors = ((TextView) view).getTextColors();

                int defaultColor = textColors.getDefaultColor();

                ColorStateList colorStateList = getColorSelector(defaultColor,
                        getValidColor(textPressColor, defaultColor),
                        getValidColor(textDisableColor, defaultColor));

                ((TextView) view).setTextColor(colorStateList);
            }
        }
    }

    private ColorStateList getColorSelector(int normalColor, int pressedColor, int disableColor) {
        return new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_activated},
                        new int[]{-android.R.attr.state_enabled},
                        StateSet.WILD_CARD
                },
                new int[]{
                        pressedColor,
                        pressedColor,
                        pressedColor,
                        disableColor,
                        normalColor
                }
        );
    }


    /**
     * 获取有效color
     *
     * @param color
     * @param defaultColor
     * @return
     */
    private int getValidColor(int color, int defaultColor) {
        return color == Integer.MAX_VALUE ? defaultColor : color;
    }
}
