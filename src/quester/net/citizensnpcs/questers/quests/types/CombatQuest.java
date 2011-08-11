package net.citizensnpcs.questers.quests.types;

import net.citizensnpcs.questers.quests.QuestIncrementer;
import net.citizensnpcs.questers.quests.Objectives.ObjectiveCycler;
import net.citizensnpcs.resources.npclib.HumanNPC;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;
import org.bukkit.event.entity.EntityDeathEvent;

public class CombatQuest extends QuestIncrementer {
	public CombatQuest(HumanNPC npc, Player player, String questName,
			ObjectiveCycler objectives) {
		super(npc, player, questName, objectives);
	}

	@Override
	public void updateProgress(Event event) {
		if (event instanceof EntityDeathEvent) {
			EntityDeathEvent ev = (EntityDeathEvent) event;
			if (ev.getEntity() instanceof Player) {
				this.getProgress().incrementCompleted(1);
			}
		}
	}

	@Override
	public boolean isCompleted() {
		return this.getProgress().getAmount() >= this.objective.getAmount();
	}

	@Override
	public Type[] getEventTypes() {
		return new Type[] { Type.ENTITY_DEATH };
	}
}