package com.junbin.mail.mailproduct.DTO;

import com.junbin.mail.mailproduct.entity.AttrEntity;
import lombok.Data;

/**
 * @ Author     ：苏俊滨
 * @ Date       ：Created in 8:54 2022/8/19
 * @ Description：${description}
 **/
@Data
public class AttrResVO extends AttrEntity {
    /*
    * categroyname
    *
    * groupname
    *
    * */


    private String catelogName;


    private String groupName;


    private Long[] catelogPath;
}
