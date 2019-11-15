package com.yf.coros.common.utils;

import com.yf.coros.common.entity.data.BikeTeamUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类.
 *
 * @author administrator
 * @version V100R100C01
 */
public class YfTools {

    private static Exception ex;

    /**
     * 私有构造方法，不允许实例化
     */
    private YfTools() {
    } // End Tools

    /**
     * 判断一个对象是否为空 字符串则去除前后的空格进行比较 数组则按长度为0进行比较
     *
     * @param object 数据对象
     * @return true:数据对象为空;false:数据对象非空.
     */
    public static boolean isEmpty(Object object) {
        return YfDataType.isEmpty(object);
    } // End isEmpty

    /**
     * 是否不为空
     *
     * @param object
     * @return
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * 输出信息到控制台
     *
     * @param object 输出的数据对象
     */
    public static void log(Object object) {
        System.out.println(object);
    } // End log

    public static String toHexString(byte[] bytes) {
        return toHexString(bytes, 0, bytes.length);
    }

    public static String toHexString(byte[] bytes, int start, int end) {
        StringBuffer sb = new StringBuffer();
        for (; start < end; ++start) {
            sb.append(String.format("%02x", bytes[start]));
        }
        return sb.toString();
    }


    public static String trimEnd(String stream, String trimstr) {
        // null或者空字符串的时候不处理
        if (stream == null || stream.length() == 0 || trimstr == null || trimstr.length() == 0) {
            return stream;
        }

        // 结束位置
        int epos = 0;

        // 正规表达式
        String regpattern = "[" + trimstr + "]*+";
        Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

        // 去掉结尾的指定字符
        StringBuffer buffer = new StringBuffer(stream).reverse();
        Matcher matcher = pattern.matcher(buffer);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            stream = new StringBuffer(buffer.substring(epos)).reverse().toString();
        }
        // 返回处理后的字符串
        return stream;
    }

    /**
     * 去掉指定字符串的开头和结尾的指定字符
     *
     * @return 处理后的字符串
     */
    public static String trim(String stream, String trimstr) {
        // null或者空字符串的时候不处理
        if (stream == null || stream.length() == 0 || trimstr == null || trimstr.length() == 0) {
            return stream;
        }

        // 结束位置
        int epos = 0;

        // 正规表达式
        String regpattern = "[" + trimstr + "]*+";
        Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

        // 去掉结尾的指定字符
        StringBuffer buffer = new StringBuffer(stream).reverse();
        Matcher matcher = pattern.matcher(buffer);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            stream = new StringBuffer(buffer.substring(epos)).reverse().toString();
        }

        // 去掉开头的指定字符
        matcher = pattern.matcher(stream);
        if (matcher.lookingAt()) {
            epos = matcher.end();
            stream = stream.substring(epos);
        }

        // 返回处理后的字符串
        return stream;
    }

    public static <T> T copy(T c) {
        if (YfTools.isEmpty(c)) {
            return null;
        }
        T t = null;
        try {
            t = (T) c.getClass().newInstance();
            BeanUtils.copyProperties(c, t);
        } catch (Exception e) {
            return null;
        }
        return t;
    }


    /**
     * 根据key获取信息
     *
     * @param messageKey key值
     * @return 信息
     */
    public static String getMessageByKey(String messageKey) {
        Locale locale = new Locale("en", "US");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", locale);
        return resourceBundle.getString(messageKey);

    }

    /**
     * 根据key获取信息
     *
     * @param messageKey         key值
     * @param languageAndCountry 语言和国家 zh-CN,en-US
     * @return 信息
     */
    public static String getMessageByKey(String messageKey, String languageAndCountry) {
        String language = "en";
        String country = "US";
        if (StringUtils.isNotBlank(languageAndCountry)) {
            String[] languageAndCountryArr = StringUtils.split(languageAndCountry, "-");
            if (ArrayUtils.isNotEmpty(languageAndCountryArr) || languageAndCountryArr.length >= 2) {
                language = StringUtils.lowerCase(languageAndCountryArr[0]);
                country = StringUtils.upperCase(languageAndCountryArr[languageAndCountryArr.length - 1]);
            }
        }
        Locale locale = new Locale(language, country);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", locale);
        return resourceBundle.getString(messageKey);

    }

    /**
     * 根据key获取信息
     *
     * @param messageKey key值
     * @param locale     语言本地化 zh-CN,en-US
     * @return 信息
     */
    public static String getMessageByKey(String messageKey, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", locale);
        return resourceBundle.getString(messageKey);
    }

    /**
     * 根据key获取信息
     *
     * @param messageKey key值
     * @return 信息
     */
    public static String getMessageByKey(String messageKey, String language, String country) {
        Locale locale = new Locale("en", "US");
        if (StringUtils.isNotBlank(language) && StringUtils.isNotBlank(country)) {
            locale = new Locale(language, country);
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", locale);
        return resourceBundle.getString(messageKey);

    }

    /**
     * 获取http请求头部或者参数中的token值
     *
     * @param request http请求传递的值
     * @return 返回token
     */
    public static String getAuthToken(HttpServletRequest request) {
        String token;
        token = request.getParameter("accessToken");
        if (token == null) {
            token = request.getHeader("accessToken");
        }
        return token;
    }

    /**
     * 判断字符串中是否不包含字母<br> 如果包含字母就返回false,不包含字母就返回true
     *
     * @param resultStr 字符串
     * @return 判断结果
     */
    public static boolean checkIsNotContainLetters(String resultStr) {
        String re1 = "[^a-z]+$";
        Pattern p = Pattern.compile(re1, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = p.matcher(resultStr);
        return matcher.matches();
    }

    public static byte[] hexToByteArray(String s) {
        if (YfTools.isEmpty(s)) {
            return null;
        }
        return hexToByteArray(s, 0, s.length());
    }

    public static byte[] hexToByteArray(String s, int start, int end) {
        int len = (end - start) / 2 * 2;
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(start + i), 16) << 4) + Character
                .digit(s.charAt(start + i + 1), 16));
        }
        return data;
    }

    /**
     * appVersion Long转化为String
     * @param version 版本号（Long）
     * @return 字符串版本号
     */
    public static String appVersionToString(Long version) {
        return null == version ? null : ((version >> (8 * 6)) + "." + (version >> (8 * 4) & 0xFF) + "."
                + (version >> (8 * 2) & 0xFF) + "." + (version & 0xFF));
    }

    /**
     * appVersion Long型字符串转化为String
     * @param version 版本号（Long型字符串）
     * @return 字符串版本号
     */
    public static String appVersionToString(String version) {
        if (StringUtils.isBlank(version)) {
            return null;
        }
        try {
            return appVersionToString(Long.parseLong(version));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * appVersion String转化为Long，如果无法正确解析，则返回0L
     * @param versionStr 版本号（String）
     * @return 版本号（Long）
     */
    public static Long appVersionToLong(String versionStr) {
        Long version = 0L;
        if (!StringUtils.isBlank(versionStr)) {
            String[] splits = versionStr.split("\\.");
            for (int i = 0; i < splits.length; i++) {
                try {
                    version += Long.parseLong(splits[i]) << ((6 - 2 * i) * 8);
                } catch (NumberFormatException e) {
                    version = 0L;
                    break;
                }
            }
        }
        return version;
    }
    /**
     * 团队信息缓存: key:团队ID value:团队信息最后更新时间,团队队员地理位置信息缓存 背景: 1. 用户上传地理位置,如果没有发现团队缓存,就创建团队缓存,并且把团队中所有用户加入到缓存中 2.
     * 如果团队缓存存在,就更新用户本人的缓存数据 3. 团队过期以后,把过期的团队缓存持久化到数据库团队用户信息表(t_bike_team_user) TODO
     * 团队缓存定时刷新缓存,然后把数据持久化到mysql。定义定时刷新的频率和业务逻辑(这个工作放在后面做)
     *
     * Created by Infi on 17/5/17.
     */
    @Slf4j
    public static class YFTeamCache {


        /*private static Cache<Object, Object> teamCache = CacheBuilder.newBuilder()
            .maximumSize(Constants.BIKE_CACHE_MAXIMUM_SIZE)
            .expireAfterWrite(Constants.BIKE_CACHE_EXPIRE_TIME_SECONDS, TimeUnit.SECONDS)
                .recordStats()
                //设置缓存的移除通知
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        //回收原因分为：
                        //a. EXPLICIT 手动回收
                        //b. REPLACED被替换，如put，refresh
                        //c. COLLECTED 被gc（软引用，弱引用）
                        //d. EXPIRED 过期
                        //e. SIZE 超过大小

                        //1. 缓存被替换或刷新是不做持久化操作,持久化到数据库
                        if (notification != null
                                && !RemovalCause.REPLACED.equals(notification.getCause())
                                && notification.getValue() != null) {
                            HashMap<Integer, BikeTeamUser> bikeTeamUserHashMap = (HashMap<Integer, BikeTeamUser>) notification.getValue();
                            List<BikeTeamUser> updateBikeTeamUsers = new ArrayList<BikeTeamUser>();
                            for (BikeTeamUser bikeTeamUser : bikeTeamUserHashMap.values()) {
                                if (!bikeTeamUser.getExitFlag()) {
                                    updateBikeTeamUsers.add(bikeTeamUser);
                                }
                            }
                            if (updateBikeTeamUsers.size() > 0) {
                                TeamServiceUtils.getTeamServiceUtils().getBikeTeamUserService().updateBatchTeamUserByUserId(updateBikeTeamUsers);
                            }
                        }
                    }
                })
                .build();*/


        /**
         * 获得团队缓存数据
         *
         * @param key 团队ID
         * @param bikeTeamUser 用户缓存数据
         * @return 团队缓存数据, 包含了团队中所有队员的缓存数据
         */
        public static Object getTeamCache(final long key, final BikeTeamUser bikeTeamUser) {
            return null;
            /*try {

                Object var = teamCache.get(key, new Callable<Object>() {
                    // 以下是诶呦获取到缓存对象的操作
                    @Override
                    public Object call() throws Exception {
                        //1. 如果是用户退出时还没有缓存,就不创建缓存
                        if (bikeTeamUser.getExitFlag()) {
                            return null;
                        }

                        //1. 查询团队最后的更新时间,团队ID错误的话,就抛出异常
                        Long teamUpdateTime = TeamServiceUtils.getTeamServiceUtils().getBikeTeamService()
                            .findUpdateTime(key);
                        if (teamUpdateTime == null || teamUpdateTime == 0) {
                            throw new YfException(MessageKey.USER_NOT_JOIN_TEAM);
                        }
                        //2. 更新用户的地理位置信息到数据库
                        TeamServiceUtils.getTeamServiceUtils().getBikeTeamUserService().updateTeamUserSharingData(key, bikeTeamUser.getUserId(), bikeTeamUser.getSharingData());

                        //3. 查询团队所有用户的信息
                        List<BikeTeamUser> bikeTeamUsers = TeamServiceUtils.getTeamServiceUtils().getBikeTeamUserService().findTeamUserAll(key);
                        //4. 查询用户昵称和头像
                        List<Long> userIds = new ArrayList<Long>();
                        for (BikeTeamUser teamUser : bikeTeamUsers) {
                            userIds.add(teamUser.getUserId());
                        }
                        List<UserInfo> userInfos = TeamServiceUtils.getTeamServiceUtils().getUserFeign()
                            .findSimpleUserInfoList(userIds);

                        //5. 构建用户缓存信息
                        HashMap<Long, BikeTeamUser> bikeTeamUserMap = new HashMap<Long, BikeTeamUser>();
                        for (BikeTeamUser teamUser : bikeTeamUsers) {
                            for (UserInfo userInfo : userInfos) {
                                if (teamUser.getUserId().equals(userInfo.getUserId())) {
                                    teamUser.setNickname(userInfo.getNickname());
                                    teamUser.setHeadPic(userInfo.getHeadPic());
                                    break;
                                }
                            }
                            bikeTeamUserMap.put(teamUser.getUserId(), teamUser);
                        }
                        // 4. 构建团队缓存
                        BikeTeamCache bikeTeamCache = new BikeTeamCache();
                        bikeTeamCache.setTeamId(key);
                        bikeTeamCache.setUserDataMap(bikeTeamUserMap);
                        bikeTeamCache.setTeamUpdateTime(teamUpdateTime);
                        return bikeTeamCache;
                    }
                });
                return var;
            } catch (ExecutionException e) {
    //            e.printStackTrace();
                log.error("创建缓存异常", e);
                return null;
            } catch (CacheLoader.InvalidCacheLoadException e) {
                // 5. 不需要创建缓存,返回null值时会抛异常,此时拦截异常,保证正常的业务逻辑
                log.info("不创建缓存,返回null,Guava Cache抛出正常的业务异常");
    //            e.printStackTrace();
                return null;
            } catch (Exception e) {
                log.error("缓存异常");
                return null;
            }*/
        }

        /**
         * 写入缓存
         *
         * @param key teamId
         * @param value 缓存对象
         */
        public static void put(Object key, Object value) {
            /*teamCache.put(key, value);*/
        }


    }

    public static String stringFormat(String format, Map<String, Object> paramMap) {
        StringBuffer buff = new StringBuffer(format);
        for (String key : paramMap.keySet()) {
            if (isNotEmpty(paramMap.get(key))) {
                buff = replaceAll(buff, "{" + key + "}", paramMap.get(key).toString());
            }
        }
        return buff.toString();
    }

    private static StringBuffer replaceAll(StringBuffer sb, String oldStr, String newStr) {
        int i = sb.indexOf(oldStr);
        int oldLen = oldStr.length();
        int newLen = newStr.length();
        while (i > -1) {
            sb.delete(i, i + oldLen);
            sb.insert(i, newStr);
            i = sb.indexOf(oldStr, i + newLen);
        }
        return sb;
    }


    /**
     * 将16进制字符串进行字节序反序
     * @param src 例如 F1CC55D129
     * @return 反序结果，例如 29D155CCF1
     */
    public static String byteOrderReverse(String src) {
        if (src.length() % 2 > 0) {
            src = "0" + src;
        }
        StringBuilder dest = new StringBuilder();
        for (int i = src.length() / 2 - 1; i >= 0; i--) {
            dest.append(src.substring(2 * i, 2 * i + 2));
        }
        return dest.toString();
    }

}
