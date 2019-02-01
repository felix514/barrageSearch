package com.group4.barrageSearch.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BarrageInfoImpl implements Serializable {
    private String content;
    private int times;
}
