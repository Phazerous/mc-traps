package me.phazerous.testforhexelion.traps.commands;

import me.phazerous.testforhexelion.traps.TrapCreator;
import me.phazerous.testforhexelion.traps.enums.TrapType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CreateTrapCommand implements CommandExecutor {
    private final TrapCreator trapsCreator;

    public CreateTrapCommand(TrapCreator trapsCreator) {
        this.trapsCreator = trapsCreator;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can activate the command.");
        }

        Player player = (Player) commandSender;

        if (command.getName().equalsIgnoreCase("createtrap")) {
            if (strings.length == 1 && strings[0].equalsIgnoreCase("help")) {
                player.sendMessage("Usage: /createtrap <trapType> <seconds>");
                player.sendMessage("Trap types: slowness, fire, poison");
                return true;
            }

            if (strings.length == 2) {
                String trapTypeArg = strings[0];
                int seconds = Integer.parseInt(strings[1]);

                TrapType trapType = null;


                if (trapTypeArg.equalsIgnoreCase("slowness")) {
                    trapType = TrapType.SLOWNESS_TRAP;
                } else if (trapTypeArg.equalsIgnoreCase("poison")) {
                    trapType = TrapType.PoisonTrap;
                } else if (trapTypeArg.equalsIgnoreCase("fire")) {
                    trapType = TrapType.FireTrap;
                } else {
                    player.sendMessage("Invalid trap type.");
                    return true;
                }

                if (trapType != null) {
                    ItemStack trapItem = trapsCreator.createTrap(trapType, seconds);
                    player.getInventory().addItem(trapItem);
                }
            }


        }

        return true;
    }
}
