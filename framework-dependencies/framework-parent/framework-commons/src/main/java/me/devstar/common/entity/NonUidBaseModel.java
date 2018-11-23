package me.devstar.common.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * <pre>
 * UID를 가지지 않는 기본 Model
 * 등록/수정 정보를 자동으로 업데이트 할 수 있도록 구현.
 * 상속받는 모델들이 각자의 정의된 Table(Document)에 저장되도록 하려면 반드시 @MappedSuperclass 를 선언해야만 상속받은 모델이 사용된다.
 * </pre>
 */
@MappedSuperclass
public abstract class NonUidBaseModel<ID extends Serializable> extends AbstractBaseModel<ID> {

	private static final long serialVersionUID = -1441530154496849036L;

}
