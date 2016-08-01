package jp.ucs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class enchodeFilter implements Filter{
    /**
     * フィルタがインスタンス化される時に呼ばれるメソッド
     * このメソッドでの処理はnop
     * @param fConfig フィルタこんフィグ
     * @see enchodeFilter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        // nop
        
    }
    
    /**
     * @see enchodeFilter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // TODO 自動生成されたメソッド・スタブ
        
    }    

}
