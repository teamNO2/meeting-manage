/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: 孙克强<sun_kq @ suixingpay.com>
 * @date: 2019/12/18 16:48
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.suixingpay.repository;

import com.suixingpay.entity.Sign;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 柴宇航
 * @date: 2019/12/18 16:48
 * @version: V1.0
 */
@Mapper
@Repository
public interface SignRepository {
    int insertSignuser(String userId, int meetingId, int signIsapply, Date signTime);

    List<Sign> issignuser(int meentingId);
    Integer countSign();
}
