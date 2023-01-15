package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.anli.R;
import com.example.anli.ShowImageActivity;

import java.util.ArrayList;
import java.util.HashMap;

import view.MyGridView;

public class MyListViewAdapter extends BaseAdapter {
    //图片数组
    private int[] img = {R.mipmap.img, R.mipmap.img_1,
                            R.mipmap.img_2, R.mipmap.img_3,
                            R.mipmap.img_4, R.mipmap.img_5,
                            R.mipmap.img_6, R.mipmap.img_7,R.mipmap.img_8};
    //加载图片的数组
    // HashMap<String, Object> 中String 为图片的名字，Object 为加载的图片。
    ArrayList<HashMap<String, Object>> imageList;

    private Context context;

    /**
     * 构造函数
     * @param context: 上下文
     */
    public MyListViewAdapter(Context context) {
        this.context = context;
        imageList = new ArrayList<>();
        for (int i=0; i< 9; i++){
            HashMap<String,Object> map = new HashMap<>();
            map.put("image",img[i]); //将图片放进map中
            imageList.add(map); //将图片添加到数组中
        }
    }

    /**
     * list的条数
     * @return
     */
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 5;
    }

    /**
     * 获取绑定单个item视图
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder = new MyHolder();
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_layout,null);
            myHolder.titleIV = convertView.findViewById(R.id.list_item_title_image);
            myHolder.titleTV = convertView.findViewById(R.id.list_item_title_text);
            myHolder.gridView = convertView.findViewById(R.id.list_item_grid_view);
            convertView.setTag(myHolder);
        }else {
            myHolder = (MyHolder) convertView.getTag();
        }
        myHolder.titleTV.setText("这是朋友圈模拟的第"+(position+1)+"数据");
        SimpleAdapter simpleAdapter = new SimpleAdapter(context,imageList,R.layout.item_grid_view_layout,new String[]{"image"},new int[]{R.id.grid_item_image} );
        myHolder.gridView.setAdapter(simpleAdapter);
        //点击item的点击事件
        myHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, ShowImageActivity.class);
                intent.putExtra("id",position); //传递位置
                intent.putExtra("image", img); //传递图片
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    /**
     * 设置item的控件
     */
    class MyHolder{
        ImageView titleIV;
        TextView titleTV;
        MyGridView gridView;
    }
}
