package com.vftour.study.oop.designpattern.pattern.command;

import com.google.common.collect.Lists;

/**
 * Client
 *
 * @version 1.0
 * @author: 东东 | d@tke.store
 * @date :  2022/10/26 11:29 下午
 */
public class Client {

    public static void main(String[] args) {
        ICommand command = new ConcreteCommand(new Receiver());

        Invoker invoker = new Invoker(Lists.newArrayList(command));

        invoker.call();
    }
}
