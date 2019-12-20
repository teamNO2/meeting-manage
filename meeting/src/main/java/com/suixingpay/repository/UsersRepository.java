/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: 孙克强<sun_kq@suixingpay.com>
 * @date: 2019/12/18 16:50
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.suixingpay.repository;

import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: 孙克强<sun_kq@suixingpay.com>
 * @date: 2019/12/18 16:50
 * @version: V1.0
 */
@Mapper
@Repository
public interface UsersRepository {
    //根据name查询
    Users selectByName( String userName);
    //模糊查询
    List<Users> findPageWithResultLike(Users users);
    //根据id查询
    Users selectById(String id);

    Integer countUser();


}
