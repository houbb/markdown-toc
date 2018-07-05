package com.github.houbb.markdown.toc.core.impl;

import com.github.houbb.markdown.toc.constant.TocConstant;
import com.github.houbb.markdown.toc.core.MarkdownToc;
import com.github.houbb.markdown.toc.exception.MarkdownTocRuntimeException;
import com.github.houbb.markdown.toc.support.IncreaseMap;
import com.github.houbb.markdown.toc.util.FileUtil;
import com.github.houbb.markdown.toc.util.StringUtil;
import com.github.houbb.markdown.toc.vo.TocGen;
import com.github.houbb.markdown.toc.vo.TocVo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * # 的标题形式
 *
 * @author bbhou
 * @version 1.0.0
 * @since 1.0.0, 2018/01/30
 */
public class AtxMarkdownToc implements MarkdownToc {

    //region 内部属性
    /**
     * 文件内容列表
     */
    private List<String> fileContentList = new LinkedList<>();

    /**
     * toc str列表
     */
    private List<String> tocStrList = new LinkedList<>();

    /**
     * toc值对象列表
     */
    private List<TocVo> tocVoList = new LinkedList<>();

    /**
     * 结果列表
     */
    private List<String> resultList = new LinkedList<>();

    /**
     * 上一个节点
     */
    private TocVo previous;
    //endregion

    //region 外部配置相关
    /**
     * 编码(默认为utf-8)
     */
    private Charset charset = Charset.forName("UTF-8");

    /**
     * 是否包含子文件夹下的文件(默认为包含)
     */
    private boolean subTree = true;

    /**
     * 是否写入到文件(默认写入)
     */
    private boolean write = true;
    //endregion

    @Override
    public TocGen genTocFile(String filePath) {
        checkPath(filePath);
        Path path = Paths.get(filePath);
        return genTocForFile(path, this.charset);
    }

    @Override
    public List<TocGen> genTocDir(String dirPath) {
        checkPath(dirPath);

        List<TocGen> tocGens = new ArrayList<>();

        Path path = Paths.get(dirPath);
        if (!Files.isDirectory(path)) {
            final String msg = String.format("%s 不是文件夹目录", dirPath);
            throw new MarkdownTocRuntimeException(msg);
        }
        List<Path> paths = FileUtil.getMdFilePathList(path, subTree);
        for(Path filePath : paths) {
            TocGen tocGen = genTocForFile(filePath, charset);
            tocGens.add(tocGen);
        }
        return tocGens;
    }

    /**
     * 创建一个实例
     * @return 新的实例
     */
    public static AtxMarkdownToc newInstance() {
        return new AtxMarkdownToc();
    }

    /**
     * 设置编码
     * @param charset 编码
     * @return this
     */
    public AtxMarkdownToc charset(final String charset) {
        this.charset = Charset.forName(charset);
        return this;
    }
    /**
     * 设置是否递归
     * 1. 注意：只有在文件夹模式下生效
     * @param subTree 是否包含子文件夹元素
     * @return this
     */
    public AtxMarkdownToc subTree(final boolean subTree) {
        this.subTree = subTree;
        return this;
    }

    /**
     * 设置是否写入到文件中
     * @param write 写入文件
     * @return this
     */
    public AtxMarkdownToc write(final boolean write) {
        this.write = write;
        return this;
    }

    /**
     * 校验路径
     *
     * @param path 文件路径
     */
    private void checkPath(final String path) {
        if (StringUtil.isEmpty(path)) {
            throw new MarkdownTocRuntimeException("文件路径不可为空！");
        }
    }

    /**
     * 为单个文件生成 toc
     *
     * TODO:
     * 1. 这里锁需要进行优化
     * 2. 粒度可以更加细致
     * @param path       文件路径
     * @param charset 编码
     */
    private synchronized TocGen genTocForFile(final Path path, final Charset charset) {
        try {
           //2. 校验文件后缀
            if(!FileUtil.isMdFile(path.toString())) {
                throw new MarkdownTocRuntimeException("当前只支持 markdown 文件");
            }

            //3. 文件内容
            initFileContentList(path, charset);

            //4. toc 相关属性
            initToc();

            //5. 回写
            TocGen tocGen = new TocGen(path.toString(), resultList);
            resultList.addAll(fileContentList);
            if(this.write) {
                Files.write(path, resultList, charset);
            }
            return tocGen;
        } catch (IOException e) {
            throw new MarkdownTocRuntimeException(e);
        }
    }

