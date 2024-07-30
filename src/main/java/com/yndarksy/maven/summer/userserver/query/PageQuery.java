package com.yndarksy.maven.summer.userserver.query;

import lombok.Data;

@Data
public class PageQuery {
    private Long pageNumber;
    private Long pageSize;
    private String name;
    private Integer depotId;
}
