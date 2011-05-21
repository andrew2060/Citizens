package com.fullwall.Citizens.NPCTypes.Questers.QuestTypes;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;

import com.fullwall.Citizens.NPCTypes.Questers.Quest;
import com.fullwall.Citizens.NPCTypes.Questers.QuestManager.QuestType;
import com.fullwall.resources.redecouverte.NPClib.HumanNPC;

public class HuntQuest extends Quest {

	private int kills = 0;
	private int amount;

	public HuntQuest(HumanNPC quester, Player player, int amount) {
		super(quester, player);
		this.amount = amount;
	}

	@Override
	public QuestType getType() {
		return QuestType.HUNT;
	}

	@Override
	public void updateProgress(Event event) {
		if (event instanceof EntityDeathEvent) {
			EntityDeathEvent ev = (EntityDeathEvent) event;
			if (ev.getEntity() instanceof Monster
					|| ev.getEntity() instanceof Creature) {
				kills += 1;
			}
			if (kills >= amount) {
				completed = true;
				super.updateProgress(event);
			}
		}
	}
}