package com.baidu.myapp.bean.scenic.spot;

import com.baidu.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/2/18.
 */

public class GetSpotBean {
    public List<SpotBean> LoadLoacalSpotBean() {
        List<SpotBean> splist = new ArrayList<>();
        //贺州学院
        //1
        SpotBean sp = new SpotBean();
        sp.setSpotId(0);
        sp.setSpotName("第三图书馆");
        sp.setSpotLatitude(24.418365);
        sp.setScenic_id(1);
        sp.setSpotLongtitude(111.52151);
        //播报
        sp.setSpotDescribe("图书馆建立于1949年，共有2万3千多本书籍，其中有2000多本古代书籍，是每个热爱学习的同学的圣地，各项buff加成，学习事半功倍");
        sp.setNextVoiceSrc("穿过小树林，前往逸夫楼");
        sp.setSpotImg(String.valueOf(R.drawable.tushuguan_img));
        //2
        SpotBean sp2 = new SpotBean();
        sp2.setSpotId(1);
        sp2.setSpotName("图书馆人像");
        sp2.setSpotLatitude(24.417987);
        sp2.setScenic_id(1);
        sp2.setSpotLongtitude(111.521413);
        //播报
        sp2.setSpotDescribe("状元郎");
        sp2.setNextVoiceSrc("继续前进，前方图书馆");
        sp2.setSpotImg(String.valueOf(R.drawable.shixiang_img2));
        //3
        SpotBean sp3 = new SpotBean();
        sp3.setSpotId(2);
        sp3.setSpotName("逸夫楼");
        sp3.setSpotLatitude(24.4175);
        sp3.setScenic_id(1);
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
