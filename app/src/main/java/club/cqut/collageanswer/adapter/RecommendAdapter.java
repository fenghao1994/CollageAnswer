package club.cqut.collageanswer.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;
import java.util.zip.Inflater;

import club.cqut.collageanswer.R;
import club.cqut.collageanswer.model.Question;
import club.cqut.collageanswer.util.comp.AnimateFirstDisplayListener;

/**
 * Created by Leero on 2015/7/1.
 */
public class RecommendAdapter extends BaseAdapter{
    private List<Question> list;
    private Context context;
    private LayoutInflater inflater;
    /*图片第一次加载时的监听*/
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public RecommendAdapter(Context context, List<Question> list_){
        this.list = list_;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    static class ViewHolder{
        ImageView cover;
        TextView name;
        TextView read_num;
        TextView question_title;
        TextView question_lable;
    }

    // DisplayImageOptions的初始化
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.mipmap.bootstrap_1)
            .showImageOnLoading(R.mipmap.bootstrap_1)
            .showImageOnFail(R.mipmap.ic_launcher)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();

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
            holder.cover = (ImageView) convertView.findViewById(R.id.question_head);
            holder.name = (TextView) convertView.findViewById(R.id.question_name);
            holder.read_num = (TextView) convertView.findViewById(R.id.read_num);
            holder.question_title = (TextView) convertView.findViewById(R.id.question_title);
            holder.question_lable = (TextView) convertView.findViewById(R.id.question_label);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //头像
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage(list.get(position).getHeadImage(), holder.cover, options);
        holder.name.setText(list.get(position).getUserName());
        holder.read_num.setText(list.get(position).getReadNum());
        holder.question_title.setText(list.get(position).getTitle());
        holder.question_lable.setText(list.get(position).getLabel());
        return convertView;
    }
}
