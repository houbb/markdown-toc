package com.github.houbb.markdown.toc.vo;

import com.github.houbb.markdown.toc.constant.VersionConstant;
import com.github.houbb.markdown.toc.support.IncreaseMap;

import org.apiguardian.api.API;

import java.util.LinkedList;
import java.util.List;

/**
 *  toc值对象
 *
 * @author bbhou
 * @version 1.0.0
 * @since 1.0.0, 2018/01/30
 */
@API(status = API.Status.INTERNAL, since = VersionConstant.V_1_0_0)
public class TocVo {
    /**
     * ROOT 节点名称
     */
    private static final String ROOT_NAME = "ROOT";

    /**
     * 起源
     */
    private String origin;

    /**
     * 水平
     */
    private int level;

    /**
     * toc标题
     */
    private String tocTitle;

    /**
     * toc href
     */
    private String tocHref;

    /**
     * 父节点
     */
    private TocVo parent;

    /**
     * 孩子
     */
    private List<TocVo> children = new LinkedList<>();

    /**
     * 自增 MAP
     */
    private final IncreaseMap increaseMap;

    /**
     * 编号序号
     * @since 1.0.5
     */
    private int order;

    /**
     * 编号序号
     * @since 1.0.5
     */
    private String orderDesc;

    /**
     * 空格缩进
     * @since 1.0.5
     */
    private String indent;

    /**
     *  toc值对象
     *
     * @param origin 起源
     * @param increaseMap map
     */
    public TocVo(String origin, IncreaseMap increaseMap) {
        this.origin = origin;
        this.increaseMap = increaseMap;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        if (ROOT_NAME.equals(this.origin)) {
            return;
        }

        String[] strings = this.origin.split("\\s+");
        this.tocTitle = this.origin.substring(strings[0].length()).trim();
        this.tocHref = increaseMap.buildActualName(tocTitle);
        this.level = strings[0].length();
    }

    /**
     * 根源toc
     *
     * @param increaseMap map 集合
     * @return com.github.houbb.markdown.toc.vo.TocVo
     */
    public static TocVo rootToc(IncreaseMap increaseMap) {
        TocVo tocVo = new TocVo(ROOT_NAME, increaseMap);
        tocVo.setLevel(0);
        return tocVo;
    }


    /**
     * 得到起源
     *
     * @return java.lang.String
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * 设置原点
     *
     * @param origin 起源
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * 得到水平
     *
     * @return int
     */
    public int getLevel() {
        return level;
    }

    /**
     * 设定等级
     *
     * @param level 水平
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * 得到toc标题
     *
     * @return java.lang.String
     */
    public String getTocTitle() {
        return tocTitle;
    }

    /**
     * 设置toc标题
     *
     * @param tocTitle toc标题
     */
    public void setTocTitle(String tocTitle) {
        this.tocTitle = tocTitle;
    }

    /**
     * 得到toc href
     *
     * @return java.lang.String
     */
    public String getTocHref() {
        return tocHref;
    }

    /**
     * 设置为hc
     *
     * @param tocHref toc href
     */
    public void setTocHref(String tocHref) {
        this.tocHref = tocHref;
    }

    /**
     * 得到孩子
     *
     * @return java.util.List
     */
    public List<TocVo> getChildren() {
        return children;
    }

    /**
     * 设置孩子
     *
     * @param children 孩子
     */
    public void setChildren(List<TocVo> children) {
        this.children = children;
    }

    /**
     * 得到父母
     *
     * @return com.github.houbb.markdown.toc.vo.TocVo
     */
    public TocVo getParent() {
        return parent;
    }

    /**
     * 设置父母
     *
     * @param parent 亲
     */
    public void setParent(TocVo parent) {
        this.parent = parent;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getIndent() {
        return indent;
    }

    public void setIndent(String indent) {
        this.indent = indent;
    }
}
