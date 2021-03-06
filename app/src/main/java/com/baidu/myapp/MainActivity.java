package com.baidu.myapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.bikenavi.BikeNavigateHelper;
import com.baidu.mapapi.bikenavi.adapter.IBEngineInitListener;
import com.baidu.mapapi.bikenavi.adapter.IBRoutePlanListener;
import com.baidu.mapapi.bikenavi.model.BikeRoutePlanError;
import com.baidu.mapapi.bikenavi.params.BikeNaviLaunchParam;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteLine;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;
import com.baidu.myapp.activity.BNaviGuideActivity;
import com.baidu.myapp.activity.BaseActivity;
import com.baidu.myapp.activity.FoodStoreActivity;
import com.baidu.myapp.activity.WNaviGuideActivity;
import com.baidu.myapp.activity.scenic.ScenicActivity;
import com.baidu.myapp.adapter.CarAdapter;
import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.bean.scenic.ScenicBean;
import com.baidu.myapp.bean.scenic.spot.GetDataBean;
import com.baidu.myapp.bean.scenic.spot.SpotBean;
import com.baidu.myapp.map.OverlayUtil;
import com.baidu.myapp.overlay.util.DrivingRouteOverlay;
import com.baidu.myapp.overlay.util.OverlayManager;
import com.baidu.myapp.overlay.util.WalkingRouteOverlay;
import com.baidu.myapp.search.RouteLineAdapter;
import com.baidu.myapp.util.CircleCrop;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.FileUtils;
import com.baidu.myapp.util.GlideRoundTransform;
import com.baidu.myapp.util.Guideutil;
import com.baidu.myapp.util.MyOrientationListener;
import com.baidu.myapp.util.scenicguide.GuideScenic;
import com.baidu.myapp.util.scenicutil.ClearViewManger;
import com.baidu.myapp.util.scenicutil.PointMove;
import com.baidu.myapp.view.HeadView;
import com.bumptech.glide.Glide;
import com.fengmap.android.map.FMMap;
import com.fengmap.android.map.FMMapUpgradeInfo;
import com.fengmap.android.map.FMMapView;
import com.fengmap.android.map.event.OnFMMapInitListener;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.baidu.location.g.a.f;
import static com.baidu.location.g.j.P;
import static com.baidu.mapapi.map.BitmapDescriptorFactory.fromResource;

public class MainActivity extends BaseActivity implements BaiduMap.OnMapClickListener,
        OnGetRoutePlanResultListener, OnFMMapInitListener {
    //
    private List<SpotBean> spotBeans = DataSupport.findAll(SpotBean.class);
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    //定位相关
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;
    private boolean isFirstIn = true;
    private Context mContext;
    private BitmapDescriptor mIconLocation;
    private MyOrientationListener mMyOrientationListener;
    private float mCurrentX;
    //线路规划相关
// 浏览路线节点相关


    ImageView mBtnPre = null; // 上一个节点
    ImageView mBtnNext = null; // 下一个节
    int nodeIndex = -1; // 节点索引,供浏览节点时使用
    RouteLine route = null;
    List<RouteLine> routes = new ArrayList<RouteLine>();
    MassTransitRouteLine massroute = null;
    OverlayManager routeOverlay = null;
    boolean useDefaultIcon = false;
    private TextView popupText = null; // 泡泡view
    //蜂鸟3D地图进入按钮
    Button BtnIntoMap;
    // 地图相关，使用继承MapView的MyRouteMapView目的是重写touch事件实现泡泡处理
    // 如果不处理touch事件，则无需继承，直接使用MapView即可
    // 地图View

    //工具类


    // 搜索相关
    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用

    WalkingRouteResult nowResultwalk = null;
    DrivingRouteResult nowResultdrive = null;
    MassTransitRouteResult nowResultmass = null;

    int nowSearchType = -1; // 当前进行的检索，供判断浏览节点时结果使用。
    boolean hasShownDialogue = false;
    //蜂鸟地图
    private FMMap mMap;
    //覆盖相关物
/*    private List<ScenicBean> infos;
    private List<FoodStore> fs;*/
    private BitmapDescriptor mDeliciousMarker;
    private BitmapDescriptor mMarker;
    private RelativeLayout mMarkerLy;
    int i = 1;
    int zzv;
    //头像
    private ImageView hp;
    //导航相关

    private LatLng startPt, endPt;

    private BikeNavigateHelper mNaviHelper;
    private WalkNavigateHelper mWNaviHelper;
    BikeNaviLaunchParam param;
    WalkNaviLaunchParam walkParam;
    private static boolean isPermissionRequested = false;
    BitmapDescriptor bdA =
            fromResource(R.drawable.icon_marka);
    BitmapDescriptor bdB =
            fromResource(R.drawable.icon_markb);
    int iii = 5;
    //导游相关
    int k = 0;
    int l = 0;
    int h = 0;

    //贺州学院坐标
    LatLng gaotiezhan = new LatLng(24.506549,111.491311);

    /**
     * 贺州学院景区内景点坐标集合
     *
     * @param savedInstanceState
     */
    List<LatLng> spotLatLngs = new ArrayList<LatLng>();
    //工具类
    OverlayUtil overlayUtil;
    List<ScenicBean> scenicBeenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMapCustomFile(this, "custom_map_config.json");
        setContentView(R.layout.activity_main);
        requestPermission();
        mMapView = (MapView) findViewById(R.id.mmap);
        mMapView.showZoomControls(false);
        mBaiduMap = mMapView.getMap();
        overlayUtil = new OverlayUtil(mBaiduMap);
        //TEST
        requestPermission();
        this.mContext = this;
        //数据初始化
        initView();
        initLocationOption();
        initLocation();
        initMaker();
        initSpotData();
        InitNav();
        //导航按钮
//        NavGuideButtonClick();

        InitFSView();


     /*   //导航点击事件处理
        mBaiduMap.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng lng) {
                Toast.makeText(MainActivity.this, "设置目的地成功", Toast.LENGTH_LONG).show();
                endPt = lng;
                addDestInfoOverlay(endPt);
            }


        });*/
        //覆盖物点击事件
        CoverClick();
        //步行骑行

        //步行规划
   /*     mBtnPre = (Button) findViewById(R.id.pre);
        mBtnNext = (Button) findViewById(R.id.next);
        mBtnPre.setVisibility(View.INVISIBLE);
        mBtnNext.setVisibility(View.INVISIBLE);*/
        // 地图点击事件处理
        mBaiduMap.setOnMapClickListener(this);
        // 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);


        //攻略点击
        final LinearLayout left_click = (LinearLayout) findViewById(R.id.left_click_ly);
        final LinearLayout right_click = (LinearLayout) findViewById(R.id.right_click_ly);
        TextView gonglue_click = (TextView) findViewById(R.id.gonglue_click);
        gonglue_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (left_click.getVisibility() == View.GONE) {
                    left_click.setVisibility(View.VISIBLE);
                    right_click.setVisibility(View.VISIBLE);
                } else {
                    left_click.setVisibility(View.GONE);
                    right_click.setVisibility(View.GONE);
                }

            }


        });

        /**
         *
         *计算两点之间的距离
         */

