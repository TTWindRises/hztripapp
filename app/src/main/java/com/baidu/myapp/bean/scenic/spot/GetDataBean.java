package com.baidu.myapp.bean.scenic.spot;

import com.baidu.myapp.R;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.bean.food.FoodCategory;
import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.bean.hotel.HotelBean;
import com.baidu.myapp.bean.scenic.ScenicProject;
import com.baidu.myapp.util.Debbuger;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import static com.baidu.location.g.j.P;

/**
 * Created by Administrator on 2019/2/18.
 */

public class GetDataBean {
    public List<SpotBean> LoadLoacalSpotBean() {
        List<SpotBean> splist = new ArrayList<>();
        //贺州学院
        //1
        splist = DataSupport.findAll(SpotBean.class);
        if (splist != null && !splist.isEmpty()) {
            return DataSupport.findAll(SpotBean.class);
        } else {
            SpotBean sp = new SpotBean();
            sp.setSpotId(0);
            sp.setSpotName("第三图书馆");
            sp.setSpotLatitude(24.418104);
            sp.setSpotOverlayImg(String.valueOf(R.drawable.spot_tushuguan));
            sp.setScenic_id(1);
            sp.setSpotLongtitude(111.520515);
            //播报
            sp.setSpotDescribe("图书馆建立于1949年，共有2万3千多本书籍，其中有2000多本古代书籍，是每个热爱学习的同学的圣地，各项buff加成，学习事半功倍");
            sp.setNextVoiceSrc("穿过小树林，前往逸夫楼");
            sp.setSpotImg(String.valueOf(R.drawable.tushuguan_img));
            //2
            SpotBean sp2 = new SpotBean();
            sp2.setSpotId(1);
            sp2.setSpotName("小桃林");
            sp2.setSpotLatitude(24.417182);
            sp2.setScenic_id(1);
            sp2.setSpotLongtitude(111.521755);
            //播报
            sp2.setSpotDescribe("桃林满天下");
            sp2.setNextVoiceSrc("继续前进，前方图书馆");
            sp2.setSpotOverlayImg(String.valueOf(R.drawable.spot_taohualin));
            sp2.setSpotImg(String.valueOf(R.drawable.taolin_img));
            //3
            SpotBean sp3 = new SpotBean();
            sp3.setSpotId(2);
            sp3.setSpotName("逸夫楼");
            sp3.setSpotLatitude(24.4175);
            sp3.setScenic_id(1);
            sp3.setSpotOverlayImg(String.valueOf(R.drawable.spot_yishulou));
            sp3.setSpotLongtitude(111.51793);
            //播报
            sp3.setSpotDescribe("逸夫基金会捐赠建造");
            sp3.setNextVoiceSrc("原路返回到大转盘处按方向指向前往创意园");
            sp3.setSpotImg(String.valueOf(R.drawable.yifuluo_img));
            //4
            SpotBean sp4 = new SpotBean();
            sp4.setSpotId(3);
            sp4.setSpotName("创意园");
            sp4.setSpotLatitude(24.415065);
            sp4.setScenic_id(1);
            sp4.setSpotLongtitude(111.519837);
            sp4.setSpotOverlayImg(String.valueOf(R.drawable.spot_chuangyiyuan));
            //播报
            sp4.setSpotDescribe("2008年建设，每年都会由学生自主设计主题");
            sp4.setNextVoiceSrc("导游结束~");
            sp4.setSpotImg(String.valueOf(R.drawable.chuangyiyuan_img));

            //黄姚古镇
            //1
            SpotBean hy = new SpotBean();
            hy.setSpotId(1001);
            hy.setSpotName("新兴街");
            hy.setSpotLatitude(24.418365);
            hy.setScenic_id(0);
            hy.setSpotLongtitude(111.52151);
            //播报
            hy.setSpotDescribe("街道繁华，有很多纪念品和特色产品销售");
            hy.setNextVoiceSrc("穿过小树林，前往逸夫楼");
            hy.setSpotImg(String.valueOf(R.drawable.huangyao_xinxingjie_img));
            //2
            SpotBean hy2 = new SpotBean();
            hy2.setSpotId(1002);
            hy2.setSpotName("带龙桥");
            hy2.setSpotLatitude(24.417987);
            hy2.setScenic_id(0);
            hy2.setSpotLongtitude(111.521413);
            //播报
            hy2.setSpotDescribe("古代桥拱，桥壁上刻有龙图");
            hy2.setNextVoiceSrc("继续前进，前方图书馆");
            hy2.setSpotImg(String.valueOf(R.drawable.huangyao_dailongqiao_img));
            //3
            SpotBean hy3 = new SpotBean();
            hy3.setSpotId(1003);
            hy3.setSpotName("千年古榕");
            hy3.setSpotLatitude(24.4175);
            hy3.setScenic_id(0);
            hy3.setSpotLongtitude(111.51793);
            //播报
            hy3.setSpotDescribe("存活千年~为当地居民祈福的圣树，可以在树枝上系绑红绳红带祈求福运哦~");
            hy3.setNextVoiceSrc("原路返回到大转盘处按方向指向前往创意园");
            hy3.setSpotImg(String.valueOf(R.drawable.huangyao_qianniangurong_img));
            //4
            SpotBean hy4 = new SpotBean();
            hy4.setSpotId(1004);
            hy4.setSpotName("钱兴烈士像");
            hy4.setSpotLatitude(24.415065);
            hy4.setScenic_id(0);
            hy4.setSpotLongtitude(111.519837);
            //播报
            hy4.setSpotDescribe("1946年9月，解放战争爆发后，积极领导组织桂林、柳州、南宁、梧州等城市的爱国民主运动，同时在农村开展反对国民党征兵、征粮、征税的“三征”斗争。1947年4月，在横县西区六秀村主持召开有广西地下党各地区领导人员参加的“横县会议”，传达中共中央上海局香港分局的指示，确定了放手发动群众，积极组织武装起义，开展武装斗争，开辟游击区的广西对敌斗争方针。同年7月，在广东省广宁县寮炭岗召开粤桂湘边工委扩大会议，当选为粤桂湘边工委副书记兼粤桂湘边纵队（西江纵队）副政委。同年8月15日晚，在广东省怀集县领导怀南起义，建立怀南游击根据地。1948年5月，国民党反动派疯狂“围剿”游击根据地，他负责绥江下游的反“围剿”斗争，率领留守部队与敌人浴血奋战，打退敌人多次进攻。同年9月，把留守部队整编为一个大队，任大队长。是年11月，在怀集县坳仔仕儒村山头遭敌袭击，不幸牺牲。");
            hy4.setNextVoiceSrc("导游结束~");
            hy4.setSpotImg(String.valueOf(R.drawable.huangyao_lieshi_img));


            //玉石林
            //1
            SpotBean ys = new SpotBean();
            ys.setSpotId(2001);
            ys.setSpotName("一线天");
            ys.setSpotLatitude(24.418365);
            ys.setScenic_id(2);
            ys.setSpotLongtitude(111.52151);
            //播报
            ys.setSpotDescribe("山中有两山，两山似一山，两山中间过，宛如一线天。");
            ys.setNextVoiceSrc("穿过小树林，前往逸夫楼");
            ys.setSpotImg(String.valueOf(R.drawable.yushilin_yixiantian_img));
            //2
            SpotBean ys2 = new SpotBean();
            ys2.setSpotId(2002);
            ys2.setSpotName("玉石观音");
            ys2.setSpotLatitude(24.418365);
            ys2.setScenic_id(2);
            ys2.setSpotLongtitude(111.52151);
            //播报
            ys2.setSpotDescribe("玉石观音，祈求平安健康~");
            ys2.setNextVoiceSrc("穿过小树林，前往逸夫楼");
            ys2.setSpotImg(String.valueOf(R.drawable.yushilin_yushiguanyin_img));
            //3
            SpotBean ys3 = new SpotBean();
            ys3.setSpotId(2003);
            ys3.setSpotName("大溶洞");
            ys3.setSpotLatitude(24.418365);
            ys3.setScenic_id(2);
            ys3.setSpotLongtitude(111.52151);
            //播报
            ys3.setSpotDescribe("幽幽溶洞~");
            ys3.setNextVoiceSrc("穿过小树林，前往逸夫楼");
            ys3.setSpotImg(String.valueOf(R.drawable.yushilin_darongdong));
            //4
            SpotBean ys4 = new SpotBean();
            ys4.setSpotId(2004);
            ys4.setSpotName("玉石成林");
            ys4.setSpotLatitude(24.418365);
            ys4.setScenic_id(2);
            ys4.setSpotLongtitude(111.52151);
            //播报
            ys4.setSpotDescribe("紧凑的玉石看起来像一片山林");
            ys4.setNextVoiceSrc("穿过小树林，前往逸夫楼");
            ys4.setSpotImg(String.valueOf(R.drawable.yushilin_yushichenglin_img));


            //十八水
            //1
            SpotBean sb = new SpotBean();
            sb.setSpotId(3001);
            sb.setSpotName("情人湖");
            sb.setSpotLatitude(24.418365);
            sb.setScenic_id(3);
            sb.setSpotLongtitude(111.52151);
            //播报
            sb.setSpotDescribe("清澈的湖水动听的溪流声，鱼儿欢快似是欢迎那走来的情侣");
            sb.setNextVoiceSrc("穿过小树林，前往逸夫楼");
            sb.setSpotImg(String.valueOf(R.drawable.shibashui_qingrenhu_img));
            //2
            SpotBean sb2 = new SpotBean();
            sb2.setSpotId(3002);
            sb2.setSpotName("大佛瀑布");
            sb2.setSpotLatitude(24.417987);
            sb2.setScenic_id(3);
            sb2.setSpotLongtitude(111.521413);
            //播报
            sb2.setSpotDescribe("气势磅礴，犹如大佛~");
            sb2.setNextVoiceSrc("继续前进，前方图书馆");
            sb2.setSpotImg(String.valueOf(R.drawable.shibashui_dafopubu_img));
            //3
            SpotBean sb3 = new SpotBean();
            sb3.setSpotId(3003);
            sb3.setSpotName("神龙瀑布");
            sb3.setSpotLatitude(24.4175);
            sb3.setScenic_id(3);
            sb3.setSpotLongtitude(111.51793);
            //播报
            sb3.setSpotDescribe("神龙见首不见尾，瀑布直流如同神龙摆尾");
            sb3.setNextVoiceSrc("原路返回到大转盘处按方向指向前往创意园");
            sb3.setSpotImg(String.valueOf(R.drawable.shibashui_shenlongpubu_img));
            //4
            SpotBean sb4 = new SpotBean();
            sb4.setSpotId(3004);
            sb4.setSpotName("菩提石");
            sb4.setSpotLatitude(24.415065);
            sb4.setScenic_id(3);
            sb4.setSpotLongtitude(111.519837);
            //播报
            sb4.setSpotDescribe("菩提本无树，一岁一枯荣，本是无一物，何处惹尘埃。");
            sb4.setNextVoiceSrc("导游结束~");
            sb4.setSpotImg(String.valueOf(R.drawable.shibashui_putishi_img));


            //姑婆山
            //1
            SpotBean gp = new SpotBean();
            gp.setSpotId(4001);
            gp.setSpotName("天堂山顶");
            gp.setSpotLatitude(24.418365);
            gp.setScenic_id(4);
            gp.setSpotLongtitude(111.52151);
            //播报
            gp.setSpotDescribe("云深不知处，恍如天上人");
            gp.setNextVoiceSrc("穿过小树林，前往逸夫楼");
            gp.setSpotImg(String.valueOf(R.drawable.guposhan_tiantangshanding_img));
            //2
            SpotBean gp2 = new SpotBean();
            gp2.setSpotId(4002);
            gp2.setSpotName("方家茶园");
            gp2.setSpotLatitude(24.418365);
            gp2.setScenic_id(4);
            gp2.setSpotLongtitude(111.52151);
            //播报
            gp2.setSpotDescribe("采茶喝茶，休闲自由~");
            gp2.setNextVoiceSrc("穿过小树林，前往逸夫楼");
            gp2.setSpotImg(String.valueOf(R.drawable.guposhan_fangjiachayuan_img));
            //3
            SpotBean gp3 = new SpotBean();
            gp3.setSpotId(4003);
            gp3.setSpotName("仙姑瀑布");
            gp3.setSpotLatitude(24.418365);
            gp3.setScenic_id(4);
            gp3.setSpotLongtitude(111.52151);
            //播报
            gp3.setSpotDescribe("瀑布下吹凉风，清爽避暑快乐逍遥");
            gp3.setNextVoiceSrc("穿过小树林，前往逸夫楼");
            gp3.setSpotImg(String.valueOf(R.drawable.guposhan_xiangupubu_img));
            //4
            SpotBean gp4 = new SpotBean();
            gp4.setSpotId(4004);
            gp4.setSpotName("贺州温泉");
            gp4.setSpotLatitude(24.418365);
            gp4.setScenic_id(4);
            gp4.setSpotLongtitude(111.52151);
            //播报
            gp4.setSpotDescribe("冬日泡温泉幸福满心间");
            gp4.setNextVoiceSrc("穿过小树林，前往逸夫楼");
            gp4.setSpotImg(String.valueOf(R.drawable.guposhan_wenquan_img));

            splist.add(sp);
            splist.add(sp2);
            splist.add(sp3);
            splist.add(sp4);

            splist.add(hy);
            splist.add(hy2);
            splist.add(hy3);
            splist.add(hy4);

            splist.add(ys);
            splist.add(ys2);
            splist.add(ys3);
            splist.add(ys4);

            splist.add(sb);
            splist.add(sb2);
            splist.add(sb3);
            splist.add(sb4);

            splist.add(gp);
            splist.add(gp2);
            splist.add(gp3);
            splist.add(gp4);
            sp.SaveAllSpotBean(splist);
            return splist;
        }
    }

