package beSen.upc.service.impl;

import beSen.upc.service.I18nService;

/**
 * 模拟：外部系统的服务,外部系统部署到注册中心
 * 然后通过动态代理调用方法。（这里只是模拟动态代理，暂时没有模拟如何在注册中心找到接口实例）
 */
public class External18nService implements I18nService {
    @Override
    public String getLanguage() throws Exception{
        return "zh_CN";
    }
}
