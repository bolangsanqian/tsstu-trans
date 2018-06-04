package com.tsstu.common.constants;
/**
 * 常量
 * @author liwei
 * 日期：2017年4月19日23:42:04
 */
public interface Constants {
	
	// 数字
	public static final int NUM_ZORE = 0;
	public static final int NUM_ONE = 1;
	public static final int NUM_TOW = 2;
	public static final int NUM_THREE = 3;
	
	// 禁用
	public static final int STATUS_DISABLE = 0;
	// 启用
	public static final int STATUS_ENABLE = 1;
	// 销户
	public static final int STATUS_CANCEL = 2;
	
	// 容许
	public static final int ALLOW = 1;
	// 不容许
	public static final int NOT_ALLOW = 0;
	
	// 否
	public static final int FALSE= 0;
	// 是
	public static final int TRUE = 1;
	/**
	 * 数据字典
	 * @author liwei
	 *
	 */
	public interface DictConstants {
		
		// 运营中心字典
		public static final String DICT_OPERATION = "DICT_OPERATION";
		
		// 普通会员字典
		public static final String DICT_COMMON_MEMBER = "DICT_COMMON_MEMBER";
		
		// 交易所、运营中心、普通会员字典
		public static final String DICT_MEMBER = "DICT_MEMBER";
		
		// 代理会员字典
		public static final String DICT_AGENT_MEMBER = "DICT_AGENT_MEMBER";
		
		// 签约银行
		public static final String DICT_CONTRACT_BANK = "DICT_CONTRACT_BANK";
		
		// 周期产品列表
		public static final String DICT_CYCLE_PRODUCT = "DICT_CYCLE_PRODUCT";
		
		// 持仓时间列表
		public static final String DICT_HOLD_TIME = "DICT_HOLD_TIME";
		
		// 签约银行状态 ( 0:待审批 , 1:变更待审批 , 2:已通过 , 3:已驳回 )
		public static final String D_CONTRACT_BANK_STATUS = "D_CONTRACT_BANK_STATUS";
		
		// 是否显示（0：不显示，1：显示）
		public static final String D_SHOW_HIDE = "D_SHOW_HIDE";
		
		// 客户提现方式 ( 0:微信提现 , 1:网银提现 ) 
		public static final String D_WITHDRAW_METHOD = "D_WITHDRAW_METHOD";
		
		// 提现订单审批状态 ( 0:未审批 , 1:已通过 , 2:已驳回 ) 
		public static final String D_WITHDRAW_REVIEW_STATUS = "D_WITHDRAW_REVIEW_STATUS";
		
		// 提现订单状态 ( 0:处理中 , 1:提现成功 , 2:提现失败 ) 
		public static final String D_WITHDRAW_STATUS = "D_WITHDRAW_STATUS";
		
		// 充值订单状态 ( 0:未支付 , 1:支付中 , 2:支付成功 , 3:支付失败 ) 
		public static final String D_RECHARGE_STATUS = "D_RECHARGE_STATUS";
		
		// 客户充值方式 ( 0:微信公众号支付 , 1:微信扫码支付 , 2:微信H5支付 , 3:微信APP支付 ) 
		public static final String D_CUST_PAYMENT_METHOD = "D_CUST_PAYMENT_METHOD";
		
		// 客户资金流水类型 ( 0:充值(+) , 1:提现(-) , 2:盈亏(+-) , 3:加币(+) , 4:减币(-) , 5:佣金(+) , 7:客户充值手续费(+) , 8:客户提现手续费(+) , 9:充值手续费(-) , 10:提现手续费(-) )
		public static final String D_MEMBER_FUND_FLOW_TYPE = "D_MEMBER_FUND_FLOW_TYPE";
		
		// 客户资金流水类型 ( 0:充值(+) , 1:提现(-) , 2:盈亏(+-) , 3:加币(+) , 4:减币(-) , 5:佣金(+) , 6:交易手续费(-) , 7:冻结保证金(-) , 8:解冻保证金(+) , 9:充值手续费(-) , 10:提现手续费(-) )
		public static final String D_CUST_FUND_FLOW_TYPE = "D_CUST_FUND_FLOW_TYPE";
		// 持仓时间单位 ( M:分钟 , H:小时 ) 
		public static final String D_HOLD_TIME_UNIT = "D_HOLD_TIME_UNIT";
		
		// 交易订单状态 ( 0:持仓中 , 1:已平仓 ) 
		public static final String D_ORDER_STATUS = "D_ORDER_STATUS";
		
		// 交易方向 ( 1:买涨 , -1:买跌 ) 
		public static final String D_TRADING_DIRECTION = "D_TRADING_DIRECTION";
		
