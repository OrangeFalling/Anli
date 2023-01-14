package view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyFlowLayout extends ViewGroup {
    private static final String TAG = "MyFlowLayout";
    private int mHorizontalSpacing = dp2px(16);   //每个item的横向间距
    private int mVerticalSpacing = dp2px(8);      //每个item的纵向间距
    private List<List<View>> allLines;     //记录所有的行，一行一行的存储，用于layout
    List<Integer> lineHeights = new ArrayList<>();  //记录每一行的行高，用于layout


    /**
     * new一个MyFlowLayout对象
     * @param context
     */
    public MyFlowLayout(Context context) {
        super(context,null);
        Log.i(TAG, "MyFlowLayout: new MyFlowLayout");
    }

    /**
     * 反射
     * 用于xml转换为java时调用
     * @param context
     * @param attrs
     */
    public MyFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        Log.i(TAG, "MyFlowLayout: 反射");
    }

    /**
     * 主题style切换需要
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public MyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "MyFlowLayout: 主题切换");
    }

    private void initMeasureParams(){
        Log.i(TAG, "initMeasureParams: ");
        if (allLines != null){
            allLines.clear();
        }else {
            allLines = new ArrayList<>();
        }
        lineHeights = new ArrayList<>();
    }

    /**
     * 确定子view布局
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, "onLayout: ");
        int lineCount = allLines.size();  //获取layout的行数
        int curL = getPaddingLeft();
        int curT = getPaddingTop();

        for (int i=0; i<lineCount; i++){
            List<View> lineViews = allLines.get(i);
            int lineHeight =lineHeights.get(i);
            for (int j=0; j< lineViews.size(); i++){
                View view = lineViews.get(j);
                int left = curL;
                int top = curT;

                int right = left + view.getMeasuredWidth();
                int bottom = top +view.getMeasuredHeight();

                view.layout(left,top,right,bottom);
                curL = right + mHorizontalSpacing;
            }
            curL = getPaddingLeft();
            curT = curL + lineHeight +mVerticalSpacing;
        }
    }

    /**
     * 测量view的大小
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure: ");
        initMeasureParams();

        //度量孩子
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        //ViewGroup解析的宽度
        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        //ViewGroup解析的高度
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);

        List<View> lineViews = new ArrayList<>();   //保存一行中的所有view
        int lineWidthUsed = 0;    //记录这行已经占用的宽度值
        int lineHeight = 0;       //记录这行的高度

        int parentNeedWidth = 0;   //measure过程中，子View 要求的父ViewGroup的宽
        int parentNeedHeight = 0;  //measure过程中，子View 要求的父ViewGroup的高

        for(int i = 0; i<childCount; i++){
            View childView  = getChildAt(i);
            LayoutParams childLp = childView.getLayoutParams();
            //计算measureSpec
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,paddingLeft+paddingRight, childLp.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,paddingTop+paddingBottom, childLp.height);
            childView.measure(childWidthMeasureSpec,childHeightMeasureSpec);

            //获取子view的宽高
            int childMeasureWidth = childView.getMeasuredWidth();
            int childMeasureHeight = childView.getMeasuredHeight();

            /**
             * 判断换行
             * 通过宽度来判断
             * 通过换行后的每行的行高来获取整个viewGroup的行高
             */
            if (childMeasureWidth + lineWidthUsed + mHorizontalSpacing > selfWidth){  //如果需要换行

                allLines.add(lineViews);
                lineHeights.add(lineHeight);

                //一旦换行，就可以判断当前行需要的宽和高，要记录下来。
                parentNeedHeight = parentNeedHeight + lineHeight +mVerticalSpacing;
                parentNeedWidth = Math.max(parentNeedWidth,lineWidthUsed +mHorizontalSpacing);

                lineViews = new ArrayList<>();
                lineWidthUsed = 0;
                lineHeight = 0;
            }

            //view 是分行layout 的，要记录好每一行用哪些view ， 这样可以方便layout布局
            lineViews.add(childView);
            //每行多有自己的宽高
            lineWidthUsed = lineWidthUsed + childMeasureWidth + mHorizontalSpacing;
            lineHeight = Math.max(lineHeight, childMeasureHeight);
        }

        //根据View的度量结果，来重新度量自己的ViewGroup
        //作为一个ViewGroup，它自己也是一个View，它的大小也需要根据它的父亲给它提供的宽高来度量
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //若父布局有确切的宽高就赋值该确切的宽高，否则赋值计算得出的父布局需要的宽高
        int realWidth = (widthMode == MeasureSpec.EXACTLY) ? selfWidth :parentNeedWidth;
        int realHeight = (heightMode == MeasureSpec.EXACTLY) ? selfHeight :parentNeedHeight;

        //度量自己
        setMeasuredDimension(realWidth,realHeight);
    }

    public static int dp2px(int dp){
        Log.i(TAG, "dp2px: ");
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp, Resources.getSystem().getDisplayMetrics());
    }
}
