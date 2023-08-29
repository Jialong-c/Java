package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    /**
     * 条件查询
     *
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * 更新商品数量
     *
     * @param shoppingCart
     */
    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateNumberById(ShoppingCart shoppingCart);

    /**
     * 插入购物车数据
     *
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart (name, user_id, dish_id, setmeal_id, dish_flavor, number, amount, image, create_time) " +
            " values (#{name},#{userId},#{dishId},#{setmealId},#{dishFlavor},#{number},#{amount},#{image},#{createTime})")
    void insert(ShoppingCart shoppingCart);

    @Delete("delete from shopping_cart where user_id = #{userId}")
    void deleteByUserId(Long currentId);

    void insertBatch(List<ShoppingCart> shoppingCartList);

    @Select("select * from shopping_cart where dish_id=#{dishId} and user_id=#{userId}")
    ShoppingCart selectDishNumber(Long dishId, Long userId);

    @Delete("delete from shopping_cart where user_id = #{userId} and dish_id=#{dishId}")
    void deleteDish(Long dishId, Long userId);

    @Delete("delete from shopping_cart where user_id = #{userId} and setmeal_id=#{setmealId}")
    void deleteSetmeal(Long setmealId, Long userId);

    @Select("select * from shopping_cart where setmeal_id=#{setmealId} and user_id=#{userId}")
    ShoppingCart selectSetmealNumber(Long setmealId, Long userId);
}