package me.devstar.web.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Request Parameter Logging Interceptor
 */
public class RequestParameterLoggingInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(RequestParameterLoggingInterceptor.class);

	private StopWatch stopWatch;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);

		try {
			if (!stopWatch.isStopped())
				stopWatch.stop();
			if (LOG.isDebugEnabled()) {
				LOG.debug("execute time: " + stopWatch.getTime() + " millisecons.");

				if (modelAndView != null) {
					ObjectMapper mapping = new ObjectMapper();
					for (Entry<String, Object> item : modelAndView.getModel().entrySet()) {
						try {
							LOG.debug("[MODEL] key: " + item.getKey() + ", value: "
									+ mapping.writeValueAsString(item.getValue()));
						} catch (Exception e) {
						}
					}
				}
			}
		} catch (Exception e) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(e.getMessage(), e);
			}
		}
	}

	@SuppressWarnings("unused")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			HttpSession session = request.getSession();
			if (stopWatch == null) {
				stopWatch = new StopWatch();
			}
			stopWatch.reset();
			stopWatch.start();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Access URI: " + request.getRequestURI() + ", Method: " + request.getMethod());
				Map<String, String[]> params = request.getParameterMap();

				Iterator<String> iter = params.keySet().iterator();
				String key = null;
				String[] values = null;
				while (iter.hasNext()) {
					key = iter.next();
					values = params.get(key);
					for (String input : values) {
						LOG.debug("[PARAM] " + key + ": " + input);
					}
				}
			}
		} catch (Exception e) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(e.getMessage(), e);
			}
		}
		return super.preHandle((HttpServletRequest) request, response, handler);
	}
}
