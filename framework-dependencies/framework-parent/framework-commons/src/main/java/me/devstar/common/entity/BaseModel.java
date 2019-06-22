package me.devstar.common.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * <pre>
 * 기본 Model
 * ID 체계와 등록/수정 정보를 자동으로 업데이트 할 수 있도록 구현.
 * 상속받는 모델들이 각자의 정의된 Table(Document)에 저장되도록 하려면 반드시 @MappedSuperclass 를 선언해야만 상속받은 모델이 사용된다.
 * </pre>
 */
@MappedSuperclass
public class BaseModel extends AbstractBaseModel<Long> {
	
	private static final long serialVersionUID = -4741626933110625809L;
	
	@Id
	@GeneratedValue
	@Column(name = "UID")
	private Long id;
	
	@Override
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
