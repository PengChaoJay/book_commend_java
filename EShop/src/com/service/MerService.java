package com.service;

import java.util.*;
import com.ORM.*;

/** 商品分类、商品及特价商品业务逻辑处理接口 */
public interface MerService {	
	/** 浏览商品分类 */
	public List browseCategory() throws Exception;	
	/** 装载指定的商品分类 */	
	public Category loadCategory(Integer id) throws Exception;	
	/** 删除指定的商品分类 */	
	public boolean delCategory(Integer id) throws Exception;	
	/** 新增商品分类 */	
	public boolean addCategory(Category cate) throws Exception;	
	/** 更新商品分类 */	
	public boolean updateCategory(Category cate) throws Exception;
	
	/** 浏览商品 */
	public List browseMer(String hql) throws Exception;	
	/** 装载指定的商品 */	
	public Merchandise loadMer(Integer id) throws Exception;	
	/** 删除指定的商品 */	
	public boolean delMer(Integer id) throws Exception;	
	/** 新增商品 */	
	public boolean addMer(Merchandise mer) throws Exception;	
	/** 更新商品 */	
	public boolean updateMer(Merchandise mer) throws Exception;
	
	/** 分页浏览商品 */
	public List browseMer(int pageSize,int pageNo,int cateId,boolean isSpecial) throws Exception;
	/** 检索商品 */
	public List browseMer(int pageSize,int pageNo,String hql) throws Exception;	
	/** 统计记录条数 */
	public int countRecord(String hql) throws Exception;	
}