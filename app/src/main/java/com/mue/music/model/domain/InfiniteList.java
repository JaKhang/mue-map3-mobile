package com.mue.music.model.domain;

import java.util.List;

public class InfiniteList<T> {
    private int total;
    private List<T> content;
    private boolean hasMore;
}