//输出所有线路的节点按照添加顺序

//        个性化地图使用

        //获取地图控件引用

        //开启个性化地图
        mMapView.setMapCustomEnable(true);

//        主界面
        initScenic();

    }

//    初始化定位配置

    /**
     * 初始化定位参数配置
     */

    private void initLocationOption() {
//定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        LocationClient locationClient = new LocationClient(getApplicationContext());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
//注册监听函数
        locationClient.registerLocationListener(myLocationListener);
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//        locationOption.setLocationMode(LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("gcj02");
//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000);
//可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
//可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
//可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false);
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
//可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000, 1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//开始定位
        locationClient.start();
    }
//   个性化地图加载

    /**
     * 将个性化文件写入本地后调用MapView.setCustomMapStylePath加载
     *
     * @param context
     * @param fileName assets目录下自定义样式文件的文件名
     */
    private void setMapCustomFile(Context context, String fileName) {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        String moduleName = null;
        try {
            inputStream = context.getAssets().open("customConfigDir/" + fileName);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            moduleName = context.getFilesDir().getAbsolutePath();
            File file = new File(moduleName + "/" + fileName);
            if (file.exists()) file.delete();
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            //将自定义样式文件写入本地
            fileOutputStream.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//设置自定义样式文件
        mMapView.setCustomMapStylePath(moduleName + "/" + fileName);
    }

    //蜂鸟地图控件初始化
    private void InitFSView() {
        BtnIntoMap = (Button) findViewById(R.id.btn_intomap);
        new Thread(new Runnable() {
            @Override
            public void run() {
                BtnIntoMap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      /*  Intent intent = new Intent(MainActivity.this, FNmapActivity.class);
                        startActivity(intent);*/

                    }
                });
            }
        }).start();


    }

    /**
     * 加载地图数据
     */
    private void openMapByPath() {
        Debbuger.LogE("加载蜂鸟地图");
        FMMapView mapView = (FMMapView) findViewById(R.id.map_view);
        mMap = mapView.getFMMap();
        mMap.setOnFMMapInitListener(this);
        //加载离线数据
        String path = FileUtils.getDefaultMapPath(this);
        mMap.openMapByPath(path);
//         openMapById(id,true) 加载在线地图数据，并自动更新地图数据
//         mMap.openMapById(FileUtils.DEFAULT_MAP_ID,true);
    }

    /**
     * 景区内景点数据初始化
     */
    public void initSpotData() {
    }

    // 景区初始化
    private void initScenic() {

        scenicBeenList = new ArrayList<>();
        scenicBeenList = DataSupport.findAll(ScenicBean.class);
        if (scenicBeenList!= null&&!scenicBeenList.isEmpty()) {
            overlayUtil.addScenicAllOverly(scenicBeenList);
        }else {
            //
      /*  ScenicBean scenicBean1 = new ScenicBean();
        scenicBean1.setScenicName("姑婆山森林酒店");
        scenicBean1.setScenicDescribe("66666");
        scenicBean1.setScenicId(5);
        scenicBean1.setScenicImg("2222");
        scenicBean1.setScenicPrice(100);
        scenicBean1.setScenicOverlayImg(R.drawable.scenic_guposhan);
        scenicBean1.setScenicLongtitude(111.573763);
        scenicBean1.setScenicLatitude(24.598501);*/
            //
            ScenicBean scenicBean = new ScenicBean();
            scenicBean.setScenicName("姑婆山(推荐)");
            scenicBean.setScenicDescribe("4A景区，靓丽的山间云朵~");
            scenicBean.setScenicId(4);
            scenicBean.setScenicImg(String.valueOf(R.drawable.guposhan_head_bg));
            scenicBean.setTickets(0);
            scenicBean.setScenicPrice(100);
            scenicBean.setScenicOverlayImg(R.drawable.scenic_guposhanicon2);
            scenicBean.setScenicLongtitude(111.566872);
            scenicBean.setScenicLatitude(24.641907);
            //
            ScenicBean scenicBean2 = new ScenicBean();
            scenicBean2.setScenicName("十八水");
            scenicBean2.setScenicDescribe("飞流直下三千尺，清风迎面徐徐来~");
            scenicBean2.setScenicId(3);
            scenicBean2.setScenicImg(String.valueOf(R.drawable.shibashui_head_bg));
            scenicBean2.setScenicPrice(37);
            scenicBean2.setTickets(0);
            scenicBean2.setScenicOverlayImg(R.drawable.scenic_shibashui);
            scenicBean2.setScenicLongtitude(111.539137);
            scenicBean2.setScenicLatitude(24.582693);
            //
            ScenicBean scenicBean3 = new ScenicBean();
            scenicBean3.setScenicName("玉石林");
            scenicBean3.setScenicDescribe("愿君耳如玉，相逢石林间~");
            scenicBean3.setScenicId(2);
            scenicBean3.setTickets(0);
            scenicBean3.setScenicImg(String.valueOf(R.drawable.yushilin_head_bg));
            scenicBean3.setScenicPrice(56);
            scenicBean3.setScenicOverlayImg(R.drawable.scenic_yushilinicon);
            scenicBean3.setScenicLongtitude(111.622378);
            scenicBean3.setScenicLatitude(24.529364);
            //
            ScenicBean scenicBean4 = new ScenicBean();
            scenicBean4.setScenicName("贺州学院");
            scenicBean4.setScenicDescribe("二本院校，3A级景区");
            scenicBean4.setScenicId(1);
            scenicBean4.setScenicImg(String.valueOf(R.drawable.scenic_hezhouxueyuan_head));
            scenicBean4.setScenicPrice(0);
            scenicBean4.setTickets(0);
            scenicBean4.setScenicOverlayImg(R.drawable.scenic_hezhouxueyuan);
            scenicBean4.setScenicLongtitude(111.519692);
            scenicBean4.setScenicLatitude(24.416049);
            //
 /*       ScenicBean scenicBean5 = new ScenicBean();
        scenicBean5.setScenicName("正菱大酒店");
        scenicBean5.setScenicDescribe("66666");
        scenicBean5.setScenicId(1);
        scenicBean5.setScenicImg("2222");
        scenicBean5.setScenicPrice(100);
        scenicBean5.setScenicOverlayImg(R.drawable.scenic_guposhan);
        scenicBean5.setScenicLongtitude(111.52293);
        scenicBean5.setScenicLatitude(24.419844);*/
            //
            ScenicBean scenicBean6 = new ScenicBean();
            scenicBean6.setScenicName("黄姚古镇(热门)");
            scenicBean6.setScenicDescribe("4A景区，历史悠久环境优美~");
            scenicBean6.setScenicId(0);
            scenicBean6.setScenicImg(String.valueOf(R.drawable.huangyaoguzhen_head_bg));
            scenicBean6.setScenicPrice(80);
            scenicBean6.setTickets(0);
            scenicBean6.setScenicOverlayImg(R.drawable.scenic_huangyaoicon);
            scenicBean6.setScenicLongtitude(111.230985);
            scenicBean6.setScenicLatitude(24.262686);
            scenicBeenList.add(scenicBean);
//        scenicBeenList.add(scenicBean1);
            scenicBeenList.add(scenicBean2);
            scenicBeenList.add(scenicBean3);
            scenicBeenList.add(scenicBean4);
//        scenicBeenList.add(scenicBean5);
            scenicBeenList.add(scenicBean6);
            scenicBean.saveScenicBean(scenicBeenList);
            overlayUtil.addScenicAllOverly(scenicBeenList);

        }
    }

    public void nodeClick(View v) {
        //某条线路上的所有节点
        LatLng nodeLocation = null;
        String nodeTitle = null;
        Object step = null;

        if (nowSearchType != 0 && nowSearchType != -1) {
            // 非跨城综合交通
            if (route == null || route.getAllStep() == null) {
                return;
            }
            if (nodeIndex == -1 && v.getId() == R.id.pre) {
                return;
            }
            // 设置节点索引
            if (v.getId() == R.id.next) {
                if (nodeIndex < route.getAllStep().size() - 1) {
                    nodeIndex++;
                } else {
                    return;
                }
            } else if (v.getId() == R.id.pre) {
                if (nodeIndex > 0) {
                    nodeIndex--;
                } else {
                    return;
                }
            }
            // 获取节结果信息
            step = route.getAllStep().get(nodeIndex);
            //route是某一条线路,如果想要存放多条线路的节点该如何呢?
            Guideutil guideutil = new Guideutil();
            guideutil.GetAllNode(routes);


            if (step instanceof DrivingRouteLine.DrivingStep) {
                nodeLocation = ((DrivingRouteLine.DrivingStep) step).getEntrance().getLocation();
                nodeTitle = ((DrivingRouteLine.DrivingStep) step).getInstructions();

            } else if (step instanceof WalkingRouteLine.WalkingStep) {
                //单个节点获取数据的地方
                //step是抽象类，方便改变其类型
                nodeLocation = ((WalkingRouteLine.WalkingStep) step).getEntrance().getLocation();
                nodeTitle = ((WalkingRouteLine.WalkingStep) step).getInstructions();
            } else if (step instanceof TransitRouteLine.TransitStep) {
                nodeLocation = ((TransitRouteLine.TransitStep) step).getEntrance().getLocation();
                nodeTitle = ((TransitRouteLine.TransitStep) step).getInstructions();
            } else if (step instanceof BikingRouteLine.BikingStep) {
                nodeLocation = ((BikingRouteLine.BikingStep) step).getEntrance().getLocation();
                nodeTitle = ((BikingRouteLine.BikingStep) step).getInstructions();
            }
        } else if (nowSearchType == 0) {
            // 跨城综合交通  综合跨城公交的结果判断方式不一样


            if (massroute == null || massroute.getNewSteps() == null) {
                return;
            }
            if (nodeIndex == -1 && v.getId() == R.id.pre) {
                return;
            }
            boolean isSamecity = nowResultmass.getOrigin().getCityId() == nowResultmass.getDestination().getCityId();
            int size = 0;
            if (isSamecity) {
                size = massroute.getNewSteps().size();
            } else {
                for (int i = 0; i < massroute.getNewSteps().size(); i++) {
                    size += massroute.getNewSteps().get(i).size();
                }
            }

            // 设置节点索引
            if (v.getId() == R.id.next) {
                if (nodeIndex < size - 1) {
                    nodeIndex++;
                } else {
                    return;
                }
            } else if (v.getId() == R.id.pre) {
                if (nodeIndex > 0) {
                    nodeIndex--;
                } else {
                    return;
                }
            }
            if (isSamecity) {
                // 同城

                step = massroute.getNewSteps().get(nodeIndex).get(0);

            } else {
                // 跨城
                int num = 0;
                for (int j = 0; j < massroute.getNewSteps().size(); j++) {
                    num += massroute.getNewSteps().get(j).size();
                    if (nodeIndex - num < 0) {
                        int k = massroute.getNewSteps().get(j).size() + nodeIndex - num;
                        step = massroute.getNewSteps().get(j).get(k);

                        break;
                    }
                }
            }

            nodeLocation = ((MassTransitRouteLine.TransitStep) step).getStartLocation();
            nodeTitle = ((MassTransitRouteLine.TransitStep) step).getInstructions();

        }

        if (nodeLocation == null || nodeTitle == null) {
            return;
        }

        // 移动节点至中心
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(nodeLocation));
        // show popup
        popupText = new TextView(MainActivity.this);
        popupText.setBackgroundResource(R.drawable.popup);
        popupText.setTextColor(0xFF000000);
        popupText.setText(nodeTitle);
        mBaiduMap.showInfoWindow(new InfoWindow(popupText, nodeLocation, 0));

    }

    /**
     * 发起路线规划搜索示例
     *
     * @param v
     */
