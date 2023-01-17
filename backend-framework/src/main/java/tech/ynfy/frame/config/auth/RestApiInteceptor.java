/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tech.ynfy.frame.config.auth;


import cn.hutool.core.util.ObjectUtil;
import tech.ynfy.common.annotation.Pass;
import tech.ynfy.common.constant.JwtConstants;
import tech.ynfy.common.constant.RedisConstant;
import tech.ynfy.frame.module.Result;
import tech.ynfy.frame.util.RenderUtil;
import tech.ynfy.frame.util.SpringUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import tech.ynfy.frame.util.JwtTokenUtil;
import tech.ynfy.frame.util.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * Rest 移动端-Api接口鉴权
 * 在WebMvcConfiguration.java中配置拦截哪些接口
 *
 */
@Component
public class RestApiInteceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {
            return true;
        }
        return check(request, response, handler);
    }

    private boolean check(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // ①:START 方法注解级拦截器
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 有 @Pass 注解，不需要认证
        Pass methodAnnotation = method.getAnnotation(Pass.class);
        if (methodAnnotation != null) {
            return true;
        }
        
        final String authToken = request.getHeader(JwtConstants.AUTH_HEADER);
        if (authToken == null) {
            //header 没有 header
            RenderUtil.renderJson(response, Result.authExpire());
            return false;
        }

        //验证token是否过期,包含了验证jwt是否正确
        try {
            boolean flag = JwtTokenUtil.isTokenExpired(authToken);
            if (flag) {//真正jwt过期失效
                RenderUtil.renderJson(response, Result.authExpire());
                return false;
            } else {
                if (redisUtil == null) {
                    redisUtil = SpringUtil.getBean(RedisUtil.class);
                }
                String userId = JwtTokenUtil.getUsernameFromToken(authToken);
                Object object = redisUtil.hget(RedisConstant.USER_KEY, userId);
                if (ObjectUtil.isEmpty(object) || !authToken.equals(object.toString())) {//过期：redis中不存在过期或者不相等
                    RenderUtil.renderJson(response, Result.authExpire());
                    return false;
                }
                request.setAttribute(JwtConstants.CURRENT_USER, userId);
            }
        } catch (JwtException e) {
            //有异常就是token解析失败
            RenderUtil.renderJson(response, Result.authExpire());
            return false;
        }
        return true;
    }

}
