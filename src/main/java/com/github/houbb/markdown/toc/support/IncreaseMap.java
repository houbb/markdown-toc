package com.github.houbb.markdown.toc.support;

import com.github.houbb.markdown.toc.constant.TocConstant;
import com.github.houbb.markdown.toc.constant.VersionConstant;
import com.github.houbb.markdown.toc.util.SpecialCharUtil;
import com.github.houbb.markdown.toc.util.StringUtil;

import org.apiguardian.api.API;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 增量 map
 * @author houbinbin
 * @since 1.0.0
 */
@API(status = API.Status.INTERNAL, since = VersionConstant.V_1_0_0)
public class IncreaseMap {

    private Map<String, AtomicInteger> map = new ConcurrentHashMap<>();

    /**
     * 构建确切的名称
     * @param originalName 原始名称
     * @return 最后的构建结果
     */
    public String buildActualName(final String originalName) {
        String actualName;
        String tocHref = getTocHref(originalName);
        AtomicInteger value = map.get(tocHref);
        if(value != null) {
            int count = value.incrementAndGet();
            actualName = TocConstant.ASTERISK+tocHref+TocConstant.MINUS+count;
        } else {
            AtomicInteger one = new AtomicInteger(0);
            map.put(tocHref, one);
            actualName = TocConstant.ASTERISK+tocHref;
        }
        return actualName;
    }

    /**
     * 对于首字母如果是
     * @param tocTitle toc 标题
     * @return 对应的连接名称
     */
    private static String getTocHref(final String tocTitle) {
        if(StringUtil.isEmpty(tocTitle)) {
            return tocTitle;
        }

        //1. GITHUB 对于大写的处理
        String result = tocTitle.toLowerCase();

        //2. GITHUB 对于空格的处理
        result = result.replace(' ', '-');

        //3. 对于特殊字符的处理
        return SpecialCharUtil.filterSpecial(result);
    }

}
