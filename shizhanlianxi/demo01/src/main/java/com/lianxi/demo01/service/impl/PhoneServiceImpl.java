package com.lianxi.demo01.service.impl;

import com.lianxi.demo01.entity.PhoneCategory;
import com.lianxi.demo01.entity.PhoneInfo;
import com.lianxi.demo01.entity.PhoneSpecs;
import com.lianxi.demo01.enums.ResultEnum;
import com.lianxi.demo01.exception.PhoneException;
import com.lianxi.demo01.resposity.PhoneCategoryRepository;
import com.lianxi.demo01.resposity.PhoneInfoRepository;
import com.lianxi.demo01.resposity.PhoneSpecsRepository;
import com.lianxi.demo01.service.PhoneService;
import com.lianxi.demo01.utils.PhoneUtil;
import com.lianxi.demo01.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    PhoneCategoryRepository phoneCategoryRepository;
    @Autowired
    PhoneInfoRepository phoneInfoRepository;
    @Autowired
    PhoneSpecsRepository phoneSpecsRepository;

    //1.首页详情
    @Override
    public DataVO findDataVO() {
        DataVO dataVO = new DataVO();
        //返回手机查询类型
        List<PhoneCategory> phoneCategoryList = phoneCategoryRepository.findAll();
        //常规写法
//        List<PhoneCategoryVO> phoneCategoryVOList = new ArrayList<>();
//        for (PhoneCategory phoneCategory : phoneCategoryList) {
//            PhoneCategoryVO phoneCategoryVO = new PhoneCategoryVO();
//            phoneCategoryVO.setCategoryName(phoneCategory.getCategoryName());
//            phoneCategoryVO.setCategoryType(phoneCategory.getCategoryType());
//            phoneCategoryVOList.add(phoneCategoryVO);
//        }
        //jdk8新特性写法
        List<PhoneCategoryVO> phoneCategoryVOList = phoneCategoryList.stream()
                .map(e -> new PhoneCategoryVO(
                        e.getCategoryName(),
                        e.getCategoryType()
                )).collect(Collectors.toList());
        dataVO.setCategories(phoneCategoryVOList);

        //返回手机查询的手机详情
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findByCategoryType(phoneCategoryList.get(0).getCategoryType());
        //新特性写法
//        List<PhoneInfoVO> phoneInfoVOList = phoneInfoList.stream()
//                .map( e -> new PhoneInfoVO(
//                        e.getPhoneId(),
//                        e.getPhoneName(),
//                        e.getPhonePrice(),
//                        e.getPhoneDescription(),
//                        PhoneUtil.createTag(e.getPhoneTag()),
//                        e.getPhoneIcon()
//                )).collect(Collectors.toList());

        //普通写法
        List<PhoneInfoVO> phoneInfoVOList = new ArrayList<>();
        for (PhoneInfo phoneInfo : phoneInfoList) {
            PhoneInfoVO phoneInfoVO = new PhoneInfoVO();



            BeanUtils.copyProperties(phoneInfo,phoneInfoVO);
            phoneInfoVO.setPhonePrice(phoneInfo.getPhonePrice()+"");
            phoneInfoVO.setTag(PhoneUtil.createTag(phoneInfo.getPhoneTag()));
            phoneInfoVOList.add(phoneInfoVO);
        }
        dataVO.setPhones(phoneInfoVOList);
        return dataVO;
    }

    //2.根据类型查询手机
    @Override
    public List<PhoneInfoVO> findPhoneInfoVOByCategoryType(Integer categoryId){

        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findByCategoryType(categoryId);

        List<PhoneInfoVO> phoneInfoVOList = new ArrayList<>();
        for (PhoneInfo phoneInfo : phoneInfoList) {
            PhoneInfoVO phoneInfoVO = new PhoneInfoVO();
            BeanUtils.copyProperties(phoneInfo,phoneInfoVO);
            phoneInfoVO.setPhonePrice(phoneInfo.getPhonePrice()+"");
            phoneInfoVO.setTag(PhoneUtil.createTag(phoneInfo.getPhoneTag()));
            phoneInfoVOList.add(phoneInfoVO);
        }
        return phoneInfoVOList;
    }

    @Override
    public SpecsPackageVO findSpecsPackageVOByPhoneId(Integer phoneId) {

        //返回手机详情（抽其中图片,和价格）
        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneId).get();
        List<PhoneSpecs> phoneSpecsList = phoneSpecsRepository.findSpecsByPhoneId(phoneId);

       //从内到外封装
        //tree
        List<PhoneSpecsVO> phoneSpecsVOList = new ArrayList<>();
        //list
        List<PhoneSpecsCasVO> phoneSpecsCasListVOList = new ArrayList<>();
        PhoneSpecsVO phoneSpecsVO;
        PhoneSpecsCasVO phoneSpecsCasVO;
        for (PhoneSpecs phoneSpecs : phoneSpecsList) {
            //将一个phoneSpecs拆分为两个分别给phoneSpecsVO和phoneSpecsCasListVO封装
            phoneSpecsVO = new PhoneSpecsVO();
            BeanUtils.copyProperties(phoneSpecs,phoneSpecsVO);
            phoneSpecsVOList.add(phoneSpecsVO);

            phoneSpecsCasVO = new PhoneSpecsCasVO();
            BeanUtils.copyProperties(phoneSpecs, phoneSpecsCasVO);
            phoneSpecsCasListVOList.add(phoneSpecsCasVO);

        }

        //Tree封装
        TreeVO treeVO = new TreeVO();
        treeVO.setV(phoneSpecsVOList);
        List<TreeVO> treeVOList = new ArrayList<>();
        treeVOList.add(treeVO);

        //sku封装
        SkuVO skuVO = new SkuVO();
        skuVO.setList(phoneSpecsCasListVOList);
        skuVO.setTree(treeVOList);
        //将b、BigDecimal类型的价格转为int
        Integer price = phoneInfo.getPhonePrice().intValue();
        skuVO.setPrice(price+".00");
        skuVO.setStock_num(phoneInfo.getPhoneStock());

        //对整体的SpecsPackageVO封装
        SpecsPackageVO specsPackageVO = new SpecsPackageVO();
        specsPackageVO.setSku(skuVO);
        //goods
        Map<String,String> goods = new HashMap<>();
        goods.put("pictures",phoneInfo.getPhoneIcon());
        specsPackageVO.setGoods(goods);


        return specsPackageVO;
    }

    @Override
    public void subStock(Integer specsId, Integer quantity) {
        //库存中的数量在两张表中有体现--（1）phoneSpecs，（2）phoneInfo
        PhoneSpecs phoneSpecs = phoneSpecsRepository.findById(specsId).get();

        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();

        Integer result = phoneSpecs.getSpecsStock() - quantity;
        if (result < 0){
            log.error("[扣库存]库存不足");
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }
        phoneSpecs.setSpecsStock(result);
        phoneSpecsRepository.save(phoneSpecs);

        result = phoneInfo.getPhoneStock() - quantity;
        if (result < 0){
            log.error("[扣库存]库存不足");
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }
        phoneInfo.setPhoneStock(result);
        phoneInfoRepository.save(phoneInfo);

    }
}
