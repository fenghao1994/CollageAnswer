package club.cqut.collageanswer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import club.cqut.collageanswer.R;
import club.cqut.collageanswer.model.Notice;

/**
 * Created by fenghao on 2015/7/5.
 */
public class NoticeItemAdapter extends BaseAdapter {

    static class ViewHolder{
        TextView title;
        TextView content;
    }

    private Context context;
    public List<Notice> list = new ArrayList<>();
    private LayoutInflater inflater;

    public NoticeItemAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * 得到最新数据往list里面添加
     */
    public void addNewNotice(List<Notice> notices){
        list.clear();
        list.addAll( notices);
    }

    /**
     * 加载更多的时候往list的最后加数据
     * @param notices
     */
    public void addOldNotice (List<Notice> notices){
        list.addAll(list.size(), notices);
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
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_item_notice_model, null);
            holder.title = (TextView) convertView.findViewById(R.id.notice_title);
            holder.content = (TextView) convertView.findViewById(R.id.notice_content);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(list.get(position).getTitle());
        holder.content.setText("\u3000\u3000" + list.get(position).getContent());
        return convertView;
    }
}
