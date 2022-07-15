package com.lc.backtrace;

public class Backtrace_arch {
    /**
     * result = []
     * def backtrack(Path, Choices):
     *      if 满足结束条件:
     *         result.add(路径)
     *         return
     *
     *     for 选择 in 选择列表:
     *         做选择
     *         backtrack(路径, 选择列表)
     *         撤销选择
     *
     *     for 选择 in 选择列表:
     *      # 做选择
     *      将该选择从选择列表移除
     *      路径.add(选择)
     *      backtrack(路径, 选择列表)
     *      # 撤销选择
     *      路径.remove(选择)
     *      将该选择再加入选择列表
     */
}
