package com.github.houbb.markdown.toc.core.impl;

import com.github.houbb.markdown.toc.constant.VersionConstant;
import com.github.houbb.markdown.toc.core.MarkdownToc;
import com.github.houbb.markdown.toc.exception.MarkdownTocRuntimeException;
import com.github.houbb.markdown.toc.support.I18N;
import com.github.houbb.markdown.toc.util.CollectionUtil;
import com.github.houbb.markdown.toc.util.FileUtil;
import com.github.houbb.markdown.toc.util.StringUtil;
import com.github.houbb.markdown.toc.util.ThreadLocalUtil;
import com.github.houbb.markdown.toc.util.ThreadUtil;
import com.github.houbb.markdown.toc.vo.TocGen;
import com.github.houbb.markdown.toc.vo.config.TocConfig;

import org.apiguardian.api.API;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * # 的标题形式
 *
 * @author bbhou
 * @version 1.0.0
 * @since 1.0.0, 2018/01/30
 */
@API(status = API.Status.MAINTAINED, since = VersionConstant.V_1_0_0)
public class AtxMarkdownToc implements MarkdownToc {

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

    /**
     * 是否添加 order 编号(默认没有编号)
     * @since 1.0.5
     */
    private boolean order = false;
    //endregion

    @Override
    public TocGen genTocFile(String filePath) {
        checkPath(filePath);

        TocConfig config = buildConfig();
        return ThreadLocalUtil.genTocFile(filePath, config);
    }

    /**
     * 构建配置信息
     * @return 配置信息
     */
    private TocConfig buildConfig() {
        TocConfig tocConfig = new TocConfig();
        tocConfig.setCharset(charset);
        tocConfig.setSubTree(subTree);
        tocConfig.setWrite(write);
        tocConfig.setOrder(this.order);
        return tocConfig;
    }

    @Override
    public List<TocGen> genTocDir(String dirPath) {
        checkPath(dirPath);

        final List<TocGen> tocGens = new CopyOnWriteArrayList<>();
        final TocConfig config = buildConfig();

        Path path = Paths.get(dirPath);
        if (!Files.isDirectory(path)) {
            final String msg = String.format(I18N.get(I18N.Key.pathIsNotDirectory), dirPath);
            throw new MarkdownTocRuntimeException(msg);
        }
        List<Path> paths = FileUtil.getMdFilePathList(path, subTree);

        //1. 列表为空
        if(CollectionUtil.isEmpty(paths)) {
            return Collections.emptyList();
        }

        //2. 是否为只有单个文件
        int bestThreadNum = ThreadUtil.bestThreadNum(paths.size());
        if(bestThreadNum <= 1) {
            final Path firstPath = paths.get(0);
            final TocGen tocGen = genTocFile(firstPath.toString());
            return Collections.singletonList(tocGen);
        }

        //3. 多个文件多线程生成
        //TODO: 后期统一使用 Async 框架
        ExecutorService executorService = Executors.newFixedThreadPool(bestThreadNum);

        List<Future<TocGen>> futureTasks = new ArrayList<>();
        for(final Path filePath : paths) {
            Callable<TocGen> tocGenCallable = new Callable<TocGen>() {
                @Override
                public TocGen call() {
                    return ThreadLocalUtil.genTocFile(filePath.toString(), config);
                }
            };

            Future<TocGen> tocGenFuture = executorService.submit(tocGenCallable);
            futureTasks.add(tocGenFuture);
        }
        executorService.shutdown();
        ThreadLocalUtil.clear();

        try {
            for(Future<TocGen> tocGenFuture : futureTasks) {
                TocGen tocGen = tocGenFuture.get();
                tocGens.add(tocGen);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new MarkdownTocRuntimeException(e);
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
     * 设置是否设置编号
     * @param order 编号
     * @return this
     */
    public AtxMarkdownToc order(final boolean order) {
        this.order = order;
        return this;
    }

    /**
     * 校验路径
     *
     * @param path 文件路径
     */
    private void checkPath(final String path) {
        if (StringUtil.isEmpty(path)) {
            throw new MarkdownTocRuntimeException(I18N.get(I18N.Key.pathIsNotAllowEmpty));
        }
    }

}
