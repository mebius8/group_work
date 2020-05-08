package com.swft.security.pojo.dto;

import lombok.*;

import java.util.List;

/**
 * @author JiangTeJie
 * @since 2020/5/7 18:27
 */
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageDto<T> {

    private Integer total;

    private Integer current;

    private Integer size;

    private List<T> data;
}
