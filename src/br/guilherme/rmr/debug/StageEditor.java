package br.guilherme.rmr.debug;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import br.guilherme.rmr.event.EventConst;
import br.guilherme.rmr.obj3d.BasicObject3d;
import br.guilherme.rmr.obj3d.Object3dConfig;

import com.threed.jpct.World;

public class StageEditor implements Observer, ActionListener {
	private static final String OBJ_CLASS_PACKAGE = "br.guilherme.rmr.obj3d.";
	private static final HashMap<String, String> CLASS_MAP = new HashMap<String, String>();
	private World world;
	private JFrame frame;
	private JTextArea text;
	private JButton button;
	private ArrayList<BasicObject3d> objList;
	
	static {
		CLASS_MAP.put("wa", OBJ_CLASS_PACKAGE+"Wall");
		CLASS_MAP.put("fe", OBJ_CLASS_PACKAGE+"Fence");
		CLASS_MAP.put("sp", OBJ_CLASS_PACKAGE+"Spike");
		CLASS_MAP.put("sl", OBJ_CLASS_PACKAGE+"Slider");
	}
	
	public StageEditor(World world) {
		this.world = world;
		objList = new ArrayList<BasicObject3d>(30);
	}
	
	private void showHide() {
		if (frame == null) createFrame();
		frame.setVisible(!frame.isVisible());
	}
	
	private void createFrame() {
		frame = new JFrame("Stage Editor");
		frame.setLayout(new BorderLayout());
		text = new JTextArea(20, 30);
		button = new JButton("Update");
		button.addActionListener(this);
		frame.add(text, BorderLayout.NORTH);
		frame.add(button, BorderLayout.SOUTH);
		frame.pack();
	}
	
	private void createObjects() {
		destroyOldObjects();
		String script = text.getText();
		String[] lines = script.split("\n");
		BasicObject3d obj;
		Object3dConfig config;
		String[] commands;
		for (String line : lines) {
			commands = line.replaceAll(" ", "").split(";");
			if (commands.length >= 3) {
				config = Object3dConfig.reuse(
						Float.parseFloat(commands[1]), 
						Float.parseFloat(commands[2])
						);
				if (commands.length > 3) config.rotation(Float.parseFloat(commands[3]));
				try {
					obj = ((BasicObject3d)
								Class.forName(CLASS_MAP.get(commands[0])).newInstance()).
							applyConfig(config);
					obj.addToWorld(world);
					obj.get3D().build();
					objList.add(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void destroyOldObjects() {
		for (BasicObject3d obj : objList) {
			obj.removeFromWorld(world);
			obj.destroy(world);
		}
		objList.clear();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.toString().equals(EventConst.DEBUG)) {
			showHide();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		createObjects();
	}

	public void shutdown() {
		if (frame != null) frame.dispose();
	}
	
}
