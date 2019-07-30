package com.java8.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 描述    :
 * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
 * (2) 交易员都在哪些不同的城市工作过？
 * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
 * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
 * (5) 有没有交易员是在米兰工作的？
 * (6) 打印生活在剑桥的交易员的所有交易额。
 * (7) 所有交易中，最高的交易额是多少？
 * (8) 找到交易额最小的交易。
 *
 * Author :Qing_X
 * Date   :2019-07-18 12:16
 */
public class LambdaTest {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        Trader QingX = new Trader("QingX","Beijing");
        List <Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(QingX, 2011, 500),
                new Transaction(raoul, 2012, 940),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        //(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> list1 = transactions.stream().filter(tran -> 2011==tran.getYear())
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println(list1);
        //(2) 交易员都在哪些不同的城市工作过？
        List<String> list2 =transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .distinct().collect(Collectors.toList());
            System.out.println(list2);
        //(3) 查找所有来自于剑桥的交易员，并按姓名排序。
        List<String> list3 =transactions.stream().filter(tran->"Cambridge".equals(tran.getTrader().getCity()))
                .map(tran->tran.getTrader().getName()).distinct().sorted(Comparator.comparing(String::toString).reversed())
                .collect(Collectors.toList());
        System.out.println(list3);
        //(4) 返回所有交易员的姓名字符串，按字母顺序排序。
        List<String> list4 = transactions.stream().map(tran->tran.getTrader().getName()).sorted(Comparator.comparing(String::toString))
                .distinct().collect(Collectors.toList());
        System.out.println(list4);
        //(5) 有没有交易员是在米兰工作的？
        Optional<Trader> optionalTrader =transactions.stream().map(tran->tran.getTrader())
                .filter(trader -> "Milan".equals(trader.getCity())).findAny();
        System.out.println(optionalTrader.isPresent());
        //(6) 打印生活在剑桥的交易员的所有交易额。
        Integer sum = transactions.stream().filter(tran->"Cambridge".equals(tran.getTrader().getCity()))
                .mapToInt(tran->tran.getValue()).sum();
        System.out.println(sum);
        //(7) 所有交易中，最高的交易额是多少？
//        Optional<Integer> optionalInteger =transactions.stream().map(tran->tran.getValue()).max(Integer::compareTo);
        Optional<Integer> optionalInteger =transactions.stream().map(tran->tran.getValue()).reduce(Integer::max);
        System.out.println(optionalInteger.get());
        //(8) 找到交易额最小的交易。
        Optional<Integer> optionalInteger1 = transactions.stream().map(tran->tran.getValue()).reduce(Integer::min);
        System.out.println(optionalInteger1.get());


    }

}

 class Trader{
    private final String name;
    private final String city;
    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }
    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }
    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}
 class Transaction{
    private final Trader trader;
    private final int year;
    private final int value;
    public Transaction(Trader trader, int year, int value){
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
    public Trader getTrader(){
        return this.trader;
    }
    public int getYear(){
        return this.year;
    }
    public int getValue(){
        return this.value;
    }
    public String toString(){
        return "{" + this.trader + ", " +
                "year: "+this.year+", " +
                "value:" + this.value +"}";
    }
}