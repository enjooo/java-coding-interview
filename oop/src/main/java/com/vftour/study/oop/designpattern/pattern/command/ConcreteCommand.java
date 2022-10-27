package com.vftour.study.oop.designpattern.pattern.command;

/**
 * ConcreteCommand
 *
 * @version 1.0
 * @author: 东东 | d@tke.store
 * @date :  2022/10/26 11:27 下午
 */
public class ConcreteCommand implements ICommand {

    private Receiver receiver;	//命令的真正的执行者

    public ConcreteCommand(Receiver receiver) {
        super();
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //命令真正执行前或后，执行相关的处理！
        receiver.action();
    }
}
