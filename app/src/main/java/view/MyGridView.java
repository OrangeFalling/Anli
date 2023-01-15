package view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * author: created by song
 * description: 自定义GridView,实现适配ListView。
 */
public class MyGridView extends GridView {
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 测量自定义视图控件的大小
     * @param widthMeasureSpec: 宽
     * @param heightMeasureSpec: 高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 为了使ListView嵌套GridView显示完整
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
