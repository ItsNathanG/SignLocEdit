package com.thenathang.signloceditor;

import org.bukkit.plugin.java.JavaPlugin;

import com.thenathang.signloceditor.commands.MainCommand;
import com.thenathang.signloceditor.configs.ConfigSigns;

public class SignLocEdit extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getCommand("signlocedit").setExecutor(new MainCommand(this));
		
		new ConfigSigns(this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
