package controller;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Cavaleiro extends Thread {
	private String nome;
	private int velocidade;
	private int posicao;
	private boolean temTocha;
	private static final int corredor = 2000;
	private static final int posicaoTocha = 500;
	private static final int posicaoPedra = 1500;
	private Semaphore semaforoTocha;
	private Semaphore semaforoPedra;
	@SuppressWarnings("unused")
	private Random random;

	public Cavaleiro(String nome, Semaphore semaforoTocha, Semaphore semaforoPedra) {
		this.nome = nome;
		this.temTocha = false;
		this.velocidade = (2 + new Random().nextInt(3));
		this.posicao = 0;
		this.semaforoTocha = semaforoTocha;
		this.semaforoPedra = semaforoPedra;
		this.random = new Random();
	}

	public void run() {
		while (posicao < corredor) {
			try {
				Thread.sleep(50);
				posicao = posicao + velocidade;

				if (posicao >= posicaoTocha && posicao < posicaoPedra) {
					if (semaforoTocha.tryAcquire()) {
						System.out.println(nome + " pegou a tocha");
						velocidade = velocidade + 2;
						temTocha = true;

					}
				}
				if (posicao >= posicaoPedra && !temTocha) {
					if (semaforoPedra.tryAcquire()) {
						System.out.println(nome + " pegou a pedra");
						velocidade = velocidade + 2;
					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(nome + " chegou ao final");

		boolean[] portas = new boolean[4];
		int portaSaida = random.nextInt(4);
		portas[portaSaida] = true;

		int escolha = random.nextInt(4);
		while(portas[escolha]) {
		}
		
		if (escolha == portaSaida) {
			System.out.println(nome + " encontrou a saida");
		} else {
			System.out.println(nome + " foi devorado");
			}

		
	}
}