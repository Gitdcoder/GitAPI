package net.gitcoder.api.java.utility;

import net.gitcoder.api.bukkit.module.tag.api.TagData;

import java.util.concurrent.ConcurrentHashMap;

public class TagDataMapUtil
    extends ConcurrentHashMap<String, TagData> {

    @Override
    public TagData get(Object key) {
        if(key instanceof String) {
            key = TeamTagUtil.parseName(((String)key));
        }
        return super.get(key);
    }

    @Override
    public TagData remove(Object key) {
        if(key instanceof String) {
            key = TeamTagUtil.parseName(((String)key));
        }
        return super.remove(key);
    }
}
