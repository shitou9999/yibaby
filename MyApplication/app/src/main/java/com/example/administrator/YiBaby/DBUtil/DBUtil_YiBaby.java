package com.example.administrator.YiBaby.DBUtil;
import com.example.administrator.YiBaby.Entity.Product;
import com.example.administrator.YiBaby.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/2.
 */
public class DBUtil_YiBaby {
    public static int[] getImgResData(){
        int[] imgs={R.mipmap.c01, R.mipmap.c03};
        return imgs;
    }
    public static List<Product> getProductData(){
        List<Product> productList=new ArrayList<Product>();
        productList.add(new Product("每日宫系列婴幼儿奶粉",0));
        productList.add(new Product(R.mipmap.baby01,"每日宫幼儿配方奶粉","$480.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product(R.mipmap.baby02,"每日宫幼儿配方奶粉","$580.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product(R.mipmap.baby03,"每日宫幼儿配方奶粉","$680.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product(R.mipmap.baby03,"每日宫幼儿配方奶粉","$780.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product(R.mipmap.baby02,"每日宫幼儿配方奶粉","$880.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product("每日金典名作系列奶粉",0));
        productList.add(new Product(R.mipmap.baby03,"每日宫幼儿配方奶粉","$980.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product(R.mipmap.baby01,"每日宫幼儿配方奶粉","$480.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product(R.mipmap.baby02,"每日宫幼儿配方奶粉","$580.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product(R.mipmap.baby03,"每日宫幼儿配方奶粉","$680.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product("每日敏瑞键系列奶粉",0));
        productList.add(new Product(R.mipmap.baby03,"每日宫幼儿配方奶粉","$780.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product(R.mipmap.baby02,"每日宫幼儿配方奶粉","$880.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product(R.mipmap.baby03,"每日宫幼儿配方奶粉","$980.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product(R.mipmap.baby01,"每日宫幼儿配方奶粉","$480.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        return productList;
    }
    public static int getProductCount(){
        return 30;
    }
    public static List<Product> getProductDataByPage(int pageNo,int pageSize){
        List<Product> productList=new ArrayList<Product>();
        for(int i=0;i<pageSize;i++){
            if(i%5==0){
                productList.add(new Product(R.mipmap.baby01,"每日宫幼儿配方奶粉","$480.00","适用于1-3岁幼儿",R.mipmap.set_point,1));
            }else{
                productList.add(new Product(R.mipmap.baby02,"我在更新数据","$580.00","适用于1-3岁幼儿",R.mipmap.set_point,1));

            }
        }
        return productList;
    }
    public static List<Product> getProductData2(){
        List<Product> productList=new ArrayList<Product>();
        productList.add(new Product("每日宫系列婴幼儿奶粉",0));
        productList.add(new Product(R.mipmap.m0003,"每日宫幼儿配方奶粉1段","$398.00","适用于出生至100天岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product(R.mipmap.baby02,"每日宫幼儿配方奶粉1段","$208.00","适用于出生至100天岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product("每日金典名作系列奶粉",0));
        productList.add(new Product(R.mipmap.baby03,"每日金典名作婴儿配方奶粉","$328.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product(R.mipmap.baby01,"每日金典名作婴儿配方奶粉","$178.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        return productList;
    }
    public static List<Product> getProductData3(){
        List<Product> productList=new ArrayList<Product>();
        productList.add(new Product("每日宫系列婴幼儿奶粉",0));
        productList.add(new Product(R.mipmap.baby01,"每日宫幼儿配方奶粉2段","$398.00","适用于出生100天至6个月岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product("每日金典名作系列奶粉",0));
        productList.add(new Product(R.mipmap.baby03,"每日金典名作婴儿配方奶粉","$328.00","适用于出生100天至6个月岁幼儿",R.mipmap.point_left,1));
        return productList;
    }
    public static List<Product> getProductData4(){
        List<Product> productList=new ArrayList<Product>();
        productList.add(new Product("每日宫系列婴幼儿奶粉",0));
        productList.add(new Product(R.mipmap.baby01,"每日宫较大婴儿配方奶粉","$398.00","适用于出生6个月至1岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product("每日金典名作系列奶粉",0));
        productList.add(new Product(R.mipmap.baby03,"每日宫较大婴儿配方奶粉","$328.00","适用于出生6个月至1岁幼儿",R.mipmap.point_left,1));
        return productList;
    }
    public static List<Product> getProductData5(){
        List<Product> productList=new ArrayList<Product>();
        productList.add(new Product("每日宫系列婴幼儿奶粉",0));
        productList.add(new Product(R.mipmap.m0002,"每日宫幼儿配方奶粉4段","$398.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        productList.add(new Product("每日金典名作系列奶粉",0));
        productList.add(new Product(R.mipmap.baby03,"每日宫幼儿配方奶粉4段","$328.00","适用于1-3岁幼儿",R.mipmap.point_left,1));
        return productList;
    }
    public static List<Product> getProductData6(){
        List<Product> productList=new ArrayList<Product>();
        productList.add(new Product("每日韵晨佳系列婴幼儿奶粉",0));
        productList.add(new Product(R.mipmap.m0001,"每日韵晨佳300g","$398.00","适用于乳蛋白及多种食物蛋白过敏婴幼儿",R.mipmap.point_left,1));
        productList.add(new Product("每日敏瑞键系列奶粉",0));
        productList.add(new Product(R.mipmap.baby03,"每日敏瑞键400g","$328.00","适用于早产低出生体重婴儿",R.mipmap.point_left,1));
        return productList;
    }
}
