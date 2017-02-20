package com.fantasi.common.db.sql;

import java.util.ArrayList;
import java.util.List;

import com.fantasi.common.db.process.Filter;
import com.fantasi.common.db.process.SelectParam;

import junit.framework.TestCase;

public class FilterTest extends TestCase{
	public void testGetFilterParams() {
		Filter filter = new Filter();
		
		filter.setColumnName("a_b");
		filter.setOp(Filter.Op_Equal);
		filter.setValue("1");
		
		SelectParam sp = Filter.getFilterParams(filter);
		System.out.println(sp.getWhereClause());
	}
	
	
	public void testComplexFilterParams() {
		Filter filter = new Filter();
		filter.setColumnComplex(true);
		filter.setValueType(Filter.ValueType_Complex);
		filter.setColumnName("contact(title,content)");
		filter.setOp(Filter.Op_Like);
		filter.setValue("民告官|豆腐渣工程|豪华会议|强权|劳民伤财|拉票|丑闻|爆料|报料|揭发|公款|双规|堕楼|腐败|卖官|买官|生活作风|违规|滥用|监管漏洞|贪官|包二奶|养情妇腐化|公费|欺压|贪污|以权谋私|公车私用|谎报|黑幕|瞒报|失职|行贿|政府失职|豪华办公受贿|谋私|内幕交易|不作为|贿赂|挪用|严重违纪|越权|举报|弄虚作假|反腐|父母官|萝卜招聘|干部任命|吃空饷");
		
		SelectParam sp = Filter.getFilterParams(filter);
		System.out.println(sp.getWhereClause());
	}
	
	public void testGetFilterParams1() {
		Filter filter = new Filter();
		
		filter.setRoot(false);
		
		List<Filter> filters = new ArrayList<Filter>();
		Filter subFilter = new Filter();
		subFilter.setColumnName("a_b");
		subFilter.setOp(Filter.Op_Equal);
		subFilter.setValue("1");
		filters.add(subFilter);
		
		subFilter = new Filter();
		subFilter.setColumnName("a_b");
		subFilter.setOp(Filter.Op_Bigger);
		subFilter.setValue("1");
		subFilter.setRelation(Filter.Relation_And);
		filters.add(subFilter);
		
		subFilter = new Filter();
		subFilter.setColumnName("a_b");
		subFilter.setOp(Filter.Op_Between);
		subFilter.setValue("|2014-01-01");
		subFilter.setFieldType(Filter.FieldType_Date);
		subFilter.setRelation(Filter.Relation_And);
		filters.add(subFilter);
		
		subFilter = new Filter();
		subFilter.setColumnName("a_b");
		subFilter.setOp(Filter.Op_Between);
		subFilter.setValue("2014-01-01|");
		subFilter.setFieldType(Filter.FieldType_Date);
		subFilter.setRelation(Filter.Relation_And);
		filters.add(subFilter);
		
		subFilter = new Filter();
		subFilter.setColumnName("a_b");
		subFilter.setOp(Filter.Op_Between);
		subFilter.setValue("2014-01-01|2014-01-01");
		subFilter.setFieldType(Filter.FieldType_Date);
		subFilter.setRelation(Filter.Relation_And);
		filters.add(subFilter);
		
		filter.setSubFilters(filters);
		
		SelectParam sp = Filter.getFilterParams(filter);
		System.out.println(sp.getWhereClause());
	}
}
