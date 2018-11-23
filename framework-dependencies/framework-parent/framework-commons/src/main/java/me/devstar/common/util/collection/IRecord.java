package me.devstar.common.util.collection;

import java.io.Serializable;
import java.net.URL;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;
import java.util.Map;

/**
 * 맵 타입의 데이터 객체
 * @author sudden(sch0718@naver.com)
 */
public interface IRecord extends Map<String, Object>, Jsonable, Serializable {

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 Blob 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return Blob 객체
	 * @throws ClassCastException
	 */
	public Blob getBlob(int columnIndex) throws ClassCastException;

	/**
	 * columnName 에 해당하는 컬럼의 값을 Blob 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return Blob 객체
	 * @throws ClassCastException
	 */
	public Blob getBlob(String columnName) throws ClassCastException;

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 boolean 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return boolean 값
	 * @throws ClassCastException
	 */
	public boolean getBoolean(int columnIndex) throws ClassCastException;

	/**
	 * columnName 에 해당하는 컬럼의 값을 boolean 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return boolean 값
	 * @throws ClassCastException
	 */
	public boolean getBoolean(String columnName) throws ClassCastException;

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 byte 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return byte 값
	 * @throws ClassCastException
	 */
	public byte getByte(int columnIndex) throws ClassCastException;

	/**
	 * columnName 에 해당하는 컬럼의 값을 byte 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return byte 값
	 * @throws ClassCastException
	 */
	public byte getByte(String columnName) throws ClassCastException;

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 Clob 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return Clob 객체
	 * @throws ClassCastException
	 */
	public Clob getClob(int columnIndex) throws ClassCastException;

	/**
	 * columnName 에 해당하는 컬럼의 값을 Clob 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return Clob 객체
	 * @throws ClassCastException
	 */
	public Clob getClob(String columnName) throws ClassCastException;

	/**
	 * columnIndex 에 해당하는 컬럼의 Class 를 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return 클래스
	 */
	public Class<?> getColumnClass(int columnIndex);

	/**
	 * 컬럼들의 Class 를 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return 클래스
	 */
	public Class<?> getColumnClass(String columnName);

	/**
	 * 컬럼의 갯수를 리턴한다.
	 * @return 컬럼의 갯수
	 */
	public int getColumnCount();

	/**
	 * 컬럼들의 이름을 배열로 리턴한다.
	 * @return 컬럼이름들의 배열
	 */
	public String[] getColumnNames();

	/**
	 * 컬럼들을 IColumns 로 리턴한다.
	 * @return IColumns 객체
	 */
	public IColumns getColumns();

	/**
	 * columnIndex 에 해당하는 컬럼의 타입을 IColumnType 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return IColumnType 객체
	 */
	public IColumnType getColumnType(int columnIndex);

	/**
	 * columnName 에 해당하는 컬럼의 타입을 IColumnType 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return IColumnType 객체
	 */
	public IColumnType getColumnType(String columnName);

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 Date 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return Date 객체
	 * @throws ClassCastException
	 */
	public Date getDate(int columnIndex) throws ClassCastException;

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 format 형식으로 변환하여 String 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return format 형식의 날짜 문자열
	 * @throws ClassCastException
	 */
	public String getDate(int columnIndex, String format) throws ClassCastException;

	/**
	 * columnName 에 해당하는 컬럼의 값을 Date 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return Date 객체
	 * @throws ClassCastException
	 */
	public Date getDate(String columnName) throws ClassCastException;

	/**
	 * columnName 에 해당하는 컬럼의 값을 format 형식으로 변환하여 String 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return format 형식의 날짜 문자열
	 * @throws ClassCastException
	 */
	public String getDate(String columnName, String format) throws ClassCastException;

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 double 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return double 값
	 * @throws ClassCastException
	 */
	public double getDouble(int columnIndex) throws ClassCastException;

	/**
	 * columnName 에 해당하는 컬럼의 값을 double 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return double 값
	 * @throws ClassCastException
	 */
	public double getDouble(String columnName) throws ClassCastException;

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 float 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return float 값
	 * @throws ClassCastException
	 */
	public float getFloat(int columnIndex) throws ClassCastException;

	/**
	 * columnName 에 해당하는 컬럼의 값을 float 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return float 값
	 * @throws ClassCastException
	 */
	public float getFloat(String columnName) throws ClassCastException;

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 int 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return int 값
	 * @throws ClassCastException
	 */
	public int getInt(int columnIndex) throws ClassCastException;

	/**
	 * columnName 에 해당하는 컬럼의 값을 int 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return int 값
	 * @throws ClassCastException
	 */
	public int getInt(String columnName) throws ClassCastException;

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 long 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return long 값
	 * @throws ClassCastException
	 */
	public long getLong(int columnIndex) throws ClassCastException;

	/**
	 * columnName 에 해당하는 컬럼의 값을 long 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return long 값
	 * @throws ClassCastException
	 */
	public long getLong(String columnName) throws ClassCastException;

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 Object 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return Object 객체
	 */
	public Object getObject(int columnIndex);

	/**
	 * columnName 에 해당하는 컬럼의 값을 Object 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return Object 객체
	 */
	public Object getObject(String columnName);

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 IRecord 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return IRecord 객체
	 */
	public IRecord getRecord(int columnIndex);

	/**
	 * columnName 에 해당하는 컬럼의 값을 IRecord 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return IRecord 객체
	 */
	public IRecord getRecord(String columnName);

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 short 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return short 값
	 * @throws ClassCastException
	 */
	public short getShort(int columnIndex) throws ClassCastException;

	/**
	 * columnName 에 해당하는 컬럼의 값을 short 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return short 값
	 * @throws ClassCastException
	 */
	public short getShort(String columnName) throws ClassCastException;

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 IStore 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return IStore 객체
	 */
	public IStore getStore(int columnIndex);

	/**
	 * columnName 에 해당하는 컬럼의 값을 IStore 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return IStore 객체
	 */
	public IStore getStore(String columnName);

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 String 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return String 객체
	 * @throws ClassCastException
	 */
	public String getString(int columnIndex) throws ClassCastException;

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 String 로 리턴한다. Null 이거나 리턴할 수 없으면 defaultValue 로 대체하여 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return String 객체
	 * @throws ClassCastException
	 */
	public String getString(int columnIndex, String defaultValue) throws ClassCastException;

	/**
	 * columnName 에 해당하는 컬럼의 값을 String 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return String 객체
	 * @throws ClassCastException
	 */
	public String getString(String columnName) throws ClassCastException;

	/**
	 * columnName 에 해당하는 컬럼의 값을 String 로 리턴한다. Null 이거나 리턴할 수 없으면 defaultValue 로 대체하여 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return String 객체
	 * @throws ClassCastException
	 */
	public String getString(String columnName, String defaultValue) throws ClassCastException;

	/**
	 * columnIndex 에 해당하는 컬럼의 값을 URL 로 리턴한다.
	 * @param columnIndex 컬럼 인덱스
	 * @return URL 객체
	 * @throws ClassCastException
	 */
	public URL getURL(int columnIndex) throws ClassCastException;

	/**
	 * columnName 에 해당하는 컬럼의 값을 URL 로 리턴한다.
	 * @param columnName 컬럼 이름
	 * @return URL 객체
	 * @throws ClassCastException
	 */
	public URL getURL(String columnName) throws ClassCastException;
}
