package me.itsnathang.signloceditor.commands.util;

import org.bukkit.ChatColor;

import org.bukkit.entity.Player;

public class HelpScreen {
	
	public static void sendHelp(Player player) {
		String[] helpMessages = {"&2&lSignLoc Edit &8Help", 
								 " &aGetting Started:",
								 "   &eLook at a sign.", 
								 "   &eDo /sle setloc <name>",
								 " &aNow you can edit it anywhere with:",
								 "   &e/sle modify <name> <line #> <text>",
								 " &aOr clear a line with:",
								 "   &e/sle clear <name> <line #>",
								 " &aOr list all signs with:",
								 "   &e/sle list"};
		
		for (String message : helpMessages) {
			player.sendMessage(colorMe(message));
		}
	}
	
	public static String colorMe(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
}
