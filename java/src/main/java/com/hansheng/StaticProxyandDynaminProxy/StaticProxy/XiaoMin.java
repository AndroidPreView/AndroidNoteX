package com.hansheng.StaticProxyandDynaminProxy.StaticProxy;

/**
 * Created by hansheng on 2016/7/22.
 */
public class XiaoMin implements ILawsuit {
    @Override
    public void submit() {
        System.out.println("boss Ƿ���Ĺ���");
    }

    @Override
    public void burden() {
        System.out.println("���Ǻ�ͬ��");
    }

    @Override
    public void defend() {
        System.out.println("֤�ݳ��");
    }

    @Override
    public void finish() {
        System.out.println("�ɹ���������");
    }
}
