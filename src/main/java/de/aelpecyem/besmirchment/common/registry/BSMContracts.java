package de.aelpecyem.besmirchment.common.registry;

import moriyashiine.bewitchment.api.registry.Contract;
import moriyashiine.bewitchment.common.registry.BWRegistries;

public class BSMContracts {
    public static final Contract CONQUEST = new Contract(true);

    public static void init(){
        BSMUtil.register(BWRegistries.CONTRACTS,"conquest", CONQUEST);
    }
}