		// 短信发送类型 ( 0:客户注册 , 1:客户找回密码 , 2:客户提现 ) 
		public static final String D_SMS_SEND_TYPE = "D_SMS_SEND_TYPE";
		
		// 银行用途 ( 0:客户签约 ) 
		public static final String D_BANK_PURPOSE = "D_BANK_PURPOSE";
		
		// 会员类型 ( 0:交易所 , 1:运营中心 , 2:会员 ) 
		public static final String D_MEMBER_TYPE = "D_MEMBER_TYPE";
		
		// 会员证件类型 ( 1:营业执照 , 2:身份证 , 0:其他 ) 
		public static final String MEMBER_IDENTITY_TYPE = "MEMBER_IDENTITY_TYPE";
		
		// 客户激活状态 ( 0:未激活 , 1:已激活 ) 
		public static final String D_CUSTOMER_ACTIVE_STATUS = "D_CUSTOMER_ACTIVE_STATUS";
		
		// 收盘日期 ( 0:当天 , 1:第二天 ) 
		public static final String CLOSING_DAY = "CLOSING_DAY";
		
		// 星期 ( 1:星期一 , 2:星期二 , 3:星期三 , 4:星期四 , 5:星期五 , 6:星期六 , 7:星期日 ) 
		public static final String D_WEEK = "D_WEEK";
		
		// 外汇产品类型 ( 0:动态外汇 , 1:固定外汇 ) 
		public static final String D_FOREIGN_RATE_TYPE = "D_FOREIGN_RATE_TYPE";
		
		// 外汇商品标识 ( 0:非外汇产品 , 1:外汇商品 ) 
		public static final String D_FOREIGN_PRODUCT_TAG = "D_FOREIGN_PRODUCT_TAG";
		
		// 是否容许状态 ( 0:不容许 , 1:容许 ) 
		public static final String D_ALLOW_STATUS = "D_ALLOW_STATUS";
		
		// HTTP请求类型 ( 0:GET请求 , 1:POST请求 ) 
		public static final String HTTP_REQUEST_METHOD = "HTTP_REQUEST_METHOD";
		
		// 系统日志类型 ( 0:其他 , 1:新增 , 2:删除 , 3:修改 , 4:查询 , 5:登录 , 6:注册 , 7:忘记密码 ) 
		public static final String D_LOG_TYPE = "D_LOG_TYPE";
		
		// 日志记录模式 ( 0:自动记录 , 1:手动记录 ) 
		public static final String D_LOG_MODE = "D_LOG_MODE";
		
		// 配置生效方式 ( 0:重启生效 , 0:实时生效 ) 
		public static final String D_EFFECTIVE_WAY = "D_EFFECTIVE_WAY";
		
		// 系统配置分组 ( 1:系统设置 , 2:充值设置 , 3:提现设置 , 4:短信通道 , 5:短信模板 ) 
		public static final String D_SYS_CONFIG_GROUP = "D_SYS_CONFIG_GROUP";
		
		// 后台用户类型 ( 0:管理员 , 1:运营中心 , 2:微会员 , 3:代理会员 , 4:经纪人 ) 
		public static final String D_CONSOLE_USER_TYPE = "D_CONSOLE_USER_TYPE";
		
		// 权限菜单类型 ( 0:菜单 , 1:页面 , 2:按钮 ) 
		public static final String D_MENU_TYPE = "D_MENU_TYPE";
		
		// 状态启用禁用 ( 1:启用 , 0:禁用 ) 
		public static final String D_ENABLE_DISABLE = "D_ENABLE_DISABLE";
		
		// 字段数据类型 ( String:字符串 , Integer:整型 , Float:浮点型 , Date:日期 , BigDecimal:金额类型 ) 
		public static final String D_FIELD_TYPE = "D_FIELD_TYPE";
		
		// 表单元素类型 ( empty:无 , text:文本框 , password:密码框 , select:选择框 , textarea:文本域 , date:日期选择框 ) 
		public static final String D_ELEMENT_TYPE = "D_ELEMENT_TYPE";
		
		// 通用布尔状态 ( 0:否 , 1:是 ) 
		public static final String D_TRUE_FALSE = "D_TRUE_FALSE";

		// 性别 ( 0:保密 , 1:女 , 2:男 )
		public static final String D_SEX = "D_SEX";
		
		// 客户状态(0：已禁用，1：已启用，2：已销户)
		public static final String D_CUSTOMER_STATUS = "D_CUSTOMER_STATUS";

		// 会员充值订单状态 ( 0:未支付 , 1:支付中 , 2:支付成功 , 3:支付失败 )
		public static final String D_MEMBER_RECHARGE_STATUS = "D_MEMBER_RECHARGE_STATUS";

