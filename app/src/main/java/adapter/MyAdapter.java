package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anli.R;

import java.util.ArrayList;
import java.util.List;

import utils.Utils;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    //图标数组
    private int[] icon = {
            R.drawable.wallet,R.drawable.wallet,
            R.drawable.wallet,R.drawable.wallet,
            R.drawable.wallet,R.drawable.wallet,
            R.drawable.wallet,R.drawable.wallet,
            R.drawable.wallet,R.drawable.wallet,
            R.drawable.wallet,R.drawable.wallet,
    };
    //名字数组
    private int[] name = {
            R.string.delete_text_name,R.string.delete_text_name,
            R.string.delete_text_name,R.string.delete_text_name,
            R.string.delete_text_name,R.string.delete_text_name,
            R.string.delete_text_name,R.string.delete_text_name,
            R.string.delete_text_name,R.string.delete_text_name,
            R.string.delete_text_name,R.string.delete_text_name,
    };
    private Context lContext;    //定义上下文
    //图标集合
    private List<Integer> listIcon = new ArrayList<Integer>();
    //名字集合
    private List<Integer> listName = new ArrayList<Integer>();

    public MyAdapter(Context context){
        lContext = context;
        //设置菜单行数与行内图标、名称
        for (int i=0; i<12;i++){
            listIcon.add(icon[i]);
            listName.add(name[i]);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView btn_rv_delete;  //删除按钮
        public TextView name;  //名字
        public ImageView img;  //图标
        public ViewGroup layout_content;   //图标与信息布局
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_rv_delete = itemView.findViewById(R.id.tv_delete);
            name = itemView.findViewById(R.id.rv_item_name);
            img = itemView.findViewById(R.id.rv_item_img);
            layout_content = itemView.findViewById(R.id.rv_layout_content);
        }
    }

    //删除列表行中信息的方法
    public void removeData(int position){
        listIcon.remove(position);    //删除列表行内图标
        listName.remove(position);    //删除行中名字
        notifyItemRemoved(position);  //删除行
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //获取列表中每行的布局文件
        View view  = LayoutInflater.from(lContext).inflate(R.layout.rv_item_layout,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    //设置列表菜单中行内所显示的内容
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.img.setBackgroundResource(listIcon.get(position));   //设置图标
        holder.name.setText(listName.get(position));  //设置名称
        //设置内容布局的宽度为屏幕的宽度
        holder.layout_content.getLayoutParams().width = Utils.getScreenWidth(lContext);
        //设置按钮单击事件
        holder.btn_rv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = holder.getLayoutPosition();  //获取要删除行的位置
                removeData(n);                       //删除列表中指定行
            }
        });
    }

    //返回数据集合中行的总数
    @Override
    public int getItemCount() {
        return listIcon.size();
    }
}
