package adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * ViewPager的适配器
 */
public class MyPageAdapter extends PagerAdapter {
    private List<View> list;

    public MyPageAdapter(List<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list!=null && list.size()>0){
            return list.size();
        }else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }
}
