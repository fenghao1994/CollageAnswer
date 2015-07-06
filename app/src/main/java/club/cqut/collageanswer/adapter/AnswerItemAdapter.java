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
import club.cqut.collageanswer.model.Answer;
import club.cqut.collageanswer.util.comp.DateUtil;

/**
 * 答案的适配器
 * Created by fenghao on 2015/7/1.
 */
public class AnswerItemAdapter extends BaseAdapter{

    static class ViewHolder{
        ImageView headImage;
        TextView priseNum;
        TextView roleName;
        TextView answerContent;
        TextView showTime;
    }

    private Context context;
    public List<Answer> list = new ArrayList<>();
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

    public AnswerItemAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    /**
     * 得到最新数据往list里面添加
     */
    public void addNewAnswer(List<Answer> questions){
        list.clear();
        list.addAll( questions);
    }

    /**
     * 加载更多的时候往list的最后加数据
     * @param questions
     */
    public void addOldAnswer(List<Answer> questions){
        list.addAll(list.size(), questions);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
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
            convertView = inflater.inflate(R.layout.layout_item_answer_model, null);
            holder.headImage = (ImageView) convertView.findViewById(R.id.head_image);
            holder.priseNum = (TextView) convertView.findViewById(R.id.prise_num);
            holder.roleName = (TextView) convertView.findViewById(R.id.role_name);
            holder.answerContent = (TextView) convertView.findViewById(R.id.answer_content);
            holder.showTime = (TextView) convertView.findViewById(R.id.show_time);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.priseNum.setText(list.get(position).getPriseNum() + "");
        holder.roleName.setText(list.get(position).getUserRole());
        holder.answerContent.setText(list.get(position).getContext());
        holder.showTime.setText(DateUtil.formatSimple(list.get(position).getShowTime()));
        //imageLoader加载图像
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage(list.get(position).getHeadImage(), holder.headImage, options);
        return convertView;
    }
}
