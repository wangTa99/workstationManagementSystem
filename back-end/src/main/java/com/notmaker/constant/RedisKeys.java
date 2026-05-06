package com.notmaker.constant;

/**
 * Redis键常量类
 * 定义系统中Redis使用的所有键前缀和格式
 */
public class RedisKeys {

    /**
     * 用户登录令牌前缀
     * 格式：user:token:{token}
     */
    public static final String USER_TOKEN = "user:token:";

    /**
     * 用户信息缓存前缀
     * 格式：user:info:{userId}
     */
    public static final String USER_INFO = "user:info:";

    /**
     * 用户权限缓存前缀
     * 格式：user:perm:{userId}
     */
    public static final String USER_PERMISSION = "user:perm:";

    /**
     * 用户角色缓存前缀
     * 格式：user:role:{userId}
     */
    public static final String USER_ROLE = "user:role:";

    /**
     * 验证码缓存前缀
     * 格式：captcha:{uuid}
     */
    public static final String CAPTCHA = "captcha:";

    /**
     * 短信验证码缓存前缀
     * 格式：sms:code:{phone}
     */
    public static final String SMS_CODE = "sms:code:";

    /**
     * 邮箱验证码缓存前缀
     * 格式：email:code:{email}
     */
    public static final String EMAIL_CODE = "email:code:";

    /**
     * 商品缓存前缀
     * 格式：product:{productId}
     */
    public static final String PRODUCT = "product:";

    /**
     * 商品列表缓存前缀
     * 格式：product:list:{categoryId}
     */
    public static final String PRODUCT_LIST = "product:list:";

    /**
     * 分类缓存前缀
     * 格式：category:{categoryId}
     */
    public static final String CATEGORY = "category:";

    /**
     * 分类列表缓存前缀
     * 格式：category:list:{parentId}
     */
    public static final String CATEGORY_LIST = "category:list:";

    /**
     * 订单缓存前缀
     * 格式：order:{orderId}
     */
    public static final String ORDER = "order:";

    /**
     * 购物车缓存前缀
     * 格式：cart:{userId}
     */
    public static final String CART = "cart:";

    /**
     * 用户积分缓存前缀
     * 格式：points:{userId}
     */
    public static final String POINTS = "points:";

    /**
     * 签到记录缓存前缀
     * 格式：sign:{userId}:{date}
     */
    public static final String SIGN = "sign:";

    /**
     * 连续签到天数缓存前缀
     * 格式：sign:continuous:{userId}
     */
    public static final String SIGN_CONTINUOUS = "sign:continuous:";

    /**
     * 优惠券缓存前缀
     * 格式：coupon:{couponId}
     */
    public static final String COUPON = "coupon:";

    /**
     * 用户优惠券缓存前缀
     * 格式：user:coupon:{userId}
     */
    public static final String USER_COUPON = "user:coupon:";

    /**
     * 系统配置缓存前缀
     * 格式：config:{configKey}
     */
    public static final String CONFIG = "config:";

    /**
     * 字典缓存前缀
     * 格式：dictionary:{dictCode}
     */
    public static final String DICTIONARY = "dictionary:";

    /**
     * 轮播图缓存前缀
     * 格式：banner:{position}
     */
    public static final String BANNER = "banner:";

    /**
     * 秒杀活动缓存前缀
     * 格式：seckill:{seckillId}
     */
    public static final String SECKILL = "seckill:";

    /**
     * 秒杀库存缓存前缀
     * 格式：seckill:stock:{seckillId}
     */
    public static final String SECKILL_STOCK = "seckill:stock:";

    /**
     * 地区信息缓存前缀
     * 格式：area:{parentId}
     */
    public static final String AREA = "area:";

    /**
     * 短信发送频率限制前缀
     * 格式：sms:limit:{phone}
     */
    public static final String SMS_LIMIT = "sms:limit:";

    /**
     * IP访问频率限制前缀
     * 格式：ip:limit:{ip}
     */
    public static final String IP_LIMIT = "ip:limit:";

    /**
     * 分布式锁前缀
     * 格式：lock:{businessKey}
     */
    public static final String LOCK = "lock:";

    /**
     * 队列前缀
     */
    public static final String QUEUE = "queue:";

    /**
     * 消息队列前缀
     */
    public static final String MQ = "mq:";

    /**
     * 用户会话前缀
     * 格式：session:{userId}
     */
    public static final String SESSION = "session:";

    /**
     * 错误次数限制前缀
     * 格式：error:limit:{key}
     */
    public static final String ERROR_LIMIT = "error:limit:";

    /**
     * 生成Redis键的通用方法
     * @param prefix 键前缀
     * @param key 键值
     * @return 完整的Redis键
     */
    public static String buildKey(String prefix, String key) {
        return prefix + key;
    }
}

/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
