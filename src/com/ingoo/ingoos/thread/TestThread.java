package com.ingoo.ingoos.thread;

public class TestThread {

	public void inlet() {
		new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();
    }

    private static class Thread1 implements Runnable{
        @Override
        public void run(){
            synchronized (TestThread.class) {
            System.out.println("enter thread1...");    
            System.out.println("thread1 is waiting...");
            try {
                //����wait()�������̻߳����������������ȴ��˶���ĵȴ�������
            	TestThread.class.wait();
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("thread1 is going on ....");
            System.out.println("thread1 is over!!!");
            }
        }
    }

    private static class Thread2 implements Runnable{
        @Override
        public void run(){
            synchronized (TestThread.class) {
                System.out.println("enter thread2....");
                System.out.println("thread2 is sleep....");
                //ֻ����Դ˶������notify()�������̲߳Ž������������׼����ȡ��������������״̬��
                TestThread.class.notify();
                //���TestD.class.notify()��ע�͵�����TestD.class������wait()����������û�е���notify()
                //���߳�1��Զ���ڹ���״̬��
                try {
                    //sleep()���������˳�����ִͣ��ָ����ʱ�䣬�ó�cpu�������̣߳�
                    //�������ļ��״̬��Ȼ�����ߣ���ָ����ʱ�䵽���ֻ��Զ��ָ�����״̬��
                    //�ڵ���sleep()�����Ĺ����У��̲߳����ͷŶ�������
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 is going on....");
                System.out.println("thread2 is over!!!");
            }
        }
    }

}
