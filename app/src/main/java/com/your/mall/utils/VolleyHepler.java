package com.your.mall.utils;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.your.mall.application.MallApplication;

public class VolleyHepler {
    private static VolleyHepler hepler;
    private RequestQueue queue;
    private ImageLoader imageLoader;

    private VolleyHepler() {
        queue = getRequestQueue();
        imageLoader = new ImageLoader(queue, AppImageCache.getInstance());
    }

    public static VolleyHepler getInstance() {
        if (hepler == null) {
            hepler = new VolleyHepler();
        }
        return hepler;
    }

    public RequestQueue getRequestQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(MallApplication.getContext());
        }
        return queue;
    }

    public <T> void addRequest(Request<T> request) {
        getRequestQueue().add(request);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
