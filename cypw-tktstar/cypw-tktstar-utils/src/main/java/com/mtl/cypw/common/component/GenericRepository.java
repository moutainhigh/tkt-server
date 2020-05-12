package com.mtl.cypw.common.component;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-22 16:07
 */
public interface GenericRepository<PK, T> {

    PK save(T model);
}
