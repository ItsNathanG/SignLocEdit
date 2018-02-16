package com.thenathang.signloceditor.configs;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import com.thenathang.signloceditor.SignLocEdit;

public class ConfigSigns {
	public static YamlConfiguration signs;
	private static SignLocEdit plugin;
	static File signsFile;
	
	public ConfigSigns(SignLocEdit p) {
		ConfigSigns.plugin = p;
		
		loadSigns();
	}
	
	public void loadSigns() {
		signsFile = new File(plugin.getDataFolder() + File.separator + "signs.yml");
		
        if(!signsFile.exists())
        	plugin.saveResource("signs.yml", true);
        
        signs = YamlConfiguration.loadConfiguration(signsFile);
	}
	
	public static void saveSigns() {
		try {
			signs.save(signsFile);
	    } catch (IOException ex) {
	        plugin.getLogger().log(Level.SEVERE, "Could not save config to " + signsFile, ex);
	    }
	}
	
	public static void saveLocation(String sign, Location location) {
		signs.set("signs." + sign + ".location", 
				location.getWorld().getName() + ", " +  location.getX() + ", " + location.getY() + ", " + location.getZ());
		
		saveSigns();
	}
	
	public static Location getLocation(String sign) {
        String[] locationSplit = signs.getString("signs." + sign + ".location").split(", ");
        
        return new Location(Bukkit.getWorld(locationSplit[0]), Double.parseDouble(locationSplit[1]), Double.parseDouble(locationSplit[2]), Double.parseDouble(locationSplit[3]));
	}
	
	public static Set<String> getSigns() {
		if (signs.isSet("signs"))
			return signs.getConfigurationSection("signs").getKeys(false);
		else
			return null;
	}
	
	

}