		// 会员充值方式 ( 1:国付宝支付 , 2:开联支付 , 3:爱农支付 )
		public static final String D_MEMBER_PAYMENT_METHOD = "D_MEMBER_PAYMENT_METHOD";

		// 加减币状态 ( 0:待审批 , 1:已通过 , 2:已驳回 )
		public static final String D_FUND_CHANGE_STATUS = "D_FUND_CHANGE_STATUS";

		// 加减币变动类型 ( 0:线下充值 , 1:线下提现 , 2:赔付 , 3:赠送 , 4:罚金 , 5:其他 )
		public static final String D_FUND_CHANGE_TYPE = "D_FUND_CHANGE_TYPE";

		// 会员加减币变动类型 ( 0:线下充值 , 1:线下提现 , 2:入保证金 , 3:赔付 , 4:赠送 , 5:罚金 , 6:其他 )
		public static final String D_MEMBER_FUND_CHANGE_TYPE = "D_MEMBER_FUND_CHANGE_TYPE";

		// 会员提现付款方式 ( 0:国付宝代付 , 1:开联代付 , 2:爱农代付 )
		public static final String D_MEMBER_WITHDRAW_PAY_METHOD = "D_MEMBER_WITHDRAW_PAY_METHOD";

		// 交易模式 ( 0:周期模式 , 1:点位模式 , 2:迷你模式 )
		public static final String D_TRADE_MODE = "D_TRADE_MODE";

		// 图片分组
		public static final String D_IMAGE_GROUP = "D_IMAGE_GROUP";

		// D_ARTICEL_GROUP
		public static final String D_ARTICEL_GROUP = "D_ARTICEL_GROUP";

		// 微信消息匹配类型 ( equals:完全匹配 , startWith:开头匹配 , endWith:结尾匹配 , indexOf:包含匹配 )
		public static final String D_WXMSG_MATCH_TYPE = "D_WXMSG_MATCH_TYPE";

		// 微信消息类型 ( 0:文本消息 , 1:图文消息 , 2:图片消息 )
		public static final String D_WXMSG_TYPE = "D_WXMSG_TYPE";

		// 微信菜单类型 ( 0:单击事件 , 1:URL跳转 )
		public static final String D_WXMENU_TYPE = "D_WXMENU_TYPE";

		// 优惠券类型 ( 0:普通优惠劵 , 1:必胜优惠券 )
		public static final String D_COUPON_TYPE = "D_COUPON_TYPE";

		// 审批状态 ( 0:待审批 , 1:已通过 , 2:已驳回 )
		public static final String D_REVIEW_STATUS = "D_REVIEW_STATUS";
	}
	
	/**
	 * 用户常量
	 * @author liwei
	 */
	public interface UserConstants {
		//登录用户信息
		public static final String LOGIN_USER_INFO = "userInfo";
		//MD5加密前缀
		public static final String MD5_PWD_PREFIX = "TSSTU";
		//登录用户权限列表
		public static final String LOGIN_USER_PERMISSION = "LOGIN_USER_PERMISSION";
		//登录用户权限标识列表
		public static final String LOGIN_USER_PERMISSION_SIGN = "LOGIN_USER_PERMISSION_SIGN";
		//登录用户左侧菜单
		public static final String LEFT_MENU_LIST = "menuList";
	}
	
	/**
	 * 用户类型
	 * @author liwei
	 */
	public interface UserTypeConstants {
		// 经纪人
		public static final Integer BROKER = 0;
		// 代理会员
		public static final Integer AGENT_MEMBER = 1;
		// 普通会员
		public static final Integer COMMON_MEMBER = 2;
		// 运营中心
		public static final Integer OPERATION = 3;
		// 交易所
		public static final Integer EXCHANGE = 4;
		// 管理员
		public static final Integer MANAGER = 5;
		// 超级管理员
		public static final Integer ADMIN = 6;
	}

	/**
	 * 角色
	 * @author liwei
	 */
	public interface RoleConstants {
		// 超级管理员
		public static final Integer ADMIN = 0;
		// 管理员
		public static final Integer MANAGER = 1;
		// 交易所
		public static final Integer EXCHANGE = 2;
		// 运营中心
		public static final Integer OPERATION = 3;
		// 普通会员
		public static final Integer COMMON_MEMBER = 4;
		// 代理会员
		public static final Integer AGENT_MEMBER = 5;
		// 经纪人
		public static final Integer BROKER = 6;
	}
	
	/**
	 * 会员常量
	 * @author liwei
	 */
	public interface MemberConstants {
		//登录会员信息
		public static final String LOGIN_MEMBER_IFNO = "memberInfo";
		//登录代理会员信息
		public static final String LOGIN_AGENT_MEMBER_IFNO = "agentMmemberInfo";
		//登录经纪人信息
		public static final String LOGIN_BROKER_IFNO = "brokerInfo";
	}
	
