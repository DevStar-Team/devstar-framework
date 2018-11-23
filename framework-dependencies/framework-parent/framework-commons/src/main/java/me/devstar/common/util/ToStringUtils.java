package me.devstar.common.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * toString() 유틸리티 클래스
 * @author sudden(sch0718@naver.com)
 */
public class ToStringUtils {

	private static Field[] doFilter(Field[] fields, String[] excepts) {
		List<Field> list = new LinkedList<Field>();
		Field[] filtered;

		boolean exist = false;

		for (Field f : fields) {
			exist = false;

			for (String e : excepts) {
				if (f.getName().equals(e)) {
					exist = true;
					break;
				}
			}

			if (!exist) {
				list.add(f);
			}
		}

		int index = 0;
		filtered = new Field[list.size()];

		for (Field f : list) {
			filtered[index++] = f;
		}

		return filtered;
	}

	private static void fromObjectToString(StringBuilder sb, Object obj, String fieldName) {
		sb.append(fieldName).append(" = [");

		if (obj instanceof Collection) {
			Collection<?> col = (Collection<?>) obj;
			Iterator<?> iter = col.iterator();
			Object item;
			int cnt = 0;

			while (iter.hasNext()) {
				item = iter.next();

				sb.append("{");

				fromObjectToString(sb, item, Integer.toString(cnt++));

				sb.append("}");

				if (cnt < col.size()) {
					sb.append(", ");
				}
			}
		} else {
			if (obj == null) {
				sb.append("null");
			} else if (obj.getClass().isArray()) {
				Object[] objects = (Object[]) obj;

				for (int i = 0; i < objects.length; i++) {
					sb.append("{");

					fromObjectToString(sb, objects[i], Integer.toString(i));

					sb.append("}");

					if (i < objects.length - 1) {
						sb.append(", ");
					}
				}
			} else {
				sb.append(obj.toString());
			}
		}

		sb.append("]");
	}

	private static void getFieldsValue(StringBuilder sb, Field[] fields, Object obj) {
		Field field;

		try {
			for (int i = 0; i < fields.length; i++) {
				field = fields[i];
				field.setAccessible(true);

				Object value = field.get(obj);

				fromObjectToString(sb, value, field.getName());

				if (i < fields.length - 1) {
					sb.append(", ");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 객체의 필드를 문자열로 반환한다.
	 * 
	 * <pre>
	 * 아래와 같이 모든 클래스에 toString() 메서드 오버라이드 구현 시 호출한다.
	 * 
	 * &#64;Override
	 * public String toString() {
	 *   ToStringUtils.toString(this);
	 * }
	 * 
	 * 필드 = [값] 과 같은 형태로 출력되며, 배열이나 Collection인 경우 필드 = [{0 = 값}, {1 = 값}, ..}] 과 같은 형태로 출력된다.
	 * </pre>
	 * 
	 * @param obj 객체
	 * @return 객체의 필드 문자열
	 */
	public static String toString(Object obj) {
		StringBuilder sb = new StringBuilder();

		Class<?> parent = obj.getClass().getSuperclass();
		Field[] fields = null;

		do {
			if (parent != null) {
				fields = parent.getDeclaredFields();
				getFieldsValue(sb, fields, obj);

				parent = parent.getSuperclass();
			}
		} while (parent != null);

		fields = obj.getClass().getDeclaredFields();
		getFieldsValue(sb, fields, obj);

		return sb.toString();
	}

	public static String toString(Object obj, String... exceptFields) {
		StringBuilder sb = new StringBuilder();

		Class<?> parent = obj.getClass().getSuperclass();
		Field[] fields = null;

		do {
			if (parent != null) {
				fields = parent.getDeclaredFields();
				getFieldsValue(sb, doFilter(fields, exceptFields), obj);

				parent = parent.getSuperclass();
			}
		} while (parent != null);

		fields = obj.getClass().getDeclaredFields();
		getFieldsValue(sb, fields, obj);

		return sb.toString();
	}
}
