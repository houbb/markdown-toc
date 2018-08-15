package com.github.houbb.markdown.toc.core;

import com.github.houbb.markdown.toc.constant.VersionConstant;
import com.github.houbb.markdown.toc.vo.TocGen;

import org.apiguardian.api.API;

import java.util.List;

/**
 * markdown toc
 * @author bbhou
 * @version 1.0.0
 * @since 1.0.0, 2018/01/30
 */
@API(status = API.Status.MAINTAINED, since = VersionConstant.V_1_0_0)
public interface MarkdownToc {

    /**
     * 生成单个文件的 toc
     * @param filePath 文件路径
     * @return 生成的结果信息
     */
    TocGen genTocFile(final String filePath);

    /**
     * 生成指定目录下的所有 markdown 文件 toc
     * @param dirPath 文件夹路径
     * @return 生成的结果信息列表
     */
    List<TocGen> genTocDir(final String dirPath);

}
