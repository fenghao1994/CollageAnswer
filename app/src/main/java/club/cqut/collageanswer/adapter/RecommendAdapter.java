package club.cqut.collageanswer.adapter;

import android.content.Context;
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
        View view = null;

        if(convertView == null){
            view = inflater.inflate(R.layout.layout_item_question_model, null);
        } else {
            view = convertView;
        }

        ImageView cover = (ImageView) view.findViewById(R.id.question_head);
        TextView name = (TextView) view.findViewById(R.id.question_name);
        TextView read_num = (TextView) view.findViewById(R.id.read_num);
        TextView question_title = (TextView) view.findViewById(R.id.question_title);
        TextView question_lable = (TextView) view.findViewById(R.id.question_label);

        //头像
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));

        name.setText(list.get(position).getUserName());
        Log.w("000",list.get(position).getReadNum()+"");
        read_num.setText(list.get(position).getReadNum());
        question_title.setText(list.get(position).getTitle());
        question_lable.setText(list.get(position).getLabel());

        return view;
    }
}