/*    public void searchProcess(View v) {
        // 重置浏览节点的路线数据


        route = null;
      *//*  mBtnPre.setVisibility(View.INVISIBLE);
        mBtnNext.setVisibility(View.INVISIBLE);*//*
        mBaiduMap.clear();
        // 处理搜索按钮响应
        // 设置起终点信息，对于tranist search 来说，城市名无意义
        PlanNode stNode = PlanNode.withLocation(myLocation);


        List<PlanNode> planNodes = new ArrayList<PlanNode>();
        for (int i = 0; i < spotLatLngs.size(); i++) {
            PlanNode Node = PlanNode.withLocation(spotLatLngs.get(i));
            planNodes.add(Node);
        }
        // 实际使用中请对起点终点城市进行正确的设定

        if (v.getId() == R.id.mass) {
            PlanNode stMassNode = PlanNode.withCityNameAndPlaceName("北京", "天安门");
            PlanNode enMassNode = PlanNode.withCityNameAndPlaceName("上海", "东方明珠");

            mSearch.masstransitSearch(new MassTransitRoutePlanOption().from(stMassNode).to(enMassNode));
            nowSearchType = 0;
        } else if (v.getId() == R.id.drive) {

            nowSearchType = 1;
        } else if (v.getId() == R.id.transit) {

            nowSearchType = 2;
        } else if (v.getId() == R.id.main_btn) {
            if (h == 0) Toast.makeText(mContext, "请选择攻略", Toast.LENGTH_SHORT).show();
            else if (h == 1) {
                h = 0;
                LatLng lng = new LatLng(24.582693, 111.559137);//111.559137,24.582693 十八水
                PlanNode myPlanNode = PlanNode.withLocation(myLocation);
                PlanNode shibashui = PlanNode.withLocation(lng);
                mSearch.walkingSearch(new WalkingRoutePlanOption().from(myPlanNode).to(shibashui));

            }
            nowSearchType = 3;
        } else if (v.getId() == R.id.bike) {

            nowSearchType = 4;
        }

    }*/

    /**
     * 步行线路规划
     */

    //我的位置
    public void MyLocation() {


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须经过同意才能打开权限", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    private void requestLocation() {
        mLocationClient.start();
    }
    //导游前准备,要知道现在自己所处的位置（是靠近还是位于旅游区）
    //如果不在旅游区则根据自己选择的攻略的以及位置给出最近到达旅游区的方案。
    //如果在旅游区则根据自己所在的位置一一进行讲解。
    //位于旅游区或靠近旅游区选择相应的数据进行初始化.

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult result) {
        //导游底层获取数据的地方
        k++;
        Log.i(TAG, "onGetWalkingRouteResult: 调用了多少次" + k);
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(MainActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            // result.getSuggestAddrInfo()
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("提示");
            builder.setMessage("检索地址有歧义，请重新设置。\n可通过getSuggestAddrInfo()接口获得建议查询信息");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {


            nodeIndex = -1;
      /*      mBtnPre.setVisibility(View.VISIBLE);
            mBtnNext.setVisibility(View.VISIBLE);*/

            if (result.getRouteLines().size() > 1) {
                nowResultwalk = result;

                if (!hasShownDialogue) {
                    MainActivity.MyTransitDlg myTransitDlg = new MainActivity.MyTransitDlg(MainActivity.this,
                            result.getRouteLines(),
                            RouteLineAdapter.Type.WALKING_ROUTE);
                    myTransitDlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            hasShownDialogue = false;
                        }
                    });
                    myTransitDlg.setOnItemInDlgClickLinster(new MainActivity.OnItemInDlgClickListener() {
                        public void onItemClick(int position) {
                            route = nowResultwalk.getRouteLines().get(position);
                            WalkingRouteOverlay overlay = new MainActivity.MyWalkingRouteOverlay(mBaiduMap);
                            mBaiduMap.setOnMarkerClickListener(overlay);
                            routeOverlay = overlay;
                            overlay.setData(nowResultwalk.getRouteLines().get(position));
                            //         Log.i(TAG, "onItemClick: a" +nowResultwalk.getRouteLines().get(position));
                            overlay.addToMap();
//                            overlay.zoomToSpan();
                        }

                    });
                    myTransitDlg.show();
                    hasShownDialogue = true;
                }
            } else if (result.getRouteLines().size() == 1) {
                // 直接显示
                Log.i(TAG, "步行规划获取线路的地方: ");
                route = result.getRouteLines().get(0);
                routes.add(route);
                Log.i(TAG, "多少条线路:" + routes.size());
                WalkingRouteOverlay overlay = new MainActivity.MyWalkingRouteOverlay(mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
                routeOverlay = overlay;
                overlay.setData(result.getRouteLines().get(0));

                overlay.addToMap();
//                overlay.zoomToSpan();

            } else {
                Log.d("route result", "结果数<0");
                return;
            }

        }

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult result) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult result) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(MainActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            // result.getSuggestAddrInfo()
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            nodeIndex = -1;


            if (result.getRouteLines().size() > 1) {
                nowResultdrive = result;
                if (!hasShownDialogue) {
                    MyTransitDlg myTransitDlg = new MyTransitDlg(MainActivity.this,
                            result.getRouteLines(),
                            RouteLineAdapter.Type.DRIVING_ROUTE);
                    myTransitDlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            hasShownDialogue = false;
                        }
                    });
                    myTransitDlg.setOnItemInDlgClickLinster(new MainActivity.OnItemInDlgClickListener() {
                        public void onItemClick(int position) {
                            route = nowResultdrive.getRouteLines().get(position);
                            DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
                            mBaiduMap.setOnMarkerClickListener(overlay);
                            routeOverlay = overlay;
                            overlay.setData(nowResultdrive.getRouteLines().get(position));
                            overlay.addToMap();
                            overlay.zoomToSpan();
                        }

                    });
                    myTransitDlg.show();
                    hasShownDialogue = true;
                }
            } else if (result.getRouteLines().size() == 1) {
                route = result.getRouteLines().get(0);
                DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
                routeOverlay = overlay;
                mBaiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
//                overlay.zoomToSpan();
         /*       mBtnPre.setVisibility(View.VISIBLE);
                mBtnNext.setVisibility(View.VISIBLE);*/
            } else {
                Log.d("route result", "结果数<0");
                return;
            }

        }
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult result) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult result) {

    }

    @Override
    public void onMapClick(LatLng lng) {

    }

    @Override
    public boolean onMapPoiClick(MapPoi poi) {
        return false;
    }

    @Override
    public void onMapInitSuccess(String s) {
        Debbuger.LogE("加载地图成功了");
    }

    @Override
    public void onMapInitFailure(String s, int i) {
        Debbuger.LogE("加载地图失败了");
    }

    @Override
    public boolean onUpgrade(FMMapUpgradeInfo fmMapUpgradeInfo) {
        return false;
    }


    private class MyWalkingRouteOverlay extends WalkingRouteOverlay {

        public MyWalkingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }

    // 定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }

    /**
     * 添加景区内景点覆盖物 //尽量取小一点的图标
     */
