package APITest.SpeedAndSroreTest;

import java.util.List;
import java.util.Vector;

import APITest.AxeContent.RepositoryInfo_AXE;
import APITest.ConnectTest.ProjectRelatedFactory;

/**
 * @author 申彬
 *多线程爬数据的测试，果然还是一样慢
 */
public class MultiThreadTest {

	static ProjectRelatedFactory factory = new ProjectRelatedFactory();
	static Vector infosList = new Vector(9);

	public static void main(String[] args) {
		long beginTime = System.currentTimeMillis();
		new MultiThreadTest().multiThreadRead();
		System.out.println(infosList.size());
		long endTime = System.currentTimeMillis();
		System.out.println("delta time: " + (endTime - beginTime));
	}
	
	public void multiThreadRead() {

		for (int i = 1; i <= 65; i++) {
			// 开启一个
			new ReadPageThread(i).start();
		}

		synchronized (infosList) {
			while (infosList.size() < 65) {
				try {
					infosList.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static class ReadPageThread extends Thread {
		int page;

		public ReadPageThread(int page) {
			this.page = page;
		}

		public void run() {
			try {
				synchronized (infosList) {
					List<RepositoryInfo_AXE> infos = factory.getRepoByPage(page);
					infosList.add(infos);
					System.out.println("1 page done");
					infosList.notifyAll();	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
