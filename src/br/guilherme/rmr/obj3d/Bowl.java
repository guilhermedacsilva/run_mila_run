package br.guilherme.rmr.obj3d;

import java.util.Random;

import br.guilherme.rmr.util.Object3dUtil;

import com.threed.jpct.Object3D;
import com.threed.jpct.World;

public class Bowl extends BasicObject3d {
	private Object3D[] foodArray;

	public Bowl() {
		loadObject("bowl", 0.75f, "bowl.png");
		foodArray = new Object3D[12];
		createFood(0, -0.5f, -0.5f, -0.5f);
		createFood(1, -0.5f, -0.5f, 0.5f);
		createFood(2, 0.5f, -0.5f, -0.5f);
		createFood(3, 0.5f, -0.5f, 0.5f);
		
		createFood(4, 0f, -0.6f, -0.2f);
		createFood(5, 0f, -0.6f, 0.2f);
		createFood(6, -0.2f, -0.6f, 0f);
		createFood(7, 0.2f, -0.6f, 0f);

		createFood(8, -0.35f, -0.7f, -0.35f);
		createFood(9, -0.35f, -0.7f, 0.35f);
		createFood(10, 0.35f, -0.7f, -0.35f);
		createFood(11, 0.35f, -0.7f, 0.35f);
	}
	
	private void createFood(int index, float x, float y, float z) {
		Object3D food = Object3dUtil.load3ds("food");
		Object3dUtil.applyTexture(food, "bowl.png");
		
		obj.addChild(food);
		foodArray[index] = food;
		
		food.translate(x,y,z);
		Random random = new Random();
		food.rotateX(0.15f * (random.nextInt(6)-3));
		food.rotateY(0.15f * (random.nextInt(6)-3));
		food.rotateZ(0.15f * (random.nextInt(6)-3));
	}
	
	@Override
	public void addToWorld(World world) {
		super.addToWorld(world);
		for (Object3D food : foodArray) {
			world.addObject(food);
		}
	}
	
	@Override
	public void removeFromWorld(World world) {
		super.removeFromWorld(world);
		for (Object3D food : foodArray) {
			world.removeObject(food);
		}
	}
	
}
