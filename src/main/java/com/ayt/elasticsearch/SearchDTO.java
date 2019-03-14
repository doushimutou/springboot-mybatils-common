package com.ayt.elasticsearch;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description
 * Author ayt  on
 */
@Data
@Getter
@Setter
public class SearchDTO {
    @NotNull(message = "索引不能为空")
    private String index;
    private String routing;
    @NotNull(message = "类型不能为空")
    private String type;
    @NotNull
    private QueryBuilder queryBuilder;
    private List<FieldSortBuilder> sortBuilders;
    private  String[] includes;
    private String[] excludes;
    private int pagesize =1000;
    private int currentPage = 0;
}
