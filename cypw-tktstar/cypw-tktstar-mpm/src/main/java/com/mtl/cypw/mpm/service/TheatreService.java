package com.mtl.cypw.mpm.service;

import com.mtl.cypw.mpm.model.Theatre;

import java.util.List;

/**
 * @author tang.
 * @date 2020/3/3.
 */
public interface TheatreService {
    /**
     * 根据id查询剧院
     *
     * @param theatreId
     * @return
     */
    Theatre getTheatreById(Integer theatreId);

    /**
     * 查询剧院列表
     *
     * @param theatreIds
     * @return
     */
    List<Theatre> getTheatreList(List<Integer> theatreIds);
}
