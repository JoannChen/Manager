package com.zuoyu.manager.entity

/**
 * <pre>
 * Function：
 *
 * Created by JoannChen on 2017/9/1 11:04
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * </pre>
 */
class Verify {

    var data: VerifyInfo? = null

    class VerifyInfo {

        //  验证码
        //  验证码创建时间

        var verify: String? = null
        var createtime: String? = null
    }
}