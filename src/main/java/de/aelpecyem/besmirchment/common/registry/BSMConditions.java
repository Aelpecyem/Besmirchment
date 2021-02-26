package de.aelpecyem.besmirchment.common.registry;

import de.aelpecyem.besmirchment.common.Besmirchment;
import io.github.ytg1234.recipeconditions.api.RecipeConds;
import io.github.ytg1234.recipeconditions.api.condition.util.RecipeCondsUtil;

public class BSMConditions {
    public static void init(){
      //  PatchouliAPI.get().setConfigFlag("bsm_final_broom", getOption("final_broom"));
        Util.register(RecipeConds.RECIPE_CONDITION, "bsm_config", RecipeCondsUtil.stringParam(BSMConditions::getOption));
    }

    public static boolean getOption(String key){
        if ("final_broom".equals(key)) {
            return Besmirchment.config.enableFinalBroom;
        }
        return false;
    }
}
