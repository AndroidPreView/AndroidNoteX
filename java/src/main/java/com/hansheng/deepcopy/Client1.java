package com.hansheng.deepcopy;

/**
 * Created by hansheng on 2016/7/9.
 * ǳ������ʹ��һ����֪ʵ�����´���ʵ���ĳ�Ա���������ֵ�������ʽ����Ϊǳ������
 * ǳ����ֻ����һ�����󣬴������ã����ܸ���ʵ��
 * <p/>
 * �������һ����Ŀ������췽��������Ҫ���ƶ�������з����ó�Ա����ֵ����ҪΪ�������͵ĳ�Ա���������µ�ʵ�������ҳ�ʼ��Ϊ��ʽ����ʵ��ֵ�������ʽ��Ϊ���
 * ����Զ����ڲ������þ����ƣ����Ǵ���һ���µ�ʵ�������Ҹ���ʵ����
 * 1����������
 * <p/>
 * ��������ǻ��������ͣ��򿽱���ֵ������ int��float �ȡ�
 * <p/>
 * 2������
 * <p/>
 * ���������һ��ʵ�������򿽱����ַ���ã�Ҳ����˵��ʱ�¶�����ԭ�������ǹ��ø�ʵ��������
 * <p/>
 * 3��String �ַ���
 * <p/>
 * ������Ϊ String �ַ������򿽱����ַ���á��������޸�ʱ��������ַ���������������һ���µ��ַ�����ԭ���ַ������󱣳ֲ��䡣
 */
public class Client1 {

    public static void getClone(){
        int x=2,y;
        y=x;
        x=1;
        System.out.println(y==x);

        String str1="hansheng",str2;
        str2=str1;
        str1="hanming";
        System.out.println(str1.equals(str2));

    }


    public static void main(String[] args) {
        getClone();

        //д���ʼ�
        Email email = new Email("��μӻ���", "�������12:30���������Ҳμӻ���...");

        Person person1 = new Person("����", email);

        Person person2 = person1.clone();
        person2.setName("����");
        Person person3 = person1.clone();
        person3.setName("����");

        person1.getEmail().setContent("�������12:00���������Ҳμӻ���...");

        System.out.println(person1.getName() + "���ʼ������ǣ�" + person1.getEmail().getContent());
        System.out.println(person2.getName() + "���ʼ������ǣ�" + person2.getEmail().getContent());
        System.out.println(person3.getName() + "���ʼ������ǣ�" + person3.getEmail().getContent());
    }
}