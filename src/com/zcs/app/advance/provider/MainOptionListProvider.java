package com.zcs.app.advance.provider;

import java.util.ArrayList;
import java.util.List;

import com.zcs.app.advance.acts.EmptyActivity;
import com.zcs.app.advance.demo.avatar.ClipAvatarActivity;
import com.zcs.app.advance.demo.drawer.DrawerActivity;
import com.zcs.app.advance.demo.flow.FlowLayoutActivity;
import com.zcs.app.advance.demo.index.IndexHomeActivity;
import com.zcs.app.advance.demo.infinite.InfiniteActivity;
import com.zcs.app.advance.demo.puzzle.PuzzleActivity;
import com.zcs.app.advance.demo.radar.RadarActivity;
import com.zcs.app.advance.demo.scroll.ScrollMenuActivity;
import com.zcs.app.advance.entity.MainOptionItem;

public class MainOptionListProvider {
	private static MainOptionItem item;

	/**
	 * 获取主菜单选项
	 * 
	 * @return
	 */
	public static List<MainOptionItem> getMainOptions() {
		List<MainOptionItem> list = new ArrayList<MainOptionItem>();
		// Demo_001:拼图游戏
		item = new MainOptionItem("demo.puzzle");
		item.setName("智力拼图");
		item.setDesc("非常经典的宫格拼图游戏");
		item.setTargetActivity(PuzzleActivity.class);
		list.add(item);

		// Demo_002:头像裁剪
		item = new MainOptionItem("demo.avatar");
		item.setName("头像裁剪");
		item.setDesc("支持双击放大，拖动，缩放的头像裁剪功能");
		item.setTargetActivity(ClipAvatarActivity.class);
		list.add(item);

		// Demo_003:主界面
		item = new MainOptionItem("demo.index");
		item.setName("Fragment主界面");
		item.setDesc("利用Fragment制作的主界面样式");
		item.setTargetActivity(IndexHomeActivity.class);
		list.add(item);

		// Demo_004:上下滑动菜单
		item = new MainOptionItem("demo.scroll");
		item.setName("纵向滑动菜单");
		item.setDesc("纵向滑动菜单");
		item.setTargetActivity(ScrollMenuActivity.class);
		list.add(item);

		// Demo_004:无限滚动焦点图
		item = new MainOptionItem("demo.infinite");
		item.setName("无限滚动");
		item.setDesc("可以左右无限滚动的焦点图");
		item.setTargetActivity(InfiniteActivity.class);
		list.add(item);

		// Demo_005:左右侧滑菜单
		item = new MainOptionItem("demo.drawer");
		item.setName("左右侧滑菜单");
		item.setDesc("使用DrawerLayout制作的左右侧滑菜单");
		item.setTargetActivity(DrawerActivity.class);
		list.add(item);

		// Demo_006:流式布局
		item = new MainOptionItem("demo.flow");
		item.setName("流式布局");
		item.setDesc("自定义流式布局");
		item.setTargetActivity(FlowLayoutActivity.class);
		list.add(item);

		// Demo_007:雷达扫描
		item = new MainOptionItem("demo.test");
		item.setName("雷达扫描");
		item.setDesc("雷达扫描动画，这个效果也常被用于扫描或定位等事件");
		item.setTargetActivity(RadarActivity.class);
		list.add(item);
		
		// Demo_000:测试
		item = new MainOptionItem("demo.test");
		item.setName("测试2");
		item.setDesc("Just a test");
		item.setTargetActivity(EmptyActivity.class);
		list.add(item);

		return list;
	}
}
