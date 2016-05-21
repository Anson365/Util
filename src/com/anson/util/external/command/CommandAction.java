package com.anson.util.external.command;

/**
 * Created by ludao on 16/5/16.
 */
public interface CommandAction {
    <T>T execute(T t);
}
