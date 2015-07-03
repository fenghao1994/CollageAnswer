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
import club.cqut.collageanswer.RoungImage.CircleImageView;
import club.cqut.collageanswer.model.User;

/**
 * 排行榜适配器
 * Created by Howe on 2015/6/30.
 */
public class RankItemAdapter extends BaseAdapter {

    static class ViewHolder{
        ImageView sortImage;
        ImageView headImage;
        TextView username;
        TextView sex;
    }

    private Context context;
    private List<User> list = new ArrayList<>();
    private LayoutInflater inflater;

    // DisplayImageOptions的初始化
    DisplayImageOptions options = new DisplayImageOptions.Builder()
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
            convertView = inflater.inflate(R.layout.layout_item_question_model, null);
            holder.sortImage = (ImageView) convertView.findViewById(R.id.sort_num);
            holder.headImage = (ImageView) convertView.findViewById(R.id.rank_cover);
            holder.username = (TextView) convertView.findViewById(R.id.rank_name);
            holder.sex = (TextView) convertView.findViewById(R.id.rank_sex);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage("http://sc.admin5.com/uploads/allimg/100202/1455521K2-2.png", holder.sortImage, options);
        imageLoader.displayImage(list.get(position).getHeadImage(), holder.headImage, options);

        holder.username.setText(list.get(position).getUsername());
        if(list.get(position).getSex()){
            holder.sex.setText("男");
        } else {
            holder.sex.setText("女");
        }

        return convertView;
    }
}
