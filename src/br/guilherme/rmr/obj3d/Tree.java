package br.guilherme.rmr.obj3d;

public class Tree extends BasicObject3d {

	public Tree() {
		loadObject("tree", 2, "tree.png");
		obj.translate(0, -2.5f, 20);
	}
	
}
