package com.zcs.app.advance.base;

/**
 * @author ZengCS
 * @since 2015年1月23日10:37:42
 */
public interface IBaseConstant {
	public static final long INIT_DELAY = 350;// 初始化数据延时
	public static final long INIT_DELAY_FAST = 50;// 初始化数据延时
	public static final long TIP_DELAY = 1500;// tip展示时间
	public static final long SAVE_DB_DELAY = 400;// 延时保存到数据库
	// Handler
	public static final int WHAT_NOTIFY = 0x001;// 告知更新数据
	public static final int WHAT_REFRESH = 0x002;// 下拉刷新
	public static final int WHAT_LOAD_MORE = 0x003;// 加载更多
	public static final int WHAT_INIT = 0x004;// 初始化数据
	public static final int WHAT_HIDE_LOADING = 0x005;// 隐藏加载提示
	public static final int WHAT_LOAD_FAILED = 0x006;// 数据加载失败
	public static final int WHAT_SAVE_TO_DB = 0x007;// 保存到数据库
	public static final int WHAT_HIDE_TOP_TIP_DELAY = 0x008;// 延时隐藏顶部提示栏
	public static final int WHAT_UPLOAD_AVATAR_SUCCESS = 0x009;// 头像上传成功
	public static final int WHAT_UPLOAD_AVATAR_FAILED = 0x00A;// 头像上传失败
	public static final int WHAT_SET_PROGRESS_DELAY = 0x00B;// 延时显示进度条
	public static final int WHAT_SUCCESS = 0x00C;// 成功-通用
	public static final int WHAT_FAILED = 0x00D;// 失败-通用

	public static final long TOP_TIP_DURATION = 2200;// 顶部提示信息延时
}