//    public void addSpotOverly() {
//
//        mBaiduMap.clear();
//
//
//        LatLng latLng = null;
//
//        Marker marker = null;
//
//        OverlayOptions options;
//
//        //某类的坐标集合
//
//        //   Log.i(TAG, "size:" + spotBeans.size());
//
//        /*   Log.i(TAG, "spotLaLngs:"+spotLatLngs.get(1));*/
//
//
//        // Log.i(TAG, "spotBeans: " + spotBeans.get(0).getSpotName());
//        LatLng pt1 = null;
//        LatLng pt2 = null;
//        int i = 0;
//        for (SpotBean spotBean : spotBeans) {
//            //经纬度
//
////            latLng = new LatLng(spotBean.getLatitude(), spotBean.getLongtitude());
//
//            //计算两点之间的距离
//            ++i;
//            Log.i(TAG, "i  is : " + i);
//
//            if (i % 2 == 0) {
//                pt2 = pt1;
//
//            }
//            pt1 = latLng;
//            Log.d(TAG, "pt1:" + pt1);
//            if (pt2 != null) {
//                Double distance = DistanceUtil.getDistance(pt1, pt2);
//
//                Log.i(TAG, "pt1 is :" + pt1);
//                Log.i(TAG, "pt2 is :" + pt2);
//                if (distance > 0) {
//                    Log.i(TAG, "distance is: " + distance);
//                }
//            }
//
//            //图标
//            options = new MarkerOptions().position(latLng).icon(mDeliciousMarker).zIndex(13);
//            marker = (Marker) mBaiduMap.addOverlay(options);
//            Bundle arg0 = new Bundle();
//            arg0.putSerializable("spotbeans", spotBean);
//            marker.setExtraInfo(arg0);
//        }
//
//
//    }




    /**
     * 覆盖物点击事件处理
     */
    //TODO 点击闪退
    private void CoverClick() {
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Debbuger.LogE("点击了Marker");
                Bundle extraInfo = marker.getExtraInfo();
                if (extraInfo == null) {//避免点击非主要标记物闪退
                    return false;
                }
                final FoodStore info = (FoodStore) extraInfo.getSerializable("FoodStore");
                final ScenicBean scenicinfo = (ScenicBean) extraInfo.getSerializable("ScenicBean");
                if ((FoodStore) extraInfo.getSerializable("FoodStore") != null) {
                    final FoodStore foodStoreBean = (FoodStore) extraInfo.getSerializable("FoodStore");
                    Intent intent = new Intent(MainActivity.this, FoodStoreActivity.class);
                    intent.putExtra("FoodStore", info);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, foodStoreBean.getStoreName(), Toast.LENGTH_SHORT).show();
                } else if (scenicinfo != null) {
                    Intent intent = new Intent(MainActivity.this, ScenicActivity.class);
                    intent.putExtra("Scenic", scenicinfo);
                    startActivity(intent);
                    Toast.makeText(mContext, scenicinfo.getScenicName(), Toast.LENGTH_SHORT).show();
                } else {
                    Debbuger.LogE("获取信息失败");
                }





          /*     final FoodStore foodStore = (FoodStore) extraInfo.getSerializable("foodstore");
                Toast.makeText(MainActivity.this, foodStore.getStoreName(), Toast.LENGTH_SHORT).show();
*/

  /*              ImageView iv = (ImageView) mMarkerLy.findViewById(R.id.img_shape);
                TextView name = (TextView) mMarkerLy.findViewById(R.id.id_info_name);
                TextView distance = (TextView) mMarkerLy.findViewById(R.id.id_info_distance);
                TextView zan = (TextView) mMarkerLy.findViewById(R.id.id_info_zan);
*/

