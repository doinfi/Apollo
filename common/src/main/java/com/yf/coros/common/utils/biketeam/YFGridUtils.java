package com.yf.coros.common.utils.biketeam;

import com.yf.coros.common.config.ApplicationContants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 二维地图网格化,工具类<br> 1. 计算任意一个进纬度对应的网格ID<br> 2. 任意一个网格ID对应周围3x3,共9个方格,不一定是3x3的网格,根据查找范围大小获得,每个网格的边长是111米,<br>
 * 如果查找半径是x米。把查找范围的网格数量是r = 111/2 + 111x  (x:表示矩阵个数,r:表示查找半径)<br> x = r/111 - 1/2<br>
 * 所以查找半径r=300米,那矩阵边x = 300/111 - 1/2 = 2.2 ~= 2<br> 矩阵的边长= 2 x 2 +1 = 5<br>
 * 所以这个矩阵是经纬度所在网格为中心,周围5x5的矩阵给<br>
 *
 * @author Infi
 * @date 17/5/12
 */
@Slf4j
public class YFGridUtils {

    /**
     * 团队搜索半径
     */
    private final static Integer BIKE_TEAM_SEARCH_RADIUS = ApplicationContants.BIKE_TIME_SEARCH_RADIUS;
    /**
     * 矩阵搜索范围,如果MATRICES_LENGTH=2,就表示要搜索用户所在网格为中心周围扩展2个网格的矩阵,矩阵应该是5x5共25个
     */
    private final static Integer MATRICES_LENGTH = new BigDecimal(
        NumberUtils.toDouble(String.valueOf(BIKE_TEAM_SEARCH_RADIUS.toString())) / 111.0 - 0.5)
        .setScale(0, BigDecimal.ROUND_HALF_UP).intValue();

    /**
     * 获得任意一个经纬度所在的网格ID<br> ID = (longitude + 180) * 10的9次方 + (latitude + 90) * 10的3次方= 302123000000
     * <br>
     *
     * @param longitude 经度
     * @param latitude 纬度
     * @return 网格ID
     */
    public static Long getGridId(Double longitude, Double latitude) {
        // 1. 获得经纬度所在网格的经纬度
        Long gridLongitudeLong = getResideGridLongitudeOrLatitude(longitude, 180);
        Long gridLatitudeLong = getResideGridLongitudeOrLatitude(latitude, 90);
        return computeGridId(gridLongitudeLong, gridLatitudeLong);
    }

    /**
     * 获得矩阵搜索范围,获得矩阵中每个网格的ID<br> 如果MATRICES_LENGTH=2,就表示要搜索用户所在网格为中心周围扩展2个网格的矩阵,矩阵应该是5x5共25个<br>
     *
     * @param longitude 经度
     * @param latitude 纬度
     * @return 网格ID
     */
    public static List<Long> getMatricesGridIds(Double longitude, Double latitude) {
        // 1. 获得经纬度所在网格的经纬度,获取到最小进纬度
        Long minGridLongitudeLong = getResideGridLongitudeOrLatitude(longitude, 180) - 2;
        Long minGridLatitudeLong = getResideGridLongitudeOrLatitude(latitude, 90) - 2;
        List<Long> matricesGridIds = new ArrayList<Long>();
        // 2. 由最小进纬度遍历,获得查找网格范围的ID列表
        Integer countFor = MATRICES_LENGTH * 2 + 1;
        for (int i = 0; i < countFor; i++) {
            for (int j = 0; j < countFor; j++) {
                // 3. 经度不能超过360,纬度不能超过180
                if (minGridLongitudeLong + i <= 360000 && minGridLatitudeLong + j <= 180000) {
                    matricesGridIds
                        .add(computeGridId(minGridLongitudeLong + i, minGridLatitudeLong + j));
                }
            }
        }
        return matricesGridIds;
    }

    /**
     * 获得经纬度所在网格经纬度
     *
     * @param longitudeOrLatitude 经纬度
     * @param shift 偏移量,经度偏移180度,纬度偏移90度
     * @return 返回所属网格的经纬度
     */
    private static Long getResideGridLongitudeOrLatitude(Double longitudeOrLatitude,
        Integer shift) {
        // 1. 经度向右偏移180度,纬度向上偏移90度,保证经纬度不是负数
        BigDecimal longitudeOrLatitudeDecimal = new BigDecimal(
            (longitudeOrLatitude + shift) * 1000);
        // 2. 经纬度取余
        BigDecimal remainderLongitudeOrLatitude = longitudeOrLatitudeDecimal
            .subtract(new BigDecimal(longitudeOrLatitudeDecimal.intValue()));
        // 3. 如果小数点3位以后大于0,经纬度的小数点第三位就要加1,否则不变
        Long longitudeOrLatitudeLong = longitudeOrLatitudeDecimal.longValue();
        if (remainderLongitudeOrLatitude.compareTo(new BigDecimal(0)) == 1) {
            longitudeOrLatitudeLong++;
        }
        return longitudeOrLatitudeLong;
    }

    /**
     * 计算网格ID
     *
     * @param gridLongitudeLong 网格经度,扩大了1000倍得到整数
     * @param gridLatitudeLong 网格纬度,扩大了1000倍得到整数
     * @return 网格ID
     */
    private static Long computeGridId(Long gridLongitudeLong, Long gridLatitudeLong) {
        return gridLongitudeLong * 1000000 + gridLatitudeLong;
    }
}
