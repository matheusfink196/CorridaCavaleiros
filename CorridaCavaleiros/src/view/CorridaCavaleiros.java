package view;

import java.util.concurrent.Semaphore;

import controller.Cavaleiro;

public class CorridaCavaleiros {
	public static void main(String[] args) {

		Semaphore semaforoTocha = new Semaphore(1);
		Semaphore semaforoPedra = new Semaphore(1);

		Cavaleiro cavaleiro1 = new Cavaleiro("Cavaleiro1", semaforoTocha, semaforoPedra);
		Cavaleiro cavaleiro2 = new Cavaleiro("Cavaleiro2", semaforoTocha, semaforoPedra);
		Cavaleiro cavaleiro3 = new Cavaleiro("Cavaleiro3", semaforoTocha, semaforoPedra);
		Cavaleiro cavaleiro4 = new Cavaleiro("Cavaleiro4", semaforoTocha, semaforoPedra);

		cavaleiro1.start();
		cavaleiro2.start();
		cavaleiro3.start();
		cavaleiro4.start();

		try {
			cavaleiro1.join();
			cavaleiro2.join();
			cavaleiro3.join();
			cavaleiro4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
