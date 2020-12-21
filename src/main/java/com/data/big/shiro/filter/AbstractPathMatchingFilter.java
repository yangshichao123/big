package com.data.big.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.data.big.util.RequestResponseUtil;
import com.data.big.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Iterator;
import java.util.Set;

/**
 *   重写过滤链路径匹配规则，增加REST风格post,get.delete,put..支持
 * @author tomsun28
 * @date 23:37 2018/4/19
 */
@Slf4j
public abstract class AbstractPathMatchingFilter extends PathMatchingFilter {

    private static final String DEFAULT_PATH_SEPARATOR = "/";

    public AbstractPathMatchingFilter() {

    }

    public void appliedPathsClaer() {
        this.appliedPaths.clear();
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        if (this.appliedPaths != null && !this.appliedPaths.isEmpty()) {

            Set<String> strings = this.appliedPaths.keySet();

            Iterator var3 = strings.iterator();

            String path;

            do {
                if (!var3.hasNext()) {
                    // 其他的判断为JWT错误无效
                    Message message = new Message().error("没有匹配url");
                    RequestResponseUtil.responseWrite(JSON.toJSONString(message),response);
                    return false;
                }

                path = (String)var3.next();
            } while(!this.pathsMatch(path, request));

            log.trace("Current requestURI matches pattern '{}'.  Determining filter chain execution...", path);
            Object config = this.appliedPaths.get(path);
            return this.isFilterChainContinued(request, response, path, config);
        } else {
            if (log.isTraceEnabled()) {
                log.trace("appliedPaths property is null or empty.  This Filter will passthrough immediately.");
            }

            return true;
        }
    }
    private boolean isFilterChainContinued(ServletRequest request, ServletResponse response, String path, Object pathConfig) throws Exception {
        if (this.isEnabled(request, response, path, pathConfig)) {
            if (log.isTraceEnabled()) {
                log.trace("Filter '{}' is enabled for the current request under path '{}' with config [{}].  Delegating to subclass implementation for 'onPreHandle' check.", new Object[]{this.getName(), path, pathConfig});
            }

            return this.onPreHandle(request, response, pathConfig);
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Filter '{}' is disabled for the current request under path '{}' with config [{}].  The next element in the FilterChain will be called immediately.", new Object[]{this.getName(), path, pathConfig});
            }

            return true;
        }
    }

    /**
     * description 重写URL匹配  加入httpMethod支持
     *
     * @param path 1
     * @param request 2
     * @return boolean
     */
    @Override
    protected boolean pathsMatch(String path, ServletRequest request) {
        String requestURI = this.getPathWithinApplication(request);
        if (requestURI != null && requestURI.endsWith(DEFAULT_PATH_SEPARATOR)) {
            requestURI = requestURI.substring(0, requestURI.length() - 1);
        }
        // path: url==method eg: http://api/menu==GET   需要解析出path中的url和httpMethod
        String[] strings = path.split("==");
        if (strings[0] != null && strings[0].endsWith(DEFAULT_PATH_SEPARATOR)) {
            strings[0] = strings[0].substring(0 , strings[0].length() - 1);
        }
        if (strings.length <= 1) {
            // 分割出来只有URL
            return this.pathsMatch(strings[0], requestURI);
        } else {
            // 分割出url+httpMethod,判断httpMethod和request请求的method是否一致,不一致直接false
            String httpMethod = WebUtils.toHttp(request).getMethod().toUpperCase();
            return httpMethod.equals(strings[1].toUpperCase()) && this.pathsMatch(strings[0], requestURI);
        }
    }


    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        return SecurityUtils.getSubject();
    }

    /**
     * description allowed
     *
     * @param var1 1
     * @param var2 2
     * @param var3 3
     * @return boolean
     * @throws Exception when
     */
    protected abstract boolean isAccessAllowed(ServletRequest var1, ServletResponse var2, Object var3) throws Exception;

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return this.onAccessDenied(request, response);
    }

    /**
     * description denied
     *
     * @param var1 1
     * @param var2 2
     * @return boolean
     * @throws Exception when
     */
    protected abstract boolean onAccessDenied(ServletRequest var1, ServletResponse var2) throws Exception;

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return this.isAccessAllowed(request, response, mappedValue) || this.onAccessDenied(request, response, mappedValue);
    }



    protected void saveRequest(ServletRequest request) {
        WebUtils.saveRequest(request);
    }



}
