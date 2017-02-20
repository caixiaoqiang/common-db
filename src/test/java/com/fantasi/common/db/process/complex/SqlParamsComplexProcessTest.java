package com.fantasi.common.db.process.complex;

import com.fantasi.common.db.process.Filter;

import junit.framework.TestCase;

public class SqlParamsComplexProcessTest extends TestCase {
	public void test() {
		Filter filter = new Filter();
		filter.setColumnComplex(true);
        filter.setColumnName("concat(title,content)");
        filter.setOp(Filter.Op_Like);
        filter.setValue("");
        SqlParamsComplexProcess filterProcess = new SqlParamsComplexProcess(filter,0);
        ComplexValueConverter filterConvertor = new ComplexValueConverter(filterProcess);

        filterConvertor.convert("今天+明天");
        System.out.println(filterProcess.Result);

        filterProcess.reset();
        filterConvertor.convert("(今天+明天)");
        System.out.println(filterProcess.Result);

        filterProcess.reset();
        filterConvertor.convert("-(今天+明天)");
        System.out.println(filterProcess.Result);

        filterProcess.reset();
        filterConvertor.convert("今天-(明天|后台)");
        System.out.println(filterProcess.Result);
        
        filterProcess.reset();
        filterConvertor.convert("-(明天)");
        System.out.println(filterProcess.Result);
        
        filterProcess.reset();
        filterConvertor.convert("-明天");
        System.out.println(filterProcess.Result);
        
//        filterProcess.reset();
//        filterConvertor.convert("(开发区|产业园|华泰|工程机械|徐工|科勃|布兰和|海伦哲|华恒|宗申|中能硅业|协鑫|艾德|晶旺|罗特艾德|燃控|恩华|万邦|大旺|天虹|银地|华东机械|普利|凯信)-(丰县开发区|丰县经济技术开发区)");
//        System.out.println(filterProcess.Result);
        
        
        filterProcess.reset();
        filterConvertor.convert("健康+(工商|登记注册|营业执照|假冒伪劣|质量|消费者|扰乱市场|无证经营)");
        
        filterProcess.reset();
        filterConvertor.convert("健康+(工商|登记注册|营业执照|假冒伪劣|质量|消费者|扰乱市场|无证经营)");
        System.out.println(filterProcess.Result);
        
        filterProcess.reset();
        filterConvertor.convert("崇川+(学校|中学|小学|初中|高中|职校|电大|职中|大学|高校|技校|学院|师大|教材|课本|学费|教师|老师|为人师表|校长|补课费|招生陷阱|学生|教育局|学科带头)");
        System.out.println(filterProcess.Result);
        
        
        
	}
}
