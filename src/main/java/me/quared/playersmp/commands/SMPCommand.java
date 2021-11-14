package me.quared.playersmp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static me.quared.playersmp.util.ConfigUtil.getConfigString;

public final class SMPCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = ((Player) sender);
            if (args.length == 0) {
                p.sendMessage(getConfigString("invalid-usage").replace("%usage%", "/" + label + " <args>"));
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("create")) {
                    if (p.hasPermission("playersmp.smp.create")) {
                        p.sendMessage(getConfigString("smp.creating-smp"));
                        return true;
                    } else {
                        p.sendMessage(getConfigString("no-permission"));
                    }
                } else if (args[0].equalsIgnoreCase("")) {

                } else {
                    p.sendMessage(getConfigString("invalid-usage").replace("%usage%", "/" + label + " <args>"));
                }
            } else {
                p.sendMessage(getConfigString("invalid-usage").replace("%usage%", "/" + label + " <args>"));
            }
        }
        return false;
    }

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completion = new ArrayList<>();
        if (args.length == 0) {
            completion.add("create");
        } else if (args.length == 1) {
            if ("create".startsWith(args[0])) completion.add("create");
        }
        return completion;
    }

}
