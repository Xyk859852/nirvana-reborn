package com.phoenix.nirvana.process.engine.config;


import com.phoenix.nirvana.process.engine.model.ProcessModel;

import java.util.List;

/**
 * @author xuyongkang
 * @version 1.0
 */
public interface ProcessParser {

    /**
     * 解析器
     *
     * @return 解析结果
     */
    List<ProcessModel> parse() throws Exception;

}
