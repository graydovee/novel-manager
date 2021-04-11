package cn.graydove.ndovel.client.filter;

import cn.graydove.ndovel.client.UserContext;
import cn.graydove.ndovel.user.api.constant.AuthConstant;
import cn.graydove.ndovel.user.api.model.vo.UserVO;
import cn.graydove.ndovel.user.api.util.TokenUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author graydove
 */
@Slf4j
public class UserInfoFilter implements Filter {

    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper objectMapper;

    public UserInfoFilter(StringRedisTemplate stringRedisTemplate, ObjectMapper objectMapper) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String header = request.getHeader(AuthConstant.AUTHORIZATION);
        if (StrUtil.isNotBlank(header)) {
            //设置UserContext
            UserContext.set(() -> {
                String key = TokenUtil.toRedisKey(header);
                String userStr = stringRedisTemplate.opsForValue().get(key);
                if (null == userStr) {
                    return null;
                }
                try {
                    return objectMapper.readValue(userStr, UserVO.class);
                } catch (JsonProcessingException e) {
                    log.error(e.getMessage(), e);
                }
                return null;
            });
        }

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContext.remove();
        }
    }
}