/*
                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                        iv.setImageResource(info.getImgId());
                        distance.setText(info.getDistance());
                        name.setText(info.getSpname());
                        zan.setText(info.getZan() + "");
                        mMarkerLy.setVisibility(View.VISIBLE);*/
                return true;
            }
        });
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng lng) {
                mMarkerLy.setVisibility(View.GONE);
            }

            @Override
            public boolean onMapPoiClick(MapPoi poi) {
                return false;
            }
        });
    }

    private void InitNav() {
        try {
            mNaviHelper = BikeNavigateHelper.getInstance();
            mWNaviHelper = WalkNavigateHelper.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        startPt =  new LatLng(24.461166, 111.545928);
        endPt = new LatLng(24.416049, 111.519692);

        param = new BikeNaviLaunchParam().stPt(startPt).endPt(endPt);
        walkParam = new WalkNaviLaunchParam().stPt(startPt).endPt(endPt);
    }

/*    private void NavGuideButtonClick() {
        ImageView bikeclick = (ImageView) findViewById(R.id.img_click_bike);
        bikeclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBikeNavi();
            }
        });

        ImageView walkclick = (ImageView) findViewById(R.id.img_click_walk);
        walkclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWalkNavi();
            }
        });
    }*/

    //    点击景点后界面拉倒中心位置
    public void spotClick(View view) {
        Toast.makeText(mContext, "查看攻略景点", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "a", Toast.LENGTH_SHORT).show();
    }

    //   美食点击后
    public void cateClick(View view) {

       Debbuger.LogE("未预定有餐饮");
//        Toast.makeText(this, "b", Toast.LENGTH_SHORT).show();
    }

    public void hotelClick(View view) {
        Toast.makeText(this, "c", Toast.LENGTH_SHORT).show();
    }

    public void ChangeOnClick(View view) {
        if (h == 1) {
            ClearViewManger.getInstance().HideAllView();
            Toast.makeText(mContext, "退出攻略", Toast.LENGTH_SHORT).show();
            GoBackCenterScenic();
            h = 0;
        } else {
            Toast.makeText(mContext, "你还未选择攻略", Toast.LENGTH_SHORT).show();
        } 
    
    }
    public void hotClick(View view) {
        if (h != 0) {
            return;
        }

        mBtnPre.setVisibility(View.VISIBLE);
        mBtnNext.setVisibility(View.VISIBLE);
        Glide.with(MainActivity.this).load(R.drawable.dui)
                .centerCrop().transform(new GlideRoundTransform(this))
                .dontAnimate()
                .into(mBtnPre);
        Glide.with(MainActivity.this).load(R.drawable.cuo)
                .centerCrop().transform(new GlideRoundTransform(this))
                .dontAnimate()
                .into(mBtnNext);

        ClearViewManger.getInstance().addView(mBtnPre);
        ClearViewManger.getInstance().addView(mBtnNext);
        mBtnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearViewManger.getInstance().HideAllView();
                Toast.makeText(mContext, "开始前往首个目的地", Toast.LENGTH_SHORT).show();
                h = 1;
                l=0;
            }
        });
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearViewManger.getInstance().HideAllView();
                Toast.makeText(mContext, "已取消", Toast.LENGTH_SHORT).show();
                GoBackCenterScenic();
                h = 0;
            }
        });
        mBaiduMap.setMaxAndMinZoomLevel(12.8f, 12.8f);
        LatLng lng = new LatLng(24.582693, 111.559137);//111.559137,24.582693 十八水
        LatLng hezhouxueyuan = new LatLng(24.416049, 111.519692);//111.559137,24.582693 贺州学院
        LatLng busStation = new LatLng(24.420826, 111.585593);//111.559137,24.582693 八步中山站
        LatLng yushilin = new LatLng(24.529364, 111.622378);//111.559137,24.582693 八步中山站
        LatLng guposhans = new LatLng(24.641907, 111.566872);//111.559137,24.582693 八步中山站
        PlanNode myPlanNode = PlanNode.withLocation(hezhouxueyuan);
        PlanNode bus = PlanNode.withLocation(busStation);
        PlanNode shibashui = PlanNode.withLocation(lng);
        PlanNode yushi = PlanNode.withLocation(yushilin);
        PlanNode guposhan = PlanNode.withLocation(guposhans);//111.566872,24.641907 姑婆山
        mSearch.walkingSearch(new WalkingRoutePlanOption().from(myPlanNode).to(bus));
        mSearch.walkingSearch(new WalkingRoutePlanOption().from(bus).to(guposhan));
        mSearch.walkingSearch(new WalkingRoutePlanOption().from(guposhan).to(shibashui));
        mSearch.walkingSearch(new WalkingRoutePlanOption().from(shibashui).to(yushi));
        overlayUtil.addOverlyByLatLng(busStation, R.drawable.scenic_bus2, "八步三中站");
        overlayUtil.addTextByLatLng(SubLatLng(busStation), "坐22路通往姑婆山");
        overlayUtil.addTextByLatLng(UpLatLng(UpLatLng(hezhouxueyuan)), "首日9点出发");
        overlayUtil.addTextByLatLng(SubLatLng(UpLatLng(UpLatLng(hezhouxueyuan))), "学院东门到三中站（约23分钟）");
        overlayUtil.addTextByLatLng(SubLatLng(SubLatLng(UpLatLng(UpLatLng(hezhouxueyuan)))), "坐6路公交:￥2/人");
        overlayUtil.addTextByLatLng(SubLatLng(SubLatLng(busStation)), "费用:￥20/人");
        overlayUtil.addTextByLatLng(SubLatLng(SubLatLng(SubLatLng(busStation))), "时长:约2小时");



        overlayUtil.addTextByLatLng(LeftLatLng(lng),"次日(10点）到达十八水");
        overlayUtil.addTextByLatLng(SubLatLng(LeftLatLng(lng)),"10点-12点智能导游");
        overlayUtil.addTextByLatLng(SubLatLng(SubLatLng(LeftLatLng(lng))),"12点-13点就餐");
        overlayUtil.addTextByLatLng(SubLatLng(SubLatLng(SubLatLng(LeftLatLng(lng)))),"自助餐:￥50/人");
        overlayUtil.addTextByLatLng(SubLatLng(SubLatLng(SubLatLng(SubLatLng(LeftLatLng(lng))))),"13点-15点自由活动");


        overlayUtil.addTextByLatLng(RightLatLng(guposhans),"首日(12点）到达姑婆山");
        overlayUtil.addTextByLatLng(SubLatLng(RightLatLng(guposhans)),"12点-14点山脚智能导游");
        overlayUtil.addTextByLatLng(SubLatLng(SubLatLng(RightLatLng(guposhans))),"14点-19点登山搭帐篷");
        overlayUtil.addTextByLatLng(SubLatLng(SubLatLng(SubLatLng(RightLatLng(guposhans)))),"烧烤:￥58/人");
        overlayUtil.addTextByLatLng(SubLatLng(SubLatLng(SubLatLng(SubLatLng(RightLatLng(guposhans))))),"观看日落日出");

        overlayUtil.addTextByLatLng(SubLatLng(yushilin),"次日(16点)到达玉石林");
        overlayUtil.addTextByLatLng(SubLatLng(SubLatLng(yushilin)),"观看玉石");
        overlayUtil.addTextByLatLng(SubLatLng(SubLatLng(SubLatLng(yushilin))),"旅行结束返程");
        LatLng spcenter = new LatLng(24.528307, 111.558717);///城市景区中心点111.558717,24.558307
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(spcenter);//
        mBaiduMap.animateMapStatus(msu,500);
        MapStatusSize(12.8f);//111.558199,24.51445


