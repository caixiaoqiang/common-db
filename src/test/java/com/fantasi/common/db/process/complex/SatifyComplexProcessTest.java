package com.fantasi.common.db.process.complex;

import org.junit.Assert;

import com.fantasi.common.db.process.Filter;

import junit.framework.TestCase;

public class SatifyComplexProcessTest extends TestCase {
	public void test() {
		doFilter();
	}
	
	private void doFilter() {
		String content = "今天 明天 a b c d  ";
		
		Filter filter = new Filter();
		filter.setColumnComplex(true);
		filter.setValueType(Filter.ValueType_Complex);
		filter.setColumnName("title,content");
		filter.setOp(Filter.Op_Like);
		
		filter.setValue("城南新区+(-招聘)");
		Assert.assertFalse(filter.satify(content));
		
		
		filter.setValue("今天+(明天|后台)");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("今天1+(明天1|后台)");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("今天1+(明天|后台)");
		Assert.assertFalse(filter.satify(content));
		
		filter.setValue("谏壁+(工商|登记注册|营业执照|假冒伪劣|质量|消费者|扰乱市场|无证经营)");
		Assert.assertFalse(filter.satify(content));
		
		filter.setValue("大市口+(工商|登记注册|营业执照|假冒伪劣|质量|消费者|扰乱市场|无证经营)");
		Assert.assertFalse(filter.satify(content));
		
		filter.setValue("健康+(工商|登记注册|营业执照|假冒伪劣|质量|消费者|扰乱市场|无证经营)");
		Assert.assertFalse(filter.satify(content));
		
		filter.setValue("检察院|京检");
		Assert.assertFalse(filter.satify(content));
		
		filter.setValue("今天");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("今天+明天");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("今天-明天");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("-今天");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("-(今天+明天)");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("-(今天|明天)");
		Assert.assertFalse(filter.satify(content));		
		filter.setValue("(今天|明天)");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("今天|明天");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("-后天");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("-明天");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("今天+后天");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("后天+今天");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("(后天+今天)");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("-(后天+今天)");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("-(后天+今天)-明天");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("-明天|-(后天+今天)");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("-明天|(后天+今天)");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("后天|今天");
		Assert.assertTrue(filter.satify(content));
		
		filter.setValue("-(后天|今天)");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("-(后天|今天)|明天");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("-(后天|今天)|(明天)");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("后天+大后天");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("后天");
		Assert.assertFalse(filter.satify(content));
		
		
		
		filter.setValue("(a|b)+(c|d)");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("(e|b)+(e|f)");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("(b|e)+(e|f)");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("(e|b)+(a|f)");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("(e|b)+(f|a)");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("((e|b)+(f|a))|c");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("((e|b)+(f|e))|c");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("((e|b)+(f|e))|f");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("((b|e)+(f|a))|c");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("((b|e)+(f|e))|c");
		Assert.assertTrue(filter.satify(content));
		filter.setValue("((b|e)+(f|e))|f");
		Assert.assertFalse(filter.satify(content));
		
		filter.setValue("i|b|l");
		Assert.assertTrue(filter.satify(content));
		
		filter.setValue("((a|b)+(c|d))-(今天)");
		Assert.assertFalse(filter.satify(content));
		filter.setValue("((a|b)+(c|d))-(今天1)");
		Assert.assertTrue(filter.satify(content));
		
		content = "# 博世暖男大揭秘#我身边的暖男是热水器暖男-居家必备 @_跳_跳_珠_";
		
		System.out.println(content.contains("博世暧男"));
		
		filter.setValue("((博世|bosch)+(冰箱|洗衣机|干衣机|烘干机|热水器|酒柜))-(博世暧男)");
		Assert.assertTrue(filter.satify(content));
//		((博世|bosch)+(冰箱|洗衣机|干衣机|烘干机|热水器|酒柜))-(博世暧男)

	}
}
