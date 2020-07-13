package com.ckj.superlearn.superlearn.base;

import java.time.Duration;
import java.util.List;

import com.google.common.collect.Lists;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

/**
 * @author c.kj
 * @Description reactor getting Started | something that basic use about spring reactor
 * @Date 2020-07-10
 * @Time 11:03
 **/
public class ReactorStrategy {

    public static void main(String[] args) throws InterruptedException {

        String a = "Apple";
        String s = a.toUpperCase();
        String s1 = "hello" + s + "!";
        System.out.println(s1);

        Mono.just("Apple").map(String::toUpperCase).map(x -> "hello" + x + "!").subscribe(System.out::println);

        Flux<String> fruitFlux = Flux.just("Apple", "Orange");

        fruitFlux.subscribe(System.out::println);

        StepVerifier.create(fruitFlux).expectNext("Apple").expectNext("Orange").verifyComplete();

        List<String> list = Lists.newArrayList();
        list.add("Apple");
        list.add("Orange");
        Flux<String> stringFlux = Flux.fromIterable(list);

        stringFlux.subscribe(System.out::println);

        Flux<Long> take = Flux.interval(Duration.ofSeconds(1)).take(5);

        // 组合反应式类型

        Flux<String> fruitFluxA = Flux.just("Apple", "Orange").delayElements(Duration.ofMillis(10));

        Flux<String> fruitFluxB = Flux.just("Banana", "watermelon").delayElements(Duration.ofMillis(50));

        Flux<String> allFlux = fruitFluxA.mergeWith(fruitFluxB);

        allFlux.subscribe(x -> System.out.println("allFlux:" + x));

        Flux<Tuple2<String, String>> zip = Flux.zip(fruitFluxA, fruitFluxB);

        zip.subscribe(x -> System.out.println("zip:" + x));

        Thread.sleep(1000);

        fruitFluxA.subscribe(System.out::println);

        fruitFluxA.filter(x -> "Apple".equals(x)).subscribe(System.out::println);

        fruitFluxA.map(String::toUpperCase).subscribeOn(Schedulers.parallel()).subscribe(x -> {

        });

        Flux<String> fruitFluxAA = Flux.just("Apple", "Orange", "Banana", "watermelon", "Apple", "Orange", "Banana",
                "watermelon", "Apple", "Orange", "Banana", "watermelon", "Apple", "Orange", "Banana", "watermelon");

        fruitFluxAA.flatMap(Mono::just).map(String::toUpperCase).subscribeOn(Schedulers.parallel()).log().subscribe();

        Thread.sleep(1000);
    }
}
