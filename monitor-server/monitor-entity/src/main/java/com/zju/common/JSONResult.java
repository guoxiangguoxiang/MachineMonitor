package com.zju.common;


import com.zju.util.ConditionUtil;
import net.sf.json.JSONObject;

/**
 * data为JSON形式的返回类
 */
public class JSONResult extends Result<JSONObject> {

    public static JSONResult isOk() {
        return new JSONResult();
    }

    public static JSONResult isFail() {
        return new JSONResult().status(FAIL);
    }

    public static JSONResult isFail(String msg) {
        return isFail().msg(msg);
    }
    public static JSONResult isFail(ErrorStatusAndMessage msg) {
        return isFail().msg(msg);
    }

    public static JSONResult isFail(ServiceException e) {
        return isFail().msg(e);
    }


    public JSONResult put(Object key, Object value) {
        if (ConditionUtil.isNull(data)) {
            data = new JSONObject();
        }
        data.put(key, value);
        return this;
    }

    @Override
    public JSONResult msg(ServiceException e) {
        return (JSONResult) super.msg(e);
    }

    @Override
    public JSONResult msg(String s) {
        return (JSONResult) super.msg(s);
    }

    @Override
    public JSONResult msg(ErrorStatusAndMessage s) {
        return (JSONResult) super.msg(s);
    }

    @Override
    public JSONResult data(JSONObject data) {
        return (JSONResult) super.data(data);
    }

    @Override
    public JSONResult status(int status) {
        return (JSONResult) super.status(status);
    }
}
