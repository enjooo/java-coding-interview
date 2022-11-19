package com.vftour.study.oop.designpattern.pattern.command;

import java.util.List;

/**
 * Invoker
 *
 * @version 1.0
 * @author: 东东 | d@tke.store
 * @date :  2022/10/26 11:28 下午
 */
public class Invoker {
    private List<ICommand> commands;

    public Invoker(List<ICommand> command) {
        super();
        this.commands = command;
    }

    public void call() {
        for (ICommand command : commands) {
            command.execute();
        }
    }
}
