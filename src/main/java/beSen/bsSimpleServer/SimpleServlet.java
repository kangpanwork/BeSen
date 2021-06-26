package beSen.bsSimpleServer;

import java.io.IOException;

/**
 * 装饰者模式
 * <p>
 * 装饰者模式拥有一个设计非常巧妙的结构，它可以动态添加对象功能。在基本的设计原则中，
 * 有一条重要的设计准则叫做合成/聚合复用原则。根据该原则的思想，代码复用应该尽可能使用委托，
 * 而不是使用继承。因为继承是一种紧密耦合，任何父类的改动都会影响其子类，不利于系统维护。
 * <p>
 * 而委托则是松散耦合，只要接口不变，委托类的改动并不会影响其上层对象。
 * 装饰者模式就充分运用了这种思想，通过委托机制，复用系统中的各个组件，
 * 在运行时，可以将这些功能组件进行叠加，从而构造一个超级对象，
 * 使其拥有所有这些组件的功能。而各个子功能模块，被很好地维护在各个组件的相关类中，
 * 拥有整洁的系统结构。
 * <p>
 * 装饰者模式可以有效分离组件，从而提示模块的可维护性并增加模块的复用性。
 * <p>
 * 1.核心组件
 */
public class SimpleServlet implements Servlet {

    public void doGet(SimpleRequest request, SimpleResponse response) {
        try {

            response.write("中文乱码" + request.getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void doPost(SimpleRequest request, SimpleResponse response) {
        try {
            response.write("中文乱码" + request.getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void service(SimpleRequest request, SimpleResponse response) {
        switch (request.getMethod()) {
            case "GET": {
                doGet(request, response);
                break;
            }
            case "POST": {
                doPost(request, response);
                break;
            }
        }
    }
}
