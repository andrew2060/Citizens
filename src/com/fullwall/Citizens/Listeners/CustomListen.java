package com.fullwall.Citizens.Listeners;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;

import com.fullwall.Citizens.Citizens;
import com.fullwall.Citizens.Events.CitizensBasicNPCEvent;
import com.fullwall.Citizens.NPCs.NPCManager;
import com.fullwall.Citizens.Utils.PropertyPool;

public class CustomListen extends CustomEventListener {
	private Citizens plugin;
	private PluginManager pm;

	public CustomListen(Citizens plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * Register custom events
	 */
	public void registerEvents() {
		pm = plugin.getServer().getPluginManager();
		pm.registerEvent(Event.Type.CUSTOM_EVENT, this, Event.Priority.Normal, plugin);
	}
	
	@Override
	public void onCustomEvent(Event ev) {
		if (!ev.getType().equals(Type.CUSTOM_EVENT)) {
			return;
		}
		if (ev.getEventName().equals("CitizensBasicNPCEvent")) {
			CitizensBasicNPCEvent e = (CitizensBasicNPCEvent) ev;
			if (e.isCancelled() == true)
				return;
			if (!PropertyPool.getLookWhenClose(e.getNPC().getUID())) {
				NPCManager.rotateNPCToPlayer(e.getNPC(), e.getPlayer());
			}
			if (!e.getText().isEmpty())
				e.getPlayer().sendMessage(e.getText());
		}
	}
}