    public void LoadFoodData() {
        //longtitude 1开头的
        //latitude 2开头的
        //饺子皇饺子店 111.543813,24.416317
        //学院食府 111.520595,24.415512
        if (DataSupport.findAll(FoodStore.class) == null || DataSupport.findAll(FoodStore.class).isEmpty()) {


            List<FoodStore> foodStores = new ArrayList<FoodStore>();
  /*      foodStores = DataSupport.findAll(FoodStore.class);
        if (foodStores != null && !foodStores.isEmpty()) {

        }*/
            FoodStore foodStore = new FoodStore();
            foodStore.setStoreID("7");
            foodStore.setStoreName("学院食府");
            foodStore.setStoreImg("" + R.drawable.store_head_bg);
            foodStore.setStore_heard_img("" + R.drawable.store_head_img);
            foodStore.setStoreLatitude(24.415512);
            foodStore.setStoreLongtitude(111.520595);
            foodStore.setStorePraise("99%");
            foodStore.setStoreDescribe("深受万千学生的喜爱，每个学生都有心中的那一碗撸粉~");
            foodStore.setTotalSales(999);
            foodStore.setScenic_id(1);
            FoodStore foodStore2 = new FoodStore();
            foodStore2.setStoreID("8");
            foodStore2.setStoreName("大黄饺子店");
            foodStore2.setStoreLatitude(24.416317);
            foodStore2.setStoreLongtitude(111.543813);
            foodStore2.setScenic_id(1);
            foodStore2.setTotalSales(782);
            foodStore2.setStorePraise("94%");
            foodStore2.setStoreDescribe("吃了还想吃，回味无穷");
            foodStore2.setStoreImg("" + R.drawable.store_head_bg);
            foodStore2.setStore_heard_img("" + R.drawable.food_store_head_img3);
            foodStores.add(foodStore);
            foodStores.add(foodStore2);
            foodStore.saveAllFoodStore(foodStores);

//        overlayUtil.addFoodStoreAllOverly(foodStores);
            List<FoodBean> foodBeanList = new ArrayList<>();
            FoodBean foodBean = new FoodBean();
            foodBean.setFoodName("单人汉堡套餐");
            foodBean.setFoodID(0);
            foodBean.setFoodSales(20);
            foodBean.setFoodPraise("好评率100%");
            foodBean.setFoodImg("" + R.drawable.food_horizontal_item_img);
            foodBean.setFoodDescribe("令人怀念的味道");
            foodBean.setFoodOriginalPrice("21");
            foodBean.setFoodPresentPrice("15");
            foodBean.setCategory_id(1);
            foodBean.setStore_id(7 + "");
            FoodBean foodBean2 = new FoodBean();
            foodBean2.setFoodName("香芋面包");
            foodBean2.setFoodID(1);
            foodBean2.setCategory_id(0);
            foodBean2.setFoodSales(200);
            foodBean2.setFoodImg("" + R.drawable.food_horizontal_item_mianbao);
            foodBean2.setFoodDescribe("令人怀念的味道");
            foodBean2.setFoodOriginalPrice("1");
            foodBean2.setFoodPresentPrice("0.5");
            foodBean2.setStore_id(7 + "");
            FoodBean foodBean3 = new FoodBean();
            foodBean3.setFoodName("油条");
            foodBean3.setCategory_id(0);
            foodBean3.setFoodID(2);
            foodBean3.setFoodSales(200);
            foodBean3.setFoodImg("" + R.drawable.food_horizontal_item_youtiao_img);
            foodBean3.setFoodDescribe("令人怀念的味道");
            foodBean3.setFoodOriginalPrice("1");
            foodBean3.setFoodPresentPrice("0.5");
            foodBean3.setStore_id(7 + "");
            FoodBean foodBean4 = new FoodBean();
            foodBean4.setFoodName("营养豆浆");
            foodBean4.setFoodID(3);
            foodBean4.setFoodSales(157);
            foodBean4.setCategory_id(1);
            foodBean4.setFoodImg("" + R.drawable.food_horizontal_item_doujiang_img);
            foodBean4.setFoodDescribe("油条配豆浆好像更搭哦~");
            foodBean4.setFoodOriginalPrice("3");
            foodBean4.setFoodPresentPrice("1.5");
            foodBean4.setStore_id(7 + "");
            FoodBean foodBean5 = new FoodBean();
            foodBean5.setFoodName("大良家的金牌烧鸡");
            foodBean5.setFoodID(4);
            foodBean5.setFoodSales(67);
            foodBean5.setCategory_id(1);
            foodBean5.setFoodImg("" + R.drawable.food_horizontal_item_shaoji);
            foodBean5.setFoodDescribe("令人怀念的味道");
            foodBean5.setFoodOriginalPrice("39");
            foodBean5.setFoodPresentPrice("20.9");
            foodBean5.setStore_id(7 + "");

            FoodBean foodBean6 = new FoodBean();
            foodBean6.setFoodName("黄金酥脆炒粉");
            foodBean6.setFoodID(5);
            foodBean6.setFoodSales(67);
            foodBean6.setCategory_id(1);
            foodBean6.setFoodImg("" + R.drawable.food_horizontal_item_chaofen);
            foodBean6.setFoodDescribe("令人怀念的味道");
            foodBean6.setFoodOriginalPrice("12");
            foodBean6.setFoodPresentPrice("9");
            foodBean6.setStore_id(7 + "");
            FoodBean foodBean7 = new FoodBean();
            foodBean7.setFoodName("清香椰子饭");
            foodBean7.setFoodID(6);
            foodBean7.setFoodSales(123);
            foodBean7.setCategory_id(1);
            foodBean7.setFoodImg("" + R.drawable.food_horizontal_yezhifan_img);
            foodBean7.setFoodDescribe("清香入喉饭甜可口");
            foodBean7.setFoodOriginalPrice("29");
            foodBean7.setFoodPresentPrice("14.5");
            foodBean7.setStore_id(7 + "");
            FoodBean foodBean8 = new FoodBean();
            foodBean8.setFoodName("青青草原冰");
            foodBean8.setFoodID(7);
            foodBean8.setFoodSales(235);
            foodBean8.setCategory_id(2);
            foodBean8.setFoodImg("" + R.drawable.food_horizontal_icecreen_img);
            foodBean8.setFoodDescribe("爱的味道");
            foodBean8.setFoodOriginalPrice("6");
            foodBean8.setFoodPresentPrice("3.9");
            foodBean8.setStore_id(7 + "");
            FoodBean foodBean9 = new FoodBean();
            foodBean9.setFoodName("绿绿草原冰");
            foodBean9.setFoodID(8);
            foodBean9.setFoodSales(271);
            foodBean9.setCategory_id(2);
            foodBean9.setFoodImg("" + R.drawable.food_horizontal_icecreen2_img);
            foodBean9.setFoodDescribe("小清新冰淇淋初恋般的感觉");
            foodBean9.setFoodOriginalPrice("6");
            foodBean9.setFoodPresentPrice("3");
            foodBean9.setStore_id(7 + "");
            FoodBean foodBean10 = new FoodBean();
            foodBean10.setFoodName("大鸡排饭");
            foodBean10.setFoodID(9);
            foodBean10.setFoodSales(23);
            foodBean10.setCategory_id(1);
            foodBean10.setFoodImg("" + R.drawable.food_horizontal_jipaifan_img);
            foodBean10.setFoodDescribe("量多吃的饱");
            foodBean10.setFoodOriginalPrice("23");
            foodBean10.setFoodPresentPrice("16");
            foodBean10.setStore_id(7 + "");
            FoodBean foodBean11 = new FoodBean();
            foodBean11.setFoodName("冬瓜青苹果冰");
            foodBean11.setFoodID(10);
            foodBean11.setFoodSales(244);
            foodBean11.setCategory_id(2);
            foodBean11.setFoodImg("" + R.drawable.food_horizontal_icecreen3_img);
            foodBean11.setFoodDescribe("美味冰淇淋");
            foodBean11.setFoodOriginalPrice("5");
            foodBean11.setFoodPresentPrice("3");
            foodBean11.setStore_id(7 + "");
            FoodBean foodBean12 = new FoodBean();
            foodBean12.setFoodName("咖啡米朵冰");
            foodBean12.setFoodID(11);
            foodBean12.setFoodSales(342);
            foodBean12.setCategory_id(2);
            foodBean12.setFoodImg("" + R.drawable.food_horizontal_icecreen4_img);
            foodBean12.setFoodDescribe("美味冰淇淋");
            foodBean12.setFoodOriginalPrice("10");
            foodBean12.setFoodPresentPrice("8");
            foodBean12.setStore_id(7 + "");
            FoodBean foodBean13 = new FoodBean();
            foodBean13.setFoodName("核桃牛奶冰");
            foodBean13.setFoodID(12);
            foodBean13.setFoodSales(213);
            foodBean13.setCategory_id(2);
            foodBean13.setFoodImg("" + R.drawable.food_horizontal_icecreen5_img);
            foodBean13.setFoodDescribe("美味冰淇淋");
            foodBean13.setFoodOriginalPrice("12");
            foodBean13.setFoodPresentPrice("5");
            foodBean13.setStore_id(7 + "");


            foodBeanList.add(foodBean);
            foodBeanList.add(foodBean2);
            foodBeanList.add(foodBean3);
            foodBeanList.add(foodBean4);
            foodBeanList.add(foodBean5);
            foodBeanList.add(foodBean6);
            foodBeanList.add(foodBean7);
            foodBeanList.add(foodBean8);
            foodBeanList.add(foodBean9);
            foodBeanList.add(foodBean10);
            foodBeanList.add(foodBean11);
            foodBeanList.add(foodBean12);
            foodBeanList.add(foodBean13);
            foodBean.saveFoodBean(foodBeanList);


            FoodCategory foodCategory = new FoodCategory();
            foodCategory.setCategoryID(0);
            foodCategory.setCategoryName("热销");
            foodCategory.setStore_id("7");

            FoodCategory foodCategory2 = new FoodCategory();
            foodCategory2.setCategoryID(1);
            foodCategory2.setCategoryName("推荐");
            foodCategory2.setStore_id("7");

            FoodCategory foodCategory3 = new FoodCategory();
            foodCategory3.setCategoryID(2);
            foodCategory3.setCategoryName("甜品");
            foodCategory3.setStore_id("7");
/*
        FoodCategory foodCategory4 = new FoodCategory();
        foodCategory4.setCategoryID(4);
        foodCategory4.setCategoryName("午餐");
        foodCategory4.setStore_id("7");

        FoodCategory foodCategory5 = new FoodCategory();
        foodCategory5.setCategoryID(5);
        foodCategory5.setCategoryName("晚餐");
        foodCategory5.setStore_id("7");*/

            List<FoodCategory> categoryList = new ArrayList<FoodCategory>();
            categoryList.add(foodCategory);
            categoryList.add(foodCategory2);
            categoryList.add(foodCategory3);
//        categoryList.add(foodCategory4);
//        categoryList.add(foodCategory5);
            foodCategory.saveFoodCategory(categoryList);

         /*   Debbuger.LogE("DataSupporFind:"+DataSupport.findAll(FoodCategory.class));
            Debbuger.LogE("DataSupporFind:"+DataSupport.findAll(FoodBean.class));
            Debbuger.LogE("DataSupporFind:"+DataSupport.findAll(FoodStore.class));*/
        }
    }
    public List<ScenicProject> LoadProjectBean() {
        List<ScenicProject> scenicProjects= new ArrayList<>();
        ScenicProject project = new ScenicProject();
        project.setScenic_id(1);
        project.setProjectName("游泳馆");
        project.setProjectImg(String.valueOf(R.drawable.project_youyongguan));
        project.setProjectID(1001);
        project.setProjectDescribe("夏日清凉游泳");
        project.setProjectPrice(String.valueOf(5));

        ScenicProject project2 = new ScenicProject();
        project2.setScenic_id(1);
        project2.setProjectName("体育馆");
        project2.setProjectImg(String.valueOf(R.drawable.project_tiyuguan));
        project2.setProjectID(1002);
        project2.setProjectDescribe("运动减肥圣地");
        project2.setProjectPrice(String.valueOf(3));
        scenicProjects.add(project);
        scenicProjects.add(project2);
        project.saveAllProject(scenicProjects);
        return scenicProjects;
    }
    public void LoadHotelBean() {
        List<HotelBean> hotelBeens = new ArrayList<>();
        HotelBean hotel = new HotelBean();
        hotel.setHotelID(0);
        hotel.setHotelName("大海度假酒店");
        hotel.setHotelImg(String.valueOf(R.drawable.hotel_danjian));
        hotel.setHotelLevel(5);
        hotel.setScenic_id(1);
        HotelBean hotel2 = new HotelBean();
        hotel.setHotelID(1);
        hotel2.setHotelName("小芳度假酒店");
        hotel.setHotelLevel(5);
        hotel2.setHotelImg(String.valueOf(R.drawable.hotel_danjian2));
        hotel2.setScenic_id(1);
        hotelBeens.add(hotel);
        hotelBeens.add(hotel2);
        hotel.saveAllHotel(hotelBeens);

    }
}
