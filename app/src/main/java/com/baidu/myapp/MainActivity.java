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

import com.baidu.myapp.activity.BNaviGuideActivity;
import com.baidu.myapp.activity.BaseActivity;
import com.baidu.myapp.activity.FNmapActivity;
import com.baidu.myapp.activity.WNaviGuideActivity;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.bean.scenic.ScenicBean;
import com.baidu.myapp.bean.scenic.SpotBean;
import com.baidu.myapp.impl.foodimpl.FoodStoreIMPL;
import com.baidu.myapp.map.OverlayUtil;
import com.baidu.myapp.overlay.util.DrivingRouteOverlay;
import com.baidu.myapp.overlay.util.OverlayManager;
import com.baidu.myapp.overlay.util.WalkingRouteOverlay;
import com.baidu.myapp.search.RouteLineAdapter;
import com.baidu.myapp.util.CircleCrop;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.FileUtils;
import com.baidu.myapp.util.Guideutil;
import com.baidu.myapp.util.MyOrientationListener;
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
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteLine;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
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
import com.baidu.myapp.view.HeadView;
import com.bumptech.glide.Glide;
import com.fengmap.android.map.FMMap;
import com.fengmap.android.map.FMMapUpgradeInfo;
import com.fengmap.android.map.FMMapView;
import com.fengmap.android.map.event.OnFMMapInitListener;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.baidu.location.g.j.L;
import static com.baidu.location.g.j.O;
import static com.baidu.location.g.j.o;

