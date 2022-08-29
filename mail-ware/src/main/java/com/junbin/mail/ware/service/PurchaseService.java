package com.junbin.mail.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.junbin.common.utils.PageUtils;
import com.junbin.mail.ware.entity.PurchaseEntity;
import com.junbin.mail.ware.vo.MergeVo;
import com.junbin.mail.ware.vo.PurchaseDoneVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author sujunbin
 * @email sujunbin@aill.com
 * @date 2022-08-08 17:57:38
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageUnreceive(Map<String, Object> params);

    @Transactional(rollbackFor = Exception.class)
    void mergePurchase(MergeVo mergeVo);



    void received(List<Long> ids);

    void done(PurchaseDoneVo doneVo);


}

