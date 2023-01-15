package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawCircle extends View {
    private Paint paint; // 画笔
    //x, y 是画圆的时候的圆心坐标
    //ox, oy 是过去的圆心坐标
    private float x=300, y = 300, ox, oy;

    /**
     * 创建一个DrawCircle对象
     * @param context
     */
    public DrawCircle(Context context) {
        super(context);
    }

    /**
     * 反射，用于布局视图的反射
     * @param context
     * @param attrs
     */
    public DrawCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 实现画圆
     * @param canvas: 画布
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setColor(Color.RED);// 设置画笔颜色
        paint.setAntiAlias(true);
        canvas.drawCircle(x,y,80,paint); // 横坐标，纵坐标，半径，画笔。
        oy = y;
        ox = x;
    }

    /**
     * 设置坐标
     * @param q: 横坐标
     * @param w: 纵坐标
     */
    public void setXY(float q, float w){
        x = q;
        y = w;
        invalidate(); //刷新视图
    }

    /**
     * 将圆心坐标传递给活动页
     * @return
     */
    public Float[] getOldValue(){
        return new Float[]{ox,oy};
    }
}
