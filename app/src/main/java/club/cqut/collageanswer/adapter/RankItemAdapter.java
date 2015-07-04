package club.cqut.collageanswer.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.model.User;

/**
 * 排行榜适配器
 * Created by Howe on 2015/6/30.
 */
public class RankItemAdapter extends BaseAdapter {

    static class ViewHolder{
        ImageView sortImage;
        ImageView headImage;
        TextView sortNum;
        TextView username;
        TextView sex;
    }

    private Context context;
    private List<User> list = new ArrayList<>();
    private LayoutInflater inflater;

    // DisplayImageOptions的初始化
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.mipmap.bootstrap_1)
            .showImageOnLoading(R.mipmap.bootstrap_1)
            .showImageOnFail(R.mipmap.ic_launcher)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();

    public RankItemAdapter(Context context){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    /**
     * 得到最新数据往list里面添加
     */
    public void addNewUser(List<User> users){
        list.clear();
        list.addAll( users);
    }

    /**
     * 加载更多的时候往list的最后加数据
     */
    public void addOldUser(List<User> users){
        list.addAll(list.size(), users);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.rank_item_model, null);
            holder.sortImage = (ImageView) convertView.findViewById(R.id.sort_bg);
            holder.headImage = (ImageView) convertView.findViewById(R.id.rank_cover);
            holder.sortNum = (TextView) convertView.findViewById(R.id.rank_num);
            holder.username = (TextView) convertView.findViewById(R.id.rank_name);
            holder.sex = (TextView) convertView.findViewById(R.id.rank_sex);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage(list.get(position).getHeadImage(), holder.headImage, options);

        holder.sortNum.setText(position + 1+ "");
        holder.username.setText(list.get(position).getUsername());

//        holder.sex.setText("男");
        if(list.get(position).getSex() == null){
            holder.sex.setText("");
        } else {
            if (list.get(position).getSex() == true) {
                holder.sex.setText("男");
            } else {
                holder.sex.setText("女");
            }
        }

        return convertView;
    }
}
