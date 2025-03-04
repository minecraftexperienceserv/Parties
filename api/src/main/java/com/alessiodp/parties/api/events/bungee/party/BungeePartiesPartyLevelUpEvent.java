package com.alessiodp.parties.api.events.bungee.party;

import com.alessiodp.parties.api.events.bungee.BungeePartiesEvent;
import com.alessiodp.parties.api.events.common.party.IPartyLevelUpEvent;
import com.alessiodp.parties.api.interfaces.Party;
import org.jetbrains.annotations.NotNull;

public class BungeePartiesPartyLevelUpEvent extends BungeePartiesEvent implements IPartyLevelUpEvent {
	private final Party party;
	private final int newLevel;
	
	public BungeePartiesPartyLevelUpEvent(Party party, int newLevel) {
		this.party = party;
		this.newLevel = newLevel;
	}
	
	@NotNull
	@Override
	public Party getParty() {
		return party;
	}
	
	@Override
	public int getNewLevel() {
		return newLevel;
	}
}
