package me.itsnathang.signloceditor;

import org.bukkit.plugin.java.JavaPlugin;

import me.itsnathang.signloceditor.commands.MainCommand;
import me.itsnathang.signloceditor.configs.ConfigSigns;

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
