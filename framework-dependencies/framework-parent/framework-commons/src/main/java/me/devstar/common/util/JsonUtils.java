/**
 * 
 */
package me.devstar.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json Utilities
 * @author sudden(sch0718@naver.com)
 */
public class JsonUtils {

	private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);

	/**
	 * JSON 문자열을 Object로 parsing한다.
	 * @param json
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T parse(String json, Class<T> clazz) throws Exception {
		T result = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.readValue(json, clazz);
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			throw e;
		}
		return result;
	}

	/**
	 * Object를 JSON 문자열로 변환한다.
	 * @param o
	 * @return
	 */
	public static String toJson(Object o) {
		String json = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(o);
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			json = "";
		}
		return json;
	}
}