public class MainActivity extends BaseActivity implements BaiduMap.OnMapClickListener,
        OnGetRoutePlanResultListener, OnFMMapInitListener {
    //
    private List<SpotBean> spotBeans = DataSupport.findAll(SpotBean.class);
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LatLng msLocation = new LatLng(24.416049, 111.519692);
    private LatLng meLocation = new LatLng(24.418206, 111.521324);
    //定位相关
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;
    private boolean isFirstIn = true;
    private Context mContext;
    private double mLaditude;
    private double mLongditude;
    private LatLng myLocation = new LatLng(24.416049, 111.519692);
    private BitmapDescriptor mIconLocation;
    private MyOrientationListener mMyOrientationListener;
    private float mCurrentX;
    private ImageView Img_htl01;
    //线路规划相关
// 浏览路线节点相关


    Button mBtnPre = null; // 上一个节点
    Button mBtnNext = null; // 下一个节
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

    // 搜索相关
    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用

    WalkingRouteResult nowResultwalk = null;
    BikingRouteResult nowResultbike = null;
    TransitRouteResult nowResultransit = null;
    DrivingRouteResult nowResultdrive = null;
    MassTransitRouteResult nowResultmass = null;

    int nowSearchType = -1; // 当前进行的检索，供判断浏览节点时结果使用。

    String startNodeStr = "姑婆山旅游区";
    String endNodeStr = "十八水原生态公园";
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


    private Marker mMarkerA;
    private Marker mMarkerB;

    private LatLng startPt, endPt;

    private BikeNavigateHelper mNaviHelper;
    private WalkNavigateHelper mWNaviHelper;
    BikeNaviLaunchParam param;
    WalkNaviLaunchParam walkParam;
    private static boolean isPermissionRequested = false;
    BitmapDescriptor bdA = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_marka);
    BitmapDescriptor bdB = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_markb);

    //导游相关
    int k = 0;
    int l = 0;
    int h = 0;
    /**
     * 贺州学院景区内景点坐标集合
     *
     * @param savedInstanceState
     */
    List<LatLng> spotLatLngs = new ArrayList<LatLng>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TEST

        FoodStore foodStore = new FoodStore();
        foodStore.setStoreName("星巴克");
        foodStore.setStoreID("1");
        foodStore.save();

















        requestPermission();
        this.mContext = this;
        //数据初始化
        mMapView = (MapView) findViewById(R.id.mmap);
        initView();
        initLocation();
        initMaker();
        initSpotData();
        MyLocation();
        //导航按钮
        NavGuideButtonClick();
        InitNav();
        InitFSView();

        //导航点击事件处理
        mBaiduMap.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng lng) {
                Toast.makeText(MainActivity.this, "设置目的地成功", Toast.LENGTH_LONG).show();
                endPt = lng;
                addDestInfoOverlay(endPt);
            }


        });
        //覆盖物点击事件
        CoverClick();
        //步行骑行

        //步行规划
        mBtnPre = (Button) findViewById(R.id.pre);
        mBtnNext = (Button) findViewById(R.id.next);
        mBtnPre.setVisibility(View.INVISIBLE);
        mBtnNext.setVisibility(View.INVISIBLE);
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
                        Intent intent = new Intent(MainActivity.this, FNmapActivity.class);
                        startActivity(intent);

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
        for (int i = 0; i < spotBeans.size(); i++) {

//            spotLatLngs.add(new LatLng(spotBeans.get(i).getLatitude(), spotBeans.get(i).getLongtitude()));
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
    public void searchProcess(View v) {
        // 重置浏览节点的路线数据


        route = null;
        mBtnPre.setVisibility(View.INVISIBLE);
        mBtnNext.setVisibility(View.INVISIBLE);
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

    }

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
            mBtnPre.setVisibility(View.VISIBLE);
            mBtnNext.setVisibility(View.VISIBLE);

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
                            overlay.zoomToSpan();
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
                overlay.zoomToSpan();

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
                overlay.zoomToSpan();
                mBtnPre.setVisibility(View.VISIBLE);
                mBtnNext.setVisibility(View.VISIBLE);
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
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
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
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }

    /**
     * 添加景区内景点覆盖物 //尽量取小一点的图标
     */
    public void addSpotOverly() {

        mBaiduMap.clear();
        Guideutil guideutil = new Guideutil();


        LatLng latLng = null;

        Marker marker = null;

        OverlayOptions options;

        //某类的坐标集合

        //   Log.i(TAG, "size:" + spotBeans.size());

        /*   Log.i(TAG, "spotLaLngs:"+spotLatLngs.get(1));*/


        // Log.i(TAG, "spotBeans: " + spotBeans.get(0).getSpotName());
        LatLng pt1 = null;
        LatLng pt2 = null;
        int i = 0;
        for (SpotBean spotBean : spotBeans) {
            //经纬度

//            latLng = new LatLng(spotBean.getLatitude(), spotBean.getLongtitude());

            //计算两点之间的距离
            ++i;
            Log.i(TAG, "i  is : " + i);

            if (i % 2 == 0) {
                pt2 = pt1;

            }
            pt1 = latLng;
            Log.d(TAG, "pt1:" + pt1);
            if (pt2 != null) {
                Double distance = DistanceUtil.getDistance(pt1, pt2);

                Log.i(TAG, "pt1 is :" + pt1);
                Log.i(TAG, "pt2 is :" + pt2);
                if (distance > 0) {
                    Log.i(TAG, "distance is: " + distance);
                }
            }

            //图标
            options = new MarkerOptions().position(latLng).icon(mDeliciousMarker).zIndex(13);
            marker = (Marker) mBaiduMap.addOverlay(options);
            Bundle arg0 = new Bundle();
            arg0.putSerializable("spotbeans", spotBean);
            marker.setExtraInfo(arg0);
        }


    }


    /**
     * 添加覆盖物
     */


    private void addDeOverlays() {
        mBaiduMap.clear();
        LatLng latLng = null;

        Marker marker = null;
        OverlayOptions options;
        List<ScenicBean> scenicBeans = DataSupport.findAll(ScenicBean.class);
        List<FoodStore> foodStoreBeans = DataSupport.findAll(FoodStore.class);
        for (FoodStore foodstores : foodStoreBeans) {
            //经纬度
//            latLng = new LatLng(foodstores.getLatitude(), foodstores.getLongtitude());
            //图标
            options = new MarkerOptions().position(latLng).icon(mDeliciousMarker).zIndex(5);
            marker = (Marker) mBaiduMap.addOverlay(options);
            Bundle arg0 = new Bundle();
            arg0.putSerializable("foodstore", foodstores);
            marker.setExtraInfo(arg0);
        }
        LatLng spcenter = latLng;//城市景区中心点
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(spcenter);
        mBaiduMap.setMapStatus(msu);
    }

    private void addOverlays() {
        OverlayUtil overlayUtil = new OverlayUtil();
        FoodStoreIMPL storeDAO = new FoodStoreIMPL();









       /* mBaiduMap.clear();
        LatLng latLng = null;

        Marker marker = null;

        OverlayOptions options;
        List<ScenicBean> scenicBeans = DataSupport.findAll(ScenicBean.class);


        for (ScenicBean ScenicBean : scenicBeans) {
            //经纬度
           *//* latLng = new LatLng(ScenicBean.getLatitude(), ScenicBean.getLongtitude());*//*
            //图标
            options = new MarkerOptions().position(latLng).icon(mMarker).zIndex(5);
            marker = (Marker) mBaiduMap.addOverlay(options);
            Bundle arg0 = new Bundle();
//            arg0.putSerializable("ScenicBean", ScenicBean);
            marker.setExtraInfo(arg0);
        }

*/
        //扩展按钮
        final TextView textView = (TextView) findViewById(R.id.sp_more);
        textView.setVisibility(View.VISIBLE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapStatusSize(11.0f);
                textView.setVisibility(View.GONE);
            }
        });

    }

    /**
     * 覆盖物点击事件处理
     */
    private void CoverClick() {
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {


                Bundle extraInfo = marker.getExtraInfo();

                final ScenicBean info = (ScenicBean) extraInfo.getSerializable("ScenicBean");
                if (info != null) {
                    /*Toast.makeText(MainActivity.this, info.getSpname(), Toast.LENGTH_SHORT).show();*/
                } else if ((FoodStore) extraInfo.getSerializable("foodstore") != null) {
                    final FoodStore foodStoreBean = (FoodStore) extraInfo.getSerializable("foodstore");
                    Toast.makeText(MainActivity.this, foodStoreBean.getStoreName(), Toast.LENGTH_SHORT).show();
                } else {
                    final SpotBean spotBean = (SpotBean) extraInfo.getSerializable("spotbeans");
//                  Toast.makeText(MainActivity.this, spotBean.getSpotName() + "", Toast.LENGTH_SHORT).show();
                }
          /*     final FoodStore foodStore = (FoodStore) extraInfo.getSerializable("foodstore");
                Toast.makeText(MainActivity.this, foodStore.getStoreName(), Toast.LENGTH_SHORT).show();
*/

                ImageView iv = (ImageView) mMarkerLy.findViewById(R.id.img_shape);
                TextView name = (TextView) mMarkerLy.findViewById(R.id.id_info_name);
                TextView distance = (TextView) mMarkerLy.findViewById(R.id.id_info_distance);
                TextView zan = (TextView) mMarkerLy.findViewById(R.id.id_info_zan);


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

        startPt = msLocation;
        endPt = meLocation;

        param = new BikeNaviLaunchParam().stPt(startPt).endPt(endPt);
        walkParam = new WalkNaviLaunchParam().stPt(startPt).endPt(endPt);
    }

    private void NavGuideButtonClick() {
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
    }

    public void spotClick(View view) {

        addOverlays();
        MapStatusSize(13.0f);
        LatLng spcenter = new LatLng(24.558307, 111.558717);//城市景区中心点111.558717,24.558307
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(spcenter);//
        mBaiduMap.setMapStatus(msu);
//        Toast.makeText(this, "a", Toast.LENGTH_SHORT).show();
    }

    public void cateClick(View view) {

        MapStatusSize(18.0f);
        addDeOverlays();
//        Toast.makeText(this, "b", Toast.LENGTH_SHORT).show();
    }

    public void hotelClick(View view) {
        Toast.makeText(this, "c", Toast.LENGTH_SHORT).show();
    }

    public void hotClick(View view) {
        h = 1;
        LatLng lng = new LatLng(24.582693, 111.559137);//111.559137,24.582693 十八水
        PlanNode myPlanNode = PlanNode.withLocation(myLocation);
        PlanNode shibashui = PlanNode.withLocation(lng);
        PlanNode guposhan = PlanNode.withLocation(new LatLng(24.641907, 111.566872));//111.566872,24.641907 姑婆山
        mSearch.walkingSearch(new WalkingRoutePlanOption().from(myPlanNode).to(shibashui));
        mSearch.walkingSearch(new WalkingRoutePlanOption().from(shibashui).to(guposhan));
        MapStatusSize(12.0f);//111.558199,24.51445
        LatLng spcenter = new LatLng(24.51445, 111.558199);//城市景区中心点111.558717,24.558307
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(spcenter);//
        mBaiduMap.setMapStatus(msu);
//        Toast.makeText(mContext, "d", Toast.LENGTH_SHORT).show();
    }

    public void downClick(View view) {
        LatLng lng = new LatLng(24.582693, 111.559137);//111.559137,24.582693 十八水
        PlanNode myPlanNode = PlanNode.withLocation(myLocation);
        PlanNode shibashui = PlanNode.withLocation(lng);
        PlanNode yushilin = PlanNode.withLocation(new LatLng(24.529364, 111.622378));//
        // 111.622378,24.529364 玉石林
        mSearch.walkingSearch(new WalkingRoutePlanOption().from(myPlanNode).to(shibashui));
        mSearch.walkingSearch(new WalkingRoutePlanOption().from(shibashui).to(yushilin));
        MapStatusSize(12.0f);//111.558199,24.51445
        LatLng spcenter = new LatLng(24.51445, 111.558199);//城市景区中心点111.558717,24.558307
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(spcenter);//
        mBaiduMap.setMapStatus(msu);

        Toast.makeText(mContext, "e", Toast.LENGTH_SHORT).show();
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
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        mMyOrientationListener.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        if (mSearch != null) {
            mSearch.destroy();
        }
        bdA.recycle();
        bdB.recycle();
    }


    //增加终点覆盖物

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

    //设置地图比例
    private void MapStatusSize(float size) {
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(size);//设置初始化大小
        mBaiduMap.setMapStatus(msu);
        Log.i(TAG, "MapStatus : " + mBaiduMap.getMapStatus().zoom);
    }

    private void initMaker() {
        mDeliciousMarker = BitmapDescriptorFactory.fromResource(R.drawable.ic_cate_red);
        mMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
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

                addSpotOverly();
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




    /*    *
     *定位到我的位置*/
    private void centerMylocation() {
        LatLng latLng = new LatLng(mLaditude, mLongditude);

        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(msu);
    }


    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
        /*   myLocation = new LatLng(location.getLatitude(),location.getLongitude());*/


            MyLocationData data = new MyLocationData.Builder()
                    .direction(mCurrentX)//builder模式初始化数据
                    .accuracy(location.getRadius())//
                    .latitude(24.416049)//
                    .longitude(111.519692)//
                    .build();
            mBaiduMap.setMyLocationData(data);
            mLaditude = 24.416049;
            mLongditude = 111.519692;


            MyLocationConfiguration config = new MyLocationConfiguration(//
                    MyLocationConfiguration
                            .LocationMode.NORMAL,
                    true, mIconLocation);
            mBaiduMap.setMyLocationConfiguration(config);
            if (isFirstIn) {

                LatLng latLng = new LatLng(mLaditude, mLongditude);
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(msu);
                isFirstIn = false;
                Toast.makeText(mContext, "贺州学院西校区", Toast.LENGTH_LONG).show();
            }
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
                    mBtnPre.setVisibility(View.VISIBLE);
                    mBtnNext.setVisibility(View.VISIBLE);
                    dismiss();
                    hasShownDialogue = false;
                }
            });
        }

        public void setOnItemInDlgClickLinster(MainActivity.OnItemInDlgClickListener itemListener) {
            onItemInDlgClickListener = itemListener;
        }

    }
}
