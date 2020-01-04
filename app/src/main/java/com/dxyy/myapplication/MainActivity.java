package com.dxyy.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private NavigationView mNavigation;
    private CircleImageView mHeader;
    private FloatingActionButton mFab;
    private SwipeRefreshLayout mSwipeRefresh;
    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;
    private Fruit[] fruits = {new Fruit("Apple", R.drawable.apple), new Fruit("Banana", R.drawable.banana),
            new Fruit("Orange", R.drawable.orange), new Fruit("Watermelon", R.drawable.watermelon),
            new Fruit("Pear", R.drawable.pear), new Fruit("Grape", R.drawable.grape),
            new Fruit("Pineapple", R.drawable.pineapple), new Fruit("Strawberry", R.drawable.strawberry),
            new Fruit("Cherry", R.drawable.cherry), new Fruit("Mango", R.drawable.mango)};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                try {
                    if(!e.isDisposed()){
                        for (int i = 0;i<10;i++){
                            e.onNext(i);
                        }
                        e.onComplete();
                    }
                }catch (Exception e1){
                    e.onError(e1);
                }
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("onnext:"+integer);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("error:"+throwable);

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("complete");
            }
        });


        Observable.just("hello world")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);

                    }
                });

        Observable.just(1,2,3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer s) throws Exception {
                        System.out.println(s);

                    }
                });

        Observable.fromArray(1,2,3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer s) throws Exception {
                        System.out.println("fromArray"+s);

                    }
                });

        List<Integer> list = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list.add(i);
        }
        Observable.fromIterable(list).repeat(10).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("fromIterable"+integer);

            }
        });

        Observable.range(0,9).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                return Observable.timer(10, TimeUnit.SECONDS);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });

        long start = System.currentTimeMillis();
        Observable.interval(500,TimeUnit.MILLISECONDS)
                .take(5)
                .repeatUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Exception {
                        return System.currentTimeMillis()-start >5000;
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println(aLong);

            }
        });

        Observable observable = Observable.defer(new Callable<ObservableSource<?>>() {
            @Override
            public ObservableSource<?> call() throws Exception {
                System.out.println("emit hello");
                return Observable.just("hello");
            }
        });

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        observable.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });

        Observable.interval(3,TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong);
                    }
                });
        Observable.timer(3,TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong);
                    }
                });

        Disposable disposable = Observable.just("HELLO ")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s.toLowerCase();
                    }
                }).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return s+"world";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });

        map 一对一转换
        flatmap 一对一或者一对多转换

        User.Address address1 = new User.Address("南京路","上海");
        User.Address address2 = new User.Address("坡子街","长沙");
        List<User.Address> addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);
        User user = new User("张三",addressList);

        Observable.just(user)
                .flatMap(new Function<User, ObservableSource<User.Address>>() {
                    @Override
                    public ObservableSource<User.Address> apply(User user) throws Exception {
                        return Observable.fromIterable(user.addresses);
                    }
                }).map(new Function<User.Address, String>() {
            @Override
            public String apply(User.Address address) throws Exception {
                return address.city+address.street;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });

        Observable.range(1,8)
                .groupBy(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return integer%2 ==0?"偶数":"奇数";
                    }
                }).subscribe(new Consumer<GroupedObservable<String, Integer>>() {
            @Override
            public void accept(GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Exception {

                if( stringIntegerGroupedObservable.getKey().equals("奇数")){
                    stringIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                            System.out.println(integer);
                        }
                    });

                }
            }
        });

        Observable.range(1,8)
                .buffer(2,2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        System.out.println(integers);
                    }
                });

        Observable.range(1,8)
                .first(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });

        Observable.interval(1, TimeUnit.SECONDS)
                .skipLast(3,TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong);
                    }
                });

        User.Address address1 = new User.Address("南京路","上海");
        User.Address address2 = new User.Address("坡子街","长沙");
        User.Address address3 = new User.Address("坡子街","长沙");

        Observable.just(address1,address2,address3)
                .distinct(new Function<User.Address, String>() {
                    @Override
                    public String apply(User.Address address) throws Exception {
                        return address.city;
                    }
                }).subscribe(new Consumer<User.Address>() {
            @Override
            public void accept(User.Address address) throws Exception {
                System.out.println(address.city+address.street);
            }
        });
        User.Address address1 = new User.Address("南京路","上海");
        User.Address address2 = new User.Address("坡子街","长沙");
        User.Address address3 = new User.Address("坡子街","长沙");
        Observable.just(address1,address2,address3)
                .filter(new Predicate<User.Address>() {
                    @Override
                    public boolean test(User.Address address) throws Exception {
                        return address.city.equals("长沙");
                    }
                }).subscribe(new Consumer<User.Address>() {
            @Override
            public void accept(User.Address address) throws Exception {
                System.out.println(address.city+address.street);
            }
        });

        Observable.just(1,2,3,4,5,6,7,8,9)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer<9;
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println(aBoolean);
            }
        });
        Observable.just(1,2,3,4,5,6,7,8,9)
                .contains(2).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println(aBoolean);
            }
        });


        Observable.ambArray(Observable.just(1,2,3).delay(1,TimeUnit.SECONDS),Observable.just(4,5,6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });

        Observable.empty().defaultIfEmpty(8)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println(o);
                    }
                });

        Observable.empty().switchIfEmpty(Observable.just(1,2,3)).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
        Observable.sequenceEqual(Observable.just(1,2,3),Observable.just(1,2)).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                System.out.println(aBoolean);
            }
        });

        Observable.intervalRange(1,9,0,1,TimeUnit.MILLISECONDS)
                .skipUntil(Observable.timer(3,TimeUnit.MILLISECONDS))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong);

                    }
                });

        Observable.just(1,2,3,4,5)
                .skipWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer<3;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });

        Observable.merge(Observable.just(1,2,3),Observable.just(4,5,6,7))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });
        Observable.zip(Observable.just(1, 2, 3), Observable.just(4, 5, 6, 7), new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) throws Exception {

                return integer+""+integer2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
        Observable.combineLatest( Observable.just(4, 5, 6, 7),Observable.just(1, 2, 3), new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) throws Exception {

                return integer+""+integer2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });


        Observable.just(1,2,3).join(Observable.just(4, 5, 6), new Function<Integer, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(Integer integer) throws Exception {
                return Observable.timer(3000, TimeUnit.MILLISECONDS);
            }
        }, new Function<Integer, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(Integer integer) throws Exception {
                return Observable.timer(3000, TimeUnit.MILLISECONDS);
            }
        }, new BiFunction<Integer, Integer, Object>() {
            @Override
            public String apply(Integer integer, Integer integer2) throws Exception {
                return integer+""+integer2;
            }
        });

        Observable.just(1,2,3)
                .startWith(0)
                .concatWith(Observable.just(4))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Observable<Long> observable = Observable.interval(1,TimeUnit.SECONDS).take(6);
        ConnectableObservable<Long> connectableObservable = observable.publish();
        connectableObservable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("onnext:"+aLong+"  time:"+sdf.format(new Date()));
            }
        });
        connectableObservable.delaySubscription(3,TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("onnext:"+aLong+"  time:"+sdf.format(new Date()));
            }
        });

        connectableObservable.connect();

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int i = 0; i<1000; i++) {
                    e.onNext(i);
                }
                e.onComplete();
            }
        }, BackpressureStrategy.DROP)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i("num",integer+"");
                    }
                });

                Observable.just(1,2,3)
                        .compose(transformer())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                System.out.println(s);
                            }
                        });



        private ObservableTransformer schedulersTransformer = new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };


        public <T> ObservableTransformer<T,T> applySchedulers(){
            return (ObservableTransformer<T,T>)schedulersTransformer;
        }



        public ObservableTransformer<Integer,String> transformer(){
            return new ObservableTransformer<Integer, String>() {
                @Override
                public ObservableSource<String> apply(Observable<Integer> upstream) {
                    return upstream.map(new Function<Integer, String>() {
                        @Override
                        public String apply(Integer integer) throws Exception {
                            return integer.toString();
                        }
                    });
                }
            };

        }
       Flowable.range(1,100)
               .parallel()
               .runOn(Schedulers.io()) //等同于ObserveOn
               .map(new Function<Integer, String>() {
                   @Override
                   public String apply(Integer integer) throws Exception {
                       return integer.toString();
                   }
               }) .sequential()
               .subscribe(new Consumer<String>() {
           @Override
           public void accept(String s) throws Exception {
               System.out.println(s);
           }
       });

        Observable.interval(1,TimeUnit.SECONDS).throttleFirst(2,TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long integer) throws Exception {
                        System.out.println(integer);
                    }
                });

         */
        mToolbar = findViewById(R.id.toolbar);
        mDrawer = findViewById(R.id.drawer);
        mNavigation = findViewById(R.id.navigation);
        mFab = findViewById(R.id.fab);
        mSwipeRefresh= findViewById(R.id.swipe_refresh);
        //要先获取header 再根据header布局获取头像控件
        mHeader = mNavigation.getHeaderView(0).findViewById(R.id.icon_image);
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open,R.string.close);
        toggle.syncState();
        mDrawer.addDrawerListener(toggle);
        mDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                Toast.makeText(MainActivity.this,"open",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                Toast.makeText(MainActivity.this,"close",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        //设置显示返回按钮
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置返回按钮可点击
      //  getSupportActionBar().setHomeButtonEnabled(true);
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_call:
                        Toast.makeText(MainActivity.this,"call",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        mHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"header",Toast.LENGTH_SHORT).show();
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"任务已完成",Snackbar.LENGTH_SHORT).setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_SHORT).show();

                    }
                }).show();
            }
        });

        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(this,"backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"settings",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                Toast.makeText(this,"finish",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;

    }
}