	/**
	 * 经纪人常量
	 * @author liwei
	 */
	public interface BrokerConstants {
		//登录经纪人信息
		public static final String LOGIN_BROKER_IFNO = "brokerInfo";
	}
	
	
	/**
	 * 文件常量
	 * @author liwei
	 */
	public interface FileConstants {
		// 文件上传目录
		public static final String UPLOAD_DIR = "upload";
		// 公众号二维码目录
		public static final String WX_QRCODE_DIR = "upload/wx_qrcode/";
		// 轮播图片目录
		public static final String BANNER_DIR = "upload/banner/";
		// 上传图片目录
		public static final String COMMON_DIR = "upload/image/";
	}
	
	/**
	 * 导出excel默认日期格式
	 * @author liwei
	 *
	 */
	public interface ExportConstants {
		public static final String DEFAUT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
	}
	
	public interface CacheConstants {
		// 权限列表
		public static final String PERMISSION_LIST = "PERMISSION_LIST";
		// 权限标识列表
		public static final String PERMISSION_TAG_LIST = "PERMISSION_TAG_LIST";
		// 系统配置
		public static final String CONIG_LIST = "CONIG_LIST";
		// 持仓时间列表
		public static final String HOLD_TIME_LIST = "HOLD_TIME_LIST";
	}
	
	/**
	 * 会员类型
	 * @author liwei
	 */
	public interface MemberTypeConstants {
		// 交易所
		public static final int  EXCHANGE = 0;
		// 运营中心
		public static final int  OPERATION = 1;
		// 会员
		public static final int  COMMON_MEMBER = 2;
	}
	
	/**
	 * 详情页面数据类型
	 * @author liwei
	 */
	public interface DetailDataTypeConstants {
		// 字符串
		public static final int  STRING = 0;
		// 金额
		public static final int  MONEY = 1;
		// 日期
		public static final int  DATE = 2;
		// 图片
		public static final int  IMAGE = 3;
	}
	
	/**
	 * 签约银行状态
	 * @author liwei
	 */
	public interface D_CONTRACT_BANK_STATUS {
		// 待审批
		public static final int  DSP = 0;
		// 变更待审批
		public static final int  BGDSP = 1;
		// 已通过
		public static final int  YTG = 2;
		// 已驳回
		public static final int  YBH = 3;
		// 已解约
		public static final int  YJY = 4;
	}
	
	/**
	 * 审批状态
	 * @author liwei
	 */
	public interface REVIEW_STATUS {
		// 待审批
		public static final int  REVIEW_WAIT = 0;
		// 已通过
		public static final int  REVIEW_PASS = 1;
		// 已驳回
		public static final int  REVIEW_REJECT = 2;
	}
	
	/**
	 * 提现状态
	 * @author liwei
	 */
	public interface WITHDRAW_STATUS {
		// 处理中
		public static final int PROCESS = 0;
		// 提现成功
		public static final int SUCCESS = 1;
		// 提现失败
		public static final int FAILURE = 2;
	}
	
	/**
	 * 
	 * 交易模式
	 * @author liwei
	 */
	public interface TRADE_MODE {
		// 周期模式
		public static final int CYCEL = 0;
		// 点位模式
		public static final int POINT = 1;
		// 迷你模式
		public static final int MINI = 2;
	}

	/**
	 * 
	 * 订单状态
	 * @author liwei
	 */
	public interface ORDER_STATUS {
		// 持仓中
		public static final int HOLD = 0;
		// 已平仓
		public static final int CLOSE = 1;
	}
	
	/**
	 * 
	 * 缓存名称
	 * @author liwei
	 */
	public interface CACHE_NAME {
		// 系统缓存
		public static final String SYS = "system";
		// 行情缓存
		public static final String HQ = "quotation";
	}
	
	/**
	 * 
	 * 缓存名称
	 * @author liwei
	 */
	public interface HTTP_METHOD {
		// get请求
		public static final String GET = "GET";
		// post请求
		public static final String POST = "POST";
	}
	
	/**
	 * 
	 * 任务代码名称
	 * @author liwei
	 */
	public interface TASK {
		// www.11shuju.com，行情数据获取任务
		public static final String TASK_HQ_SHUJU_11 = "TASK_HQ_SHUJU_11";
		// zz，行情获取任务
		public static final String TASK_HQ_ZZ = "TASK_HQ_ZZ";
		// huobi.com行情获取任务
		public static final Object TASK_HQ_HUOBI = "TASK_HQ_HUOBI";
		// 清除行情任务
		public static final Object TASK_HQ_DELETE = "TASK_HQ_DELETE";
	}
	
	
	
}
