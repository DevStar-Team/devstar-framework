package me.devstar.common.jpa;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * <pre>
 * Page{@link org.springframework.data.domain.Page}의 Json serialize가 Spring Boot 2.x이후 변경됨에 따라 기존에 지원되던 properties들이 지원되지 않아 재구현 함.
 * </pre>
 * 
 * @author sudden(sch0718@naver.com)
 */
@JsonComponent
public class PageImplJacksonSerializer extends JsonSerializer<Page<?>> {

	@Override
	public void serialize(Page<?> page, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeObjectField("content", page.getContent());
		gen.writeObjectField("pageable", page.getPageable());
		gen.writeObjectField("previousPageable", page.previousPageable());
		gen.writeObjectField("nextPageable", page.nextPageable());
		gen.writeBooleanField("isFirst", page.isFirst());
		gen.writeBooleanField("isLast", page.isLast());
		gen.writeBooleanField("isEmpty", page.isEmpty());
		gen.writeBooleanField("hasPrevious", page.hasPrevious());
		gen.writeBooleanField("hasNext", page.hasNext());
		gen.writeBooleanField("hasContent", page.hasContent());
		gen.writeNumberField("totalPages", page.getTotalPages());
		gen.writeNumberField("totalElements", page.getTotalElements());
		gen.writeNumberField("numberOfElements", page.getNumberOfElements());
		gen.writeNumberField("size", page.getSize());
		gen.writeNumberField("number", page.getNumber());
		Sort sort = page.getSort();
		gen.writeArrayFieldStart("sort");
		for (Sort.Order order : sort) {
			gen.writeStartObject();
			gen.writeStringField("property", order.getProperty());
			gen.writeStringField("direction", order.getDirection().name());
			gen.writeBooleanField("isIgnoreCase", order.isIgnoreCase());
			gen.writeBooleanField("isAscending", order.isAscending());
			gen.writeBooleanField("isDescending", order.isDescending());
			gen.writeStringField("nullHandling", order.getNullHandling().name());
			gen.writeEndObject();
		}
		gen.writeEndArray();
		gen.writeEndObject();
	}

}
