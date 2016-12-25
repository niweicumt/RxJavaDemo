package org.study.rxJava;

import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by niwei on 2016/12/22.
 */
public class Demo {

    private Community[] communities;

    @Before
    public void setup() {
        communities = new Community[]{new Community(1, "abc"), new Community(2, "aaa"),
                new Community(3, "bbb"), new Community(4, "ccc"), new Community(5, "dddd")};
    }

    @Test
    public void test() {
        /**
         * 创建观察者Observer
         */
        Observer<Object> observer = new Observer<Object>() {
            public void onCompleted() {
                System.out.println("It's onCompleted.");
            }

            public void onError(Throwable throwable) {
                System.out.println("It's onError.");
            }

            public void onNext(Object o) {
                System.out.println("It's onNext.Passed in " + o);
            }
        };

        /**
         * 创建被观察者Observable
         */
        Observable observable = Observable.just("One", "Two", "Three");
        /**
         * 这行代码会依次调用
         * onNext("One");
         * onNext("Two");
         * onNext("Three");
         * onCompleted();
         */

        String[] parameters = {"One", "Two", "Three"};
        Observable observable2 = Observable.from(parameters);
        /**
         * 这行代码会依次调用
         * onNext("One");
         * onNext("Two");
         * onNext("Three");
         * onCompleted();
         */

        /**
         * 被观察者Observable订阅观察者Observer
         */
        observable.subscribe(observer);
        observable2.subscribe(observer);
    }

    @Test
    public void operator() {
        Observable.just(1, 2, 3, 4, 5).map(new Func1<Integer, String>() {
            public String call(Integer integer) {
                return "This is " + integer;
            }
        }).subscribe(new Action1<String>() {
            public void call(String s) {
                System.out.println(s);
            }
        });

        Observable.just(1, 2, 3, 4, 5).scan(new Func2<Integer, Integer, Integer>() {
            public Integer call(Integer integer, Integer integer2) {
                return integer * integer2;
            }
        }).subscribe(new Action1<Integer>() {
            public void call(Integer integer) {
                System.out.print(integer + " ");
            }
        });
    }

    @Test
    public void filter() {
        /**
         * filter 过滤观测序列中不想要的值
         */
        Observable.from(communities).filter(new Func1<Community, Boolean>() {
            public Boolean call(Community community) {
                return community.getId() > 2;
            }
        }).subscribe(new Action1<Community>() {
            public void call(Community community) {
                System.out.println(community.getName());
            }
        });

        System.out.println("===============");

        /**
         * take(n) 从原始的序列中取前n个元素
         */
        Observable.from(communities).take(2).subscribe(new Action1<Community>() {
            public void call(Community community) {
                System.out.println(community.getName());
            }
        });

        System.out.println("===============");

        /**
         * takeLast(n) 从原始的序列中取后n个元素
         */
        Observable.from(communities).takeLast(2).subscribe(new Action1<Community>() {
            public void call(Community community) {
                System.out.println(community.getName());
            }
        });

        System.out.println("===============");

        /**
         * skip(n) 从原始的序列中忽略前n个元素
         */
        Observable.from(communities).skip(2).subscribe(new Action1<Community>() {
            public void call(Community community) {
                System.out.println(community.getName());
            }
        });

        System.out.println("===============");

        /**
         * skip(n) 从原始的序列中忽略后n个元素
         */
        Observable.from(communities).skipLast(2).subscribe(new Action1<Community>() {
            public void call(Community community) {
                System.out.println(community.getName());
            }
        });

        System.out.println("===============");

        /**
         * elementAt(n) 从原始的序列中获取第n个元素
         */
        Observable.from(communities).elementAt(2).subscribe(new Action1<Community>() {
            public void call(Community community) {
                System.out.println(community.getName());
            }
        });

        System.out.println("===============");

        /**
         * distinct 只允许还没有发射过的数据通过
         */
        Observable.just(2, 1, 2, 2, 3, 4, 3, 4, 5, 5)
                .distinct()
                .subscribe(new Action1<Integer>() {
                    public void call(Integer i) {
                        System.out.println(i);
                    }
                });

        System.out.println("===============");

        /**
         * distinctUntilChanged 当前数据项和前一个数据项是否相同
         */
        Observable.just(2, 1, 2, 2, 3, 4, 3, 4, 5, 5)
                .distinctUntilChanged()
                .subscribe(new Action1<Integer>() {
                    public void call(Integer i) {
                        System.out.println(i);
                    }
                });

        System.out.println("===============");

        /**
         * first 从原始的序列中获取第一个数据项
         */
        Observable.from(communities).first().subscribe(new Action1<Community>() {
            public void call(Community community) {
                System.out.println(community.getName());
            }
        });

        System.out.println("===============");

        /**
         * last 从原始的序列中获取最后一个数据项
         */
        Observable.from(communities).last().subscribe(new Action1<Community>() {
            public void call(Community community) {
                System.out.println(community.getName());
            }
        });
    }
}
