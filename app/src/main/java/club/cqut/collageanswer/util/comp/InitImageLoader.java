package club.cqut.collageanswer.util.comp;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import club.cqut.collageanswer.R;

/**
 * imageLoader的初始化
 * Created by fenghao on 2015/6/30.
 */
public class InitImageLoader {
    DisplayImageOptions options;

    public InitImageLoader() {
    }

    public DisplayImageOptions getInitImageLoader(){
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.bootstrap_1)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true).cacheOnDisk(false)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();
        return options;
    }
}
