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
import club.cqut.collageanswer.model.QuestionAnswer;

/**
 * Created by Howe on 2015/7/3.
 */
public class MyAnswerAdapter extends BaseAdapter {

    public List<QuestionAnswer> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    static class ViewHolder{
        ImageView headImage;
        TextView username;
        TextView question;
        TextView answer;
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

    public MyAnswerAdapter(Context context){
        this.context =context;
        this.inflater = LayoutInflater.from(context);
    }

    public List<QuestionAnswer> getList() {
        return list;
    }

    public void setList(List<QuestionAnswer> list) {
        this.list = list;
    }

    /**
     * 得到最新数据往list里面添加
     */
    public void addNewQA(List<QuestionAnswer> questions_answer){
        list.clear();
        list.addAll( questions_answer);
    }

    /**
     * 加载更多的时候往list的最后加数据
     */
    public void addOldQA(List<QuestionAnswer> questions_answer){
        list.addAll(list.size(), questions_answer);
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
            convertView = inflater.inflate(R.layout.layout_item_myanswer_model, null);
            holder.headImage = (ImageView) convertView.findViewById(R.id.cover);
            holder.username = (TextView) convertView.findViewById(R.id.questioner_name);
            holder.question = (TextView) convertView.findViewById(R.id.question_content);
            holder.answer = (TextView) convertView.findViewById(R.id.answer_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage(list.get(position).getQuestionCover(), holder.headImage, options);
        holder.username.setText(list.get(position).getQuestionName());
        holder.question.setText(list.get(position).getContent());
        holder.answer.setText(list.get(position).getAnswer());

        return convertView;
    }
}
