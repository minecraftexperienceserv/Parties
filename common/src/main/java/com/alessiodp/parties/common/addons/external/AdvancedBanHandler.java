package com.alessiodp.parties.common.addons.external;

import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.parties.common.PartiesPlugin;
import com.alessiodp.parties.common.configuration.data.ConfigMain;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.leoko.advancedban.manager.PunishmentManager;
import me.leoko.advancedban.utils.Punishment;
import me.leoko.advancedban.utils.PunishmentType;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class AdvancedBanHandler {
	@NonNull protected final PartiesPlugin plugin;
	private static final String ADDON_NAME = "AdvancedBan";
	protected static boolean active;
	
	public void init() {
		active = false;
		if (ConfigMain.ADDITIONAL_MODERATION_ENABLE && ConfigMain.ADDITIONAL_MODERATION_PLUGINS_ADVANCEDBAN) {
			if (plugin.isPluginEnabled(ADDON_NAME)) {
				active = true;
				
				registerListener();
				
				plugin.getLoggerManager().log(String.format(Constants.DEBUG_ADDON_HOOKED, ADDON_NAME), true);
			} else {
				ConfigMain.ADDITIONAL_MODERATION_PLUGINS_ADVANCEDBAN = false;
				active = false;
				
				plugin.getLoggerManager().log(String.format(Constants.DEBUG_ADDON_FAILED, ADDON_NAME), true);
			}
		}
	}
	
	public static boolean isMuted(UUID uuid) {
		if (active) {
			return PunishmentManager.get().isMuted(uuid.toString());
		}
		return false;
	}
	
	public void onBan(Punishment punishment) {
		if (active && ConfigMain.ADDITIONAL_MODERATION_AUTOKICK) {
			if (punishment.getType() == PunishmentType.BAN || punishment.getType() == PunishmentType.IP_BAN) {
				try {
					UUID uuid = UUID.fromString(punishment.getUuid());
					plugin.getPartyManager().kickBannedPlayer(uuid);
				} catch (IllegalArgumentException ignored) {}
			}
		}
	}
	
	public abstract void registerListener();
}
