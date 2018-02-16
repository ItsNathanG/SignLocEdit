package me.itsnathang.signloceditor.commands.util;

import me.rayzr522.jsonmessage.JSONMessage;
import org.bukkit.ChatColor;

import org.bukkit.entity.Player;

public class HelpScreen {
	
	public static void sendHelp(Player player) {
		JSONMessage msg = JSONMessage.create();

		msg.then("SignLoc Edit Quick Tutorial:").color(ChatColor.GREEN)
				.newline();

		msg.then("» ").color(ChatColor.GRAY)
				.then("Look at a sign.").color(ChatColor.GRAY)
				.newline();

		msg.then("» ").color(ChatColor.GRAY)
				.then("Use the command ").color(ChatColor.GRAY)
				.then("/sle setloc <name>").color(ChatColor.YELLOW)
				.newline();

		msg.then("» ").color(ChatColor.GRAY)
				.then("Edit the sign with ").color(ChatColor.GRAY)
				.then("/sle modify <name> <line #> <text>").color(ChatColor.YELLOW)
				.newline();

		msg.then("» ").color(ChatColor.GRAY)
				.then("Clear a line with ").color(ChatColor.GRAY)
				.then("/sle clear <name> <line #>").color(ChatColor.YELLOW)
				.newline();

		msg.then("» ").color(ChatColor.GRAY)
				.then("List all signs with ").color(ChatColor.GRAY)
				.then("/sle list").color(ChatColor.YELLOW);

		msg.send(player);
	}
}
