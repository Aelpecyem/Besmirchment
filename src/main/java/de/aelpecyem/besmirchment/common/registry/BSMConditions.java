package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.Besmirchment;
import io.github.ytg1234.recipeconditions.api.RecipeConds;
import io.github.ytg1234.recipeconditions.api.condition.util.RecipeCondsUtil;
import vazkii.patchouli.api.PatchouliAPI;

public class BSMConditions {
    public static void init(){
        PatchouliAPI.get().setConfigFlag("bsm_final_broom", Besmirchment.config.enableFinalBroom);
        PatchouliAPI.get().setConfigFlag("bsm_witchy_dye", Besmirchment.config.enableWitchyDye);
        PatchouliAPI.get().setConfigFlag("bsm_elite_coffin", Besmirchment.config.enableEliteCoffin);
        PatchouliAPI.get().setConfigFlag("bsm_love_potion", Besmirchment.config.enableLovePotion);
        Util.register(RecipeConds.RECIPE_CONDITION, "bsm_config", RecipeCondsUtil.stringParam(BSMConditions::getOption));
    }

    public static boolean getOption(String key){
        switch (key) {
            case "final_broom":
                return Besmirchment.config.enableFinalBroom;
            case "witchy_dye":
                return Besmirchment.config.enableWitchyDye;
            case "elite_coffin":
                return Besmirchment.config.enableEliteCoffin;
            case "love_potion":
                return Besmirchment.config.enableLovePotion;
        }
        return false;
    }
}
