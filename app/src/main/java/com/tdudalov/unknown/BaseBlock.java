package com.tdudalov.unknown;

public class BaseBlock {
    private final int size;
    private final int numOfBlocksHorizontal = 10;
    BaseBlock(int x) {
        size = x / numOfBlocksHorizontal;
    }
}
