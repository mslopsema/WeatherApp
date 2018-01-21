package mikeslopsema.weather;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class HttpThread {
    private static HttpThread mHttpThread;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private Context mCtx;

    private HttpThread(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static synchronized HttpThread getInstance(Context context) {
        if (mHttpThread == null) mHttpThread = new HttpThread(context);
        return mHttpThread;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        return mRequestQueue;
    }

    public <T> void addRequest(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
