package com.thenathang.signloceditor.commands;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.thenathang.signloceditor.SignLocEdit;
import com.thenathang.signloceditor.commands.util.HelpScreen;
import com.thenathang.signloceditor.configs.ConfigSigns;

public class MainCommand implements CommandExecutor {
	static SignLocEdit plugin;
	
	public MainCommand(SignLocEdit plugin) {
		MainCommand.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("signlocedit"))
			return false;
		
		if (!sender.hasPermission("sle.admin")) {
			sender.sendMessage(colorMe("&cYou don't have permission for this!"));
			return true;
		}
		
		/*
		 * Help Command
		 * Description: Send help to player.
		 * Usage: /sle [help]
		 */
		if (args.length < 1 || args[0].equalsIgnoreCase("help"))
			HelpScreen.sendHelp((Player) sender);
		
		if (args.length > 0) {
			
			if (args[0].equalsIgnoreCase("setloc")) {
				if (args.length < 2) {
					sender.sendMessage(colorMe("&cUsage: /sle setloc <name>"));
					return true;
				}
				Player player = (Player) sender;
				Block target = player.getTargetBlock((Set<org.bukkit.Material>) null, 10);
				
				if (target == null || !(target.getState() instanceof Sign)) {
					sender.sendMessage(colorMe("&cYou must target a sign!"));
					return true;
				}
				
				ConfigSigns.saveLocation(args[1], target.getLocation());
				sender.sendMessage(colorMe("&aSign &e" + args[1] + " &aset to the sign you are looking at!"));
				return true;
			}
			
			if (args[0].equalsIgnoreCase("modify")) {
				if (args.length < 4) {
					sender.sendMessage(colorMe("&cUsage: /sle modify <sign> <line> <message>"));
					return true;
				}
				
				int line;
				
				try {	
					line = Integer.valueOf(Integer.parseInt(args[2]));
				} catch (Exception e) {
					sender.sendMessage(colorMe("&cCould not parse " + args[2] + " for the line number!"));
					return true;
				}
				
				if (line > 4 || line < 1) {
					sender.sendMessage(colorMe("&cCould not parse " + args[2] + " for the line number!"));
					return true;
				}
				
				Sign sign = (Sign) ConfigSigns.getLocation(args[1]).getBlock().getState();
				line = line - 1;
				
				String message = "";
				
				for (int i = 3; i < args.length; i++) {
					if (i == 3)
						message = args[i];
					else
						message = message + " " + args[i];
				}
				
				message = colorMe(message).replace("\\s", " ");
				
				if (message.length() > 16) {
					sender.sendMessage(colorMe("&cSpecified message is too long!"));
					return true;
				}
				
				sender.sendMessage(colorMe("&aSet line " + (line + 1) + " of sign " + args[1] + " to '" + message + "&a'!"));
				
				sign.setLine(line, message);
				sign.update();
				return true;
			}
			
			if (args[0].equalsIgnoreCase("list")) {
				Set<String> signsArray = ConfigSigns.getSigns();
				
				if (signsArray == null) {
					sender.sendMessage(colorMe("&cNo signs found!"));
					return true;
				}
				
				StringBuilder signs = new StringBuilder();

				for (String sign : signsArray) {
				    if (signs.length() != 0) {
				    	signs.append("&7, ");
				    }
				    
				    signs.append("&e" + sign);
				}
				
				signs.append("&7.");
				
				sender.sendMessage(colorMe("&aAll Signs: " + signs));
			}
			
		}
		
		
		return true;
	}
	
	public String colorMe(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
}
