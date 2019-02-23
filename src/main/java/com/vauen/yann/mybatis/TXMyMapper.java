package com.vauen.yann.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
* @ClassName: TXMyMapper  
* @Description: TODO(通用mapper---继承自己的MyMapper)  
* @author 作者：Mike  
* @date 2019年2月22日   
* @param <T>
 */
public interface TXMyMapper <T> extends Mapper<T>, MySqlMapper<T>{

	//TODO	特别注意，该接口不能被扫描到，否则会出错
}
