package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class XJPA {
	private static EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory("Lab7");
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public static void main(String[] args) {
		try {
			EntityManager em = XJPA.getEntityManager();
			System.out.println("finish");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("fail");
		}
	}
}