//        Toast.makeText(mContext, "d", Toast.LENGTH_SHORT).show();
    }

    public void downClick(View view) {
     /*   LatLng lng = new LatLng(24.582693, 111.559137);//111.559137,24.582693 十八水
        PlanNode myPlanNode = PlanNode.withLocation(myLocation);
        PlanNode shibashui = PlanNode.withLocation(lng);
        PlanNode yushilin = PlanNode.withLocation(new LatLng(24.529364, 111.622378));//
        // 111.622378,24.529364 玉石林
        mSearch.walkingSearch(new WalkingRoutePlanOption().from(myPlanNode).to(shibashui));
        mSearch.walkingSearch(new WalkingRoutePlanOption().from(shibashui).to(yushilin));
        MapStatusSize(12.0f);//111.558199,24.51445
//        LatLng spcenter = new LatLng(24.546137, 111.576111);//城市景区中心点111.558717,24.558307
//        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(spcenter);//
//        mBaiduMap.setMapStatus(msu);*/

        Toast.makeText(mContext, "暂不支持", Toast.LENGTH_SHORT).show();
    }

    //百度引入的导航功能
    private void startBikeNavi() {
        Log.d("View", "startBikeNavi");
        try {
            mNaviHelper.initNaviEngine(this, new IBEngineInitListener() {
                @Override
                public void engineInitSuccess() {
                    Log.d("View", "engineInitSuccess");
                    routePlanWithParam();
                }

                @Override
                public void engineInitFail() {
                    Log.d("View", "engineInitFail");
                }
            });
        } catch (Exception e) {
            Log.d("Exception", "startBikeNavi");
            e.printStackTrace();
        }
    }

    private void startWalkNavi() {
        Log.d("View", "startBikeNavi");
        try {
            mWNaviHelper.initNaviEngine(this, new IWEngineInitListener() {
                @Override
                public void engineInitSuccess() {
                    Log.d("View", "engineInitSuccess");
                    routePlanWithWalkParam();
                }

                @Override
                public void engineInitFail() {
                    Log.d("View", "engineInitFail");
                }
            });
        } catch (Exception e) {
            Log.d("Exception", "startBikeNavi");
            e.printStackTrace();
        }
    }

    private void routePlanWithParam() {
        mNaviHelper.routePlanWithParams(param, new IBRoutePlanListener() {
            @Override
            public void onRoutePlanStart() {
                Log.d("View", "onRoutePlanStart");
            }

            @Override
            public void onRoutePlanSuccess() {
                Log.d("View", "onRoutePlanSuccess");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, BNaviGuideActivity.class);
                startActivity(intent);
            }

            @Override
            public void onRoutePlanFail(BikeRoutePlanError error) {
                Log.d("View", "onRoutePlanFail");
            }

        });
    }

    private void routePlanWithWalkParam() {
        mWNaviHelper.routePlanWithParams(walkParam, new IWRoutePlanListener() {
            @Override
            public void onRoutePlanStart() {
                Log.d("View", "onRoutePlanStart");
            }

            @Override
            public void onRoutePlanSuccess() {
                Log.d("View", "onRoutePlanSuccess");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, WNaviGuideActivity.class);
                startActivity(intent);
            }

            @Override
            public void onRoutePlanFail(WalkRoutePlanError error) {
                Log.d("View", "onRoutePlanFail");
            }

        });
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionRequested) {

            isPermissionRequested = true;

            ArrayList<String> permissions = new ArrayList<>();
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }

            if (permissions.size() == 0) {
                return;
            } else {
                requestPermissions(permissions.toArray(new String[permissions.size()]), 0);
            }
        }
    }
