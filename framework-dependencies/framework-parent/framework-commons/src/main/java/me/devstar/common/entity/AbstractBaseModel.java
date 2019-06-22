package me.devstar.common.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import me.devstar.common.util.ToStringUtils;

/**
 * <pre>
 * 기본 Model
 * ID에 대한 정책은 하위 객체에 위임한다.
 * 상속받는 모델들이 각자의 정의된 Table(Document)에 저장되도록 하려면 반드시 @MappedSuperclass 를 선언해야만 상속받은 모델이 사용된다.
 * </pre>
 */
@MappedSuperclass
@JsonIgnoreProperties({ "new" })
public abstract class AbstractBaseModel<ID extends Serializable> extends AuditModel
		implements Serializable, Persistable<ID> {

	private static final long serialVersionUID = 7202336249035732217L;

	@Override
	public abstract ID getId();

	@Override
	public boolean isNew() {
		return null == getId();
	}

	/**
	 * @param id entity ID
	 */
	public abstract void setId(ID id);

	@Override
	public String toString() {
		return ToStringUtils.toString(this);
	}
}
