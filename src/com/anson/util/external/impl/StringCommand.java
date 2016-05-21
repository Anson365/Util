package com.anson.util.external.impl;

import com.anson.util.external.command.CommandAction;

/**
 * Created by ludao on 16/5/21.
 */
public class StringCommand implements CommandAction {

    @Override
    public <T> T execute(T t) {
        if(t == null){
            return t;
        }else {
            if(t instanceof String){
                t = (T)(t.toString() + "?");
            }
        }
        return t;
    }
}
