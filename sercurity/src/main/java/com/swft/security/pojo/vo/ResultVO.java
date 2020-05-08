package com.swft.security.pojo.vo;

import lombok.*;

/**
 * @author JiangTeJie
 * @since 2020/5/7 18:25
 */

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultVO<T> {

    private String status;

    private String msg;

    private T data;

    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setStatus("1");
        resultVO.setMsg("登录成功");
        return resultVO;
    }
}
