package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.Besmirchment;
import io.github.ytg1234.recipeconditions.api.RecipeConds;
import io.github.ytg1234.recipeconditions.api.condition.util.RecipeCondsUtil;
import vazkii.patchouli.api.PatchouliAPI;

public class BSMConditions {
    public static void init(){
        PatchouliAPI.get().setConfigFlag("bsm_final_broom", Besmirchment.config.enableFinalBroom);
        PatchouliAPI.get().setConfigFlag("bsm_witchy_dye", Besmirchment.config.enableWitchyDye);
        Util.register(RecipeConds.RECIPE_CONDITION, "bsm_config", RecipeCondsUtil.stringParam(BSMConditions::getOption));
    }

    public static boolean getOption(String key){
        if ("final_broom".equals(key)) {
            return Besmirchment.config.enableFinalBroom;
        }else if ("witchy_dye".equals(key)){
            return Besmirchment.config.enableWitchyDye;
        }
        return false;
    }
}