//

    @Override
    protected void onResume() {
        super.onResume();
        Debbuger.LogE("zzzonResume");
        mMapView.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }
        mMyOrientationListener.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Debbuger.LogE("zzzStop");
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        mMyOrientationListener.stop();
    }

    @Override
    protected void onPause() {

        super.onPause();
        Debbuger.LogE("zzzonPause");
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debbuger.LogE("zzzDestroy");
        // mMapView.onDestroy();
        if (mSearch != null) {
            mSearch.destroy();
        }
        bdA.recycle();
        bdB.recycle();
    }
    /*   //增加终点覆盖物

       private void addDestInfoOverlay(LatLng destInfo) {
           mBaiduMap.clear();
           OverlayOptions options = new MarkerOptions().position(destInfo)//
                   .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_en)).zIndex(5);
           param.stPt(startPt).endPt(destInfo);
           walkParam.stPt(startPt).endPt(destInfo);
           LinearLayout typeoftraffc = (LinearLayout) findViewById(R.id.type_of_traffc);
           typeoftraffc.setVisibility(View.VISIBLE);
           mBaiduMap.addOverlay(options);
       }
   */
    //设置地图比例
    private void MapStatusSize(float size) {
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(size);//设置初始化大小
        mBaiduMap.setMapStatus(msu);
        Log.i(TAG, "MapStatus : " + mBaiduMap.getMapStatus().zoom);
    }

    private void initMaker() {
        mDeliciousMarker = fromResource(R.drawable.ic_cate_red);
        mMarker = fromResource(R.drawable.icon_marka);
        mMarkerLy = (RelativeLayout) findViewById(R.id.id_map_ly);
    }

    private void initLocation() {
        mLocationClient = new LocationClient(this);
        mLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mLocationListener);

        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);

        mIconLocation = BitmapDescriptorFactory//
                .fromResource(R.mipmap.arrow);
        mMyOrientationListener = new MyOrientationListener(mContext);
        mMyOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mCurrentX = x;
            }
        });
    }

    private void initView() {

        //初始化地图
        mMapView.removeViewAt(1);
        mBaiduMap = mMapView.getMap();//获取地图实例对象
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(18.0f);//设置初始化大小
        mBaiduMap.setMapStatus(msu);
        //实现地图和卫星地图的切换
        //添加图片

        mBtnNext = (ImageView) findViewById(R.id.next);
        mBtnPre = (ImageView) findViewById(R.id.pre);
        hp = (ImageView) findViewById(R.id.head_portrait); //给头像替换图片
        hp.setOnCreateContextMenuListener(this);
        hp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.showContextMenu();
            }
        });
        HeadView headView = new HeadView(this);
        headView.init(this);
        Glide.with(this).load(R.drawable.hp_01).transform(new CircleCrop(this)).into(hp);
        //

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        MenuItemSwich(item);
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return true;
    }

    private void MenuItemSwich(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_map_common:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.id_map_site:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.id_map_traffic:
                if (mBaiduMap.isTrafficEnabled()) {
                    mBaiduMap.setTrafficEnabled(false);
                    item.setTitle("实时交通(off)");

                } else {
                    mBaiduMap.setTrafficEnabled(true);
                    item.setTitle("实时交通(on)");

                }
                break;
            case R.id.id_map_location:
                centerMylocation();
                break;
            case R.id.id_map_overlays:
                //覆盖物点击事件处理
                MapStatusSize(11.8f);


                break;
            case R.id.into_databases:


                break;
            default:
                break;

        }
    }

    /**
     * 添加美食店覆盖物
     */


