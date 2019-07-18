package com.github.houbb.markdown.toc.util;

import com.github.houbb.markdown.toc.constant.TocConstant;
import com.github.houbb.markdown.toc.constant.VersionConstant;

import org.apiguardian.api.API;

import java.util.regex.Pattern;

/**
 * 特殊字符工具类
 * @author bbhou
 * @since 1.0.1
 * @version 1.0.1
 */
@API(status = API.Status.MAINTAINED, since = VersionConstant.V_1_0_1)
public final class SpecialCharUtil {

    /**
     * @since 1.0.8
     */
    private SpecialCharUtil(){}

    /**
     * 标点符号正则
     * @since 1.0.8
     */
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("\\p{P}");

    /**
     * emoji 表情正则表达式
     * https://github.com/zly394/EmojiRegex
     * https://github.com/vdurmont/emoji-java
     * @since 1.0.8
     */
    private static final String EMOJI_PATTERN_STR = "(?:[\\uD83C\\uDF00-\\uD83D\\uDDFF]|[\\uD83E\\uDD00-\\uD83E\\uDDFF]|[\\uD83D\\uDE00-\\uD83D\\uDE4F]|[\\uD83D\\uDE80-\\uD83D\\uDEFF]|[\\u2600-\\u26FF]\\uFE0F?|[\\u2700-\\u27BF]\\uFE0F?|\\u24C2\\uFE0F?|[\\uD83C\\uDDE6-\\uD83C\\uDDFF]{1,2}|[\\uD83C\\uDD70\\uD83C\\uDD71\\uD83C\\uDD7E\\uD83C\\uDD7F\\uD83C\\uDD8E\\uD83C\\uDD91-\\uD83C\\uDD9A]\\uFE0F?|[\\u0023\\u002A\\u0030-\\u0039]\\uFE0F?\\u20E3|[\\u2194-\\u2199\\u21A9-\\u21AA]\\uFE0F?|[\\u2B05-\\u2B07\\u2B1B\\u2B1C\\u2B50\\u2B55]\\uFE0F?|[\\u2934\\u2935]\\uFE0F?|[\\u3030\\u303D]\\uFE0F?|[\\u3297\\u3299]\\uFE0F?|[\\uD83C\\uDE01\\uD83C\\uDE02\\uD83C\\uDE1A\\uD83C\\uDE2F\\uD83C\\uDE32-\\uD83C\\uDE3A\\uD83C\\uDE50\\uD83C\\uDE51]\\uFE0F?|[\\u203C\\u2049]\\uFE0F?|[\\u25AA\\u25AB\\u25B6\\u25C0\\u25FB-\\u25FE]\\uFE0F?|[\\u00A9\\u00AE]\\uFE0F?|[\\u2122\\u2139]\\uFE0F?|\\uD83C\\uDC04\\uFE0F?|\\uD83C\\uDCCF\\uFE0F?|[\\u231A\\u231B\\u2328\\u23CF\\u23E9-\\u23F3\\u23F8-\\u23FA]\\uFE0F?)";

    /**
     * 特殊符号
     */
    private static final String SPECIAL_CHARS = "≠≡⁄≤≥«#©¨!¯&®'$¬%£*+¢¡(≈)§./¦¥,»¸¾¿¼½;:°±¶?·>=´<@♠←↑→♣↓↔♥∩♦∫–—‚’×‘^„”◊“†‡•∂€…∏™‰″′˜−∑‹∞÷~|›√‾￥`️";

    /**
     * 过滤特殊字符
     * @param string 待验证字符
     * @return 结果
     */
    public static String filterSpecial(String string) {
        if(StringUtil.isEmpty(string)) {
            return string;
        }

        // 替换掉表情
        // github 部分表情是不一致的，暂时不做处理。
        String trimEmoji = string.replaceAll(EMOJI_PATTERN_STR, StringUtil.EMPTY);

        // 处理每一个字节
        char[] chars = trimEmoji.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(char c : chars) {
            String cStr = String.valueOf(c);
            //1.-_ 直接添加
            if(TocConstant.MINUS.equals(cStr)
                || TocConstant.UNDERLINE.equals(cStr)) {
                stringBuilder.append(c);
                continue;
            }
            //2. 特殊字符
            if(isSpecialChar(cStr)
                    || isPunctuation(cStr)) {
                continue;
            }
            //3. 其他
            stringBuilder.append(c);
        }

        // 过滤掉 pattern
        return stringBuilder.toString();
    }

    /**
     * 是否为特殊字符
     * （2）正则匹配的标点符号
     * （3）emoji 符号
     * （4）其他特殊符号
     * 统一放在此处判断
     * @return 是否
     * @since 1.0.8
     */
    private static boolean isSpecialChar(final String string) {
        return SPECIAL_CHARS.contains(string);
    }

    /**
     * 是否为标点符号
     * 中文符号：参考：https://blog.csdn.net/ztf312/article/details/54310542
     * @param string 字符
     * @return 结果
     */
    private static boolean isPunctuation(String string) {
        return PUNCTUATION_PATTERN.matcher(string).find();
    }

}