    /**
     * 初始化文件内容
     * 1. 如果文件已经包含了 toc 目录，则进行删除。
     *
     * @param path    文件路径
     * @param charset 文件编码
     * @throws IOException if any
     */
    private void initFileContentList(Path path, Charset charset) throws IOException {
        fileContentList.clear();
        fileContentList = Files.readAllLines(path, charset);

        //原先的目录过滤
        String firstLine = fileContentList.get(0);
        if (firstLine.startsWith(TocConstant.DEFAULT_TOC_HEAD)) {
            Iterator<String> stringIterator = fileContentList.iterator();
            //开头
            nextAndRemove(stringIterator, 2);
            while (stringIterator.hasNext()) {
                String contentTrim = stringIterator.next().trim();
                if (contentTrim.startsWith(TocConstant.STAR)) {
                    stringIterator.remove();
                } else {
                    //直接跳出循环
                    break;
                }
            }
            //移除当前换行
            stringIterator.remove();
            //最后的换行
            nextAndRemove(stringIterator, 1);
        }
    }

    /**
     * 下一个并且移除元素
     *
     * @param stringIterator 迭代
     * @param times          次数
     */
    private void nextAndRemove(Iterator<String> stringIterator, int times) {
        for (int i = 0; i < times; i++) {
            String content = stringIterator.next();
            stringIterator.remove();
        }
    }

    /**
     * 初始化 toc 内容
     */
    private void initToc() {
        //0. 清空原始的数据信息
        resultList.clear();
        tocStrList.clear();
        tocVoList.clear();

        //1. ATX 默认文件头
        resultList.add(TocConstant.DEFAULT_TOC_HEAD + TocConstant.RETURN_LINE);

        //2. 所有 toc 行
        for (String string : fileContentList) {
            String trim = string.trim();
            if (trim.startsWith("#")) {
                tocStrList.add(trim);
            }
        }

        //3. 构建 tocVo
        //3.1 创建一个自增 map，用来处理重复名称
        IncreaseMap increaseMap = new IncreaseMap();

        TocVo root = TocVo.rootToc(increaseMap);
        root.setParent(null);
        //初始化根节点
        tocVoList.add(root);
        previous = root;
        for (String string : tocStrList) {
            addNewToc(string, increaseMap);
        }

        //4. 展现
        showToc(root.getChildren());
        resultList.add(TocConstant.RETURN_LINE);
    }

    /**
     * 显示toc
     *
     * @param tocVoList toc值对象列表
     */
    private void showToc(List<TocVo> tocVoList) {
        if (tocStrList.isEmpty()) {
            return;
        }
        for (TocVo tocVo : tocVoList) {
            String suffix = getSuffix(tocVo.getLevel());
            String tocVoContent = suffix + String.format(TocConstant.TOC_FORMAT,
                    tocVo.getTocTitle(), tocVo.getTocHref());
            resultList.add(tocVoContent);
            showToc(tocVo.getChildren());
        }
    }

    /**
     * 得到后缀
     *
     * @param level 水平
     * @return java.lang.String
     */
    private String getSuffix(int level) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < level - 1; i++) {
            result.append(TocConstant.TWO_BLANK);
        }
        return result.toString();
    }


    /**
     * 添加新的toc
     *
     * @param tocTrimStr toc trim str
     */
    private void addNewToc(String tocTrimStr, IncreaseMap increaseMap) {
        TocVo current = new TocVo(tocTrimStr, increaseMap);

        //1 级目录
        if (current.getLevel() == 1) {
            TocVo root = tocVoList.get(0);
            current.setParent(root);
            //添加到根目录
            root.getChildren().add(current);
        } else if (current.getLevel() <= previous.getLevel()) {
            TocVo previousParent = previous.getParent();
            current.setParent(previousParent);
            previousParent.getChildren().add(current);
            //上一节点的子节点
        } else if (current.getLevel() > previous.getLevel()) {
            current.setParent(previous);
            previous.getChildren().add(current);
        }

        previous = current;
    }

}
