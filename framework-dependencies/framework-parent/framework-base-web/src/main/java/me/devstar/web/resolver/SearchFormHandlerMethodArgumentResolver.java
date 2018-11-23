package me.devstar.web.resolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import me.devstar.common.entity.SearchForm;

/**
 * 검색 폼 Resolver 구현체
 */
public class SearchFormHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	private static final Logger LOG = LoggerFactory.getLogger(SearchFormHandlerMethodArgumentResolver.class);
	
	public static final String COOKIE_NAME_PAGEABLE_SIZE = "Pageable.size";
	
	private PageableHandlerMethodArgumentResolver pageableResolver;
	
	/**
	 * 생성자.
	 */
	public SearchFormHandlerMethodArgumentResolver(PageableHandlerMethodArgumentResolver pageableResolver) {
		this.pageableResolver = pageableResolver;
	}
	
	/**
	 * 해당 쿠키를 가져온다.
	 * @param request
	 * @param cookieName
	 * @return
	 */
	private Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (cookieName.equals(c.getName())) {
					cookie = c;
					break;
				}
			}
		}
		return cookie;
	}
	
	/**
	 * 해당 이름의 쿠키 값을 가져온다.
	 * @param webRequest
	 * @param cookieName
	 * @return
	 */
	private String getCookieValue(NativeWebRequest webRequest, String cookieName) {
		String value = null;
		Cookie cookie = getCookie(webRequest.getNativeRequest(HttpServletRequest.class), cookieName);
		if (cookie != null) value = cookie.getValue();
		return value;
	}
	
	/**
	 * 해당 이름의 쿠키가 있는지 여부를 리턴한다.
	 * @param webRequest
	 * @param cookieName
	 * @return
	 */
	private boolean hasCookie(NativeWebRequest webRequest, String cookieName) {
		Cookie cookie = getCookie(webRequest.getNativeRequest(HttpServletRequest.class), cookieName);
		return cookie != null;
	}
	
	@Override
	public SearchForm resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		SearchForm searchForm = (SearchForm) parameter.getParameterType().newInstance();
		
		WebDataBinder dataBinder = binderFactory.createBinder(webRequest, searchForm, SearchForm.FORM_NAME);
		if (dataBinder instanceof WebRequestDataBinder) {
			((WebRequestDataBinder) dataBinder).bind(webRequest);
		} else {
			MutablePropertyValues pvs = new MutablePropertyValues(webRequest.getParameterMap());
			dataBinder.bind(pvs);
		}
		
		/**
		 * 파라미터 or 어노테이션으로 설정된 Pageable 를 가져온다.
		 */
		Pageable pageable = pageableResolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
		if (pageableResolver.isFallbackPageable(pageable)) {
			LOG.debug("Search pagination settings by cookie...");
			
			int size = -1;
			if (hasCookie(webRequest, COOKIE_NAME_PAGEABLE_SIZE)) {
				size = NumberUtils.toInt(getCookieValue(webRequest, COOKIE_NAME_PAGEABLE_SIZE), pageable.getPageSize());
			}
			pageable = PageRequest.of(pageable.getPageNumber(), size < 0 ? pageable.getPageSize() : size, pageable.getSort());
		}
		searchForm.setPageable(pageable);
		return searchForm;
	}
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return SearchForm.class.isAssignableFrom(parameter.getParameterType());
	}
}
