package com.vetor.test;

import java.io.IOException;

import com.vetor.view.FruitView;

public class Test {

	public static void main(String[] args) {
		FruitView fruitView = new FruitView();
		try {
			fruitView.fruitView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
