package com.github.houbb.markdown.toc.vo;

import com.github.houbb.markdown.toc.constant.TocConstant;

import java.util.LinkedList;
import java.util.List;

public class TocVo {

    private String origin;

    private int level;

    private String tocTitle;

    private String tocHref;

    private TocVo parent;   //父节点

    private List<TocVo> children = new LinkedList<>();

    public TocVo(String origin) {
        this.origin = origin;
        init();
    }

    private void init() {
        if("ROOT".equals(this.origin)) {
            return;
        }

        String[] strings = this.origin.split("\\s+");
        this.tocTitle = strings[1];
        this.tocHref = TocConstant.ASTERISK+strings[1];
        this.level = strings[0].length();
    }

    public static TocVo rootToc() {
        TocVo tocVo = new TocVo("ROOT");
        tocVo.setLevel(0);
        return tocVo;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTocTitle() {
        return tocTitle;
    }

    public void setTocTitle(String tocTitle) {
        this.tocTitle = tocTitle;
    }

    public String getTocHref() {
        return tocHref;
    }

    public void setTocHref(String tocHref) {
        this.tocHref = tocHref;
    }

    public List<TocVo> getChildren() {
        return children;
    }

    public void setChildren(List<TocVo> children) {
        this.children = children;
    }

    public TocVo getParent() {
        return parent;
    }

    public void setParent(TocVo parent) {
        this.parent = parent;
    }
}