//     定位到我的位置 高铁站
    private void centerMylocation() {
        LatLng latLng = new LatLng(24.461298, 111.542766);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(msu);
    }

    private double lati = gaotiezhan.latitude;
    private double longi = gaotiezhan.longitude;
    //位置监听包括第一次初始化的位置 TODO 位置监听,MyLocationDate就是定位图标的位置设置
    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (h == 1) {


            }

            MyLocationData data = new MyLocationData.Builder()
                    .direction(mCurrentX)//builder模式初始化数据
                    .accuracy(location.getRadius())//
                    .latitude(lati)//实际是以接收到的location为参数值
                    .longitude(longi)//实际是以接收到的location为参数值，这样就会图标根据位置的移动而在地图上移动。
                    .build();
            mBaiduMap.setMyLocationData(data);


//            mLaditude = 24.416049;
//            mLongditude = 111.519692;


            MyLocationConfiguration config = new MyLocationConfiguration(//
                    MyLocationConfiguration
                            .LocationMode.NORMAL,
                    true, mIconLocation);
            mBaiduMap.setMyLocationConfiguration(config);
            if (isFirstIn) {
                GoBackCenterScenic();
                isFirstIn = false;
                Toast.makeText(mContext, "您目前正在贺州市", Toast.LENGTH_LONG).show();
            }

            PointMove p = new PointMove(new LatLng(24.418836,111.522042), new LatLng(lati,longi));
            if (p.Move() != null&&h==1) {
                lati = p.Move().latitude;
                longi = p.Move().longitude;
                Debbuger.LogE("Moving lati:" + lati);
                Debbuger.LogE("Moving longi:"+longi);
            } else if (l == 0&&h==1) {
                l=1;
                mBaiduMap.clear();
                mBaiduMap.setMaxAndMinZoomLevel(17.8f, 17.0f);
                MapStatusSize(18.0f);
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(new LatLng(24.416049,111.519692));
                mBaiduMap.animateMapStatus(msu,600);
                GetDataBean getDataBean = new GetDataBean();
                getDataBean.LoadLoacalSpotBean();
                List<SpotBean> spots = DataSupport.where("scenic_id=?", "1").find(SpotBean.class);
                overlayUtil.addSpotOverlay(spots);
                Toast.makeText(mContext, "你已到达贺州学院", Toast.LENGTH_SHORT).show();
                LatLng p1 = new LatLng(lati, longi);
                LatLng p2 = new LatLng(spots.get(0).getSpotLatitude(), spots.get(0).getSpotLongtitude());
                LatLng p3 = new LatLng(spots.get(1).getSpotLatitude(), spots.get(1).getSpotLongtitude());
                PlanNode first = PlanNode.withLocation(p1);
                PlanNode second = PlanNode.withLocation(p3);

                mSearch.walkingSearch(new WalkingRoutePlanOption().from(first).to(second));
                return;
            }

          /*  //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null){
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);*/
        }
    }

    // 响应DLg中的List item 点击
    interface OnItemInDlgClickListener {
        public void onItemClick(int position);
    }

    // 供路线选择的Dialog
    public class MyTransitDlg extends Dialog {

        private List<? extends RouteLine> mtransitRouteLines;
        private ListView transitRouteList;
        private RouteLineAdapter mTransitAdapter;

        MainActivity.OnItemInDlgClickListener onItemInDlgClickListener;

        public MyTransitDlg(Context context, int theme) {
            super(context, theme);
        }

        public MyTransitDlg(Context context, List<? extends RouteLine> transitRouteLines, RouteLineAdapter.Type
                type) {
            this(context, 0);
            mtransitRouteLines = transitRouteLines;
            mTransitAdapter = new RouteLineAdapter(context, mtransitRouteLines, type);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        @Override
        public void setOnDismissListener(OnDismissListener listener) {
            super.setOnDismissListener(listener);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_transit_dialog);

            transitRouteList = (ListView) findViewById(R.id.transitList);
            transitRouteList.setAdapter(mTransitAdapter);

            transitRouteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    onItemInDlgClickListener.onItemClick(position);
           /*         mBtnPre.setVisibility(View.VISIBLE);
                    mBtnNext.setVisibility(View.VISIBLE);*/
                    dismiss();
                    hasShownDialogue = false;
                }
            });
        }

        public void setOnItemInDlgClickLinster(MainActivity.OnItemInDlgClickListener itemListener) {
            onItemInDlgClickListener = itemListener;
        }

    }


    //返回景区中心点
    public void GoBackCenterScenic() {
        mBaiduMap.clear();
        overlayUtil.addScenicAllOverly(scenicBeenList);
        LatLng latLng = new LatLng(24.420853, 111.422715);
        mBaiduMap.setMaxAndMinZoomLevel(11.7f, 11.7f);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(msu,500);
        MapStatusSize(11.7f);
    }

    //控制文本间距,应该根据地图的缩放比例的监听来控制这个，牵连太多，暂时还没时间弄
    public LatLng UpLatLng(LatLng latLng) {
        double latitude = latLng.latitude + 0.02;
        double longtitude = latLng.longitude;
        LatLng latLng1 = new LatLng(latitude, longtitude);
        return latLng1;
    }

    public LatLng SubLatLng(LatLng latLng) {
        double latitude = latLng.latitude - 0.007;
        double longtitude = latLng.longitude;
        LatLng latLng1 = new LatLng(latitude, longtitude);
        return latLng1;
    }

    public LatLng LeftLatLng(LatLng latLng) {
        double latitude = latLng.latitude + 0.021;
        double longtitude = latLng.longitude - 0.06;
        LatLng latLng1 = new LatLng(latitude, longtitude);
        return latLng1;
    }
    public LatLng RightLatLng(LatLng latLng) {
        double latitude = latLng.latitude + 0.021;
        double longtitude = latLng.longitude + 0.05;
        LatLng latLng1 = new LatLng(latitude, longtitude);
        return latLng1;
    }
}
